package com.mall.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mall.common.entity.GoodsInfo;
import com.mall.common.service.CategoryInfoService;
import com.mall.common.vo.GoodsInfoVO;

public class GoodsInfoUtil {

	/**
	 * @Description: domain对象转换为vo对象
	 * @param categoryInfoService
	 * @param infos
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年5月3日
	 */
	public static List<GoodsInfoVO> infoToVo(CategoryInfoService categoryInfoService,List<GoodsInfo> infos){
		List<GoodsInfoVO> vos = new ArrayList<GoodsInfoVO>();
		if (infos == null || infos.isEmpty()) {
			return vos;
		}
		
		for (GoodsInfo info:infos) {
			GoodsInfoVO vo = new GoodsInfoVO();
			vo.setId(info.getId());
			vo.setName(info.getName());
			vo.setMarketPrice(info.getMarketPrice());
			vo.setPrice(info.getPrice());
			vo.setSales(info.getSales());
			vo.setStock(info.getStock());
			vo.setClicks(info.getClicks());
			vo.setCode(info.getCode());
			vo.setMainImg(info.getMainImg());
			vo.setImgUrl(getImgUrl(info.getPreImg()));
			vo.setCategory(categoryInfoService.queryById(info.getCategoryId()));
			vo.setDescription(info.getDescription());
			vos.add(vo);
		}
		return vos;
	}

	/**
	 * @Description: domain对象转换为vo对象
	 * @param categoryInfoService
	 * @param goodsInfo
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年5月3日
	 */
	public static GoodsInfoVO infoToVo(CategoryInfoService categoryInfoService,
			GoodsInfo goodsInfo) {
		List<GoodsInfo> infos = new ArrayList<GoodsInfo>();
		infos.add(goodsInfo);
		List<GoodsInfoVO> vos = infoToVo(categoryInfoService, infos);
		if (vos == null || vos.isEmpty()) {
			return null;
		}
		return vos.get(0);
	}
	
	/**
	 * @Description: 抽取图片存入list中
	 * @param str
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年5月3日
	 */
	private static List<String> getImgUrl(String str){
		List<String> imgs = new ArrayList<String>();
		Pattern pattern = Pattern.compile("src=\"(.*?)\"");
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			imgs.add(matcher.group(1));
		}
		return imgs;
	}
	
	public static void main(String[] args) {
		getImgUrl("<p><img src=\"https://img.alicdn.com/imgextra/i1/12974155/TB2mj9AeFXXXXapXpXXXXXXXXXX_!!12974155.jpg\"/></p><p><img src=\"https://img.alicdn.com/imgextra/i1/12974155/TB2mj9AeFXXXXapXpXXXXXXXXXX_!!12974155.jpg\"/></p>");
	}
}
