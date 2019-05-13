package com.gsau.portal.repository;


import com.gsau.portal.pojo.po.MoniYear;

import java.util.List;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:20
 * @Desc 
 */
public interface MoniYearRepository  extends CrudRepository<MoniYear, Integer> {

    @Query(value = "select * from tb_moniyear " ,nativeQuery=true)
    List<MoniYear> queryList();

    @Query(value = "select * from tb_moniyear where moniid = ?1" ,nativeQuery=true)
    Subject findMoniYear(String moniid);


}
