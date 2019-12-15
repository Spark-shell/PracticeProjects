import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import axios from 'axios'

Vue.config.productionTip = false
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
//安装axios
//axios的全局配置
axios.defaults.baseURL='http://httpbin.org/'
// axios.defaults.timeout=5
axios({
  url:'get',
  method:'get'
}).then(res => {
  console.log(res)
})
//请求方式2
axios.get('get')
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
