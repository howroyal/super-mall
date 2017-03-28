package com.mall.controller.ps;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.entity.GoodsInfo;
import com.mall.common.model.CartModel;
import com.mall.common.model.CartProductModel;
import com.mall.common.service.GoodsInfoService;
import com.mall.common.vo.JsonVo;
import com.mall.config.CommonVariables;
import com.mall.controller.BaseController;
import com.mall.util.CartUtil;
import com.mall.util.CookieUtil;

/**
 * @Title: 购物车模块
 * @Description:
 * @Author:hao.wang
 * @Since:2015年12月3日
 * @Version:1.1.0
 */
@Controller
public class CartController extends BaseController{
	@Resource
	private GoodsInfoService goodsInfoService;
	
	/**
	 * 购物车首页
	 * @Description:
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	@RequestMapping("/cart")
	public ModelAndView cart(HttpServletRequest request){
		//获取购物车数据
		CartModel model = CartUtil.getCartModelFromCookie(request,goodsInfoService);
		ModelAndView view = new ModelAndView("cart","dto",model);
		return view;
	}
	
	/**
	 * 添加购物车
	 * @Description:
	 * @param dto
	 * @param count
	 * @param request
	 * @param response
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	@RequestMapping("/cart/addToCart")
	@ResponseBody
	public JsonVo<Object> addToCart(Long id, String count,HttpServletRequest request,HttpServletResponse response){
		JsonVo<Object> object = new JsonVo<Object>(false,"0",null);
		GoodsInfo info = goodsInfoService.queryById(id);
		if (info == null) {
			return object;
		}

		if (StringUtils.isEmpty(count)) {
			count = "1";
		}
		String cartStr = CartUtil.getCartStrFromCookie(request);
		Map<String, String> cartMap = CartUtil.getCartMapFromCartStr(cartStr);
		String countStr = cartMap.get(id.toString());
		if (StringUtils.isEmpty(countStr)) {
			countStr = "0 0";
		}
		String[] countArr = countStr.split(" ");
		int totalCount = Integer.valueOf(count) + Integer.valueOf(countArr[0]);
		cartMap.put(id.toString(), String.valueOf(totalCount) +" 0");
		
		CookieUtil.setCookie(request, response, CommonVariables.CARTCOOKIENAME, CartUtil.getCartStrFromCartMap(cartMap));
		
		List<CartProductModel> products = CartUtil.getCartProductsFromMap(cartMap,goodsInfoService);
		CartModel cdto = CartUtil.createCartModel(products,true);
		object = new JsonVo<Object>(true,"0",cdto);
		return object;
	}
	
	/**
	 * 添加购物车
	 * @Description:
	 * @param dto
	 * @param count
	 * @param request
	 * @param response
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	@RequestMapping("/cart/cartdata")
	@ResponseBody
	public JsonVo<Object> cartdata(HttpServletRequest request){
		JsonVo<Object> object = new JsonVo<Object>(false,"0",null);
		
		CartModel cdto = CartUtil.getCartModelFromCookie(request, goodsInfoService);
		object = new JsonVo<Object>(true,"0",cdto);
		return object;
	}
	
	/**
	 * 修改购物车内数据
	 * @Description:
	 * @param type
	 * @param productId
	 * @param count
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月3日
	 */
	@RequestMapping("/cart/update")
	@ResponseBody
	public JsonVo<Object> update(String type, String productId,boolean status,String count,HttpServletRequest request, HttpServletResponse response){
		JsonVo<Object> resultObject = new JsonVo<Object>(false,"-1",null);
		if (StringUtils.isEmpty(type)) {
			return resultObject;
		}
		
		String cartStr = CartUtil.getCartStrFromCookie(request);
		Map<String, String> cartMap = CartUtil.getCartMapFromCartStr(cartStr);

		if (cartMap.isEmpty()) {
			return resultObject;
		}
		
		if ("check".equals(type)) {
			CartUtil.doCheck(cartMap,productId,status);
		}
		if("remove".equals(type)){
			CartUtil.doRemove(cartMap,productId);
		}
		if("add".equals(type)){
			CartUtil.doAdd(cartMap,productId);
		}
		if("cut".equals(type)){
			CartUtil.doCut(cartMap,productId);
		}
		if("updateCount".equals(type)){
			CartUtil.updateCount(cartMap,productId,count,goodsInfoService);
		}
		
		cartStr = CartUtil.getCartStrFromCartMap(cartMap);
		CookieUtil.setCookie(request, response, CommonVariables.CARTCOOKIENAME, cartStr);
		
		List<CartProductModel> productDtos = CartUtil.getCartProductsFromMap(cartMap,goodsInfoService);
		
		CartModel dto = CartUtil.createCartModel(productDtos,false);
		resultObject = new JsonVo<Object>(true,"0",dto);
		return resultObject;
	}
	
	/**
	 * 订单确认前检测参数
	 * @Description:
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月8日
	 */
	@RequestMapping("/cart/check")
	@ResponseBody
	public JsonVo<Object> check(HttpServletRequest request){
		JsonVo<Object> result = new JsonVo<Object>(true,CommonVariables.SUCCCODE,null);
	
		//验证是否选中商品 -1=未选择商品
		CartModel dto = CartUtil.getCartModelFromCookie(request, goodsInfoService);
		
		if (dto == null || dto.getTotalCount() == 0) {
			return new JsonVo<Object>(false,"-1","未选择商品");
		}
		
		//验证是否库存不足 -2=库存不足
		CartProductModel cartProductDto = CartUtil.getLowStocks(dto);
		if (cartProductDto != null ) {
			return new JsonVo<Object>(false,"-2",cartProductDto);
		}
		
		//验证是否登录  -1=未登录
		String userId = getUserIdFormCookie(request);
		if (StringUtils.isEmpty(userId)) {
			return new JsonVo<Object>(false,"-3",null);
		}
			//验证积分 -3=积分不够
//		String memid = null;
//		try {
//			memid = DesErp.decrypt(memidMd5);
//		} catch (Exception e) {
//			logger.error("com.zhaogang.zhineng.ps.front.controller.mall.CartControler.check ", e);
//		}
//			
//		int userScore = 0;
//		ScoreAccountModel model = ScoreAccountUtil.getByUserId(util,memid);
//		if (model != null) {
//			if (model.getAvailableScore() != null) {
//				userScore = model.getAvailableScore();
//			}
//		}
//     	if (userScore < dto.getTotalScoreSale()) {
//     		return new ResultObject("-4","积分不够",null);
//		}
		
		return result;
	}
	
	/**
	 * 获取用户积分 让为了速度更快
	 * @Description:
	 * @param request
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月17日
	 */
	@RequestMapping("/cart/userscore")
	@ResponseBody
	public JsonVo<Object> getUserScore(HttpServletRequest request){
		return new JsonVo<Object>(true,"0",1030);
	}
}
