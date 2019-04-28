package com.gsau.eureka_server.controller;

import com.gsau.api_8081.entities.Department;
import com.gsau.eureka_server.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/4/28 12:15
 * @Desc 
 */
@RestController
public class TestRestController {
    @Autowired
    DeptService service;

    @GetMapping(value = "/rest/{id}")
    public Department findById(@PathVariable("id") int id) {
       return service.findById(id);
    }

    /**
     *   @requestBody注解
     *       作用在形参列表上，用于将前台发送过来固定格式的数据【xml 格式或者 json等】封装为对应的 JavaBean 对象，
     *       封装时使用到的一个对象是系统默认配置的 HttpMessageConverter进行解析，然后封装到形参上
     *  @ResponseBody
     *     @ResponseBody是作用在方法上的，@ResponseBody 表示该方法的返回结果直接写入 HTTP response body 中，
     *     一般在异步获取数据时使用【也就是AJAX】，在使用 @RequestMapping后，返回值通常解析为跳转路径，
     *     但是加上 @ResponseBody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。
     *     比如异步获取 json 数据，加上 @ResponseBody 后，会直接返回 json 数据。@RequestBody 将 HTTP 请求正文插入方法中，
     *     使用适合的 HttpMessageConverter 将请求体写入某个对象。
     * @param department
     * @return
     */
    @PostMapping(value ="/rest" )
    public boolean findById(@RequestBody  Department department) {
        return service.addDept(department);
    }
    /**
     * 全部查询
     * @return
     */
    @GetMapping("/rest/list")
    public List<Department> list() {
       return service.findAll();
    }
}
