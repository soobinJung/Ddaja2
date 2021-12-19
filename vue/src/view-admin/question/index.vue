<template>
  <div class = "main-container">
    <div class = "main-title">
      <font class = "font1">Questions Manager</font>
      <el-button
        icon   = "el-icon-circle-plus-outline"
        style  = "float:right; margin: 0 58px 0 0; width:100px; height :50px"
        @click = "insertDrawerStatus(true)"
      > 추가 </el-button>
      <el-button
        type   = "danger"
        icon   = "el-icon-delete"
        style  = "float:right; margin: 0 30px 0 0; width:100px; height :50px"
        @click = "deleteQuestion()"
      > 삭제 </el-button>
    </div>

    <div class = "div1" style = "padding:0px 0px 70px 29%;">
      <div style = "float:left; padding:1px 20px 0px 0px">
        <el-select 
          v-model     = "param.licenseID" 
          placeholder = "Select" 
          style       = "width:500px">
          <el-option
            v-for  = "item in licenseOptions"
            :key   = "item.value"
            :label = "item.label"
            :value = "item.value"
          />
        </el-select>

      </div>
      <div style = "float:left; padding:0px 20px 0px 0px">
        <el-input
          v-model             = "param.examYear"
          style               = "width:500px"
          placeholder         = "응시 년도를 입력 하세요."
          @keyup.enter.native = "clickSearch"
        />
      </div>
      <div style = "float:left; padding:0px 0px 0px 0px">
        <el-button
          type   = "primary"
          @click = "clickSearch"
        >검 색</el-button>
      </div>
    </div>

    <div style = "width: 90%; margin: 0 5% 0 5%;">
      <div style = "width: 40%; float:left;">
        <el-table
          ref   = "singleTable"
          :data = "examinationList"
          style = "width: 100%"
        >
          <el-table-column
            type  = "index"
            width = "50"
          />
          <el-table-column
            label = "시험명"
          >
            <template slot-scope = "props">
              <span 
                style  = "cursor: pointer;" 
                @click = "detailRound(props.row)">{{ props.row.name }}</span>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style = "width: 60%; float:left; padding : 0 0 0 5%">
        <el-table
          :data = "questionsList"
          style = "width: 100%"
        >
          <el-table-column type = "expand">
            <template slot-scope = "props">
              <div v-if = "props.row.content != ''">
                <v-html>{{ props.row.content }}</v-html>
              </div>
              <div 
                v-for = "(vo, index) in props.row.answers" 
                :key  = "vo.answersID"
                style = "padding:10px">
                <span
                  v-if = "index + 1 === props.row.answer"
                  style = "font-weight:bold; color : red"
                > {{ vo.answersID }} . {{ vo.answers }}</span>
                <span
                  v-else
                  style = "font-weight:bold"
                > {{ vo.answersID }} . {{ vo.answers }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column
            label = "NO"
            prop  = "no"
            width = "50"
          />
          <el-table-column
            label = "문제"
            prop  = "title"
          >
          <template slot-scope = "props">
            <span
              style = "font-size :15px">{{props.row.title}}</span>
          </template>
          </el-table-column>
          <el-table-column
            label = "점수"
            prop  = "score"
            width = "50"
          />
          <el-table-column
            label = "수정"
            width = "100"
          >
          <template slot-scope = "props">
            <i class="el-icon-setting" @click="updateDrawerStatus(true, props.row)"></i>
          </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <insertDrawer
      :popup-val          = "insertDrawerVal"
      @refresh            = "createSetting()"
      @close:insertdrawer = "insertDrawerStatus"
    />
    <updateDrawer
      :popup-val            = "updateDrawerVal"
      :update-question-data = "updateQuestionData"
      @refresh              = "createSetting()"
      @close:insertdrawer   = "updateDrawerStatus"
    />
  </div>
</template>

<script>
import Pagination   from '@/components/Pagination'
import insertDrawer from './components/insertDrawer'
import updateDrawer from './components/updateDrawer'
import { licenseList, fetchRoundList, fetchQuestionList } from '@/ddaja-api/admin/examination/Examination'
import _ from 'lodash'

export default {
  name: 'AdminQuestion'

  , components: {
    Pagination
    , insertDrawer
    , updateDrawer
  }

  , data() {
    return {
      licenseOptions: []
      , tableData: []
      , totalCount: 0
      , param: {
        licenseID: 0
        , examYear: ''
      }
      , selectList: []
      , insertDrawerVal: false
      , updateDrawerVal: false
      , updateQuestionData : {}
      , examinationList: []

      , questionsList: []
    }
  },

  created() {
    this.createSetting()
  },

  methods: {

    async createSetting() {
      await this.getLicense().then( response => {
        this.param.licenseID = this.licenseOptions[0].value
      })
      await this.getRoundList()
    }

    , async getLicense() {
      this.licenseOptions = []
      await licenseList().then(response => {
        response.items.forEach(x => {
          var type = (x.item.type === 'WRITING') ? '필기' : '실기'
          var label = '[' + type + '] ' + x.item.name
          this.licenseOptions.push({ value: x.item.id, label: label })
        })
      })
    }

    , async getRoundList() {
      var param = {
        licenseID  : this.param.licenseID
        , examYear : this.param.examYear
      }

      await fetchRoundList(param).then(response => {
        this.examinationList = []
        response.items.forEach(x => {
          const examDate = x.item.examDate
          const name = examDate.substring(0, 4) +
                    ' / ' +
                    examDate.substring(4, 6) +
                    ' / ' +
                    examDate.substring(6, 8) +
                    ' - ' + x.item.round + '회차 기출문제'

          this.examinationList.push({
            id          : x.item.id
            , examDate  : x.item.examDate
            , examYear  : x.item.examYear
            , inUse     : x.item.inUse
            , isCreated : x.item.isCreated
            , lid       : x.item.lid
            , round     : x.item.round
            , name      : name
          })
        })
      })
    }

    , async detailRound(round) {
      var param = {
        licenseID : round.lid
        , roundID : round.id
      }
      await fetchQuestionList(param).then(response => {
        this.questionsList = []

        response.items.forEach(x => {
          const answers = []

          if (x.item.answerOne != '' && x.item.answerOne != undefined) {
            answers.push({
              answersID : 1
              , answers : x.item.answerOne
            })
          }

          if (x.item.answerTwo != '' && x.item.answerTwo != undefined) {
            answers.push({
              answersID : 2
              , answers : x.item.answerTwo
            })
          }

          if (x.item.answerThr != '' && x.item.answerThr != undefined) {
            answers.push({
              answersID : 3
              , answers : x.item.answerThr
            })
          }

          if (x.item.answerTwo != '' && x.item.answerTwo != undefined) {
            answers.push({
              answersID : 4
              , answers : x.item.answerTwo
            })
          }

          if (x.item.answerFive != '' && x.item.answerFive != undefined) {
            answers.push({
              answersID : 5
              , answers : x.item.answerFive
            })
          }

          this.questionsList.push({
            id            : x.item.id
            , no          : x.item.no
            , title       : x.item.title
            , content     : x.item.content
            , score       : x.item.score
            , answers     : answers
            , answer      : x.item.answer
            , createdDate : x.item.createdDate
          })
        })
      })
    }

    , deleteQuestion() {
      this.$alert('삭제 기능 개발 예정')
    }

    , insertDrawerStatus(val) {
      this.insertDrawerVal = val
    }

    , updateDrawerStatus(val, row) {
      this.updateDrawerVal = val
      this.updateQuestionData = row
      console.log(row)
    }

    , async clickSearch() {
      await this.getRoundList()
    }
  }
}
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&display=swap');
.main-container{
width: 100%;
text-align: center;
padding: 30px;
overflow:auto;
background-color: rgb(255, 255, 255);
.main-title{
text-align: left;
margin-top: 5px;
margin-bottom: 30px;
color: rgb(0, 0, 0);
margin-left: 1%;
margin-right: 1%;
padding: 1.5% 2% 0% 4%;
.font1{
font-size: 50px;
font-family: 'Do Hyeon', sans-serif;
}
.font2{
font-size: 40px;
padding: 0 0 0 30px;
font-family: 'Do Hyeon', sans-serif;
}
}
.div1{
width: 90%;
margin: 0 5% 0 5%;
padding: 2% 0 3% 0;
.select1{
width: 200px;
}
.btn1{
width: 100px;
}
}
}
</style>
