package com.qf.base;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.utils.JdbcUtils;

/**
 * 这个dao是一个通用的dao 需要岁外提供增删改查的方法
 * @ClassName: BaseDao 
 * @Description: TODO
 * @author 小波波
 * @date 2017-8-1 下午3:03:21 
 *
 */
public class BaseDao {
    /**
     * @throws SQLException 
     * 通用的update的方法
     * @Title: update 
     * @Description: TODO
     * @param @param sql
     * @param @param parames    
     * @return void     
     * @throws
     */
	public void update(String sql,Object... parames) throws SQLException{
		//第一步:获取连接
		Connection conn=JdbcUtils.getConnection();
		PreparedStatement state=conn.prepareStatement(sql);
		//获取咋们对外请求参数的元数据
		ParameterMetaData metaData=state.getParameterMetaData();
		//获取请求的参数的个数
		int parameCount=metaData.getParameterCount();
		//判断请求参数的个数和parameCount是否一致
		if(parameCount!=parames.length){
			throw new RuntimeException("参数不一致");
		}
		//下面应该将数组里面的值 赋值给我们statement
		for (int i = 0; i < parames.length; i++) {
			state.setObject(i+1,parames[i]);
		}
		//下面向数据库的服务器发送请求
		state.executeUpdate();
		JdbcUtils.close(state, conn);
	}
	
	
}
