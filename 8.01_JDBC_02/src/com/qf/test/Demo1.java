package com.qf.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.mysql.jdbc.Driver;

public class Demo1 {
	private String url="jdbc:mysql:///Day1704_JDBC";
	private String user="root";
	private String password="886887";
	
	/**
	 * ����������������
	 * @Title: testGenerateKeys 
	 * @Description: TODO
	 * @param @throws Exception    
	 * @return void     
	 * @throws
	 */
	@Test 
	public void testGenerateKeys() throws Exception {
		//��ȡ�Ǹ�����
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ�������
		Connection conn = DriverManager.getConnection(url, user, password);
		
		String sqlDept="insert into dept(deptName) values(?)";
		String sqlEmp="insert into emp(empName,deptId) values(?,?)";
		
		//����Ӧ�ò������ զ�ǵĲ�����Ϣ
		PreparedStatement state=conn.prepareStatement(sqlDept,PreparedStatement.RETURN_GENERATED_KEYS);
		//�����ǲ��Ǿ�Ӧ�ø������ռλ����ֵ
		state.setString(1,"��Ů��");
		
		//�ǲ��Ǿ�Ӧ�ý��в�ѯ��
		state.executeUpdate();
		//��������Ӧ�û�ȡ�Ǹ���������ֵ��....
		ResultSet set=state.getGeneratedKeys();
		int index=-1;
		while (set.next()) {
		   index=set.getInt(1);
		}
		
		
		//�����ڽ��в���Ա��
		PreparedStatement state1=conn.prepareStatement(sqlEmp);
		//�����Ӧ������ռλ��
		state1.setString(1,"����");
		state1.setInt(2,index);
		state1.executeUpdate();
		System.out.println(index);
		
		
		state.close();
		state1.close();
		conn.close();
	}

}
