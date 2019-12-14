// 要使用`import`命令加载的Vue构建版本
// （仅运行时或独立运行）已在webpack.base.conf中设置了别名。
import Vue from 'vue'
import App from './App'
//导入路由
import vueRouter from './router/index'

Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router:vueRouter,                 //使用路由
  components: { App },
  template: '<App/>'
})

