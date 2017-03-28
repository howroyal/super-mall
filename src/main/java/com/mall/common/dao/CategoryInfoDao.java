package com.mall.common.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.gson.internal.StringMap;
import com.mall.common.entity.CategoryInfo;

@Repository
@SuppressWarnings("unchecked")
public class CategoryInfoDao extends JdbcDaoSupport{

	public void add(CategoryInfo bean){
		String sql = "INSERT INTO category_info (name,img_url,description,parent_id) VALUES (?,?,?,?)";
		Object[] params = {bean.getName(),bean.getImgUrl(),bean.getDescription(),bean.getParentId()};
		getJdbcTemplate().update(sql,params);
	}

	public List<CategoryInfo> query(int curPage,int pageSize) {
		String sql = "SELECT * FROM category_info LIMIT ?,?";
		Object[] paramsArr = {(curPage - 1) * pageSize ,pageSize};
		return getJdbcTemplate().query(sql,paramsArr, new EntityMapper(CategoryInfo.class));
	}
	
	public void update(CategoryInfo info){
		String sql = "UPDATE category_info SET name = ?,img_url = ?,description = ?,parent_id = ? WHERE id = ?";
		Object[] params = {info.getName(),info.getImgUrl(),info.getDescription(),info.getParentId(),info.getId()};
		getJdbcTemplate().update(sql,params);
	}
	
	public void delete(Long id){
		if (id == null) {
			return;
		}
		String sql = "DELETE FROM category_info WHERE id = ?";
		Object[] params = {id};
		getJdbcTemplate().update(sql,params);
	}

	public Long queryCount() {
		String sql = "SELECT COUNT(id) FROM category_info";
		return getJdbcTemplate().queryForLong(sql);
	}
	
	public List<CategoryInfo> queryTop(){
		String sql = "SELECT * FROM category_info WHERE parent_id is null";
		return getJdbcTemplate().query(sql,new EntityMapper(CategoryInfo.class));
	
	}
	
	public List<CategoryInfo> queryByParentId(Long parentId){
		String sql = "SELECT * FROM category_info WHERE parent_id";
		Object[] params = new Object[]{};
		if (parentId == null) {
			sql += " IS NULL";
		}else {
			params = new Object[]{parentId};
			sql += " = ?";
		}
		
		return getJdbcTemplate().query(sql,params,new EntityMapper(CategoryInfo.class));
	}

	@SuppressWarnings("finally")
	public CategoryInfo queryById(Long id) {
		CategoryInfo info = null;
		if (id == null) {
			return null;
		}
		String sql = "SELECT * FROM category_info WHERE id = ?";
		Object[] params = {id};
		
		try {
			info = getJdbcTemplate().queryForObject(sql,params,new EntityMapper(CategoryInfo.class));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return info;
		}
	}

	public List<CategoryInfo> queryByParams(StringMap<String> reqMap) throws Exception {
		StringBuffer sql = new StringBuffer("SELECT * FROM category_info WHERE 1=1 ");
		List<String> reqList = new ArrayList<String>();
		String isBand = reqMap.get("is_Band");
		if (isBand != null) {
			sql.append(" AND is_band = ?");
			reqList.add(isBand);
		}
		return getJdbcTemplate().query(sql.toString(),reqList.toArray(), new EntityMapper(CategoryInfo.class));
	}
}
