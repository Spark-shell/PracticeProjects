package com.gsau.zull_session.zuul_filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author WangGuoQing
 * @date 2019/5/7 16:09
 * @Desc 定制zuul过滤器
 * 错误过滤器：
 */
public class ErrorFilter extends ZuulFilter {
    /**
     * 使用返回值设定Filter类型，可以设置pre、route、post、error
     * @return error
     */
    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    /**
     *  使用返回值确定当前类型的过滤器执行顺序，同一类型的过滤器，数值越小越先执行，如果每种类型都有一个过滤器的话，返回值写0就好了。
     * @return
     */
    @Override
    public int filterOrder() {
        return -1;
    }

    /**
     * 使用返回值确定该Filter是否执行/生效，可以当作这个过滤器的开关，true：开，false：关。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 这里就写你这个过滤器的业务逻辑，想让该过滤器做什么都可以在这里写
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        System.out.println("这是ErrorFilter");
        return null;
    }
}