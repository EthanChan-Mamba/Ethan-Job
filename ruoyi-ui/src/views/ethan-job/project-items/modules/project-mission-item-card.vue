<template>
   <el-card class="box-card">
      <template #header>
         <div class="card-header">
         <span class="header-title">{{missionItem.projectName}}</span>
         <!-- <el-button class="button" type="text" @click="newProjectMissionItem">新增任务</el-button> -->
         </div>
      </template>
      <el-descriptions>
         <el-descriptions-item  label-class-name="desc-label" label="创建人：">{{missionItem.createBy}}</el-descriptions-item>
         <el-descriptions-item label="项目开始时间：">{{missionItem.finishTimeStart}}</el-descriptions-item>
         <el-descriptions-item label="项目结束时间：">{{missionItem.finishTimeEnd}}</el-descriptions-item>
         <el-descriptions-item label="项目状态：">{{missionItem.projectStatusContent}}</el-descriptions-item>
         <el-descriptions-item label="项目类型：">{{missionItem.projectType}}</el-descriptions-item>
      </el-descriptions>
      <project-mission-item-tags ref="ProjectMissionItemTagsRef"/>

   </el-card>
</template>

<script setup name="ProjectMissionItemCard">
import ProjectMissionItemTags from "./project-mission-item-tags.vue"

const { proxy } = getCurrentInstance();

const open = ref(false);
const title = ref("");
let ProjectMissionItemTagsRef = $ref(null)
let missionItem = $ref({});

const data = reactive({
   // missionItem: und
});

const { } = toRefs(data);
defineExpose({
	setMissionItem
})
/** 赋值任务 */
function setMissionItem(mi) {
   missionItem = mi
   if(missionItem.projectStatus === -1) {
      missionItem.projectStatusContent = '失效'
   } else if(missionItem.projectStatus === 0) {
      missionItem.projectStatusContent = '未开始'
   } else if(missionItem.projectStatus === 1) {
      missionItem.projectStatusContent = '进行中'
   } else if(missionItem.projectStatus === 2) {
      missionItem.projectStatusContent = '超时'
   } else if(missionItem.projectStatus === 3) {
      missionItem.projectStatusContent = '延期'
   } else if(missionItem.projectStatus === 4) {
      missionItem.projectStatusContent = '完成'
   }
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