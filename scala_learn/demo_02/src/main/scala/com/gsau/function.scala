package com.gsau
/**
  * @author WangGuoQing
  * @date 2019/5/3 9:33
  * @Desc 匿名函数，函数的高级玩法
  */
object function {
    def main(args: Array[String]): Unit = {
        //函数字面量简写方式
        //test1()
        //将函数作为参数进行传递,变量double是一个函数的实例，被称为函数值可以像调用方法一样调用它了
        val double = (i: Int) => i * 2
        //可以将double传入任何和它具有相同参数签名的方法或者函数中
        val list = List.range(1, 80)
        val list2 = list.filter(double(_) > 50)           //示例：1
        println(list2)
        var map = list.map(double)                        //示例：2
        println(map)
        //函数字面量声明的方式至少有两种，一种是隐式推断函数的返回值，另一种是：显式声明返回值的类型
        val f = (i: Int) => {i % 2 == 100}            //第一种方式
        val f1: (Int) => Boolean = i => {i % 2 == 0} //第二种方式
        val f2: Int => Boolean = i => {i % 2 == 0}   //第二种方式,简写
        val f3: Int => Boolean = i => i % 2 == 0
        val f4: Int => Boolean = _ % 2 == 0
        //测试
        println(list.filter(f))
        println(list.filter(f1))
        println(list.filter(f2))
        println(list.filter(f3))
        println(list.filter(f4))

    }
    /**
      * 函数字面量简写方式
      */
    def test1(): Unit = {
        val x = List.range(1, 10)
        //给list常量x得分 filter方法传入匿名函数，创建一个只有偶数的新list
        val events = x.filter((i: Int) => i % 2 == 0) //定义函数字面量的明确形式
        val events1 = x.filter(i => i % 2 == 0) //简化一：因为scala变异器可以推断出i是一个Int所以可以不用显示指定
        val events2 = x.filter(_ % 2 == 0)            //简化二：当参数在函数中只出现一次时，scala允许使用“_”通配符替换变量名
        println("x   " + x)
        println("events   " + events)
        /*
        上面函数字面量的代码组成：
             (i: Int) => i % 2 == 0
             1.(i: Int) 参数列表
             2.=>       转换器
             3.i % 2 == 0 函数体
         */
        //示例二
        x.foreach((i: Int) => print("   " + i))     //定义函数字面量的明确形式
        x.foreach((i) => print("   " + i))         //简化一
        x.foreach(i => print("   " + i))           //简化二
        x.foreach(println(_))                      //简化三
        x.foreach(println)                         //简化三
    }
}
