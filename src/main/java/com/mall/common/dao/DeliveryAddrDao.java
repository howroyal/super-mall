/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年3月28日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dao;

import java.util.List;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.mall.common.entity.DeliveryAddress;

/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年3月28日
 *@Version:1.1.0
 */
@Repository
public class DeliveryAddrDao extends JdbcDaoSupport{

	/**
	 * @Description:
	 * @param bean
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月28日
	 */
	public void add(DeliveryAddress bean) {
		String sql = "INSERT INTO delivery_address "
				+"(member_id,prov_id,city_id,area_id,address_detail,"
				+ "contact_name,contact_phone,contact_tel,"
				+ "post_code,is_default,valid) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {bean.getMemberId(),bean.getProvId(),bean.getCityId(),
						  bean.getAreaId(),bean.getAddressDetail(),bean.getContactName(),
						  bean.getContactPhone(),bean.getContactTel(),
						  bean.getPostCode(),bean.getIsDefault(),bean.getValid()
						};
		getJdbcTemplate().update(sql,params);
	}

	/**
	 * @Description:
	 * @param bean
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月28日
	 */
	public void update(DeliveryAddress bean) {

		String sql = "UPDATE delivery_address SET prov_id = ?,city_id = ?,area_id = ?,address_detail = ?,"
				+ "contact_name = ?,contact_phone = ?,contact_tel = ?,"
				+ "post_code = ?,is_default = ?,valid = ? WHERE id = ? AND member_id = ?";
		Object[] params = {bean.getProvId(),bean.getCityId(),bean.getAreaId(),bean.getAddressDetail(),
							bean.getContactName(),bean.getContactPhone(),bean.getContactTel(),
							bean.getPostCode(),bean.getIsDefault(),bean.getValid(),bean.getId(),bean.getMemberId()};
		getJdbcTemplate().update(sql,params);
	}

	/**
	 * @Description:
	 * @param id
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月28日
	 */
	@SuppressWarnings("unchecked")
	public DeliveryAddress queryById(Long id) {

		DeliveryAddress info = null;
		if (id == null) {
			return null;
		}
		String sql = "SELECT * FROM delivery_address WHERE id = ?";
		Object[] params = {id};
		
		try {
			info = getJdbcTemplate().queryForObject(sql,params,new EntityMapper(DeliveryAddress.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * @Description:
	 * @param memberId
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月28日
	 */
	@SuppressWarnings("unchecked")
	public List<DeliveryAddress> queryBy(String memberId) {
		if (memberId == null) {
			return null;
		}
		StringBuffer sql = new StringBuffer("SELECT * FROM delivery_address WHERE member_id = ? AND valid = 0 ORDER BY is_default DESC");
		Object[] params = {memberId};
		return getJdbcTemplate().query(sql.toString(),params, new EntityMapper(DeliveryAddress.class));
	}

	/**
	 * @Description:
	 * @param memberId
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年3月28日
	 */
	public long queryCountBy(String memberId) {
		if (memberId == null) {
			return 0;
		}
		StringBuffer sql = new StringBuffer("SELECT COUNT(id) FROM delivery_address WHERE member_id = ? AND valid = 0");
		Object[] params = {memberId};
		return getJdbcTemplate().queryForLong(sql.toString(),params);
	}

	/**
	 * @Description:
	 * @param id
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年4月1日
	 */
	public void remove(Long id) {
		String sql = "UPDATE delivery_address SET valid = 1 WHERE id = ?";
		Object[] params = {id};
		getJdbcTemplate().update(sql,params);
	}

}
