/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月4日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.common.dao.BannerInfoDao;
import com.mall.common.dto.BannerInfoDto;
import com.mall.common.entity.BannerInfo;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月4日
 *@Version:1.1.0
 */
@Service("bannerInfoService")
public class BannerInfoService{

	@Resource
	private BannerInfoDao dao;
	
	public Long add(BannerInfo info) {
		return dao.add(info);
	}

	public void update(BannerInfo info) {
		dao.update(info);
	}

	public BannerInfo query(Long id) {
		return dao.queryById(id);
	}

	public List<BannerInfo> query(BannerInfoDto dto) {
		return dao.query(dto);
	}

	public long queryCount(BannerInfoDto dto) {
		return dao.queryCount(dto);
	}

}
