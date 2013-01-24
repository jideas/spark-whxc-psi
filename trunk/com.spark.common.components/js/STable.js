/** * * * * * * * * * * * ���ؿؼ�* * * * * * * * * * * * * * * * * * * * * * */
var STableHide = function() {
};
// ���߿�Ŀ��
var STableHideProperty = {
	borderWidth : 2
}

STable = function() {
}
/**
 * �ж�DNA�Ƿ�������
 */
STable.handleClientResize = function(event, widget) {
	STableHide.resizeTable(widget.children()[widget.children().length - 1],true);
};

STable.handleClientInit = function(event, widget) {
	STable.handleClientResize(event, widget);
	widget.fireClientEvent("tableInited", {});
}

/**
 * ������������ݹ������в���
 */
STableHide.handleServerNotify = function(event, widget) {
	var rowOperations = widget.getCustomObject("rowOperations");
	var operationList = rowOperations.operationList;
	// TOOD����������б�
	var sTable = widget.parent();
	var headerArea = sTable.get(0);
	var scrolledPanel = sTable.get(1);
	var headerTable = headerArea.get(0);
	var bodyArea = scrolledPanel.children()[0];
	var bodyTable = bodyArea.children()[bodyArea.children().length - 1];
	if (operationList != null && operationList.length > 0) {

		for (var i = 0; i < operationList.length; i++) {
			var operation = operationList[i];
			var type = operation.type;
			var id = operation.id;
			if (type != null && type.length > 0) {
				if (type.toLowerCase() == "add") {

					var rowData = operation.rowData;
					bodyTable.setHeight(bodyTable.getHeight()
							+ sTable.getRowHeight());
					bodyTable.exeMethod("addRow", [rowData]);
					STableHide.bodyTableResize(widget, [id]);
					sTable.fireClientEvent("rowAdded", id);
				} else if (type.toLowerCase() == "update") {
					var rowData = operation.rowData;
					bodyTable.exeMethod("updateRow", [rowData, id]);
					sTable.fireClientEvent("rowUpdated", id);
				} else if (type.toLowerCase() == "remove") {
					bodyTable.setHeight(bodyTable.getHeight()
							- sTable.getRowHeight());
					bodyTable.exeMethod("removeRow", [id]);
					STableHide.bodyTableResize(widget, [id]);
					sTable.fireClientEvent("rowRemoved", id);
				} else if (type.toLowerCase() == "updatecell") {
					var column = operation.column;
					var text = operation.text;
					var tableInfo = widget.getCustomObject("tableInfo");
					var selctionMode = tableInfo.selctionMode;
					if (selctionMode == 'Single' || selctionMode == 'Multi') {
						column++;
					}
					var toolTipText = null;
					if (operation.toolTipText != null) {
						toolTipText = operation.toolTipText;
					}
					bodyTable.exeMethod("updateCell", [column, id, text,
									toolTipText]);
					sTable.fireClientEvent("rowUpdated", id);
				} else if (type.toLowerCase() == "addrows") {
					var rowDatas = operation.rowDatas;
					var ids = operation.ids;
					bodyTable.exeMethod("addRow", [rowDatas]);
					STableHide.bodyTableResize(widget, ids);
					sTable.fireClientEvent("rowAddsed", ids);
				}
			}
		}
		// ��������б�
		rowOperations.operationList = [];
		widget.setCustomObject("rowOperations", rowOperations);
	}

	// ��ѡ��
	var selectRow = widget.getCustomObject("selectRow");
	if (selectRow != null) {
		widget.setCustomObject("selectRow", null);
		bodyTable.exeMethod("rowSelected", [selectRow.rowId]);
	}
	
	//�ı䵥Ԫ����ɫ
	var textBackgroundColor = widget.getCustomObject("textBackgroundColor");
	if(textBackgroundColor != null) {
		widget.setCustomObject("textBackgroundColor", null);
		var sTableTextColors = textBackgroundColor.sTableTextColors;
		try{
			//����������֮ǰ���û�й�����ɣ���ô�ͻ�����⣬���ﲶ�񵽣����ǲ���Ҫ����
			bodyTable.exeMethod("changeTextBackgroundColor", [sTableTextColors]);
		}catch(e) {
		}
	}
};


/**
 * �������һ�еĵ�Ԫ��Ŀ��
 * 
 * @param {}
 *            actualColumnWidths
 * @param {}
 *            width
 */
STableHide.computeTableRightTdWidth = function(actualColumnWidths, width) {
	actualColumnWidths[actualColumnWidths.length - 1] = actualColumnWidths[actualColumnWidths.length
			- 1]
			+ width;
}

/**
 * ��������Ŀ�ȴ��ڴ���Ŀ�ȵ�ʱ����Ҫ���¼�����ÿһ�еĿ��
 * 
 * @param {}
 *            widget STableHide
 * @param {}
 *            borderWidth ���ұߵ�Ԫ��ı߿���
 * @param {}
 *            availableWidth ������Ŀ��
 * @return {}
 */
STableHide.computeActualColumnWidths = function(widget,availableWidth) {
	var tableInfo = widget.getCustomObject("tableInfo");
	var columns = [];
	for (var i = 0; i < tableInfo.columns.length; i++) {
		columns[i] = tableInfo.columns[i];
	}
	var selctionMode = tableInfo.selctionMode;
	if (selctionMode == 'Single' || selctionMode == 'Multi') {
		for (var i = columns.length - 1; i >= 0; i--) {
			columns[i + 1] = columns[i];
		}
		columns[0] = {};
		columns[0].width = 25;
		columns[0].grab = false;
		widget.setData("hasOperationColumn", true);
	}
	var actualColumnWidths = new Array();
	var grabCount = 0;
	var originalWidth = 0;
	var borderWidth = 0;
	// ����߿�ȣ�firefox��Ҫ�ر���
	if (window.navigator.userAgent.indexOf("MSIE") == -1) {
		borderWidth = 1;
	}
	for (var i = 0; i < columns.length; i++) {
		actualColumnWidths[i] = columns[i].width - borderWidth;
		originalWidth += Number(columns[i].width);
		if (columns[i].grab) {
			grabCount++;
		}
	}

	var overWidth = availableWidth - originalWidth;
	var actualWidth = originalWidth;
	if (overWidth > 0) {
		actualWidth = availableWidth;
		var avg = parseInt(overWidth / grabCount);
		for (var i = 0; i < columns.length; i++) {
			if (columns[i].grab) {
				actualColumnWidths[i] = columns[i].width + avg - borderWidth;
				overWidth = overWidth - avg;
			}
		}
		if (overWidth > 0) {
			STableHide.computeTableRightTdWidth(actualColumnWidths, overWidth);
		}
	}
	return [actualWidth, actualColumnWidths,borderWidth];
}

STableHide.scrolledPanel = function(scrolledPanel) {
	scrolledPanel.setHorizontalPosition(0);
	STableScrolledPanel.resetHeaderPosition(scrolledPanel);
}

/**
 * ����������ߴ緢���仯���߳�ʼ����ʱ��
 */
STableHide.resizeTable = function(widget) {
	var sTable = widget.parent();
	var scrolledPanel = sTable.get(1);
	var bodyArea = scrolledPanel.children()[0];
	var tableInfo = widget.getCustomObject("tableInfo");
	// ���������ڲ����
	scrolledPanel._getWidget()._resize();
	try {
		var availableWidth = scrolledPanel.getClientSize().width;
		if (bodyArea.getHeight()+1 >= tableInfo.rowLength * tableInfo.rowHeight) {
			var isScrollbar = false;
		} else {
			var isScrollbar = true;
			availableWidth -= 17;
		}
	} catch (e) {
		return;
	}

	var actualArray = STableHide.computeActualColumnWidths(widget,
			availableWidth);
	//�����ܿ��
	var actualWidth = actualArray[0];
	//��񾭹�����õ���ÿһ�е�����
	var actualColumnWidths = actualArray[1];
	//������ұߵ�Ԫ��ı߿��ߵĿ��
	var borderWidth = actualArray[2];
	
	// �Ƿ��й�����
	if (!isScrollbar) {
		// ���û�й���������ô���һ�е��ұ�������Ҫȥ���ģ�firefox��width��������ߣ��������һ�еĿ����Ҫ+1����
		STableHide.computeTableRightTdWidth(actualColumnWidths, borderWidth);
	}
	widget.setData("actualColumnWidths", actualColumnWidths);
	var actualColumn = {
		widths : actualColumnWidths,
		isScrollbar : isScrollbar,
		overWidth : sTable.getWidth() - availableWidth,
		bodyActualWidth : actualWidth,
		headerActualWidth : actualWidth + sTable.getWidth() - availableWidth,
		isResize : true
	}
	widget.setCustomObject("actualColumn", actualColumn);

	STableHide.scrolledPanel(scrolledPanel);
	
	widget.notifyAction();
};

/**
 * ��ִ���������޸ģ�ɾ�������Ժ󣬿ͻ���Ҫ���ߴ����֤
 */
STableHide.bodyTableResize = function(widget, rowids) {
	var sTable = widget.parent();
	var headerArea = sTable.get(0);
	var scrolledPanel = sTable.get(1);
	var headerTable = headerArea.get(0);
	var bodyArea = scrolledPanel.children()[0];
	var bodyTable = bodyArea.children()[bodyArea.children().length - 1];
	var tableInfo = widget.getCustomObject("tableInfo");
	scrolledPanel._getWidget()._resize();
	// ��������Ŀ��
	var availableWidth = scrolledPanel.getClientSize().width;

	var actualArray = STableHide.computeActualColumnWidths(widget,
			availableWidth);
	//�����ܿ��
	var actualWidth = actualArray[0];
	//��񾭹�����õ���ÿһ�е�����
	var actualColumnWidths = actualArray[1];
	//������ұߵ�Ԫ��ı߿��ߵĿ��
	var borderWidth = actualArray[2];

	var isScrollbar = true;
	// �Ƿ��й�����
	if (Number(sTable.getWidth() - availableWidth) <= Number(STableHideProperty.borderWidth)) {
		// ��2PX�ı߿������ж��ǲ��Ǵ���2������ʾ�������������
		// ���û�й���������ô���һ�е��ұ�������Ҫȥ���ģ�firefox��width��������ߣ��������һ�еĿ����Ҫ+1����
		STableHide.computeTableRightTdWidth(actualColumnWidths, borderWidth);
		isScrollbar = false;
	}
	widget.setData("actualColumnWidths", actualColumnWidths);
	// �޸Ŀͻ��˱����п�
	bodyArea.setWidth(actualWidth);
	bodyTable.setWidth(actualWidth);

	bodyTable.exeMethod("setColumnWidths", [actualColumnWidths, isScrollbar,
					rowids]);

	// ���ñ�ͷ�Ŀ��
	if (isScrollbar) {
		actualWidth = actualWidth + sTable.getWidth() - availableWidth;
	}
	headerArea.setWidth(actualWidth);
	headerTable.setWidth(actualWidth);
	headerTable.exeMethod("setColumnWidths", [actualColumnWidths,
					sTable.getWidth() - availableWidth, isScrollbar]);

	//
	STableHide.scrolledPanel(scrolledPanel);
};

/** * * * * * * * * * * * �����ؼ�* * * * * * * * * * * * * * * * * * * * * * */
STableScrolledPanel = function() {
};

STableScrolledPanel.handlePanelScrolled = function(event, widget) {
	STableScrolledPanel.resetHeaderPosition(widget);
};
STableScrolledPanel.resetHeaderPosition = function(scrolledPanel) {
	//
	var sTable = scrolledPanel.parent();
	var scrollLeft = scrolledPanel.getHorizontalPosition(); // 0
	sTable.get(0).get(0).setX(-scrollLeft);
	var scrollPosition = scrolledPanel.getScrollPosition();
	if ("bottom" == scrollPosition) { // �ײ���ҳ
		scrolledPanel.notifyAction();
	}
};
/** * * * * * * * * * * * * ����ؼ� * * * * * * * * * * * * * * * * * * * * * */

var STableBody = function() {
};

STableBody.handleClientAction = function(event, widget) {
	var values = event.getActionValue();
	var action = values.substring(0, values.indexOf(":"));
	var value;
	var sTable = widget.parent().parent().parent();
	var scrolledPanel = sTable.get(1);
	var sTableHide = sTable.children()[sTable.children().length - 1];
	var sTableTitle = sTable.children()[0].children()[0];
	if (action == "links") {
		// �ж��Ƿ��з�����������
		if (sTableHide.getCustomObject("actionregistered") != null) {
			value = values.substring(Number(values.indexOf(":") + 1),
					values.length);
			var splitString = value.split(":");
			var actionName = splitString[0];
			var actionValue = splitString[1];
			var rowId = splitString[2];
			var x = splitString[3];
			var y = splitString[4];
			x -= scrolledPanel.getHorizontalPosition();
			y -= scrolledPanel.getVerticalPosition();
			var p = Display.mapping(sTable, {
						x : x,
						y : y
					}, Display.getShell());
			var eventData = {
				actionName : actionName,
				actionValue : actionValue,
				rowId : rowId,
				notifyServer : true,
				x : p.x,
				y : p.y
			};
			sTable.fireClientEvent("tableAction", eventData);
			if (eventData.notifyServer) {
				sTableHide.setCustomObject("link", eventData);
				sTableHide.notifyAction();
			}
		}

	} else if (action == "tableSelection") {
		value = values
				.substring(Number(values.indexOf(":") + 1), values.length);
		var splitString = value.split(":");
		var jsonobj = sTableHide.getCustomObject("selections");
		if (splitString[0].length == 0) {
			jsonobj.ids = [];
		} else {
			jsonobj.ids = splitString[0].split(",");
		}
		sTableHide.setCustomObject("selections", jsonobj);
		sTable.fireClientEvent("tableSelection");
		if (sTableHide.getCustomObject("selectregistered") != null) {
			// �ж��Ƿ��з�����������
			sTableHide.setCustomObject("selected", {
						"1" : "1"
					});
			sTableHide.notifyAction();
		}
		if (splitString[1] != "null") {
			sTableTitle.exeMethod("checksel", [splitString[1]]);
		}

	} else if (action == "selClick") {
		value = values
				.substring(Number(values.indexOf(":") + 1), values.length);
		var splitString = value.split(":");
		var colIndex = splitString[1];
		if (sTableHide.getData("hasOperationColumn")) {
			colIndex = colIndex - 1;
		}
		sTable.fireClientEvent("rowClick", {
					"row" : splitString[0],
					"col" : colIndex,
					"id" : splitString[2]
				});
	} else if (action == 'table_mouseover') {
		value = values
				.substring(Number(values.indexOf(":") + 1), values.length);
		var splitString = value.split(":");
		sTable.fireClientEvent("tableMouseover", {
					"row" : splitString[0],
					"col" : splitString[1]
				});

	} else if (action == 'table_mouseout') {
		value = values
				.substring(Number(values.indexOf(":") + 1), values.length);
		var splitString = value.split(":");
		var mouseOut = {
			"row" : splitString[0],
			"col" : splitString[1],
			"value" : true
		}
		sTable.fireClientEvent("tableMouseout", mouseOut);
		if (mouseOut.value == true || mouseOut.value == "true") {
			widget.exeMethod("tableMouseOut", [splitString[2]]);
		}
	} else if (action == 'dblClick') {
		value = values
				.substring(Number(values.indexOf(":") + 1), values.length);
		var splitString = value.split(":");
		sTable.fireClientEvent("tabledblClick", {
					"row" : splitString[0]
				});
	} else if (action == 'findText') {
		value = values
				.substring(Number(values.indexOf(":") + 1), values.length);
		var splitString = value.split(":");
		var textName = splitString[0];
		var arrayText = splitString[1].split(",");
		if (arrayText == "" || arrayText.length == 0) {
			arrayText = null;
		}
		var findText = {
			"textName" : textName,
			"arrayText" : arrayText,
			"arrayNumber" : 0
		};
		sTable.bubbleMessage("stableMessage", findText);
	}
};
/**
 * STable��ʼ����ɣ������ݼ�����ϣ�
 */
STableBody.handleInited = function(event, widget) {
	//
	var sTable = widget.parent().parent().parent();
	var sTableHide = sTable.children()[sTable.children().length - 1];
	// ����setCellText����
	sTable.setCellText = function(targetRow, cellIndex, text) {
		if (text == "" || text.trim().length == 0) {
			text = " ";
		}
		if (sTableHide.getData("hasOperationColumn")) {
			cellIndex = Number(cellIndex) + 1;
		}
		if (isNaN(targetRow)) {
			widget.exeMethod("updateCell", [cellIndex, targetRow, text]);
		} else {
			widget.exeMethod("setTableText", [targetRow, cellIndex, text]);
		}
	};
	// ͨ�������ȡ����ID
	sTable.getRowIdByRowIndex = function(RowNumber) {
		return sTableHide.getCustomObject("rowIds").rowIdArray.get(RowNumber);
	};
	// �õ����е���ID
	sTable.getRowIds = function() {
		return sTableHide.getCustomObject("rowIds").rowIdArray;
	};
	// �õ�ָ���еĿ��
	sTable.getColumnWidth = function(columnsNumber) {
		var actualColumnWidths = sTableHide.getData("actualColumnWidths");
		var width = 0;
		var borderWidth = 0;
		if (window.navigator.userAgent.indexOf("MSIE") == -1) {
			borderWidth = 1;
		}
		if (sTableHide.getData("hasOperationColumn")) {
			width = actualColumnWidths[columnsNumber + 1] + borderWidth;
		} else {
			width = actualColumnWidths[columnsNumber] + borderWidth;
		}
		return width;
	};
	// �õ��еĳ�ʼλ������X
	sTable.getLeftMargin = function() {
		if (sTableHide.getData("hasOperationColumn")) {
			var actualColumnWidths = sTableHide.getData("actualColumnWidths");
			if (window.navigator.userAgent.indexOf("MSIE") != -1) {
				return actualColumnWidths[0];
			} else {
				return actualColumnWidths[0] + 1;
			}
		} else {
			return 0;
		}
	};
	// �õ��ж�����
	sTable.getColumnCount = function() {
		var tableInfo = sTableHide.getCustomObject("tableInfo");
		return tableInfo.columns.length;
	};
	// �õ��и�
	sTable.getRowHeight = function() {
		var tableInfo = sTableHide.getCustomObject("tableInfo");
		return tableInfo.rowHeight;
	};
	// �õ�����ѡ����
	sTable.getSelections = function() {
		var selections = sTableHide.getCustomObject("selections");
		if (selections) {
			return selections.ids;
		} else {
			return null;
		}
	};
	// �õ���ѡ��
	sTable.getSelection = function() {
		var ids = sTable.getSelections();
		if (ids == null && ids.length == 0) {
			return null;
		} else {
			return ids[0];
		}
	};
	// ������ѡ��,���뵱ǰ��ID
	sTable.setRowClickSelection = function(rowId) {
		widget.exeMethod("setRowClickSelection", [rowId]);
	};
	// ȡ������ѡ��ģʽ
	sTable.getSelectionMode = function(rowId) {
		var tableInfo = sTableHide.getCustomObject("tableInfo");
		return tableInfo.selctionMode;
	};
	// ͨ������ŷ�������
	sTable.getColNameByNumber = function(number) {
		var tableInfo = sTableHide.getCustomObject("tableInfo");
		// var selctionMode = tableInfo.selctionMode;
		// if(selctionMode == 'Single' || selctionMode == 'Multi') {
		// number -= 1;
		// }
		if (number < tableInfo.columns.length) {
			return tableInfo.columns[number].name;
		}
		return "";
	}
	// �����ַ���
	sTable.findText = function(text) {
		widget.exeMethod("findText", [text]);
	}
	// ��һ��,��һ��,�л���ɫ
	sTable.changeColor = function(searchArray, searchNumber) {
		widget.exeMethod("changeSearchColor", [searchArray, searchNumber]);
	}
	//

};
/** * * * * * * * * * * * * *��ͷ�ؼ� * * * * * * * * * * * * * * * * * * * * */
var STableHeader = function() {
};

STableHeader.handleClientAction = function(event, widget) {
	var values = event.getActionValue();
	var sTable = widget.parent().parent();
	var sTableHide = sTable.children()[sTable.children().length - 1];
	var action = values.substring(0, values.indexOf(":"));
	var number = sTable.children()[1].children()[0].children().length;
	var stable_content = sTable.children()[1].children()[0].children()[number
			- 1];
	if (action == "sort") {
		value = values
				.substring(Number(values.indexOf(":") + 1), values.length);
		var splitString = value.split(":");
		sTableHide.setCustomObject("sort", {
					"column" : splitString[0],
					"direction" : splitString[1]
				});
		sTableHide.notifyAction();
	} else if (action == "selall") {
		value = values
				.substring(Number(values.indexOf(":") + 1), values.length);
		var splitString = value.split(":");
		stable_content.exeMethod("childrenSel", [splitString[0]]);
	}
};
