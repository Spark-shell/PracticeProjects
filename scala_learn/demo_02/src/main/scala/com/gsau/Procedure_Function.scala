package com.gsau
/**
  * @author WangGuoQing
  * @date 2019/5/2 20:05
  * @Desc 函数（function）和过程（procedure）的区别
  *      1.将函数的返回值类型是Unit的函数称之为过程
  *
  */
object Procedure_Function {
    def main(args: Array[String]): Unit = {
        lazy val sum = lazyFunction(2, 3)
        println("---------------")
        println(sum)
    }
    /**
      * 定义一个过程
      */
    def func1(): Unit = {
        println("过程！")
    }
    /**
      *     当函数返回值被声明为lazy时，函数的执行将会被推迟，知道首次用其结果的时候函数才会执行，这种函数被称作惰性函数
      * 注意：
      *     1.lazy不能修饰var 类型的变量
      *     2.不单单是在函数调用时，加了lazy会导致函数的延迟执行，同样在声明一个常量的时候加了lazy，那么变量值的分配也会被延迟
      * @param n1
      * @param n2
      * @return
      */
    def lazyFunction(n1: Int, n2: Int): Int = {
        println("lazyFunction函数执行.....")
        return n1 + n2
    }
}
