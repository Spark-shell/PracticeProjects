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
//configureWebpack 
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
* 安装axios
```shell 
//引入axios
import axios from 'axios/index'
//导出
export function request(config) {
    // 1.创建axios的实例
    const axiosInstace = axios.create({
        baseURL: 'http://123.207.32.32:8000',
        timeout: 5000
    })
    // 2.axios的拦截器
    // 2.1.请求拦截的作用
    axiosInstace.interceptors.request.use(config => {
        return config
    }, err => {
        // console.log(err);
    })
    // 2.2.响应拦截
    axiosInstace.interceptors.response.use(res => {
        return res.data
    }, err => {
        console.log(err);
    })
    // 3.发送真正的网络请求
    return axiosInstace(config)
}
```
* 安装Scroll
~~~shell
npm install better-scroll --dev
~~~





 