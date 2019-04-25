package com.gsau.cache.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author WangGuoQing
 * @date 2019/4/23 14:49
 * @Desc 异步任务处理
 */
@Service
public class AsynTaskService {
    /**
     *    @Async：   说明hello方式是一个异步方法
     */
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据处理中....");
    }
}
