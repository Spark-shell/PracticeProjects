package com.gsau
import scala.io.StdIn
/**
  * @author WangGuoQing
  * @date 2019/5/1 19:10
  * @Desc 循环练习
  *      1.Scala内置控制结构去掉了break和continue，推荐使用函数的风格解决break和continue的功能
  */
object ProcessControl {
    def main(args: Array[String]): Unit = {
        //orderControl()
        //branchControl()
        forCycleControl()
        whileCycleControl()
    }
    /**
      * @author WangGuoQing
      * @date 2019/5/1 19:13
      * @Desc 顺序控制
      *       和java中的控制相同都是，从上到下顺序执行
      */
    def orderControl(): Unit = {
        var num1 = 12
        var num2 = 34 + num1
        println(num1 + num2)
    }
    /**
      * @author WangGuoQing
      * @date 2019/5/1 19:13
      * @Desc       分支控制
      *      1.单分支
      *      2.多分支
      *      3.双分支
      *      4.注意：
      *             4.1 如果｛｝括号中的代码只有一行｛｝括号可以省略
      *             4.2 Scala中任意表达式都是有返回值的，返回值取决于表达式的最后一行内容
      *             var flag = if (2 > 3) "真确" else "错误"
      *             4.3 scala中的嵌套分支最好不要超过三层
      */
    def branchControl(): Unit = {
        var num1 = 12
        //if else 单分支
        if (num1 >= 12) {
            println("满足大于等于12")
        }
        //双分支
        println("请输入数字")
        num1 = StdIn.readInt()
        if (num1 > 12) {
            println("大")
        } else if (num1 < 12) {
            println("大")
        } else if (num1 == 12) {
            println("刚合适")
        }

        var flag = if (2 > 3) "真确" else "错误"
        println(flag)
    }
    /**
      * @author WangGuoQing
      * @date 2019/5/1 19:13
      * @Desc for循环控制
      */
    def forCycleControl(): Unit = {
        //for循环  范围数据循环方式一（打印1 到 10前后封闭）
        for (i <- 1 to 10) {
            print(i)
        }
        var list = List("start", 1, 2, 4, 5, 6, "end")
        for (item <- list) {
            if (item == "start") {
                println()
            }
            if (item != "end")
                print(item + " <-")
            else
                print(item)

        }
        //for循环  范围数据循环方式二（打印1 到 10前后不封闭）
        for (i <- 1 until 10) println("until " + i)
        //for循环  循环守卫
        for (i <- 1 to 100 if i < 50) println("守卫 " + i)
        //for循环  引入变量
        for (i <- 1 to 100; j = i + 33; if j > 50) println("引入变量 " + i + "  j(33)  " + j)
        //for循环  嵌套循环
        for (i <- 1 to 100; j <- i + 1 to 10) println("嵌套循环 " + i + "  j=i+1=  " + j)
        //循环返回值  使用yield将遍历的结果返回到一个新的Vector（向量）中
        var returnValue = for (i <- 0 to 20) yield i
        println(returnValue)
        //for循环 可以使用｛｝代替（）
        for {
            i <- 1 to 30
            if i < 25
            j = i * i
        } {println((i, j))}
        //for循环 的步长控制
        for (i <- Range(1, 100, 2)) {
            println(i)
        }
    }
    /**
      * @author WangGuoQing
      * @date 2019/5/1 20:07
      * @Desc while循环控制
      *      1.while循环没有返回值
      *      2.条件是一个返回值布尔值的表达式
      *      3.不推荐用while推荐使用for
      */
    def whileCycleControl(): Unit = {
        var i = 0
        while (i < 1000) {
            println(i)
            i += 1
        }
        //do while循环
        do {
            println(i)
            i += 10
        } while (i < 10000)
    }
}
