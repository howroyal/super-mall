/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月28日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.mall.common.entity.Province;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月28日
 *@Version:1.1.0
 */
@Repository
public class ProvinceDao extends JdbcDaoSupport{

	@SuppressWarnings("unchecked")
	public Province queryById(Long id){
		Province info = null;
		if (id == null) {
			return null;
		}
		String sql = "SELECT * FROM province WHERE id = ?";
		Object[] params = {id};
		
		try {
			info = getJdbcTemplate().queryForObject(sql,params,new EntityMapper(Province.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	
	}
	
	public List<Province> queryProvinces(){
		String sql = "SELECT * FROM province";
		return getJdbcTemplate().query(sql, new EntityMapper(Province.class));
	}
}
