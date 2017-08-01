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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * �о����Ǹ����ı��Ĵ洢
 * @ClassName: Demo3 
 * @Description: TODO
 * @author С����
 * @date 2017-8-1 ����10:17:36 
 *
 */
public class Demo3 {
	private String url="jdbc:mysql:///test";
	private String user="root";
	private String password="886887";
	/**
	 * ����զ�ǵ�txt�ļ�������
	 * @Title: testBigTextSave 
	 * @Description: TODO
	 * @param @throws Exception    
	 * @return void     
	 * @throws
	 */
	@Test
	public void testBigTextSave() throws Exception {
		//��ȡ�Ǹ�����
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ�������
		Connection conn = DriverManager.getConnection(url, user, password);
		String sql="insert into t_user(uName) values(?)";
		
		PreparedStatement state=conn.prepareStatement(sql);
		//��������ı�д����
		state.setCharacterStream(1,new FileReader(new File("bobo.txt")));
		//��������
		state.executeUpdate();
		state.close();
		conn.close();
	}
	
	/**
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * �����ݿ��ȡզ�ǵ�����
	 * @Title: readTxtfromDB 
	 * @Description: TODO
	 * @param     
	 * @return void     
	 * @throws
	 */
	@Test
	public void readTxtfromDB() throws ClassNotFoundException, SQLException, IOException{
		//��ȡ�Ǹ�����
		Class.forName("com.mysql.jdbc.Driver");
				//��ȡ�������
		Connection conn = DriverManager.getConnection(url, user, password);
		//׼��զ�ǵ�sl���
		String sql="select * from bigData";
		//������Ӧ�� ִ�����Sql���
		PreparedStatement state=conn.prepareStatement(sql);
		ResultSet set=state.executeQuery();
		while (set.next()) {
			//��������Ӧ�û�ȡզ�ǵĵ����������
			Reader reader=set.getCharacterStream("content");
			//�����������Ҫ��������ݶ�ȡ�����Ļ�
			BufferedReader reader2=new BufferedReader(reader);
			String content=reader2.readLine();
			System.out.println(content);
			reader2.close();
		}
	}
	/**
	 * д�����Ƶ��ļ�
	 * @Title: writerBinarayFile 
	 * @Description: TODO
	 * @param @throws ClassNotFoundException
	 * @param @throws SQLException
	 * @param @throws IOException    
	 * @return void     
	 * @throws
	 */
	@Test
	public void writerBinarayFile() throws ClassNotFoundException, SQLException, IOException{
		//��ȡ�Ǹ�����
		Class.forName("com.mysql.jdbc.Driver");
				//��ȡ�������
		Connection conn = DriverManager.getConnection(url, user, password);
		//׼��զ�ǵ�sl���
		PreparedStatement state=conn.prepareStatement("insert into bigData(img) values(?)");
		//�����Ӧ�����ò��������
		state.setBinaryStream(1,new FileInputStream(new File("C:/Users/apple/Desktop/aa.jpg")));
		state.executeUpdate();
		
		state.close();
		conn.close();
	}
	/**
	 * 
	 * @Title: readBinarayFile 
	 * @Description: TODO
	 * @param @throws ClassNotFoundException
	 * @param @throws SQLException
	 * @param @throws IOException    
	 * @return void     
	 * @throws
	 */
	@Test
	public void readBinarayFile() throws ClassNotFoundException, SQLException, IOException{
		//��ȡ�Ǹ�����
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ�������
		Connection conn = DriverManager.getConnection(url, user, password);
		//׼��զ�ǵ�sl���
		PreparedStatement state=conn.prepareStatement("select * from bigData where img is not null");
        ResultSet set=state.executeQuery();
        while (set.next()) {
        	FileOutputStream out=new FileOutputStream(new File("G:/bobo.jpg"));
			InputStream in=set.getBinaryStream("img");
			int length;
			byte[] buf=new byte[1024];
			while ((length=in.read(buf))!=-1) {
				out.write(buf, 0, length);
			}
			in.close();
			out.close();
		}
		
		state.close();
		conn.close();
	}
	
	
	
	
}
