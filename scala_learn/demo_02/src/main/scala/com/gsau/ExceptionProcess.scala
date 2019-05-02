package com.gsau
object ExceptionProcess {
    def main(args: Array[String]): Unit = {
        try {
            caluteNum(0, 0)
        } catch {
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
}
