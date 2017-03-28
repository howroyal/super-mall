package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.internal.StringMap;
import com.mall.common.dao.GoodsInfoDao;
import com.mall.common.entity.GoodsInfo;

@Service
public class GoodsInfoService {
	@Resource
	private GoodsInfoDao goodsInfoDao;
	
	public void add(GoodsInfo bean){
		goodsInfoDao.add(bean);
	}
	
	public List<GoodsInfo> query(int curPage, int pageSize) {
		
		return goodsInfoDao.query(curPage,pageSize);
	}
	public long queryCount() {
		return goodsInfoDao.queryCount();
	}

	public void update(GoodsInfo info) {
		goodsInfoDao.update(info);
	}

	public GoodsInfo queryById(Long id) {
		return goodsInfoDao.queryById(id);
	}

	/**
	 * 得到推荐的商品
	 * @Title    TODO
	 * @Description    TODO  
	 * @author 王浩  
	 * @Since 2015-8-13
	 * @return
	 */
	public List<GoodsInfo> getRecommendGoodsInfos() {
		//默认最多显示4条记录
		return goodsInfoDao.query(1, 4);
	}

	
	public List<GoodsInfo> queryByParams(StringMap<String> params) {
		if (params == null) {
			return null;
		}
		return goodsInfoDao.queryByParams(params);
	}
	
	public Long queryCountByParams(StringMap<String> params) {

		if (params == null) {
			return null;
		}
		
		return goodsInfoDao.queryCountByParams(params);
	}
	/**   
	 * 查询最便宜的商品
	 * @Title    TODO
	 * @Description    TODO  
	 * @author 王浩  
	 * @Since 2015-8-20
	 * @param curPage
	 * @param PageSize
	 * @return 
	 */  
	public List<GoodsInfo> getCheapInfos(int curPage, int pageSize) {
		
		StringMap<String> reqMap = new StringMap<String>();
		reqMap.put("orderBy", "price");
		reqMap.put("orderType", "ASC");
		reqMap.put("curPage", curPage+"");
		reqMap.put("pageSize", pageSize+"");
		
		return goodsInfoDao.queryByParams(reqMap);
	}

	/**   
	 * 查询最新上架商品
	 * @Title    TODO
	 * @Description    TODO  
	 * @author 王浩  
	 * @Since 2015-8-20
	 * @param curPage
	 * @param PageSize
	 * @return 
	 */  
	public List<GoodsInfo> getNewestInfos(int curPage, int pageSize) {
		StringMap<String> reqMap = new StringMap<String>();
		reqMap.put("orderBy", "id");
		reqMap.put("orderType", "DESC");
		reqMap.put("curPage", curPage+"");
		reqMap.put("pageSize", pageSize+"");
		
		return goodsInfoDao.queryByParams(reqMap);
	}

	/**   
	 * 查询热卖商品
	 * @Title    TODO
	 * @Description    TODO  
	 * @author 王浩  
	 * @Since 2015-8-20
	 * @param curPage
	 * @param PageSize
	 * @return 
	 */  
	public List<GoodsInfo> getHotInfos(int curPage, int pageSize) {
		StringMap<String> reqMap = new StringMap<String>();
		reqMap.put("orderBy", "salse_count");
		reqMap.put("orderType", "DESC");
		reqMap.put("curPage", curPage+"");
		reqMap.put("pageSize", pageSize+"");
		
		return goodsInfoDao.queryByParams(reqMap);
	}

	/**
	 * 取得浏览人数排序的数据   
	 * @Title    TODO
	 * @Description    TODO  
	 * @author 王浩  
	 * @Since 2015-8-20
	 * @param curPage
	 * @param PageSize
	 * @return 
	 */  
	public List<GoodsInfo> getInventoriesInfos(int curPage, int pageSize) {
		StringMap<String> reqMap = new StringMap<String>();
		reqMap.put("orderBy", "inventories_number");
		reqMap.put("orderType", "DESC");
		reqMap.put("curPage", curPage+"");
		reqMap.put("pageSize", pageSize+"");
		
		return goodsInfoDao.queryByParams(reqMap);
	}
	
}
