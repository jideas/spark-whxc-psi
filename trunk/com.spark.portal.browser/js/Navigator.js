// �����ռ�
var SaasNavigator = {};

/**
 * 
 */
SaasNavigator.onMenuClick = function(event, widget) {
	var functionData = widget.getCustomObject("functionData");
	SaasNavigator.openMainFunction(functionData.name, functionData.title);
};

SaasNavigator.isMainWindowVisible = function() {
	try {
		var mainWindow = Display.get("MainWindow");
		if (mainWindow != null && mainWindow.isVisible()) {
			return true;
		}
	} catch (e) {
	}
	return false;
};

SaasNavigator.isModalWindowVisible = function() {
	try {
		var children = Display.children();
		if (children) {
			for ( var i = 0; i < children.length; i++) {
				if (children[i].isVisible()
						&& (children[i]._getWidget().getStyle() & DNA.JWT.MODAL) != 0) {
					return true;
				}
			}
		}
	} catch (e) {
	}
	return false;
};

SaasNavigator.openMainFunction = function(code) {
	SaasNavigator.openMainWindow("function",code);
};

SaasNavigator.openPage = function(name,title,argument) {
	SaasNavigator.openMainWindow("page",{name:name,title:title,argument:argument});
};


SaasNavigator.openMainWindow = function(type,value) {
	// У���Ƿ���ģ̬����
	if (SaasNavigator.isModalWindowVisible()) {
		return;
	}
	var shell = Display.getShell();
	//
	var actionData = {
		type : type,
		value : value
	};
	//
	var mainWindow = Display.get("MainWindow");
	if (mainWindow != null) {
		if (mainWindow.isVisible()) {
			var callback = {
				run : function(processCurrentPage) {
					actionData.processCurrentPage = processCurrentPage;
					shell.setCustomObject("actionData", actionData);
					if (!processCurrentPage) {
						// SaasNavigator.hideWindowContent(mainWindow);
						SaasNavigator.resizeMainWindow();
					}
					shell.notifyAction();
				}
			};
			SaasNavigator.handleTargetDisposing(mainWindow.find(mainWindow
					.getCustomObject("PageStack").current, 10), type,
					callback);
		} else {
			SaasNavigator.resizeMainWindow();
			// mainWindow.setVisible(true);
			shell.setCustomObject("actionData", actionData);
			shell.notifyAction();
		}
	} else {
		SaasNavigator.resizeMainWindow();
		shell.setCustomObject("actionData", actionData);
		shell.notifyAction();
	}
};

/**
 * 
 */
SaasNavigator.onMainWindowClose = function(event, widget) {
	event.stopServerEvent();
	SaasNavigator.processMainWindowClose();
};

/**
 * 
 */
SaasNavigator.processMainWindowClose = function() {
	var mainWindow = Display.get("MainWindow");
	var pageStack = mainWindow.getCustomObject("PageStack");
	var callback = {
		run : function(processCurrentPage) {
			mainWindow.setCustomObject("actionData", {
				processCurrentPage : processCurrentPage
			});
			if (!processCurrentPage) {
				if (!pageStack || pageStack.count <= 0) {
					SaasNavigator.hideMainWindow(mainWindow);
				}
			}
			mainWindow.notifyAction();
		}
	};
	if (pageStack.current) {
		SaasNavigator.handleTargetDisposing(mainWindow.find(pageStack.current,
				10), 'window', callback);
	}
};

/**
 * 
 */
SaasNavigator.hideWindowContent = function(window) {
	var contentArea = window.get(0).get(1).get(0);
	if (contentArea) {
		var children = contentArea.children();
		if (children.length > 0) {
			children[children.length - 1].setVisible(false); // ����������
		}
	}
};

/**
 * 
 */
SaasNavigator.onWindowInit = function(event, widget) {
	SaasNavigator.resizeMainWindow();
};

/**
 * 
 */
SaasNavigator.onShellResize = function(event, widget) {
	SaasNavigator.resizeMainWindow();
};

/**
 * 
 */
SaasNavigator.resizeMainWindow = function() {
	var shell = Display.getShell();
	var mainWindowInfo = shell.getCustomObject("mainWindowInfo");
	var x = 5;
	var y = 58;
	var width = shell.getWidth() - 10;
	var height = shell.getHeight() - 125;
	if (mainWindowInfo) {
		x = mainWindowInfo.marginLeft;
		y = mainWindowInfo.marginTop;
		width = shell.getWidth() - mainWindowInfo.marginLeft
				- mainWindowInfo.marginRight;
		height = shell.getHeight() - mainWindowInfo.marginTop
				- mainWindowInfo.marginBottom;
	}
	var windowBounds = {};
	windowBounds.x = x;
	windowBounds.y = y;
	windowBounds.width = width;
	windowBounds.height = height;
	shell.setCustomObject("windowBounds", windowBounds);
	var mainWindow = Display.get("MainWindow");
	if (mainWindow != null) {
		mainWindow.setX(x);
		mainWindow.setY(y);
		mainWindow.setWidth(width);
		mainWindow.setHeight(height);
	}
};

/**
 * 
 */
SaasNavigator.hideMainWindow = function(mainWindow) {
	mainWindow.setVisible(false);
	// TODO:֧�ֶ�̬Ч������Ҫ���ǳߴ�仯�Ƿ��Ƶ������ӿؼ����֣�����Ч������
};

/**
 * 
 */
SaasNavigator.onFrameWindowCloseButtonClick = function(event, widget) {
	event.stopServerEvent();
	var callback = {
		run : function(processCurrentPage) {
			widget.setCustomObject("actionData", {
				processCurrentPage : processCurrentPage
			});
			if (!processCurrentPage) {
				widget.setVisible(false);
			}
			widget.notifyAction();
		}
	};
	SaasNavigator.handleTargetDisposing(widget.find("FramePage", 10), 'window',
			callback);
};

/**
 * 
 */
SaasNavigator.tryDisposeFramePage = function(widget, callback,
		customActionName, customActionDesc) {
	var page = widget.find("FramePage", 10);
	while (page == null && widget != null) {
		try {
			var id = widget.getID();
			if (id != null && id.indexOf("FramePage") == 0) {
				page = widget;
				break;
			}
			widget = widget.parent();
		} catch (e) {
			widget = null;
		}
	}
	if (page != null) {
		SaasNavigator.handleTargetDisposing(page, "custom", callback,
				customActionName, customActionDesc);
	}
};

/**
 * 
 */
SaasNavigator.handleTargetDisposing = function(page, type, callback,
		customActionName, customActionDesc) {
	var queryState = {
		promptMessage : ""
	};
	page.broadcastMessage("QueryDataState", queryState);
	if (queryState.promptMessage) {
		Display.setData("SystemPromptCallback", callback);
		var button1Title = "";
		var button2Title = "";
		var message = "";
		if (type == 'window') {
			button1Title = "����ر�";
			button2Title = "ֱ�ӹر�";
			message = queryState.promptMessage + "���Ƿ񱣴���ٹرմ��ڣ�";
		} else if (type == 'function') {
			button1Title = "�����";
			button2Title = "ֱ�Ӵ�";
			message = queryState.promptMessage + "���Ƿ񱣴���ٴ��¹��ܣ�";
		} else if (type == 'tab') {
			button1Title = "�����л�";
			button2Title = "ֱ���л�";
			message = queryState.promptMessage + "���Ƿ񱣴�����л�ҳǩ��";
		} else if (type == 'page') {
			button1Title = "�����";
			button2Title = "ֱ�Ӵ�";
			message = queryState.promptMessage + "���Ƿ񱣴���ٴ����ҳ�棿";
		} else if (type == 'custom') {
			button1Title = "����" + customActionName;
			button2Title = "ֱ��" + customActionName;
			message = queryState.promptMessage + "���Ƿ񱣴����" + customActionDesc
					+ "��";
		} 
		var promptWindow = Display.get("SystemPromptWindow");
		if (promptWindow == null) {
			var shell = Display.getShell();
			var actionData = {};
			actionData.type = "prompt";
			actionData.button1Title = button1Title;
			actionData.button2Title = button2Title;
			actionData.message = message;
			shell.setCustomObject("actionData", actionData);
			shell.notifyAction();
		} else {
			var textLabel = promptWindow.find("PromptTextLabel", 5);
			var button1 = promptWindow.find("Button1", 5);
			var button2 = promptWindow.find("Button2", 5);
			button1.setText(button1Title);
			button2.setText(button2Title);
			textLabel.setText(message);
			promptWindow.setVisible(true);
			textLabel.parent().layout();
		}
	} else {
		callback.run(false);
	}
};

/**
 * 
 */
SaasNavigator.handleTabClick = function(event, widget) {
	var tabInfo = null;
	while ((tabInfo = widget.getCustomObject("tabInfo")) == null) {
		widget = widget.parent();
		if (widget == null) {
			return;
		}
	}
	var currentSelection = widget.parent().getCustomObject("selectionInfo").index
	var index = widget.getCustomObject("tabInfo").index;
	if (index == currentSelection) {
		return;
	}
	var callback = {
		run : function(processCurrentPage) {
			widget.parent().setCustomObject("actionData", {
				index : index,
				processCurrentPage : processCurrentPage
			});
			widget.parent().notifyAction();
		}
	};
	SaasNavigator.handleTargetDisposing(widget, 'tab', callback);
};

/**
 * 
 */
SaasNavigator.handlePromptWindowClose = function(event, widget) {
	widget.setVisible(false);
	event.stopServerEvent();
};

/**
 * 
 */
SaasNavigator.handlePromptButton1 = function(event, widget) {
	SaasNavigator.handlePromptButtonClick(false, true);
};

/**
 * 
 */
SaasNavigator.handlePromptButton2 = function(event, widget) {
	SaasNavigator.handlePromptButtonClick(false, false);
};

/**
 * 
 */
SaasNavigator.handlePromptButton3 = function(event, widget) {
	SaasNavigator.handlePromptButtonClick(true);
};

SaasNavigator.handlePromptButtonClick = function(cancel, processCurrentPage) {
	var promptWindow = Display.get("SystemPromptWindow");
	promptWindow.setVisible(false);
	if (cancel) {
		return;
	}
	Display.getData("SystemPromptCallback").run(processCurrentPage);
};

/**
 * 
 */
SaasNavigator.handleQueryDataState = function(event, widget) {
	var promptInfo = widget.getCustomObject("promptInfo");
	if (promptInfo) {
		var promptMessage = promptInfo.message;
		if (!promptMessage) {
			promptMessage = "���ݷ����ı�";
		}
		if (promptInfo.dataChanged) {
			event.getMessage().promptMessage = promptMessage;
		}
	}
};

/**
 * 
 */
SaasNavigator.handlePageDataChanged = function(event, widget) {
	event.getTransmitter().terminate();
	var promptInfo = widget.getCustomObject("promptInfo");
	if (promptInfo) {
		if (promptInfo.resetTime
				&& (new Date().getTime() - promptInfo.resetTime < 500)) {
			return;
		}
		promptInfo.dataChanged = true;
	}
	widget.setCustomObject("promptInfo", promptInfo);
};

/**
 * 
 */
SaasNavigator.resetDataChangedState = function(event, widget) {
	var promptInfo = widget.getCustomObject("promptInfo");
	if (promptInfo) {
		promptInfo.dataChanged = false;
		promptInfo.resetTime = new Date().getTime();
	}
	widget.setCustomObject("promptInfo", promptInfo);
};

/**
 * handleTableDataChanged�����������ݷ����仯��
 */
SaasNavigator.$1 = function(event, widget) {
	if (!(event.getFireType && event.getFireType() == 1)) { // �������˴�����ʱ������
		widget.bubbleMessage("DataChanged", "");
	}
};

/**
 * handleInputControlDocumentChanged�������ı���������ݱ仯��
 */
SaasNavigator.$2 = function(event, widget) {
	//
	if (!(event.getFireType && event.getFireType() == 1)) { // �������˴�����ʱ������
		widget.bubbleMessage("DataChanged", "");
	}
	// �����������Ŀǰ������У����������ǿգ����пͻ��˴���
};

/**
 * handleInputControlKeyDown������س��л�����ȣ�
 */
SaasNavigator.$3 = function(event, widget) {
	//
};

/**
 * handleInputControlKeyDown������س��л�����ȣ�
 */
SaasNavigator.handleFocusInfoWindowBeforeShow = function(event, widget) {
	//

};
