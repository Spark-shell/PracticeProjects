const {getInfo} =require('./page.js')
console.log(getInfo())
//依赖css文件
//npm install css-loader style-loader less --save-dev
require('../css/style.css')
//依赖less文件   npm install less-loader less --save-dev
require('../css/special.less')
//1.安装vue   npm install vue --save
//2.引入vue
import Vue from 'vue'                           //
// import App from '../vue/app.js'              //方式一
import App from '../vue/App.vue'                //方式二 配置加载vue文件的loader   npm install --save-dev vue-loader vue-template-compiler
const app=new Vue({
    el:'#app',
    template: '<App/>',
    components:{
        App
    }
})