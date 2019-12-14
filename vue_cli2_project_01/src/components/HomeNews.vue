<template>
  <div>
    <h1>欢迎来到新闻!!!</h1>
    <ul >
        <li v-for="item in news"> {{ item }}</li>
    </ul>
  </div>
</template>
<script>
    export default {
        name: "HomeNews",
        data(){
          return {
                  news:[
                    "实时热点换一换",
                    "大众车撞烂法拉" ,
                    "利厄齐尔发表不当言论" ,
                    "刘诗雯夺冠拉维奇宣布退役" ,
                    "AG对战QG" ,
                    "宋炳南逝世72岁老兵万里寻妻" ,
                    "哈登54分约翰逊胜选演说" ,
                    "杜德利被驱逐沃尔" ,
                    "母亲去世学生减负方案" ,
                    "曝马蜂窝裁员40%" ,
                    "邓莎拔火罐被烧伤" ,
                    "电梯被关老人猝死" ,
                    "国乒男单4强"
                  ]
          }
        },
        computed:{

      }
    }
</script>
<style scoped>
   
</style>
