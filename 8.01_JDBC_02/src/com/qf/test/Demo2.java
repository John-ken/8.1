package com.qf.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

/**
 * �о���զ�ǵ�����
 * @ClassName: Demo2 
 * @Description: TODO
 * @author С����
 * @date 2017-8-1 ����9:17:21 
 *
 */
public class Demo2 {
	private String url="jdbc:mysql:///test";
	private String user="root";
	private String password="886887";
	
	@Test 
	public void testGenerateKeys() {
		Connection conn=null;
		//������������ʾ���ǵ�һ��ת��
		PreparedStatement state=null;
		PreparedStatement state1=null;
		
		//��ʾ���ǵڶ��ε�ת��
		PreparedStatement state21=null;
		PreparedStatement state22=null;
		
		
		
		try {
			//��ȡ�Ǹ�����
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ�������
			conn = DriverManager.getConnection(url, user, password);
			String scSql="update t_user set money=money-1000 where uName=?";
			String gxSql="update t_user set money=money+1000 where uName=?";
			
			//ʹ������ĵ�һ��:�����ֶ��ύ
			conn.setAutoCommit(false);   //�ر�զ�ǵ��Զ��ύ
			//��ʼ����
			state=conn.prepareStatement(scSql);
			state.setString(1,"��˼��");
			state.executeUpdate();
			
			//�������ͻ����������
			
			//�����Ӧ��ת����
			state1=conn.prepareStatement(gxSql);
			state1.setString(1,"�ָ���");
			state1.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			System.out.println("��������û�ҵ�");
		} catch (Exception e) {
			//����ͳ�����Sql�쳣
			//����������쳣�Ļ���ô����Ҫ�ع�
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			//����û������Ļ�  Ӧ���ύ
			try {
				conn.commit();
			} catch (SQLException e1) {
				System.out.println("�ύ����������");
			}
			//������ر���Դ
			try {
				if(null!=state){
					state.close();
				}
			} catch (SQLException e) {
				System.out.println("�ر�state��ʱ������쳣");
			}
			try {
				if(null!=state1){
					state1.close();
				}
			} catch (SQLException e) {
				System.out.println("�ر�sate1��ʱ������쳣");
			}
			try {
				if(null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("�ر����ӳ������쳣");
			}
		}	
	}
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * ͨ���ع��������ûع��ĵط�
	 * @Title: testGenerateKeys2 
	 * @Description: TODO
	 * @param     
	 * @return void     
	 * @throws
	 */
	@Test 
	public void testGenerateKeys2() throws ClassNotFoundException, SQLException {
		Connection conn=null;
		//������������ʾ���ǵ�һ��ת��
		PreparedStatement state=null;
		PreparedStatement state1=null;
		
		//��ʾ���ǵڶ��ε�ת��
		PreparedStatement state21=null;
		PreparedStatement state22=null;
		//���ûع���
		Savepoint savepoint=null;
		//��ȡ�Ǹ�����
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ�������
		conn = DriverManager.getConnection(url, user, password);
		
		
		try {
			String scSql="update t_user set money=money-1000 where uName=?";
			String gxSql="update t_user set money=money+1000 where uName=?";
			
			
			//��һ�ε�ת��
			
			
			//ʹ������ĵ�һ��:�����ֶ��ύ
			conn.setAutoCommit(false);   //�ر�զ�ǵ��Զ��ύ
			//��ʼ����
			state=conn.prepareStatement(scSql);
			state.setString(1,"��˼��");
			state.executeUpdate();
			//�����Ӧ��ת����
			state1=conn.prepareStatement(gxSql);
			state1.setString(1,"�ָ���");
			state1.executeUpdate();
			
			
			//������Ҫ���õ��ǻع���
			
			savepoint=conn.setSavepoint();
			
			//������е��ǵڶ��ε�ת��
			
			//��ʼ����
			state21=conn.prepareStatement(scSql);
			state21.setString(1,"��˼��");
			state21.executeUpdate();
			
			//�������������
			int k=1/0;
			
			//�����Ӧ��ת����
			state22=conn.prepareStatement(gxSql);
			state22.setString(1,"�ָ���");
			state22.executeUpdate();
				
		} catch (Exception e) {
			//����ͳ�����Sql�쳣
			//����������쳣�Ļ���ô����Ҫ�ع�
			conn.rollback(savepoint);
		}finally{
			//����û������Ļ�  Ӧ���ύ
			try {
				conn.commit();
			} catch (SQLException e1) {
				System.out.println("�ύ����������");
			}
			//������ر���Դ
			try {
				if(null!=state){
					state.close();
				}
			} catch (SQLException e) {
				System.out.println("�ر�state��ʱ������쳣");
			}
			try {
				if(null!=state1){
					state1.close();
				}
			} catch (SQLException e) {
				System.out.println("�ر�sate1��ʱ������쳣");
			}
			
			try {
				if(null!=state21){
					state21.close();
				}
			} catch (SQLException e) {
				System.out.println("�ر�state��ʱ������쳣");
			}
			try {
				if(null!=state22){
					state22.close();
				}
			} catch (SQLException e) {
				System.out.println("�ر�sate1��ʱ������쳣");
			}
			try {
				if(null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("�ر����ӳ������쳣");
			}
		}	
	}
}
