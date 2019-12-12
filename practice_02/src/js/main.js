const {getInfo} =require('./page.js')
//1.安装vue   npm install vue --save
//2.引入vue
import Vue from 'vue'
console.log(getInfo())
require('../css/style.css')
//依赖less文件   npm install less-loader less --save-dev
require('../css/special.less')
const app=new Vue({
    el:'#app',
    data:{
        msg:"HelloWebPack!!!"
    }
})