<template>
  <div id="app">
    <div id="tab-bar">
      <div class="tab-bar-item">首页</div>
      <div class="tab-bar-item">分类</div>
      <div class="tab-bar-item">购物车</div>
      <div class="tab-bar-item">我的</div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'App'
  }
</script>
<style>
  /*在style中引入css文件的方式*/
  @import "assets/css/base.css";

  #tab-bar {
    display: flex;
    background: #42b983;
    position: fixed;
    right: 0;
    bottom: 0;
    left: 0;
    box-shadow: 0px -3px 1px rgba(66, 185, 131, 0.1);
  }
  .tab-bar-item {
    flex: 1;
    text-align: center;
    height: 49px;
  }
</style>
