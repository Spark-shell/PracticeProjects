<template>
  <div id="app">
    <main-tab-bar/>
  </div>
</template>
<script>
  import MainTabBar from 'components/content/mainTabbar/mainTabbar'
export default {
  name: 'app',
  components: {
    MainTabBar
  }
}
</script>

<style>
    @import "assets/css/base.css";
</style>
