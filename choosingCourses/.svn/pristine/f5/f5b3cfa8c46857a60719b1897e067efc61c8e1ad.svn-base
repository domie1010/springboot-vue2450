//新闻列表遍历
function classifyList(classifyId){
	$("#parentArticleList").empty();//移除元素的内容
	$("#parentArticleList").append("<div class=\"flow-default\" id=\"articleList\"></div>");
	layui.use('flow', function(){
		  var flow = layui.flow;
		  flow.load({
		    elem: '#articleList' //流加载容器
		    ,done: function(page, next){ //执行下一页的回调
		    	setTimeout(function(){
		    	$.post("/admin/article/list",{page:page,pageSize:5,classify:classifyId}, function(data) {
		    		 	var lis = [];
				        for(var i = 0; i < data.data.length; i++){
				        	var content=delHtmlTag(data.data[i].content);
				        	if(content.length>=80){
				        		content=content.substring(0,86);
				        	}
				          lis.push("<div class=\"article shadow animated zoomIn\">\n" + 
				  				"                		<div class=\"article-left \">\n" + 
								"                			<img src=\"/static/images/"+data.data[i].imageName+"\" alt=\""+data.data[i].title+"\"/>\n" + 
								"                		</div>\n" + 
								"                		<div class=\"article-right\">\n" + 
								"                        	<div class=\"article-title\">\n");
					          if(data.data[i].isTop==1){
					        	  lis.push("<span class=\"article_is_top\">置顶</span>&nbsp;");
					          }
					          if(data.data[i].isOriginal==1){
					        	  lis.push("<span class=\"article_is_yc\">原创</span>&nbsp;");
					          }else{
					        	  lis.push("<span class=\"article_is_zz\">转载</span>&nbsp;");
					          }
					   lis.push("                        		<a href=\"/foreground/detail/"+data.data[i].articleId+"\">"+data.data[i].title+"</a>\n" + 
								"                        	</div>\n" + 
								"                        	<div class=\"article-abstract\">\n" + 
								"                          	"+content+"</div>\n" + 
								"                        </div>\n" + 
								"                        <div class=\"clear\"></div>\n" + 
								"                        <div class=\"article-footer\">\n" + 
								"	                        <span><i class=\"fa fa-clock-o\"></i>&nbsp;&nbsp;"+data.data[i].publishDate+"</span>\n" + 
								"	                        <span class=\"article-author\"><i class=\"fa fa-user\"></i>&nbsp;&nbsp;' "+data.data[i].author+"</span>\n" + 
								"	                        <span><i class=\"fa fa-tag\"></i>&nbsp;&nbsp;<a href=\"javascript:classifyList("+data.data[i].classify.classifyId+");\"> "+data.data[i].classify.classifyName+"</a></span>\n" + 
								"	                        <span class=\"article-viewinfo\"><i class=\"fa fa-eye\"></i>&nbsp;"+data.data[i].click+"</span>\n" + 
								"	                        <span class=\"article-viewinfo\"><i class=\"fa fa-commenting\"></i>&nbsp;"+data.data[i].commentNum+"</span>\n" + 
								"                    	</div>\n" + 
								"                	</div>");
				        }
		    		
		        //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
		        //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
		        next(lis.join(''), page < data.totalPage);
		    	});
		    	});
		    }
		  });
		  
		});
}
//格式化HTML标签
function delHtmlTag(str){
	return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
}