package com.mall.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.internal.StringMap;
import com.mall.common.dao.CategoryInfoDao;
import com.mall.common.entity.CategoryInfo;
import com.mall.common.vo.CategoryTree;

@Service
public class CategoryInfoService {
	
	@Resource
	private CategoryInfoDao categoryInfoDao;
	public void addCategory(CategoryInfo bean){
		categoryInfoDao.add(bean);
	}
	
	public List<CategoryInfo> query(int curPage,int pageSize) {
		return	categoryInfoDao.query(curPage,pageSize);
	}
	
	public void delete(Long id){
		categoryInfoDao.delete(id);
	}
	
	public void update(CategoryInfo info){
		categoryInfoDao.update(info);
	}

	public Long queryCount() {
		return categoryInfoDao.queryCount();
	}

	/**   
	 * 生成菜单树
	 * @author 王浩  
	 * @Since 2015-8-10
	 * @return 
	 */  
	public List<CategoryTree> createTree() {
		List<CategoryTree> trees = new ArrayList<CategoryTree>();
		StringMap<String> map = new StringMap<String>();
		map.put("isParentIdEmpty", "true");
		//得到第一级数据
		List<CategoryInfo> infos = categoryInfoDao.queryTop();
		for (CategoryInfo info : infos) {
			CategoryTree topTree = new CategoryTree(info);
			topTree.setChildren(createChildren(topTree.getId()));
			trees.add(topTree);
		}
		
		return trees;
	}
	
	public List<CategoryTree> createChildren(Long pid){
		List<CategoryTree> trees = new ArrayList<CategoryTree>();
		List<CategoryInfo> infos = categoryInfoDao.queryByParentId(pid);
		for (CategoryInfo info : infos) {
			CategoryTree tree = new CategoryTree(info);
			tree.setChildren(createChildren(tree.getId()));
			trees.add(tree);
		}
		
		return trees;
	}

	public CategoryInfo queryById(Long id) {
		if (id == null) {
			return null;
		}
		
		return categoryInfoDao.queryById(id);
	}

	public List<CategoryInfo> queryByParentId(Long parentId) {
		return categoryInfoDao.queryByParentId(parentId);
	}

	public List<CategoryInfo> findAllBands() throws Exception{
		StringMap<String> reqMap = new StringMap<String>();
		reqMap.put("isBand", "0");
		return categoryInfoDao.queryByParams(reqMap);
	}
}
