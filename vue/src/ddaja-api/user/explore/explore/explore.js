import request from '@/utils/request'

export function fetchLicense(param) {
  return request({
    url: '/licenses/' + param.licenseID,
    method: 'get',
    params: {
      licenseID: param.licenseID,
      inUse: true
    }
  })
}

export function fetchLicenseIf(param) {
  return request({
    url: '/license/if',
    method: 'get',
    params: {
      licenseID: param.licenseID
    }
  })
}

export function fetchSuccessComment(param) {
  return request({
    url: '/success-comment',
    method: 'get',
    params: {
      licenseID: param.licenseID
    }
  })
}
