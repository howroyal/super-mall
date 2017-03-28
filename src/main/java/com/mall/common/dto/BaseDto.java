package com.mall.common.dto;

public class BaseDto {

	private int start = 0;	//起始行
	private int end = 10;	//结束行
	
	private String orderbys;	//排序字段
	private String order;		//升序/降序
	
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

	public String getOrderbys() {
		return orderbys;
	}

	public void setOrderbys(String orderbys) {
		this.orderbys = orderbys;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}


}
