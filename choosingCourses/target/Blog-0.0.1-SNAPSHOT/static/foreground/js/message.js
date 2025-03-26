var result = {"msg":null,"code":1,"item":[{"content":"模板挺好看的，我很喜欢可以分享模板吗，www.scever.com","time":"2017-11-14 16:13:53","dzan":"81","isYe":"","name":"紫逸","img":"/user/7.jpg",
					"item":[{"content":"稍等，后期我会整理出来，现在也可以去github上面下载，我给源码直接贴上去了","time":"2017-11-14 18:30:56","dzan":"6","name":"zuoqy","img":"/user/1.jpg"}]},
					{"content":"<a target=\"_self\" href=\"http://www.yansuanzhi.cn\">http://www.yansuanzhi.cn</a><br>",
					"time":"2017-11-14 14:00:41","dzan":"64","name":"验算纸","img":"/user/12.jpg",
					"item":[{"content":"你这个打不开啊","time":"2017-11-14 18:29:19","dzan":"2","name":"zuoqy","img":"/user/1.jpg"}]},
					{"content":"怎么能联系到博主","time":"2017-11-14 14:00:19","dzan":"1","isYe":"","name":"验算纸",
					"img":"/user/12.jpg","item":[{"content":"首页，就有4种联系方式","time":"2017-11-14 18:28:52","dzan":"1","name":"zuoqy","img":"/user/1.jpg"}]},
					]};

var aid = '0';
var user = MyLocalStorage.get('user');
if (user!=null) user = JSON.parse(user);
layui.use(['jquery', 'form', 'layedit','flow','util'], function () {
	var util = layui.util;
    var form = layui.form;
    var $ = layui.jquery;
    var layedit = layui.layedit;
    var flow = layui.flow;
    
    // 获取页面aid编号
    var url = window.location.href.split("?");
    if (url.length==2) {
    	url = url[1];
    	var args = url.split("&");
    	aid = args[0].split("=")[1];
    }

    //评论和留言的编辑器
    var editIndex = layedit.build('remarkEditor', {
        height: 150,
        tool: ['face', '|','strong','italic', 'del','left', 'center', 'right', '|', 'link'],
    });
    
    //评论和留言的编辑器的验证
    layui.form.verify({
        content: function (value) {
        	if (user==null) return "登录后才能提交"; 
            value = $.trim(layedit.getText(editIndex));
            if (value == "") return "至少得有一个字吧";
            layedit.sync(editIndex);
        }
    });
    
    
    	

	var msgs = result.item;
	var lis = [];
	for (var i=0; i<msgs.length; i++) {
		var time = util.timeAgo(formatDate(""+msgs[i].time));
		var html = '<li>'+
        '<div class="comment-parent">'+
            '<img src="images'+msgs[i].img+'"/>'+
            '<div class="info">'+
                '<span class="username">'+msgs[i].name+'</span>'+
            '</div>'+
            '<div class="content">'+
                	msgs[i].content+
            '</div>'+
            '<p class="info">'+
            	'<span class="time"><i class="fa fa-clock-o"></i>&nbsp;'+time+'</span>'+
            	'<span class="dh">'+
            		'<a class="btn-dzan" href="javascript:dzan(\''+msgs[i].mid+'\');" id="dzan_'+msgs[i].mid+'"><img src="images/zan.png"></img>'+msgs[i].dzan+'</a>'+
            		'<a class="btn-reply" href="javascript:btnReplyClick(\''+msgs[i].mid+'\')" id="a_'+msgs[i].mid+'");"><img src="images/huifu.png"></img>回复</a>'+
            	'</span>'+
            '</p>'+
        '</div>'+
        '<hr />';
		var cs = msgs[i].item;
		if (cs!=null && cs.length>0) {
			for (var j=0; j<cs.length; j++) {
				var time = util.timeAgo(""+formatDate(cs[j].time));
				html += '<div class="comment-child">'+
					'<img src="images'+cs[j].img+'" alt="Absolutely" />'+
					'<div class="info">'+
						'<span class="username">'+cs[j].name+'</span><span>'+cs[j].content+'</span>'+
					'</div>'+
					'<p class="info">'+
                	'<span class="time"><i class="fa fa-clock-o"></i>&nbsp;'+time+'</span>'+
                	'<span class="dh">'+
                		'<a class="btn-dzan" href="javascript:dzan(\''+cs[j].mid+'\');" id="dzan_'+cs[j].mid+'"><img src="images/zan.png"></img>'+cs[j].dzan+'</a>'+
                	'</span>'+
                	'</p><hr/>'+
                	'</div>';
			}
		}
		 html +='<div class="replycontainer layui-hide" id="'+msgs[i].mid+'">'+
		'<form class="layui-form" action="">'+
		'<input type="hidden" name="isYe" value="'+msgs[i].mid+'">'+
		'<div class="layui-form-item">'+
			'<textarea name="replyContent" lay-verify="replyContent" id="t_'+msgs[i].mid+'" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px;"></textarea>'+
		'</div>'+
		'<div class="layui-form-item">'+
			'<button class="layui-btn layui-btn-mini" lay-submit="formReply" lay-filter="formReply">提交</button>'+
		'</div>'+
		'</form>'+
		'</div></li>';
		 lis.push(html);
	}
	//next(lis.join(''), page < result.count);
	$(".blog-comment").html(lis.join(''));

    //监听留言提交
    form.on('submit(formLeaveMessage)', function (data) {
        var index = layer.load(1);
        //模拟留言提交
        setTimeout(function () {
        	layer.close(index);
        	var json = {aid:aid,content:data.field.editorContent,isYe:"",uid:user.uid};
        	$.ajax({
        		type: 'POST',
        		data: json,
        		url: _contextPath+"/msg/set.do",
        		success:function(result) {
        			if (result.code==1) {
        				var msg = result.item;
        				var time = util.timeAgo(formatDate(""+msg.time));
        				var html = '<li>'+
        	            '<div class="comment-parent">'+
        	            '<img src="images'+user.img+'"/>'+
        	            '<div class="info">'+
        	                '<span class="username">'+user.name+'</span>'+
        	            '</div>'+
        	            '<div class="content">'+
        	                	msg.content+
        	            '</div>'+
        	            '<p class="info">'+
        	            	'<span class="time"><i class="fa fa-clock-o"></i>&nbsp;'+time+'</span>'+
        	            	'<span class="dh">'+
        	            		'<a class="btn-dzan" href="javascript:dzan(\''+msg.mid+'\');" id="dzan_'+msg.mid+'"><img src="images/zan.png"></img>'+msg.dzan+'</a>'+
        	            		'<a class="btn-reply" href="javascript:btnReplyClick(\''+msg.mid+'\')" id="a_'+msg.mid+'");"><img src="images/huifu.png"></img>回复</a>'+
        	            	'</span>'+
        	            '</p>'+
        	            '</div>'+
        	            '<hr />'+
        	            '<div class="replycontainer layui-hide" id="'+msg.mid+'">'+
        				'<form class="layui-form" action="">'+
        				'<input type="hidden" name="isYe" value="'+msg.mid+'">'+
        				'<div class="layui-form-item">'+
        					'<textarea name="replyContent" lay-verify="replyContent" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px;"></textarea>'+
        				'</div>'+
        				'<div class="layui-form-item">'+
        					'<button class="layui-btn layui-btn-mini" lay-submit="formReply" lay-filter="formReply">提交</button>'+
        				'</div>'+
        				'</form>'+
        				'</div></li>';
        	            $('.blog-comment').prepend(html);
        	            $('#remarkEditor').val('');
        	            editIndex = layui.layedit.build('remarkEditor', {
        	                height: 150,
        	                tool: ['face', '|', 'left', 'center', 'right', '|', 'link'],
        	            });
        	            layer.msg("留言成功", { icon: 1 });
        			} else {
        				layer.msg(result.msg,{anim:6,icon:5});
        			}
        		}
        	});
        }, 500);
        return false;
    });

    //监听留言回复提交
    form.on('submit(formReply)', function (data) {
    	if (user==null) {
    		layer.msg("登录后才能提交",{anim:6,icon:5});
    		return false;
    	}
        if (data.field.replyContent == "") {
        	layer.msg("至少得有一个字吧",{anim:6,icon:5});
        	return false;
        } 
        var index = layer.load(1);
        //模拟留言回复
        setTimeout(function () {
            layer.close(index);
            var json = {aid:aid,content:data.field.replyContent,isYe:data.field.isYe,uid:user.uid};
            $.ajax({
        		type: 'POST',
        		data: json,
        		url: _contextPath+"/msg/set.do",
        		success:function(result) {
        			if (result.code==1) {
        				var msg = result.item;
        				var time = util.timeAgo(formatDate(""+msg.time));
        				var html = '<div class="comment-child">'+
        				'<img src="images'+user.img+'" alt="Absolutely" />'+
        				'<div class="info">'+
        					'<span class="username">'+user.name+'</span><span>'+msg.content+'</span>'+
        				'</div>'+
        				'<p class="info">'+
        	        	'<span class="time"><i class="fa fa-clock-o"></i>&nbsp;'+time+'</span>'+
        	        	'<span class="dh">'+
        	        		'<a class="btn-dzan" href="javascript:dzan(\''+msg.mid+'\');" id="dzan_'+msg.mid+'"><img src="images/zan.png"></img>'+msg.dzan+'</a>'+
        	        	'</span>'+
        	        	'</p><hr/>'+
        	        	'</div>';
        	            $(data.form).find('textarea').val('');
        	            $(data.form).parent('.replycontainer').before(html).siblings('.comment-parent').children('p').children('a').click();
        	            layer.msg("回复成功", { icon: 1 });
        			} else {
        				layer.msg(result.msg,{anim:6,icon:5});
        			}
        		}
        	});
        }, 500);
        return false;
    });
});

function btnReplyClick(elem) {
    var $ = layui.jquery;
    $('#'+elem).toggleClass('layui-hide');
    if ($('#a_'+elem).text() == '回复') {
        $('#a_'+elem).html('<i class="fa fa-caret-square-o-up" style="font-size:18px;"></i>&nbsp;收起');
    } else {
        $('#a_'+elem).html('<img src="images/huifu.png"></img>回复');
    };
}

function dzan(mid) {
    var i = parseInt($('#dzan_'+mid).text());
    i++;
    $.ajax({
		type: 'POST',
		data: {dzan:i,mid:mid},
		url: _contextPath+"/msg/dzan.do",
		success:function(result) {
			if (result.code==1) {
				  $('#dzan_'+mid).html('<img src="images/zan_d.png" class="animated bounceIn"></img>'+i);
			} else {
				layer.msg(result.msg,{anim:6,icon:5});
			}
		}
	});

}