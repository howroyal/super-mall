/**
 * @Version:1.0
 * @User:hao.wang
 * @Date:2016年7月12日
 * @Copyright:Copyright (c) 2015 - 2100
 * @Company:http://www.zhaogang.com/
 */
package com.mall.common.vo;


/**
 *@Title:
 *@Description:
 *@Author:hao.wang
 *@Since:2016年7月12日
 *@Version:1.1.0
 */
public class CommentVO {

	//全部评论
	private int commentUserNum;
	//好评
	private int goodCommentCount;
	//好评率
	private float goodCommentRate = 100;
	//中评
	private int normalCommentCount;
	//中评率
	private float normalCommentRate;
	//差评
	private int badCommentCount;
	//差评率
	private float badCommentRate;
	//追评
	private int addCommentCount;
	//晒单
	private int commentPicCount;
	//回复
	private int replyCount;
	
	private String[] impression = {"价格公道","质量不错"};
	
	public int getCommentUserNum() {
		return commentUserNum;
	}
	public void setCommentUserNum(int commentUserNum) {
		this.commentUserNum = commentUserNum;
	}
	public int getGoodCommentCount() {
		return goodCommentCount;
	}
	public void setGoodCommentCount(int goodCommentCount) {
		this.goodCommentCount = goodCommentCount;
	}
	public float getGoodCommentRate() {
		return goodCommentRate;
	}
	public void setGoodCommentRate(float goodCommentRate) {
		this.goodCommentRate = goodCommentRate;
	}
	public int getNormalCommentCount() {
		return normalCommentCount;
	}
	public void setNormalCommentCount(int normalCommentCount) {
		this.normalCommentCount = normalCommentCount;
	}
	public float getNormalCommentRate() {
		return normalCommentRate;
	}
	public void setNormalCommentRate(float normalCommentRate) {
		this.normalCommentRate = normalCommentRate;
	}
	public int getBadCommentCount() {
		return badCommentCount;
	}
	public void setBadCommentCount(int badCommentCount) {
		this.badCommentCount = badCommentCount;
	}
	public float getBadCommentRate() {
		return badCommentRate;
	}
	public void setBadCommentRate(float badCommentRate) {
		this.badCommentRate = badCommentRate;
	}
	public int getAddCommentCount() {
		return addCommentCount;
	}
	public void setAddCommentCount(int addCommentCount) {
		this.addCommentCount = addCommentCount;
	}
	public int getCommentPicCount() {
		return commentPicCount;
	}
	public void setCommentPicCount(int commentPicCount) {
		this.commentPicCount = commentPicCount;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public String[] getImpression() {
		return impression;
	}
	public void setImpression(String[] impression) {
		this.impression = impression;
	}
	
}
