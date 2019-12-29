package com.gsau.proxy.impl;
import com.gsau.proxy.IProducer;
import static java.lang.System.out;
/**
  * @ Author: WangGQ
  * @ Date: 2019/12/29 14:44
  * @ Version: 1.0
  * @ Description: 代理接口实现类 
  */
public class ProducerImpl implements IProducer {
    @Override
    public void method1(String name) {
        out.println("ProducerImpl.java--method1--16-"+name+"->"+ "执行了！！！");
    }
    @Override
    public void method2(int id) {
        out.println("ProducerImpl.java--method2--21-->"+ "执行了");
    }
    public void method3(){
        out.println("ProducerImpl.java--method3--24-->"+ "不是从接口继承的方法");
    }
}
