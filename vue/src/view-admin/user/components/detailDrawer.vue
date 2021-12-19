<template>
  <div class="main-container">
    <el-drawer
      title="I am the title"
      :visible.sync="popupVal"
      :with-header="false"
      style="width:100%"
      :before-close="handleClose"
    >
      <div class="div1">
        <span class="span1">사용자 정보</span>
      </div>

      <div class=" div2">
        <div class="div2-1">
          <span>ID</span>
        </div>
        <div class="div2-2">{{ userInfo.id }}</div>
      </div>

      <div class=" div2">
        <div class="div2-1">
          <span>userId</span>
        </div>
        <div class="div2-2">{{ userInfo.userId }}</div>
      </div>

      <div class=" div2">
        <div class="div2-1">
          <span>Nick Name</span>
        </div>
        <div class="div2-2">{{ userInfo.nickName }}</div>
      </div>

      <div class=" div2">
        <div class="div2-1">
          <span>email</span>
        </div>
        <div class="div2-2">{{ userInfo.email }}</div>
      </div>

      <div class=" div2">
        <div class="div2-1">
          <span>등록일</span>
        </div>
        <div class="div2-2">
          {{ $moment(userInfo.createdDate).format('YYYY-MM-DD') }}
        </div>
      </div>

      <div class=" div2">
        <div class="div2-1">
          <span>수정일</span>
        </div>
        <div class="div2-2">
          {{ $moment(userInfo.modifiedDate).format('YYYY-MM-DD') }}
        </div>
      </div>

    </el-drawer>
  </div>
</template>

<script>

import { userDetail } from '@/ddaja-api/admin/user/User'

export default {
  name: 'Community',
  props: {
    popupVal: false,
    id: {}
  },
  data() {
    return {
      param: {
        id: 0
      },
      userInfo: {}
    }
  },
  watch: {
    popupVal(val) {
      if (val) {
        this.param.id = this.id
        this.fetchInfo()
      }
    }
  },
  methods: {
    async fetchInfo() {
      await userDetail(this.param).then(response => {
        this.userInfo = response.item
      })
    },

    popupClose() {
      this.$emit('close:detaildrawer', false)
    },

    handleClose() {
      this.popupClose()
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
        width : 100%; padding: 1% 0% 4% 0%;
        .div2-1{
width : 15%; float :left ; padding: 1% 1% 1% 6%;text-align : right; font-weight:bold;
        }
        .div2-2{
            width : 84%; text-align : left;float :left ; padding: 1% 1% 1% 3%;
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
