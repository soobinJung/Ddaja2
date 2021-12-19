import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

const service = axios.create({

  baseURL: process.env.NODE_ENV === 'production'
    ? ''
    : 'http://localhost',

  withCredentials: false
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['X-Token'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  async response => {
    const res = response.data

    if (response.data.info) {
      // access 토큰 만료시 작동되도록
      if (response.data.info.http_state === 400) {
        const errorCd = response.data.response_data.error_code
        // BadCredentialsException (JWT토큰 만료시)
        if (errorCd === 102) {
          // 유저 정보 비우기
          await store.dispatch('user/logout')

          // 라우터 정보 비우기
          await store.dispatch('permission/resetRoutes')
          router.push(`/login`)
          return
        } else if (errorCd === 105) {
          // 유저 정보 비우기
          await store.dispatch('user/logout')

          // 라우터 정보 비우기
          await store.dispatch('permission/resetRoutes')
          router.push(`/login`)
          return
        } else {
          return renewToken(response.config).then(res => {
            return res
          }, error => {
          // 유저 정보 비우기
            store.dispatch('user/logout')

            // 라우터 정보 비우기
            store.dispatch('permission/resetRoutes')
            router.push(`/login`)
            return
          })
        }
      }
    }
    return res
  }
  , error => {
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
