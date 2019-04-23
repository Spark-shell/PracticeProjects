package com.gsau.cache.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WangGuoQing
 * @date 2019/4/23 14:49
 * @Desc 定时任务
 */
@Service
public class ScheduledTaskService {
    /**
     * @Scheduled：   说明hello方式是一个定时方法
     *  cron = "* * * * * *" 定时表达式，总共六位代表的含义是
     *  cron = "秒 分 时 日 月 周几"
     */
    @Scheduled(cron = "0 0 * * * MON-SAT")
    public void hello(){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("定时任务："+df.format(day));
    }
}
