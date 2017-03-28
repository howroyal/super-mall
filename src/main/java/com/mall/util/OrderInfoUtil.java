package com.mall.util;

public class OrderInfoUtil {
	
	/**   
	 * 生成订单号
	 * @Title    TODO
	 * @Description    TODO  
	 * @author 王浩  
	 * @Since 2015-10-19
	 * @return 
	 */  
	public static String createOrderNo(){
		long time = System.currentTimeMillis();
		return String.valueOf(time);
	}
}
