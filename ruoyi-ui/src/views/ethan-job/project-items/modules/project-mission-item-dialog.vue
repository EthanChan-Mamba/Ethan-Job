<template>
   <div class="app-container">

      <!-- 添加或修改菜单对话框 -->
      <el-dialog :title="title" v-model="open" width="680px" append-to-body>
         <el-form ref="projectRef" :model="form" :rules="rules" label-width="120px">
            <el-row>
               <el-col :span="12">
                  <el-form-item label="任务名称" prop="missionItemName">
                     <el-input v-model="form.missionItemName" placeholder="请输入任务名称" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="任务起止时间" prop="missionItemTime" required>
                     <div class="block">
                        <el-date-picker
                           v-model="form.missionItemTime"
                           type="datetimerange"
                           range-separator="~"
                           start-placeholder="任务开始时间"
                           end-placeholder="任务结束日期"
                        >
                        </el-date-picker>
                     </div>
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="20">
                  <el-form-item label="任务描述" prop="missionItemRemark">
                     <el-input
                        v-model="form.missionItemRemark"
                        :rows="4"
                        resize="none"
                        type="textarea"
                        placeholder="请输入任务描述"
                     />
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

<script setup name="ProjectMissionItemsDialog">
import { addProjectMissionItem } from "@/api/ethan-business/projectMissionItem";

const { proxy } = getCurrentInstance();
const emit = defineEmits();

const open = $ref(false);
const title = ref('');
const dialogProjectItemId = $ref('');

const data = reactive({
   form: {
      missionItemName: undefined,
      missionItemTime: undefined,
      missionItemRemark: undefined
   },
   rules: {
      missionItemName: [{ required: true, message: "任务名称不能为空", trigger: "blur" },],
      missionItemTime: [{ required: true, message: '任务起止时间不能为空', trigger: 'blur' }]
   },
});

const { form, rules } = toRefs(data);
defineExpose({
	openDialog
})
/** 取消按钮 */
function cancel() {
  open.value = false;
}
/** 提交按钮 */
function submitForm() {
   proxy.$refs["projectRef"].validate(valid => {
      if (valid) {
         form.value.finishTimeStart = form.value.missionItemTime[0]
         form.value.finishTimeEnd = form.value.missionItemTime[1]
         addProjectMissionItem(form.value).then(response => {
            if (response.code == 200) {
               proxy.$modal.msgSuccess("新增成功");
               open.value = false;
               emit('ok', dialogProjectItemId)
            }
         });
         // if (form.value.menuId != undefined) {
         //    updateMenu(form.value).then(response => {
         //       proxy.$modal.msgSuccess("修改成功");
         //       open.value = false;
         //       getList();
         //    });
         // } else {
         //    addProjectMissionItem(form.value).then(response => {
         //       proxy.$modal.msgSuccess("新增成功");
         //       open.value = false;
         //       getList();
         //    });
         // }
      }
   });
}
/** 打开窗口 */
function openDialog(projectItemId) {
   dialogProjectItemId = projectItemId
   open = true;
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
