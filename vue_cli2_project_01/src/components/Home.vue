<template>
  <div id="Home">
    <h1>欢迎来到Home!!!</h1>
    <router-link to="/home/homeNews">新闻</router-link>
    <router-link to="/home/homeMsgs">消息</router-link>
    <!--           <router-link to ="/home/urlParam">用户信息</router-link>-->
    <!--           <router-link :to ="{path:'/home/urlParam',query:{userName:'王国庆',password:'12346'} }">用户信息</router-link>    &lt;!&ndash; 方式一 &ndash;&gt;-->
    <button @click="btnUrl">用户信息</button>   <!-- 方式二 -->
    <div>
      <img src="../assets/logo.png">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Home",
    methods: {
      btnUrl() {
        this.$router.push({
          path: '/home/urlParam',
          query: {
            userName: "苍井空",
            age: 123,
            addr: '日本',
            sex: '女'
          }
        })
      }
    }
  }
</script>
<style scoped>

</style>
