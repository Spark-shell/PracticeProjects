package com.gsau.project01.controller.supperMall;

import com.gsau.project01.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * @ Author: WangGQ
 * @ Date: 2019/12/17 20:30
 * @ Version: 1.0
 * @ Description:  获取首页数据
 */
@Controller
@RequestMapping("/home")
public class Home {

    public static final String path=" http://192.168.1.100:8082/imgs/";
    @ResponseBody
    @RequestMapping("getHomeGoods")
    public Map getHomeGoods(@RequestParam String type,@RequestParam Integer page) {
        out.print(type);
        out.print(page);
        HashMap resutlts=new HashMap();
        HashMap goods=new HashMap();
        HashMap show=new HashMap();
        ArrayList gList=new ArrayList<Map>();
        HashMap good=new HashMap();
        good.put("title","潮流上衣");
        good.put("price",2000);
        good.put("cfav",1);
        show.put("img",path+"1-1.jpg");
        good.put("show",show);
        gList.add(good);

        good=new HashMap();
        show=new HashMap();
        good.put("title","潮流上衣");
        good.put("price",2000);
        good.put("cfav",10);
        show.put("img",path+"1-2.jpg");
        good.put("show",show);
        gList.add(good);

        good=new HashMap();
        show=new HashMap();
        good.put("title","潮流上衣");
        good.put("price",2000);
        good.put("cfav",10000);
        show.put("img",path+"1-3.jpg");
        good.put("show",show);
        gList.add(good);

        good=new HashMap();
        show=new HashMap();
        good.put("title","潮流上衣");
        good.put("price",2000);
        good.put("cfav",0);
        show.put("img",path+"1-4.jpg");
        good.put("show",show);
        gList.add(good);

        goods.put("page",1);
        goods.put("list",gList);
        resutlts.put("success",200);
        resutlts.put("data",goods);
        return resutlts;
    }
}

