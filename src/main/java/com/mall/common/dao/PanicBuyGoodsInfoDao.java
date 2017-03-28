/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2015年12月30日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.mall.common.entity.PanicBuyGoodsInfo;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2015年12月30日
 *@Version:1.1.0
 */
@Repository
public class PanicBuyGoodsInfoDao extends JdbcDaoSupport{

	/**
	 * 获取数据库抢购信息
	 * @Description:
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2015年12月30日
	 */
	public List<PanicBuyGoodsInfo> getInfos() {
		String sql = "SELECT * FROM panic_buy_goods";
		return getJdbcTemplate().query(sql,new EntityMapper(PanicBuyGoodsInfo.class));
	}
}
