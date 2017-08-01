package com.qf.test;

import org.junit.Test;

import com.qf.dao.UserDao;

/**
 * 这里面我们完成那个增删改
 * @ClassName: Demo5 
 * @Description: TODO
 * @author 小波波
 * @date 2017-8-1 下午3:27:13 
 *
 */
public class Demo5 {

	@Test
	public void testInsert() throws Exception {
		String sql="insert into t_user(uName) values(?)";
		String uName="晓波";
		UserDao userDao=new UserDao();
		userDao.update(sql, uName);
	}
	
	@Test
	public void testDelete() throws Exception {
		String sql="delete from t_user where uName=?";
		String uName="晓波";
		UserDao userDao=new UserDao();
		userDao.update(sql, uName);
	}
	
	@Test
	public void testUpdate() throws Exception {
		String sql="update t_user set uName=? where uId=1";
		UserDao userDao=new UserDao();
		userDao.update(sql, "小王子");
	}
}
