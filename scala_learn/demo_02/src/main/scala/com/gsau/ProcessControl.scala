package com.gsau
import scala.io.StdIn
/**
  * @author WangGuoQing
  * @date 2019/5/1 19:10
  * @Desc 练习二
  */
object ProcessControl {
    def main(args: Array[String]): Unit = {
        //orderControl()
        //branchControl()
        cycleControl()
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
      * @Desc 分支控制
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
      * @Desc 循环控制
      */
    def cycleControl(): Unit = {
        //for循环
    }

}
