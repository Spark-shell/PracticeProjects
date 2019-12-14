<template>
  <div>
    <h1>欢迎来到用户界面!!!</h1>
    <h5 v-text="params + userId ">{{ userId }}</h5>
  </div>
</template>
<script>
    export default {
        name: "User",
        computed:{
          userId(){
            return this.$route.params.userId
          },
          params(){
              console.log(this.$route.params)
            return ''
          }
      }
    }
</script>
<style scoped>
   
</style>
