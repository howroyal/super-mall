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

import com.mall.common.dto.OrderInfoDto;
import com.mall.common.entity.OrderInfo;

@Repository
public class OrderInfoDao extends JdbcDaoSupport{

	public Long add(OrderInfo bean){
		final String sql = "INSERT INTO order_info "
			   + "(order_no,user_id,amount_price,payment_type,name,phone,address,message,is_pay,is_delivery)"
			   + " VALUES (?,?,?,?,?,?,?,?,?,?)";
		final Object[] params = {
				bean.getOrderNo(),bean.getUserId(),bean.getAmountPrice(),
				bean.getPaymentType(),bean.getName(),bean.getPhone(),
				bean.getAddress(),bean.getMessage(),bean.getIsPay(),bean.getIsDelivery()
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
	 * @Description: 查询订单信息
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年5月26日
	 */
	public List<OrderInfo> getOrders(OrderInfoDto dto) {
		StringBuffer sql = new StringBuffer("SELECT * FROM order_info WHERE 1 = 1");
		List<Object> params = new ArrayList<Object>();
		Long id = dto.getId();
		if (id != null) {
			sql.append(" AND ID = ?");
			params.add(id);
		}
		
		String orderNo = dto.getOrderNo();
		if (orderNo != null) {
			sql.append(" AND ORDER_NO = ?");
			params.add(orderNo);
		}
		
		String userId = dto.getUserId();
		if (userId != null) {
			sql.append(" AND user_id = ?");
			params.add(userId);
		}
		
		Integer paymentType = dto.getPaymentType();
		if (paymentType != null) {
			sql.append(" AND payment_type = ?");
			params.add(paymentType);
		}
		
		Integer isPay = dto.getIsPay();
		if (isPay != null) {
			sql.append(" AND is_pay = ?");
			params.add(isPay);
		}
		
		Integer isDelivery = dto.getIsDelivery();
		if (isDelivery != null) {
			sql.append(" AND is_delivery = ?");
			params.add(isDelivery);
		}
		
		Integer start = dto.getStart();
		Integer end = dto.getEnd();
		if (start != null && end != null) {
			sql.append(" LIMIt " + start +"," + end);
		}
		
		return getJdbcTemplate().query(sql.toString(),params.toArray(), new EntityMapper(OrderInfo.class));
	
	}

	/**
	 * @Description:
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年5月26日
	 */
	public long getOrdersCount(OrderInfoDto dto) {

		StringBuffer sql = new StringBuffer("SELECT count(*) FROM order_info WHERE 1 = 1");
		List<Object> params = new ArrayList<Object>();
		Long id = dto.getId();
		if (id != null) {
			sql.append(" AND ID = ?");
			params.add(id);
		}
		
		String orderNo = dto.getOrderNo();
		if (orderNo != null) {
			sql.append(" AND ORDER_NO = ?");
			params.add(orderNo);
		}
		
		String userId = dto.getUserId();
		if (userId != null) {
			sql.append(" AND user_id = ?");
			params.add(userId);
		}
		
		Integer isPay = dto.getIsPay();
		if (isPay != null) {
			sql.append(" AND is_pay = ?");
			params.add(isPay);
		}
		
		Integer isDelivery = dto.getIsDelivery();
		if (isDelivery != null) {
			sql.append(" AND is_delivery = ?");
			params.add(isDelivery);
		}
		
		return getJdbcTemplate().queryForLong(sql.toString(),params.toArray());
	}
}
