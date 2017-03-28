<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>商城首页Banner管理</title>
	<c:import url="../common.jsp"></c:import>
</head>
<body>
	<div class="container-fluid">
		<ol class="breadcrumb">
		  <li>商城CMS系统</li>
		  <li class="active">商城首页Banner管理</li>
		</ol>
		<div class="panel panel-default">
		  <div class="panel-body">
		    <div class="row">
		      <div class="col-xs-4">
			    <div class="input-group">
			      <span class="input-group-addon">
			        	名称
			      </span>
			      <input type="text" class="form-control" id="bannerName" maxlength="30"/>
			    </div><!-- /input-group -->
			  </div><!-- /.col-xs-4 -->
			  <div class="col-xs-4">
			    <div class="input-group">
			      <span class="input-group-addon">
			        	状态:
			      </span>
			     <select class="form-control" id="state" name="state">
			     <option value="">全部</option>
			     <option value="0">已上线</option>
			     <option value="1">已下线</option>
			     <option value="2">已删除</option>
			     </select>
			    </div>
			  </div><!-- /.col-xs-4 -->
			  <div class="col-xs-4">
			  	<button type="button" class="btn btn-primary pull-right" id="searchBtn"> 
			    		<i class="glyphicon glyphicon-search"></i>查询
			   	</button>
			  </div><!-- /.col-xs-4 -->
		  </div>
		</div>
		 <div class="panel panel-default">
		   <div class="panel-body">
		  	<button type="button" class="btn btn-success" data-toggle="modal" id="addBtn">
			    		<i class="glyphicon glyphicon-plus"></i>新建Banner
			</button>
			<!--
		  	<button type="button" class="btn btn-warning" id="editBtn">
			    		<i class="glyphicon glyphicon-sort"></i>位置设定
			</button>
			 -->
		  	<button type="button" class="btn btn-info" id="viewBtn">
			    		<i class="glyphicon glyphicon-eye-open"></i>效果预览
			</button>
		</div>
		</div>
		<!-- table 插件 -->
		<c:import url="../table.jsp"></c:import>
	</div>
	</div>
	<!-- 预览任务 -->
<div class="modal fade" id="viewModal" role="dialog"
	aria-labelledby="viewModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="viewModalLabel">效果预览</h4>
			</div>
			<div class="modal-body">
				<div class="carousel slide" id="carousel-268003">
					<ol class="carousel-indicators">
					<!--
						<li data-slide-to="0" data-target="#carousel-268003">&nbsp;</li>
						<li class="active" data-slide-to="0" data-target="#carousel-268003">&nbsp;</li>
						<li id="data-slide-to" data-slide-to="1" data-target="#carousel-268003">&nbsp;</li>
						
						-->
					</ol>

					<div class="carousel-inner">
					<!--
						<div class="item">
							<img alt="" src="../img/banner01.jpg" width='560' height='250' />
							<div class="carousel-caption" contenteditable="true"></div>
						</div>

						<div class="item active">
							<img alt="" src="../img/banner.jpg" width='560' height='250' />
							<div class="carousel-caption" contenteditable="true"></div>
						</div>

						<div class="item">
							<img alt="" src="../img/b_luck.jpg" width='560' height='250' />
							<div class="carousel-caption" contenteditable="true"></div>
						</div>
						
						-->
					</div>
					<a class="left carousel-control" data-slide="prev"
						href="#carousel-268003">&lsaquo;</a> <a
						class="right carousel-control" data-slide="next"
						href="#carousel-268003">&rsaquo;</a>
				</div>
			</div>

			<div class="modal-footer" style="text-align: center">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

	<!-- 预览任务 无数据时 -->
<div class="modal fade" id="viewModalNon" role="dialog"
	aria-labelledby="viewModalNonLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="viewModalNonLabel">效果预览</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning alert-dismissable">
				  无上线banner,请先上线banner再预览。
				</div>
			</div>

			<div class="modal-footer" style="text-align: center">
				<button type="button" class="btn btn-default" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i>关闭
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<!-- 新建banner -->
<div class="modal fade" id="myModal" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">新建Banner</h4>
			</div>
			<div class="modal-body">
				<div class="addAlert alert alert-warning alert-dismissable hide">
				   警告！请不要提交。
				</div>
				<form class="form-horizontal" role="form" id='addForm'>
					<div class="form-group">
						<label for="inputBannerName" class="col-sm-2 control-label">名称<font color="red">*</font>:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputBannerName" name="bannerName" maxlength="30"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputUrl" class="col-sm-2 control-label">页面URL<font color="red">*</font>:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputUrl" name="url" maxlength="100"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputState" class="col-sm-2 control-label">状态操作:</label>
						<div class="col-sm-10">
							<select class="form-control" id="inputState" name="state">
								<option value="0">上线</option>
								<option value="1">下线</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="inputDescription" class="col-sm-2 control-label">图片url<font color="red">*</font>:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="picUrl" name="picUrl"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">图片预览:</label>
						<div class="col-sm-4">
							<div class="container-fluid">
								<div class="row-fluid">
									<div class="span12">
										<img id='pic' width=100 height=48 alt="预览图" src="" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="inputDescription" class="col-sm-2 control-label">描述:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputDescription" name="description" maxlength="100"/>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer" style="text-align: center">
				<button type="button" class="btn btn-default" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i>取消
				</button>
				<button type="button" class="btn btn-primary" id="createBtn"><i class="glyphicon glyphicon-ok"></i>确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>


<!-- 删除banner -->
<div class="modal fade" id="deleteModal" role="dialog"
	aria-labelledby="deleteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="deleteModalLabel">删除banner</h4>
			</div>
			
			<div class="modal-body">
			<input type="hidden" id="removeId"/>
				该操作暂时不可恢复,确认删除此banner吗?
			</div>

			<div class="modal-footer" style="text-align: center">
				<button type="button" class="btn btn-default" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i>取消</button>
				<button type="button" class="btn btn-primary" id="deleteSubmitBtn"><i class="glyphicon glyphicon-ok"></i>确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<!-- 编辑banner -->
<div class="modal fade" id="editModal" role="dialog"
	aria-labelledby="editModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="editModalLabel">编辑Banner</h4>
			</div>
			<div class="modal-body">
			
				<div class="editAlert alert alert-warning alert-dismissable hide">
				   警告！请不要提交。
				</div>
				<form class="form-horizontal" role="form" id='editForm'>
					<input type="hidden" class="form-control" id="id" name="id"/>
					<div class="form-group">
						<label for="editBannerName" class="col-sm-2 control-label">名称<font color="red">*</font>:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="editBannerName" name="bannerName" maxlength="30"/>
						</div>
					</div>
					<div class="form-group">
						<label for="editUrl" class="col-sm-2 control-label">页面URL:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="editUrl" name="url" maxlength="100"/>
						</div>
					</div>

					<div class="form-group">
						<label for="editState" class="col-sm-2 control-label">状态操作:</label>
						<div class="col-sm-10">
							<select class="form-control" id="editState" name="state">
								<option value="0">上线</option>
								<option value="1">下线</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="inputDescription" class="col-sm-2 control-label">图片url<font color="red">*</font>:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="editPicUrl" name="editPicUrl"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputFile" class="col-sm-2 control-label">图片预览<font color="red">*</font>:</label>
						<div class="col-sm-4">
							<div class="container-fluid">
								<div class="row-fluid">
									<div class="span12">
										<img id='editpic' width=100 height=48 alt="预览图" src="" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="editDescription" class="col-sm-2 control-label">描述:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="editDescription" name="description" maxlength="100"/>
						</div>
					</div>
						</form>
			</div>
			<div class="modal-footer" style="text-align: center">
				<button type="button" class="btn btn-default" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i>取消
				</button>
				<button type="button" class="btn btn-primary" id="editSubmitBtn"><i class="glyphicon glyphicon-ok"></i>确定
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/qiao.js"></script>
	<script src="${pageContext.request.contextPath}/static/admin/cms/bannerManager.js"></script>
</body>
</html>
