import Vue from 'vue'
import Vuex from 'vuex'
//1.安装插件  npm install vuex --save
//2.引用插件
Vue.use(Vuex)
//3.创建对象
const store = new Vuex.Store({
  state: {           //state：用来存放组件之间共享的数据。他跟组件的data选项类似，只不过data选项是用来存放组件的私有数据。
  },
  mutations: {      //mutations：前面讲到的都是如何获取state的数据，那如何把数据存储到state中呢？在 Vuex store 中，实际改变 状态(state) 的唯一方式是通过 提交(commit) 一个 mutation。　　mutations下的函数接收state作为参数，接收一个叫做payload（载荷）的东东作为第二个参数，这个东东是用来记录开发者使用该函数的一些信息，比如说提交了什么，提交的东西是用来干什么的，包含多个字段，所以载荷一般是对象（其实这个东西跟git的commit很类似）还有一点需要注意：mutations方法必须是同步方法！
  },
  getters: {        //对state的数据进行筛选，过滤

  },
  actions: {},
  modules: {}
})
//4.导出store
export default store
