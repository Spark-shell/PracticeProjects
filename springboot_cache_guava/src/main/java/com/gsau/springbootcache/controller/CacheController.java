package com.gsau.springbootcache.controller;
import com.gsau.springbootcache.bean.Person;
import com.gsau.springbootcache.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangGuoQing
 * @date 2019/4/9 14:49
 * @Desc 在spring4.0引入了简化的RESTful Web服务的创建,是一个结合@Controller和@ResponseBody的便利注释
 * 它消除了使用@ResponseBody注释注释控制器类的每个请求处理方法的需要
 */
@RestController
@RequestMapping(value = "/person")
public class CacheController {
    @Autowired
   private PersonService personService;
    @RequestMapping(method = RequestMethod.POST)
//    @ResponseBody
    public Person put(Person person){
        return personService.save(person);

    }
    //http://localhost:8080/person/1
    @RequestMapping(value ="/{id}" ,method = RequestMethod.GET)
//    @ResponseBody
    public Person cacheable( @PathVariable Long id){
        return personService.findOne(id);

    }
}