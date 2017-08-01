package com.qf.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC的一个帮助类
 * @ClassName: JdbcUtils 
 * @Description: TODO
 * @author 小波波
 * @date 2017-7-31 下午4:18:20 
 *
 */
public class JdbcUtils {
	private static String url="jdbc:mysql:///test";
	private static String user="root";
	private static String password="886887";
	
	private static Statement statement=null;
    private static Connection conn= null;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//下面就应该 获取我们的连接
		    conn= DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("加载我们的驱动程序失败....");
			throw new RuntimeException("加载驱动程序失败");
		} catch (SQLException e) {
			System.out.println("Sql出现了异常....");
		}
		
	}
	

	
	/**
	 * 提供一个方法返回咋们的statement对象
	 * @Title: getStatement 
	 * @Description: TODO
	 * @param @return    
	 * @return Statement     
	 * @throws
	 */
	public static Connection getConnection(){
		return conn;
	}
	
	/**
	 * 获取咋们的Statement
	 * @Title: getStatement 
	 * @Description: TODO
	 * @param @return
	 * @param @throws SQLException    
	 * @return Statement     
	 * @throws
	 */
	public static Statement getStatement() throws SQLException{
		return conn.createStatement();
	}
	/**
	 * @throws SQLException 
	 * 关闭咋们的资源
	 * @Title: close 
	 * @Description: TODO
	 * @param @param state
	 * @param @param conn    
	 * @return void     
	 * @throws
	 */
	public static  void  close(Statement state,Connection conn) throws SQLException{
		if(null!=state){
			state.close();
		}
		if(null!=conn){
			conn.close();
		}
	}
}
