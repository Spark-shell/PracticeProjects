package com.gsau.portal.controller.app;


import com.gsau.portal.pojo.MessageObject;
import com.gsau.portal.pojo.po.MoniYear;
import com.gsau.portal.repository.MoniYearRepository;
import com.gsau.portal.util.GsonUtil;
import com.gsau.portal.util.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:15
 * @Desc 
 */
@RestController
@RequestMapping("/app/moniyear")
public class MoniYearApp {
    @Autowired
    MoniYearRepository moniYearRepository;

    /**
     * 查看某一个资源
     * @return
     */
    @RequestMapping(value="/viewlist")
    @ResponseBody
    public MessageObject viewlist(){
        MessageObject mo = new MessageObject(SystemConfig.mess_succ,"登录成功");
        System.out.println("--查看历年的模拟题列表--");
        List<MoniYear> list =(List<MoniYear>)moniYearRepository.findAll();
        System.out.println("--查看历年的模拟题列表--"+list.size());
        if(list!=null){
            String content = GsonUtil.objTOjson(list);
            System.out.println("****content111***"+content);
            mo.setMcontent(content);
        }

        return mo;
    }


}
