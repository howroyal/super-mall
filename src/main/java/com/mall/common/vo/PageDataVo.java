package com.mall.common.vo;

import java.util.List;

/**
* @Description: 分页数据对象
* @author zb
* @date 2014-12-1 下午6:04:23
*
 */
public class PageDataVo<T> {
	/**
	 * 分页起始记录数
	 */
	private Integer start; 
	
	/**
	 * 一页显示记录数 (pageSize = Integer.MAX_VALUE时，查全部记录）
	 */
	private Integer pageSize;
	
	/**
	 * 总页数
	 */
	private Integer totalPage; 
	
	private Long totalRows;//总记录数

	/**
	 * 当前页数据
	 */
	private List<T> dataList;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}

}
