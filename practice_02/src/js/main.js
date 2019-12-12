const {getInfo} =require('./page.js')
console.log(getInfo())
//依赖css文件
require('../css/style.css')
//依赖less文件   npm install less-loader less --save-dev
require('../css/special.less')
//1.安装vue   npm install vue --save
//2.引入vue
import Vue from 'vue'
const App={
    template:`
      <div>
            <h2> {{ msg }}</h2>
            <button @click="btnClick">点我啊！</button>
            <h2> {{ num }}</h2>
       </div>
    `,
    data(){
        return {
            num:0,
            msg:"世界你好！！！"
        }
    },
    methods:{
        btnClick(){
            this.num ++
        }
    }
}
const app=new Vue({
    el:'#app',
     template: '<App/>',
    components:{
        App
    }
})