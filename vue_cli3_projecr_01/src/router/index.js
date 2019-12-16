import VueRouter from 'vue-router'
import Vue from 'vue'
//1.通过Vue.use(插件)来安装插件
Vue.use(VueRouter)
const Home = () => import('../views/home/Home')
const Profile = () => import('../views/profile/Profile')
const Cart = () => import('../views/cart/Cart')
const Category = () => import('../views/category/Category')
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
