export default {
    template: `
      <div>
            <h2> {{ msg }}</h2>
            <button @click="btnClick">点我啊！</button>
            <h2> {{ num }}</h2>
       </div>
    `,
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
    }
}