package com.qf.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

/**
 * 研究下咋们的事务
 * @ClassName: Demo2 
 * @Description: TODO
 * @author 小波波
 * @date 2017-8-1 上午9:17:21 
 *
 */
public class Demo2 {
	private String url="jdbc:mysql:///test";
	private String user="root";
	private String password="886887";
	
	@Test 
	public void testGenerateKeys() {
		Connection conn=null;
		//下面这两个表示的是第一的转账
		PreparedStatement state=null;
		PreparedStatement state1=null;
		
		//表示的是第二次的转账
		PreparedStatement state21=null;
		PreparedStatement state22=null;
		
		
		
		try {
			//获取那个连接
			Class.forName("com.mysql.jdbc.Driver");
			//获取这个连接
			conn = DriverManager.getConnection(url, user, password);
			String scSql="update t_user set money=money-1000 where uName=?";
			String gxSql="update t_user set money=money+1000 where uName=?";
			
			//使用事务的第一步:设置手动提交
			conn.setAutoCommit(false);   //关闭咋们的自动提交
			//开始事务
			state=conn.prepareStatement(scSql);
			state.setString(1,"王思聪");
			state.executeUpdate();
			
			//这里假设就会出现问题了
			
			//下面就应该转账了
			state1=conn.prepareStatement(gxSql);
			state1.setString(1,"林更新");
			state1.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			System.out.println("驱动程序没找到");
		} catch (Exception e) {
			//这里就出现了Sql异常
			//如果出现了异常的话那么就需要回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			//都还没有问题的话  应该提交
			try {
				conn.commit();
			} catch (SQLException e1) {
				System.out.println("提交出现了问题");
			}
			//这里面关闭资源
			try {
				if(null!=state){
					state.close();
				}
			} catch (SQLException e) {
				System.out.println("关闭state的时候出现异常");
			}
			try {
				if(null!=state1){
					state1.close();
				}
			} catch (SQLException e) {
				System.out.println("关闭sate1的时候出现异常");
			}
			try {
				if(null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("关闭连接出现了异常");
			}
		}	
	}
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 通过回滚点来设置回滚的地方
	 * @Title: testGenerateKeys2 
	 * @Description: TODO
	 * @param     
	 * @return void     
	 * @throws
	 */
	@Test 
	public void testGenerateKeys2() throws ClassNotFoundException, SQLException {
		Connection conn=null;
		//下面这两个表示的是第一的转账
		PreparedStatement state=null;
		PreparedStatement state1=null;
		
		//表示的是第二次的转账
		PreparedStatement state21=null;
		PreparedStatement state22=null;
		//设置回滚点
		Savepoint savepoint=null;
		//获取那个连接
		Class.forName("com.mysql.jdbc.Driver");
		//获取这个连接
		conn = DriverManager.getConnection(url, user, password);
		
		
		try {
			String scSql="update t_user set money=money-1000 where uName=?";
			String gxSql="update t_user set money=money+1000 where uName=?";
			
			
			//第一次的转账
			
			
			//使用事务的第一步:设置手动提交
			conn.setAutoCommit(false);   //关闭咋们的自动提交
			//开始事务
			state=conn.prepareStatement(scSql);
			state.setString(1,"王思聪");
			state.executeUpdate();
			//下面就应该转账了
			state1=conn.prepareStatement(gxSql);
			state1.setString(1,"林更新");
			state1.executeUpdate();
			
			
			//这里需要设置的是回滚点
			
			savepoint=conn.setSavepoint();
			
			//下面进行的是第二次的转账
			
			//开始事务
			state21=conn.prepareStatement(scSql);
			state21.setString(1,"王思聪");
			state21.executeUpdate();
			
			//这里出现了问题
			int k=1/0;
			
			//下面就应该转账了
			state22=conn.prepareStatement(gxSql);
			state22.setString(1,"林更新");
			state22.executeUpdate();
				
		} catch (Exception e) {
			//这里就出现了Sql异常
			//如果出现了异常的话那么就需要回滚
			conn.rollback(savepoint);
		}finally{
			//都还没有问题的话  应该提交
			try {
				conn.commit();
			} catch (SQLException e1) {
				System.out.println("提交出现了问题");
			}
			//这里面关闭资源
			try {
				if(null!=state){
					state.close();
				}
			} catch (SQLException e) {
				System.out.println("关闭state的时候出现异常");
			}
			try {
				if(null!=state1){
					state1.close();
				}
			} catch (SQLException e) {
				System.out.println("关闭sate1的时候出现异常");
			}
			
			try {
				if(null!=state21){
					state21.close();
				}
			} catch (SQLException e) {
				System.out.println("关闭state的时候出现异常");
			}
			try {
				if(null!=state22){
					state22.close();
				}
			} catch (SQLException e) {
				System.out.println("关闭sate1的时候出现异常");
			}
			try {
				if(null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("关闭连接出现了异常");
			}
		}	
	}
}
