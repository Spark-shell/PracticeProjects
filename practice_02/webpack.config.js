const path = require('path')
const HtmlWebpackPlugin=require("html-webpack-plugin")
const UglifyjsWebpackPlugin=require("uglifyjs-webpack-plugin")
//安装js压缩插件  npm install uglifyjs-webpack-plugin@1.1.1 --save-dev

module.exports = {
    entry: "./src/js/main.js",
    output: {
        path: path.resolve(__dirname, "dist"),
        filename: "boundle.js",
        publicPath: '../../dist/'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                // css-loader只负责加载
                //style-loader将样式添加到DOM中去
                //使用多个loader的时候加载的时候是从右向左加载的
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.less$/,
                // css-loader只负责加载
                //style-loader将样式添加到DOM中去
                //使用多个loader的时候加载的时候是从右向左加载的
                use: [{
                    loader: "style-loader" // creates style nodes from JS strings
                }, {
                    loader: "css-loader" // translates CSS into CommonJS
                }, {
                    loader: "less-loader" // compiles Less to CSS
                }]
            },
            {
                test: /\.(png|jpg|gif)$/,
                //>npm install css-loader file-loader less --save-dev
                //>npm install css-loader url-loader less --save-dev
                use: [
                    {
                        loader: 'url-loader',
                        options: {
                            limit: 1024,
                            name: 'images/[name].[hash:8].[ext]'
                        }
                    }
                ]
            },
            //npm install babel-preset-es2015  --save-dev
            {
                test: /\.js$/,
                exclude: /(node_modules|bower_components)/,         //排除
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['es2015']
                    }
                }
            },
            {
                test: /\.vue$/,
                use: ['vue-loader']
            }
        ]
    },
    resolve:{
        extensions:[".vue",".js",".css",".less"],
        alias:{
            'vue$' : 'vue/dist/vue.esm.js'
        }
    },
    plugins:[
        // npm install --save-dev html-webpack-plugin
        new HtmlWebpackPlugin({
            template:'./src/htmls/index.html'
        }),
        //实例化该插件时可以不配置任何参数
        new UglifyjsWebpackPlugin(),
    ]
}


