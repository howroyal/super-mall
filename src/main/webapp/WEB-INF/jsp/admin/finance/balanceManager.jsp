<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>余额管理</title>
<c:import url="../common.jsp"></c:import>
</head>
<body>
	<div class="container-fluid">
	<ol class="breadcrumb" style="margin-bottom: 0px;">
		<li class="active">余额管理</li>
		</ol>
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-3">
						<div class="input-group">
							<span class="input-group-addon"> 用户名 </span> 
							<input type="text" class="form-control" id="userId" maxlength="30" />
						</div>
						<!-- /input-group -->
					</div>
					<!-- /.col-xs-2 -->
					<div class="col-xs-2">
						<div class="input-group">
							<span class="input-group-addon"> 类别  </span> <select
								class="form-control" id="type" name="type">
								<option value="">全部</option>
								<option value="0">充值</option>
								<option value="1">支付</option>
							</select>
						</div>
					</div>
					<!-- /.col-xs-3 -->
					<div class="col-xs-2">
						<div class="input-group">
							<span class="input-group-addon"> 状态 </span> 
							<select class="form-control" id="isValid" name="isValid">
								<option value="">全部</option>
								<option value="0">有效</option>
								<option value="1">无效</option>
							</select>
						</div>
					</div>
					<!-- /.col-xs-4 -->
					<div class="col-xs-2">
						<button type="button" class="btn btn-primary pull-right"
							id="searchBtn">
							<i class="glyphicon glyphicon-search"></i>查询
						</button>
					</div>
					<!-- /.col-xs-4 -->
				</div>
			</div>
			<!-- table 插件 -->
			<c:import url="../table.jsp"></c:import>
		</div>

		<!-- 删除order -->
		<div class="modal fade" id="deleteModal" role="dialog"
			aria-labelledby="deleteModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header bg-primary">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="deleteModalLabel">删除记录</h4>
					</div>

					<div class="modal-body">
						<input type="hidden" id="removeId" /> 该操作暂时不可恢复,确认删除此记录吗?
					</div>

					<div class="modal-footer" style="text-align: center">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<i class="glyphicon glyphicon-remove"></i>取消
						</button>
						<button type="button" class="btn btn-primary" id="deleteSubmitBtn">
							<i class="glyphicon glyphicon-ok"></i>确定
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/qiao.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/admin/balanceManager.js"></script>
</body>
</html>
