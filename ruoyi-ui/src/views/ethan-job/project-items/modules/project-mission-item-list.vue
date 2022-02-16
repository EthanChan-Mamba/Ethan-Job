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
      <el-button :icon="Search" @click="getMissionItems">查询</el-button>
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
    </el-table>
  </el-row> 
  <project-mission-item-dialog ref="ProjectMissionItemDialogRef"/>
</template>

<script setup name="ProjectMissionItemList">
import { Search } from '@element-plus/icons-vue'
import { addProjectItems } from "@/api/ethan-business/projectItems";
import ProjectMissionItemDialog from "./project-mission-item-dialog.vue"
import { listProjectMissionItem } from "@/api/ethan-business/projectMissionItem";

const { proxy } = getCurrentInstance();
let ProjectMissionItemDialogRef = $ref(null)

const tableData = $ref([])
const open = ref(false);
const title = ref("");
const projectId = $ref("");

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
  console.log('projectId', projectId)
  form.value.projectItemId = projectId
  listProjectMissionItem(form).then(response => {
    console.log(response)
    tableData = response.rows
    // getList();
  });
}
function newProjectMissionItem() {
   ProjectMissionItemDialogRef.openDialog()
}
function tableRowClassName({ row, rowIndex }) {
  if (rowIndex === 1) {
    return 'warning-row'
  } else if (rowIndex === 3) {
    return 'success-row'
  }
  return ''
}

// getQuery();
</script>
<style scoped>
.el-table :deep() .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-lighter);
}
.el-table :deep() .success-row {
  --el-table-tr-bg-color: var(--el-color-success-lighter);
}
</style>
