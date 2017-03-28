<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="pkg_page clearfix">

	<a href="javascript:"
		class="up <c:if test="${dto.cp==1}">up_nocurrent</c:if>">上一页</a>
	<c:if test="${dto.cp != 1}">
		<a href="javascript:">1</a>
		<c:if test="${dto.cp-1 != 1}">
			<span class="pkg_page_ellipsis">...</span>
			<a href="javascript:">${dto.cp-1}</a>
		</c:if>
	</c:if>
	<a href="javascript:;" class="current">${dto.cp}</a>
	<c:if test="${dto.cp != pagenum}">
		<c:if test="${dto.cp+1 != pagenum}">
			<a href="javascript:">${dto.cp+1}</a>
			<span class="pkg_page_ellipsis">...</span>
		</c:if>
		<a href="javascript:">${pagenum}</a>
	</c:if>
	<a href="javascript:"
		class="down <c:if test="${dto.cp==pagenum}">down_nocurrent</c:if>">下一页</a>
	<!-- 
	<span class="pkg_pagevalue"> 到<input type="text" id="iptPageTxt"
		class="pkg_page_num" value=''>页<a href="javascript:"
		class="pkg_page_submit">确定</a>
	</span>
	 -->
</div>