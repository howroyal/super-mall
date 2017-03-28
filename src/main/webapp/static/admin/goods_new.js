
var GoodsNew = {}


$(function(){
	var imgUrlUE = UE.getEditor('preImg');
	var descUE = UE.getEditor('description');
	
	$('#mainImgUrlSelect').click(function(){
		$('#selectPic').modal('show');
	});
	
	$('#picSubmitBtn').click(function(){
		var picUrl = $('#sePicUrl').val();
		$('#mainImg').val(picUrl);
		$('#mainImgUrlPre').attr('src',picUrl);
		$('#selectPic').modal('hide');
	});
	

		$('#addBtn').click(function() {
			var name = $('#name').val();
			var categoryId = $('#categoryId').val();
			var price = $('#price').val();
			var marketPrice = $('#marketPrice').val();
			var stock = $('#stock').val();
			var sales = $('#sales').val();
			var clicks = $('#clicks').val();
			var mainImg = $('#mainImg').val();
			var preImg = UE.getEditor('preImg').getContent(); 
			var description = UE.getEditor('description').getContent(); 
			
			$.ajax({
				url : contextPath + "/goodsManager/addGoodsInfo.html",
				type : "POST",
				data : {
					name : name,
					categoryId : categoryId,
					price : price,
					marketPrice : marketPrice,
					stock : stock,
					sales : sales,
					clicks : clicks,
					mainImg : mainImg,
					preImg : preImg,
					description : description
				},// 你的formid
				error : function(request) {
					alert('出错啦');
				},
				success : function(data) {
					if(data.success){
						 window.location.reload();
					}
				}
			});
	});
});
