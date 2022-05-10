import request from '@/utils/request'

// 查询任务项目个体列表
export function listProjectMissionItem(query) {
  return request({
    url: '/ethan-business/ProjectMissionItem/list',
    method: 'get',
    params: query
  })
}

// 首页查询任务项目个体列表
export function homepageListMissionListByUserid(query) {
  return request({
    url: '/ethan-business/ProjectMissionItem/homepageListMissionListByUserid',
    method: 'get',
    params: query
  })
}

// 查询任务项目个体列表
export function listMissionListByUserid(query) {
  return request({
    url: '/ethan-business/ProjectMissionItem/listMissionListByUserid',
    method: 'get',
    params: query
  })
}

// 查询任务项目个体列表
export function listNumsWithMissionList(query) {
  return request({
    url: '/ethan-business/ProjectMissionItem/listNumsWithMissionList',
    method: 'get',
    params: query
  })
}

// 查询任务项目个体详细
export function getProjectMissionItem(missionItemId) {
  return request({
    url: '/ethan-business/ProjectMissionItem/' + missionItemId,
    method: 'get'
  })
}

// 新增任务项目个体
export function addProjectMissionItem(data) {
  return request({
    url: '/ethan-business/ProjectMissionItem/add',
    method: 'post',
    data: data
  })
}

// 修改任务项目个体
export function updateProjectMissionItem(data) {
  return request({
    url: '/ethan-business/ProjectMissionItem',
    method: 'put',
    data: data
  })
}

// 删除任务项目个体
export function delProjectMissionItem(missionItemId) {
  return request({
    url: '/ethan-business/ProjectMissionItem/' + missionItemId,
    method: 'delete'
  })
}
