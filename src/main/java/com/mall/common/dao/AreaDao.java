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

import com.mall.common.entity.Area;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月31日
 *@Version:1.1.0
 */
@Repository
public class AreaDao extends JdbcDaoSupport{

	public List<Area> queryAreasByProCode(String cityCode){
		if (cityCode == null) {
			return null;
		}
		String sql = "SELECT * FROM AREA WHERE CITY_CODE = ?";
		return getJdbcTemplate().query(sql,new Object[]{cityCode}, new EntityMapper(Area.class));
	}

	/**
	 * @Description:
	 * @param areaId
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月31日
	 */
	public Area queryById(Long id) {
		Area info = null;
		if (id == null) {
			return null;
		}
		String sql = "SELECT * FROM area WHERE id = ?";
		Object[] params = {id};
		
		try {
			info = getJdbcTemplate().queryForObject(sql,params,new EntityMapper(Area.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	
	}
}
