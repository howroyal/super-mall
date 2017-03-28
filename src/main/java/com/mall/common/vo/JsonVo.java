package com.mall.common.vo;

public class JsonVo<T> {

	private Object status;
	private String code;
	private T data;
	
	public JsonVo(){
		
	}
	
	public JsonVo(Object status, String code, T data){
		this.status = status;
		this.code = code;
		this.data = data;
	}
	public Object isStatus() {
		return status;
	}
	public void setStatus(Object status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
