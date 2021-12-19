import request from '@/utils/request'

export function fetchListByRounds(param) {
  return request({
    url: '/round',
    method: 'get',
    params: {
      licenseID: param.licenseID || '',
      examYear: param.examYear || '',
      inUse: true
    }
  })
}

export function fetchListByQuestions(param) {
  return request({
    url: '/question'
    , method: 'get'
    , params: {
      licenseID   : param.licenseID || 0
      , roundID   : param.roundID   || 0
      , SubjectID : param.SubjectID || 0
      , inUse     : true
    }
  })
}