package com.gsau
/**
  * @author WangGuoQing
  * @date 2019/5/2 21:12
  * @Desc           scala异常处理机制，Scala中只有一个catch,但在catch 有多个case  用于匹配捕捉异常
  *                 scala中符号“=>”的含义：
  *                 1.表示函数的返回类型(Function Type)
  *                 2.匿名函数
  *                 3.case语句
  *                 4.By-Name Parameters(传名参数）
  */
object ExceptionProcess {
    def main(args: Array[String]): Unit = {
        try {
            var x1: Int = 0
            var x2: Int = 1
            caluteNum(0, 1)
            //1.表示函数的返回类型(Function Type),函数的参数是Int 返回值类型是Int，计算逻辑乘以1024
            //var x: (Int) => Int = i=>i*1024
            var x: (Int) => Int = (_ * 1024)
            println("--------" + x(3))
            //2.匿名函数定义， 左边是参数 右边是函数实现体
            var xx = (x: Int) => {
                x + 1
                println("匿名函数" + x)
            }
            xx(20)

        } catch {
            //在模式匹配 match 和 try-catch 都用 “=>” 表示输出的结果或返回的值
            case exception: ArithmeticException => {
                println("捕获到0作为除数的异常")
            }
        } finally {
            println("程序结束....")
        }
    }
    /**
      * 计算
      *
      * @param num1
      * @param num2
      * @return
      */
    def caluteNum(num1: Int, num2: Int): Any = {
        var result = num1 / num2
        result
    }
    def sum(x1: Int = 1, x2: Int = 1): Int = {
        return x1 + x2
    }
    def double(x: Int): Int = x * 2
}
