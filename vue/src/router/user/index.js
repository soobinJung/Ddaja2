/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const exploreRouter = {
  path: '',
  component: Layout,
  redirect: '/',
  name: 'explore',
  children: [
    {
      path: 'explore',
      component: {
        render(c) { return c('router-view') }
      },
      meta: {
        title: '자격증',
        noCache: true
      },
      children: [
        {
          path: '',
          component: () => import('@/views/explore'),
          name: '자격증',
          meta: {
            title: '자격증 TEST',
            noCache: true
          }
        },
        {
          path: 'acceptance-review',
          component: () => import('@/views/explore/acceptance-review'),
          name: '합격 리뷰',
          meta: {
            title: '합격 리뷰',
            noCache: true
          }
        },
        {
          path: 'examination',
          component: () => import('@/views/explore/examination'),
          name: '모의고사',
          meta: { title: '모의고사', noCache: true }
        },
        {
          path: 'examination-again',
          component: () => import('@/views/explore/examination-again'),
          name: '틀린 문제',
          meta: { title: '틀린 문제', noCache: true }
        },
        {
          path: 'examination-one',
          component: () => import('@/views/explore/examination-one'),
          name: '한문제씩',
          meta: { title: '한문제씩', noCache: true }
        },
        {
          path: 'examination-word',
          component: () => import('@/views/explore/examination-word'),
          name: '단어 암기',
          meta: { title: '단어 암기', noCache: true }
        },
        {
          path: 'license-information',
          component: () => import('@/views/explore/license-information'),
          name: '자격증 정보',
          meta: { title: '자격증 정보', noCache: true }
        }
      ]
    }
  ]
}
export default exploreRouter
