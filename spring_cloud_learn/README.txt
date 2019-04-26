springCloud 学习DEMO笔记
    Maven命令含义
        1.maven的用途
            maven是一个项目构建和管理的工具，提供了帮助管理 构建、文档、报告、依赖、scms、发布、分发的方法。可以方便的编译代码、进行依赖管理、管理二进制库等等。
            maven的好处在于可以将项目过程规范化、自动化、高效化以及强大的可扩展性
            利用maven自身及其插件还可以获得代码检查报告、单元测试覆盖率、实现持续集成等等。
        2.maven命令含义
            clearn :    删除项目路径下的target文件，但是不会删除本地的maven仓库已经生成的jar文件
            install:    在package的基础上，会在本地maven仓库生成jar文件，供其他项目使用
            compile:    编译命令，会在项目路径下生成一个target目录，目录包含一个classes文件夹，里面是class文件和字节码文件
            package:    在compile的基础上生成jar、war文件
            test:
            site:
            deploy:
            validate:

