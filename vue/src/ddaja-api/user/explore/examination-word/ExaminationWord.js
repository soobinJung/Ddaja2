import request from '@/utils/request'

export function fetchList(param) {
  return request({
    url: '/words',
    method: 'get',
    params: {
      licenseID: param.licenseID,
      name: param.name || '',
      page: param.page || 0,
      size: param.size || 0,
      inUse: true
    }
  })
}

export function fetchListTotalCount(param) {
  return request({
    url: '/words/total-count',
    method: 'get',
    params: {
      licenseID: param.licenseID,
      name: param.name || ''
    }
  })
}

export function fetchWordQuestion(param) {
  return request({
    url: '/word-questions',
    method: 'get',
    params: {
      wordID: param.wordID,
      page: param.page || 0,
      size: param.size || 0,
      inUse: true
    }
  })
}
