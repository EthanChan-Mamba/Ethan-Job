<template>
   <el-card class="box-card">
      <template #header>
         <div class="card-header">
            <span class="header-title">{{projectItem.projectName}}</span>
            <el-button type="danger" @click="delProjectItem">删除项目</el-button>
         </div>
      </template>
      <el-descriptions>
         <el-descriptions-item  label-class-name="desc-label" label="创建人：">{{projectItem.createBy}}</el-descriptions-item>
         <el-descriptions-item label="项目开始时间：">{{projectItem.finishTimeStart}}</el-descriptions-item>
         <el-descriptions-item label="项目结束时间：">{{projectItem.finishTimeEnd}}</el-descriptions-item>
         <el-descriptions-item label="项目状态：">{{projectItem.projectStatusContent}}</el-descriptions-item>
         <el-descriptions-item label="项目类型：">{{projectItem.projectType}}</el-descriptions-item>
      </el-descriptions>
      <project-mission-item-tags ref="ProjectMissionItemTagsRef"/>

   </el-card>
</template>

<script setup name="ProjectMissionItemCard">
import ProjectMissionItemTags from "./projectMissionItemTags.vue"

const { proxy } = getCurrentInstance();

const open = ref(false);
const title = ref("");
let ProjectMissionItemTagsRef = $ref(null)
let projectItem = $ref({});

// const data = reactive({
//    projectItem: {}
// });

// const { projectItem } = toRefs(data);
defineExpose({
	setProjectItem
})
/** 赋值任务 */
function setProjectItem(pi) {
   projectItem = pi
   if(projectItem.projectStatus === -1) {
      projectItem.projectStatusContent = '失效'
   } else if(projectItem.projectStatus === 0) {
      projectItem.projectStatusContent = '未开始'
   } else if(projectItem.projectStatus === 1) {
      projectItem.projectStatusContent = '进行中'
   } else if(projectItem.projectStatus === 2) {
      projectItem.projectStatusContent = '超时'
   } else if(projectItem.projectStatus === 3) {
      projectItem.projectStatusContent = '延期'
   } else if(projectItem.projectStatus === 4) {
      projectItem.projectStatusContent = '完成'
   }
   ProjectMissionItemTagsRef.setProjectItem(pi)
}
function delProjectItem() {
   proxy.$modal.confirm('确认要删除"' + projectItem.projectName + '"项目吗?').then(function () {
      // return runJob(row.jobId, row.jobGroup);
   }).then(() => {
      proxy.$modal.msgSuccess("删除成功");})
   .catch(() => {});
}

</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-header .header-title {
   font-size: 28px;
   font-weight: bold;
}

.desc-label {
   font-weight: bold;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 100%;
}
</style>