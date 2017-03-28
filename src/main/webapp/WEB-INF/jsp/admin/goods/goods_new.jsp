<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>新建商品</title>

<c:import url="../common.jsp"></c:import>
</head>
<body>
	<div class="container-fluid">
		<ol class="breadcrumb"style="margin-bottom: 1px;">
			<li class="active">新建商品</li>
			<li><a href="https://tu.taobao.com/redaction/manager.htm" target="_blank">阿里图片空间</a></li>
		</ol>
		<div class="panel panel-default">
			<form class="form-horizontal" role="form" id='goodsForm'>
				<div class="row">
					<div class="col-xs-12">
						<div class="input-group">
							<span class="input-group-addon"> 商品名称 </span> 
							<input type="text" class="form-control" id="name" name="name" maxlength="300" />
						</div>
					</div>
				</div>
			
				<div class="row">
					<div class="col-xs-2">
						<div class="input-group">
							<span class="input-group-addon"> 所属类目 </span> <select
								class="form-control" id="categoryId" name="categoryId">
								<option value="1">家居生活</option>
								<option value="2">数码家电</option>
								<option value="3">母婴玩具</option>
								<option value="4">食品保健</option>
								<option value="5">美容配饰</option>
								<option value="6">汽车用品</option>
							</select>
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							<span class="input-group-addon"> 价格 </span> 
							<input type="text" class="form-control" id="price" name="price" maxlength="300" />
						</div>
					</div>
					<div class="col-md-2">
					<div class="input-group">
							<span class="input-group-addon"> 市场价 </span> 
							<input type="text" class="form-control" id="marketPrice" name="marketPrice" maxlength="300" />
					</div>	
					</div>
					<div class="col-md-2">
						<div class="input-group">
							<span class="input-group-addon"> 库存 </span> 
							<input type="text" class="form-control" id="stock" name="stock" maxlength="300" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							<span class="input-group-addon"> 销量 </span> 
							<input type="text" class="form-control" id="sales" name="sales" maxlength="300" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							<span class="input-group-addon"> 点击量 </span> 
							<input type="text" class="form-control" id="clicks" name="clicks" maxlength="300" />
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-4">
						<div class="input-group">
							<span class="input-group-addon"> 商品主图 </span> 
							<input type="hidden" id="mainImg" name="mainImg" value=""  />
								<div class="col-sm-4">
									<div class="container-fluid">
										<div class="row-fluid">
											<div class="span12">
												<img id='mainImgUrlPre' width=80 height=80 alt="预览图" src="" />
											</div>
										</div>
									</div>
								</div>
								<div class="input-group-btn">
									<button type="button" class="btn btn-success" id="mainImgUrlSelect">
										<i class="glyphicon glyphicon-plus"></i>选择图片
									</button>
								</div>
						</div>
					</div>
				</div>
				
				<div class="row">
				  	<div class="col-md-12">
					    <div class="input-group">
					     	<span class="input-group-addon">
					        	<i class="require-star"></i>商品图片
					     	</span>
					     	<script id="preImg" type="text/plain" style="width:1230px;height:300px;"></script>
					    </div>
					 </div>
					 <div class="col-md-12">
					    <div class="input-group">
					     	<span class="input-group-addon">
					        	<i class="require-star"></i>商品描述
					     	</span>
					     	<script id="description" name="description" type="text/plain" style="width:1230px;height:300px;"></script>
					    </div>
					 </div>
				 </div>
				 
				 <div id="addProduct_div_40">
				  	<div id="addProduct_div_41" class="col-md-6">
					    <div id="addProduct_div_42" class="pull-right">
					    	<button type="button" class="btn btn-primary" id="addBtn">
					    		<i class="glyphicon glyphicon-floppy-disk"></i>保存
					    	</button>
					    </div>
					 </div><!-- /.col-md-6 -->
					 <div id="addProduct_div_43" class="col-md-6">
					    <div id="addProduct_div_44" class="pull-left">
					    	<button type="button" class="btn btn-danger" id="preBtn">
					    		<i class="glyphicon glyphicon-remove"></i>关闭
					    	</button>
					    </div>
					 </div><!-- /.col-md-6 -->
				 </div>
				</form>
		</div>
	</div>
		<div class="modal fade" id="selectPic" role="dialog"
			aria-labelledby="editModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header bg-primary">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="editModalLabel">选择图片</h4>
					</div>
					<div class="modal-body">
						<div class="editAlert alert alert-warning alert-dismissable hide">
							警告！请不要提交。</div>
						<form class="form-horizontal" role="form" id='editForm'>
							<div class="form-group">
								<label for="editUrl" class="col-sm-2 control-label">图片URL:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="sePicUrl" name="sePicUrl"
										maxlength="100" />
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer" style="text-align: center">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<i class="glyphicon glyphicon-remove"></i>取消
							<button type="button" class="btn btn-primary" id="picSubmitBtn">
								<i class="glyphicon glyphicon-ok"></i>确定
							</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/js/qiao.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor1_4_3_2/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor1_4_3_2/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor1_4_3_2/lang/zh-cn/zh-cn.js"></script>
	<script src="${pageContext.request.contextPath}/static/admin/goods_new.js"></script>
</body>
</html>
