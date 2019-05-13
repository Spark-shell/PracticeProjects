package com.gsau.portal.controller.web;

import com.gsau.portal.pojo.po.MoniYear;
import com.gsau.portal.repository.MoniYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * @author WangGuoQing
 * @date 2019/5/13 21:15
 * @Desc 
 */
@Controller
@RequestMapping("/web/moniyear")
public class MoniYearController {

    @Autowired
    MoniYearRepository moniYearRepository;

    /**
     * 查看某一个资源
     * @return
     */
    @RequestMapping(value="/viewlist")
    @ResponseBody
    public List<MoniYear> viewlist(){

        System.out.println("--查看历年的模拟题列表--");
        List<MoniYear> list =(List<MoniYear>)moniYearRepository.findAll();


        return list;
    }



}
