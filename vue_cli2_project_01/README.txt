搭建本地服务器
 1.webpack提供了一个可选的本地开发服务器，这个本地服务器基于node.js搭建，内部使用express框架，可以实现我们想要的让浏览器自动刷新显示我们修改后的结果。不过它是一个单独的模块，在webpack中使用之前需要先安装它
  npm install --save-dev webpack-dev-server@2.9.1
 2.devserver也是作为webpack中的一个选项，选项本身可以设置如下属性：
       contentBase：为哪一个文件夹提供本地服务，默认是根文件夹，我们这里要填写./dist
       port：端口号
       inline：页面实时刷新
       historyApiFallback：在SPA页面中，依赖HTML5的history模式
 3.webpack.config.js 中配置：  (只是在开发的时候配置)
      devServer:{
              contentBase:"./dist",
              inline:true,
              rort:8081,
          },
      启动方式两种：
           1.路径启动：因为npm install --save-dev webpack-dev-server@2.9.1命令安装的是开发时依赖不是全局的
           2.在package.json中配置
               "scripts": {
                   "test": "echo \"Error: no test specified\" && exit 1",
                   "dev": "webpack-dev-server  --open"    //加上--open 启动成功后会自动打开浏览器页面
                  }
       执行命令：npm run dev
 4.配置文件的抽离：
       1.安装配置文件合并插件：
           npm install webpack-merge --save-dev      //开发时依赖
       2.配置文件写法：
           prod.config.js:
                   //部署时配置文件，放的是部署时的依赖
                   const WebpackMerge=require("webpack-merge")
                   const baseConfig=require("./base.config.js")
                   const UglifyjsWebpackPlugin=require("uglifyjs-webpack-plugin")
                   module.exports =WebpackMerge(baseConfig,{
                       plugins:[
                           new UglifyjsWebpackPlugin(),
                       ]
                   })
           dev.config.js:
               //开发时配置文件，放的是开发时的依赖
               const WebpackMerge=require("webpack-merge")
               const baseConfig=require("./base.config.js")
               module.exports = WebpackMerge(baseConfig,{
                   devServer:{
                       contentBase:"./dist",
                       inline:true,
                       port:8081,
                   }
               })
       3.修改package.json
                    "build": "webpack ",
                    "dev": "webpack-dev-server --open "
                修改成这样：
                    "build": "webpack --config ./src/config/dev.config.js",
                    "dev": "webpack-dev-server --open  --config ./src/config/dev.config.js"  //教程说这里可以不用加这个  --config ./src/config/dev.config.js 但是不加，会报找不到配置文件的错误
5.Vue Cli (脚手架)
   意义：
       使用Vue.js开发大型应用时，我们需要考虑代码目录结构、项目结构和部署、热加载、代码单元测试等事情。如果每个项目都要手动完成这些工作，那无疑效率比较低效，所以通常会使用一些脚手架工具来帮助完成这些事情
   认识：``
       CLI是Command-Line Interface, 翻译为命令行界面, 但是俗称脚手架.
       Vue CLI是一个官方发布 vue.js 项目脚手架，使用 vue-cli 可以快速搭建Vue开发环境以及对应的webpack配置
   Vue CLI使用前提 - Node：
       1.安装NodeJS
           可以直接在官方网站中下载安装.  网址: http://nodejs.cn/download/
       2.检测安装的版本
           2.1 默认情况下自动安装Node和NPM，Node环境要求8.9以上或者更高版本
           2.2 NPM的全称是Node Package Manager 是一个NodeJS包管理和分发工具，已经成为了非官方的发布Node模块（包）的标准。
   安装Vue脚手架：
       npm install -g @vue/cli     //-g g是global的简写
       注意：npm install -g @vue/cli 默认安装的是Vue CLI3的版本，如果需要想按照Vue CLI2的方式初始化项目时需要拉取下2.x的模板，使用 npm install -g @vue/cli-init  安装一个桥接工具,之后就可以可以用旧版本的 vue init 功能了
   Vue CLI2初始化项目： vue init webpack my-project
       初始化项目的过程：
           1. > vue init webpack vueclie_project_01
           2.? Project name (vueclie_project_01)        //项目名称
           3.? Project description (A Vue.js project)   //作者的信息默认会从git中拉取
           4.? Author (bfeng  <1456065693@qq.com>)
           5.? Vue build (Use arrow keys)                 //选择编译的方式
                 > Runtime + Compiler: recommended for most users
                 Runtime-only: about 6KB lighter min+gzip, but templates (or any Vue-specific HTML) are ONLY allowed in .vue files - render functions are required elsewhere
             如果在之后的开发中，依然使用template，就需要选择Runtime-Compiler,如果之后的开发中，使用的是.vue文件夹开发，那么可以选择Runtime-only
           6.? Install vue-router? (Y/n)               //是否安装路由
           7.? Use ESLint to lint your code? (Y/n)     //是否使用使用ESLint检查代码规范
           8.? Set up unit tests (Y/n)                 //是否启用单元测试
           9.? Setup e2e tests with Nightwatch? (Y/n)  //是否启用端到端测试
           10? Should we run `npm install` for you after the project has been created? (recommended) (Use arrow keys)   //选择项目中包管理工具
               > Yes, use NPM
                 Yes, use Yarn
                 No, I will handle that myself
   Vue CLI3初始化项目：vue create vue_cli3_projecr_01
            说明：
                 vue-cli 3 与 2 版本有很大区别
                 vue-cli 3 是基于 webpack 4 打造，vue-cli 2 还是 webapck 3
                 vue-cli 3 的设计原则是“0配置”，移除的配置文件根目录下的，build和config等目录
                 vue-cli 3 提供了 vue ui 命令，提供了可视化配置，更加人性化
                  移除了static文件夹，新增了public文件夹，并且index.html移动到public
            初始化项目的过程：
              1.? Please pick a preset: (Use arrow keys)    //选择配置预设
                > default (babel, eslint)                   //1.默认配置
                  Manually select features                  //2.手动选择一些特殊配置
              2.? Where do you prefer placing config for Babel, ESLint, etc.? (Use arrow keys)   //  Babel, ESLint, etc这些东西的配置方式
                > In dedicated config files                 //1.专门写一个配置文件
                  In package.json                           //2.放在package.json当中
              3.? Save this as a preset for future projects? (y/N)    //是否保存这些配置在将来的项目中
  关于Runtime Only 和 Runtime+Compiler：
                  5.0 vue的编译过程：template-->ast-->render--->virtual dom--->UI
                  5.1 Runtime Only
                     我们在使用 Runtime Only 版本的 Vue.js 的时候，通常需要借助如 webpack 的 vue-loader 工具把 .vue 文件编译成 JavaScript，因为是在编译阶段做的，所以它只包含运行时的 Vue.js 代码，因此代码体积也会更轻量。
                  在将 .vue 文件编译成 JavaScript的编译过程中会将组件中的template模板编译为render函数，所以我们得到的是render函数的版本。所以运行的时候是不带编译的，编译是在离线的时候做的。
                  5.2 Runtime+Compiler
                  我们如果没有对代码做预编译，但又使用了 Vue 的 template 属性并传入一个字符串，则需要在客户端编译模板，如下所示
                  // 需要编译器的版本
                  new Vue({
                    template: '<div>{{ hi }}</div>'
                  })
                  // 这种情况不需要
                  new Vue({
                    render (h) {
                      return h('div', this.hi)
                    }
                  })
   说明：Vue.js官方脚手架工具就使用了webpack模板,对所有的资源会做压缩等优化操作,它在开发过程中提供了一套完整的功能，能够使得我们开发过程中变得高效。
