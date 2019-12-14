//部署时配置文件，放的是部署时的依赖
const WebpackMerge=require("webpack-merge")
const baseConfig=require("./base.config.js")
const UglifyjsWebpackPlugin=require("uglifyjs-webpack-plugin")
module.exports =WebpackMerge(baseConfig,{
    plugins:[
        new UglifyjsWebpackPlugin(),
    ]
})


