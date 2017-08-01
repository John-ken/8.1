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
 * 研究下那个大文本的存储
 * @ClassName: Demo3 
 * @Description: TODO
 * @author 小波波
 * @date 2017-8-1 上午10:17:36 
 *
 */
public class Demo3 {
	private String url="jdbc:mysql:///test";
	private String user="root";
	private String password="886887";
	/**
	 * 存入咋们的txt文件的内容
	 * @Title: testBigTextSave 
	 * @Description: TODO
	 * @param @throws Exception    
	 * @return void     
	 * @throws
	 */
	@Test
	public void testBigTextSave() throws Exception {
		//获取那个连接
		Class.forName("com.mysql.jdbc.Driver");
		//获取这个连接
		Connection conn = DriverManager.getConnection(url, user, password);
		String sql="insert into t_user(uName) values(?)";
		
		PreparedStatement state=conn.prepareStatement(sql);
		//向这个大文本写内容
		state.setCharacterStream(1,new FileReader(new File("bobo.txt")));
		//发送请求
		state.executeUpdate();
		state.close();
		conn.close();
	}
	
	/**
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 从数据库获取咋们的内容
	 * @Title: readTxtfromDB 
	 * @Description: TODO
	 * @param     
	 * @return void     
	 * @throws
	 */
	@Test
	public void readTxtfromDB() throws ClassNotFoundException, SQLException, IOException{
		//获取那个连接
		Class.forName("com.mysql.jdbc.Driver");
				//获取这个连接
		Connection conn = DriverManager.getConnection(url, user, password);
		//准备咋们的sl语句
		String sql="select * from bigData";
		//接下来应该 执行这个Sql语句
		PreparedStatement state=conn.prepareStatement(sql);
		ResultSet set=state.executeQuery();
		while (set.next()) {
			//接下来就应该获取咋们的的这个数据了
			Reader reader=set.getCharacterStream("content");
			//如果是我们需要将这个内容读取出来的话
			BufferedReader reader2=new BufferedReader(reader);
			String content=reader2.readLine();
			System.out.println(content);
			reader2.close();
		}
	}
	/**
	 * 写二进制的文件
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
		//获取那个连接
		Class.forName("com.mysql.jdbc.Driver");
				//获取这个连接
		Connection conn = DriverManager.getConnection(url, user, password);
		//准备咋们的sl语句
		PreparedStatement state=conn.prepareStatement("insert into bigData(img) values(?)");
		//下面就应该设置插入的数据
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
		//获取那个连接
		Class.forName("com.mysql.jdbc.Driver");
		//获取这个连接
		Connection conn = DriverManager.getConnection(url, user, password);
		//准备咋们的sl语句
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
