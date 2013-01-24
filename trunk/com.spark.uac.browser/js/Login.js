var UACLogin = {};

UACLogin.handleEnterKey = function(event, widget) {
	if (event.getKeyCode() == 13 && !event.isShift()) {
		// XXX��ĿǰUI��ȱ�ݣ�����а󶨵�Menu���֣��޷�����forceFocus��������ʱֱ�ӵ���CustomPopupWindow�ķ����ȹر�Menu
		try {
			CustomPopupWindow.handleTargetLostFocus(null, widget);
		} catch (e) {
		}
		var contentArea = widget.parent();
		widget = widget.next();
		while (widget != null) {
			if (widget.getType() == "Text") {
				widget.forceFocus();
				return;
			} else {
				widget = widget.next();
			}
		}
		contentArea.findByType("Button", 3).forceFocus();
		contentArea.notifyAction();
	}
};

UACLogin.handleGetPwdWindowShow = function(event, widget) {
	var loginMobileText = Display.getShell().find("mobileText", 5);
	var moibleNumber = loginMobileText.getText(); 
	var mobileText = widget.find("mobileText", 5); 
	var userTitleText = widget.find("userTitleText", 5); 
	mobileText.setText(moibleNumber); 
	userTitleText.setText("");
	userTitleText.forceFocus(); 
	CustomPopupWindow.clearBindingText(mobileText); 
	CustomPopupWindow.clearBindingText(userTitleText);
	mobileText.applyMode(DNA.JWT.STATE_MODE_NROMAL); 
	userTitleText.applyMode(DNA.JWT.STATE_MODE_NROMAL);

	//
	var p = Display.mapping(loginMobileText, {
		x : 0,
		y : 0
	}, Display.getShell());
	var offsetX = Display.getShell().getWidth() - p.x - 500;
	if (offsetX >= 0) {
		event.getEventData().offsetX = offsetX;
	}
};
