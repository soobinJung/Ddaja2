<template>
    <div >
        <el-dialog 
        :visible.sync = "popupVal" 
        :before-close = "handleClose" 
        width         = "1700px"
        top           = "2%"
        title         = "">
        <div class = "div1">
            <div class = "div1-1">
                <span class = "span1">{{roundName}} </span>
                <span class = "span2"> 정답 수  현황 : {{userNumber}} / {{totalNumber}} 개</span>
                <span class = "span2"> 점수 현황 : {{userScore}} / {{totalScore}} 점</span>
                <div style="float: right; padding : 40px 100px 0 0"> 
                    <el-radio-group v-model = "checkMode">
                        <el-radio-button label="1">모든 문제 확인</el-radio-button>
                        <el-radio-button label="2">틀린 문제만 확인</el-radio-button>
                        <el-radio-button label="3">맞은 문제만 확인</el-radio-button>
                    </el-radio-group>
                </div>
            </div> 
        </div> 
        <div 
            class = "div2" 
            v-for = "(quiz) in questionList" 
            :key  = "quiz.id" >
            <div class = "div2-1">
                <div class = "div2-1-1">
                    <el-button style="float:left" @click="communityPopupStatus(true, quiz)">{{quiz.no}}. {{quiz.title}} ( {{quiz.score}} 점 ) </el-button>
                </div> 
                <div class = "div2-1-2" v-if="quiz.content != ''">
                    <span class = "span1"> {{quiz.content}}  </span>  
                </div> 
                <div class = "div2-1-3" v-if = "quiz.answerOne != ''">
                    <span class = "span1"> 

                        <el-checkbox :disabled="quiz.answer != 1" v-model = "quiz.userAnswer[0]" @change = "checkAnswer(quiz, 0)">
                            1 . {{quiz.answerOne}}
                        </el-checkbox>
                    </span> 
                </div>  
                <div class = "div2-1-3" v-if = "quiz.answerTwo != ''">
                    <span class = "span1"> 
                        <el-checkbox :disabled="quiz.answer != 2" v-model = "quiz.userAnswer[1]" @change = "checkAnswer(quiz, 1)" >
                            2 . {{quiz.answerTwo}}
                        </el-checkbox> 
                    </span> 
                </div>  
                <!--:disabled="disabled"-->
                <div class = "div2-1-3" v-if = "quiz.answerThr != ''">
                    <span class = "span1"> 
                        <el-checkbox :disabled="quiz.answer != 3" v-model = "quiz.userAnswer[2]" @change = "checkAnswer(quiz, 2)" >
                            3 . {{quiz.answerThr}}
                        </el-checkbox> 
                    </span> 
                </div>  
                <div class = "div2-1-3" v-if = "quiz.answerFour != ''">
                    <span class = "span1"> 
                        <el-checkbox :disabled="quiz.answer != 4" v-model = "quiz.userAnswer[3]" @change = "checkAnswer(quiz, 3)" >
                            4 . {{quiz.answerFour}}
                        </el-checkbox> 
                    </span> 
                </div>  
                <div class = "div2-1-3" v-if = "quiz.answerFive != ''">
                    <span class = "span1"> 
                        <el-checkbox :disabled="quiz.answer != 5" v-model = "quiz.userAnswer[4]" @change = "checkAnswer(quiz, 4)" >
                            5 . {{quiz.answerFive}}
                        </el-checkbox> 
                    </span> 
                </div>  
            </div>
        </div>
        <span slot = "footer" class = "dialog-footer"> 
            <el-button @click = "handleClose()" type="danger" plain>닫 기</el-button> 
        </span>
        </el-dialog> 
        <community
            :popup-val       = "communityPopupVal"
            :question-data   = "questionData"
            @close:community = "communityPopupStatus"
        />
    </div> 
</template>

<script>
import community from '@/views/explore/communication'
import { fetchListByQuestions } from '@/ddaja-api/user/explore/examination/Examination.js'
import _ from 'lodash'

export default {
    name: 'gradingPopup'

    , data() {
        return { 
            communityPopupVal  : false
            , questionData     : {}
            , questionList     : []
            , questionListOrg  : []
            , checkMode        : 1
            , quizType         : 1
            , questionNumber   : 0
            , totalNumber      : 0
            , userNumber       : 0
            , userScore        : 0
            , totalScore       : 0
        }
    }

    , components : {
        community
    }

    , props: {
        popupVal : {
            type : Boolean
            , defalut: false
        } 
        , roundID    : {
            type : Number
            , defalut : 0
        }
        , roundName    : {
            type : String
            , defalut : ''
        } 
        , userQuestionResult    : {
            type : Array
            , defalut : function (){
                return [1, 2, 3, 4, 3, 2]
            }
        }
    }

    , watch: { 
        popupVal(val){
            if(val != 0){
                this.fetchList()
            }
        }
        , roundID(val){
            if(val != 0){
                this.fetchList()
            }
        }
        , checkMode (val) {
            this.questionResultCheckMode(val)
        }
    }

    , methods: { 
        
        async fetchList (){
            
            if(this.roundID === 0){ return }

            this.totalScore = 0
            this.totalNumber = 0
            this.userScore = 0
            this.userNumber = 0
            this.questionListOrg = []
            this.questionList = []

            let param = {
                licenseID   : 0
                , roundID   : this.roundID || 0
                , SubjectID :  0
            }
            await fetchListByQuestions(param).then( response => {
                let userQuestionResult = this.userQuestionResult

                response.items.forEach( (x, index) => {
                    let userAnswer = [false, false, false, false, false]
                    
                    let userResult = userQuestionResult[index]
                    userAnswer[userResult-1] = true

                    this.questionList.push({
                        id           : x.item.id
                        , no         : x.item.no
                        , title      : x.item.title
                        , content    : x.item.content
                        , answer     : x.item.answer
                        , answerOne  : x.item.answerOne
                        , answerTwo  : x.item.answerTwo
                        , answerThr  : x.item.answerThr
                        , answerFour : x.item.answerFour
                        , answerFive : x.item.answerFive
                        , userAnswer : userAnswer
                        , created    : x.item.created
                        , createdDate: x.item.createdDate
                        , inUse      : x.item.inUse
                        , isCreated  : x.item.isCreated
                        , score      : x.item.score
                        , lid        : x.item.lid
                        , rid        : x.item.rid
                        , sid        : x.item.sid
                    })
                })
                this.questionListOrg = this.questionList

                let questionListOrg = _.cloneDeep(this.questionListOrg);

                questionListOrg.forEach( x => {
                    this.totalScore  += x.score 
                    this.totalNumber += 1 
                    if(x.userAnswer[x.answer-1]){
                        this.userScore  += x.score 
                        this.userNumber += 1 
                    }
                })
            })
        }

        , questionResultCheckMode  ( mode ){

            mode = Number(mode)
            let questionListOrg = _.cloneDeep(this.questionListOrg);
            this.questionList = []
            
            // 모두
            if(mode === 1){
                this.questionList =  questionListOrg; 
            }

            // 틀린 것만
            if(mode === 2){
                this.questionList =  _.filter(questionListOrg, function(x) { 
                    return !x.userAnswer[x.answer-1] ; 
                });
            }

            // 맞은 것만
            if(mode === 3){
                this.questionList =  _.filter(questionListOrg, function(x) { 
                    return x.userAnswer[x.answer-1] ; 
                });
            }
        }

        , popupClose(val) { 
            this.$emit('close:examination', val) 
        }

        , handleClose(done) {
        this.$confirm('종료 하시겠습니까 ?')
            .then(_ => {  
                this.popupClose(false);
            })
            .catch(_ => {});
        } 
        
        , communityPopupStatus(val, data){
            if(val){
                // 토론창 열기 
                this.communityPopupVal = val
                this.questionData      = data
            }else{
                // 토론창 닫기
                this.communityPopupVal = val
            }
        }
    }
}
</script>
<style lang="scss" scoped>

@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&display=swap');
.div1{
    width: 100%;
    height: 30px; 
    .div1-1{ 
        width: 100%;
        float: left;
        .span1{
            float: left;
            font-size: 40px;
            font-family: 'Do Hyeon', sans-serif;
            color: black;
            padding: 0 0 0 5%;
        }
        .span2{
            float: left;
            font-size: 20px;
            font-family: 'Do Hyeon', sans-serif;
            color: black;
            padding: 1% 0 0 5%;
        }
    }
}
.div2{
    .div2-1{
        width: 93%;
        padding: 1%;
        height: inherit; 
        float: left;
        margin: 1% 3% 1% 3%;
        .div2-1-1{ 
            float: left;
            width: inherit;
            padding: 2%;
            font-size: 20px;
            font-weight: bold;
            .span1{
                float: left;
                text-align: left;
            } 
        }
        .div2-1-2{ 
            float: left;
            width: inherit;
            padding: 2%;
            font-size: 15px;
            font-weight: bold;
            .span1{
                float: left;
            } 
        }
        .div2-1-3{ 
            float: left;
            width: inherit;
            padding: 1.3%;
            font-size: 15px;
            font-weight: bold;
            .span1{
                padding-left: 5%;
                float: left;
            }
        }
    }
}
</style>