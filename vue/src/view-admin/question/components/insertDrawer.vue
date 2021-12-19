<template>
  <div class="main-container">
    <el-drawer
      v-loading="loading"
      :visible.sync="popupVal"
      :with-header="false"
      :before-close="popupClose"
      style="width:100%"
    >
      <div class="div1">
        <span class="span1">Questions ADD</span>
        <div style="float:right; padding: 0 150px 0  0">
          <el-button
            type="primary"
            @click="onSubmit"
          >Create</el-button>
          <el-button
            @click="downloadByQuestionExcelSample"
          >Excel Sample</el-button>
        </div>
      </div>

      <div style="padding:0 30px 10px  30px">
        <el-form
          ref="param"
          :model="param"
          label-width="200px"
        >
          <el-form-item label="자격증">
            <el-select
              ref="license"
              v-model="param.lID"
              placeholder="Select"
              style="width:750px"
              filterable
            >
              <el-option
                v-for="item in licenseOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label = "기출문제 응시년도">
            <el-input
              ref     = "examYear"
              v-model = "param.examYear"
              style   = "width:750px"
            />
          </el-form-item>

          <el-form-item label = "기출문제 응시일">
            <el-input
              ref     = "examDate"
              v-model = "param.examDate"
              style   = "width:750px"
            />
          </el-form-item>

          <el-form-item label = "기출문제 응시회차">
            <el-input
              ref     = "round"
              v-model = "param.round"
              style   = "width:750px"
            />
          </el-form-item>
        </el-form>
      </div>

      <div>
        <ExcelUpload
          :on-success    = "handleSuccess"
          :before-upload = "beforeUpload"
        />
        <el-scrollbar
          ref   = "scrollbar"
          style = "height: calc(100vh - 45px)"
        >
          <el-table
            border
            highlight-current-row
            :data = "tableData"
            style = "width: 100%; margin-top:20px; height: 500px; overflow:scroll"
          >
            <el-table-column
              v-for  = "item of tableHeader"
              :key   = "item"
              :prop  = "item"
              :label = "item"
            />
          </el-table>
        </el-scrollbar>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import ExcelUpload from '../components/excelUpload'
import { licenseList, insertRound, insertQuestion } from '@/ddaja-api/admin/examination/Examination'

export default {
  name: 'AdminQuestion_Insert'

  , components: {
    ExcelUpload
  }
  
  , props: {
    popupVal: {
      type      : Boolean
      , defalut : false
    }
  },
  data() {
    return {
      param: {
        lID: 0
        , examYear: 2021
        , examDate: 20210101
        , round: 1
        , inUse: true

      }

      , licenseOptions : []
      , tableHeader    : []
      , tableData      : []
      , loading        : false
    }
  }

  , created() {
    this.getLicense()
  }

  , methods: {
    async onSubmit() {
      let param = {
        lID          : this.param.lID
        , examYear   : this.param.examYear
        , examDate   : this.param.examDate
        , round      : this.param.round
        , inUse      : this.param.inUse
        , rID        : 0
        , sID        : 0
        , no         : 0
        , title      : ''
        , content    : ''
        , answerOne  : ''
        , answerTwo  : ''
        , answerThr  : ''
        , answerFour : ''
        , answerFive : ''
        , score      : ''
      }
      await insertRound(param).then(response => {
        param.rID = response.item.id
      })

      if (this.tableData.length === 0) { return }
      this.loading = true

      for (var i in this.tableData) {
        var x = this.tableData[i]

        param.no         = x.no         || 1
        param.sID        = x.sid        || 1
        param.title      = x.title      || ''
        param.content    = x.content    || ''
        param.answerOne  = x.answerOne  || ''
        param.answerTwo  = x.answerTwo  || ''
        param.answerThr  = x.answerThr  || ''
        param.answerFour = x.answerFour || ''
        param.answerFive = x.answerFive || ''
        param.score      = x.score      || 0
        await this.insertQuestion(param).then()
      }
      this.loading = false
      this.popupClose()
      this.$alert('저장 성공')
    }

    , async insertQuestion(param){
      await insertQuestion(param).then()
    }

    , async downloadByQuestionExcelSample (){

    import('@/vendor/Export2Excel').then(excel => {
        const tHeader   = ['no', 'title', 'content', 'answerOne', 'answerTwo', 'answerThr', 'answerFour', 'answerFive', 'score', 'sid']
        const filterVal = ['no', 'title', 'content', 'answerOne', 'answerTwo', 'answerThr', 'answerFour', 'answerFive', 'score', 'sid']
        const list      = []
        const data      = this.formatJson(filterVal, list)
        excel.export_json_to_excel({
          header      : tHeader
          , filename  : 'Ddaja Question Sample'
          , autoWidth : true
          , bookType  : 'xlsx'
          , data
        })
      })
    }
    
    , formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    }

    , async getLicense() {
      await licenseList().then(response => {
        response.items.forEach(x => {
          var type = (x.item.type === 'WRITING') ? '필기' : '실기'
          var label = type + ' - ' + x.item.name
          this.licenseOptions.push({ value: x.item.id, label: label })
        })
        this.param.lID = this.licenseOptions[0].value
      })
    }
    
    , verification() {
      if (this.param.lID === 0) {
        this.$alert('LICENSE NULL ERROR')
        this.$refs.license.focus()
        return false
      }
      if (this.param.titleS === '') {
        this.$alert('TITLE NULL ERROR')
        this.$refs.title.focus()
        return false
      }
      return true
    }

    , beforeUpload(file) {
      const isLt1M = file.size / 1024 / 1024 < 1
      if (isLt1M) { return true }
      this.$message({
        message : '용량 초과.'
        , type  : 'warning'
      })
      return false
    }

    , handleSuccess({ results, header }) {
      this.tableData = results
      this.tableHeader = header
    }

    , popupClose() {
      if (this.loading) {
        this.$alert('데이터 넣는 중 입니다.')
        return
      }
      this.$emit('refresh')
      this.$emit('close:insertdrawer', false)
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
