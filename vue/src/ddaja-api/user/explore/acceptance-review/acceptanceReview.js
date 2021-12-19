import request from '@/utils/request'

export function fetchSuccessComment(param) {
  return request({
    url: '/success-comment',
    method: 'get',
    params: {
      licenseID: param.licenseID
    }
  })
}

export function insertSuccessComment(param) {
  return request({
    url: '/success-comment',
    method: 'post',
    params: {
      lID: param.lID,
      uID: param.uID,
      inUse: param.inUse,
      successTitle: param.successTitle,
      successComment: param.successComment,
      likeCount: param.likeCount,
      successScore: param.successScore
    }
  })
}
