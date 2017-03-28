/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月25日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.controller.ps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.dto.OrderSubmitDto;
import com.mall.common.entity.OrderDetailInfo;
import com.mall.common.entity.OrderInfo;
import com.mall.common.model.CartModel;
import com.mall.common.model.CartProductModel;
import com.mall.common.model.DeliveryAddressModel;
import com.mall.common.service.DeliveryAddrService;
import com.mall.common.service.GoodsInfoService;
import com.mall.common.service.OrderService;
import com.mall.common.vo.JsonVo;
import com.mall.config.CommonVariables;
import com.mall.controller.BaseController;
import com.mall.util.CartUtil;
import com.mall.util.CookieUtil;

/**
 *@Title: 订单页面
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月25日
 *@Version:1.1.0
 */
@Controller
public class OrderController extends BaseController{
	@Resource
	private GoodsInfoService goodsInfoService;
	@Resource
	private DeliveryAddrService addrService;
	@Resource
	private OrderService orderservice;
	
	/**
	 * 购物车确定页面
	 * @Description:
	 * @param productId
	 * @param count
	 * @param type
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月16日
	 */
	@RequestMapping(value="/order",method=RequestMethod.POST)
	public ModelAndView order(String productId,String count,HttpServletRequest request){
		boolean isFromCart = false;
		//获取用户收货地址
		List<DeliveryAddressModel> addrs = null;
		try {
			//获取用户信息
			String memId = getUserIdFormCookie(request);
			
			if (StringUtils.isNotEmpty(memId)) {
				addrs = addrService.beanToModel(addrService.queryBy(memId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (addrs == null) {
				addrs = new ArrayList<DeliveryAddressModel>();
			}
		}
		
		//不为空则表示不取购物车数据
		CartModel cartModel = null;
		String countT = count;
		if (StringUtils.isNotEmpty(productId)) {
			//现在只支持单个商品的多件购买 
			if(StringUtils.isEmpty(countT) || !StringUtils.isNumeric(countT)){
				countT = "1";
			}
			String cartStr = productId + "." + countT + " 0";
			Map<String, String> cartMap = CartUtil.getCartMapFromCartStr(cartStr);
			List<CartProductModel> cartProductDtos = CartUtil.getCartProductsFromMap(cartMap, goodsInfoService);
			cartModel = CartUtil.createCartModel(cartProductDtos,false);
		}else {
			isFromCart = true;
			//从cookie中取数据
			cartModel = CartUtil.getCartModelFromCookie(request, goodsInfoService);
		}
		
//		//生成包裹信息
//		List<PackageModel> packageModels = CartUtil.createPackageFromCart(cartDto);
//		//是否包含第三方供应商 true=包含 false= 不包含
//		boolean isContainOther = CartUtil.isContainOther(cartDto);
//		int totalCount = 0;
//		int totalScore = 0;
		String productstr = "";//商品id与供应商
		if (CollectionUtils.isNotEmpty(cartModel.getProducts())) {
			for(CartProductModel model : cartModel.getProducts()){
				if (model.isChecked()) {
					productstr += model.getProductId() + "." + model.getProductCount() + " 0|";
				}
			}
		}
		
		ModelAndView view = new ModelAndView("order");
		view.addObject("addrs",addrs);
		view.addObject("productstr",productstr);
		view.addObject("isFromCart",isFromCart);//是否从购物车中来
		view.addObject("cartModel",cartModel);
		return view;
	}
	
	@RequestMapping(value="/order/checkOrderSubmit",method=RequestMethod.POST)
	@ResponseBody
	public JsonVo<Object> checkOrderSubmit(){
		return new JsonVo<Object>(true,"0",null);
	}
	
	/**
	 * 提交订单
	 * @Description:
	 * @param dto
	 * @param request
	 * @param response
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年4月5日
	 */
	@RequestMapping(value="/order/orderSubmit",method=RequestMethod.POST)
	public ModelAndView orderSubmit(OrderSubmitDto dto,HttpServletRequest request,HttpServletResponse response){

		ModelAndView view = new ModelAndView("order_succ");
		String orderCode = null;
		int score = 10234;
		//获取用户信息
		String memId = getUserIdFormCookie(request);
		try{
			int paymentType = 0;
			if(StringUtils.isNumeric(dto.getWay())){
				paymentType = Integer.valueOf(dto.getWay());
			}
			
			String addrId = dto.getAddrId();
			String products = dto.getProducts();
			String remark = dto.getRemark();
			
			boolean fromCart = dto.isFromCart();
			
			Map<String, String> cartMap = CartUtil.getCartMapFromCartStr(products);
			List<CartProductModel> cartProductDtos = CartUtil.getCartProductsFromMap(cartMap, goodsInfoService);
			CartModel cartDto = CartUtil.createCartModel(cartProductDtos,true);
			//获取用户收货地址信息
			 DeliveryAddressModel addressModel = addrService.beanToModel(addrService.queryById(Long.parseLong(addrId)));
			
			OrderInfo orderInfo = CartUtil.createOrderInfo(memId,paymentType,remark,cartDto,addressModel);
			
			String cookieStr = CartUtil.getCartStrFromCookie(request);
			Map<String, String> cookieMap = CartUtil.getCartMapFromCartStr(cookieStr);
			
			//调用积分接口
			if (orderInfo != null) {
				//下单成功删除购物车数据
				//SOA调用
				orderCode = orderservice.save(orderInfo);
				if (fromCart) {
					//购物完成后删除购物车数据
					for(OrderDetailInfo orderdetail : orderInfo.getDetailInfos()){
						if (fromCart && cookieMap != null) {
							cookieMap.remove(orderdetail.getGoodsId()+"");
							CookieUtil.setCookie(request, response, CommonVariables.CARTCOOKIENAME, CartUtil.getCartStrFromCartMap(cookieMap));
						}
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		view.addObject("orderCode", orderCode);
		//view.addObject("errors", errors);
		view.addObject("score", score);
		return view;
	}
}
