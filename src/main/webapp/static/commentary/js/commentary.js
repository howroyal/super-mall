// JavaScript Document
var MallCommentary = {}

MallCommentary.goodsId = $('#goodsId').val();
MallCommentary.staticHost = $('#staticHost').val();//本域名
MallCommentary.loginPathURL = $('#loginPathURL').val();//登录页面
MallCommentary.myFeiniuURL = $('#myFeiniuURL').val();//我得飞牛页面
MallCommentary.clickGoodUrl = MallCommentary.staticHost + "/mallCommentary/clickNiceCount.html";
MallCommentary.queryUrl = MallCommentary.staticHost + "/mallCommentary/queryCommentary.html?1=1";
MallCommentary.queryCommentVoUrl = MallCommentary.staticHost + "/mallCommentary/productComment.html?1=1";
/**
 *	查询评论
 * @author 王浩
 * @since 2015-01-08
 */
MallCommentary.query = function(curPage){
	//取得点击的是哪种类型的评论
	var index = MallCommentary.getComentIndex();
	var url = MallCommentary.queryUrl;
	if(index == 1){
		url = url + "&starLevel=4,5";
	}else if(index == 2){
		url = url + "&starLevel=3";
	}else if(index == 3){
		url = url + "&starLevel=1,2";
	}else if(index == 4){
		//追评
		url = url + "&addCommentText=2";
	}else if(index == 5){
		//晒图
		url = url + "&commentPicUrls=2";
	}else if(index == 6){
		//回复
		url = url + "&replyText=2";
	}
	if($("#isDefault").prop("checked")){
		url = url + "&isDefalut=0";
	}else{
		url = url + "&isDefalut=1";
	}
	
	$.ajax({
		type : "post",
		url : url,
		dataType : "jsonp",
		jsonp:'jsonpcallback',
		data : {
			goodsId : MallCommentary.goodsId,
			curPage : curPage
		},
		cache : true,
		async : false,
		success : function(resp,status) {
			debugger;
			var data = resp.dataList;
			var totalPage = resp.totalPage;
			// 查询信息填充网页
			var contengsHtml = MallCommentary.createCommentsContentHtml(index,data);
			var pagingHtml = MallCommentary.pagingHtml(curPage,totalPage);
			$('.pjContents').eq(index).html(contengsHtml + pagingHtml);
			MallCommentary.unBindListenter();
			MallCommentary.bindListenter();
			try{
				window.parent.item.updateHeightOfCommentaryIframe();
			}catch(e){
			}
		}
	});
};

MallCommentary.queryCommentVo = function(){
	//取得点击的是哪种类型的评论
	var url = MallCommentary.queryCommentVoUrl;
	if($("#isDefault").prop("checked")){
		url = url + "&isDefalut=0";
	}else{
		url = url + "&isDefalut=1";
	}
	
	$.ajax({
		type : "post",
		url : url,
		dataType : "jsonp",
		jsonp:'jsonpcallback',
		data : {
			goodsId : MallCommentary.goodsId
		},
		cache : true,
		async : false,
		success : function(resp,status) {
			 $.each($(".pj-content").children(),function(){
				if($(this).children(":first").html().indexOf("全部") >= 0){
					$(this).children(":first").html("全部评论("+resp.commentUserNum +")");
				}else if($(this).children(":first").html().indexOf("好评") >= 0){
					$(this).children(":first").html("好评("+resp.goodCommentCount+")");
				}else if($(this).children(":first").html().indexOf("中评") >= 0){
					$(this).children(":first").html("中评("+resp.normalCommentCount+")");
				}else if($(this).children(":first").html().indexOf("差评") >= 0){
					$(this).children(":first").html("差评("+resp.badCommentCount+")");
				}else if($(this).children(":first").html().indexOf("追评") >= 0){
					$(this).children(":first").html("追评("+resp.addCommentCount+")");
				}else if($(this).children(":first").html().indexOf("晒单") >= 0){
					$(this).children(":first").html("晒单("+resp.commentPicCount+")");
				}else if($(this).children(":first").html().indexOf("回复") >= 0){
					$(this).children(":first").html("回复("+resp.replyCount+")");
				}
				 
				 
			 });
			debugger;
			var data = resp.dataList;
			var totalPage = resp.totalPage;
		}
	});
};
/**
 *	根据分页返回结果填充网页dom
 * @author 王浩
 * @since 2015-01-08
 */
MallCommentary.createCommentsContentHtml = function(index,resp){
	var inHtml= '';
	$.each(resp,function(index, data){
		var impression = data.impression;
		var imp = '';
		$.each(impression,function(i,v){
			imp += '<p>' + v + '</p>';
		});
		var colorHtml = '';
		if(data.color){
			colorHtml = '<span>颜色：' + data.color + ' </span>';
		}
		var sizeHtml = '';
		if(data.size){
			sizeHtml = '<span>尺寸:' + data.size + ' </span>';
		}
		
		//晒图html
		var picHtml= MallCommentary.createPicHtml(data);
		
		//商家回复html
		var replyHtml = MallCommentary.createReplyHtml(data);
		
		//追评html
		var addCommentHtml = MallCommentary.crateAddCommentHtml(data);
		
		inHtml += '<div class="pj-box">'
		+'<div class="author-info">'
		+'<div class="author-portrait">'
		+'<a class="author-portrait-container"> <!-- <div class="portrait-cover"> -->'	
		+'	<img src="'+data.userPhoto + '" width="70px" height="70px" /> </a>'	
		+'</div>'
		+'<p>' + data.userName +'</p>'
		+'</div>'
		+'<div class="pj-box-warp">'
		+'<em class="pj-box-bg"></em>'
		+'<div class="pj-box-content">'
		+'	<div class="pj-title">'
		+'		<span'
		+'			class="start-'+data.commentStar +'"></span><span'
		+'			class="pl-date">'+data.commentDate+'</span>'
		+'	</div>'
		+'	<ul class="pl-ul">'
		+'		<!-- 规格开始 -->'
		+'		<li class="clearfix">'
		+'			<div class="dd">规　　格:</div>'
		+'			<div class="dt">'
		+	colorHtml
		+	sizeHtml
		+'			</div>'
		+'		</li>'
		+'		<!-- 印象开始 -->'
		+'		<li class="clearfix">'
		+'			<div class="dd">印　　象:</div>'
		+'			<div class="dt">'
		+ 	imp
		+'			</div>'
		+'		</li>'
		+'		<li class="clearfix">'
		+'			<div class="dd">体　　会:</div>'
		+'			<div class="dt">'
		+'				<span>'+data.commentText+'</span>'
		+'			</div>'
		+'		</li>'
		+'		<!-- 晒图开始 -->'
		+	picHtml
		+'		<li class="clearfix">'
		+'			<div class="dd">购买日期:</div>'
		+'			<div class="dt">'
		+'				<span>'+data.buyDate+'</span>'
		+'			</div>'
		+'		</li>'
		+'		<!-- 商家回复开始 -->'
		+ replyHtml
		+'        <!-- 追评开始 -->'
		+ addCommentHtml
		+'        <li class="clearfix">'
		+'           <div class="dd"></div>'
		+'           <div class="dt"><a class="zhan" commentId="'+data.id+'">赞('+data.niceCount+')</a></div>'
		+'        </li>'
		+'	</ul>'
		+'</div>'
		+'</div>'
		+'</div>';
	});
	
	return inHtml;
	//inHtml +=  MallCommentary.pagingBarHtml();
	//$('.pjContents').eq(index).html(inHtml);
	//重新绑定所有事件
};

/**
 * 生成晒图的html
 */
MallCommentary.createPicHtml = function(data){
	var picHtml= "";
	var picUrlHtml = "";
	var commentPicUrls = data.commentPicUrls;
	if(commentPicUrls){
		$.each(commentPicUrls,function(i,v){
			picUrlHtml += '                        <li class="">'
					   +'                           <img src="'+v+'" alt="">'
					   +'                           <b></b>'
					   +'                        </li>';
		});
		picHtml = 	'<li class="clearfix">'
		+'               <div class="dd">晒　　图:</div>'
		+'                <div class="dt">'
		+'                    <ul class="fn-comment-photos">'
		+ picUrlHtml
		+'                    </ul>'
		+'                    <div class="fn-photos-view"> <span class="css-hook"></span><img src="" alt="">'
		+'                        <a class="view-navleft" href="javascript:;"><i></i></a>'
		+'                       <a class="view-navright" href="javascript:;"><i></i></a>'
		+'                    </div>'
		+'                </div>'
		+'            </li>';
	}
	
	return picHtml;
};
/**
 * 生成商家回复的html
 */
MallCommentary.createReplyHtml = function(data){
	var replyHtml = '';
	if(data.replyText){
		replyHtml += '            <li class="clearfix">'
				  +'                <div class="dd replyByservice">商家回复:</div>'
				  +'                <div class="dt"><span class="replyByservice">'+data.replyText+'</span></div>'
				  +'            <li class="clearfix">'
				  +'                <div class="dd"></div>'
				  +'                <div class="dt"><font class="replyTime">'+data.replyDate+'</font></div>'
				  +'            </li>';
	}
	
	return replyHtml;
};
/**
 * 追评的hmtl生成
 */
MallCommentary.crateAddCommentHtml = function(data){
	//追评回复html
	var addReplyHtml = '';
	if(data.addCommentReplyText){
		addReplyHtml = '           <!-- 客服回复 -->'
			+'           <li class="clearfix">'
			+'               <div class="dd replyByservice">商家回复:</div>'
			+'               <div class="dt"><span class="replyByservice">'+data.addCommentReplyText+'</span></div>'
			+'            </li>'
			+'            <li class="clearfix">'
			+'               <div class="dd"></div>'
			+'               <div class="dt"><font class="replyTime">'+data.addCommentReplyDate+'</font></div>'
			+'           </li>';
	}
	
	//追评晒图html
	var addPicHtml = '';
	var addpicUrlHtml = "";
	var addcommentPicUrls = data.addCommentPicUrls;
	if(addcommentPicUrls){
		$.each(addcommentPicUrls,function(i,v){
			addpicUrlHtml += '                        <li class="">'
					   +'                           <img src="'+v+'" alt="">'
					   +'                           <b></b>'
					   +'                        </li>';
		});
		addPicHtml = 	'<li class="clearfix">'
		+'               <div class="dd">晒　　图:</div>'
		+'                <div class="dt">'
		+'                    <ul class="fn-comment-photos">'
		+ addpicUrlHtml
		+'                    </ul>'
		+'                    <div class="fn-photos-view"> <span class="css-hook"></span><img src="" alt=""> '
		+'                        <a class="view-navleft" href="javascript:;"><i></i></a>'
		+'                       <a class="view-navright" href="javascript:;"><i></i></a>'
		+'                    </div>'
		+'                </div>'
		+'            </li>';
	}
	
	//追评html
	var addCommentHtml = '';
	if(data.addCommentText){
		addCommentHtml = '           <li class="comments-line clearfix">'
			+'               <div class="dd">追　　评:</div>'
			+'                <div class="dt"><span>'+data.addCommentText+'</span></div>'
			+'            </li>'
			+'            <!-- 追评日期 -->'
			+'            <li class="clearfix">'
			+'                <div class="dd"></div>'
			+'               <div class="dt"><font class="replyTime">'+data.addCommentDate+'</font></div>'
			+'            </li>'
			+'            <!-- 追评晒图开始 -->'
			+addPicHtml
			+'           <!-- 客服回复 -->'
			+addReplyHtml;
	};
	
	return addCommentHtml;
	
};
/**
 *	根据分页返回结果填充网页dom
 * @author 王浩
 * @since 2015-01-08
 */
MallCommentary.createCommentsContentHtmlold = function(index,resp){
	
	var inHtml= '';
	$.each(resp,function(index, value){
		var impression = value.impression;
		var imp = '';
		$.each(impression,function(i,v){
			imp += '<p>' + v + '</p>';
		});
		var colorHtml = '';
		if(value.color){
			colorHtml = '<span>颜色：' + value.color + ' </span>'
		}
		
		
		var sizeHtml = '';
		if(value.color){
			sizeHtml = '<span>尺寸:' + value.size + ' </span>'
		}
		var zanHtml = '';
		if(!value.replyText){
			zanHtml = '<li>'
				+'<div class="dd"></div>'
				+'<div class="dt">'
				+'<a class="zhan" commentId="' + value.id + '">赞(' + 

value.niceCount +')</a>'
				+'</div></li>'
		}
		
		var customeReplyHtml = '';
		
		if(value.replyText){
			customeReplyHtml = '<!-- 客服回复开始 -->'
			+'<div class="pj-box-content" style="border-top: none;">'
			+'<ul class="pl-ul">'
			+'	<li>'
			+'		<div class="dd replyByservice">客服回复:</div>'
			+'		<div class="dt">'
			+'			<span class="replyByservice">' + 

value.replyText + '</span>'
			+'		</div></li>'
			+'	<li>'
			+'		<div class="dd"></div>'
			+'		<div class="dt">'
			+'			<font class="replyTime">' + value.replyDate + 

'</font>'
			+'		</div></li>'
			+'	<li>'
			+'		<div class="dd"></div>'
			+'		<div class="dt">'
			+'			<a class="zhan" commentId="' + value.id + '">'

			+ '赞(' + value.niceCount +')</a>'
			+'		</div></li>'
			+'	</ul>'
			+' </div>';
		}
		
		inHtml += '<div class="pj-box">'
			+'<div class="author-info">'
			+'<div class="author-portrait">'
			+'<a class="author-portrait-container"> <!-- <div class="portrait-'

+ 'cover"> -->'
			+'	<img src="' + value.userPhoto+ '"'
			+'	width="70px" height="70px" /> </a>'
			+'</div>'
			+'<p>' + value.userName + '</p>'
			+'</div>'
			+'<div class="pj-box-warp">'
			+'	<em class="pj-box-bg"></em>'
			+'<div class="pj-box-content">'
			+'	<div class="pj-title">'
			+'	<span class="start-'+ value.commentStar + '"></span><span '

+ 'class="pl-date">' + value.commentDate + '</span>'
			+'</div>'
			+'<ul class="pl-ul">'
			+'	<li>'
			+'		<div class="dd">规　　格:</div>'
			+'		<div class="dt">'
			+ colorHtml
			+ '&nbsp;' 
			+ sizeHtml
			+'		</div></li>'
			+'	<li class="yx-col">'
			+'		<div class="dd">印　　象:</div>'
			+'		<div class="dt">' + imp + '</div></li>'
			+'	<li>'
			+'		<div class="dd">体　　会:</div>'
			+'		<div class="dt">'
			+'			<span>' + value.commentText + '</span>'
			+'		</div></li>'
			+'	<li>'
			+'		<div class="dd">购买日期:</div>'
			+'		<div class="dt">'
			+'			<span>' + value.buyDate + '</span>'
			+'		</div>'
			+'	</li>'
			+ zanHtml
			+' </ul>'
			+' </div>'
			+ customeReplyHtml
			+' </div>'
			+' </div>';
	});
	
	return inHtml;
	//inHtml +=  MallCommentary.pagingBarHtml();
	//$('.pjContents').eq(index).html(inHtml);
	//重新绑定所有事件
	
}

MallCommentary.pagingHtml = function(curPage,totalPage){
	if(totalPage <= 1){
		return "";
	}
	var prve = "prve";
	if(curPage <= 1){
		prve = "prve off";
	}
	var next = "next";
	if(curPage >= totalPage){
		next = "next off";
	}
	
	var pageNumHtml = MallCommentary.createPageNumHtml(curPage,totalPage);
	pagingBarHtml = '<!-- 全部评论分页 -->'
		+ '	<div class="fn_page clearfix">'
		+ '<ul>'
		+ '<li class="' + prve + '"><a href="javascript:void(0);"> <i'
		+ '		class="arrow_prev"></i> <span>上一页</span> </a></li>'
		+ pageNumHtml
		+ '	<li class="' + next + '"><a href="javascript:void(0);"> <span>下一页'

+'</span>'
		+ '			<i class="arrow_next"></i> </a>'
		+ '	</li>'
		+ '	<li><span>到第</span> <input id="amount" style="IME-MODE: disabled; '

+'WIDTH: 60px; HEIGHT: 28px" onkeyup="return MallCommentary.ValidateNumber(this,value)" '

+'maxlength="6" size="14" name="amount" type="text" value="' + curPage + '" maxValue=' + 

totalPage +' /> <span>页</span>'
		+ '	</li>'
		+ '	<li class="goto"><a href="javascript:void(0);">跳转</a>'
		+ '	</li>'
		+ '</ul>'
		+ '</div>';
	return pagingBarHtml;
}

MallCommentary.createPageNumHtml = function(curPage, totalPage){
	var pageHTML = "";
	if (totalPage <= 10) {
		for (var i = 1; i <= totalPage; i++) {
			if(i == curPage){
				pageHTML += "<li class='active'><a href='#' class='active'>" + 

i + "</a></li>";
			}else {
				pageHTML += "<li><a href='#' class='pageNum'>" + i + 

"</a></li>";
			}
		}
		
		return pageHTML;
	}

	//特殊情况，如果curPage<=5则显示所有前面页
	if(curPage <= 5){
		for (var i = 1; i < 7; i++) {
			if(i == curPage){
				pageHTML += "<li class='active'><a href='#' class='active'>" + 

i +"</a></li>";
			}else {
				pageHTML += "<li><a href='#' class='pageNum'>" + i + 

"</a></li>";
			}
		}
		//再加上后面2页数据
		//打点数据
		pageHTML += "<li><span class='page_ellipsis'>...</span></li>";
		for (var i = 1; i >= 0; i--) {
			var thisPage = (totalPage-i);
			pageHTML += "<li><a href='#' class='pageNum'>" + thisPage + 

"</a></li>";
			
		}
		return pageHTML;
	} 
	if(totalPage - curPage <=5){
		//显示最前面3页数据
		for (var i = 1; i <= 2; i++) {
			pageHTML += "<li><a href='#' class='pageNum'>" + i + "</a></li>";
		}
		//打点数据
		pageHTML += "<li><span class='page_ellipsis'>...</span></li>";
		
		//显示最后面3页数据
		for (var i = 7; i >= 0; i--) {
			var thisPage = (totalPage-i);
			if(thisPage == curPage){
				pageHTML += "<li class='active'><a href='#' class='active'>" + 

thisPage + "</a></li>";
			}else {
				pageHTML += "<li><a href='#' class='pageNum'>" + thisPage + 

"</a></li>";
			}
		}
		return pageHTML;
	}
	
	//显示最前面3页数据
	for (var i = 1; i <= 2; i++) {
		pageHTML += "<li><a href='#' class='pageNum'>" + i + "</a></li>";
	}
	//打点数据
	pageHTML += "<li><span class='page_ellipsis'>...</span></li>";
	
	//显示当前页前后2页数据
	for (var i = 1; i <= 5; i++) {
		var thisPage = (i+(curPage-3));
		if(thisPage == curPage){
			pageHTML += "<li class='active'><a href='#'>" + thisPage +"</a></li>";
		}else {
			pageHTML += "<li><a href='#' class='pageNum'>" + thisPage + 

"</a></li>";
		}
	}
	
	//打点数据
	pageHTML += "<li><span class='page_ellipsis'>...</span></li>";
	
	//显示最后面3页数据
	for (var i = 1; i >= 0; i--) {
		var thisPage = (totalPage-i);
		pageHTML += "<li><a href='#' class='pageNum'>" + thisPage + "</a></li>";
	}
	return pageHTML;
}

MallCommentary.getComentIndex = function(){
	var index = 0;
	var s = $('.selected').text();
	if(s.indexOf("全部评论") == 0){
		index = 0;
	}
	if(s.indexOf("好评") == 0){
		index = 1;
	}
	if(s.indexOf("中评") == 0){
		index = 2;
	}
	if(s.indexOf("差评") == 0){
		index = 3;
	}
	if(s.indexOf("追评") == 0){
		index = 4;
	}
	if(s.indexOf("晒单") == 0){
		index = 5;
	}
	if(s.indexOf("回复") == 0){
		index = 6;
	}
	return index;
}
MallCommentary.ValidateNumber = function(e, pnumber){
	if (!/^\d+$/.test(pnumber)){
		e.value = /^\d+/.exec(e.value);}
		return false;
}

/**
 *	点赞动作
 * @author 王浩
 * @since 2015-01-08
 */
MallCommentary.clickGood = function(emem){
	var $this = $(emem);
	var commentId = $this.attr('commentId');
	if(!$this){
		//alert('未选择任何操作');
		return;
	}
	var userName = MallCommentary.getCookie("fn_username_for_js");
	//未登录
	if(userName == null){
		//页面重定向到登录页
		parent.location.href = MallCommentary.loginPathURL + parent.location.href;
		return;
	}
	//已登录
	$.ajax({
		type : "post",
		url : MallCommentary.clickGoodUrl,
		dataType : "jsonp",
		jsonp:'jsonpcallback',
		data : {
			commentId: commentId,
			userName : userName
		},
		cache : false,
		async : false,
		success : function(resp,status) {
			var status = resp.status;
			if(status){
				$this.text('赞(' + resp.data + ')');
			}
//			else {
//				if(resp.code == '1'){
//					parent.location.href='https://member.beta1.fn/sh/getaway/login?checkout_url=' + parent.location.href;
//				}
//			}
		}
	});
}

/**
 *	取得cookie
 * @author 王浩
 * @since 2015-01-08
 */
MallCommentary.getCookie = function (c_name){
	if (document.cookie.length>0){
		c_start = document.cookie.lastIndexOf(c_name + "=");
	  if (c_start!=-1){ 
		  c_start = c_start + c_name.length + 1;
		  c_end = document.cookie.indexOf(";",c_start)
	    if (c_end == -1){
	    	c_end = document.cookie.length;
	    } 
		return unescape(document.cookie.substring(c_start,c_end));  
	  } 
	}
	return null;
//	var arrStr = document.cookie.split("; ");
//	for(var i = 0;i < arrStr.length;i ++){
//	var temp = arrStr[i].split("=");
//	if(temp[0] == objName) return unescape(temp[1]);
//	} 
}
/**
 *	绑定事件
 * @author 王浩
 * @since 2015-01-08
 */
MallCommentary.bindListenter = function(){
	
	//绑定分页点击对象
	//分页控件绑定事件
	//绑定上一页事件
	$('.prve').click(function(){
		//取得当前分页栏目的当前页
		var curPage = parseInt($(this).parent().find('.active a').text());
		var maxValue = $(this).parent().find('#amount').attr('maxValue');
		if(curPage == 1){
			return;
		}
		MallCommentary.query(curPage-1,maxValue);
		
		//alert('点击的是上一页,上一页是：' + (curPage-1));
	});
	//绑定数字事件
	$('.pageNum').click(function(){
		var curPage = parseInt($(this).text());
		var maxValue = $(this).parent().parent().find('#amount').attr('maxValue');
		MallCommentary.query(curPage,maxValue);
	});
	//绑定下一页事件
	$('.next').click(function(){
		var curPage = parseInt($(this).parent().find('.active a').text());
		var maxValue = $(this).parent().find('#amount').attr('maxValue');
		if(curPage == maxValue){
			return;
		}
		
		MallCommentary.query(curPage+1,maxValue);
	});
	//绑定跳转事件
	$('.goto').click(function(){
		var curPage = parseInt($(this).parent().find('#amount').val());
		var maxValue = $(this).parent().find('#amount').attr('maxValue');
		if(curPage == ''){
			curPage = 1;
		}
		if(curPage == parseInt($(this).parent().find('.active a').text())){
			return;
		}
		if(curPage >= maxValue){
			curPage = maxValue;
		}
		MallCommentary.query(curPage,maxValue);
	});
	
	//点赞事件绑定
	$('.zhan').click(function(){
		MallCommentary.clickGood(this);
	});
}

/**
 *	解除绑定事件，不然事件多次绑定会造成不可预知的后果
 * @author 王浩
 * @since 2015-01-08
 */
MallCommentary.unBindListenter = function(){
	$('.prve').unbind("click");
	$('.pageNum').unbind("click");
	$('.next').unbind("click");
	$('.goto').unbind("click");
	$('.zhan').unbind("click");
}
/**
 *页面加载后自动调用
 * @author 王浩
 * @since 2015-01-08
 */
$(function(){
	//好中差评价切换
	$(".pj-content a").click(function(){
		var thisA=$(this);
		if(thisA.attr("canSelected") != 'false'){
			thisA.parents(".pj-content").children("li").find("a").removeClass("selected");
			thisA.addClass("selected");
			var index = $(".pj-content a").index(this);
			//如果页面没内容则请求后台填充数据
			MallCommentary.query(1,0);
			$(".pjContents").eq(index).show().siblings(".pjContents").hide();
			
			//自适应父窗口
			try{
				window.parent.item.updateHeightOfCommentaryIframe();
			}catch(e){
			}
		}
	});
	$(".pj-content a span").click(function(){//绑定点击有内容事件
		 if($("#isDefault").prop("checked"))
		   {
			 $("#isDefault").prop('checked',false);
		   }
		   else
		   {
			   $("#isDefault").prop('checked',true);
		   }
		 //修改评论数量
		 MallCommentary.queryCommentVo();
		 MallCommentary.query(1,0);
	});
	$("#isDefault").click(function(){//绑定勾选框
		 //修改评论数量
		MallCommentary.queryCommentVo();
		 MallCommentary.query(1,0);
		}
	)
	//绑定发表评价事件
	$('.fbpjBtn').click(function(){
		window.open(MallCommentary.myFeiniuURL);
	});
	//绑定勾选有内容事件
	
	//绑定各种事件
	MallCommentary.bindListenter();	
	
	
	var eShow = {
         
	    init: function () {

	        eShow.bindEvents();

	    },
	    bindEvents: function () {

	        var $commented = $('.proAppraise .pjContents');

	        //晒图
	        $commented.on('click', '.fn-comment-photos li', eShow.EventList.showImages);

	        //晒图按钮
	        $commented.on('click', '.fn-photos-view a', eShow.EventList.changeImages);

	        //关闭晒图大图
	        $commented.on('click', '.fn-photos-view img', eShow.EventList.hideImages);
	    },
	    EventList: {

	        //晒图
	        showImages: function (e) {

	            var $this = $(e.currentTarget),
                    $imgs = $this.parent();
	            $vcon = $this.parent().next('.fn-photos-view'),
                $vbtn = $vcon.children('a'),
                $lbtn = $vcon.children('.view-navleft'),
                $rbtn = $vcon.children('.view-navright'),
                src = $this.children('img').attr('src'),
                lsize = $imgs.children().length;
	            flength = $this.prev().length,
                llength = $this.next().length;


	            if ($this.hasClass('vselect')) {
	                $this.removeClass('vselect');
	                $vcon.hide(200);
	            }
	            else {
	                $this.addClass('vselect').siblings().removeClass('vselect');
	                $vcon.show(200);
	            }

	            if (lsize == 1) {
	                $vbtn.hide();
	            }
	            else {
	                if (!flength) {
	                    $lbtn.addClass('adisabled');
	                }
	                else {
	                    $lbtn.removeClass('adisabled');
	                }

	                if (!llength) {
	                    $rbtn.addClass('adisabled');
	                }
	                else {
	                    $rbtn.removeClass('adisabled');
	                }
	            }
	            $vcon.children('img').attr('src', src);



	        },

	        //晒图按钮
	        changeImages: function (e) {

	            var $this = $(e.currentTarget),
                    $imgs = $this.parent().prev('.fn-comment-photos'),
                    length = $imgs.children().length,
                    index = $imgs.children('.vselect').index();

	            if (length == 1 || $this.hasClass('adisabled')) {
	                return;
	            }
	            else {
	                if ($this.hasClass('view-navleft')) {

	                    index--
	                    if (index <= 0) {
	                        // index = 0;
	                        $this.addClass('adisabled').siblings().removeClass('adisabled');
	                    }
	                    else {
	                        $this.removeClass('adisabled').siblings().removeClass('adisabled');
	                    }

	                }
	                else {
	                    index++
	                    if (index >= length - 1) {
	                        //index = length - 1
	                        $this.addClass('adisabled').siblings().removeClass('adisabled');
	                    }
	                    else {
	                        $this.removeClass('adisabled').siblings().removeClass('adisabled');
	                    }

	                }

	                var src = $imgs.children().eq(index).children().attr('src');
	                $imgs.children().eq(index).addClass('vselect').siblings().removeClass('vselect');
	                $this.siblings('img').attr('src', src);
	            }
	            // return;

	        },

	        //关闭晒图大图
	        hideImages: function (e) {
	            var $this = $(e.currentTarget),
                    $vcon = $this.parent('.fn-photos-view'),
                    $imgsli = $vcon.prev('.fn-comment-photos').children('li');
	            $vcon.hide(200);
	            $imgsli.removeClass('vselect');
	        }

	    }

	}

	eShow.init();

});