layui.use('jquery', function () {
	var $ = layui.jquery;
	$("#submit").on("click",function(){
		var src = $("#src").val();
		if (/^((ht|f)tps?):\/\/[\w\-]+(\.[\w\-]+)+([\w\-\.,@?^=%&:\/~\+#]*[\w\-\@?^=%&\/~\+#])?$/.test(src)==false) {
			layer.msg("请输入正确解析路径",{anim:6});
			return ;
		}
		html = '<iframe border="0" id="vplay" name="vplay" src="http://api.1008net.com/v.php?url='+src+'" scrolling="no" width="100%" height="570px" frameborder="0">'+
                		'</iframe>';
		$(".vip-video").html(html);
	});
});
