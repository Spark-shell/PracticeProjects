package com.gsau
/**
  * @author WangGuoQing
  * @date 2019/4/30 14:37
  * @Desc Scala测试
  */
object HelloScala {
    /**
      * 返回值类型是Unit指的是空
      *
      * @param args
      */
    def main(args: Array[String]): Unit = {
        def age: Int = 20
        def name: String = 20 + ""
        def gender: Boolean = false
        //转义字符
        println(age + "|" + name + "|" + gender)
        println(age + "\t" + name + "\t" + gender + "\t")
        println(age + "\n" + name + "\n" + gender + "\n")
        println(age + "\r" + name + "\r" + gender + "\r")
        println(s"age=$age  name=$name  gender=$gender")
        //调用方法
        declareVariable()
        dataType()
    }
    /**
      * @author WangGuoQing
      * @date 2019/4/30 16:43
      * @Desc           声明变量练习
      *                 注意：
      *             1.变量类型可以指定也可以不指定，scala编译器会自动推断
      *             2.  var 声明变量可赋值
      *                 val 声明常量不可以赋值，编译后相当于加了final
      *                 3.声明变量的时候必须要初始值
      */
    def declareVariable(): Unit = {
        var age = 0
        var name: String = "name"
        val gender = false
        val gender1: String = "false"
        println(s"age=$age  name=$name  gender=$gender gender1=$gender1")
    }
    /**
      * @author WangGuoQing
      * @date 2019/4/30 16:50
      * @Desc   scala数据类型:scala和java有着相同的数据类型，但是在Scala中数据类型都是对象，没有java中的那种原生类型
      *         类别：
      *         1.AnyVal(值类型)：Double\Float\Long\Int\Short\Byte\Boolean\Char\StringOps\Unit
      *         2.AnyRef(引用类型)：Collect及其子类型
      *         3.Nothing:这个类型是Scala中的特别类型,可以将Nothing类型的值返回给任意变量或者函数，在抛出异常中经常使用
      *         4.scala中任然遵守低精度向高精度自动转换
      *         5.Unit:表示无值，和其他语言的void相同，Unit只有一个实例那就是"()"
      */
    def dataType(): Unit = {
        //因为scala中的数据类型都是对象，所以可以使用变量就和使用对象是一样的，比如name.contact("对对对")进行拼接字符串
        var name: String = "字符串"
        println(s"name=$name")
        println(name.concat("串"))
       return ()
    }
}
