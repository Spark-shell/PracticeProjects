import Vue from 'vue'
import Vuex from 'vuex'
//1.安装插件  npm install vuex --save
//2.引用插件
Vue.use(Vuex)
//3.创建对象
const store = new Vuex.Store({
  state: {           //state：用来存放组件之间共享的数据。他跟组件的data选项类似，只不过data选项是用来存放组件的私有数据。
    userInfo: {
      username: 'wgq',
      age: 12,
      addr: '甘肃庆阳宁县',
      phone: '1889391054*',
    }
  },
  mutations: {      //mutations：前面讲到的都是如何获取state的数据，那如何把数据存储到state中呢？在 Vuex store 中，实际改变 状态(state) 的唯一方式是通过 提交(commit) 一个 mutation。　　mutations下的函数接收state作为参数，接收一个叫做payload（载荷）的东东作为第二个参数，这个东东是用来记录开发者使用该函数的一些信息，比如说提交了什么，提交的东西是用来干什么的，包含多个字段，所以载荷一般是对象（其实这个东西跟git的commit很类似）还有一点需要注意：mutations方法必须是同步方法！
    fillInfo(state) {
      state.userInfo.addr = '上海宝山'
    },
    delInfo(state) {
      state.userInfo.username = '?'
      state.userInfo.addr = '?'
    },
    addInfos(state, params) {
      //1.添加数据的时候，如果state中之前没有的属性，赋值(增加)的数据不会刷新到页面
      // this.state.userInfo.subject = params.subject
      // this.state.userInfo.school = params.school
      //2.要想刷新到页面，需要用这种方法
      Vue.set(this.state.userInfo,'subject',params.subject)
      Vue.set(this.state.userInfo,'school',params.school)
      //3.同样删除的时候，如果用这种方法做不到响应式
      // delete this.state.userInfo.age
      //4.要做到响应式需要用这种方式
      Vue.delete(this.state.userInfo,'age')

    }
  },
  getters: {        //对state的数据进行筛选，过滤，处理
    pwd(state) {
      let info = ''
      info += state.userInfo.username + "|"
      info += state.userInfo.age + "|"
      info += state.userInfo.addr + "|"
      info += state.userInfo.phone + "|"
      return info
    }
  },
  actions: {},
  modules: {}
})
//4.导出store
export default store