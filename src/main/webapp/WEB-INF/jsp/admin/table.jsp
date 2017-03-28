<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- table 插件  CSS 文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/common/css/bootstrap-3.3.5/bootstrap-table.min.css?v=e46bad163d" />
<!-- 自定义 CSS 文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/common/css/backend.css?v=b4569dad2b" />
<!-- table 插件 js 文件 -->
<script src="${pageContext.request.contextPath}/static/common/js/bootstrap-3.3.5/bootstrap-table.min.js?v=b4666afe4e"></script>
<!-- table 插件 locale 国际化 js 文件 -->
<script src="${pageContext.request.contextPath}/static/common/js/bootstrap-3.3.5/bootstrap-table-zh-CN.min.js?v=7426a14aa4"></script>
<table id="table_table_1" class="table table-hover table-condensed table-striped"
	data-locale="zh-CN"
	data-pagination="true" 
	data-side-pagination="server" 
	data-click-to-select="true" 
	data-smart-display="true" 
	data-pagination-first-text="首页" 
	data-pagination-pre-text="上一页" 
	data-pagination-next-text="下一页" 
	data-pagination-last-text="尾页" 
	data-page-number="1" 
	data-page-size="10" 
	data-page-list="[10,20,50,100]">
</table>
<script type="text/javascript">
	/*
	 * onSort，Fires when user sort a column
	 * name: the sort column field name
	 * order: the sort column order
	 */
	$('.table.table-striped').on('sort.bs.table', function(e, name, order) {
		//设置从起始页开始
		$(this).bootstrapTable('getOptions').pageNumber = 1;
	});
</script>
