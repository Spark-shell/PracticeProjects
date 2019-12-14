const {add} =require('./module01.js')
console.log(add(125,125))
//依赖css文件
require('../css/style.css')
//依赖less文件   npm install less-loader less --save-dev
require('../css/special.less')