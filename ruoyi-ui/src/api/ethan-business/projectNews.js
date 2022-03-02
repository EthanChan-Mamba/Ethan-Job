import request from '@/utils/request'

// 测试项目动态
export function projectNews(query) {
  return request({
    url: '/testtest/user/list',
    method: 'get',
    params: query
  })
}
