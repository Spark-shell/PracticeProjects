<template>
  <div id="app">
<!--    <router-link to="/home" tag="button" >首页</router-link>-->
<!--    <router-link  to="/about">关于</router-link>-->
    <button @click="btnHome">首页</button>
    <button @click="btnAbout">关于</button>
    <router-link :to ="'/user/'+ userId">用户</router-link>
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </div>
</template>
<script>
export default {
  name: 'App',
  data(){
    return {
      img:'assets/girl.jpg',
      userId:'王国庆'
    }
  },
  methods:{
    btnHome(){
      this.$router.push("/home")
    },
    btnAbout(){
        this.$router.push("/about")
    }
  }
}
</script>
<style scoped>
  .router-link-active{
    color: #d0ff64;
  }
  #app{
    position: relative;
    left: 0%;
  }
</style>
