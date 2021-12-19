import Layout from '@/layout'

const adminRouter = {
  path: '/admin-ddaja',
  component: Layout,
  redirect: '/admin-ddaja/dashboard',
  name: 'admin',
  meta: {
    title: '관리자'
  },
  children: [
    {
      path: 'dashboard',
      component: () => import('@/view-admin/dashboard'),
      name: '관리자 메인',
      meta: {
        title: '관리자 메인'
      },
      roles: ['admin']
    },
    {
      path: 'license',
      component: () => import('@/view-admin/license'),
      name: 'Admin / License Manager',
      meta: {
        title: 'Admin / License Manager'
      },
      roles: ['admin']
    },
    {
      path: 'notice',
      component: () => import('@/view-admin/notice'),
      name: 'Admin / Notice Manager',
      meta: {
        title: 'Admin / Notice Manager'
      },
      roles: ['admin']
    },
    {
      path: 'question',
      component: () => import('@/view-admin/question'),
      name: 'Admin / Question Manager',
      meta: {
        title: 'Admin / Question Manager'
      },
      roles: ['admin']
    },
    {
      path: 'user',
      component: () => import('@/view-admin/user'),
      name: 'Admin / User Manager',
      meta: {
        title: 'Admin / User Manager'
      },
      roles: ['admin']
    },
    {
      path: 'word',
      component: () => import('@/view-admin/word'),
      name: 'Admin / Word Manager',
      meta: {
        title: 'Admin / Word Manager'
      },
      roles: ['admin']
    }
  ]
}
export default adminRouter
