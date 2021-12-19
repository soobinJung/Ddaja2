import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './styles/element-variables.scss'
import enLang from 'element-ui/lib/locale/lang/en'// 如果使用中文语言包请默认支持，无需额外引入，请删除该依赖

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'
import VueSession from 'vue-session'
import axios from 'axios'
import cors from 'cors'
import moment from 'vue-moment'
import map from 'lodash'
import VueKakaoSdk from 'vue-kakao-sdk'

axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*'
axios.defaults.headers.common['Access-Control-Allow-Headers'] = 'origin, access-control-allow-origin'
axios.defaults.headers.post['Content-Type'] = 'application/json'
axios.defaults.timeout = 100000
axios.defaults.baseURL = '{url}'

Vue.prototype.$http = axios

Vue.use(cors)
Vue.use(moment)
Vue.use(map)

Vue.prototype.$googleClientID = '71530109499-g2q6soik7tnfhh27lkqt8jr4pgmbo3ca.apps.googleusercontent.com'

const apiKey = 'a227a64e7aecffa5d4254e4526d679b1'
Vue.use(VueKakaoSdk, { apiKey })

import './icons' // icon
import './permission' // permission control
import './utils/error-log' // error log

import * as filters from './filters' // global filters

var sessionOptions = {
  persist: true
}
Vue.use(VueSession, sessionOptions)

if (process.env.NODE_ENV === 'production') {
  // const { mockXHR } = require('../mock')
  // mockXHR()
}

Vue.use(Element, {
  size: Cookies.get('size') || 'medium', // set element-ui default size
  locale: enLang // 如果使用中文，无需设置，请删除
})

// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
