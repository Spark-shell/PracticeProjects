<template>
  <div id="app">
    <router-view></router-view>
    <tab-bar>
      <tab-bar-item path="/home" activeColor="red">
        <img slot="item-icon" src="./assets/imgs/tabbar/home.svg">
        <img slot="item-icon-active" src="./assets/imgs/tabbar/home_active.svg">
        <div slot="item-text">首页</div>
      </tab-bar-item>
      <tab-bar-item path="/category" activeColor="red">
        <img slot="item-icon-active" src="./assets/imgs/tabbar/category_active.svg">
        <img slot="item-icon" src="./assets/imgs/tabbar/category.svg">
        <div slot="item-text">分类</div>
      </tab-bar-item>
      <tab-bar-item path="/cart" activeColor="red">
        <img slot="item-icon-active" src="./assets/imgs/tabbar/shopcart_active.svg">
        <img slot="item-icon" src="./assets/imgs/tabbar/shopcart.svg">
        <div slot="item-text">购物车</div>
      </tab-bar-item>
      <tab-bar-item path="/profile" activeColor="red">
        <img slot="item-icon-active" src="./assets/imgs/tabbar/profile_active.svg">
        <img  slot="item-icon" src="./assets/imgs/tabbar/profile.svg">
        <div slot="item-text">我的</div>
      </tab-bar-item>
    </tab-bar>
  </div>
</template>
<script>
  import TabBar from './components/tabbar/TabBar'
  import TabBarItem from './components/tabbar/TabBarItem'
  export default {
    name: 'App',
    components:{
      TabBar,
      TabBarItem
    }
  }
</script>
<style>
  /*在style中引入css文件的方式*/
  @import "assets/css/base.css";
</style>
