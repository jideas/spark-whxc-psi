var SComponent = {};

// 格式化数字，加上千分位
SComponent.formatNumber = function(value, decimal) {
	// debugger
	if (decimal < 0 || value == null || (value.trim && value.trim() == '') || isNaN(value)) {
		return value;
	}
	var v = SComponent.trimNumber(value, decimal);
	var result = '';
	if (v.charAt(0) == '-') {
		result = '-';
		v = v.substring(1);
	}
	var index = v.lastIndexOf('.');
	var s1 = v;
	var s2 = '';
	if (index != -1) {
		s1 = v.substring(0, index);
		s2 = v.substring(index);
	}
	var n = s1.length % 3;
	if (n > 0) {
		result += s1.substring(0, n);
		s1 = s1.substring(n);
	}
	while (s1.length > 0) {
		if (result.length > 0 && result != '-') {
			result += ',';
		}
		result += s1.substring(0, 3);
		s1 = s1.substring(3);
	}
	result += s2;
	return result;
};

// 标准化数字
SComponent.trimNumber = function(value, decimal) {
	if (decimal < 0 || value == null || (value.trim && value.trim() == '') || isNaN(value)) {
		return value;
	}
	var v = Number(value);
	var p = Math.pow(10, decimal);
	v = Math.round(value * p) / p;
	v = v.toString();
	if (decimal > 0) {
		if (v.lastIndexOf('.') == -1) {
			v += '.';
		}
		var inc = decimal - (v.length - v.lastIndexOf('.') - 1);
		for (i = 0; i < inc; i++) {
			v += '0';
		}
	}
	return v;
};

SComponent.hanleNumberTextFocusLost = function(event, widget) {
	var decimal = widget.getCustomObject("data").decimal;
	widget.setText(SComponent.trimNumber(widget.getText(), decimal));
	widget.setDescription(SComponent.formatNumber(widget.getText(), decimal));
};

SComponent.handleSearchTextFocus = function(event, widget) {
	widget.setStartSelectionPosition(0);
	widget.setEndSelectionPosition(1000);
	widget.forceFocus();
};

SComponent.handleButtonAreaKey = function(event, widget) {
	if (event.getKeyCode() == 37) {
		while (widget = widget.prev()) {
			widget.forceFocus();
		}
	} else if (event.getKeyCode() == 39) {
		while (widget = widget.next()) {
			widget.forceFocus();
		}
	}
};


//光标悬浮在目标上触发窗口显示
SComponent.handleDelayWindowShow = function(event, widget) {
	event.getEventData().offsetX = 10;
	if(widget.getData("dataLoaded")) {
		return;
	}
	widget.setData("dataLoaded",true);
	widget.notifyAction();
};

//服务器端加载完数据，通过目标控件出发重新定位窗口位置
SComponent.handleDelayWindowTargetNotify = function(event, widget) {
	CustomPopupWindow.showPopupWindow(widget,Display.get(widget.getID()));
};
