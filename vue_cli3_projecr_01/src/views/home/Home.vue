<template>
    <div id="home" class="wrapper">
        <nav-bar class="home-nav"><div slot="center">购物街</div></nav-bar>
    </div>
</template>

<script>
    import NavBar from '../../components/common/navbar/NavBar'
    export default {
        name: "Home",
        components: {
            NavBar
        },
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
