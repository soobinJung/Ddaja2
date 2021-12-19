<template>
  <div>
    <el-container>
      <el-aside>
        <el-menu :default-openeds="['1']">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-user-solid" />내 정보</template>
            <el-menu-item index="1-1" @click="setForm('userInfo')">수정하기</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu" />Navigator Two</template>
            <el-menu-item-group>
              <template slot="title">Group 1</template>
              <el-menu-item index="2-1">Option 1</el-menu-item>
              <el-menu-item index="2-2">Option 2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group 2">
              <el-menu-item index="2-3">Option 3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">Option 4</template>
              <el-menu-item index="2-4-1">Option 4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting" />Navigator Three</template>
            <el-menu-item-group>
              <template slot="title">Group 1</template>
              <el-menu-item index="3-1">Option 1</el-menu-item>
              <el-menu-item index="3-2">Option 2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group 2">
              <el-menu-item index="3-3">Option 3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">Option 4</template>
              <el-menu-item index="3-4-1">Option 4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header>
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px" />
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>View</el-dropdown-item>
              <el-dropdown-item>Add</el-dropdown-item>
              <el-dropdown-item>Delete</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>{{ userInfo.nickName == '' ? '익명의 사용자' : userInfo.nickName }} 님</span>
        </el-header>
        <el-main>
          <user-info-form
            :is-user-info-form="isUserInfoForm"
            :user-info="userInfo"
          />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import userInfoForm from './components/userInfoForm.vue'
export default {
  components: {
    userInfoForm
  },
  data() {
    const item = {
      date: '2016-05-02',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles'
    }
    return {
      userInfo: {
        id: '',
        email: '',
        userId: '',
        nickName: '',
        createdDate: '',
        modifiedDate: ''
      },
      tableData: Array(5).fill(item),
      isUserInfoForm: false
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() {
      const jwt = window.localStorage.getItem('jwt')
      console.log(jwt)
      if (!jwt) {
        alert('잘못된 접근입니다.')
        // this.$router.push('/')
      }
      this.$http
        .get('http://localhost/users/' + window.localStorage.getItem('userID'), {
          headers: {
            Authorization: jwt
          }
        })
        .then(res => {
          console.log(res)
          this.userInfo.id = res.data.item.id
          this.userInfo.email = res.data.item.email
          this.userInfo.userId = res.data.item.userId
          this.userInfo.nickName = res.data.item.nickName
          this.userInfo.createdDate = res.data.item.createdDate
          this.userInfo.modifiedDate = res.data.item.modifiedDate
        })
        .catch(err => {
          alert('잘못된 접근입니다.')
          console.log(err)
          // this.$router.push('/')
        })
    },
    setForm(formName) {
      switch (formName) {
        case 'userInfo' :
          this.isUserInfoForm = true
          break
        default :
          this.isUserInfoForm = false
          break
      }
    }
  }
}
</script>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
    text-align: right;
    font-size: 12px;
  }
  .el-aside {
    color: #333;
    background-color: rgb(238, 241, 246);
    width: 100px;
  }
  .el-container {
      height: 100%;
      border: 1px solid #eee;
  }
</style>
