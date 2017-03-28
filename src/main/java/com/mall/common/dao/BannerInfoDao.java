/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月4日
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

import com.mall.common.dto.BannerInfoDto;
import com.mall.common.entity.BannerInfo;


/**
 *@Title: banner管理的dao类
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月4日
 *@Version:1.1.0
 */
@Repository
@SuppressWarnings("unchecked")
public class BannerInfoDao extends JdbcDaoSupport{
 
	/**
	 * 新增banner信息
	 * @Description:
	 * @param info
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	public Long add(BannerInfo bean){
		final String sql = "INSERT INTO BANNER_INFO (banner_name,pic_url,url,description,seat,state)VALUES(?,?,?,?,?,?)";
		final Object[] params = {bean.getBannerName(),bean.getPicUrl(),bean.getUrl(),bean.getDescription(),bean.getSeat(),bean.getState()};
		
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
	 * 更新banner信息
	 * @Description:
	 * @param info
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	public void update(BannerInfo bean){
		String sql = "UPDATE BANNER_INFO SET"
					+ " banner_name = ?,"
					+ " pic_url = ?,"
					+ " url = ?,"
					+ " description = ?,"
					+ " seat = ?,"
					+ " state = ?"
					+ " WHERE id = ?";
		Object[] params = {bean.getBannerName(),
							bean.getPicUrl(),
							bean.getUrl(),
							bean.getDescription(),
							bean.getSeat(),
							bean.getState(),
							bean.getId()};
		getJdbcTemplate().update(sql,params);
		
	}
	
	/**
	 * 根据pkid查询banner信息
	 * @Description:
	 * @param pkid
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	public BannerInfo queryById(Long id){
		if (id == null) {
			return null;
		}
		String sql = "SELECT * FROM BANNER_INFO WHERE id = ?";
		Object[] params = {id};
		return getJdbcTemplate().queryForObject(sql,params,new EntityMapper(BannerInfo.class));
	}
	
	/**
	 * 分页查询banner信息
	 * @Description:
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	public List<BannerInfo> query(BannerInfoDto dto){
		StringBuffer sql = new StringBuffer("SELECT * FROM BANNER_INFO WHERE 1 = 1");
		List<Object> params = new ArrayList<Object>();
		Long id = dto.getId();
		if (id != null) {
			sql.append(" AND ID = ?");
			params.add(id);
		}
		
		String bannerName = dto.getBannerName();
		if (bannerName != null) {
			sql.append(" AND banner_name = ?");
			params.add(bannerName);
		}
		
		Integer state = dto.getState();
		if (state != null) {
			sql.append(" AND state = ?");
			params.add(state);
		}
		
		Integer start = dto.getStart();
		Integer end = dto.getEnd();
		if (start != null && end != null) {
			sql.append(" LIMIt " + start +"," + end);
		}
		
		return getJdbcTemplate().query(sql.toString(),params.toArray(), new EntityMapper(BannerInfo.class));
	}
	
	/**
	 * 查询banner信息总数
	 * @Description:
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	public long queryCount(BannerInfoDto dto){
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM BANNER_INFO WHERE 1 = 1");
		List<Object> params = new ArrayList<Object>();
		Long id = dto.getId();
		if (id != null) {
			sql.append(" AND ID = ?");
			params.add(id);
		}
		
		String bannerName = dto.getBannerName();
		if (bannerName != null) {
			sql.append(" AND banner_name = ?");
			params.add(bannerName);
		}
		
		Integer state = dto.getState();
		if (state != null) {
			sql.append(" AND state = ?");
			params.add(state);
		}
		return getJdbcTemplate().queryForLong(sql.toString(),params.toArray());
	}
}
