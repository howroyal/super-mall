package com.mall.util;

/**   
 * @ClassName  PageUtil   
 * @Description 分页工具类   
 * @author 王浩  
 * @date   2014-12-26 下午05:50:15   
 *      
 */  
public class PageUtil {

	
	/**   
	 * @Title    构造第一页的html分页
	 * 如果总页数totalPage <=10 则全部显示
	 * 如果总页数大于10，
	 * 则显示当前页面的前后3页，前面显示1,2页，后面显示最后1,2页，其余打...
	 * 
	 * @Description      
	 * @param totalPage
	 * @param curPage
	 * @return 
	 * @author 王浩  
	 */  
	public static String createPageHtml(int totalPage, int curPage){
		
		String pageHTML = "";
		if (totalPage <= 10) {
			for (int i = 1; i <= totalPage; i++) {
				if(i == curPage){
					pageHTML += "<li class='active'><a href='#' class='active'>" + i + "</a></li>";
				}else {
					pageHTML += "<li><a href='#' class='pageNum'>" + i + "</a></li>";
				}
			}
			
			return pageHTML;
		}

		//特殊情况，如果curPage<=5则显示所有前面页
		if(curPage <= 5){
			for (int i = 1; i < 7; i++) {
				if(i == curPage){
					pageHTML += "<li class='active'><a href='#' class='active'>" + i +"</a></li>";
				}else {
					pageHTML += "<li><a href='#' class='pageNum'>" + i + "</a></li>";
				}
			}
			//再加上后面2页数据
			//打点数据
			pageHTML += "<li><span class='page_ellipsis'>...</span></li>";
			for (int i = 1; i >= 0; i--) {
				int thisPage = (totalPage-i);
				pageHTML += "<li><a href='#' class='pageNum'>" + thisPage + "</a></li>";
				
			}
			return pageHTML;
		} 
		if(totalPage - curPage <=5){
			//显示最前面3页数据
			for (int i = 1; i <= 2; i++) {
				pageHTML += "<li><a href='#' class='pageNum'>" + i + "</a></li>";
			}
			//打点数据
			pageHTML += "<li><span class='page_ellipsis'>...</span></li>";
			
			//显示最后面3页数据
			for (int i = 7; i >= 0; i--) {
				int thisPage = (totalPage-i);
				if(thisPage == curPage){
					pageHTML += "<li class='active'><a href='#' class='active'>" + thisPage + "</a></li>";
				}else {
					pageHTML += "<li><a href='#' class='pageNum'>" + thisPage + "</a></li>";
				}
			}
			return pageHTML;
		}
		
		//显示最前面3页数据
		for (int i = 1; i <= 2; i++) {
			pageHTML += "<li><a href='#' class='pageNum'>" + i + "</a></li>";
		}
		//打点数据
		pageHTML += "<li><span class='page_ellipsis'>...</span></li>";
		
		//显示当前页前后2页数据
		for (int i = 1; i <= 5; i++) {
			int thisPage = (i+(curPage-3));
			if(thisPage == curPage){
				pageHTML += "<li class='active'><a href='#'>" + thisPage +"</a></li>";
			}else {
				pageHTML += "<li><a href='#' class='pageNum'>" + thisPage + "</a></li>";
			}
		}
		
		//打点数据
		pageHTML += "<li><span class='page_ellipsis'>...</span></li>";
		
		//显示最后面3页数据
		for (int i = 1; i >= 0; i--) {
			int thisPage = (totalPage-i);
			pageHTML += "<li><a href='#' class='pageNum'>" + thisPage + "</a></li>";
		}
		return pageHTML;
	}
	
	public static void main(String[] args) {
		System.out.println(createPageHtml(100,10));
	}
}
