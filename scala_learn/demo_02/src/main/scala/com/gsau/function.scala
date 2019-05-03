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
        //将已经存在的函数/方法赋给函数变量,称为应用函数
        val c = scala.math.cos(_)
        val pow = scala.math.pow(_, _)
        println(c(30))
        println(pow(1, 3))
        val sum: (Int, Int, Int) => Int = (x, y, z) => {x + y + z} //声明函数一
        val sum_1: (Int) => Int = sum(1, 2, _: Int)                 //部分应用函数
        println(sum_1(20))
        //从函数或者方法中返回一个函数（算法）
        val say = saySomeThing("开始说了...")
        var greet = greeting("Chinese")
        println(say("说什么？"))
        println(greet("王先生"))
        //调用偏函数
        println(divide(12))
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
    def test2(): Unit = {
        //将函数作为参数进行传递,变量double是一个函数的实例，被称为函数值可以像调用方法一样调用它了
        val double = (i: Int) => i * 2
        //可以将double传入任何和它具有相同参数签名的方法或者函数中
        val list = List.range(1, 80)
        val list2 = list.filter(double(_) > 50)           //示例：1
        println(list2)
        var map = list.map(double)                        //示例：2
        println(map)
        //函数字面量声明的方式至少有两种，一种是隐式推断函数的返回值，另一种是：显式声明返回值的类型
        val f = (i: Int) => {i % 2 == 100} //第一种方式
        val f1: (Int) => Boolean = i => {i % 2 == 0} //第二种方式
        val f2: Int => Boolean = i => {i % 2 == 0} //第二种方式,简写
        val f3: Int => Boolean = i => i % 2 == 0
        val f4: Int => Boolean = _ % 2 == 0
        //测试
        println(list.filter(f))
        println(list.filter(f1))
        println(list.filter(f2))
        println(list.filter(f3))
        println(list.filter(f4))
        //隐式声明函数(implicit approach)
        val add = (x: Int, y: Int) => {x + y}
        val add1 = (x: Int, y: Int) => x + y
        //显式声明函数(explicit approach)
        val add2: (Int, Int) => Int = (x, y) => {x + y}
        val add3: (Int, Int) => Int = (x, y) => x + y
        println(add(1, 2))
        println(add1(1, 2))
        println(add2(1, 2))
        println(add3(1, 2))
    }
    /**
      * 定义一个返回函数的函数
      * “=”号的左边是，是常见的方法定义，右边是一个函数字面量
      *
      * @param prefix
      * @return
      */
    def saySomeThing(prefix: String) = (s: String) => {
        prefix + "    " + s
    }
    def greeting(language: String) = (name: String) => {
        language match {
            case "Englist" => "Hello " + name
            case "Chinese" => "你好 " + name
        }
    }
    /**
      * 偏函数详解
      * 函数的输入值是Int
      * 函数的返回值是Int
      *
      * @return
      */
    def divide = new PartialFunction[Int, Int] {
        def apply(x: Int) = 42 / x
        def isDefinedAt(x: Int) = x != 0
        /**
          * 声明一个偏函数，输入Int返回String
          * isDefinedAt（判断输入值是否在值域内）
          */
        val convertLowNumToString = new PartialFunction[Int, String] {
            override def isDefinedAt(x: Int) = x > 0 && x < 6
            override def apply(v1: Int): String = v1.toString
        }
        //偏函数很棒的特性就是可以将它们连在一起使用，比如一个可以计算奇数另一个可以计算偶数，那他们连起来就可以计算所有的整数了
        val convert1to5=new PartialFunction[Int,String] {
            override def isDefinedAt(x: Int)= x >=1 && x<=5
            override def apply(v1: Int): String = v1+"->"
        }
        val convert5to10=new PartialFunction[Int,String] {
            override def isDefinedAt(x: Int)=  x>5&&x<=10
            override def apply(v1: Int): String = v1+"->"
        }
        val nums=convert1to5 orElse convert5to10            //orElse 是PartialFunction特质特有的
        for(item<- List.range(1,10)){
            println(nums(item))
        }
    }
}
