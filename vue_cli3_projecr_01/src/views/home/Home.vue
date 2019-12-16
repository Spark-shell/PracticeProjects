<template>
    <div id="home" class="wrapper">
        <nav-bar class="home-nav"><div slot="center">购物街</div></nav-bar>
        <tab-control :titles="['流行', '新款', '精选']"   @tabClick="tabClick" ></tab-control>
    </div>
</template>

<script>
    import NavBar from '../../components/common/navbar/NavBar'
    import TabControl from '../../components/content/tabControl/TabControl'
    export default {
        name: "Home",
        components: {
            NavBar,
            TabControl
        },
        methods: {
            tabClick(index) {
                 console.log(index)
            },
        }
    }
</script>

<style scoped>
    #home {
        height: 100vh;
        position: relative;
    }
    .home-nav {
        background-color: var(--color-tint);
        color: #fff;
    }

</style>
