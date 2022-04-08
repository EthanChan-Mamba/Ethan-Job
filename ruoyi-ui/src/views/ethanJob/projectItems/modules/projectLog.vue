<template>
  <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto" infinite-scroll-immediate="false">
    <el-timeline>
      <el-timeline-item v-for="item in listData" :key="item.newsId" class="infinite-list-item" :timestamp="item.createTime" placement="top">
        <el-card>
          <h4>{{item.projectNewsName}}</h4>
          <p>{{item.projectNewsContent}}</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </ul>
</template>

<script setup name="ProjectLog">
import { listProjectNewsByProjectId } from "@/api/ethanBusiness/projectNews";

const { proxy } = getCurrentInstance();
let ProjectMissionItemDialogRef = $ref(null)

let listData = $ref([])
let projectId = $ref(null);

const data = reactive({});
let count = $ref(0)
const load = () => {
  console.log('load')
  count += 2
}

const { form } = toRefs(data);
defineExpose({
  getLog
});
function getLog (projectItem) {
  projectId = projectItem.projectId
  listProjectNewsByProjectId({ projectId: projectId}).then(response => {
    console.log('listProjectNewsByProjectId', response)
    listData = response.rows
  });
}

</script>
<style scope>
.infinite-list {
  height: 600px;
  padding: 0;
  margin: 0;
  list-style: none;
}
.infinite-list .infinite-list-item {
  display: flex;
  /* justify-content: center; */
  /* background: var(--el-color-primary-light-9); */
  /* margin: 10px; */
  color: var(--el-color-primary);
}
.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}
</style>
