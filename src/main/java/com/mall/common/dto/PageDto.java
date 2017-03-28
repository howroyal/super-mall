/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年5月26日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.dto;


/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年5月26日
 *@Version:1.1.0
 */
public class PageDto {

	private int start;
	private int end;
	//private int pageIndex;
	//private int pageSize;
	private int totalRow;
	private int pageCount;

	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
}
