### vue练习案例项目
**开发准备**
* vue cli3
* 安装路由 npm install vue-router --save
* 路由基本配置
```
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
```
* 路径别名配置
```js
module.exports = {
    configureWebpack: {
        resolve: {
            alias: {
                'assets': '@/assets',
                'common': '@/common',
                'components': '@/components',
                'network': '@/network',
                'views': '@/views',
            }
        }
    }
}
```





 