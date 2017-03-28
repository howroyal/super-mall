package com.mall.common.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.gson.internal.StringMap;
import com.mall.common.entity.GoodsInfo;

@Repository
public class GoodsInfoDao extends JdbcDaoSupport{

	public void add(GoodsInfo bean){
		String sql = "INSERT INTO goods_info "
				   + "(code,name,category_id,main_img,pre_img,stock,sales,"
				   + "clicks,market_price,price,description)"
				   + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {
							  bean.getCode(), bean.getName(),bean.getCategoryId(),bean.getMainImg(),bean.getPreImg(),
							  bean.getStock(),bean.getSales(),bean.getClicks(),bean.getMarketPrice(),bean.getPrice(),
							  bean.getDescription()
						   };
		getJdbcTemplate().update(sql,params);
	}
	
	public void update(GoodsInfo bean){
		String sql = "UPDATE goods_info SET "
			 		+ "name=?,category_id=?,img_url=?,commodity_code=?,original_model=?,"
			 		+ "inventories_number=?,salse_count=?,shelves_time=?,click_number=?,market_price=?,"
			 		+ "original_price=?,price=?,description=?,label=?,specification=?"
				   + " WHERE id = ?";
		Object[] params = {
				  bean.getCode(), bean.getName(),bean.getCategoryId(),bean.getMainImg(),bean.getPreImg(),
				  bean.getStock(),bean.getSales(),bean.getClicks(),bean.getMarketPrice(),bean.getPrice(),
				  bean.getDescription(),bean.getId()
			   };
		getJdbcTemplate().update(sql,params);
	}
	
	public void delete(Long id){
		if (id == null) {
			return;
		}
		String sql = "DELETE FROM goods_info WHERE id = ?";
		Object[] params = {id};
		getJdbcTemplate().update(sql,params);
	}
	
	@SuppressWarnings("unchecked")
	public List<GoodsInfo> query(int curPage, int pageSize) {
		String sql = "SELECT * FROM goods_info LIMIT ?,?";
		Object[] paramsArr = {(curPage - 1) * pageSize ,pageSize};
		return getJdbcTemplate().query(sql,paramsArr, new EntityMapper(GoodsInfo.class));
	
	}

	public long queryCount() {
		String sql = "SELECT COUNT(id) FROM goods_info";
		return getJdbcTemplate().queryForLong(sql);
	}

	@SuppressWarnings("unchecked")
	public GoodsInfo queryById(Long id) {
		String sql = "SELECT * FROM goods_info WHERE id = ?";
		Object[] params = {id};
		return getJdbcTemplate().queryForObject(sql,params,  new EntityMapper(GoodsInfo.class));
	}

	
	@SuppressWarnings("unchecked")
	public List<GoodsInfo> queryByParams(StringMap<String> paramsMap) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("SELECT * FROM goods_info WHERE 1=1");
		String categoryIds = paramsMap.get("categoryIds");
		if (categoryIds != null) {
			sql.append(" AND category_id IN " + categoryIds);
		}
		String minPrice = paramsMap.get("minPrice");
		if (minPrice != null) {
			sql.append(" AND price >= ?");
			params.add(Integer.parseInt(minPrice));
		}
		String maxPrice = paramsMap.get("maxPrice");
		if (maxPrice != null) {
			sql.append(" AND price <= ?");
			params.add(Integer.parseInt(maxPrice));
		}

		String name = paramsMap.get("name");
		if (name != null) {
			sql.append(" AND name LIKE ?");
			params.add("%" + name + "%");
		}
		
		String orderBy = paramsMap.get("orderBy");
		String orderType = paramsMap.get("orderType");
		if ((orderBy != null) && (orderType != null)) {
			sql.append(" ORDER BY " + orderBy + " " + orderType);
		}else {
			sql.append(" ORDER BY id DESC");
		}
		
		String curPage = paramsMap.get("curPage");
		String pageSize = paramsMap.get("pageSize");
		if (curPage != null && pageSize != null) {
			sql.append(" LIMIt " + (Integer.parseInt(curPage)-1)* Integer.parseInt(pageSize) +"," + pageSize);
		}else {
			String start = paramsMap.get("start");
			String end = paramsMap.get("end");
			if (start != null && end != null) {
				sql.append(" LIMIt " + start +"," + (Integer.parseInt(end) - Integer.parseInt(start)));
			}
		}
		
		return getJdbcTemplate().query(sql.toString(),params.toArray() ,new EntityMapper(GoodsInfo.class));
	}

	public Long queryCountByParams(StringMap<String> paramsMap) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("SELECT COUNT(id) FROM goods_info WHERE 1=1");
		String categoryIds = paramsMap.get("categoryIds");
		if (categoryIds != null) {
			sql.append(" AND category_id IN " + categoryIds);
		}
		String minPrice = paramsMap.get("minPrice");
		if (minPrice != null) {
			sql.append(" AND price >= ?");
			params.add(Integer.parseInt(minPrice));
		}
		String maxPrice = paramsMap.get("maxPrice");
		if (maxPrice != null) {
			sql.append(" AND price <= ?");
			params.add(Integer.parseInt(maxPrice));
		}
		String name = paramsMap.get("name");
		if (name != null) {
			sql.append(" AND name LIKE ?");
			params.add("%" + name + "%");
		}
		return getJdbcTemplate().queryForLong(sql.toString(),params.toArray());
	}
}
