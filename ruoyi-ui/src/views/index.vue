<template>
  <div class="app-container home">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <div class="dashboard-hello">{{ timeFix }},{{ user.nickName }}</div>
        </div>
      </template>
      <el-button @click="testNews">测试项目动态</el-button>
      <p class="front-card">任务数据如下：</p>
      
      <el-row :gutter="20">
          <el-col :span="8" :xs="24">
            <el-card class="card-item card-item-pending">
              <p class="card-title">今日待完成</p>
              <span class="card-number">6</span>
            </el-card>
          </el-col>
          <el-col :span="8" :xs="24">
            <el-card class="card-item card-item-overtime">
              <p class="card-title">超期未完成</p>
              <span class="card-number">6</span>
            </el-card>
          </el-col>
          <el-col :span="8" :xs="24">
            <el-card class="card-item" shadow="always">
              <p class="card-title">参与的项目</p>
              <span class="card-number">6</span>
            </el-card>
          </el-col>
      </el-row>
    </el-card>
    
    <el-card class="box-card-down">
      <template #header>
        <span>任务列表</span>
      </template>
      <div class="infinite-list-wrapper" style="overflow: auto">
        <ul
          class="list"
          infinite-scroll-disabled="disabled"
        >
          <li v-for="(item, index) in unfinishList" :key="index" class="list-item">
            <el-row :gutter="20">
              <el-col :span="8" :xs="24">
                <span>{{ index }}</span>
              </el-col>
              <el-col :span="8" :xs="24">
                <span>{{item.title}}</span>
              </el-col>
              <el-col :span="8" :xs="24">
              </el-col>
          </el-row>
          </li>
        </ul>
      </div>
    </el-card>
  </div>
</template>


<script>
import { timeFix } from '@/utils/util'
import { getUserProfile } from "@/api/system/user"
import { projectNews } from "@/api/ethan-business/projectNews"
// const version = ref('3.8.1')
export default {
  name: 'Index',
  components: {
  },
  data () {
    return {
      noMore: false,
      count: 10,
      // 用户信息
      user: {
        dept: {
          deptName: ''
        }
      },
      timeFix: timeFix(),
      roleGroup: {},
      postGroup: {},
      unfinishList: [
        {
          title: 'NIO',
          startTime: '2022-06-22 10:00:00',
          endTime: '2022-06-22 10:00:00'
        },
        {
          title: 'NIO',
          startTime: '2022-06-22 10:00:00',
          endTime: '2022-06-22 10:00:00'
        },
        {
          title: 'NIO',
          startTime: '2022-06-22 10:00:00',
          endTime: '2022-06-22 10:00:00'
        },
        {
          title: 'NIO',
          startTime: '2022-06-22 10:00:00',
          endTime: '2022-06-22 10:00:00'
        },
        {
          title: 'NIO',
          startTime: '2022-06-22 10:00:00',
          endTime: '2022-06-22 10:00:00'
        },
        {
          title: 'NIO',
          startTime: '2022-06-22 10:00:00',
          endTime: '2022-06-22 10:00:00'
        }
      ]
    }
  },
  computed: {
  },
  created () {
    this.getUser()
  },
  mounted () {},
  methods: {
    // 获取用户信息
    getUser () {
      getUserProfile().then(response => {
        this.user = response.data
        this.roleGroup = response.roleGroup
        this.postGroup = response.postGroup
      })
    },
    testNews () {
      projectNews({missionItemId: 1, missionItemName: 'helo'}).then(response => {
        console.log('projectNews', response)
      })
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  padding: 2% 20%;
  .front-card {
    color: #58A;
    font-size: 18px;
    font-weight: bold;
  }
  .date-text {
    color: #444;
  }
  .box-card {
    border-radius: 10px;
    width: 100%;
  }
  .box-card-down {
    border-radius: 10px;
    margin-top: 20px;
    width: 100%;
  }
  .card-item {
    border-radius: 16px;
  }
  .card-item-pending {
    border-radius: 16px;
    color: #030852;
    border: solid 1px var(--el-color-primary);
    background-color: var(--el-color-primary-light-2);
  }
  .card-item-pending:hover {
    box-shadow: 0 0 7px 5px rgba(64 158 255 / 40%);
  }
  .card-item-overtime {
    border-radius: 16px;
    color: #FFFFFF;
    border: solid 1px #c0392b;
    background-color: #e74c3c ;
    // box-shadow: 0 0 10px 10px #f1c40f;
  }
  .card-item-overtime:hover {
    // box-shadow: #f1c40f;
    box-shadow: 0 0 7px 5px rgba(231 76 60 / 40%);
  }
  
  .card-title {
    font-weight: bold;
    font-size: 22px;
  }
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .dashboard-hello {
    word-wrap: break-word;
    color: #333;
    font-size: 20px;
  }
  .card-number {
    font-size: 40px;
    font-weight: bold;
  }
  .infinite-list-wrapper {
    height: 300px;
    text-align: center;
  }
  .infinite-list-wrapper .list {
    padding: 0;
    margin: 0;
    list-style: none;
  }

  .infinite-list-wrapper .list-item {
    display: flex;
    // align-items: center;
    // justify-content: center;
    line-height: 50px;
    padding: 0 20px;
    height: 50px;
    background: var(--el-color-primary-light-7);
    border-left: solid 2px var(--el-color-primary-light-7);
    color: #030852;
  }
  .infinite-list-wrapper .list-item:hover {
    border-left: solid 2px rgba(64 158 255 / 80%);
    background: var(--el-color-primary-light-3);
    cursor: pointer;
  }
  .infinite-list-wrapper .list-item + .list-item {
    margin-top: 10px;
  }
  .infinite-list-wrapper::-webkit-scrollbar {
    /*滚动条整体样式*/
    width : 8px;  /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
  }
  .infinite-list-wrapper::-webkit-scrollbar-thumb {
    /*滚动条里面小方块*/
    border-radius   : 10px;
    background-color: skyblue;
    background-image: -webkit-linear-gradient(
        45deg,
        rgba(255, 255, 255, 0.2) 25%,
        transparent 25%,
        transparent 50%,
        rgba(255, 255, 255, 0.2) 50%,
        rgba(255, 255, 255, 0.2) 75%,
        transparent 75%,
        transparent
    );
  }
  .infinite-list-wrapper::-webkit-scrollbar-track {
    /*滚动条里面轨道*/
    box-shadow   : inset 0 0 5px rgba(0, 0, 0, 0.2);
    background   : #ededed;
    border-radius: 10px;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

