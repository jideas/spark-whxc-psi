var PSIWizard = function() {
};

/**
 * Ա�����û����ѡ���¸�����
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
 * ������������ϵͳ��������ʱִ��
 */
PSIWizard.StepMouseMoveIn = function(event, widget) {
	var images = widget.getCustomObject("images");
	widget._getWidget()._setBackimage({url:images.hoverImage});
	widget.next()._getWidget()._setForeground(0xDF8A18);
};

/**
 * ������������ϵͳ������뿪ʱִ��
 */
PSIWizard.StepMouseMoveOut = function(event, widget) {
	var images = widget.getCustomObject("images");
	widget._getWidget()._setBackimage({url:images.enableImage});
	widget.next()._getWidget()._setForeground(0x008015);
};

/**
* ����ģ��
*/
PSIWizard.downTemplate = function(event,widget) {
	var _file = widget.getCustomObject("tempPath");
	window.open(_file.file);
}

/**
 * ����������
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
	SaasNavigator.tryDisposeFramePage(widget, callback, "����", "����������");
};

/**
 * ������һ��
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
	SaasNavigator.tryDisposeFramePage(widget, callback, "��һ��", "������һ������");
};

/**
 * ������һ��
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
	SaasNavigator.tryDisposeFramePage(widget, callback, "��һ��", "������һ������");
};

