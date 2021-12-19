<template>
  <div
    class="examination-popup"
    @keyup.enter.exact="answerCheck()"
    @keydown.32.exact="answerCheck()"
    @keydown.37.exact="setWordQuestion(-1)"
    @keydown.39.exact="setWordQuestion(1)"
  >
    <el-dialog
      width="60%"
      :visible.sync="popupVal"
      :before-close="handleClose"
    >
      <el-card
        class="card1"
        width="60%"
        :visible.sync="popupVal"
        :before-close="handleClose"
      >
        <div slot="header" class="div1">
          <el-button
            v-if="questionIndex != 0"
            style="float: left; padding: 3px 0"
            type="text"
            @click="setWordQuestion(-1)"
          > ← 이전 단어 </el-button>
          <span class="span1">
            {{ wordTitle }}
          </span>
          <el-button
            v-if="questionListSize != questionIndex"
            style="float: right; padding: 3px 0"
            type="text"
            @click="setWordQuestion(1)"
          > 다음 단어 → </el-button>
        </div>

        <div class="div2">
          <span class="span1">
            <i class="el-icon-caret-right" /> {{ questionIndex +1 }} 번 단어 <br><br>
            {{ question.content }}
          </span>
        </div>

        <div class="div3">
          <div v-if="questionResult" class="div3-2">
            <el-button
              type="success"
              class="btn1"
              plain
              @click="answerCheck()"
            >정답 확인 하기
            </el-button>
          </div>
          <div v-else class="div3-3">
            <span class="span1 span2"> {{ question.answer }} </span>
          </div>
        </div>
      </el-card>
    </el-dialog>
  </div>
</template>

<script>

import { fetchWordQuestion } from '@/ddaja-api/user/explore/examination-word/ExaminationWord'

export default {
  name: 'ExaminationPopup'

  , props: {
    popupVal: {
      type: Boolean
      , default: false
    }
    , wordID: {
      type: Number
      , default: 0
    }
    , wordQuestionsCount: {
      type: Number
      , default: 0
    }
  }

  , data() {
    return {
      amswerVal          : ''
      , wordTitle        : ''
      , questionResult   : true
      , questionList     : []
      , questionListSize : 0
      , questionIndex    : 0
      , question: {
        id            : 0
        , lid         : 0
        , answer      : ''
        , content     : ''
        , createDate  : ''
        , modifieDate : ''
      }
    }
  }

  , watch: {
    popupVal(val) {
      if (val) {
        this.fetchWordQuestion()
      }
    }
  }

  , methods: {

    fetchWordQuestion() {
      var param = {
        wordID: this.wordID
        , size: this.wordQuestionsCount
        , page: 0
      }

      fetchWordQuestion(param).then(response => {
        var questionList = []

        response.items.forEach(x => {
          questionList.push(x.item)
        })

        this.questionIndex = 0
        this.questionListSize = questionList.length - 1
        this.questionList = questionList
        this.wordTitle = response.items[0].item.wordDTO.title
        this.setWordQuestion(0)
      })
    }

    , answerCheck() {
      this.questionResult = false
    }
    
    , setWordQuestion(index) {
      console.log('setWordQuestion ' + index)
      var questionIndex = this.questionIndex + index
      this.questionIndex = questionIndex
      this.question = this.questionList[questionIndex]
      this.amswerVal = ''
      this.questionResult = true
    }

    , handleClose() {
      this.$confirm('종료 하시겠습니까 ?').then(_ => {
        this.popupClose()
      }).catch(_ => {})
    }

    , popupClose() {
      this.question = {
        id            : 0
        , lid         : 0
        , answer      : ''
        , content     : ''
        , createDate  : ''
        , modifieDate : ''
      }
      this.wordTitle = ''
      this.$emit('close:examination', false)
    }
  }
}
</script>

<style lang="scss" scoped>
.examination-popup{
    .card1{
        padding-bottom: 20px;
    }
    .div1{
        .span1 {
            font-size: 22px;
            font-weight: bold;
        }
    }
    .div2{
        float: left;
        width: 100%;
        .span1 {
            font-size: 18px;
            padding-top: 12px;
            font-weight: bold;
            float: left;
            padding: 3% 3% 3% 3%;
            text-align: left;
            line-height: 2em;
        }
    }
    .div3{
      .div3-1{
          padding: 30px 0 0 0;
          float: left;
          width: 100%;
          span{
              float: left;
              padding: 0 0 0 3%;
              font-size: 17px;
              text-align: left;
              font-weight: bold;
          }
        }
        .div3-2{
            .btn1{
              text-align: left;
              font-size : 18px;
              font-weight: bold;
              padding: 2% 0 2% 3%;
              margin : 3% 2% 0 2%;
              width: 94%;
            }
        }
        .div3-3{
            float: left;
            width: 100%;
            height: 100px;
            .span1, .span2{
                float: left;
                padding: 3% 0 2% 3%;
                font-size: 21px;
                text-align: left;
                font-weight: bold;
            }
            .span2{
                color: red;
            }
        }
    }
}
</style>
