<template>
   <div class="app-container">
      <el-row :gutter="20">
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" label-width="68px">
               <el-form-item label="用户名称" prop="createBy">
                  <el-input
                     v-model="queryParams.createBy"
                     placeholder="请输入用户名称"
                     clearable
                     style="width: 240px"
                     @keyup.enter="handleQuery"
                  />
               </el-form-item>
               <el-form-item label="项目名称" prop="projectName">
                  <el-input
                     v-model="queryParams.projectName"
                     placeholder="请输入项目名称"
                     clearable
                     style="width: 240px"
                     @keyup.enter="handleQuery"
                  />
               </el-form-item>
               <el-form-item label="创建时间" style="width: 308px;">
                  <el-date-picker
                     v-model="dateRange"
                     value-format="YYYY-MM-DD"
                     type="daterange"
                     range-separator="-"
                     start-placeholder="开始日期"
                     end-placeholder="结束日期"
                  ></el-date-picker>
               </el-form-item>
               <el-form-item>
                  <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                  <el-button icon="Refresh" @click="resetQuery">重置</el-button>
               </el-form-item>
            </el-form>


            <el-table v-loading="loading" :data="projectList">
               <el-table-column label="项目编号" align="center" key="projectId" prop="projectId" v-if="columns[0].visible" />
               <el-table-column label="项目名称" align="center" key="projectName" prop="projectName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
               <el-table-column label="开始日期" align="center" key="finishTimeStart" prop="finishTimeStart" v-if="columns[2].visible" :show-overflow-tooltip="true">
                  <template #default="scope">
                     <span>{{ parseTime(scope.row.finishTimeStart, '{y}-{m}-{d}') }}</span>
                  </template>
               </el-table-column>
               <el-table-column label="结束日期" align="center" key="finishTimeEnd" prop="finishTimeEnd" v-if="columns[3].visible" :show-overflow-tooltip="true">
                  <template #default="scope">
                     <span>{{ parseTime(scope.row.finishTimeEnd, '{y}-{m}-{d}') }}</span>
                  </template>
               </el-table-column>
               <el-table-column label="项目状态" align="center" key="projectStatus" prop="projectStatus" v-if="columns[5].visible"/>
               <el-table-column label="创建人" align="center" key="createBy" prop="createBy" v-if="columns[4].visible" width="120" />
               <el-table-column label="创建时间" align="center" prop="createTime" v-if="columns[6].visible" width="160">
                  <template #default="scope">
                     <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
                  </template>
               </el-table-column>
               <el-table-column label="更新人" align="center" key="updateBy" prop="updateBy" v-if="columns[4].visible" width="120" />
               <el-table-column label="更新时间" align="center" prop="updateTime" v-if="columns[6].visible" width="160">
                  <template #default="scope">
                     <span>{{ parseTime(scope.row.updateTime) }}</span>
                  </template>
               </el-table-column>
               <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
                  <template #default="scope">
                     <el-tooltip content="修改" placement="top" v-if="scope.row.userId !== 1">
                        <el-button
                           type="text"
                           icon="Edit"
                           @click="handleUpdate(scope.row)"
                           v-hasPermi="['system:user:edit']"
                        ></el-button>
                     </el-tooltip>
                     <el-tooltip content="删除" placement="top" v-if="scope.row.userId !== 1">
                        <el-button
                           type="text"
                           icon="Delete"
                           @click="handleDelete(scope.row.projectId)"
                           v-hasPermi="['system:user:remove']"
                        ></el-button>
                     </el-tooltip>
                  </template>
               </el-table-column>
            </el-table>
            <pagination
               v-show="total > 0"
               :total="total"
               v-model:page="queryParams.pageNum"
               v-model:limit="queryParams.pageSize"
               @pagination="getList"
            />
         </el-col>
      </el-row>
      <project-item-dialog ref="projectItemDialogRef"/>
   </div>
</template>

<script setup name="projectList">
import { listProjectItems, delProjectItems } from "@/api/ethanBusiness/projectItems";
import projectItemDialog from "../projectItems/modules/projectItemDialog.vue";
const { proxy } = getCurrentInstance();
let projectList = $ref([]);
const projectItemDialogRef = $ref(null);
let loading = $ref(true);
let dateRange = $ref([]);
let total = $ref(0);
// 列显隐信息
const columns = ref([
  { key: 0, label: `项目编号`, visible: true },
  { key: 1, label: `项目名称`, visible: true },
  { key: 2, label: `开始日期`, visible: true },
  { key: 3, label: `结束日期`, visible: true },
  { key: 4, label: `项目状态`, visible: true },
  { key: 5, label: `创建人`, visible: true },
  { key: 6, label: `创建时间`, visible: true },
  { key: 7, label: `更新人`, visible: true },
  { key: 8, label: `更新时间`, visible: true }
]);
const data = reactive({
   queryParams: {
      pageNum: 1,
      pageSize: 10,
      createBy: undefined,
      projectName: undefined
   }
});
const { queryParams } = toRefs(data);
function handleQuery () {
   listProjectItems(proxy.addDateRange(queryParams.value, dateRange)).then(response => {
      projectList = response.rows
      total = response.rows.length
      loading = false
   });
};
function handleUpdate(record) {
   projectItemDialogRef.openDialog(record)
}
function handleDelete(projectId) {
   delProjectItems(projectId).then(response => {
      proxy.$modal.msgSuccess("删除成功");
   });
}
function resetQuery () {
   dateRange = []
   proxy.resetForm("queryRef");
   handleQuery()
};
handleQuery()
</script>
