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
axios({
  url:'http://httpbin.org/get',
  method:'get'
}).then(res => {
  console.log(res)
})

//请求方式2
axios.get('http://httpbin.org/get')
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
