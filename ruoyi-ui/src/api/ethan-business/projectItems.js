import request from '@/utils/request'

// 查询projectItems列表
export function listHomePage(query) {
  return request({
    url: '/ethan-business/projectItems/homeList',
    method: 'get',
    params: query
  })
}

// 查询projectItems列表
export function listProjectItems(query) {
  return request({
    url: '/ethan-business/projectItems/list',
    method: 'get',
    params: query
  })
}

// 查询projectItems列表
export function listProjectItemsNoPage(query) {
  return request({
    url: '/ethan-business/projectItems/listNoPage',
    method: 'get',
    params: query
  })
}

// 查询projectItems详细
export function getProjectItems(projectId) {
  return request({
    url: '/ethan-business/projectItems/' + projectId,
    method: 'get'
  })
}

// 新增projectItems
export function addProjectItems(data) {
  return request({
    url: '/ethan-business/projectItems',
    method: 'post',
    data: data
  })
}

// 修改projectItems
export function updateProjectItems(data) {
  return request({
    url: '/ethan-business/projectItems',
    method: 'put',
    data: data
  })
}

// 删除projectItems
export function delProjectItems(projectId) {
  return request({
    url: '/ethan-business/projectItems/' + projectId,
    method: 'delete'
  })
}
