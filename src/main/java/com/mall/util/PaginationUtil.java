package com.mall.util;

public class PaginationUtil {

	public static String createPageHTML(int curPage,int pageSize,long count){
		String pageHTML = "";
		
		int totalPage = ((int)count-1)/pageSize + 1;

		if(totalPage < 2){
			return pageHTML;
		}
		//上一页
		String pre = "<li class='pre'><a href='#'>&laquo;</a></li>";
		if (curPage == 1) {
			pre = "<li class='disabled'><a href='#'>&laquo;</a></li>";
		}
		
		//数字页 只存5页的数据
		String num = "";
		//总页数少于5时 
		if (totalPage <= 5 || curPage <= 3) {
			for (int i = 1; i <= totalPage; i++) {
				if (i == curPage) {
					num += "<li class='active'><a href='#'>" + i + "</a></li>";
				}else {
					num += "<li class='num'><a href='#'>" + i + "</a></li>";
				}
			}
		}else {
			//当前页前2页，当前页后2页
			for (int i = curPage-2; i <= curPage+2; i++) {
				if (i == curPage) {
					num += "<li class='active'><a href='#'>" + i + "</a></li>";
				}else {
					num += "<li class='num'><a href='#'>" + i + "</a></li>";
				}
			}
		}
		
		String next = "";
		if (curPage >= totalPage) {
			next = "<li class='disabled'><a href='#'>&raquo;</a></li>";
		}else {
			next = "<li class='next'><a href='#'>&raquo;</a></li>";
		}
		
		pageHTML = pre + num + next;
		
		return pageHTML;
	}
}
