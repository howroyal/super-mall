package com.mall.common.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.mall.common.entity.CarouselInfo;

@Repository
@SuppressWarnings("unchecked")
public class CarouselInfoDao extends JdbcDaoSupport{

	public void add(CarouselInfo bean) {
		String sql = "INSERT INTO carousel_info (name,url,img_url) VALUES (?,?,?)";
		Object[] params = {bean.getName(),bean.getUrl(),bean.getImgUrl()};
		getJdbcTemplate().update(sql,params);
	}
	
	public List<CarouselInfo> query(int curPage,int pageSize){
		String sql = "SELECT * FROM carousel_info LIMIT ?,?";
		Object[] paramsArr = {(curPage - 1) * pageSize ,pageSize};
		return getJdbcTemplate().query(sql,paramsArr, new EntityMapper(CarouselInfo.class));
	}
	
	public void update(CarouselInfo info){
		String sql = "UPDATE carousel_info SET name = ?,url=?,img_url = ? WHERE id = ?";
		Object[] params = {info.getName(),info.getUrl(),info.getImgUrl(),info.getId()};
		getJdbcTemplate().update(sql,params);
	}
}
