<template>
    <div>
        <h2 id="h2"> {{ msg }}</h2>
        <button @click="btnClick">点我啊！</button>
        <h2> {{ num }}</h2>
        <Cpn/>
    </div>
</template>
<script>
    import Cpn from './Cpn.vue'

    export default {
        name: 'App',
        data() {
            return {
                num: 0,
                msg: "世界你好！！！"
            }
        },
        methods: {
            btnClick() {
                this.num++
            }
        },
        components: {
            Cpn
        }
    }
</script>
<style scoped>
    h2 {
        color: #ff2327;
    }
</style>