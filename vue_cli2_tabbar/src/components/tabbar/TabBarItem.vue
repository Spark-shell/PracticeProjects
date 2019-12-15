<template>
  <div class="tab-bar-item">
    <div v-if="!isActive" >
      <slot name="item-icon"></slot>
    </div>
    <div v-els>
      <slot e name="item-icon-active"></slot>
    </div>
    <div :class="{active:isActive}">
      <slot name="item-text"></slot>
    </div>
    <!--    <img src="../../assets/imgs/tabbar/home.svg"><div>首页</div>-->
  </div>
</template>
<script>
  export default {
    name: 'TabBarItem',
    data() {
      return {
        isActive: true
      }
    }
  }
</script>
<style scoped>
  /*在style中引入css文件的方式*/
  .tab-bar-item {
    flex: 1;
    text-align: center;
    height: 49px;
    font-size: 14px;
  }

  .tab-bar-item img {
    width: 24px;
    height: 24px;
    margin-top: 3px;
    vertical-align: middle;
    margin-bottom: 2px;
  }

  .active {
    color: red;
  }
</style>
