import request from '@/utils/request'

export function fetchRoundList(param) {
  return request({
    url: '/round'
    , method: 'get'
    , params: {
      licenseID  : param.licenseID || 0
      , examYear : param.examYear || ''
      , inUse    : true
    }
  })
}

export function fetchQuestionList(param) {
  return request({
    url: '/question'
    , method: 'get'
    , params: {
      licenseID : param.licenseID || 0
      , roundID : param.roundID   || 0
      , inUse   : true
    }
  })
}

export function licenseList() {
  return request({
    url: '/licenses'
    , method: 'get'
    , params: {}
  })
}

export function insertRound(param) {
  return request({
    url: '/round'
    , method: 'post'
    , params: {
      lID        : param.lID      || 0
      , examYear : param.examYear || 0
      , examDate : param.examDate || 0
      , round    : param.round    || 0
      , inUse    : param.inUse    || true
    }
  })
}

export function updateQuestion(param) {
  return request({
    url: '/question'
    , method: 'patch'
    , params: {
      id           : param.id         || 0
      , rID        : param.rid        || 0
      , lID        : param.lid        || 0
      , sID        : param.sid        || 0
      , no         : param.no         || 0
      , inUse      : param.inUse      ? 1 : 0
      , title      : param.title      || ''
      , content    : param.content    || ''
      , answerOne  : param.answerOne  || ''
      , answerTwo  : param.answerTwo  || ''
      , answerThr  : param.answerThr  || ''
      , answerFour : param.answerFour || ''
      , answerFive : param.answerFive || ''
      , score      : param.score      || 0
    }
  })
}

export function insertQuestion(param) {
  return request({
    url: '/question'
    , method: 'post'
    , params: {
      rID          : param.rID 
      , lID        : param.lID        || 1
      , sID        : param.sID        || 1
      , no         : param.no         || 1
      , inUse      : param.inUse      || true
      , title      : param.title      || ''
      , content    : param.content    || ''
      , answerOne  : param.answerOne  || ''
      , answerTwo  : param.answerTwo  || ''
      , answerThr  : param.answerThr  || ''
      , answerFour : param.answerFour || ''
      , answerFive : param.answerFive || ''
      , score      : param.score      || 0
    }
  })
}

export function getQuestion(param) {
  return request({
    url: '/question/' + param.id
    , method: 'get'
    , params: {}
  })
}