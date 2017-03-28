package com.mall.common.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.mall.common.entity.ArticleCategoryInfo;

@Repository
public class ArticleCategoryDao extends JdbcDaoSupport{

	public List<ArticleCategoryInfo> getInfos() {
		String sql = "SELECT * FROM article_category_info";
		return getJdbcTemplate().query(sql,new EntityMapper(ArticleCategoryInfo.class));
	}

	public Long getCount() {
		String sql = "SELECT count(id) FROM article_category_info";
		return getJdbcTemplate().queryForLong(sql);
	}
}
