import request from '@/utils/request'

export function licenseList(param) {
  return request({
    url: '/licenses',
    method: 'get',
    params: {}
  })
}

export function wordList(param) {
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

export function wordInsert(param) {
  return request({
    url: '/words',
    method: 'post',
    params: {
      lID: param.lID,
      title: param.title || '',
      inUse: true
    }
  })
}

export function wordUpdate(param) {
  return request({
    url: '/words/' + param.id,
    method: 'patch',
    params: {
      lID: param.lID,
      title: param.title || '',
      inUse: true
    }
  })
}

export function wordDelete(param) {
  return request({
    url: '/words',
    method: 'delete',
    params: {
      id: param.id
    }
  })
}

export function wordListTotalCount(param) {
  return request({
    url: '/words/total-count',
    method: 'get',
    params: {
      licenseID: param.licenseID,
      name: param.name || '',
      inUse: true
    }
  })
}

export function wordQuestionList(param) {
  return request({
    url: '/word-questions',
    method: 'get',
    params: {
      wordID: param.wID,
      page: param.page || 0,
      size: param.size || 0,
      inUse: true
    }
  })
}

export function wordQuestionInsert(param) {
  return request({
    url: '/word-questions',
    method: 'post',
    async: true,
    params: {
      wID: param.wID,
      lID: param.lID,
      answer: param.answer || '',
      content: param.content || '',
      inUse: true
    }
  })
}

export function wordQuestionUpdate(param) {
  return request({
    url: '/word-questions/' + param.id,
    method: 'patch',
    params: {
      wID: param.wID,
      lID: param.lID,
      answer: param.answer || '',
      content: param.content || '',
      inUse: true
    }
  })
}

export function wordQuestionDelete(param) {
  return request({
    url: '/word-questions',
    method: 'delete',
    params: {
      id: param.id
    },
    dataType: 'json'
  })
}
