<template>
  <div >
    <h1>欢迎来到Cart</h1>
    <h5>{{$store.state.userInfo}}</h5>
    <h5>{{ $store.getters.pwd }}</h5>
    <button @click="add">补充信息</button>
    <button @click="addInfo">补充信息2</button>
    <button @click="del">删除信息</button>

  </div>
</template>
<script>
  export default {
    name: 'Cart',
    computed:{
      userInfo(){
        console.log($store.state )
        return ($store.state.userInfo)
      }
    },
    methods:{
      add(){
        console.log(this.$store.state)
        this.$store.commit('fillInfo')
      },
      del(){
        console.log(this.$store.state)
        this.$store.commit('delInfo')
      },
      addInfo(){
        let params={
          school:'甘肃农业大学',
          subject :'计算机科学与技术',
        }
        this.$store.commit('addInfos',params)
      }
    }
  }
</script>
<style scoped>
</style>
