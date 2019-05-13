package com.gsau.portal.service;

import javax.management.Query;
import java.math.BigInteger;
import java.util.List;


/**
 * @author WangGuoQing
 * @date 2019/5/13 17:42
 * @Desc 这个类的作用是：对EntityManagerFactory em 的封装
 *  主要用于多表查询
 */

public class EntityManagerService {

	private EntityManagerFactory factory;//EntityManagerFactory 是一个重量级的对象，如果程序不使用它应该及时关闭以便释放资源
	private EntityManager em;
	
	public static EntityManagerService instance ;
	public synchronized  static EntityManagerService getInstance(){
		if(null == instance){
			return new EntityManagerService();
		}
		return instance;
	}
	
	
	
	private void create_em(){
		factory = (EntityManagerFactory) ApplicationUtil.getBean(EntityManagerFactory.class);
		 if(null != factory){
			 em = factory.createEntityManager();
		 }else{
			  System.out.println("---create emememem failed----");
			 
		 }
	}
	
	
	private void close_em(){
		if(em != null ){
			em.close();
		}
	}

	/**
	 * 单表查询：sql="SELECT c FROM CustomerEO c"
	 * 联表查询 :SQL=select new com.ljq.entity.Abc(a.imsi, a.sipss, b.mdn) from A a, B b where a.imsi=b.imsi"
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<?> queryList(String sql , Class cla){
		List result = null;
		try {
			create_em();
			Query query = em.createNativeQuery(sql , cla);
	        result = query.getResultList();   
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			close_em();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public BigInteger queryCount(String sql){
		List result = null;
		try {
			create_em();
			Query query = em.createNativeQuery(sql );   
			result = query.getResultList(); 
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			close_em();
		}
		
		return (BigInteger)result.get(0);
	}


//	public void delete (String sql){
//		try {
//			create_em();
//			em.createQuery(sql).executeUpdate();   
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}finally{
//			close_em();
//		}
//	}
	

	
	
	

}
