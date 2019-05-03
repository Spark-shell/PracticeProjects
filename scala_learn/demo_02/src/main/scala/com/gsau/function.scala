package com.gsau
/**
  * @author WangGuoQing
  * @date 2019/5/3 9:33
  * @Desc 匿名函数，函数的高级玩法
  */
object function {
    def main(args: Array[String]): Unit = {
        test1
    }

    def test1(): Unit = {
        val x = List.range(1, 100)
        //给list常量x得分 filter方法传入匿名函数，创建一个只有偶数的新list
        val events = x.filter((i: Int) => i % 2 == 0)   //定义函数字面量的明确形式
        val events1 = x.filter(i => i % 2 == 0)         //简化一：因为scala变异器可以推断出i是一个Int所以可以不用显示指定
        val events2 = x.filter(_ % 2 == 0)              //简化二：当参数在函数中只出现一次时，scala允许使用“_”通配符替换变量名
        println("x   " + x)
        println("events   " + events)
        /*
        上面函数字面量的代码组成：
             (i: Int) => i % 2 == 0
             1.(i: Int) 参数列表
             2.=>       转换器
             3.i % 2 == 0 函数体
         */
    }
}
