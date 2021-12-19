<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" class="form-container">
      <div class="createPost-main-container">
        <el-row>
          <Warning />
          <el-col :span="24">
            <el-form-item style="margin-bottom: 20px;" prop="successTitle">
              <el-input
                ref="successTitle"
                v-model="postForm.successTitle"
                placeholder="후기 제목을 입력해주세요."
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item style="margin-bottom: 40px;" prop="successScore">
              <el-input
                ref="successScore"
                v-model="postForm.successScore"
                placeholder="합격 점수를 입력해주세요."
                type="number"
                @change="postForm.successScore = postForm.successScore > 100 ? 100 : postForm.successScore < 0 ? 0 : postForm.successScore"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item prop="successComment" style="margin-bottom: 30px;">
          <Tinymce ref="editor" v-model="postForm.successComment" :height="400" />
        </el-form-item>
        <el-row>
          <el-col :span="24" align="right">
            <el-button @click="saveSuccessComment(false)">저장 하기</el-button>
          </el-col>
        </el-row>
      </div>
    </el-form>
  </div>
</template>

<script>
import { insertSuccessComment } from '@/ddaja-api/user/explore/acceptance-review/acceptanceReview'
import Tinymce from '@/components/Tinymce'
import Warning from './Warning'

export default {
  name: 'ArticleDetail',
  components: { Tinymce, Warning },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      postForm: {
        id: 0,
        lID: 0,
        uID: 0,
        likeCount: 0,
        successTitle: '',
        successComment: '',
        successScore: undefined
      },
      successScore: ''
    }
  }, watch: {
    successScore() {
      this.setSuccessScore(String(this.successScore).replace(/[^0-9]/g, ''))
    }
  },

  methods: {
    async saveSuccessComment() {
      if (this.postForm.successTitle === '') {
        this.$alert('제목을 입력 하세요')
        this.$refs.successTitle.focus()
        return
      }

      if (this.postForm.successComment === '') {
        this.$alert('후기를 입력 하세요')
        return
      }

      if (this.postForm.successScore === 0 || this.postForm.successScore === undefined) {
        this.$alert('점수를 입력 하세요. ')
        this.$refs.successScore.focus()
        return
      }

      var param = {
        lID: this.$session.get('licenseInfo').licenseID,
        uID: 3,
        likeCount: 0,
        inUse: true,
        successTitle: this.postForm.successTitle,
        successComment: this.postForm.successComment,
        successScore: this.postForm.successScore
      }

      await insertSuccessComment(param).then(response => {
        this.$notify({
          title: '후기',
          message: '등록 완료 하였습니다.',
          type: 'success',
          duration: 2000
        })
        this.popupClose(false)
      })
    },

    popupClose(val) {
      this.$emit('close:popup', val)
    },

    setSuccessScore(val) {
      this.successScore = val
    }
  }

}
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";

.createPost-container {
  position: relative;

  .createPost-main-container {
    padding: 40px 45px 20px 50px;

    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;

      .postInfo-container-item {
        float: left;
      }
    }
  }

  .word-counter {
    width: 40px;
    position: absolute;
    right: 10px;
    top: 0px;
  }
}

.article-textarea ::v-deep {
  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }
}
</style>
