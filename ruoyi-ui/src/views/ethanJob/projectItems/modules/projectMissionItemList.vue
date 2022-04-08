<template>
  <el-row :gutter="20" >
    <el-col :span="6">
      <el-input
        v-model="form.missionItemName"
        placeholder="请输入项目名称"
        clearable
        size="small"
        prefix-icon="el-icon-search"
        style="margin-bottom: 20px"
      />
    </el-col>
    <el-col :span="3">
      <el-button :icon="Search" @click="getMissionItems(projectItemId)">查询</el-button>
    </el-col>
    <el-col :span="4">
      <el-button type="primary" @click="newProjectMissionItem">新增任务</el-button>
    </el-col>
  </el-row>
  <el-row :gutter="20" >
    <el-table
      :data="tableData"
      style="width: 100%"
      :row-class-name="tableRowClassName"
    >
      <el-table-column prop="missionItemName" label="任务名称" width="200" />
      <el-table-column prop="finishTimeEnd" label="结束时间" />
      <el-table-column prop="missionItemStatus" label="项目状态" >
        <template #default="scope">
          <div style="display: flex; align-items: center">
            <el-icon><timer /></el-icon>
            &nbsp;
            <el-select v-model="scope.row.missionItemStatus"
              v-bind:class="{ ongoing: (scope.row.missionItemStatus == 1),
                timeout: (scope.row.missionItemStatus == 2),
                failure: (scope.row.missionItemStatus == 3),
                complete: (scope.row.missionItemStatus == 4),
                delay: (scope.row.missionItemStatus == 5) } "
              class="missionItemStatusSelect" placeholder="Select" size="small" style="width: 28%;"
              @change="value=>{missionItemStatusChange(value, scope.row)}">
              <el-option
                v-for="item in mission_item_status"
                :key="item.value"
                :label="item.label"
                :value="parseInt(item.value)"
              >
                {{item.label}}
              </el-option>
            </el-select>
          </div>
        </template>
      </el-table-column>
    </el-table>
  </el-row>
  <project-mission-item-dialog ref="ProjectMissionItemDialogRef" @ok="getMissionItems"/>
</template>

<script setup name="ProjectMissionItemList">
import { Search } from '@element-plus/icons-vue'
import { addProjectItems } from "@/api/ethanBusiness/projectItems";
import ProjectMissionItemDialog from "./projectMissionItemDialog"
import { listProjectMissionItem, updateProjectMissionItem } from "@/api/ethanBusiness/projectMissionItem";

const { proxy } = getCurrentInstance();
const { mission_item_status } = proxy.useDict("mission_item_status");
let ProjectMissionItemDialogRef = $ref(null)

let tableData = $ref([])
const open = ref(false);
const title = ref("");
let projectItemId = $ref("");

const data = reactive({
  form: {
    projectItemId: undefined,
    missionItemName: undefined,
    missionItemTime: undefined,
    missionItemRemark: undefined
  }
});

const { form } = toRefs(data);
defineExpose({
  getMissionItems
});
function getMissionItems(projectId) {
  projectItemId = projectId
  form.value.projectItemId = projectId
  listProjectMissionItem(form).then(response => {
    tableData = response.rows
  });
}
function newProjectMissionItem() {
   ProjectMissionItemDialogRef.openDialog(form.value.projectItemId)
}
function missionItemStatusFormat(missionItemStatus) {
  for (let i = 0; i < mission_item_status.value.length; i++) {
    const element = mission_item_status.value[i];
    if (element.value == missionItemStatus) {
      return element.label
    }
  }
}
function missionItemStatusChange(missionItemStatus, record) {
  // 更新状态
  updateProjectMissionItem(record).then(response => {
  });
}

</script>
<style scoped>
.el-table :deep() .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-lighter);
}
.el-table :deep() .success-row {
  --el-table-tr-bg-color: var(--el-color-success);
}
/* 修改的是el-input的样式
一种方法是设置最里层el-input__inner的背景色 外层的两级父元素设置为透明色
另一种方法是从el-select到el-input__inenr的背景色都设置为需要的颜色 */
.ongoing :deep() .el-input__inner {
  color: var(--el-color-success);
  border:1px solid var(--el-color-success);
}
/* 修改的是el-input上下的小图标的颜色 */
.ongoing :deep() .el-select__caret{
  color:var(--el-color-success);
}
/* el-input聚焦的时候 外层的border会有一个样式 */
.ongoing :deep() .el-input.is-focus .el-input__inner{
  border:1px solid var(--el-color-success);
}
.timeout :deep() .el-input__inner {
  color: var(--el-color-danger);
  border:1px solid var(--el-color-danger);
}
.timeout :deep() .el-select__caret{
  color:var(--el-color-danger);
}
.timeout :deep() .el-input.is-focus .el-input__inner{
  border:1px solid var(--el-color-danger);
}
.failure :deep() .el-input__inner {
  color: var(--el-color-info);
  border:1px solid var(--el-color-info);
}
.failure :deep() .el-select__caret{
  color:var(--el-color-info);
}
.failure :deep() .el-input.is-focus .el-input__inner{
  border:1px solid var(--el-color-info);
}
.complete :deep() .el-input__inner {
  color: var(--el-color-success);
  border:1px solid var(--el-color-success);
}
.complete :deep() .el-select__caret{
  color:var(--el-color-success);
}
.complete :deep() .el-input.is-focus .el-input__inner{
  border:1px solid var(--el-color-success);
}
.delay :deep() .el-input__inner {
  color: var(--el-color-danger);
  border:1px solid var(--el-color-danger);
}
.delay :deep() .el-select__caret{
  color:var(--el-color-danger);
}
.delay :deep() .el-input.is-focus .el-input__inner{
  border:1px solid var(--el-color-danger);
}


</style>
