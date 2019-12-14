<template>
     <div id="Home">
          <h1>欢迎来到Home!!!</h1>
           <router-link to ="/home/homeNews">新闻</router-link>
           <router-link to ="/home/homeMsgs">消息</router-link>
       <div>
         <img src="../assets/logo.png">
         <router-view></router-view>
       </div>
     </div>
</template>

<script>
    export default {
        name: "Home"
    }
</script>
<style scoped>

</style>
