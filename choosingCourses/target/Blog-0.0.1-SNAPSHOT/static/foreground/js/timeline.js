var path = $("#path").val();
$(function(){
	  $.ajax({
          type: 'post',
          url: path+'/timeline/list',
          data: { 
          	"pageNo": 1, 
          },
          async:false,
          success: function (data) {
              if (data.result == 1) {
               	var datas = data.datas;
               	var _html='';
               	for (var i=0;i<datas.length;i++) {
               		_html += '<div class="timeline-year">';
               			_html += '<h2><a class="yearToggle" href="javascript:;">'+datas[i].year+'年</a><i class="fa fa-caret-down fa-fw"></i></h2>';
               		var monthDatas= datas[i].month;
               		for(var _month in monthDatas){
               			var _data = monthDatas[_month];
               			_html += '<div class="timeline-month">';
	               			_html += '<h3 class=" animated fadeInLeft"><a class="monthToggle" href="javascript:;">'+_month+'月</a><i class="fa fa-caret-down fa-fw"></i></h3>';
	               			_html += '<ul>';
	               			for(var j=0;j<_data.length;j++){
	               				_html += '<li class=" ">';
	               					_html += '<div class="h4  animated fadeInLeft">';
	               						_html += '<p class="date">'+_data[j].create_time+'</p>';
	               					_html += '</div>';
	               					_html += '<p class="dot-circle animated "><i class="fa fa-dot-circle-o"></i></p>';
	               					_html += '<div class="content animated fadeInRight">'+_data[j].content+'</div>';
	               					_html += '<div class="clear"></div>';
	               				_html += '</li>';
	               			}
	               			_html += '</ul>';
	               			_html += '</div>';
               		}
               		_html += '</div>';	
               	}
               	$(".timeline-line").after(_html);
              }
          },
          error: function (e) {
              layer.msg("获取数据失败");
          }
      });
	  $('.monthToggle').click(function () {
	        $(this).parent('h3').siblings('ul').slideToggle('slow');
	        $(this).siblings('i').toggleClass('fa-caret-down fa-caret-right');
	    });
	    $('.yearToggle').click(function () {
	        $(this).parent('h2').siblings('.timeline-month').slideToggle('slow');
	        $(this).siblings('i').toggleClass('fa-caret-down fa-caret-right');
	    });
})