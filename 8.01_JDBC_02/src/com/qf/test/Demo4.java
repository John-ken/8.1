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
 * �о���Ԫ����
 * @ClassName: Demo4 
 * @Description: TODO
 * @author С����
 * @date 2017-8-1 ����2:29:01 
 *
 */
public class Demo4 {
	private String url="jdbc:mysql:///Day1704_JDBC";
	private String user="root";
	private String password="root";
	/**
	 * �о������ݿ��Ԫ����
	 * @Title: testDBMetaData 
	 * @Description: TODO
	 * @param @throws Exception    
	 * @return void     
	 * @throws
	 */
	@Test
	public void testDBMetaData() throws Exception {
		//��ȡ�Ǹ�����
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ�������
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		//����Ϳ��Ի�ȡ���ݿ��Ԫ������
		DatabaseMetaData metaData=conn.getMetaData();
		//ͨ��Ԫ��������Ϳ��Ի�ȡզ�ǵ����ݿ�������Ϣ��
		System.out.println(metaData.getDriverName());
		System.out.println(metaData.getDriverVersion());
		System.out.println(metaData.getURL());
		System.out.println(metaData.getUserName());
		System.out.println(metaData.getConnection());
	}
	
	/**
	 * �о������������Ԫ����
	 * @Title: testParamesMetaData 
	 * @Description: TODO
	 * @param @throws Exception    
	 * @return void     
	 * @throws
	 */
	@Test
	public void testParamesMetaData() throws Exception {
		//��ȡ�Ǹ�����
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ�������
		Connection conn = DriverManager.getConnection(url, user, password);
		//�����Ӧ�û�ȡ���ǵ�statement��
		PreparedStatement state=conn.prepareStatement("select * from t_user where uName=? and uId=? and kkk=?");
		
		//����Ϳ��Ի�ȡզ�ǵ����������Ԫ������....
	     ParameterMetaData metaData=state.getParameterMetaData();
	     
		//����Ϳ��Ի�ȡ�Ǹ�����Ĳ����ĸ�����....
	     System.out.println("���������һ����Ҫ"+metaData.getParameterCount()+"������");		
	}
	/**
	 * ��ȡ�������Ԫ����
	 * @Title: testResultSetMetaData 
	 * @Description: TODO
	 * @param @throws Exception    
	 * @return void     
	 * @throws
	 */
	@Test
	public void testResultSetMetaData() throws Exception {
		//��ȡ�Ǹ�����
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ�������
		Connection conn = DriverManager.getConnection(url, user, password);
		//�����Ӧ�û�ȡ���ǵ�statement��
		PreparedStatement state=conn.prepareStatement("select * from t_user");
		//��ȡզ�ǵķ��ؽ��
		ResultSet set=state.executeQuery();
		//�����ؾ���Ҫ��ȡզ�ǵ���������Ԫ������
		ResultSetMetaData metaData=set.getMetaData();
		
		System.out.println("�е�������:"+metaData.getColumnCount());
		
		System.out.println("��ȡһ�µ�һ�е�����:"+metaData.getColumnName(1));
		//���α������ƶ�һ��λ��
		set.next();
		System.out.println("��ȡ��һ�еĵ�һ����ֵ:"+set.getInt(metaData.getColumnName(1)));
	}
}
