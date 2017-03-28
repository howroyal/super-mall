package com.mall.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mall.common.entity.OrderDetailInfo;

@Repository
public class OrderDetailInfoDao extends JdbcDaoSupport{

	public void add(OrderDetailInfo bean){
		final String sql = "INSERT INTO order_detail_info "
			   + "(order_id,goods_id,goods_name,main_img_url,price"
			   + ",amount,total_price)"
			   + " VALUES (?,?,?,?,?,?,?)";
		
		final Object[] params = {bean.getOrderId(),bean.getGoodsId(),bean.getGoodsName()
				,bean.getMainImgUrl(),bean.getPrice(),bean.getAmount(),bean.getTotalPrice()};
		
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
		//return  keyHolder.getKey().longValue();
	}
}
