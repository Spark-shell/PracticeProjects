package com.us.example.controller;
import com.us.example.bean.Person;
import com.us.example.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangGuoQing
 * @date 2019/4/9 22:51
 * @Desc restFul 访问接口
 */
@RestController
public class CacheController {
    //注入service
    @Autowired
   private DemoService demoService;

    //http://localhost:8080/put?name=abel&age=23&address=shanghai

    /**
     * 插入操作
     * @param person
     * @return
     */
    @RequestMapping("/put")
    public Person put(Person person){
        return demoService.save(person);

    }
    //http://localhost:8080/able?id=1    //id=1就表示id=1的实体
    /**
     * 查询
     * @param person
     * @return
     */
    @RequestMapping("/able")
    @ResponseBody
    public Person cacheable(Person person){
        System.out.println(person);
        return demoService.findOne(person);
    }

    //http://localhost:8080/evit?id=1
    @RequestMapping("/evit")
    public String  evit(Long id){
        demoService.remove(id);
        return "ok";

    }


}