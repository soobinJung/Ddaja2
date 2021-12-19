import request from '@/utils/request'

export function fetchLicenseList(param) {
  return request({
    url: '/licenses',
    method: 'get',
    params: {
      name: param.name,
      page: 0,
      size: 100,
      inUse: true
    }
  })
}
