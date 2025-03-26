var pics = {
  "data": [
      {"src": "/static/foreground/images/pic/1.jpeg",   "alt": "佐助",},
      {"src": "/static/foreground/images/pic/2.jpg",   "alt": "漩涡鸣人"},
      {"src": "/static/foreground/images/pic/3.jpg",   "alt": "宇智波鼬"},
      {"src": "/static/foreground/images/pic/4.jpeg",   "alt": "海贼王"},
      {"src": "/static/foreground/images/pic/5.jpg",   "alt": "紫霞仙子"},
      {"src": "/static/foreground/images/pic/6.jpg",   "alt": "紫霞仙子"}
   ]};
layui.use(['jquery','flow'], function () {
	var $ = layui.jquery;
	// 流加载 图片
    var flow = layui.flow;
    var count = pics.data.length-1;
    flow.load({
    	elem: '.mixed-main', //流加载容器
    	isAuto: true,
    	end: '没有更多的图片了~QAQ',
    	done: function(page,next) {
    		var lis = [];
    		for (var i=0; i<8; i++) {
    			if (count < -1) break;
    			if (count==-1) {
    				lis.push('<div class="mixed shadow animated zoomIn">'+
    	                 '<div class="mixed-pic">'+
    	                    '<a href="javascript:"><img src="/static/foreground/images/pic/0.jpg" alt="图片还在拍摄中" /></a>'+
	                    '</div>'+
	                    '<div class="mixed-info">图片还在拍摄中</div>'+
	                    '<div class="mixed-footer">'+
	                        '<a class="layui-btn layui-btn-small layui-btn-primary layui-btn-disabled"><i class="fa fa-eye fa-fw"></i>查看</a>'+
	                        '<a class="layui-btn layui-btn-small layui-btn-primary layui-btn-disabled"><i class="fa fa-download fa-fw"></i>下载</a>'+
	                    '</div>',
	                '</div>');
    			} else {
	    			lis.push('<div class="mixed shadow animated zoomIn">'+
	                    '<div class="mixed-pic">'+
	                        '<a href="javascript:view('+count+')"><img src="'+pics.data[count].src+'" alt="'+pics.data[count].alt+'" /></a>'+
	                    '</div>'+
	                    '<div class="mixed-info">'+pics.data[count].alt+'</div>'+
	                    '<div class="mixed-footer">'+
	                        '<a class="layui-btn layui-btn-small layui-btn-primary" href="javascript:view('+count+')"><i class="fa fa-eye fa-fw"></i>查看</a>'+
	                        '<a class="layui-btn layui-btn-small layui-btn-primary" href="/foreground/downloadImage?fileName='+pics.data[count].src+'"><i class="fa fa-download fa-fw"></i>下载</a>'+
	                    '</div>',
	                '</div>');
    			}
    			count--;
    		}
    		next(lis.join(''), page < pics.data.length/8);
    	}
    });
});
function view(start) {
	pics.start = start;
	layer.photos({photos: pics });	
}
