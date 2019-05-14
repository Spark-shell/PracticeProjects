package com.gsau.zull_session.zuul_filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author WangGuoQing
 * @date 2019/5/7 16:24
 * @Desc Session管理过滤器
 */
public class SessionFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
      /*  System.out.println("Session Filter Starter");
        RequestContext ctx = RequestContext.getCurrentContext();                //从RequestContext获取上下文
        HttpServletRequest request = ctx.getRequest();                          //从上下文获取HttpServletRequest
        HttpSession session = request.getSession();
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if (userName.equals("wgq") && password.equals("123")) {
            ctx.setSendZuulResponse(true);                                      // 对该请求进行路由
            ctx.addZuulRequestHeader("Cookie", "SESSION=" + session.getId());
            System.out.println("用户名密码验证成功，开始写入session");
            ctx.set("logic-is-success", true);
        }else{
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("{'status':500,'message':'用户不存在！'}");         //设定responseBody供PostFilter使用
            //logic-is-success保存于上下文，作为同类型下游Filter的执行开关
            ctx.set("logic-is-success", false);
        }
        System.out.println(session.getId());
        System.out.println(session.getAttributeNames());*/
        return null;
    }
}
