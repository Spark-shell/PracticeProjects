import VueRouter from 'vue-router'
import Vue from 'vue'
//1.通过Vue.use(插件)来安装插件
Vue.use(VueRouter)

import Home from '../views/home/Home'
import Category from '../views/category/Category'
import Cart from '../views/cart/Cart'
import Profile from '../views/profile/Profile'
//配置路由
export default new VueRouter({
    mode:'history',
    routes:[
        {
            path:'',
            component:Home
        },
        {
            path:'/home',
            component:Home
        },
        {
            path:'/category',
            component:Category
        },
        {
            path:'/cart',
            component:Cart
        },
        {
            path:'/profile',
            component:Profile
        }
    ]
})
