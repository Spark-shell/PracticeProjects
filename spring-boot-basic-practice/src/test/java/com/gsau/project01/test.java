package com.gsau.project01;
import com.gsau.project01.Annotation.Hello;
import java.lang.reflect.Method;
public class test {
    @Hello("HelloWorld！！！")
    public static void main(String[] args) throws NoSuchMethodException {
        Class cls = test.class;
        Method method = cls.getMethod("main", String[].class);
        Hello hello = method.getAnnotation(Hello.class);
        System.out.println(hello.value());   //打印结果----->HelloWorld！！！
    }
}
