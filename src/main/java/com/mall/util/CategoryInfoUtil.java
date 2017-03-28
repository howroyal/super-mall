package com.mall.util;

import java.util.List;

import com.mall.common.entity.CategoryInfo;
import com.mall.common.service.CategoryInfoService;

public class CategoryInfoUtil {


	public static void getParentInfo(CategoryInfoService categoryInfoService, Long categoryId,List<CategoryInfo> categoryInfos){
		if (categoryId == null) {
			return;
		}
		CategoryInfo info = categoryInfoService.queryById(categoryId);
		if (info != null) {
			categoryInfos.add(info);
			Long pId =  info.getParentId();
			if (pId != null) {
				getParentInfo(categoryInfoService,pId,categoryInfos);
			}
		}
	}
}
