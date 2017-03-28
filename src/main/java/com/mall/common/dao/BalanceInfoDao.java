/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年6月3日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
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

import com.mall.common.dto.BalanceDto;
import com.mall.common.entity.BalanceInfo;

/**
 *@Title: 余额dao
 *@Description:
 *@Author:hao.wang
 *@Since:2016年6月3日
 *@Version:1.1.0
 */
@Repository
@SuppressWarnings("unchecked")
public class BalanceInfoDao extends JdbcDaoSupport{

	public Long add(BalanceInfo bean){
		final String sql = "INSERT INTO balance_info (user_id,money,type,is_valid) VALUES (?,?,?,?)";
		final Object[] params = {bean.getUserId(),bean.getMoney(),bean.getType(),bean.getIsValid()};
		
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
	
	public BalanceInfo getById(Long id){
		if (id == null) {
			return null;
		}
		String sql = "SELECT * FROM balance_info WHERE id = ?";
		Object[] params = {id};
		return getJdbcTemplate().queryForObject(sql,params,new EntityMapper(BalanceInfo.class));
	}
	
	public List<BalanceInfo> getBalances(BalanceDto dto){
		StringBuffer sql = new StringBuffer("SELECT * FROM balance_info WHERE 1 = 1");
		List<Object> params = new ArrayList<Object>();
		Long id = dto.getId();
		if (id != null) {
			sql.append(" AND ID = ?");
			params.add(id);
		}
		
		String userId = dto.getUserId();
		if (userId != null) {
			sql.append(" AND user_id = ?");
			params.add(userId);
		}
		
		Integer type = dto.getType();
		if (type != null) {
			sql.append(" AND type = ?");
			params.add(type);
		}
		
		Integer isValid = dto.getIsValid();
		if (isValid != null) {
			sql.append(" AND is_valid = ?");
			params.add(isValid);
		}
		
		Integer start = dto.getStart();
		Integer end = dto.getEnd();
		if (start != null && end != null) {
			sql.append(" LIMIt " + start +"," + end);
		}
		
		return getJdbcTemplate().query(sql.toString(),params.toArray(), new EntityMapper(BalanceInfo.class));
	
	}
	
	public long getBalanceCount(BalanceDto dto){

		StringBuffer sql = new StringBuffer("SELECT count(*) FROM balance_info WHERE 1 = 1");
		List<Object> params = new ArrayList<Object>();
		Long id = dto.getId();
		if (id != null) {
			sql.append(" AND ID = ?");
			params.add(id);
		}
		
		String userId = dto.getUserId();
		if (userId != null) {
			sql.append(" AND user_id = ?");
			params.add(userId);
		}
		
		Integer type = dto.getType();
		if (type != null) {
			sql.append(" AND type = ?");
			params.add(type);
		}
		
		Integer isValid = dto.getIsValid();
		if (isValid != null) {
			sql.append(" AND is_valid = ?");
			params.add(isValid);
		}
		
		return getJdbcTemplate().queryForLong(sql.toString(),params.toArray());
	}

	/**
	 * 得到用户余额
	 * @Description:
	 * @param userId
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月3日
	 */
	public float getBalance(String userId) {
		StringBuffer sql = new StringBuffer("SELECT sum(money) FROM balance_info WHERE user_id = ?");
		Object[] params = {userId};
		Float money = getJdbcTemplate().queryForObject(sql.toString(),params,Float.class);
		if (money == null) {
			money = 0F;
		}
		
		return money;
	}

	/**
	 * @Description:
	 * @param info
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月22日
	 */
	public int update(BalanceInfo info) {
		String sql = "UPDATE balance_info SET is_valid = ? WHERE id = ?";
		Object[] params = {info.getIsValid(),info.getId()};
		
		return getJdbcTemplate().update(sql,params);
	}
	
}
