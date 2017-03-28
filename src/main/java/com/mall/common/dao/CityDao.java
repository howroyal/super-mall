/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月31日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.mall.common.entity.City;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月31日
 *@Version:1.1.0
 */
@Repository
public class CityDao extends JdbcDaoSupport{

	public List<City> queryCitysByProCode(String provinceCode){
		if (provinceCode == null) {
			return null;
		}
		String sql = "SELECT * FROM city WHERE PROVINCE_CODE = ?";
		return getJdbcTemplate().query(sql,new Object[]{provinceCode}, new EntityMapper(City.class));
	}

	/**
	 * @Description:
	 * @param cityId
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月31日
	 */
	public City queryById(Long id) {
		City info = null;
		if (id == null) {
			return null;
		}
		String sql = "SELECT * FROM city WHERE id = ?";
		Object[] params = {id};
		
		try {
			info = getJdbcTemplate().queryForObject(sql,params,new EntityMapper(City.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	
	}
}
