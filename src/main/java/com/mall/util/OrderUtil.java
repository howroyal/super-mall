/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年4月5日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年4月5日
 *@Version:1.1.0
 */
public class OrderUtil {
	 private final static SimpleDateFormat    dateFormat  = new SimpleDateFormat("yyMMddHHmmss");

	/**
	 * 生成订单编号
	 * @Description:
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年4月5日
	 */
	public synchronized static String createOrderNo(){
		return dateFormat.format(new Date());
		//1459825805581
		//yyyyMMdd
	//	8136494210597
//		long time = System.currentTimeMillis();
//		long no = System.nanoTime();
//		System.out.println(time);
//		System.out.println(no);
//		return null;
	}
	
	@Test
	public void test(){
	//	long s = PKGenerator.getInstance().nextPK();
		System.out.println(createOrderNo());
	}
}
