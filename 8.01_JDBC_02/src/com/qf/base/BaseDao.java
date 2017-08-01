package com.qf.base;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.qf.utils.JdbcUtils;

/**
 * ���dao��һ��ͨ�õ�dao ��Ҫ�����ṩ��ɾ�Ĳ�ķ���
 * @ClassName: BaseDao 
 * @Description: TODO
 * @author С����
 * @date 2017-8-1 ����3:03:21 
 *
 */
public class BaseDao {
    /**
     * @throws SQLException 
     * ͨ�õ�update�ķ���
     * @Title: update 
     * @Description: TODO
     * @param @param sql
     * @param @param parames    
     * @return void     
     * @throws
     */
	public void update(String sql,Object... parames) throws SQLException{
		//��һ��:��ȡ����
		Connection conn=JdbcUtils.getConnection();
		PreparedStatement state=conn.prepareStatement(sql);
		//��ȡզ�Ƕ������������Ԫ����
		ParameterMetaData metaData=state.getParameterMetaData();
		//��ȡ����Ĳ����ĸ���
		int parameCount=metaData.getParameterCount();
		//�ж���������ĸ�����parameCount�Ƿ�һ��
		if(parameCount!=parames.length){
			throw new RuntimeException("������һ��");
		}
		//����Ӧ�ý����������ֵ ��ֵ������statement
		for (int i = 0; i < parames.length; i++) {
			state.setObject(i+1,parames[i]);
		}
		//���������ݿ�ķ�������������
		state.executeUpdate();
		JdbcUtils.close(state, conn);
	}
	
	
}
