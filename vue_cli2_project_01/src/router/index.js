//配置路由相关信息
//0.安装VueRouter  npm install vue-router --save-dev
import VueRouter from 'vue-router'
import Vue from 'vue'
// import Home from '../components/Home.vue'
// import About from '../components/About.vue'
// import User from '../components/User.vue'
//路由懒加载方式导入组件的写法
const Home = () => import('../components/Home.vue')
const About = () => import('../components/About.vue')
const User = () => import('../components/User.vue')
const HomeMsgs = () => import('../components/HomeMsgs.vue')
const HomeNews = () => import('../components/HomeNews.vue')
const UrlParam = () => import('../components/UrlParam.vue')
//1.通过Vue.use(插件)来安装插件
Vue.use(VueRouter)
//2.创建路由对象
const vueRouter = new VueRouter({
  //配置路由和组件之间的对应关系
  mode: 'history',
  routes: [
    //路由重定向
    {
      path: '',
      redirect: '/home',
      meta: {
        title: '首页'
      },
    },
    {
      path: '/home',
      component: Home,
      children: [
        {
          path: '',
          component: HomeNews
        },
        {
          path: 'homeNews',
          component: HomeNews
        },
        {
          path: 'urlParam',
          component: UrlParam
        },
        {
          path: 'homeMsgs',
          component: HomeMsgs
        },
      ],
      meta: {
        title: '首页'
      },
      beforeEnter: (to, from, next) => {
        console.log('路由独享守卫')
        next()
      }
    },

    {
      path: '/about',
      component: About,
      meta: {
        title: '关于'
      }
    },
    {
      path: '/user/:userId',
      component: User,
      meta: {
        title: '用户'
      }
    }
  ]
})
//3.将vueRouter挂在到Vue实例上面去
//4.配饰全局导航守卫
//前置守卫
vueRouter.beforeEach((to, from, next) => {       //前置钩子   //to from 其实就是 route 对象
  document.title = to.matched[0].meta.title
  next()
})
//后置守卫
vueRouter.afterEach((to, from) => {
  console.log('-afterEach---')
})
//路由独享守卫

//组件内守卫
//导出vueRouter
export default vueRouter

