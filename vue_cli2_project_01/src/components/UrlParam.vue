<template>
  <div>
    <h1>用户信息查询!!!</h1>
    <h5> {{ params }}</h5>
  </div>
</template>
<script>
    export default {
        name: "UrlParam",
        computed:{
          params(){
              console.log(this.$route)
            return this.$route.query
          }
      }
    }
</script>
<style scoped>
   
</style>
