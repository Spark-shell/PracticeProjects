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
        println("function1 "+function1(1, 2)+
                "\nfunction2 "+function2()
        )
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
}
