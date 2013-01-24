MainPageDna = function() {
}

MainPageDna.MainPageNotify = function(event, widget) { 
	
	var monitorOption = widget.getCustomObject("monitorOption");//监控配置
	var bubbleMessage = widget.getCustomObject("bubbleMessage"); //气泡消息
	var noteText = widget.getCustomObject("noteText");//随手记
	if(noteText != null) {
		//随手记
		widget.setCustomObject("noteText", null);
		mainPage.openNote(noteText);
	} else if(bubbleMessage != null) {
		MainProperty.bubbleMessage = bubbleMessage;
		mainPage.doLoginMessage("detail");
		widget.setCustomObject("bubbleMessage",null);//气泡消息
		if($("#desktopUnit").length > 0) {
			addMonitor(widget);
		}
	}else if(monitorOption != null) {
		//默认监控项显示
		widget.setCustomObject("monitorOption", null);
		mainPage.openMonitor(monitorOption);
	} 

}

MainPageDna.MessageNotify = function(event, widget) { 
	mainPage.closeShowmessage(); 
}

DNA.onReady(function() {
	var dis = DNA.getDisplay().getShell().get("mainpage");
	dis.setWidth(DNA.getDisplay().getShell().getWidth());
	dis.setHeight(54);
	dis.layout();
	//
	var json = dis.getCustomObject("icons");
	//dis.setCustomObject("theme", null);
	if(dis.getCustomObject("theme") != null && dis.getCustomObject("theme").index_style != null) {
		MainProperty.style = dis.getCustomObject("theme").index_style;
	}
	//dis.setCustomObject("theme", null);
	var $img = $("<img>", {
		"class" : "main_img"
	});
	$("body").prepend($img);
	changeService(MainProperty.style.split("_")[0], "_" + MainProperty.style.split("_")[1]);
	$img.animate({
		"width" : "100%",
		"height" : "100%"
	}, 1000, function() {
		$("body").css("background-color", "white");
		MainProperty.arrayshortcutIcons = json.shortcutIcons;
		//大图标
		MainProperty.arrayfucicons = json.funIcons;
		//保存主功能区的tab的标识，用于判断有没有标识下面没有图标的，如果没有图标，那么就删除这个tab页
		var tabfoldArray = "";
		for(var i = 0; i < MainProperty.arrayfucicons.length; i++) {
			var imgfunction = MainProperty.arrayfucicons[i];
			if(imgfunction.isPutMain && imgfunction.group != "06") {
				mainPage.createBigImg(null, null, imgfunction);
			}
			tabfoldArray += imgfunction.group;
		}
		
		if(tabfoldArray.indexOf("01") == -1) {
			$("#tabTextSale").parent().css("display","none");
		} 
		if(tabfoldArray.indexOf("02") == -1) {
			$("#tabTextInventory").parent().css("display","none");
		} 
		if(tabfoldArray.indexOf("03") == -1) {
			$("#tabTextFinance").parent().css("display","none");
		} 
		if(tabfoldArray.indexOf("04") == -1) {
			$("#tabTextBuy").parent().css("display","none");
		} 
		if(tabfoldArray.indexOf("05") == -1) {
			$("#tabTextOther").parent().css("display","none");
		}
		
		//快捷图标
		mainPage.initShortcutIcons();
	});
	
	//气泡消息
	var bubbleMessage = dis.getCustomObject("bubbleMessage");
	if(bubbleMessage != null) {
		MainProperty.bubbleMessage = bubbleMessage;
		mainPage.doLoginMessage("init");
		dis.setCustomObject("bubbleMessage",null);//气泡消息
	}else {
		MainProperty.bubbleMessageshow = false;
	}
	setInterval(function(){
		//判断是不是气泡是不是已经弹完了
		if(MainProperty.bubbleMessageshow) {
			return;
		}
		var bubbleMessage = {
			"bubbleMessage" : true
		}
		toServer("getbubbleMessage",bubbleMessage);
	},60000);
	
	
	addMonitor(dis);
	
	//信息提示
	var showMessagedis = DNA.getDisplay().getShell().get("showMessage");
	showMessagedis.setWidth(348);
	showMessagedis.setHeight(430);
	showMessagedis.layout();
	
	//判断是否启动系统
	var isStart = dis.getCustomObject("isStart");
	if(isStart != null && !isStart.start) {
		dis.notifyAction();
	}
	
	var tool = dis.getCustomObject("tool");
	if(tool != null) {
		dis.setCustomObject("tool",null);
		var indexTools = tool.indexTools;
		for(var i = 0 ; i < indexTools.length;i++) {
			tools = indexTools[i];
			if(tools == "clock") {
				mainPage.initClock(100, 100);
			}else if(tools == "calculator") {
				mainPage.openCalculator();
			}else if(tools == "date") {
				mainPage.openDate();
			}else if(tools == "note") {
				mainPage.openNote(tool.noteObject);
			}else if(tools == "weather") {
				mainPage.openWeather();
			}else if(tools == "phatnotes") {
				mainPage.openPhatnotes();
			}else if(tools == "desktop") {
				mainPage.openDeskop();
			}else if(tools == "monitor") {
				mainPage.openMonitor(tool.jsonMonitorObject);
			}
		}
		
	}
});

/**
 * 添加监控项
 * @param {} dis
 */
function addMonitor(dis) {
	//监控项
	var monitorOption = dis.getCustomObject("monitorOption");
	if(monitorOption != null) {
		dis.setCustomObject("monitorOption", null);
		MainProperty.monitorOption = monitorOption.monitors;
		initmonitorOption();
	}
}

function initmonitorOption() {
	//默认监控项显示
	var monitors = MainProperty.monitorOption;
	var list = new Array();
	for(var i = 0; i < monitors.length; i++) {
		var monitorOption = monitors[i];
		if(!monitorOption.isShow) {
			continue;
		}
		list.push(monitorOption);
	}
	if(list.length == 0) {
		$(".monitorUnit").remove();
		$("#desktopUnit").remove();
		return;
	}
	monitorProperty.monitorwindowX = $(window).width()-300;
	var height = 70 * list.length;
	$(".monitorUnit").remove();
	$("#desktopUnit").remove();
	var div2 = $("<div class='monitorUnit'></div>");
	div2.css("width", 250);
	div2.css("height", height);
	for(var i = 0; i < list.length; i++) {
		monitor.createUnitContent(div2, "app/theme/common/tool/monitor/" + list[i].name + ".png", list[i].name,list[i].number+"");
	}
	if(height != 0) {
		if(list.length < 3) {
			height += 20;
		}
		monitor.createMonitorwindow(monitorProperty,height,div2);
	}
}

function toServer(key, json) {
	var dis = DNA.getDisplay().getShell().get("mainpage");
	dis.setCustomObject(key, json);
	dis.notifyAction();
}

DNA.registerHandle("MainPageDna.MainPageNotify", MainPageDna.MainPageNotify);
DNA.registerHandle("MainPageDna.MessageNotify", MainPageDna.MessageNotify);