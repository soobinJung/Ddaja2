<template>
  <div class="login-container">
    <el-form class="login-form" autocomplete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">DDaJa Login</h3>
      </div>
      <img id="kakao-login-btn" src="@/images/social/kakao_login.png" @click="kakaoLogin">
      <google-login />
      <button @click="kakaoLogout">로그아웃</button>
    </el-form>
  </div>
</template>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
import axios from 'axios'
import googleLogin from './components/GoogleLogin.vue'
export default {
  name: 'Login',
  components: {
    axios
    , googleLogin
  }
  , data() {
    return {
      socialAccessToken: {}
    }
  }
  ,
  methods: {
    googleLogin() {
      const AUTHORIZE_URI = "https://accounts.google.com/o/oauth2/v2/auth";
      const REDIRECT_URI = "localhost:9527/social/login"
      const RESPONSE_TYPE = "token"
      const SCOPE = "https://www.googleapis.com/auth/contacts.readonly"
      const REQUEST_URI = AUTHORIZE_URI + "?" + "client_id=" + this.$googleClientID
      // const queryStr = qs.stringify({
      //   client_id: CLIENT_ID,
      //   redirect_uri: window.location.href,
      //   response_type: "token",
      //   scope: "https://www.googleapis.com/auth/contacts.readonly",
      // });
      console.log("a")
    }
    , kakaoLogout() {
      alert(Kakao.Auth.getAccessToken())
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function(response) {
          console.log(response);
        },
        fail: function(error) {
          console.log(error);
        },
      });
      Kakao.Auth.logout(function() {
        console.log(Kakao.Auth.getAccessToken());
      });
    },
    kakaoLogin() {
      this.$kakao.Auth.createLoginButton({
        container: '#kakao-login-btn',
        success: function(authObj) {
          console.log(JSON.stringify(authObj))
          const socialAccessToken = {
            accessToken : authObj.access_token
            , refreshToken : authObj.refresh_token
            , tokenType : 'KAKAO'
            , expireTime : authObj.expires_in
            , refreshExpireTime : authObj.refresh_token_expires_in
          }
          const userInfoURI = "http://localhost/users/social"
          axios.post(userInfoURI, socialAccessToken)
          .then(res => {
            console.log(res)
            if(res.status == 200) {
              console.log("hreer!")
              window.localStorage.setItem('jwt', res.data.item.jwt)
              window.localStorage.setItem('userID', res.data.item.id)
              //window.location.href="/"
              return
            } else if(res.status == 201) {
              window.localStorage.setItem('jwt', res.data.item.jwt)
              window.localStorage.setItem('userID', res.data.item.id)
              return
            }
            alert("로그인에 실패하였습니다 :(")
          })
          .catch(err => {
            console.log(err)
          })
        },
        fail: function(err) {
          alert('failed to login: ' + JSON.stringify(err))
        }
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */
$bg:#283443;
$light_gray:#fff;
$cursor: #fff;
@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}
/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;
      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;
.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: auto;
  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .title-container {
    position: relative;
    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }
  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
</style>
