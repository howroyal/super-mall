/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年6月6日
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

import com.mall.common.dto.RechargeDto;
import com.mall.common.entity.RechargeInfo;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年6月6日
 *@Version:1.1.0
 */
@Repository
@SuppressWarnings("unchecked")
public class RechargeInfoDao extends JdbcDaoSupport{
	public Long add(RechargeInfo bean){
		final String sql = "INSERT INTO recharge_info "
			   + "(user_id,pay_way,pay_account,recharge_money,pay_info)"
			   + " VALUES (?,?,?,?,?)";
		final Object[] params = {bean.getUserId(),
								 bean.getPayWay(),
								 bean.getPayAccount(),
								 bean.getRechargeMoney(),
								 bean.getPayInfo()
								 };
		
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

	/**
	 * @Description:
	 * @param id
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月21日
	 */
	public RechargeInfo getById(Long id) {
		if (id == null) {
			return null;
		}
		String sql = "SELECT * FROM recharge_info WHERE id = ?";
		Object[] params = {id};
		return getJdbcTemplate().queryForObject(sql,params,new EntityMapper(RechargeInfo.class));
	}

	/**
	 * @Description:
	 * @param info
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月21日
	 */
	public int update(RechargeInfo info) {
		String sql = "UPDATE recharge_info SET valid = ? WHERE id = ?";
		Object[] params = {info.getValid(),info.getId()};
		
		return getJdbcTemplate().update(sql,params);
	}

	/**
	 * @Description:
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月21日
	 */
	public List<RechargeInfo> getRecharges(RechargeDto dto) {
		StringBuffer sql = new StringBuffer("SELECT * FROM recharge_info WHERE 1 = 1");
		List<Object> params = new ArrayList<Object>();
		Long id = dto.getId();
		if (id != null) {
			sql.append(" AND ID = ?");
			params.add(id);
		}
		
		Long userId = dto.getUserId();
		if (userId != null) {
			sql.append(" AND user_id = ?");
			params.add(userId);
		}
		
		String payWay = dto.getPayWay();
		if (payWay != null) {
			sql.append(" AND pay_way = ?");
			params.add(payWay);
		}
		
		String valid = dto.getValid();
		if (valid != null) {
			sql.append(" AND valid = ?");
			params.add(valid);
		}
		
		Integer start = dto.getStart();
		Integer end = dto.getEnd();
		if (start != null && end != null) {
			sql.append(" LIMIt " + start +"," + end);
		}
		
		return getJdbcTemplate().query(sql.toString(),params.toArray(), new EntityMapper(RechargeInfo.class));
	}

	/**
	 * @Description:
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年6月21日
	 */
	public long getRechargeCount(RechargeDto dto) {
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM recharge_info WHERE 1 = 1");
		List<Object> params = new ArrayList<Object>();
		Long id = dto.getId();
		if (id != null) {
			sql.append(" AND ID = ?");
			params.add(id);
		}
		
		Long userId = dto.getUserId();
		if (userId != null) {
			sql.append(" AND user_id = ?");
			params.add(userId);
		}
		
		String payWay = dto.getPayWay();
		if (payWay != null) {
			sql.append(" AND pay_way = ?");
			params.add(payWay);
		}
		
		String valid = dto.getValid();
		if (valid != null) {
			sql.append(" AND valid = ?");
			params.add(valid);
		}
		
		return getJdbcTemplate().queryForLong(sql.toString(),params.toArray());
	}
}
