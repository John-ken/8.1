package com.qf.test;

import org.junit.Test;

import com.qf.dao.UserDao;

/**
 * ��������������Ǹ���ɾ��
 * @ClassName: Demo5 
 * @Description: TODO
 * @author С����
 * @date 2017-8-1 ����3:27:13 
 *
 */
public class Demo5 {

	@Test
	public void testInsert() throws Exception {
		String sql="insert into t_user(uName) values(?)";
		String uName="����";
		UserDao userDao=new UserDao();
		userDao.update(sql, uName);
	}
	
	@Test
	public void testDelete() throws Exception {
		String sql="delete from t_user where uName=?";
		String uName="����";
		UserDao userDao=new UserDao();
		userDao.update(sql, uName);
	}
	
	@Test
	public void testUpdate() throws Exception {
		String sql="update t_user set uName=? where uId=1";
		UserDao userDao=new UserDao();
		userDao.update(sql, "С����");
	}
}
