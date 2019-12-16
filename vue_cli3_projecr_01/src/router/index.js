import VueRouter from 'vue-router'
import Vue from 'vue'

//1.通过Vue.use(插件)来安装插件
Vue.use(VueRouter)
//配置路由
export default new VueRouter({
    mode:'history',
    routes:[
        {
            path:'/home',
            component:null
        }
    ]
})
