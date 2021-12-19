<template>
  <div class="main-container">
    <div class="div1 main-title">
      <div class="div1-1">
        <font class="title-font1">
          합격 리뷰
        </font>
        <font font class="title-font2">
          - {{ licenseInfo.licenseName }}
        </font>
      </div>
      <div class="div1-2">
        <el-button type="success" class="btn1" @click="popupState(true)">
          합격 후기 쓰러 가기
        </el-button>
      </div>
    </div>
    <div class="div2">
      <el-table
        :data="successComments"
        style="width: 100%"
      >
        <el-table-column type="expand">
          <template slot-scope="props">
            <div style="padding: 5px">
              <span style="font-size:18px; font-weight:bold">{{ props.row.successTitle }}</span>
            </div>
            <div style="padding: 5px">
              <span v-html="props.row.successComment" />
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="제목"
          prop="successTitle"
        />
        <el-table-column
          label="합격 점수"
          prop="successScore"
          width="180"
        />
        <el-table-column
          label="후기 작성일"
          width="180"
          align="left"
        >
          <template slot-scope="scope">
            {{ $moment(scope.row.createDate).format('YYYY-MM-DD') }}
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="div3">
      <el-pagination
        class="paging"
        layout="prev, pager, next"
        :total="50"
      />
    </div>
    <popup
      :popup-val="popupVal"
      @close:popup="popupState"
    />
  </div>
</template>

<script>
import popup from './component/reviewWritePopup.vue'
import { fetchSuccessComment } from '@/ddaja-api/user/explore/acceptance-review/acceptanceReview'

export default {
  name: '',
  components: {
    popup
  },
  data() {
    return {
      tableData: [
        { date: '2016-05-03', name: '정보처리 산업기사 2회차', successTitle: '저는 따자만 주구장창 풀고 합격했어요', content: '비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다', scrore: '80' },
        { date: '2016-05-03', name: '정보처리 산업기사 2회차', title: '저는 따자만 주구장창 풀고 합격했어요', content: '비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다', scrore: '90' },
        { date: '2016-05-03', name: '정보처리 산업기사 2회차', title: '저는 따자만 주구장창 풀고 합격했어요', content: '비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다', scrore: '85' },
        { date: '2016-05-03', name: '정보처리 산업기사 2회차', title: '저는 따자만 주구장창 풀고 합격했어요', content: '비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다', scrore: '84' },
        { date: '2016-05-03', name: '정보처리 산업기사 2회차', title: '저는 따자만 주구장창 풀고 합격했어요', content: '비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다', scrore: '87' },
        { date: '2016-05-03', name: '정보처리 산업기사 2회차', title: '저는 따자만 주구장창 풀고 합격했어요', content: '비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다', scrore: '95' },
        { date: '2016-05-03', name: '정보처리 산업기사 2회차', title: '저는 따자만 주구장창 풀고 합격했어요', content: '비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다', scrore: '82' },
        { date: '2016-05-03', name: '정보처리 산업기사 2회차', title: '저는 따자만 주구장창 풀고 합격했어요', content: '비 전공자인 저는 따자만 주구장창 풀고 합격했어요 너무 유용하게 잘 사용 했습니다', scrore: '86' }
      ],
      popupVal: false,
      successComments: []
    }
  },
  created() {
    this.licenseInfo = this.$session.get('licenseInfo')
    this.subject = this.licenseInfo.subject

    if (this.licenseInfo.licenseID != 0) {
      this.setSuccessComment()
    }
  },
  methods: {
    popupState(value) {
      this.popupVal = value
      if (!value) {
        this.setSuccessComment()
      }
    },

    async setSuccessComment() {
      var param = {
        licenseID: this.licenseInfo.licenseID
      }
      await fetchSuccessComment(param).then(response => {
        var successComments = []
        if (response.items.length === 0) {
          successComments.push({
            id: 1,
            likeCount: 0,
            successComment: '.',
            successTitle: '합격 후기가 존재하지 않습니다.',
            successScore: 0
          })
        }
        response.items.forEach(x => {
          successComments.push({
            id: x.item.id,
            likeCount: x.item.likeCount,
            successTitle: x.item.successTitle,
            successComment: x.item.successComment,
            successScore: x.item.successScore,
            createdDate: x.item.createdDate
          })
        })
        this.successComments = successComments
      })
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
        .div1-1{
            width: 80%;
            float: left;
            .title-font1{
                font-size: 50px;
                font-family: 'Do Hyeon', sans-serif;
            }
            .title-font2{
                font-size: 40px;
                padding: 0 0 0 30px;
                font-family: 'Do Hyeon', sans-serif;
            }
        }
        .div1-2{
            width: 20%;
            float: left;
            .btn1{
                float: right;
                height: 50px;
                width: 200px;
            }
        }
    }
    .div2{
        padding: 1.5% 2% 0% 4%;
        margin: 0 0 30px 0 ;
        float:left;
        width: 100%;
        max-height:550px;
        .p1{ font-size: 20px; font-weight: bold; }
        .p2{ font-size: 14px; font-weight: bold; }
        .p3{ padding: 1.5%; font-size: 14px; }
    }
    .div3{
        float:left;
        padding: 2% 11% 2% 0;
        text-align: center;
        position: fixed;
        bottom: 0;
        width: 100%;
        .paging{ display: inline-block;}
    }
}
</style>
