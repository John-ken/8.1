package com.qf.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC��һ��������
 * @ClassName: JdbcUtils 
 * @Description: TODO
 * @author С����
 * @date 2017-7-31 ����4:18:20 
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
			//�����Ӧ�� ��ȡ���ǵ�����
		    conn= DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("�������ǵ���������ʧ��....");
			throw new RuntimeException("������������ʧ��");
		} catch (SQLException e) {
			System.out.println("Sql�������쳣....");
		}
		
	}
	

	
	/**
	 * �ṩһ����������զ�ǵ�statement����
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
	 * ��ȡզ�ǵ�Statement
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
	 * �ر�զ�ǵ���Դ
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
