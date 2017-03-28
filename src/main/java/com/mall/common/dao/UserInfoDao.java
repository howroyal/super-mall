package com.mall.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mall.common.entity.UserInfo;


/**
 * 用户信息dao
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年7月25日
 *@Version:1.1.0
 */
@Repository
@SuppressWarnings("unchecked")
public class UserInfoDao extends JdbcDaoSupport{

	public Long add(UserInfo bean){
		final String sql = "INSERT INTO user_info "
			   + "(user_name,password)"
			   + " VALUES (?,?)";
		final Object[] params = {bean.getUserName(),bean.getPassword()};
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
				return ps;
			}
		}, keyHolder);
		
		return  keyHolder.getKey().longValue();
	}
	
	public UserInfo queryById(Long id) {
		String sql = "SELECT * FROM user_info WHERE id = ?";
		Object[] params = {id};
		return getJdbcTemplate().queryForObject(sql,params,  new EntityMapper(UserInfo.class));
	}
	
	public List<UserInfo> query(UserInfo info){
		StringBuffer sql = new StringBuffer("SELECT * FROM user_info WHERE 1=1");
		List<String> objs = new ArrayList<String>();
		if (info != null) {
			if(info.getUserName() != null){
				sql.append(" AND user_name = ?");
				objs.add(info.getUserName());
			}
			
			if(info.getPassword() != null){
				sql.append(" AND password = ?");
				objs.add(info.getPassword());
			}
		}
		
		return getJdbcTemplate().query(sql.toString(),objs.toArray(), new EntityMapper(UserInfo.class));
	}
	
	public void update(UserInfo info){
		String sql = "UPDATE user_info SET password = ? WHERE id = ?";
		Object[] params = {info.getPassword(),info.getId()};
		getJdbcTemplate().update(sql,params);
	}
}
