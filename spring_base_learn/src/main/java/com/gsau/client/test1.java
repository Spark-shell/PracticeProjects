package com.gsau.client;

import com.gsau.entity.Dept;
import com.gsau.entity.Emp;
import com.gsau.entity.Person;
import com.gsau.factory.BeanFactory;
import com.gsau.factory.SpringBeanFactory;
import com.gsau.service.impl.AccountServiceImpl;
import com.gsau.service.impl.CollectionInjectionImpl;
import com.gsau.service.impl.ConstructInjectionImpl;
import com.gsau.service.impl.SetMethodInjectionImpl;

public class test1 {
    public static void main(String[] args) {
//        AccountServiceImpl service= (AccountServiceImpl) BeanFactory.getBean("accountServiceImpl");
//         AccountServiceImpl service= SpringBeanFactory.getBean("accountServiceImpl",AccountServiceImpl.class);
//         Person person=SpringBeanFactory.getBean("person", Person.class);
//         Emp emp=SpringBeanFactory.getBean("emp", Emp.class);
//         Dept dept=SpringBeanFactory.getBean("dept", Dept.class);
//         System.out.println("test1-test1-13->"+person.toString());
//         System.out.println("test1-test1-17->"+emp.id);
//         System.out.println("test1-test1-20->"+dept.name);
//         service.saveAccount();
        ConstructInjectionImpl constructInjectionImpl=SpringBeanFactory.getBean("constructInjectionImpl", ConstructInjectionImpl.class);
        SetMethodInjectionImpl setMethodInjectionImpl=SpringBeanFactory.getBean("setMethodInjectionImpl", SetMethodInjectionImpl.class);
        CollectionInjectionImpl collectionInjectionImpl=SpringBeanFactory.getBean("collectionInjectionImpl", CollectionInjectionImpl.class);
        constructInjectionImpl.println();
        setMethodInjectionImpl.println();
        collectionInjectionImpl.println();
    }
}
