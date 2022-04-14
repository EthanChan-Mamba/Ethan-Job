<template>
   <div class="app-container">

      <!-- 添加或修改菜单对话框 -->
      <el-dialog :title="title" v-model="open" width="680px" append-to-body>
         <el-form ref="projectRef" :model="form" :rules="rules" label-width="120px">
            <el-row>
               <el-col :span="24">
                  <el-form-item label="项目名称" prop="projectName">
                     <el-input v-model="form.projectName" placeholder="请输入项目名称" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="项目起止时间" prop="projectTime" required>
                     <div class="block">
                        <el-date-picker
                           v-model="form.projectTime"
                           type="datetimerange"
                           range-separator="~"
                           start-placeholder="项目开始时间"
                           end-placeholder="项目结束日期"
                        >
                        </el-date-picker>
                     </div>
                  </el-form-item>
               </el-col>
            </el-row>
         </el-form>
         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitForm">确 定</el-button>
               <el-button @click="cancel">取 消</el-button>
            </div>
         </template>
      </el-dialog>
   </div>
</template>

<script setup name="ProjectItemsDialog">
import { addProjectItems, updateProjectItems } from "@/api/ethanBusiness/projectItems";

const { proxy } = getCurrentInstance();

let open = $ref(false);
let title = $ref("");

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
	openDialog
})
/** 取消按钮 */
function cancel() {
  open = false;
}
/** 提交按钮 */
function submitForm() {
   proxy.$refs["projectRef"].validate(valid => {
      if (valid) {
         form.value.finishTimeStart = form.value.projectTime[0]
         form.value.finishTimeEnd = form.value.projectTime[1]
         if (form.value.projectId) {
            updateProjectItems(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open = false;
               getList();
            });
         } else {
            addProjectItems(form.value).then(response => {
               proxy.$modal.msgSuccess("新增成功");
               open = false;
            });
         }
      }
   });
}
/** 打开窗口 */
function openDialog(record) {
   open = true
   if (!record) {
      title = "新增项目"
   } else {
      title = "编辑项目"
      record.projectTime = [record.finishTimeStart, record.finishTimeEnd]
      form.value = record
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