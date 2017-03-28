/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2015年12月9日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.mall.common.entity.GoodsInfo;
import com.mall.common.entity.OrderDetailInfo;
import com.mall.common.entity.OrderInfo;
import com.mall.common.model.CartModel;
import com.mall.common.model.CartProductModel;
import com.mall.common.model.DeliveryAddressModel;
import com.mall.common.service.GoodsInfoService;

/**
 *@Title:
 * 购物车工具类
 *@Description:
 *@Author:hao.wang
 *@Since:2015年12月9日
 *@Version:1.1.0
 */
public class CartUtil {
	
	/**
	 * 购物车cookie名
	 */
	private static final String CARTCOOKIENAME = "cart-item";
	
	/**
	 * @Description:
	 * @param goodsInfoService
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月25日
	 */
	public static CartModel getCartModelFromCookie(HttpServletRequest request,GoodsInfoService productMallService) {
		String cartStr = getCartStrFromCookie(request);
		Map<String, String> cartMap = getCartMapFromCartStr(cartStr);
		List<CartProductModel> products = getCartProductsFromMap(cartMap,productMallService);
		
		return createCartModel(products,false);
	}
	
	/**
	 * 从cookie内获取原始数据
	 * @Description:
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	public static String getCartStrFromCookie(HttpServletRequest request){
		String cartStr = CookieUtil.getCookieValue(request, CARTCOOKIENAME);
		return cartStr;
	}
	
	/**
	 * 将购物车str数据转换为map数据
	 * @Description:
	 * @param cartStr
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	public static Map<String, String> getCartMapFromCartStr(String cartStr){
		Map<String, String> cartMap = new LinkedHashMap<String, String>();
		
		if (StringUtils.isNotEmpty(cartStr)) {
			String[] cartArr = cartStr.split("\\|");
			for (int i = 0; i < cartArr.length; i++) {
				String[] tempArr = cartArr[i].split("\\.");
				if (tempArr.length == 2) {
					cartMap.put(tempArr[0], tempArr[1]);
				}
			}
		}
		return cartMap;
	}
	
	/**
	 * 获取购物车结构化数据
	 * @Description:
	 * @param cartMap
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	public static List<CartProductModel> getCartProductsFromMap(Map<String, String> cartMap,GoodsInfoService productMallService){
		List<CartProductModel> malls = new ArrayList<CartProductModel>();
		Set<String> keySet = cartMap.keySet();
		for(String key : keySet){
			GoodsInfo goodsInfo = productMallService.queryById(Long.parseLong(key));
			if (goodsInfo != null) {
				CartProductModel mall = new CartProductModel();
				mall.setProductId(goodsInfo.getId());
				mall.setProductCode("1100312");
				mall.setProductName(goodsInfo.getName());
				mall.setProductStock(goodsInfo.getStock());
				mall.setProductPicUrl(goodsInfo.getMainImg());
				mall.setProductScoreSale(goodsInfo.getPrice());
			//	mall.setSupplierId(dto.getSupplierId());
			//	mall.setSupplier(dto.getSupplier());
			//	mall.setUp(dto.getUp());
				mall.setCategoryId(goodsInfo.getCategoryId());
				String count  = cartMap.get(key);
				boolean isChecked = true;
				if (StringUtils.isNotEmpty(count)) {
					String[] countArr = count.split(" ");
					if (countArr.length == 2) {
						count = countArr[0];
						isChecked = countArr[1].equals("0");
					}
				}
				mall.setProductCount(Integer.parseInt(count));
				mall.setChecked(isChecked);
				malls.add(mall);
			}
			
		}
		return malls;
	}
	
	/**
	 * 构造购物车页面对象
	 * @Description:
	 * @param products
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	public static CartModel createCartModel(List<CartProductModel> products,boolean isAll){
		CartModel dto = new CartModel();
		int totalCount = 0;
		int totalScoreSale = 0;
		boolean isAllChecked = true;
		for(CartProductModel product : products){

			if (product.isChecked()) {
				totalCount += product.getProductCount();
				totalScoreSale += product.getProductCount() * product.getProductScoreSale();
			}else {
				if (isAll) {
					totalCount += product.getProductCount();
				}
				isAllChecked = false;
			}
		
		}
		dto.setTotalCount(totalCount);
		dto.setTotalScoreSale(totalScoreSale);
		dto.setProducts(products);
		dto.setAllChecked(isAllChecked);
		return dto;
	}
	
	/**
	 * 购物车内商品选择操作
	 * @Description:
	 * @param cartMap
	 * @param productId
	 * @param isChecked
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	public static void doCheck(Map<String, String> cartMap,String productId,boolean isChecked){
		Set<String> keySet = cartMap.keySet();
		if (StringUtils.isNotEmpty(productId)) {
			doCheckSingle(cartMap, productId, isChecked);
		}else {
			for(String key : keySet){
				doCheckSingle(cartMap, key, isChecked);
			}
		}
	}
	
	/** 
	 * 单个商品选择操作
	 * @Description:
	 * @param cartMap
	 * @param productId
	 * @param isChecked
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	public static void doCheckSingle(Map<String, String> cartMap,String productId,boolean isChecked){
		String count = cartMap.get(productId);
		String[] countArr = count.split(" ");
		if (countArr.length == 2) {
			count = countArr[0] + ' ' + (isChecked?"0":"1");
			cartMap.put(productId, count);
		}
	}
	
	/**
	 * @Description: 删除购物车中的商品
	 * @param cartMap
	 * @param productId
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	public static void doRemove(Map<String, String> cartMap, String productId) {
		if(cartMap == null || StringUtils.isEmpty(productId)){
			return;
		}
		cartMap.remove(productId);
	}
	
	/** 购物车内的指定商品+1
	 * @Description:
	 * @param cartMap
	 * @param productId
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	public static void doAdd(Map<String, String> cartMap, String productId) {
		if(cartMap == null || StringUtils.isEmpty(productId)){
			return;
		}
		String count = cartMap.get(productId);
		if(StringUtils.isEmpty(count)){
			return;
		}
		String[] countArr = count.split(" ");
		if (countArr.length == 2) {
			count = countArr[0];
			cartMap.put(productId, (Integer.parseInt(count) + 1) + " 0");
		}
	}
	
	/** 购物车内的指定商品-1
	 * @Description:
	 * @param cartMap
	 * @param productId
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	public static void doCut(Map<String, String> cartMap, String productId) {
		if(cartMap == null || StringUtils.isEmpty(productId)){
			return;
		}
		String count = cartMap.get(productId);
		if(StringUtils.isEmpty(count)){
			return;
		}
		String[] countArr = count.split(" ");
		if (countArr.length == 2) {
			count = countArr[0];
			int countInt = Integer.parseInt(count) - 1;
			if (countInt <= 1) {
				countInt = 1;
			}
			cartMap.put(productId, countInt + " 0");
		}
	}
	
	/**
	 * 将map数据转换为str数据
	 * @Description:
	 * @param cartMap
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	public static String getCartStrFromCartMap(Map<String,String> cartMap){
		String cartStr = cartMap.toString();
		cartStr = cartStr.replace("{", "").replace("}", "").replace(", ", "|").replace("=", ".");
		return cartStr;
	}
	
	/**
	 * 更新购物车内商品数量
	 * @Description:
	 * @param cartMap
	 * @param productId
	 * @param count
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月8日
	 */
	public static void updateCount(Map<String, String> cartMap, String productId, String updateCount,GoodsInfoService productMallService) {
		if(cartMap == null || StringUtils.isEmpty(productId)){
			return;
		}
		//判断updateCount
		if (StringUtils.isEmpty(updateCount)) {
			return;
		}
		if (!StringUtils.isNumeric(updateCount)) {
			return;
		}
		int updateCountInt = Integer.parseInt(updateCount);
		if (updateCountInt <= 0) {
			return;
		}
		//取到商品信息
		GoodsInfo info = productMallService.queryById(Long.parseLong(productId));
		if (info == null) {
			return;
		}
		if(info.getStock() <= updateCountInt){
			updateCountInt = info.getStock();
		}
		
		cartMap.put(productId, updateCountInt + " 0");
	}
	
	/**
	 * 获取库存不足的商品
	 * @Description:
	 * @param dto
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月9日
	 */
	public static CartProductModel getLowStocks(CartModel dto) {
		CartProductModel productDto = null;
		List<CartProductModel> products = dto.getProducts(); 
		for(CartProductModel product : products){
			if (product.isChecked()) {
				int stok = product.getProductStock();
				int count = product.getProductCount();
				if (stok <= 0 || (stok<count)) {
					productDto = product;
					break;
				}
			}
		}
		return productDto;
	}

	/**
	 * @Description:
	 * @param memId
	 * @param remark
	 * @param cartDto
	 * @param addressModel
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年4月1日
	 */
	public static OrderInfo createOrderInfo(String userId,int paymentType, String remark, CartModel cartDto,
			DeliveryAddressModel addressModel) {
		OrderInfo info = new OrderInfo();
		info.setOrderNo(OrderInfoUtil.createOrderNo());
		info.setAmountPrice(cartDto.getTotalScoreSale());
		info.setUserId(userId);
		info.setMessage(remark);
		info.setPhone(addressModel.getContactPhone());
		info.setName(addressModel.getContactName());
		info.setAddress(addressModel.getProvName()+addressModel.getCityName()+addressModel.getAreaName() + addressModel.getAddressDetail());
		info.setPaymentType(paymentType);
		//生成订单详细信息
		List<OrderDetailInfo> detailInfos = new ArrayList<OrderDetailInfo>();
		List<CartProductModel> products = cartDto.getProducts();
		for (CartProductModel model : products) {
			OrderDetailInfo dinfo = new OrderDetailInfo();
			dinfo.setAmount(model.getProductCount());
			dinfo.setGoodsId(String.valueOf(model.getProductId()));
			dinfo.setGoodsName(model.getProductName());
			dinfo.setMainImgUrl(model.getProductPicUrl());
			dinfo.setPrice(model.getProductScoreSale());
			dinfo.setTotalPrice(model.getProductCount() * model.getProductScoreSale());
			detailInfos.add(dinfo);
		}
		
		info.setDetailInfos(detailInfos);
		return info;
	}
}
