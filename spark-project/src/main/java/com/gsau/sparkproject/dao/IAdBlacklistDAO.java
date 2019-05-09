package com.gsau.sparkproject.dao;

import java.util.List;

import com.gsau.sparkproject.domain.AdBlacklist;

/**
   * @Description: 广告黑名单DAO接口
   * @author: wangguoqing
   * @date: 2018/10/29 10:14
  */
public interface IAdBlacklistDAO {

	/**
	 * 批量插入广告黑名单用户
	 * @param adBlacklists
	 */
	void insertBatch(List<AdBlacklist> adBlacklists);
	
	/**
	 * 查询所有广告黑名单用户
	 * @return
	 */
	List<AdBlacklist> findAll();
	
}
