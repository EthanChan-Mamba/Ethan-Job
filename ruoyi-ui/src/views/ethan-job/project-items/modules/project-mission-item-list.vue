<template>
  <el-row :gutter="20" >
    <el-col :span="6">
      <el-input
        v-model="deptName"
        placeholder="请输入项目名称"
        clearable
        size="small"
        prefix-icon="el-icon-search"
        style="margin-bottom: 20px"
      />
    </el-col>
    <el-col :span="3">
      <el-button :icon="Search" @click="getQuery">查询</el-button>
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
      <el-table-column prop="date" label="Date" width="180" />
      <el-table-column prop="name" label="Name" width="180" />
      <el-table-column prop="address" label="Address" />
    </el-table>
  </el-row> 
  <project-mission-item-dialog ref="ProjectMissionItemDialogRef"/>
</template>

<script setup name="ProjectMissionItemList">
import { Search } from '@element-plus/icons-vue'
import { addProjectItems } from "@/api/ethan-business/projectItems";
import ProjectMissionItemDialog from "./project-mission-item-dialog.vue"

const { proxy } = getCurrentInstance();
let ProjectMissionItemDialogRef = $ref(null)

const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
const open = ref(false);
const title = ref("");

const data = reactive({});

const {} = toRefs(data);
defineExpose({});
function getQuery() {}
function newProjectMissionItem() {
   ProjectMissionItemDialogRef.openDialog()
}
function tableRowClassName({ row, rowIndex }) {
  console.log(rowIndex)
  if (rowIndex === 1) {
    return 'warning-row'
  } else if (rowIndex === 3) {
    return 'success-row'
  }
  return ''
}
</script>
<style scoped>
.el-table /deep/ .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-lighter);
}
.el-table /deep/ .success-row {
  --el-table-tr-bg-color: var(--el-color-success-lighter);
}
</style>
