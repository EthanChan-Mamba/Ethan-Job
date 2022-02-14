<template>
  <el-tabs type="border-card">
    <el-tab-pane label="任务列表">
      <project-mission-item-list ref="ProjectMissionItemListRef"/>
    </el-tab-pane>
    <el-tab-pane label="任务日报">Config</el-tab-pane>
    <el-tab-pane label="任务动态">Role</el-tab-pane>
  </el-tabs>
</template>

<script setup name="ProjectMissionItemsTags">
import { addProjectItems } from "@/api/ethan-business/projectItems";
import ProjectMissionItemList from "./project-mission-item-list.vue"

const { proxy } = getCurrentInstance();

const open = ref(false);
const title = ref("");
let ProjectMissionItemListRef = $ref(null)

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
defineExpose({
})

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