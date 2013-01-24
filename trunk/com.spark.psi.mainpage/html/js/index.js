var mainPage = function() {
}
var MainProperty = {
	arrayfucicons : null, // 图标的map
	arrayshortcutIcons : null, // 快捷图标的map
	ismove : false, // 是否在移动图标
	ismenuPage : true,// 主功能区是否打开
	isimgmove : false,// 是否在移动桌面大图标
	ishideDivMove : false, // 隐藏的div是否移动
	hideDivLeft : 0,// 时钟小工具专用隐藏的DIV的坐标
	hideDivTop : 0,// 时钟小工具专用隐藏的DIV的坐标
	style : "blue_bg3",// 默认的背景图片样式
	isopenMessageWindow : false,// 是否打开的消息提示窗口
	bubbleMessage : null,// 从后台取过来的气泡信息
	bubbleMessageshow : true,// 小气泡显示的消息是否已经显示完毕
	isopenSaasNavigator : false, // 是否通过消息提示窗口打开过二级界面，处理z-index问题
	monitorOption : null
	// 存放监控项的数据
};

$(document).ready(function() {
	$("#statusAreaLeft").mouseover(function() {
				if (!MainProperty.isopenMessageWindow) {
					MainProperty.isopenMessageWindow = true;
					mainPage.showmessage(null);
				}
			});

	$("body").mouseover(function(event) {
		var obj = $(event.target);
		if (obj.attr("class") == "naviMenuBut") {
			// 移动到下面那个箭头
			if (SaasNavigator.isModalWindowVisible()) {
				return;
			}

			if (SaasNavigator.isMainWindowVisible()) {
				var div = $("<div></div>");
				div.addClass("hideDiv");
				$("body").append(div);
			}
			$("#menuPage").stop();

			$("#menuPage").animate({
						bottom : "0px"
					}, 500, function() {
						MainProperty.ismenuPage = true;

						$(".tabfold").each(function(index) {
									if ($(this).css("display") != "none") {
										mainPage.openMenu($(this).children()
												.eq(0));
										return false;
									}
								});
					});
			$(".tabfold").each(function() {
				$(this).css(
						"background-image",
						$(this).css("background-image").replace(
								"TabFolder-h.png", "TabFolder-n.png"))
			});
			$("#tabTextSale").parent(".tabfold").css(
					"background-image",
					$("#tabTextSale").parent(".tabfold")
							.css("background-image").replace("TabFolder-n.png",
									"TabFolder-h.png"));

		} else if (obj.attr("class") == "main_img"
				|| obj.attr("class") == "hideDiv") {
			// 移动到桌面
			mainPage.menuPageClose();
		} else if (obj.attr("class") == "tabTextn") {
			// 移动到主功能模块
			// 改变颜色
			$(".tabfold").each(function() {
				$(this).css(
						"background-image",
						$(this).css("background-image").replace(
								"TabFolder-h.png", "TabFolder-n.png"))
			});
			obj.parent(".tabfold").css(
					"background-image",
					obj.parent(".tabfold").css("background-image").replace(
							"TabFolder-n.png", "TabFolder-h.png"));

			mainPage.openMenu(obj);
		} else if (obj.attr("class") == "statusAreaRightCommButton") {
			// 移动到小工具
			if ($(".commonFuncPage").length > 0) {
				return;
			}
			var div = $("<div></div>");
			div.addClass("commonFuncPage");
			var divtop = $("<div></div>");
			divtop.addClass("commonFuncTop");
			var divcenter = $("<div></div>");
			divcenter.addClass("commonFuncCenter");
			div.append(divtop);
			div.append(divcenter);
			mainPage.initTool(divcenter,
					"app/theme/common/images/level1/tool/System-monitor.png",
					"监控配置", function() {
						var monitor = {
							monitor : "monitor"
						};
						toServer("monitor", monitor);
					});
			mainPage
					.initTool(
							divcenter,
							"app/theme/common/images/level1/tool/System-arrange_lcons.png",
							"个性桌面", function() {
								mainPage.openDeskop();
							});
			mainPage
					.initTool(
							divcenter,
							"app/theme/common/images/level1/tool/System-calculator.png",
							"计&nbsp;算&nbsp;器", function() {
								mainPage.openCalculator();
							});
			mainPage.initTool(divcenter,
					"app/theme/common/images/level1/tool/System-clock.png",
					"时&nbsp;&nbsp;&nbsp;&nbsp;钟", function() {
						mainPage.initClock(100, 100);
					});
			mainPage.initTool(divcenter,
					"app/theme/common/images/level1/tool/System-date.png",
					"日&nbsp;&nbsp;&nbsp;&nbsp;历", function() {
						mainPage.openDate();
					});
			mainPage.initTool(divcenter,
					"app/theme/common/images/level1/tool/System-phatnotes.png",
					"随&nbsp;手&nbsp;记", function() {
						var note = {
							oper : "get"
						};
						toServer("note", note);
					});
			mainPage.initTool(divcenter,
					"app/theme/common/images/level1/tool/System-weather.png",
					"天&nbsp;&nbsp;&nbsp;&nbsp;气", function() {
						mainPage.openWeather();
					});
			mainPage
					.initTool(
							divcenter,
							"app/theme/common/images/level1/tool/System-network_speed.png",
							"测&nbsp;速&nbsp;器", function() {
								mainPage.openPhatnotes();
							});
			$("body").append(div);
			div.stop();
			div.animate({
						"bottom" : "20px"
					}, 500);
			var div = $("<div></div>");
			div.addClass("hideDiv");
			$("body").append(div);
		}

	});

	$("body").mousemove(function(event) {
		if ($(".hidemovediv").length > 0) {
			$(".hidemovediv").css("left",
					event.clientX - MainProperty.hideDivLeft);
			$(".hidemovediv").css("top",
					event.clientY - MainProperty.hideDivTop);
		} else if ($(".clockHideDiv").css("display") == "block") {
			$(".clockHideDiv").css("left",
					event.clientX - MainProperty.hideDivLeft);
			$(".clockHideDiv").css("top",
					event.clientY - MainProperty.hideDivTop);
		}

	});

	$("body").mousedown(function(event) {
				var obj = $(event.target);
				if (obj.parents().attr("id") == 'top') {
					mainPage.createhideDiv(obj.parent().parent());
				} else if (obj.attr("class") == 'clockmovediv') {
					mainPage.createhideDiv(obj);
				} else if (obj.attr("class") == 'note_center_top') {
					mainPage.createhideDiv(obj.parent());
				}
			});

	$("body").mouseup(function(event) {
		var obj = $(event.target);
		var id = $(".hidemovediv").data("id");
		$(".hidemovediv").remove();
		if (id == "clock") {
			$(".clockmovediv").css("left",
					event.clientX - MainProperty.hideDivLeft);
			$(".clockmovediv").css("top",
					event.clientY - MainProperty.hideDivTop);
		} else if (id != null) {
			$("#" + id).css("left", event.clientX - MainProperty.hideDivLeft);
			$("#" + id).css("top", event.clientY - MainProperty.hideDivTop);
			if (id == "desktopUnit") {
				monitorProperty.monitorwindowX = event.clientX
						- MainProperty.hideDivLeft;
				monitorProperty.monitorwindowY = event.clientY
						- MainProperty.hideDivTop;
			}
		}
	});

});

/**
 * 显示消息提示页面
 */
mainPage.showmessage = function(message) {
	var showMessagedis = DNA.getDisplay().getShell().get("showMessage");
	var open = {
		type : message
	}
	showMessagedis.setCustomObject("open", open);
	showMessagedis.notifyAction();
	$("#showmessage").css("display", "block");
	if (!MainProperty.isopenSaasNavigator) {
		MainProperty.isopenSaasNavigator = true;
	} else {
		$("#showmessage").css("z-index", 99999);
	}
}

mainPage.createhideDiv = function(obj) {
	var div = $("<div class='hidemovediv'></div>");
	div.css("position", "absolute");
	div.css("left", obj.offset().left);
	div.css("top", obj.offset().top);
	div.css("z-index", "99999");
	div.css("width", obj.width());
	div.css("height", obj.height());
	div.css("cursor", "pointer");
	div.css("border-style", "dashed");
	div.css("border-width", "1");
	div.data("id", obj.attr("id"));
	$("body").append(div);
	// event得到鼠标相对于body的位置,然后减去当前组件相对于body的位置,就等于鼠标相对于当前组件的位置
	MainProperty.hideDivLeft = event.clientX - obj.offset().left;
	MainProperty.hideDivTop = event.clientY - obj.offset().top;
}

/**
 * 打开计算器
 */
mainPage.openCalculator = function() {
	mainPage
			.moveframe(
					"calculator",
					"app/theme/common/images/level1/tool/System-calculator.png",
					"计算器",
					225,
					250,
					500,
					$(window).height() - 400,
					"<iframe src='app/calculator/calculator.html' style='width:100%;height:100%;' allowtransparency='true'  marginwidth='0' framespacing='0' marginheight='0' frameborder='0'></iframe>");
	mainPage.showTool("calculator");
}

/**
 * 打开监控配置
 */
mainPage.openMonitor = function(monitorOption) {
	var monitors = monitorOption.monitors;
	var height = 110;
	if (monitors.length > 2) {
		var number = monitors.length / 2;
		if (number % 2 != 0) {
			number++;
		}
		height += 40 * number;
	}
	mainPage.toolframe("monitor", 100, 100, 455, height, "监控配置",
			"app/theme/common/images/level1/tool/monitor_icon.png", monitor
					.deploy(monitors));
	var list = new Array();
	$(".monitorContent").each(function() {
				if ($(this).find(".divimg").text() == "显示") {
					list.push($(this));
				}
			});
	var height = 70 * list.length;
	$(".monitorUnit").remove();
	$("#desktopUnit").remove();
	var div2 = $("<div class='monitorUnit'></div>");
	div2.css("width", 250);
	div2.css("height", height);
	for (var i = 0; i < list.length; i++) {
		monitor.createUnitContent(div2, list[i].data("imgsrc"), list[i]
						.data("id"), list[i].data("monitorNumber") + "");
	}
	if (height != 0) {
		if (list.length < 3) {
			height += 20;
		}
		monitor.createMonitorwindow(monitorProperty, height, div2);
	}
	mainPage.showTool("monitor");

}

/**
 * 打开个性桌面
 */
mainPage.openDeskop = function() {
	mainPage.toolframe("desktop", 600, 100, 340, 470, "个性桌面",
			"app/theme/common/icon/desk.png", desktop.createContent());
	mainPage.showTool("desktop");
}

/**
 * 打开日历
 */
mainPage.openDate = function() {
	mainPage
			.moveframe(
					"date",
					"app/theme/common/images/level1/tool/System-date.png",
					"日历",
					237,
					290,
					400,
					$(window).height() - 400,
					"<iframe src='app/Datepicker/datepicker.html' style='width:100%;height:100%;overflow: hidden;' allowtransparency='true'  marginwidth='0' framespacing='0' marginheight='0' frameborder='0' ></iframe>");
	mainPage.showTool("date");
}

/**
 * 打开测速器
 */
mainPage.openPhatnotes = function() {
	mainPage
			.toolframe(
					"phatnotes",
					100,
					100,
					240,
					150,
					"测速器",
					"app/theme/common/images/level1/tool/System-phatnotes.png",
					"<iframe src='app/speed/testspeed.html' style='width:100%;height:100%;' allowtransparency='true'  marginwidth='0' framespacing='0' marginheight='0' frameborder='0'></iframe>");
	mainPage.showTool("phatnotes");
}

/**
 * 打开天气预报
 */
mainPage.openWeather = function() {
	mainPage
			.moveframe(
					"weather",
					"app/theme/common/images/level1/tool/System-weather.png",
					Weathertext,
					250,
					200,
					$(window).width() - 400,
					$(window).height() - 300,
					"<iframe src='app/weather/weather.html' style='width:100%;height:100%;overflow: hidden;' allowtransparency='true'  marginwidth='0' framespacing='0' marginheight='0' frameborder='0' ></iframe>");
	mainPage.showTool("weather");
}

/**
 * 打开随手记
 */
mainPage.openNote = function(noteText) {
	var value = null;
	if (noteText.text != null) {
		value = noteText.text;
	}
	mainPage.initNote("note", value, 300, 300, 100, 100);
	mainPage.showTool("note");
}

// 显示对应的功能图标
mainPage.openMenu = function(obj) {
	if (obj.attr("id") == "tabTextSale") {
		mainPage.createImg("01");
	} else if (obj.attr("id") == "tabTextInventory") {
		mainPage.createImg("02");
	} else if (obj.attr("id") == "tabTextFinance") {
		mainPage.createImg("03");
	} else if (obj.attr("id") == "tabTextBuy") {
		mainPage.createImg("04");
	} else if (obj.attr("id") == "tabTextOther") {
		mainPage.createImg("05");
	}
}

/**
 * 初始化工具
 */
mainPage.initTool = function(divcenter, img, text, func) {
	var divs = $("<div></div>");
	divs.addClass("commonFuncCenterDiv");
	divs.hover(function() {
				$(this)
						.css("background-image",
								"url(app/theme/common/images/level1/tool/System-bj-h.png)");
				$(this).css("width",
						Number($(this).css("width").replace("px", "")) - 15);
				$(this).css("margin-left", "10px");
				$(this).find(".commonFuncCenterDivImg").css("margin-left",
						"5px");
			}, function() {
				$(this).css("background-image", "");
				$(this).css("width",
						Number($(this).css("width").replace("px", "")) + 15);
				$(this).css("margin-left", "3px");
				$(this).find(".commonFuncCenterDivImg").css("margin-left",
						"13px");
			});
	divcenter.append(divs);
	var divsImg = $("<div><img src='" + img + "'></img></div>");
	divsImg.addClass("commonFuncCenterDivImg");
	divs.append(divsImg);
	var divText = $("<div>" + text + "</div>");
	divText.addClass("commonFuncCenterDivText");
	divs.append(divText);
	var divsSpace = $("<div><img src='app/theme/common/images/level1/tool/System-bj-line.png'></img></div>");
	divsSpace.addClass("commonFuncCenterSpace");
	divcenter.append(divsSpace);
	divs.click(func);
}
/**
 * 关掉主功能区
 */
mainPage.menuPageClose = function() {
	if (!MainProperty.ismove) {
		MainProperty.ismenuPage = false;
		$("#menuPage").stop();
		$("#menuPage").animate({
					bottom : "-330px"
				}, 500);
		$(".bigImgTop").hide(200);
		$(".imgDiv").remove();
		$(".hideDiv").remove();
		$(".closegifon_clcok").remove();
		$(".commonFuncPage").stop();
		$(".commonFuncPage").animate({
					"bottom" : "-260px"
				}, 500, function() {
					$(".commonFuncPage").remove();
				});
	}
}
/**
 * 创建桌面上的图标
 */
mainPage.createBigImg = function(x, y, imgfunction) {
	var divImg = $("<div></div>");
	divImg.data("imgfunction", imgfunction);
	$("body").append(divImg);
	var divTop = $("<div></div>");
	divImg.append(divTop);
	var divCenter = $("<div></div>");
	divImg.append(divCenter);
	divImg.addClass("bigImg")
	divTop.addClass("bigImgTop");
	divTop.click(function() {
		var divImg = $(this).parents(".bigImg").eq(0);
		divImg.animate({
					left : $(".naviMenuBut").offset().left - 13,
					top : $(".naviMenuBut").offset().top - 100
				}, 500, function() {
					divImg.animate({
								top : $(document).height()
							}, 500, function() {
								var changeImgState = {
									code : divImg.data("imgfunction").code,
									state : "moveIn"
								}
								for (var i = 0; i < MainProperty.arrayfucicons.length; i++) {
									var arrayfucicon = MainProperty.arrayfucicons[i];
									if (arrayfucicon.code == divImg
											.data("imgfunction").code) {
										arrayfucicon.isPutMain = false;
									}
								}
								toServer("changeImgState", changeImgState);
								divImg.remove();
							});
				});
	});
	divCenter.addClass("bigImgCenter");
	divCenter.data("imgfunction", imgfunction);
	divCenter.click(function() {
				mainPage.imgclick($(this));
			});
	var height = $(document).height();
	divImg.mouseover(function() {
				$(this).find(".bigImgTop").show(200);
			});
	divImg.easydrag();
	divImg.ondrag(function(event) {
				divImg.css("z-index", 4);
				MainProperty.ismove = true;
				MainProperty.isimgmove = true;
			});
	divImg.ondrop(function(event) {
				divImg.css("z-index", 2);
				mainPage.moveIcons($(event.target).parents(".bigImg"), true);
			});
	// TODO:添加divCenter的具体图片和信息
	var divCenterImg = $("<div></div>");
	divCenterImg.addClass("bigImgDivTop");
	divCenterImg.css("background-image", "url(" + imgfunction.largeNormalIcon
					+ ")");
	divCenterImg.data("imgfunction", imgfunction);
	divCenterImg.hover(function() {
				$(this).css(
						"background-image",
						"url(" + $(this).data("imgfunction").largeHoverIcon
								+ ")");
			}, function() {
				$(this).css(
						"background-image",
						"url(" + $(this).data("imgfunction").largeNormalIcon
								+ ")");
			});
	var divCenterText = $("<div>" + imgfunction.name + "</div>");
	divCenterText.addClass("bigImgDivText");
	divCenter.append(divCenterImg);
	divCenter.append(divCenterText);

	if (x == null && y == null) {
		divImg.css("left", imgfunction.x);
		divImg.css("top", imgfunction.y);
	} else {
		divImg.css("left", x);
		divImg.css("top", y);
		mainPage.moveIcons(divImg, true);
	}

}
/**
 * 创建主功能区图标
 */
mainPage.createImg = function(group) {
	if (!MainProperty.ismenuPage || MainProperty.ismove) {
		return;
	}
	$("body").find(".imgDiv").remove();
	var left = $("#menuPage").css("left").replace("px", "");
	var x = Number(left) - 240;
	var y = 300;
	var number = 0;
	if (MainProperty.arrayfucicons) {
		for (var i = 0; i < MainProperty.arrayfucicons.length; i++) {
			var arrayfucicon = MainProperty.arrayfucicons[i];
			if (arrayfucicon.group == group) {
				x += 80;
				if (number % 6 == 0) {
					y -= 100;
					x = Number(left) - 240;
				}
				mainPage.initImg(x, y, arrayfucicon);
				number++;
			}
		}
	}
}
/**
 * 初始化主功能区的图标
 */
mainPage.initImg = function(x, y, imgfunction) {
	var div = $("<div></div>");
	div.data("imgfunction", imgfunction);
	div.click(function() {
				mainPage.imgclick($(this));
			});
	div.css("left", x);
	div.css("bottom", y);
	var textDiv = $("<div>" + imgfunction.name + "</div>");
	var imgDiv = $("<div></div>");
	div.append(imgDiv);
	div.append(textDiv);
	div.addClass("imgDiv");
	imgDiv.addClass("imgDivTop");
	textDiv.addClass("imgDivText");
	$("body").append(div);

	imgDiv.css("background-image", "url(" + imgfunction.middleNormalIcon + ")");
	imgDiv.data("imgfunction", imgfunction);
	imgDiv.hover(function() {
		if (!MainProperty.ismove) {
			$(this).css("background-image",
					"url(" + $(this).data("imgfunction").middleHoverIcon + ")");
		}

	}, function() {
		if (!MainProperty.ismove) {
			$(this)
					.css(
							"background-image",
							"url("
									+ $(this).data("imgfunction").middleNormalIcon
									+ ")");
		}
	});
	if (!imgfunction.isPutMain && !SaasNavigator.isMainWindowVisible()) {
		div.easydrag();
		div.ondrag(function() {
					MainProperty.ismove = true;
					MainProperty.isimgmove = true;
				});
		div.ondrop(function(event) {
					// 在主功能区里面拖动,不需要生成大图标
					MainProperty.ismove = false;
					if (event.clientX > $("#menuPage").offset().left
							&& event.clientX < $("#menuPage").width()
									+ $("#menuPage").offset().left
							&& event.clientY > $("#menuPage").offset().top) {
						return;
					}

					$("#menuPage").stop();
					$(".imgDiv").hide();
					$("#menuPage").animate({
								bottom : "-330px"
							}, 500);
					/*
					 * if(event.clientY < 40) { event.clientY = 40; }else
					 * if(event.clientY > ($(window).height() - 135) ) {
					 * event.clientY = ($(window).height() - 135); }
					 * if(event.clientX <= 0) { event.clientX = 0; }else
					 * if(event.clientX > ($(window).width()) -80) {
					 * event.clientX = ($(window).width() -80); }
					 */

					mainPage.createBigImg(event.clientX - 36, event.clientY
									- 36, $(event.target).data("imgfunction"));
					// 和后台交互，把桌面上的图标加上去
					var changeImgState = {
						code : $(event.target).data("imgfunction").code,
						state : "moveOut",
						x : event.clientX - 36,
						y : event.clientY - 36
					}
					toServer("changeImgState", changeImgState);
					$(event.target).remove();
				});
	}

}
/**
 * 6个快捷图标构建
 */
mainPage.initShortcutIcons = function() {
	$(".navigation1CenterImg").remove();
	for (var i = 0; i < MainProperty.arrayshortcutIcons.length; i++) {
		var imgfunction = MainProperty.arrayshortcutIcons[i];
		if (imgfunction.group == "06") {
			continue;
		}
		var div = $("<div></div>");
		div.click(function() {
					mainPage.imgclick($(this));
					MainProperty.isimgmove = false;

				});
		div.data("imgfunction", imgfunction);
		var textDiv = $("<div>" + imgfunction.name + "</div>");
		var imgDiv = $("<div></div>");
		div.append(imgDiv);
		div.append(textDiv);
		div.addClass("navigation1CenterImg");
		imgDiv.addClass("navigation1CenterImgTop");
		textDiv.addClass("navigation1CenterImgText");
		// X位置 = navigation1BeforeCenter容器X位置 + 相隔图标的宽度
		var left = $(".navigation1BeforeCenter").offset().left - 10
				+ $(".navigation1CenterImg").length * 65;
		if ($(".navigation1CenterImg").length >= 3) {
			// 第三个和第四个中间隔了一个图标
			var left = left + 70;
		}
		var top = $(".navigation1BeforeCenter").offset().top;
		div.css("left", left);
		div.css("top", top + 5);

		imgDiv.css("background-image", "url(" + imgfunction.middleNormalIcon
						+ ")");
		imgDiv.data("imgfunction", imgfunction);
		imgDiv.hover(function() {
			$(this).css("background-image",
					"url(" + $(this).data("imgfunction").middleHoverIcon + ")");
		}, function() {
			$(this)
					.css(
							"background-image",
							"url("
									+ $(this).data("imgfunction").middleNormalIcon
									+ ")");
		});
		$("body").append(div);
	}
}
/**
 * 双击打开功能界面
 */
mainPage.imgclick = function(div) {
	// 保存所有的桌面上的坐标
	if (MainProperty.isimgmove) {
		MainProperty.isimgmove = false;
		return;
	}
	var toServerArray = new Array();
	for (var i = 0; i < MainProperty.arrayfucicons.length; i++) {
		var imgfunction = MainProperty.arrayfucicons[i];
		if (imgfunction != null && imgfunction.isPutMain) {
			toServerArray.push(imgfunction);
		}
	}
	if (toServerArray.length > 0) {
		var openWindowsjsonObject = {
			"toServerArray" : toServerArray
		}
		toServer("openWindowsjsonObject", openWindowsjsonObject);
	}
	SaasNavigator.openMainFunction(div.data("imgfunction").code, div
					.data("imgfunction").name);
	mainPage.closeShowmessage();
	mainPage.menuPageClose();
	$("#showmessage").css("z-index", 99999);
}
/**
 * 移动之后做碰撞处理
 */
mainPage.moveIcons = function(dragImg, isPutMain) {
	MainProperty.ismove = false;
	for (var i = 0; i < MainProperty.arrayfucicons.length; i++) {
		var imgfunction = MainProperty.arrayfucicons[i];
		// 得到所有保存在map里面的图标的坐标,遍历这些坐标,
		// 当用户移动图标到某一个位置点的时候,去判断该点附近(+-10px)是否存在已有的图标,
		// 如果存在,再移动该图标向右下20px,以防止重叠
		if (imgfunction != null) {
			if (imgfunction.isPutMain
					&& (imgfunction.x != 0 || imgfunction.y != 0)) {
				if (((dragImg.offset().left - imgfunction.x <= 10 && dragImg
						.offset().left
						- imgfunction.x > 0) || (dragImg.offset().left
						- imgfunction.x > -10 && dragImg.offset().left
						- imgfunction.x <= 0))
						&& ((dragImg.offset().top - imgfunction.y > 0 && dragImg
								.offset().top
								- imgfunction.y <= 10) || (dragImg.offset().top
								- imgfunction.y > -10 && dragImg.offset().top
								- imgfunction.y <= 0))) {
					if (imgfunction.code != dragImg.data("imgfunction").code) {
						dragImg.css("left", imgfunction.x + 20 + "px");
						dragImg.css("top", imgfunction.y + 20 + "px");
						mainPage.moveIcons(dragImg, isPutMain);
					}
				}
			}
			if (imgfunction.code == dragImg.data("imgfunction").code) {
				imgfunction.x = dragImg.offset().left;
				imgfunction.y = dragImg.offset().top;
				imgfunction.isPutMain = isPutMain;
			}
		}
	}
}
/**
 * 计算器,日历,天气的框架
 */
mainPage.moveframe = function(id, imgsrc, text, width, height, x, y, composite) {
	$("#" + id).remove();
	alldiv = $("<div></div>");
	$("body").append(alldiv);

	alldiv.addClass("moveframe");
	alldiv.attr("id", id);
	alldiv.css("left", x);
	alldiv.css("top", y);
	alldiv.css("width", width);
	alldiv.css("height", height);

	// 上边框
	var top = $("<div id='top' class='moveframeBottombg'></div>");

	top.css("width", "100%");
	top.css("height", 10);
	alldiv.append(top);
	var topLdiv = $("<div class='moveframeBottombg'></div>");
	topLdiv.css("background-image",
			"url(app/theme/common/images/level1/toolface/cct_bgtop_l.png)");
	topLdiv.css("width", 8);
	top.append(topLdiv);
	var topCdiv = $("<div></div>");
	topCdiv.css("background-image",
			"url(app/theme/common/images/level1/toolface/cct_bgtop_c.png)");
	topCdiv.css("width", width - 16);
	topCdiv.css("height", "100%");
	topCdiv.css("float", "left");
	topCdiv.css("background-repeat", "repeat-x");
	top.append(topCdiv);
	if (imgsrc != null && text != null) {
		var topContentImg = $("<img>", {
					"src" : imgsrc
				}).css("height", "20px").css("position", "absolute");
		topCdiv.append(topContentImg);
		var topContentText = $("<font>", {
					"class" : "topContentText"
				}).text(text);
		topCdiv.append(topContentText);
	}
	// 加关闭按钮
	var closeDiv = $("<div>", {
				"class" : "saas_tool_window_close"
			});
	closeDiv.css("width", "20");
	closeDiv.css("height", "17");
	closeDiv.css("position", "absolute");
	closeDiv.css("top", "0");
	closeDiv.css("left", width - 18 - closeDiv.width());
	topCdiv.append(closeDiv);
	closeDiv.data("obj", this);
	closeDiv.click(function() {
				$(this).removeClass("saas_tool_window_close");
				$(this).removeClass("saas_tool_window_close_hover");
				$(this).addClass("saas_tool_window_close_click");
				$("#" + id).remove();
				mainPage.closeTool(id);
			});
	var topRdiv = $("<div class='moveframeBottombg'></div>");
	topRdiv.css("background-image",
			"url(app/theme/common/images/level1/toolface/cct_bgtop_r.png)");
	topRdiv.css("width", 8);
	top.append(topRdiv);

	// 主体部分
	var divtop = $("<div></div>");
	divtop.addClass("moveframeCenterbg");
	divtop.css("height", height - 25);
	alldiv.append(divtop);
	var divtopleft = $("<div></div>");
	divtopleft.css("background-image",
			"url(app/theme/common/images/level1/toolface/cct_bgWindow_l.png)");
	divtopleft.css("width", 8);
	divtopleft.addClass("moveframeCenterbg");
	divtop.append(divtopleft);
	var divtopcenter = $("<div></div>");
	divtopcenter.css("background-image",
			"url(app/theme/common/images/level1/toolface/cct_bgWindow_c.png)");
	divtopcenter.css("width", width - 16);
	divtopcenter.css("height", "100%");
	divtopcenter.css("float", "left");
	divtopcenter.append(composite);
	divtop.append(divtopcenter);
	var divtopright = $("<div></div>");
	divtopright.css("background-image",
			"url(app/theme/common/images/level1/toolface/cct_bgWindow_r.png)");
	divtopright.css("width", 8);
	divtopright.addClass("moveframeCenterbg");
	divtop.append(divtopright);

	// 下边框
	var buttom = $("<div class='moveframeBottombg'></div>");
	buttom.css("width", "100%");
	buttom.css("height", 10);
	alldiv.append(buttom);
	var buttomdiv = $("<div class='moveframeBottombg'></div>");
	buttomdiv.css("background-image",
			"url(app/theme/common/images/level1/toolface/cct_bgFolder_l.png)");
	buttomdiv.css("width", 8);
	buttom.append(buttomdiv);
	var buttomdiv = $("<div></div>");
	buttomdiv.css("background-image",
			"url(app/theme/common/images/level1/toolface/cct_bgFolder_c.png)");
	buttomdiv.css("width", width - 16);
	buttomdiv.css("height", "100%");
	buttomdiv.css("float", "left");
	buttomdiv.css("background-repeat", "repeat-x");
	buttom.append(buttomdiv);
	var buttomdiv = $("<div class='moveframeBottombg'></div>");
	buttomdiv.css("background-image",
			"url(app/theme/common/images/level1/toolface/cct_bgFolder_r.png)");
	buttomdiv.css("width", 8);
	buttom.append(buttomdiv);
}
/*
 * 个人配置，个性桌面,监控配置，监控器使用的框架
 */
mainPage.toolframe = function(id, left, top, width, height, title, icon,
		composite) {
	$("#" + id).remove();
	var $moveDiv = $("<div>", {
				"class" : "saas_system_tool"
			});
	$moveDiv.attr("id", id);
	$moveDiv.css("position", "absolute");
	$moveDiv.css("left", left);
	$moveDiv.css("top", top);
	$moveDiv.css("z-index", "9995");
	$moveDiv.css("width", width);
	$moveDiv.css("height", height);
	$("body").append($moveDiv);

	var topdiv = $("<div></div>");
	topdiv.css("width", width);
	topdiv.attr("id", "top");
	var topleft = $("<div class='toolTopLeft' >&nbsp;</div>");
	topdiv.append(topleft);

	var topcenter = $("<div class='toolTopCenter'></div>");
	var $p = $("<font  style='font-size:14px;font-weight: bold;font-family: 宋体;' color='#c7c7c7'>"
			+ title + "</font>");
	$p.css("position", "absolute");
	$p.css("top", 11);
	$p.css("left", 18);
	if (icon != "") {
		var $img = $("<img>&nbsp;</img>");
		$img.css("position", "absolute");
		$img.attr("src", icon);
		$img.css("top", "8px");
		$img.css("left", "18px");
		$p.css("left", "48px");
		topcenter.append($img);
	}
	topcenter.append($p);
	topdiv.append(topcenter);
	var topright = $("<div class='toolTopRight' ></div>");
	topdiv.append(topright);
	topcenter.css("width", width - 18);
	$moveDiv.append(topdiv);
	// end top
	var $center = $("<div>")
	var cl = $("<div class='centerLeft'></div>")
	var center = $("<div class='centerCenter'></div>")
	var cr = $("<div class='centerRight'></div>")
	cl.css("height", height - 49);
	cl.css("margin", 0);
	center.css("margin", 0);
	center.css("float", "left");
	center.css("overflow", "hidden");
	cr.css("height", height - 49);
	center.css("height", height - 49);
	center.css("width", width - 18);
	$center.append(cl);
	$center.append(center);
	$center.append(cr);
	$moveDiv.append($center);

	var $bottom = $("<div>")
	$bottom.css("clear", "both");
	$bottom.css("height", 11);
	$bottom.css("overflow", "hidden");
	var bl = $("<div class='bottomLeft'></div>")
	var bc = $("<div class='bottomCenter'></div>")
	var br = $("<div class='bottomRight'></div>")
	bc.css("width", width - 18);
	bl.css("height", 11);
	bc.css("height", 11);
	br.css("height", 11);
	$bottom.append(bl);
	$bottom.append(bc);
	$bottom.append(br);
	$moveDiv.append($bottom);
	// 创建关闭按钮图标
	var closeDiv = $("<div>", {
				"class" : "saas_tool_window_close"
			});
	closeDiv.attr("id", id + "close");
	closeDiv.css("width", "20");
	closeDiv.css("height", "17");
	closeDiv.css("position", "absolute");
	closeDiv.css("top", "0");
	closeDiv.css("left", width - 18 - closeDiv.width());
	topcenter.append(closeDiv);
	closeDiv.data("obj", this);
	closeDiv.click(function() {
				// 消息提示只需要隐藏就行了,不去删除,删除了里面的DNA控件没办法建立了
				if ($(this).parents(".saas_system_tool").attr("id") == "showmessage") {
					mainPage.closeShowmessage();
				} else {
					$(this).removeClass("saas_tool_window_close");
					$(this).removeClass("saas_tool_window_close_hover");
					$(this).addClass("saas_tool_window_close_click");
					$(this).parents(".saas_system_tool").remove();
					mainPage.closeTool($(this).parents(".saas_system_tool").attr("id"));
				}
			});
	closeDiv.mousedown(mainPage.stopMove);

	closeDiv.hover(function() {
				$(this).removeClass("saas_tool_window_close_click");
				$(this).removeClass("saas_tool_window_close");
				$(this).addClass("saas_tool_window_close_hover");
			}, function() {
				$(this).removeClass("saas_tool_window_close_click");
				$(this).removeClass("saas_tool_window_close_hover");
				$(this).addClass("saas_tool_window_close");
			});

	cl.mousedown(mainPage.stopMove);
	center.mousedown(mainPage.stopMove);
	cr.mousedown(mainPage.stopMove);
	bl.mousedown(mainPage.stopMove);
	bc.mousedown(mainPage.stopMove);
	br.mousedown(mainPage.stopMove);

	center.append(composite);
}

mainPage.closeShowmessage = function() {
	$("#showmessage").css("display", "none");
	MainProperty.isopenMessageWindow = false;
}

/**
 * 初始化随手记
 */
mainPage.initNote = function(id, value, width, height, left, top) {
	$("#" + id).remove();
	var wirteDiv = $("<div>", {
				"class" : "saas_tool_move_div_hide"
			}).attr("id", id);
	var topleft = $("<div>").attr("class", "note_left_top");
	var topcenter = $("<div>").attr("class", "note_center_top");
	var close = $("<div>").attr("class", "saas_note_close").click(function() {
				$("#" + id).remove();
				mainPage.closeTool(id);
			});
	close.hover(function() {
				$(this).css("background-image",
						"url(app/note/images/note_close_h.png)");
			}, function() {
				$(this).css("background-image",
						"url(app/note/images/note_close_n.png)");
			});
	topcenter.append(close);
	var topright = $("<div>").attr("class", "note_right_top");
	var layout = $("<div>", {
				"class" : "layout"
			});
	var iframe = $("<iframe src='app/note/noteMain.jsp?notetext="
			+ encodeURIComponent(value)
			+ "' border='0' allowtransparency='true' frameborder='0' scrolling='no' style='background-color:transparent;'></iframe>")
	iframe.css("width", width);
	iframe.css("height", height);
	// iframe.css("border","1px solid red");
	wirteDiv.append(topleft);
	wirteDiv.append(topcenter);
	wirteDiv.append(topright);
	wirteDiv.append(layout);
	wirteDiv.append(iframe);

	wirteDiv.css("left", left);
	wirteDiv.css("top", top);
	$("body").append(wirteDiv);

	close.click(function(event) {
				$(".saas_tool_move_div_hide").remove();
				event.stopPropagation();
				return false;
			});
}

mainPage.initClock = function(left, top) {
	mainPage.showTool("clock");
	$(".clockmovediv").remove();
	var iframe = $('<iframe id="flashcontent" src="app/clock/Colck.html"  border="0"  allowtransparency="true" frameborder="0" scrolling="no"  style="background-color:transparent;width:150px;height:155px;"></iframe>');
	var movediv = $("<div class='clockmovediv'></div>");
	movediv.css("position", "absolute");
	movediv.css("left", left);
	movediv.css("top", top);
	movediv.append(iframe);
	$("body").append(movediv);

	var hidemovediv = $("<div id='clock' class='clockmovediv'></div>");
	hidemovediv.css("position", "absolute");
	hidemovediv.css("left", left);
	hidemovediv.css("top", top);
	hidemovediv.css("opacity", 0);
	hidemovediv.css("z-index", 4);
	hidemovediv.css("background-color", "red");
	hidemovediv.data("class", "clockmovediv");
	$("body").append(hidemovediv);
	hidemovediv.mouseover(function() {
				$(".closegifon_clcok").remove();
				var $closeDiv = $("<div></div>");
				$closeDiv.addClass("closegifon_clcok");
				$closeDiv.css("left", $(".clockmovediv").offset().left + 150);
				$closeDiv.css("top", $(".clockmovediv").offset().top);
				$closeDiv.click(function(e) {
							$(".clockmovediv").remove();
							$(this).remove();
							mainPage.closeTool("clock");
						});
				$("body").append($closeDiv);
			});
}
/**
 * 停止分发事件
 */
mainPage.stopMove = function(event) {
	event.stopPropagation();
	return false;
}
/**
 * 分辨率发生变化
 */
$(window).resize(function() {
			mainPage.initShortcutIcons();
		});

// 提示登陆有多少条未处理信息
mainPage.doLoginMessage = function(type) {
	try {
		// messages表示服务端发过来的消息
		var messages = MainProperty.bubbleMessage;
		var messageNumber = MainProperty.bubbleMessage.messageArray.length;
		if (type == "init" && messages.count == 0) {
			return;
		} else if (type == "detail") {
			// 具体的消息
			messages = MainProperty.bubbleMessage.messageArray[0];
			MainProperty.bubbleMessage.messageArray.removeAt(0);
			var message = {
				"id" : messages.id
			}
			toServer("message", message);
		}

		flash("message-flash-n.gif");
		$(".messageTipDiv").remove();
		var $div = $("<div>", {
					"class" : "messageTipDiv"
				});
		var borderDiv = $("<div>", {
					"class" : "messageborder"
				});
		$div.append(borderDiv);

		var upDiv = $("<div>").css("height", "36px");
		var messageleft = $("<div>", {
					"class" : "messageleft"
				});
		var messagecenter = $("<div>", {
					"class" : "messagecenter"
				});
		var messageright = $("<div>", {
					"class" : "messageright"
				});
		var messagebottom = $("<div>", {
					"class" : "messagebottom"
				});
		upDiv.append(messageleft);
		upDiv.append(messagecenter);
		upDiv.append(messageright);
		borderDiv.append(upDiv);
		borderDiv.append(messagebottom);

		var pos = getAbsPos(document.getElementById("statusAreaLeft"));
		pos.x = pos.x - 8;
		// pos.y=pos.y+2;
		$("body").append($div);
		var $span1 = $("<span>", {
					"class" : "span1"
				}).html(messages.bofore);
		var $span2 = $("<a>", {
					"class" : "span2"
				}).html(messages.count);//
		var $span3 = $("<span>", {
					"class" : "span3"
				}).html(messages.after);
		messagecenter.append($span1).append($span2).append($span3);
		messagecenter.data("messages", messages);
		messagecenter.click(function() {
					if ($(this).data("messages").type == "01") {
						if (!MainProperty.isopenMessageWindow) {
							MainProperty.isopenMessageWindow = true;
							mainPage.showmessage(null);
							mainPage.closeLoginMessageBubble();
						}
					} else if ($(this).data("messages").type == "02") {
						if (messages.textCode != null) {
							SaasNavigator.openPage(messages.textCode,
									messages.textName, messages.textID);
						}
						mainPage.closeLoginMessageBubble();
					}
				});
		var tipMessageWidth = 0;
		try {
			tipMessageWidth = parseInt(messagecenter[0].offsetWidth) + 20;
		} catch (e) {
			tipMessageWidth = 300;
		}
		var tipMessageHieght = 47;
		$div.css("width", tipMessageWidth);
		$div.css("height", tipMessageHieght);
		$div.css("top", pos.y + tipMessageHieght * 2);
		$div.css("left", pos.x);
		$div.animate({
					top : pos.y - tipMessageHieght,
					left : pos.x,
					width : tipMessageWidth,
					height : tipMessageHieght
				}, 500, 'swing', function() {
					$(this).css("z-index", "10000000");
				});

		// 只有一条消息，15秒后消失或者是本次最有一条消息
		if (messageNumber > 1) {
			window.setTimeout("mainPage.doLoginMessage('detail')", 5000);
			MainProperty.bubbleMessageshow = true;
		} else {
			window.setTimeout('mainPage.closeLoginMessageBubble()', 15000);
			MainProperty.bubbleMessageshow = false;
		}
	} catch (e) {
	}
}

mainPage.closeLoginMessageBubble = function() {
	$(".messageTipDiv").remove();
}

mainPage.showFunctionMessageBubble = function(messages, messageArray) {
	mainPage.doLoginMessage(messages, messageArray[0]);
	messageArray.removeAt(0);
}

mainPage.showTool = function(name) {
	var key = {
		name : name,
		opertion : "add"
	}
	toServer("indexTool", key);
}

mainPage.closeTool = function(name) {
	var key = {
		name : name,
		opertion : "del"
	}
	toServer("indexTool", key);
}

// 图标闪烁
function flash(image) {
	$("#statusAreaLeft").css("background-image",
			"url(app/theme/common/images/level1/footer/" + image + ")")
}

// 取得元素的绝对位置
function getAbsPos(e) {
	var x = e.offsetLeft, y = e.offsetTop;
	while (e = e.offsetParent) {
		x += e.offsetLeft;
		y += e.offsetTop;
	}
	return {
		x : x,
		y : y
	};
}
