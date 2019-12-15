<template>
  <div id="tab-bar">
    <slot></slot>
  </div>
</template>

<script>
  export default {
    name: 'TabBar'
  }
</script>
<style scoped>
  /*在style中引入css文件的方式*/
  #tab-bar {
    display: flex;
    background: #42b983;
    position: fixed;
    right: 0;
    bottom: 0;
    left: 0;
    box-shadow: 0px -3px 1px rgba(66, 185, 131, 0.1);
  }
</style>
