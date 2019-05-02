package com.gsau
/**
  * @author WangGuoQing
  * @date 2019/5/1 20:20
  * @Desc   函数式编程
  *         概念明确：
  *         1.在Scala中方法和函数几乎可以等同（比如他们的定义、使用、运行机制都是一样的），只是函数的使用方式更加的灵活多样
  *         2.函数式编程是从编程方式的角度来谈的，可以这样理解：函数是编程是把函数当做一等公民来处理的，充分利用函数、支持函数的多种
  *         使用方式，比如函数可以像变量一样既可以作为函数的参数也可以将函数赋值给一个变量，函数的创建不依赖于类或者对象
  *         3.面向对象编程是以对象为基础的编程方式
  *         4.在Scala中函数式编程和面向对象编程混合在了一起
  */
object FunctionalProgramming {
    def main(args: Array[String]): Unit = {
        function1(1, 2)
        function3
        function4
        function5
        getInfo()
        getInfo("小明",23,"12345455")
        getInfo(name="小白")

    }
    //基础语法练习（Basic grammar practice）
    /**
      * 有输入参数和返回值的函数,有返回值如果没有写return，那么返回的值就是最后一行的值
      *
      * @param x 参数1
      * @param y 参数2
      * @return String 返回值
      */
    def function1(x: Int, y: Int): String = {
        return (x + y).toString
    }
    /**
      * 当返回值不确定的时候使用 “=”号，确定的使用使用“:=”指定返回值的类型，当没有返回指的时候直接使用“:”
      */
    def function2() = {
        var i = 2
        var j = 3
        i + j
    }
    /**
      * @author WangGuoQing
      * @date 2019/5/2 19:16
      * @Desc
      *      1.函数的形参列表可以是多个，如果函数没有形参那么调用的时候可以不使用（）括号
      *      2.形参列表和返回值列表的数据类型可以是值类型和引用类型
      *      3.scala中的函数可以根据函数体最后一行代码自行推断函数的返回值类型，所以可以省略return
      */
    def function3() {
        println("function3函数被调用了！！！！")
    }
    /**
      * @author WangGuoQing
      * @date 2019/5/2 19:21
      * @Desc 因为Scala可以推断返回值类型，所以省略return关键字的场合返回值的类型也是可以省略的
      *      1.如果函数体中使用了return关键字，那么函数就不能自行推断了，这个时候就需要明确指定返回值得类型（:String =）
      *      2.如果不要求函数有返回值[如：def  func(){} ]，那么即使在函数体内使用了return value也是无效的
      */
    def function4() = {
        var num1 = 13
        var num2 = 12
        (num1 + num2)
    }
    /**
      * @author WangGuoQing
      * @date 2019/5/2 19:30
      * @Desc 如果明确无返回值或者不确定返回值类型，那么返回值的类型可以省略，或者声明成Any
      *      1.scala中任何的语法结构都可以嵌套其他语法结构
      */
    def function5(): Any = {
        var i = 0
        var j = i + 1
        //函数中定义函数
        def innerFunct(x: Int, y: Int): Any = x + "|________|" + y
        println("函数嵌套： " + innerFunct(i, j))
    }
    /**
      * @author WangGuoQing
      * @date 2019/5/2 19:45
      * @Desc  创建带有默认形参值的函数
      *      1.如果函数存在多个参数都可以设定默认值，那么这个时候传递的参数到底是覆盖默认值，还是赋值给没有默认值的参数？
      *      这就不确定了（默认是按照从左到右的方式覆盖），这种情况下可以采用带名参数， getInfo(name="小白")这样咯
      */
    def getInfo(name:String="王国庆",age:Int=12,addr:String="上海"): Any = {
            println(s"name=$name   age=$age    addr=$addr")
    }
}
