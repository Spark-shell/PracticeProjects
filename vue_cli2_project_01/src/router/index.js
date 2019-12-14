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
  mode:'history',
  routes: [
    //路由重定向
    {
      path: '',
      redirect: '/home'
    },
    {
      path: '/home',
      component: Home,
      children:[
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
      ]
    },

    {
      path: '/about',
      component: About
    } ,
    {
      path: '/user/:userId',
      component: User
    }
  ]
})
//3.将vueRouter挂在到Vue实例上面去
//导出vueRouter
export default vueRouter

