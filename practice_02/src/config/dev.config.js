//开发是配置文件，放的是开发时的依赖
const WebpackMerge=require("webpack-merge")
const baseConfig=require("./base.config.js")
module.exports = WebpackMerge(baseConfig,{
    devServer:{
        contentBase:"./dist",
        inline:true,
        port:8081,
    }
})




