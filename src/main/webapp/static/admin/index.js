var MIndex = {
	loadHtml: function($li){
		console.log($li.attr('value'));
		$('#mainFrame').attr("src",$li.attr('value'));
  		$('.active').removeClass('active');
  		$li.addClass('active');
//		$.ajax({
//	        url: $li.attr('value'),
//	        global: false,
//	        type: "POST",
//	        data: {},
//	        dataType: "html",
//	        async: false,
//	        success: function(res) {
//	            $('#mainFrame').src(res);
//	    		$('.active').removeClass('active');
//	    		$li.addClass('active');
//	        }
//	    });
	}

};

$(function(){
	$('.li').click(function(){
		MIndex.loadHtml($(this));
	});
})