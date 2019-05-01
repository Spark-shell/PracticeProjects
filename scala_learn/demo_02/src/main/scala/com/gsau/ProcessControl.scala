package com.gsau
/**
  * @author WangGuoQing
  * @date 2019/5/1 19:10
  * @Desc 练习二
  */
object ProcessControl {
    def main(args: Array[String]): Unit = {
        orderControl()
        branchControl()
        cycleControl()
    }
    /**
      * @author WangGuoQing
      * @date 2019/5/1 19:13
      * @Desc 顺序控制
      *      和java中的控制相同都是，从上到下顺序执行
      */
    def orderControl(): Unit = {
        var num1=12
        var num2=34 + num1
        println (num1+num2)
    }
    /**
      * @author WangGuoQing
      * @date 2019/5/1 19:13
      * @Desc 分支控制
      *      1.单分支
      *      2.多分支
      *      3.双分支
      */
    def branchControl(): Unit = {
        //if else

    }
    /**
      * @author WangGuoQing
      * @date 2019/5/1 19:13
      * @Desc 循环控制
      */
    def cycleControl() {}

}
