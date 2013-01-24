//监控器,这个是本系统专用的没必要做成一个iframe
var monitor = function() {
}

var monitorProperty = {
	monitorwindowX : 600,//监控器窗口的xy坐标
	monitorwindowY : 100
}

//监控配置
monitor.deploy = function(monitors) {
	var div = $("<div class='monitorDiv'></div>");
	for(var i = 0; i < monitors.length; i++) {
		var monitorOption = monitors[i];
		var id = monitorOption.name;
		var imgsrc = "app/theme/common/tool/monitor/" + monitorOption.name + ".png";
		var text = monitorOption.isShow == false ? "不显示":"显示";
		var monitorNumber = monitorOption.number
		var content_div = $("<div class='monitorContent'></div>");
		content_div.data("id", id);
		content_div.data("imgsrc", imgsrc);
		content_div.data("monitorNumber", monitorNumber);
		var left_div = $("<div class='monitorContentLeft'></div>");
		content_div.append(left_div);
		var center_div = $("<div class='monitorContentCenter'></div>");
		content_div.append(center_div);
		var right_div = $("<div class='monitorContentRight'></div>");
		content_div.append(right_div);
		div.append(content_div); 
		var center_div_left = $("<div class='center_div_left'><img src='app/theme/common/tool/monitor/code/"+monitorOption.code+".png'></img><div id='center_div_left_text'>"+monitorOption.title+"</div></div><div id='monitorCutline'><img src='app/theme/common/tool/monitor/Mon_con_botton_cutline.png'></img></div>");
		center_div.append(center_div_left);
		var center_div_right = $("<div class='center_div_right'><div class='monitorUnitCenterDivCutline'></div>" + "</div>");
		var imgsrc = "app/theme/common/tool/monitor/Mon_con_shows_open.png";
		if(text == "不显示") {
			imgsrc = "app/theme/common/tool/monitor/Mon_con_shows_shut.png";
		} 
		var divimg = $("<div class='divimg'><img src='"+imgsrc+"'/><font>" + text + "</font></div>");
		center_div_right.append(divimg);
		center_div.append(center_div_right);

		content_div.click(function() {
			var isshow = false;
			if($(this).find("font").text() == '显示') {
				$(this).find("font").text("不显示");
				$(this).find(".divimg").find("img").attr("src", "app/theme/common/tool/monitor/Mon_con_shows_shut.png");
			} else {
				$(this).find("font").text("显示");
				$(this).find(".divimg").find("img").attr("src", "app/theme/common/tool/monitor/Mon_con_shows_open.png");
				isshow = true;
			}
			
			var monitorUpdate = {
				"id" : $(this).data("id"),
				"isshow":isshow
			}
			toServer("monitorUpdate", monitorUpdate);
			monitor.openmonitorwindow();
		});
	}
	return div;
};

/**
 * 打开监控器窗口
 */
monitor.openmonitorwindow = function() {
	var list = new Array();
	$(".monitorContent").each(function() {
		if($(this).find(".divimg").text() == "显示") {
			list.push($(this));
		}
	});
	var height = 70 * list.length;
	$(".monitorUnit").remove();
	$("#desktopUnit").remove();
	var div = $("<div class='monitorUnit'></div>");
	div.css("width", 250);
	div.css("height", height);
	for(var i = 0; i < list.length; i++) {
		monitor.createUnitContent(div, list[i].data("imgsrc"), list[i].data("id"),list[i].data("monitorNumber")+"");
	}
	if(height != 0) {
		if(list.length < 3) {
			height += 20;
		}
		monitor.createMonitorwindow(monitorProperty,height,div);
	}
}

monitor.createMonitorwindow = function(monitorProperty,height,div) {
	mainPage.toolframe("desktopUnit", monitorProperty.monitorwindowX, monitorProperty.monitorwindowY, 270, height + 20, "监控器", "app/theme/common/images/level1/tool/System-monitor.png", div);
}

monitor.createUnitContent = function(div, imgsrc, id,monitorNumber) {
	for(var i = 0 ; i < 3;i++) {
		monitorNumber = monitorNumber.charAt(i) == "" ? "0" + monitorNumber : monitorNumber;
	}
	var content_div = $("<div id=" + id + " class='monitorUnitContent'></div>");
	var left_div = $("<div class='monitorUnitContentLeft'></div>");
	content_div.append(left_div);
	var center_div = $("<div class='monitorUnitContentCenter'></div>");
	content_div.append(center_div);
	var right_div = $("<div class='monitorUnitContentRight'></div>");
	content_div.append(right_div);
	div.append(content_div);

	var center_div_left = $("<div class='monitorUnitCenterDivLeft'><img src='" + imgsrc + "'></img></div>");
	center_div.append(center_div_left);
	var center_div_right = $("<div class='monitorUnitCenterDivRight'></div>");
	var number_1 = $("<div class='Number'></div>");
	center_div_right.append(number_1);
	if(monitorNumber.charAt(0) != "" && monitorNumber.charAt(0) != "0") {
		var number = Number(monitorNumber.charAt(0));
		number = 28 * number;
		number_1.css("background-position","0 "+-number+"");
	}
	var number_2 = $("<div class='Number'></div>");
	center_div_right.append(number_2);
	if(monitorNumber.charAt(1) != "" && monitorNumber.charAt(1) != "0") {
		var number = Number(monitorNumber.charAt(1));
		number = 28 * number;
		number_2.css("background-position","0 "+-number+"");
	}
	var number_3 = $("<div class='Number'></div>");
	center_div_right.append(number_3);
	if(monitorNumber.charAt(2) != "" && monitorNumber.charAt(2) != "0") {
		var number = Number(monitorNumber.charAt(2));
		number = 28 * number;
		number_3.css("background-position","0 "+-number+"");
	}
	var closeImg = $("<img class='closeimg' src='app/theme/common/tool/monitor/monitor_close_n.png'></img>");
	closeImg.click(function() {
		var id  = $(this).parents(".monitorUnitContent").attr("id");
		var monitorUpdate = {
			"id" : id,
			"isshow":false
		}
		toServer("monitorUpdate", monitorUpdate);
		if($(".monitorContent").length > 0) {
			$(".monitorContent").each(function() {
				if($(this).data("id") != null && $(this).data("id") == id) {
					$(this).find("font").text("不显示");
					$(this).find(".divimg").find("img").attr("src", "app/theme/common/tool/monitor/Mon_con_shows_shut.png");
				}
			});
			monitor.openmonitorwindow();
		}else {
			var number = MainProperty.monitorOption.length;
			for(var i=0;i<number;i++) {
				var monitorOption = MainProperty.monitorOption[i];
				if(!monitorOption.isShow) {
					continue;
				}else if(monitorOption.name == id) {
					MainProperty.monitorOption.remove(monitorOption);
					break;
				}
			}
			initmonitorOption();
		}
		return false;
	});
	closeImg.hover(function(){
		$(this).attr("src","app/theme/common/tool/monitor/monitor_close_h.png");
	},function(){
		$(this).attr("src","app/theme/common/tool/monitor/monitor_close_n.png");
	});
	center_div_right.append(closeImg);
	
	center_div.append(center_div_right);
	
	content_div.click(function() {
		mainPage.showmessage($(this).attr("id"));
	});
}