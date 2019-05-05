package com.gsau
/**
  * @author WangGuoQing
  * @date 2019/5/3 17:01
  * @Desc 定义接受简单函数作为参数的方法，和闭包问题
  */
object ScalaClosure {
    def main(args: Array[String]): Unit = {
        method1(x=>println(x+"====method1======"),"测试参数是函数的方法")
        mothod2((x,y) => x >= y,1,2)
    }
    /**
      * 参数接受一个函数，这个函数要求输入一个Int值不返回
      * @param f
      */
    def method1(f:String=>Unit,parameter1:String): Unit ={
       f(parameter1)
    }
    /**
      * 接受一个函数作为参数，这个函数的输入值是（Int,Int）返回值的Boolean
      * @param f1
      * @param num1
      * @param num2
      */
    def mothod2(f1:(Int,Int)=>Boolean,num1:Int,num2:Int): Unit ={
        var flag:Boolean=f1(num1,num2)
        println(s"==返回的是===$flag")
    }
}
