<template>
  <!-- <el-tabs type="border-card"> -->
  <el-tabs v-model="tabValue" type="border-card" @tab-click="tabsChange">
    <el-tab-pane label="任务列表" name="projectMissionItem">
      <project-mission-item-list ref="ProjectMissionItemListRef"/>
    </el-tab-pane>
    <el-tab-pane label="任务日报" name="config">Config</el-tab-pane>
    <el-tab-pane label="任务动态" name="projectLog">
      <project-log ref="ProjectLogRef"/>
    </el-tab-pane>
  </el-tabs>
</template>

<script setup name="ProjectMissionItemsTags">
import { addProjectItems } from "@/api/ethan-business/projectItems";
import ProjectMissionItemList from "./project-mission-item-list.vue"
import ProjectLog from "./project-log.vue"

const { proxy } = getCurrentInstance();

const open = ref(false);
const title = ref("");
const tabValue = $ref('projectMissionItem')
let ProjectMissionItemListRef = $ref(null)
let ProjectLogRef = $ref(null)

const data = reactive({
   form: {
      projectName: undefined,
      projectTime: undefined,
   },
   rules: {
      projectName: [{ required: true, message: "项目名称不能为空", trigger: "blur" },],
      projectTime: [{ required: true, message: '项目起止时间不能为空', trigger: 'blur' }]
   },
});

const { form, rules } = toRefs(data);

let projectItem = $ref({});

defineExpose({
	setProjectItem
})
/** 赋值任务 */
function setProjectItem(pi) {
  projectItem = pi
  ProjectMissionItemListRef.getMissionItems(pi.projectId)
}
function tabsChange (tab, event) {
  console.log('tab', tab)
  if (tab = 'projectLog') {
    console.log('projectItem', projectItem)
    ProjectLogRef.getLog(projectItem)
  }
}

</script>

<style scoped>
.block {
  margin: 0 0 0 -118px;
  text-align: center;
  border-right: solid 1px var(--el-border-color-base);
  flex: 1;
}
.block:last-child {
  border-right: none;
}
.block .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
</style>