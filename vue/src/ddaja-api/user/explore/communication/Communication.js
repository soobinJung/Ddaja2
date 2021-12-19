import request from '@/utils/request'

export function getQuestion(param) {
    return request({
        url: '/question/' + param.id
        , method: 'get'
        , params: {}
    })
}


export function getReply(param) {
    return request({
        url: '/debate'
        , method: 'get'
        , params: {
            questionID : param.qID
            , page     : 1
            , size     : 100
        }
    })
}


export function saveReply(param) {
    return request({
        url: '/debate'
        , method: 'post'
        , params: {
            dpID           : param.dpID
            , qID          : param.qID
            , uID          : param.uID
            , debate       : param.debate
            , inUse        : param.inUse
            , likeCount    : param.likeCount
            , dislikeCount : param.dislikeCount
        }
    })
}


export function updateReply(param) {
    return request({
        url: '/debate/' +  param.id
        , method: 'patch'
        , params: {
            dpID           : param.dpID
            , qID          : param.qid
            , uID          : param.uid
            , debate       : param.debate
            , likeCount    : param.likeCount
            , dislikeCount : param.dislikeCount
        }
    })
}