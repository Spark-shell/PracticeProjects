const path = require('path')
module.exports = {
    entry: "./src/js/main.js",
    output: {
        path: path.resolve(__dirname, "dist"),
        filename: "boundle.js",
        publicPath:'../../dist/'
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
                use: [
                    {
                        loader: 'url-loader',
                        options: {
                            limit: 1024,
                            name:'images/[name].[hash:8].[ext]'
                        }
                    }
                ]
            },
            {
                test: /\.js$/,
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            }
        ]
    }
}


