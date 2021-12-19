<template>
  <div class = "cotegory-container">
    <div class = "title-style">
      <font class = "title-font">{{ licenseInfo.name }} - {{ licenseInfo.type === 'WRITING' ? "필기" : licenseInfo.type === "PERFORM" ? "실기" : "" }}</font>
    </div>
    <div class = "block1">
      <div class = "block4"> 
        <router-link to = "/explore/examination">
          <el-button style = "height: 150px;" type="primary" plain>기출 문제 - 모든 문제</el-button>
        </router-link>          
      </div> 
      <div class = "block3"> 
          <div style = "text-align: left;">
            <div style = "padding : 0 3% 3% 3%">
              <span class = "span3">
                {{ licenseInfo.name }} 자격증은 <el-button size="small">{{ licenseInfo.agency }}</el-button> 에서 주최하며, <br/>
                <span v-if = "licenseIfs.length > 0">
                  응시 조건은
                  <span v-for = "item, index in licenseIfs" :key="item.id">
                    <el-button size = "small">{{ item.name }}</el-button>
                    <span v-if = "index != licenseIfs.length-1">, </span>
                  </span>
                  입니다.
                </span>
                <span v-else>응시 조건은 없습니다.</span>
                <br>
                평균 점수가 <el-button size = "small">{{ licenseInfo.passScore }}</el-button> 를 넘어야 하고,
                <br>
                응시료는 <el-button size = "small">{{ licenseInfo.examFee }}</el-button> 이며
                <br>
                합격률은 <el-button size = "small">5,000,000 / 453,250</el-button> 입니다.
                <br>
                공부해야 하는 과목들은
                <span v-for = "item, index in licenseInfo.subjects" :key = "item.id">
                  <el-tooltip :content = "item.minScoreMsg" placement = "top">
                    <el-button size = "small">{{ item.name }}</el-button>
                  </el-tooltip>
                  <span v-if = "index != licenseInfo.subjects.length-1">, </span>
                </span>
                입니다.
                <br>
              </span>
            </div>
          </div>
      </div>
    </div>
    <div class = "block1">
      <div class = "block4"> 
        <!-- <router-link to = "/explore/examination-one"> -->
          <el-button style = "height: 150px;" type = "success" @click = "develAlert()" plain>기출 문제 - 한문제씩</el-button>
        <!-- </router-link> -->
      </div> 
    </div>
    <div class = "block1">
      <div class = "block4"> 
        <router-link to = "/explore/examination-word">
          <el-button style = "height: 150px;" type = "warning" plain>기출 용어 - 암기단어</el-button>
        </router-link>     
      </div> 
    </div>
    <div class = "block2">
      <div class = "block4">
        <router-link to = "/explore/acceptance-review">
          <span class = "demonstration"> BEST 합격 후기 </span>
        </router-link>
      </div>
      <el-carousel height = "530px">
        <el-carousel-item 
          v-for = "item in successComments" 
          :key  = "item.id" 
          style = "background-color:#ffff; border:2px solid #e0e0e0; border-radius:50px">
          <router-link to = "/explore/acceptance-review">
            <div style = "padding: 5% 5% 2% 5%;">
              <span style = "font-size:20px; font-weight: bold;">
                <i class = "el-icon-trophy" /> {{ item.successTitle }}
              </span>
            </div>
            <div style = "width:100%; height: 50px; padding :0% 5% 7% 3%">
              <div style = "width:15%; float:left; height: 100%; padding: 1.5% 2% 2% 2%;">
                <span style = "font-size:15px; font-weight: bold;">좋아요</span>
              </div>
              <div style = "width:35%; float:left; height: 100%; padding: 1.5% 2% 2% 2%; ">
                <span>{{ item.likeCount }}</span>
              </div>
              <div style = "width:15%; float:left; height: 100%; padding: 1.5% 2% 2% 2%; ">
                <span style = "font-size:15px; font-weight: bold;">합격 점수</span>
              </div>
              <div style = "width:35%; float:left; height: 100%; padding: 1.5% 2% 2% 2%; ">
                <span>{{ item.successScore }}</span>
              </div>
            </div>
            <div style = "padding:0% 5% 0% 5%;">
              <span v-html = "item.successComment" />
            </div>
          </router-link>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
import { fetchLicense, fetchLicenseIf, fetchSuccessComment } from '@/ddaja-api/user/explore/explore/explore'

export default {
  name : 'Explore_Index'
  
  , data() {
    return {
      licenseInfo: []
      , licenseIfs: []
      , successComments: []
    }
  }

  , created() {
    this.licenseInfo = this.$session.get('licenseInfo')
    this.setLicenseInfo()
    this.setLicenseIf()
    this.setSuccessComment()
  }
  
  , methods: {
    async setLicenseInfo() {
      var param = {
        licenseID: this.licenseInfo.licenseID
      }

      await fetchLicense(param).then(response => {
        var subjects = []

        response.item.subjects.forEach(x => {
          x.minScoreMsg = '최소 점수 : ' + x.minScore
          subjects.push(x)
        })

        this.licenseInfo = {
          id             : response.item.id
          , name         : response.item.name
          , code         : response.item.code
          , type         : response.item.type
          , agency       : response.item.agency
          , examFee      : response.item.examFee
          , passScore    : response.item.passScore
          , inUse        : response.item.inUse
          , createdDate  : response.item.createdDate
          , modifiedDate : response.item.modifiedDate
          , subjects     : subjects
        }
      })
    }

    , async setLicenseIf() {
      var param = {
        licenseID: this.licenseInfo.licenseID
      }

      await fetchLicenseIf(param).then(response => {
        var licenseIfs = []
        response.items.forEach(x => {
          licenseIfs.push({
            name: x.item.licenseIf,
            id: x.item.id
          })
        })
        this.licenseIfs = licenseIfs
      })
    }

    , async setSuccessComment() {
      var param = {
        licenseID: this.licenseInfo.licenseID
      }
      await fetchSuccessComment(param).then(response => {
        var successComments = []
        if (response.items.length === 0) {
          successComments.push({
            id               : 1
            , likeCount      : 0
            , successComment : '.'
            , successTitle   : '합격 후기가 존재하지 않습니다.'
            , successScore   : 0
          })
        }
        response.items.forEach(x => {
          successComments.push({
            id               : x.item.id
            , likeCount      : x.item.likeCount
            , successTitle   : x.item.successTitle
            , successComment : x.item.successComment
            , successScore   : x.item.successScore
          })
        })
        this.successComments = successComments
      })
    }

    , develAlert (){
      alert('개발 중 입니다')
    }
  }
}
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&display=swap');
.cotegory-container {
  text-align: left;
  padding: 30px;
  overflow:auto;
  .title-style{
    margin-top: 5px;
    margin-bottom: 5px;
    color: rgb(0, 0, 0);
    margin-left: 1%;
    margin-right: 2%;
    padding: 1.5% 2% 1.5% 2%;
    .title-font{
      font-size: 50px;
      font-family: 'Do Hyeon', sans-serif;}
  }
  .div-common{
    border-radius: 40px 80px / 80px 40px;
    float: left; width: 31%;
    height: 230px;
    margin: 1%;
    text-align: center;
    padding-top:85px
  }
  .div1{ border: 10px solid rgb(255, 255, 255); background-color:#e9bcc5;}
  .div1:hover { background-color:#f75273;}
  .div2{ border: 10px solid rgb(255, 255, 255);  background-color:#f5da9f;}
  .div2:hover { background-color:#fcc141;}
  .div3{ border: 10px solid rgb(255, 255, 255);  background-color:#ecf7b3;}
  .div3:hover { background-color:#daf74b;}
  .div4{ border: 10px solid rgb(255, 255, 255);  background-color:#b7ffa9;}
  .div4:hover { background-color:#6af34e;}
  .div5{ border: 10px solid rgb(255, 255, 255);  background-color:#a1cff5;}
  .div5:hover { background-color:#4caaf7;}
  .div6{ border: 10px solid rgb(255, 255, 255);  background-color:#5a70ee;}
  .div6:hover { background-color:#2645f7;}
  .font1{ font-size: 40px;  font-family: 'Do Hyeon', sans-serif; }
}

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #e2ecfa;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #ffeceb;
  }

  .block1{
    width: 200px;
    float: left;
    margin: 2%;
  }

    .block2{
    width: 900px;
    float: left;
    margin: 2%;
  }

  .block3{
    width: 750px;
    float: left;
    margin: 20% 2% 2% 2%;
  }

  .block4{
    padding: 10px 10px 20px 0;

      .demonstration{
        font-size: 17px;
        font-weight: bold;
      }
  }

.span1{
    font-weight: bold;
    color: #000000;
    font-size: 18px;
    opacity: 0.75;
}

.span2{
    color: #3b3b3b;
    font-size: 20px;
    opacity: 0.75;
    font-weight: bold;
}

.span3{
    color: #3b3b3b;
    font-size: 20px;
    opacity: 0.75;
    font-weight: bold;
    line-height : 50px;
}

.span4{
    color: #000000;
    font-size: 15px;
    font-weight: bold;
    line-height : 50px;
}
</style>
