/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月3日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mall.common.dto.BannerInfoDto;
import com.mall.common.entity.BannerInfo;
import com.mall.common.service.BannerInfoService;

/**
 *@Title:积分商城首页banner管理
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月3日
 *@Version:1.1.0
 */
@Controller
@RequestMapping("bannerManager")
public class BannerManagerController {

	private int deleteState = 2;
	private int onlineState = 0;
	private int maxSize = 10000;
	
	@Resource
	private BannerInfoService service;
	
	
	/**
	 * 进入页面
	 * @Description:
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	@RequestMapping("index")
	public String index(){
		return "admin/cms/bannerManager";
	}
	
	/**
	 * 取得分页记录数
	 * @Description:
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	@RequestMapping("infos")
	@ResponseBody
	public Map<String, Object> getInfos(BannerInfoDto dto){
		String bannerName = dto.getBannerName();
		if (bannerName != null) {
			dto.setBannerName(bannerName);
		}
		List<BannerInfo> infos = service.query(dto);
		long total = service.queryCount(dto);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", infos);
		result.put("success", true);
		return result;
	}
	
	/**
	 * 新增banner信息
	 * @Description:
	 * @param bannerPic
	 * @param info
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	@RequestMapping("add")
	@ResponseBody
	public  Map<String, Object> add(BannerInfo info){
		
		boolean succ = false;
		try {
			service.add(info);
			succ = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", succ);
		return result;
	}
	
	/**
	 * 编辑banner信息
	 * @Description:
	 * @param editBannerPic
	 * @param info
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	@RequestMapping("edit")
	@ResponseBody
	public  Map<String, Object> edit(BannerInfo info){
		boolean succ = false;
		try {
			service.update(info);
			succ = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", succ);
		return result;
	}
	
	/**
	 * 删除banner信息
	 * @Description:
	 * @param pkid
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	@RequestMapping("remove")
	@ResponseBody
	public  Map<String, Object> remove(Long id){
		if (id != null) {
			BannerInfo info = service.query(id);
			if (info != null) {
				info.setState(deleteState);
				service.update(info);
			}
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", "true");
		return result;
	}
	
	/**
	 * 预览banner信息
	 * @Description:
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月9日
	 */
	@RequestMapping("preview")
	@ResponseBody
	public Map<String, Object> preview(){
		BannerInfoDto dto = new BannerInfoDto();
		dto.setEnd(maxSize);
		dto.setState(onlineState);
		List<BannerInfo> infos = service.query(dto);
		long total = service.queryCount(dto);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", infos); 
		result.put("success", true);
		return result;
	}
}
