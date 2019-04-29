package com.gsau.consumer_dept_8083.ConsumerController;


import com.gsau.api_8081.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author WangGuoQing
 * @date 2019/4/28 13:51
 * @Desc 消费者
 */
@RestController
public class ConsumerController {
    //部分生产者地址，非微服务等的方式访问
    // private static final String REST_URL_PREFIX="http://localhost:8082/rest";
    //微服务的方式访问
    private static final String REST_URL_PREFIX="http://PROVIDER-DEPT-SEVICE";
    @Autowired
    RestTemplate restTemplate;
    @GetMapping(value = "/consumer/add")
    public boolean add( Department department){
        //三个参数：url,requestMap ResponseBean.class
        /**
         * 发送POST请求
         */
        return  restTemplate.postForObject(
                REST_URL_PREFIX+"/rest",                        //URL
                department,                                         //数据集
                Boolean.class);                                     //返回值类型
    }
}
