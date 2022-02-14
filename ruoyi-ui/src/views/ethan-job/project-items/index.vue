<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!--项目数据-->
         <el-col :span="4" :xs="24">
            <div class="head-container">
               <el-input
                  v-model="deptName"
                  placeholder="请输入项目名称"
                  clearable
                  size="small"
                  prefix-icon="el-icon-search"
                  style="margin-bottom: 20px"
               />
            </div>
            <div class="head-container">
               <div class="infinite-list-wrapper" style="overflow: auto">
                  <ul
                     class="list"
                     infinite-scroll-disabled="disabled"
                  >
                     <li v-for="(item, index) in projectList" :key="index" class="list-item">
                        <el-row :gutter="20">
                           <el-col :span="24" :xs="24">
                              <span>{{ item.projectName }}</span>
                           </el-col>
                        </el-row>
                     </li>
                  </ul>
               </div>
            </div>
         </el-col>
         <el-col :span="20" :xs="24">
            <el-row :gutter="10" class="mb8">
               <el-col :span="1.5">
                  <el-button type="primary" @click="newProjectItem">新增项目</el-button>
               </el-col>
            </el-row>
            <el-row :gutter="10" class="mb8">
               <el-col :span="20">
                  <project-mission-item-card-module ref="ProjectMissionItemRef" />
               </el-col>
            </el-row>
         </el-col>
      </el-row>

      <project-item-dialog ref="projectItemDialogRef"/>
   </div>
</template>

<script setup name="ProjectItems">
import { listProjectItemsNoPage } from "@/api/ethan-business/projectItems";
import projectItemDialog from "./modules/project-item-dialog.vue"
import ProjectMissionItemCardModule from "./modules/project-mission-item-card.vue"

const { proxy } = getCurrentInstance();

const deptName = ref("");
const projectList = ref([]);
const projectItemDialogRef = ref(null)
let ProjectMissionItemRef = $ref(null)
let ProjectMissionItem = $ref(undefined)

const data = reactive({
});

const { } = toRefs(data);

/** 通过条件过滤节点  */
const filterNode = (value, data) => {
  if (!value) return true;
  return data.label.indexOf(value) !== -1;
};
/** 根据名称筛选部门树 */
watch(deptName, val => {
  proxy.$refs["deptTreeRef"].filter(val);
});
/** 查询任务 */
function getPorjectselect() {
   listProjectItemsNoPage().then(response => {
      projectList.value = response.rows;
      if (projectList.value.length != 0) {
         ProjectMissionItem = projectList.value[0]
         ProjectMissionItemRef.setMissionItem(ProjectMissionItem)
      }
   });
};
function newProjectItem() {
   projectItemDialogRef.value.openDialog()
}

getPorjectselect();
</script>


<style scoped lang="scss">
  .infinite-list-wrapper {
    height: 300px;
    text-align: center;
  }
  .infinite-list-wrapper .list {
    padding: 0;
    margin: 0;
    list-style: none;
  }

  .infinite-list-wrapper .list-item {
    display: flex;
    // align-items: center;
    // justify-content: center;
    line-height: 50px;
    padding: 0 20px;
    height: 50px;
    background: var(--el-color-primary-light-7);
    border-left: solid 2px var(--el-color-primary-light-7);
    color: #030852;
  }
  .infinite-list-wrapper .list-item:hover {
    border-left: solid 2px rgba(64 158 255 / 80%);
    background: var(--el-color-primary-light-3);
    cursor: pointer;
  }
  .infinite-list-wrapper .list-item + .list-item {
    margin-top: 10px;
  }
  .infinite-list-wrapper::-webkit-scrollbar {
    /*滚动条整体样式*/
    width : 8px;  /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
  }
  .infinite-list-wrapper::-webkit-scrollbar-thumb {
    /*滚动条里面小方块*/
    border-radius   : 10px;
    background-color: skyblue;
    background-image: -webkit-linear-gradient(
        45deg,
        rgba(255, 255, 255, 0.2) 25%,
        transparent 25%,
        transparent 50%,
        rgba(255, 255, 255, 0.2) 50%,
        rgba(255, 255, 255, 0.2) 75%,
        transparent 75%,
        transparent
    );
  }
  .infinite-list-wrapper::-webkit-scrollbar-track {
    /*滚动条里面轨道*/
    box-shadow   : inset 0 0 5px rgba(0, 0, 0, 0.2);
    background   : #ededed;
    border-radius: 10px;
  }
</style>