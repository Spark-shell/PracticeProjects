import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
//非懒加载方式导入的写法
// import Home from '../views/home/Home'
// import Category from '../views/category/Category'
// import Profile from '../views/profile/Profile'
// import Cart from '../views/cart/Cart'
//路由懒加载方式导入组件的写法
const Home = () => import('../views/home/Home.vue')
const Category = () => import('../views/category/Category.vue')
const Profile = () => import('../views/profile/Profile.vue')
const Cart = () => import('../views/cart/Cart.vue')
export default new Router({
  mode:'history',
  routes: [
    {
      path: '/home',
      component: Home
    },
    {
      path: '/profile',
      component: Profile
    },
    {
      path: '/cart',
      component: Cart
    },
    {
      path: '/category',
      component: Category
    },
  ]
})
