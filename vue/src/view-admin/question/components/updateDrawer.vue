<template>
    <div class = "main-container">
        <el-drawer
            v-loading     = "loading"
            :visible.sync = "popupVal"
            :with-header  = "false"
            :before-close = "popupClose"
            style         = "width:100%"
        >
            <div class = "div1">
                <span class="span1">Questions 수정</span>
                <div style="float:right; padding: 0 150px 80px 0">
                    <el-button
                        type="primary"
                        @click="onSubmit"
                    >수정</el-button>
                </div>
                <div style = "width:100%; float:left; margin: 10px 0 10px 0">
                    <div style = "width:20%; float:left">
                        <span style="float:right; padding: 10px 50px 0 0">NO</span> 
                    </div>
                    <div style = "width:80%; float:left">
                        {{qusetionInfo.no}}
                    </div>
                </div>
                <div style = "width:100%; float:left; margin: 10px 0 10px 0">
                    <div style = "width:20%; float:left">
                        <span style="float:right; padding: 10px 50px 0 0">title</span> 
                    </div>
                    <div style = "width:80%; float:left">
                        <el-input 
                            v-model = "qusetionInfo.title"
                            style   = "width: 850px;"
                        ></el-input>
                    </div>
                </div>
                <div style = "width:100%; float:left; margin: 10px 0 10px 0">
                    <div style = "width:20%; float:left">
                        <span style="float:right; padding: 10px 50px 0 0">content</span> 
                    </div>
                    <div style = "width:80%; float:left">
                        <el-input 
                            v-model = "qusetionInfo.content"
                            style   = "width: 850px;"
                        ></el-input>
                    </div>
                </div>
                <div style = "width:100%; float:left; margin: 10px 0 10px 0">
                    <div style = "width:20%; float:left">
                        <span style="float:right; padding: 10px 50px 0 0">answer</span> 
                    </div>
                    <div style = "width:80%; float:left">
                        <el-input 
                            v-model = "qusetionInfo.answer"
                            style   = "width: 850px;"
                        ></el-input>
                    </div>
                </div>
                <div style = "width:100%; float:left; margin: 10px 0 10px 0">
                    <div style = "width:20%; float:left">
                        <span style="float:right; padding: 10px 50px 0 0">answerOne</span> 
                    </div>
                    <div style = "width:80%; float:left">
                        <el-input 
                            v-model = "qusetionInfo.answerOne"
                            style   = "width: 850px;"
                        ></el-input>
                    </div>
                </div>
                <div style = "width:100%; float:left; margin: 10px 0 10px 0">
                    <div style = "width:20%; float:left">
                        <span style="float:right; padding: 10px 50px 0 0">answerTwo</span> 
                    </div>
                    <div style = "width:80%; float:left">
                        <el-input 
                            v-model = "qusetionInfo.answerTwo"
                            style   = "width: 850px;"
                        ></el-input>
                    </div>
                </div>
                <div style = "width:100%; float:left; margin: 10px 0 10px 0">
                    <div style = "width:20%; float:left">
                        <span style="float:right; padding: 10px 50px 0 0">answerThr</span> 
                    </div>
                    <div style = "width:80%; float:left">
                        <el-input 
                            v-model = "qusetionInfo.answerThr"
                            style   = "width: 850px;"
                        ></el-input>
                    </div>
                </div>
                <div style = "width:100%; float:left; margin: 10px 0 10px 0">
                    <div style = "width:20%; float:left">
                        <span style="float:right; padding: 10px 50px 0 0">answerFour</span> 
                    </div>
                    <div style = "width:80%; float:left">
                        <el-input 
                            v-model = "qusetionInfo.answerFour"
                            style   = "width: 850px;"
                        ></el-input>
                    </div>
                </div>
                <div style = "width:100%; float:left; margin: 10px 0 10px 0">
                    <div style = "width:20%; float:left">
                        <span style="float:right; padding: 10px 50px 0 0">answerFive</span> 
                    </div>
                    <div style = "width:80%; float:left">
                        <el-input 
                            v-model = "qusetionInfo.answerFive"
                            style   = "width: 850px;"
                        ></el-input>
                    </div>
                </div>
                <div style = "width:100%; float:left; margin: 10px 0 10px 0">
                    <div style = "width:20%; float:left">
                        <span style="float:right; padding: 10px 50px 0 0">inUse</span> 
                    </div>
                    <div style = "width:80%; float:left">
                        <el-switch
                            v-model        = "qusetionInfo.inUse"
                            active-color   = "#13ce66"
                            inactive-color = "#ff4949">
                        </el-switch>
                    </div>
                </div>
            </div>
        </el-drawer>
    </div>
</template>

<script>
import { getQuestion, updateQuestion } from '@/ddaja-api/admin/examination/Examination'

export default {
    name: 'AdminQuestion_Update'

    , components: {
    }

    , props: {
        popupVal: {
            type      : Boolean
            , defalut : false
        }
        , updateQuestionData: {
            type      : Object
            , defalut : function (){
                return {
                    id : 0
                }
            }
        }
    }
    
    , data() {
        return {
            loading : false
            , qusetionInfo : {
                id            : 39
                , no          : 6
                , score       : 2
                , title       : "소프트웨어 공학에서 모델링 (Modeling)과 관련한 설명으로 틀린 것은?("
                , content     : ""
                , answer      : 2
                , answerOne   : "개발팀이 응용문제를 이해하는 데 도움을 줄 수 있다."
                , answerTwo   : "유지보수 단계에서만 모델링 기법을 활용한다."
                , answerThr   : "개발될 시스템에 대하여 여러 분야의 엔지니어들이 공통된 개념을 공유하는 데 도움을 준다."
                , answerFour  : "절차적인 프로그램을 위한 자료흐름도는 프로세스 위주의 모델링 방법이다."
                , answerFive  : ""
                , created     : false
                , createdDate : 1639175968000
                , inUse       : true
                , isCreated   : false
                , rid         : 26
                , lid         : 1
                , sid         : 1
            }
        }
    }

    , created() {
    }

    , methods: {

        async setQuestion (){

            let param = {
                id : this.updateQuestionData.id
            }

            await getQuestion(param).then( response => {
                this.qusetionInfo = response.item
            })
        }

        , async onSubmit() {
            await updateQuestion(this.qusetionInfo).then( response => {
                this.popupClose()
                this.$alert('수정 성공')
            })
        }

        , popupClose() {
            this.$emit('refresh')
            this.$emit('close:insertdrawer', false)
        }
    }

    , watch : {
        popupVal (val){
            if(val){
                this.setQuestion()
            }
        }
    }
}
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Kirang+Haerang&display=swap');
.main-container{
  width: 100%;
  .div1{
    text-align: left;
    margin-top: 5px;
    margin-bottom: 30px;
    color: rgb(0, 0, 0);
    margin-left: 1%;
    margin-right: 1%;
    padding: 4% 2% 2% 4%;
    .span1{
        font-size: 50px;
        font-family: 'Do Hyeon', sans-serif;
    }
  }
  .div2{
    width : 100%;
    margin: 1% 4% 2% 4%;
    overflow:auto;
  .div2-1{
    width : 100%;
    float :left ;
    padding: 1% 1% 1% 0%;
    text-align : right;
    font-weight:bold;
    .div2-1-1{
      width : 5%;
      padding: 1% 3% 1% 6%;
      float :left ;
    }
    .div2-1-2{
      width : 82%;
      padding: 1% 1% 1% 1%;
      float :left ;
      text-align: left;
    }
  }
  .div2-2{
    width : 100%; text-align : left;float :left ; padding: 1% 10% 1% 10%;
  }
}
  .pointer{
      cursor:pointer;
  }
}
::v-deep .el-drawer{
  width: 70% !important;
}
</style>
