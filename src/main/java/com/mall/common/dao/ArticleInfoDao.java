package com.mall.common.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.mall.common.entity.ArticleInfo;
import com.mall.common.entity.CarouselInfo;

@Repository
@SuppressWarnings("unchecked")
public class ArticleInfoDao extends JdbcDaoSupport{
	
	public void add(ArticleInfo bean) {
		String sql = "INSERT INTO article_info (name,content,createtime) VALUES (?,?,?)";
		Object[] params = {bean.getName(),bean.getContent(),bean.getCreateTime()};
		getJdbcTemplate().update(sql,params);
	}
	
	public List<ArticleInfo> query(int curPage,int pageSize){
		String sql = "SELECT * FROM article_info  ORDER by id DESC LIMIT ?,?";
		Object[] paramsArr = {(curPage - 1) * pageSize ,pageSize};
		return getJdbcTemplate().query(sql,paramsArr, new EntityMapper(ArticleInfo.class));
	}
	
	public ArticleInfo queryById(Long id){
		if (id == null) {
			return null;
		}
		String sql = "SELECT * FROM article_info WHERE id = ?";
		Object[] paramsArr = {id};
		return getJdbcTemplate().queryForObject(sql,paramsArr, new EntityMapper(CarouselInfo.class));
	}
	
	public void update(ArticleInfo bean){
		String sql = "UPDATE article_info SET name = ?,url=?,imgurl = ?";
		Object[] params = {bean.getName(),bean.getContent()};
		getJdbcTemplate().update(sql,params);
	}

	public List<ArticleInfo> queryByPid(Long pId) {
		String sql = "SELECT * FROM article_info WHERE pid = ?";
		Object[] paramsArr = {pId};
		return getJdbcTemplate().query(sql,paramsArr, new EntityMapper(ArticleInfo.class));
	}
}
