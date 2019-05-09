package com.gsau.sparkproject.dao.factory;

import com.gsau.sparkproject.dao.impl.AdClickTrendDAOImpl;
import com.gsau.sparkproject.dao.IAdBlacklistDAO;
import com.gsau.sparkproject.dao.IAdClickTrendDAO;
import com.gsau.sparkproject.dao.IAdProvinceTop3DAO;
import com.gsau.sparkproject.dao.IAdStatDAO;
import com.gsau.sparkproject.dao.IAdUserClickCountDAO;
import com.gsau.sparkproject.dao.IAreaTop3ProductDAO;
import com.gsau.sparkproject.dao.IPageSplitConvertRateDAO;
import com.gsau.sparkproject.dao.ISessionAggrStatDAO;
import com.gsau.sparkproject.dao.ISessionDetailDAO;
import com.gsau.sparkproject.dao.ISessionRandomExtractDAO;
import com.gsau.sparkproject.dao.ITaskDAO;
import com.gsau.sparkproject.dao.ITop10CategoryDAO;
import com.gsau.sparkproject.dao.ITop10SessionDAO;
import com.gsau.sparkproject.dao.impl.AdBlacklistDAOImpl;
import com.gsau.sparkproject.dao.impl.AdProvinceTop3DAOImpl;
import com.gsau.sparkproject.dao.impl.AdStatDAOImpl;
import com.gsau.sparkproject.dao.impl.AdUserClickCountDAOImpl;
import com.gsau.sparkproject.dao.impl.AreaTop3ProductDAOImpl;
import com.gsau.sparkproject.dao.impl.PageSplitConvertRateDAOImpl;
import com.gsau.sparkproject.dao.impl.SessionAggrStatDAOImpl;
import com.gsau.sparkproject.dao.impl.SessionDetailDAOImpl;
import com.gsau.sparkproject.dao.impl.SessionRandomExtractDAOImpl;
import com.gsau.sparkproject.dao.impl.TaskDAOImpl;
import com.gsau.sparkproject.dao.impl.Top10CategoryDAOImpl;
import com.gsau.sparkproject.dao.impl.Top10SessionDAOImpl;

/**
 * @Description: DAO工厂类
 * @author: wangguoqing
 * @date: 2018/10/29 10:05
 */
public class DAOFactory {

    /**
     * @Description: 获取执行任务
     * @author: wangguoqing
     * @date: 2018/10/29 10:06
     */
    public static ITaskDAO getTaskDAO() {
        return new TaskDAOImpl();
    }

    /**
     * @Description: Session聚合dao的实现获取
     * @author: wangguoqing
     * @date: 2018/10/29 10:06
     */
    public static ISessionAggrStatDAO getSessionAggrStatDAO() {
        return new SessionAggrStatDAOImpl();
    }

    /**
     * @Description: 随机抽取session的DAO实现
     * @author: wangguoqing
     * @date: 2018/10/29 10:07
     */
    public static ISessionRandomExtractDAO getSessionRandomExtractDAO() {
        return new SessionRandomExtractDAOImpl();
    }

    /**
     * @Description: session明细DAO实现类
     * @author: wangguoqing
     * @date: 2018/10/29 10:07
     */
    public static ISessionDetailDAO getSessionDetailDAO() {
        return new SessionDetailDAOImpl();
    }

    /**
     * @Description: top10品类DAO实现
     * @author: wangguoqing
     * @date: 2018/10/29 10:07
     */
    public static ITop10CategoryDAO getTop10CategoryDAO() {
        return new Top10CategoryDAOImpl();
    }

    /**
     * @Description: top10活跃session的DAO实现
     * @author: wangguoqing
     * @date: 2018/10/29 10:07
     */
    public static ITop10SessionDAO getTop10SessionDAO() {
        return new Top10SessionDAOImpl();
    }

    /**
     * @Description: 页面切片转化率DAO实现类
     * @author: wangguoqing
     * @date: 2018/10/29 10:08
     */
    public static IPageSplitConvertRateDAO getPageSplitConvertRateDAO() {
        return new PageSplitConvertRateDAOImpl();
    }

    /**
     * @Description: 各区域top3热门商品DAO实现类
     * @author: wangguoqing
     * @date: 2018/10/29 10:08
     */
    public static IAreaTop3ProductDAO getAreaTop3ProductDAO() {
        return new AreaTop3ProductDAOImpl();
    }

    /**
     * @Description: 用户广告点击量DAO实现类
     * @author: wangguoqing
     * @date: 2018/10/29 10:08
     */
    public static IAdUserClickCountDAO getAdUserClickCountDAO() {
        return new AdUserClickCountDAOImpl();
    }

    /**
     * @Description: 广告黑名单DAO实现类
     * @author: wangguoqing
     * @date: 2018/10/29 10:08
     */
    public static IAdBlacklistDAO getAdBlacklistDAO() {
        return new AdBlacklistDAOImpl();
    }

    /**
     * @Description: 广告实时统计DAO实现类
     * @author: wangguoqing
     * @date: 2018/10/29 10:09
     */
    public static IAdStatDAO getAdStatDAO() {
        return new AdStatDAOImpl();
    }

    /**
     * @Description: 各省份top3热门广告DAO实现类
     * @author: wangguoqing
     * @date: 2018/10/29 10:09
     */
    public static IAdProvinceTop3DAO getAdProvinceTop3DAO() {
        return new AdProvinceTop3DAOImpl();
    }

    /**
     * @Description: 广告点击趋势DAO实现类
     * @author: wangguoqing
     * @date: 2018/10/29 10:09
     */
    public static IAdClickTrendDAO getAdClickTrendDAO() {
        return new AdClickTrendDAOImpl();
    }

}
