const path=require('path')
module.exports={
    entiry:"./src/js/main.js",
    output:{
        path:path.reslove(__dirname,"dist"),
        filename:"boundle.js"
    }
}