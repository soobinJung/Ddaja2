<template>
  <div class = "main-container">
    <el-drawer
      title         = "I am the title"
      :visible.sync = "popupVal"
      :with-header  = "false"
      :before-close = "handleClose"
      style         = "width:100%"
    >
      <div class = "div1">
        <span class = "span1">Word Questions </span>
        <span 
          class  = "span2" 
          style  = "cursor: pointer" 
          @click = "isTitle = true">{{ param.title }}</span>
      </div>

      <div 
        v-if  = "isTitle" 
        style = "padding : 0 20px 60px 20px">
        <el-input 
          v-model     = "param.title" 
          placeholder = "Please input">
          <template slot = "append">
            <div  
              style  = "cursor: pointer" 
              @click = "titleUpdate()">
              <i class = "el-icon-setting" />
            </div>
          </template>
        </el-input>
      </div>

      <div>
        <el-table
          :data  = "tableData"
          height = "500"
          style  = "width: 100%"
        >
          <el-table-column type = "expand">
            <template slot-scope = "props">
              <p>{{ props.row.content }}</p>
            </template>
          </el-table-column>
          <el-table-column
            label = "id"
            prop  = "id"
            width = "100"
          />
          <el-table-column
            label = "정답"
            prop  = "answer"
          />
          <el-table-column
            label = "생성일"
            prop  = "createdDate"
            width = "100"
          />
          <el-table-column
            label = "수정일"
            prop  = "modifiedDate"
            width = "100"
          />
          <el-table-column
            label = "수정"
            prop  = "id"
            width = "100"
          >
            <template slot-scope = "{row}">
              <i class = "el-icon-s-tools" @click = "updateQuestion(row)" />
            </template>
          </el-table-column>

          <el-table-column
            label = "삭제"
            prop  = "id"
            width = "100"
          > 
            <template slot-scope = "{row}">
              <i class = "el-icon-delete" @click = "deleteQuestion(row)" />
            </template>
          </el-table-column>
        </el-table>
      </div>

      <div v-if = "status==='insert'" style = "padding: 5% 8% 5% 8%">
        <div style = "float:left; width:100%">
          <el-input 
            v-model     = "param.answer" 
            placeholder = "Answer" 
            style       = "float:left; width:50%" />
        </div>
        <div style = "float:left;">
          <el-input
            v-model     = "param.content"
            type        = "textarea"
            :rows       = "4"
            placeholder = "Content"
            style       = "width:1000px; padding:1% 0 0 0"
          />
        </div>
        <div style = "float:right;">
          <el-button
            plain
            type   = "primary"
            style  = "height:100px; margin:10px 0 0 0"
            @click = "save"
          >등 록</el-button>
        </div>
      </div>

      <div v-if = "status==='update'" style = "padding: 5% 8% 5% 8%">
        <div style = "float:left; width:100%">
          <el-input 
            v-model     = "param.answer" 
            placeholder = "Answer" 
            style       = "float:left; width:50%" />
        </div>
        <div style = "float:left;">
          <el-input
            v-model     = "param.content"
            type        = "textarea"
            :rows       = "4"
            placeholder = "Content"
            style       = "width:1000px; padding:1% 0 0 0"
          />
        </div>
        <div style = "float:right;">
          <el-button
            plain
            type   = "primary"
            style  = "height:100px; margin:10px 0 0 0"
            @click = "update"
          >수 정</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>
<script>

import { wordQuestionList, wordQuestionUpdate, wordQuestionInsert, wordQuestionDelete, wordUpdate } from '@/ddaja-api/admin/word/Word'

export default {
  name: 'AdminWordQuestion_Update'

  , props: {
    popupVal: {
      type: Boolean
      , default: false
    }
    , wordInfo: {
      type: Object
      , default: function() {
        return {
          wID                  : 0
          , lID                : 0
          , title              : ''
          , wordQuestionsCount : 0
        }
      }
    }
  }

  , data() {
    return {
      param : {
        wID       : 0
        , lID     : 0
        , answer  : ''
        , title   : ''
        , content : ''
        , page    : 0
        , size    : 100
      }

      , tableData: []
      , status: 'insert'
      , isTitle: false
    }
  }

  , watch: {
    popupVal(val) {
      if (val) {
        this.param.wID = this.wordInfo.wID
        this.param.lID = this.wordInfo.lID
        this.param.title = this.wordInfo.title

        if (this.wordInfo.wordQuestionsCount > 0) {
          this.fetchInfo()
        }
      }
    }
  }

  , methods: {
    async fetchInfo() {
      await wordQuestionList(this.param).then(response => {
        this.tableData = []
        response.items.forEach(x => {
          this.tableData.push({ id: x.item.id, answer: x.item.answer, content: x.item.content, createdDate: x.item.createdDate, modifiedDate: x.item.modifiedDate, wID: x.item.wordDTO.id, lID: x.item.lid })
        })
      })
    }

    , updateQuestion(row) {
      this.status        = 'update'
      this.param.id      = row.id
      this.param.answer  = row.answer
      this.param.content = row.content
    }

    , async update() {
      await wordQuestionUpdate(this.param).then(response => {
        this.$message({
          message: 'Word Question Update Success',
          type: 'success'
        })
        this.fetchInfo()
        this.status = 'insert'
        this.param.answer = ''
        this.param.content = ''
      })
    }

    , async titleUpdate() {
      var param = {
        id: this.param.wID,
        title: this.param.title,
        lID: this.param.lID
      }
      await wordUpdate(param).then(response => {
        this.$message({
          message: 'Word Title Update Success',
          type: 'success'
        })
      })
    }

    , async deleteQuestion(row) {
      this.param.id = row.id
      await wordQuestionDelete(this.param).then(response => {
        this.$message({
          message: 'Word Question Delete Success',
          type: 'success'
        })
      })
      await this.fetchInfo()
    }

    , async save() {
      await wordQuestionInsert(this.param).then(response => {
        this.$message({
          message: 'Word Question Save Success',
          type: 'success'
        })
        this.fetchInfo()
        this.param.answer = ''
        this.param.content = ''
      })
    }

    , popupClose() {
      this.tableData = []
      this.$emit('refresh')
      this.$emit('close:updatedrawer', false)
    }

    , handleClose() {
      this.popupClose()
    }
  }
}
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Kirang+Haerang&display=swap');
.main-container{ width: 100%; .div1{ text-align: left; margin-top: 5px; margin-bottom: 30px; color: rgb(0, 0, 0); margin-left: 1%; margin-right: 1%; padding: 4% 2% 2% 4%;
.span1{ font-size: 50px; font-family: 'Do Hyeon', sans-serif; } .span2{ font-size: 30px; font-family: 'Do Hyeon', sans-serif; }} .div2{ width : 100%; margin: 1% 4% 2% 4%; overflow:auto; .div2-1{ width : 100%; float :left ; padding: 1% 1% 1% 0%;text-align : right; font-weight:bold; .div2-1-1{ width : 5%; padding: 1% 3% 1% 6%; float :left ; } .div2-1-2{ width : 82%; padding: 1% 1% 1% 1%; float :left ; text-align: left;}} .div2-2{ width : 100%; text-align : left;float :left ; padding: 1% 10% 1% 10%;}} .pointer{ cursor:pointer;}} ::v-deep .el-drawer{ width: 70% !important; }
</style>
