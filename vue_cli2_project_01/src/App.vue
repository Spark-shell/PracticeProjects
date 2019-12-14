<template>
  <div id="app">
<!--    <router-link to="/home" tag="button" >首页</router-link>-->
<!--    <router-link  to="/about">关于</router-link>-->
    <button @click="btnHome">首页</button>
    <button @click="btnAbout">关于</button>
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  name: 'App',
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
    color: red;
  }
  #app{
    position: relative;
    left: 50%;
  }
</style>
