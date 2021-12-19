<template>
  <el-form v-show="isUserInfoForm" ref="form" :model="form" label-width="120px">
    {{ userInfo }}
    <el-form-item label="닉네임">
      <el-col :span="11">
        <el-input v-model="userInfo.nickName" />
      </el-col>
    </el-form-item>
    <el-form-item label="프로필">
      <el-col :span="11">
        <el-upload
          class="avatar-uploader"
          action="http://localhost/user/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
        >
          <img v-if="userInfo.profileImage" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-col>
    </el-form-item>
    <el-form-item label="가입일">
      <el-col :span="11">
        <el-date-picker v-model="userInfo.createdDate" type="date" placeholder="Pick a date" style="width: 100%;" :disabled="true" />
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="modifyUserInfo()">수정</el-button>
    </el-form-item>
  </el-form>

</template>
<script>
import axios from 'axios'
export default {
  props: {
    isUserInfoForm: {},
    userInfo: {}
  },
  data() {
    return {
      form: {
        name: '',
        date1: '2021-09-27',
        imageUrl: '',
        region: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  methods: {
    modifyUserInfo() {
      const instance = axios.create({
        headers: {
          Authorization: window.localStorage.getItem('jwt')
        }
      })
      console.log('modifyUserInfo')
      console.log(this.userInfo)
      instance.patch('http://localhost/users/' + this.userInfo.id, this.userInfo)
        .then(res => {
          console.log(res.data)
          const h = this.$createElement
          this.$notify({
            title: '완료',
            message: h('i', { style: 'color: teal' }, '수정이 완료되었습니다.')
          })
        })
        .catch(error => {
          console.log(error)
        })
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
      console.log(this.imageUrl)
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('Avatar picture must be JPG format!')
      }
      if (!isLt2M) {
        this.$message.error('Avatar picture size can not exceed 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
