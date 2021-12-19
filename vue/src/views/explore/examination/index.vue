<template>
  <div class = "main-container">
    <div class = "main-title">
      <font class = "font1">모의 고사</font>
      <font class = "font2"> - {{ licenseInfo.licenseName }} {{ licenseInfo.type === 'WRITING' ? "필기" : licenseInfo.type === "PERFORM" ? "실기" : "" }}</font>
    </div>
    <div class = "div2">
      <el-table
        :data = "tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
        style = "width: 100%; height: 100%;"
      >
        <el-table-column
          label = "Name"
          prop  = "name"
        />
        <el-table-column
          label = "examDate"
          prop  = "examDate"
        />
        <el-table-column align = "right">
          <template slot = "header" slot-scope = "{}">
            <el-input
              v-model     = "search"
              size        = "mini"
              placeholder = "Type to search"
            />
          </template>
          <template slot-scope = "scope">
            <el-button
              size   = "mini"
              @click = "examPopupStatus(scope.row, true)"
            >응시 하기</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <examinationPopup
      :popup-val         = "examPopupStatusVal"
      :roundID           = "roundID"
      :roundName         = "roundName"
      @close:examination = "examPopupStatus"
    />
    <gradingPopup
      :popup-val            = "gradingPopupStatusVal"
      :roundID           = "roundID"
      :roundName         = "roundName"
      :user-question-result = "userQuestionResult"
      @close:examination    = "gradingPopupStatus"
    />
  </div>
</template>

<script>

import { fetchListByRounds } from '@/ddaja-api/user/explore/examination/Examination.js'
import examinationPopup from '@/views/explore/examination/component/examinationPopup'
import gradingPopup from '@/views/explore/examination/component/gradingPopup'

export default {
  name: 'Examination'
  , components: {
    examinationPopup
    , gradingPopup
  }

  , data() {
    return {
      roundID                 : 0 
      , roundName             : ''
      , tableData             : []
      , userQuestionResult    : []
      , search                : ''
      , examPopupStatusVal    : false
      , gradingPopupStatusVal : false
    }
  }

  , created() {
    this.licenseInfo = this.$session.get('licenseInfo')
    this.subject = this.licenseInfo.subject
    this.setRoundList()
  }

  , methods: {
    async setRoundList() {
      var param = {
        licenseID  : this.licenseInfo.licenseID
        , examYear : 0
      }
      await fetchListByRounds(param).then(response => {
        const roundList = []
        response.items.forEach(x => {
          roundList.push({
            id         : x.item.id
            , round    : x.item.round
            , examDate : x.item.examDate.substring(0, 4) + ' / ' + x.item.examDate.substring(4, 6) + ' / ' + x.item.examDate.substring(6, 8)
            , examYear : x.item.examYear
            , name     : x.item.examYear + ' 년도 ' + x.item.round + ' 회차 시험문제 '
          })
        })
        this.tableData = roundList
      })
    }

    , examPopupStatus(data, val) {
      
      if (val === true) {
        // data = 시험 문제 정보
        this.roundID            = data.id
        this.roundName          = data.name
        this.examPopupStatusVal = val
      } else {
        // data = 사용자 답안
        this.userQuestionResult = data
        this.examPopupStatusVal = val
        this.gradingPopupStatus(true)
      }
    }

    , gradingPopupStatus(val) {
      this.gradingPopupStatusVal = val
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
    .div1 {
        padding: 0 5.5% 1.5% 0;
        float: right;
    }
    .div2{
        width: 90%;
        margin: 0 5% 0 5%;
    }
}
</style>
