<template>
    <div class = "main-container">
        <el-drawer title      = ""
            :visible.sync = "popupVal"
            :with-header  = "false" 
            :before-close = "handleClose">
            <div style = "float:left; width: 100%;">
                <div style = "float:left; width: 40%; height: 100%;">
                    <div class = "div2">
                        <div style="height: 60px;"> 
                            <h1 style="text-align:left"> 토론
                            </h1> 
                        </div>
                        <div class = "div2-1"><span> {{question.no}} .  {{question.title}} ( {{question.score}} 점 )  </span></div>
                        <div 
                            v-if = "question.answerOne != '' " 
                            class = "div2-2">
                            <span>1 . {{question.answerOne}} </span>
                        </div>
                        <div 
                            v-if = "question.answerTwo != '' " 
                            class = "div2-2">
                            <span>2 .  {{question.answerTwo}} </span>
                        </div>
                        <div 
                            v-if = "question.answerThr != '' " 
                            class = "div2-2">
                            <span>3 .  {{question.answerThr}} </span>
                        </div>
                        <div 
                            v-if = "question.answerFour != '' " 
                            class = "div2-2">
                            <span>4 .  {{question.answerFour}} </span>
                        </div>
                        <div 
                            v-if = "question.answerFive != '' " 
                            class = "div2-2">
                            <span>5 .  {{question.answerFive}} </span>
                        </div>                                                                
                    </div>
                </div>
                <div style = "float:left; width: 60%; height: 100%; padding : 50px 0 0 0">
                    <div style = "overflow:scroll; height: 780px; overflow-x: hidden">
                        <div 
                            v-for = "item in questionReplyList" 
                            :key  = "item.key" 
                            style = "padding: 2%;"> 
                            <div style = "">
                                <div style = "text-align:left; padding: 0 1% 1% 0;">
                                    <div style = "float : left">
                                        <span style = "float:left"> {{item.uid}} ( {{item.createdDate}} )</span>
                                    </div>
                                    <div style = "float : right; width: 100px;">
                                        <el-button 
                                            style  = "width: 90px;" 
                                            @click = "updateLikeCount(item, 'dislike')" 
                                            > 비공감 {{item.dislikeCount}} 
                                        </el-button>
                                    </div>
                                    <div style = "float : right">
                                        <el-button 
                                            style  = "width: 90px;" 
                                            @click = "updateLikeCount(item, 'like')"
                                            > 공감 {{item.likeCount}}
                                        </el-button>
                                    </div>
                                </div>
                                <div style = "padding: 30px  0 0 0">
                                    <p style = "text-align : left; font-size : 13px">
                                    {{item.debate}}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style = "width: 100%; overflow:auto; padding : 20px 0 20px 0;">
                        <div style = "width:10%; float: left;">
                            <p style = "padding : 0 0 100px 0">토론</p>
                        </div>
                        <div style = "width:68%; float: left; padding : 5px 0 0 0">
                            <el-input 
                                placeholder         = "생각을 공유해보세요" 
                                v-model             = "replyInput"
                                @keyup.enter.native = "saveQuestionReply"
                                ></el-input>
                        </div>
                        <div style = "width: 20%; float: left; padding : 5px 0 0 0">
                            <el-button 
                                @click = "saveQuestionReply"
                            > 저장 </el-button>
                        </div>
                    </div>
                </div>
            </div> 
        </el-drawer>
    </div>
</template>

<script>
import { getQuestion, getReply, saveReply, updateReply } from '@/ddaja-api/user/explore/communication/Communication.js'

export default {
    name: 'community'

    , data() {
        return { 
            reply : ""
            , questionReplyList : []
            , replyInput : ''
            , question : {
                id            : 0
                , no          : 0
                , score       : 0
                , answer      : 0
                , title       : ""
                , content     : ""
                , answerOne   : ""
                , answerTwo   : "-"
                , answerThr   : "-"
                , answerFour  : "-"
                , answerFive  : ""
                , created     : false
                , createdDate : ''
                , inUse       : true
                , isCreated   : false
                , lid         : 1
                , sid         : 1
                , rid         : 1
            }
            
        }
    }

    , props: {
        popupVal : {
            type : Boolean
            , defalut: false
        }
        , questionData    : {
            type : Object
            , defalut : function (){
                return {
                    id            : 0
                    , no          : 0
                    , score       : 0
                    , answer      : 0
                    , title       : ""
                    , content     : ""
                    , answerOne   : ""
                    , answerTwo   : "-"
                    , answerThr   : "-"
                    , answerFour  : "-"
                    , answerFive  : ""
                    , created     : false
                    , createdDate : ''
                    , inUse       : true
                    , isCreated   : false
                    , lid         : 1
                    , sid         : 1
                    , rid         : 1
                }
            }
        }
    }

    , methods: {
        async fetchList (){
            await this.setQuestion()
            await this.setReply()
        }

        , async setQuestion(){
            await getQuestion({id : this.questionData.id}).then( response => {
                this.question = response.item
            })
        }

        , async setReply(){
            this.questionReplyList = []

            await getReply({qID : this.questionData.id}).then( response => {
                response.items.forEach( x => {
                    this.questionReplyList.push({
                        id             : x.item.id
                        , debate       : x.item.debate
                        , dislikeCount : x.item.dislikeCount
                        , dpID         : x.item.dpID
                        , created      : x.item.created
                        , inUse        : x.item.inUse === '1' ? true : false
                        , isCreated    : x.item.isCreated
                        , likeCount    : x.item.likeCount
                        , qid          : x.item.qid
                        , uid          : x.item.uid
                        , createdDate  : x.item.createdDate
                    })
                })
            })
        }

        , async saveQuestionReply (){
            if(this.replyInput == ''){
                alert('의견을 입력해주세요')
            }

            await saveReply({
                dpID           : 0
                , qID          : this.questionData.id
                , uID          : 1
                , debate       : this.replyInput
                , likeCount    : 0
                , dislikeCount : 0
                , inUse        : 1
            }).then( response => {
                this.replyInput = ''
                this.setReply()
            })
        }

        , async updateLikeCount ( reply, status ){
            if(status === 'like'){
                reply.likeCount = (reply.likeCount + 1)
            }

            if(status === 'dislike'){
                reply.dislikeCount = (reply.dislikeCount + 1)
            }

            await updateReply(reply).then()
        }

        , popupClose() { 
            this.questionReplyList = []
            this.$emit('close:community', false) 
        }

        , handleClose(){
            this.popupClose()
        }
    }

    , watch : {
        popupVal ( val ){
            if(val){
                this.fetchList();
            }
        }
    }
}

</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&display=swap');

.main-container{
    width: 100%; 
    .div2{  
        width: 93%;
        padding: 2%;
        height: inherit; 
        float: left;
        margin: 1% 3% 1% 3%;
        .div2-1{ 
            float: left;
            width: inherit;
            padding: 7% 1% 7% 1%;
            font-size: 20px;
            text-align: left;
            font-weight: bold;
            span{
                float: left;
            } 
        }
        .div2-2{ 
            float: left;
            width: inherit;
            padding: 1.3%;
            font-size: 13px;
            font-weight: bold;
            text-align: left;
            span{
                padding: 2.5% 0 0 1%;
                font-size: 16px;
                float: left;
            } 
        }
    }
}  

::v-deep .el-drawer{
    width: 85% !important;
}
</style>
