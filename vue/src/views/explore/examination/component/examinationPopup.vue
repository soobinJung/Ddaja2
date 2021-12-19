<template>
    <div>
        <el-dialog 
        :visible.sync = "popupVal" 
        :before-close = "handleClose" 
        width         = "1700px"
        top           = "2%"
        title         = "">
        <div class = "div1">
            <div class = "div1-1">
                <span class = "span1">{{roundName}}</span>
            </div> 
        </div> 
        <div 
            class = "div2" 
            v-for = "(quiz) in questionList" 
            :key  = "quiz.id" >
            <div class = "div2-1">
                <div class = "div2-1-1">
                    <span class = "span1"> {{quiz.no}}. {{quiz.title}}  </span>  
                </div> 
                <div class = "div2-1-2" v-if="quiz.content != ''">
                    <span class = "span1"> {{quiz.content}}  </span>  
                </div> 
                <div class = "div2-1-3" v-if = "quiz.answerOne != ''">
                    <span class = "span1"> 
                        <el-checkbox v-model = "quiz.answer[0]" @change = "checkAnswer(quiz, 0)">
                            1 . {{quiz.answerOne}}
                        </el-checkbox>
                    </span> 
                </div>  
                <div class = "div2-1-3" v-if = "quiz.answerTwo != ''">
                    <span class = "span1"> 
                        <el-checkbox v-model = "quiz.answer[1]" @change = "checkAnswer(quiz, 1)" >
                            2 . {{quiz.answerTwo}}
                        </el-checkbox> 
                    </span> 
                </div>  
                <div class = "div2-1-3" v-if = "quiz.answerThr != ''">
                    <span class = "span1"> 
                        <el-checkbox v-model = "quiz.answer[2]" @change = "checkAnswer(quiz, 2)" >
                            3 . {{quiz.answerThr}}
                        </el-checkbox> 
                    </span> 
                </div>  
                <div class = "div2-1-3" v-if = "quiz.answerFour != ''">
                    <span class = "span1"> 
                        <el-checkbox v-model = "quiz.answer[3]" @change = "checkAnswer(quiz, 3)" >
                            4 . {{quiz.answerFour}}
                        </el-checkbox> 
                    </span> 
                </div>  
                <div class = "div2-1-3" v-if = "quiz.answerFive != ''">
                    <span class = "span1"> 
                        <el-checkbox v-model = "quiz.answer[4]" @change = "checkAnswer(quiz, 4)" >
                            5 . {{quiz.answerFive}}
                        </el-checkbox> 
                    </span> 
                </div>  
            </div>
        </div>
        <span slot = "footer" class = "dialog-footer"> 
            <el-button @click = "questionResultCheck()" type="danger" plain>채 점  하 기</el-button> 
        </span>
        </el-dialog> 
    </div> 
</template>

<script>
import { fetchListByQuestions } from '@/ddaja-api/user/explore/examination/Examination.js'

export default {
    name: 'Examination_Popup'

    , data() {
        return { 
            communityPopupVal : false
            , questionList    : []
            , userQuestionResult : []
        }
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
    }
    
    , watch: {
        roundID ( val ){
            if(val != 0){
                this.fetchList()
            }
        }
    }
    
    , methods: { 
        async fetchList(){
            let param = {
                licenseID   : 0
                , roundID   : this.roundID || 0
                , SubjectID :  0
            }
            await fetchListByQuestions(param).then( response => {
                response.items.forEach( x => {
                    this.questionList.push({
                        id           : x.item.id
                        , no         : x.item.no
                        , title      : x.item.title
                        , content    : x.item.content
                        , answerOne  : x.item.answerOne
                        , answerTwo  : x.item.answerTwo
                        , answerThr  : x.item.answerThr
                        , answerFour : x.item.answerFour
                        , answerFive : x.item.answerFive
                        , answer     : [false, false, false, false, false]
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
            })
        }

        , questionResultCheck (){

            this.questionList.forEach( x => {
                x.answer.forEach( (y, index) => {
                    if(y){
                        this.userQuestionResult.push(index+1)
                    }
                })
            })
            this.popupClose(false)
        }

        , checkAnswer (quiz, index){
            let answer = []
            quiz.answer.forEach( (x, i) => {
                answer.push(index === i ? true : false)
            })
            quiz.answer = answer
        }

        , popupClose(val) { 
            this.$emit('close:examination', this.userQuestionResult, val ) 
        }

        , handleClose() {
            this.$confirm('종료 하시겠습니까 ?')
                .then(_ => {  
                    this.popupClose(false);
                })
                .catch(_ => {});
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