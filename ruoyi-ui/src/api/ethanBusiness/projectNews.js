import request from '@/utils/request'

// 测试项目动态
export function listProjectNewsByProjectId(query) {
  return request({
    url: '/ethan-business/project-news/list',
    method: 'get',
    params: query
  })
}
