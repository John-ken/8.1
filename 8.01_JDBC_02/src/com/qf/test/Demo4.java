package com.qf.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;
/**
 * 研究下元数据
 * @ClassName: Demo4 
 * @Description: TODO
 * @author 小波波
 * @date 2017-8-1 下午2:29:01 
 *
 */
public class Demo4 {
	private String url="jdbc:mysql:///Day1704_JDBC";
	private String user="root";
	private String password="root";
	/**
	 * 研究下数据库的元数据
	 * @Title: testDBMetaData 
	 * @Description: TODO
	 * @param @throws Exception    
	 * @return void     
	 * @throws
	 */
	@Test
	public void testDBMetaData() throws Exception {
		//获取那个连接
		Class.forName("com.mysql.jdbc.Driver");
		//获取这个连接
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		//下面就可以获取数据库的元数据了
		DatabaseMetaData metaData=conn.getMetaData();
		//通过元数据下面就可以获取咋们的数据库的相关信息了
		System.out.println(metaData.getDriverName());
		System.out.println(metaData.getDriverVersion());
		System.out.println(metaData.getURL());
		System.out.println(metaData.getUserName());
		System.out.println(metaData.getConnection());
	}
	
	/**
	 * 研究下请求参数的元数据
	 * @Title: testParamesMetaData 
	 * @Description: TODO
	 * @param @throws Exception    
	 * @return void     
	 * @throws
	 */
	@Test
	public void testParamesMetaData() throws Exception {
		//获取那个连接
		Class.forName("com.mysql.jdbc.Driver");
		//获取这个连接
		Connection conn = DriverManager.getConnection(url, user, password);
		//下面就应该获取我们的statement了
		PreparedStatement state=conn.prepareStatement("select * from t_user where uName=? and uId=? and kkk=?");
		
		//下面就可以获取咋们的请求参数的元数据了....
	     ParameterMetaData metaData=state.getParameterMetaData();
	     
		//下面就可以获取那个请求的参数的个数了....
	     System.out.println("你的请求中一共需要"+metaData.getParameterCount()+"个参数");		
	}
	/**
	 * 获取结果集的元数据
	 * @Title: testResultSetMetaData 
	 * @Description: TODO
	 * @param @throws Exception    
	 * @return void     
	 * @throws
	 */
	@Test
	public void testResultSetMetaData() throws Exception {
		//获取那个连接
		Class.forName("com.mysql.jdbc.Driver");
		//获取这个连接
		Connection conn = DriverManager.getConnection(url, user, password);
		//下面就应该获取我们的statement了
		PreparedStatement state=conn.prepareStatement("select * from t_user");
		//获取咋们的返回结果
		ResultSet set=state.executeQuery();
		//下面呢就需要获取咋们的这个结果集元数据了
		ResultSetMetaData metaData=set.getMetaData();
		
		System.out.println("列的数量是:"+metaData.getColumnCount());
		
		System.out.println("获取一下第一列的名字:"+metaData.getColumnName(1));
		//把游标往下移动一个位置
		set.next();
		System.out.println("获取第一列的第一个的值:"+set.getInt(metaData.getColumnName(1)));
	}
}
