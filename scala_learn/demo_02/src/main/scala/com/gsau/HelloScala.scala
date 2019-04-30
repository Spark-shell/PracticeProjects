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
        println(s"age=$age  name=$name  gender=$gender")
        //调用方法
        declareVariable()
        dataType()
        dataTypeTransform()
        forcedTypeConversion()
        operatorSymbol()
    }
    /**
      * @author WangGuoQing
      * @date 2019/4/30 16:43
      * @Desc           声明变量练习
      *                 注意：
      *             1.变量类型可以指定也可以不指定，scala编译器会自动推断
      *             2.  var 声明变量可赋值
      *                 val 声明常量不可以赋值，编译后相当于加了final
      *                 3.声明变量的时候必须要初始值
      */
    def declareVariable(): Unit = {
        var age = 0
        var name: String = "name"
        val gender = false
        val gender1: String = "false"
        println(s"age=$age  name=$name  gender=$gender gender1=$gender1")
    }
    /**
      * @author WangGuoQing
      * @date 2019/4/30 16:50
      * @Desc   scala数据类型:scala和java有着相同的数据类型，但是在Scala中数据类型都是对象，没有java中的那种原生类型
      *         类别：
      *         1.AnyVal(值类型)：Double\Float\Long\Int\Short\Byte\Boolean\Char\StringOps\Unit
      *         2.AnyRef(引用类型)：Collect及其子类型
      *         3.Nothing:这个类型是Scala中的特别类型,可以将Nothing类型的值返回给任意变量或者函数，在抛出异常中经常使用
      *         4.scala中任然遵守低精度向高精度自动转换
      *         5.Unit:表示无值，和其他语言的void相同，Unit只有一个实例那就是"()"
      */
    def dataType(): Unit = {
        //因为scala中的数据类型都是对象，所以可以使用变量就和使用对象是一样的，比如name.contact("对对对")进行拼接字符串
        var name: String = "字符串"
        println(s"name=$name")
        println(name.concat("串"))
        return ()
    }
    /**
      * @author WangGuoQing
      * @date 2019/4/30 17:16
      * @Desc 数据类型转换,精度从低的往高的转换
      */
    def dataTypeTransform(): Unit = {
        var i: Int = 10
        var j: Float = 20
        println(i * j)
    }
    /**
      * @author WangGuoQing
      * @date 2019/4/30 17:20
      * @Desc scala强制类型转换：自动转换的逆过程，高精度强制转向低精度的时候，可能造成精度降低或者溢出
      */
    def forcedTypeConversion(): Unit = {
        var x: Int = 23
        var y: Double = 343.23
        println(x)
        println(y)
        //强制类型转换
        println(y.toInt)
    }
    /**
      * @author WangGuoQing
      * @date 2019/4/30 17:27
      * @Desc 运算符
      *      1.算术运算符：=、-、*、/、%（取余）、+
      *      2.赋值运算符：=、+=、-=、*=、/=、%=
      *      3.比较运算符：==、!=、<、>、<=、>=
      *      4.逻辑运算符：&& 、|| 、!
      *      5.位运算符:&、|、~、<<、>>、>>>
      *      6.运算符的优先级：
      *               ^  ()[]
      *               ^  单目运算符
      *               ^  算数运算符
      *               ^  位移运算符
      *               ^  比较运算符
      *               ^  位运算符
      *               ^  关系运算符
      *               ^  赋值运算符
      */
    def operatorSymbol(): Unit = {
        //1.算术运算符：=、-、*、/、%（取余）、+
        var x1:Int=2
        var y1:Int=3
        println (s"x=$x1  y=$y1 x+y ="+ (x1+y1))
        println (s"x=$x1  y=$y1 x-y ="+ (x1-y1))
        println (s"x=$x1  y=$y1 x * y ="+ (x1*y1))
        println (s"x=$x1  y=$y1 x / y ="+ (x1/y1))
        println (s"x=$x1  y=$y1 x % y ="+ (x1%y1))
        //2.赋值运算符：=、+=、-=、*=、/=、%=
        x1 = 2; x1 += 5
        println (s"x =" + x1)
        x1 = 2; x1 -= 5
        println (s"x =" + x1)
        x1 = 2; x1 *= 5
        println (s"x =" + x1)
        x1 = 2;x1 /= 5
        println (s"x =" + x1)
        x1 = 2;x1 %= 5
        println (s"x =" + x1)
        //3.比较运算符：==、!=、<、>、<=、>=
        //4.逻辑运算符：&& 、|| 、!
        //5.位运算符:<<=、>>=、&=、^=、|=
        //不支持三目运算符只有if else
        val index=if(x1>y1) 20 else 30
        println(index)
    }
}
/**
  * @author WangGuoQing
  * @date 2019/4/30 17:11
  * @Desc
  */
class Dog {

}
