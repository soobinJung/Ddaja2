import request from '@/utils/request'

export function userList(param) {
  return request({
    url: '/users',
    method: 'get',
    params: {}
  })
}

export function userListTotalCount(param) {
  return request({
    url: '/users',
    method: 'get',
    params: {}
  })
}

export function userDetail(param) {
  return request({
    url: '/users/' + param.id,
    method: 'get',
    params: {}
  })
}
