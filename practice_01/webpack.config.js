const path = require('path')
module.exports = {
    entry: "./src/js/main.js",
    output: {
        path: path.resolve(__dirname, "dist"),
        filename: "boundle.js"
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                // css-loader只负责加载
                //style-loader将样式添加到DOM中去
                use: ['style-loader', 'css-loader']
            }
        ]
    }
}


