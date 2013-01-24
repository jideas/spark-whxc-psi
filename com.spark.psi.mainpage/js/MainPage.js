var SaasMainPage = function() {
};

SaasMainPage.handleResize = function(event, widget) {
    var dis = DNA.getDisplay().getShell().get("mainpage");
    dis.setWidth(DNA.getDisplay().getShell().getWidth());
    dis.layout();
};

SaasMainPage.handleCloseWindow = function(event, widget) {
	widget.bubbleMessage("CustomPopupWindow_Hide"); // 发送消息通知关闭
};

SaasMainPage.handleHeadImageWindowBeforeShow = function(event, widget) {
	event.getEventData().offsetX = -12;
};

SaasMainPage.handleMessageClick = function(event, widget) {
	var openWindow = widget.getCustomObject("openWindow");
	SaasNavigator.openPage(openWindow.code,openWindow.name,openWindow.id);
	
};

