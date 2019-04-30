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
    }
}
