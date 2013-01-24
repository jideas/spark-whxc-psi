var PSIWizard = function() {
};

/**
 * 员工和用户表格选择事个处理
 */
PSIWizard.EmployeeTableSelection = function(event, widget) {
	//debugger
	//var selections = widget.getSelections();
	//var selected = selections != null && selections.length > 0;
	//var contentArea = widget.parent().parent();
	//contentArea.find('Button_SetDept', 3).setEnabled(selected);
	//contentArea.find('Button_SetRoles', 3).setEnabled(selected);
};

/**
 * 按照流程配置系统，鼠标进入时执行
 */
PSIWizard.StepMouseMoveIn = function(event, widget) {
	var images = widget.getCustomObject("images");
	widget._getWidget()._setBackimage({url:images.hoverImage});
	widget.next()._getWidget()._setForeground(0xDF8A18);
};

/**
 * 按照流程配置系统，鼠标离开时执行
 */
PSIWizard.StepMouseMoveOut = function(event, widget) {
	var images = widget.getCustomObject("images");
	widget._getWidget()._setBackimage({url:images.enableImage});
	widget.next()._getWidget()._setForeground(0x008015);
};

/**
* 下载模板
*/
PSIWizard.downTemplate = function(event,widget) {
	var _file = widget.getCustomObject("tempPath");
	window.open(_file.file);
}

/**
 * 返回配置向导
 */
PSIWizard.handleBackAction = function(event, widget) {
	var callback = {
		run : function(processCurrentPage) {
			widget.setServerObject("actionData", {
				processCurrentPage : processCurrentPage
			});
			widget.notifyAction();
		}
	};
	SaasNavigator.tryDisposeFramePage(widget, callback, "返回", "返回配置向导");
};

/**
 * 处理上一步
 */
PSIWizard.handlePrevAction = function(event, widget) {
	var callback = {
		run : function(processCurrentPage) {
			widget.setServerObject("actionData", {
				processCurrentPage : processCurrentPage
			});
			widget.notifyAction();
		}
	};
	SaasNavigator.tryDisposeFramePage(widget, callback, "上一步", "返回上一步操作");
};

/**
 * 处理下一步
 */
PSIWizard.handleNextAction = function(event, widget) {
	var callback = {
		run : function(processCurrentPage) {
			widget.setServerObject("actionData", {
				processCurrentPage : processCurrentPage
			});
			widget.notifyAction();
		}
	};
	SaasNavigator.tryDisposeFramePage(widget, callback, "下一步", "进入下一步操作");
};

