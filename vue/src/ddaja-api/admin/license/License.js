import request from '@/utils/request'

export function licenseList(param) {
  return request({
    url: '/licenses',
    method: 'get',
    params: {
      name: param.name,
      page: param.page,
      size: param.size,
      inUse: true
    }
  })
}

export function licenseById(param) {
  return request({
    url: '/licenses/' + param.id,
    method: 'get',
    params: {}
  })
}

export function licenseListTotalCount(param) {
  return request({
    url: '/licenses/total-count',
    method: 'get',
    params: {
      name: param.name,
      inUse: true
    }
  })
}

export function licenseDelete(param) {
  return request({
    url: '/licenses',
    method: 'delete',
    params: {
      id: param.id
    }
  })
}

export function insertLicense(param) {
  return request({
    url: '/licenses',
    method: 'post',
    params: {
      name: param.name,
      code: param.code,
      type: param.type,
      inUse: param.inUse,
      agency: param.agency,
      passScore: param.passScore
    }
  })
}

export function insertSubject(param) {
  return request({
    url: '/subject',
    method: 'post',
    params: {
      lID: param.lID,
      name: param.name,
      minScore: param.minScore,
      inUse: param.inUse

    }
  })
}

export function updateSubject(param) {
  return request({
    url: '/subject/' + param.id,
    method: 'patch',
    params: {
      id: param.id,
      lID: param.lID,
      name: param.name,
      minScore: param.minScore,
      inUse: true
    }
  })
}

export function updateLicense(param) {
  return request({
    url: '/licenses',
    method: 'patch',
    params: {
      id: param.id,
      code: param.code,
      name: param.name,
      agency: param.agency,
      type: param.type,
      passScore: param.passScore,
      inUse: true
    }
  })
}

export function licenseCodes(param) {
  return request({
    url: '/licenses/code',
    method: 'get',
    params: {}
  })
}
