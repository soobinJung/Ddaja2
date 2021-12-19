import getPageTitle from '@/utils/get-page-title'
import NProgress from 'nprogress'
import router from './router'
import store from './store'
import { getToken } from '@/utils/auth'
import 'nprogress/nprogress.css'

NProgress.configure({ showSpinner: false })

router.beforeEach(async(to, from, next) => {
  NProgress.start()
  document.title = getPageTitle(to.meta.title)

  const hasToken = getToken()

  /** ADMIN **/
  if (hasToken === 'admin-token') {
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done()
    } else {
      routerNext('admin', to, next)
    }

  /** VISTOR **/
  } else {
    if (to.path === '/login') {
      store.dispatch('user/resetToken')
      next()
    } else {
      routerNext('visitor', to, next)
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})

async function routerNext(roleName, to, next) {
  if (store.getters.permission_routes.length === 0) {
    const role = [roleName]
    const accessRoutes = await store.dispatch('permission/generateRoutes', role).catch((error) => {
      console.log(error)
    })

    router.addRoutes(accessRoutes)
    next({ path: to.path })
    NProgress.done()

  /** router.afterEach() 호출 **/
  } else {
    next()
  }
}
