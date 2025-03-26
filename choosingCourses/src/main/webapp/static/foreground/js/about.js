layui.use(['jquery', 'form', 'layedit'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    var layedit = layui.layedit;

    //评论和留言的编辑器
    var editIndex = layedit.build('remarkEditor', {
        height: 150,
        tool: ['face', '|', 'left', 'center', 'right', '|', 'link'],
    });
    //评论和留言的编辑器的验证
    layui.form.verify({
        content: function (value) {
            value = $.trim(layedit.getText(editIndex));
            if (value == "") return "至少得有一个字吧";
            layedit.sync(editIndex);
        }
    ,userId: function (value) {
        if (value == "" || value==null) return "至少你得先登录吧！";
    }
    ,replyContent: function (value) {
        if($.trim(value) == ""){
        	return "至少得有一个字吧!";
        }
    }
    });

    //监听留言提交
    form.on('submit(formLeaveMessage)', function (data) {
        var index = layer.load(1);
        //模拟留言回复
    		 var url = '/comment/add';
    	        $.ajax({
    	            type: "POST",
    	            url: url,
    	            data: data.field,
    	            success: function (res) {
    	                if(res.success){
    	                	 layer.close(index);
    	                     var content = data.field.content;
    	                     var html = '<li><div class="comment-parent"><img src="'+res.comment.user.headPortrait+'" alt="'+res.comment.user.nickname+'"/><div class="info"><span class="username">'+res.comment.user.nickname+'</span>';
    	                     if(res.comment.user.userId=='1'){
    	                    	 	html+=" <span class=\"is_bloger\">博主</span>&nbsp;";
						         }
    	                     html+='</div><div class="content">' + data.field.content + '</div><p class="info info-footer"><span class="time">'+res.comment.commentDate+'</span>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn-reply"href="javascript:;" style="color: #009688;font-size:14px;" onclick="btnReplyClick(this)">回复</a></p></div><hr /><!--回复表单默认隐藏--><div class="replycontainer layui-hide"><form class="layui-form"action="">            <input type="hidden" id="comment" name="comment" value="'+res.comment.commentId+'" />       <input type="hidden" id="user" lay-verify="userId" name="user" value="'+res.comment.user.userId+'" />                    <div class="layui-form-item"><textarea name="content"lay-verify="replyContent"placeholder="回复@'+res.comment.user.nickname+'"class="layui-textarea"style="min-height:80px;"></textarea></div><div class="layui-form-item"><button class="layui-btn layui-btn-mini"lay-submit="formReply"lay-filter="formReply">提交</button></div></form></div></li>';
    	                     $('.blog-comment').prepend(html);
    	                     $('#remarkEditor').val('');
    	                     editIndex = layui.layedit.build('remarkEditor', {
    	                         height: 150,
    	                         tool: ['face', '|', 'left', 'center', 'right', '|', 'link'],
    	                     });
    	                     layer.msg("评论成功", { icon: 1 });
    	                }
    	                else{
    	                	layer.msg("评论失败！");
    	                }
    	            },
    	            error: function(data) {
    	            	layer.msg("网络错误！");
    	            }
    	        });
        return false;
    });

    //监听留言回复提交
    form.on('submit(formReply)', function (data) {
        var index = layer.load(1);
        //模拟留言回复
    		 var url = '/reply/add';
    	        $.ajax({
    	            type: "POST",
    	            url: url,
    	            data: data.field,
    	            success: function (res) {
    	                if(res.success){
    	                	layer.close(index);
    	                	var html = '<div class="comment-child"><img src="'+res.reply.user.headPortrait+'" alt="'+res.reply.user.nickname+'"/><div class="info"><span class="username">'+res.reply.user.nickname+' : </span>';
    	                	if(res.reply.user.userId=='1'){
	                    	 	html+=" <span class=\"is_bloger\">博主</span>&nbsp;";
					         }
    	                	html+="回复 <span class=\"username\">@"+res.reply.comment.user.nickname+" </span>";
    	                	if(res.reply.comment.user.userId=='1'){
	                    	 	html+=" <span class=\"is_bloger\">博主</span>&nbsp;";
					         }
    	                	html+='：<span>' + data.field.content + '</span></div><p class="info"><span class="time">'+res.reply.replyDate+'</span></p></div>';
    	                	$(data.form).find('textarea').val('');
    	                    $(data.form).parent('.replycontainer').before(html).siblings('.comment-parent').children('p').children('a').click();
    	                    layer.msg("回复成功", { icon: 1 });
    	                }
    	                else{
    	                	layer.msg("回复失败！");
    	                }
    	            },
    	            error: function(data) {
    	            	layer.msg("网络错误！");
    	            }
    	        });
        return false;
    });
});
function btnReplyClick(elem) {
    var $ = layui.jquery;
    $(elem).parent('p').parent('.comment-parent').siblings('.replycontainer').toggleClass('layui-hide');
    if ($(elem).text() == '回复') {
        $(elem).text('收起')
    } else {
        $(elem).text('回复')
    }
}

systemTime();

function systemTime() {
	//获取系统时间。
	var dateTime = new Date();
	var year = dateTime.getFullYear();
	var month = dateTime.getMonth() + 1;
	var day = dateTime.getDate();
	var hh = dateTime.getHours();
	var mm = dateTime.getMinutes();
	var ss = dateTime.getSeconds();
	//分秒时间是一位数字，在数字前补0。
	mm = extra(mm);
	ss = extra(ss);

	//将时间显示到ID为time的位置，时间格式形如：19:18:02
	document.getElementById("time").innerHTML = year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
	//每隔1000ms执行方法systemTime()。
	setTimeout("systemTime()", 1000);
}

//补位函数。
function extra(x) {
    //如果传入数字小于10，数字前补一位0。
    if (x < 10) {
        return "0" + x;
    }
    else {
        return x;
    }
}