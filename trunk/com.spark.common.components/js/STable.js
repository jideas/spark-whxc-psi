/** * * * * * * * * * * * 隐藏控件* * * * * * * * * * * * * * * * * * * * * * */
var STableHide = function() {
};
// 表格边框的宽度
var STableHideProperty = {
	borderWidth : 2
}

STable = function() {
}
/**
 * 判断DNA是否加载完毕
 */
STable.handleClientResize = function(event, widget) {
	STableHide.resizeTable(widget.children()[widget.children().length - 1],true);
};

STable.handleClientInit = function(event, widget) {
	STable.handleClientResize(event, widget);
	widget.fireClientEvent("tableInited", {});
}

/**
 * 处理服务器传递过来的行操作
 */
STableHide.handleServerNotify = function(event, widget) {
	var rowOperations = widget.getCustomObject("rowOperations");
	var operationList = rowOperations.operationList;
	// TOOD：处理操作列表
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
		// 清除操作列表
		rowOperations.operationList = [];
		widget.setCustomObject("rowOperations", rowOperations);
	}

	// 行选中
	var selectRow = widget.getCustomObject("selectRow");
	if (selectRow != null) {
		widget.setCustomObject("selectRow", null);
		bodyTable.exeMethod("rowSelected", [selectRow.rowId]);
	}
	
	//改变单元格颜色
	var textBackgroundColor = widget.getCustomObject("textBackgroundColor");
	if(textBackgroundColor != null) {
		widget.setCustomObject("textBackgroundColor", null);
		var sTableTextColors = textBackgroundColor.sTableTextColors;
		try{
			//如果调用这个之前表格还没有构建完成，那么就会出问题，这里捕获到，但是不需要处理
			bodyTable.exeMethod("changeTextBackgroundColor", [sTableTextColors]);
		}catch(e) {
		}
	}
};


/**
 * 计算最后一列的单元格的宽度
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
 * 当浏览器的宽度大于传入的宽度的时候，需要重新计算表格每一列的宽度
 * 
 * @param {}
 *            widget STableHide
 * @param {}
 *            borderWidth 最右边单元格的边框宽度
 * @param {}
 *            availableWidth 浏览器的宽度
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
	// 表格线宽度，firefox需要特别处理
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
 * 处理浏览器尺寸发生变化或者初始化的时候
 */
STableHide.resizeTable = function(widget) {
	var sTable = widget.parent();
	var scrolledPanel = sTable.get(1);
	var bodyArea = scrolledPanel.children()[0];
	var tableInfo = widget.getCustomObject("tableInfo");
	// 滚动条的内部宽度
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
	//表格的总宽度
	var actualWidth = actualArray[0];
	//表格经过计算得到的每一列的数据
	var actualColumnWidths = actualArray[1];
	//表格最右边单元格的边框线的宽度
	var borderWidth = actualArray[2];
	
	// 是否有滚动条
	if (!isScrollbar) {
		// 如果没有滚动条，那么最后一行的右边线是需要去掉的，firefox的width不计算边线，所以最后一行的宽度需要+1才行
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
 * 当执行新增，修改，删除操作以后，客户端要做尺寸的验证
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
	// 浏览器表格的宽度
	var availableWidth = scrolledPanel.getClientSize().width;

	var actualArray = STableHide.computeActualColumnWidths(widget,
			availableWidth);
	//表格的总宽度
	var actualWidth = actualArray[0];
	//表格经过计算得到的每一列的数据
	var actualColumnWidths = actualArray[1];
	//表格最右边单元格的边框线的宽度
	var borderWidth = actualArray[2];

	var isScrollbar = true;
	// 是否有滚动条
	if (Number(sTable.getWidth() - availableWidth) <= Number(STableHideProperty.borderWidth)) {
		// 有2PX的边框，所以判断是不是大于2，来表示纵向滚动条出现
		// 如果没有滚动条，那么最后一行的右边线是需要去掉的，firefox的width不计算边线，所以最后一行的宽度需要+1才行
		STableHide.computeTableRightTdWidth(actualColumnWidths, borderWidth);
		isScrollbar = false;
	}
	widget.setData("actualColumnWidths", actualColumnWidths);
	// 修改客户端表格的列宽
	bodyArea.setWidth(actualWidth);
	bodyTable.setWidth(actualWidth);

	bodyTable.exeMethod("setColumnWidths", [actualColumnWidths, isScrollbar,
					rowids]);

	// 设置表头的宽度
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

/** * * * * * * * * * * * 滚动控件* * * * * * * * * * * * * * * * * * * * * * */
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
	if ("bottom" == scrollPosition) { // 底部分页
		scrolledPanel.notifyAction();
	}
};
/** * * * * * * * * * * * * 表体控件 * * * * * * * * * * * * * * * * * * * * * */

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
		// 判断是否有服务器监听器
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
			// 判断是否有服务器监听器
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
 * STable初始化完成（非内容加载完毕）
 */
STableBody.handleInited = function(event, widget) {
	//
	var sTable = widget.parent().parent().parent();
	var sTableHide = sTable.children()[sTable.children().length - 1];
	// 增加setCellText方法
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
	// 通过行序号取得行ID
	sTable.getRowIdByRowIndex = function(RowNumber) {
		return sTableHide.getCustomObject("rowIds").rowIdArray.get(RowNumber);
	};
	// 得到所有的行ID
	sTable.getRowIds = function() {
		return sTableHide.getCustomObject("rowIds").rowIdArray;
	};
	// 得到指定列的宽度
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
	// 得到列的初始位置坐标X
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
	// 得到有多少列
	sTable.getColumnCount = function() {
		var tableInfo = sTableHide.getCustomObject("tableInfo");
		return tableInfo.columns.length;
	};
	// 得到行高
	sTable.getRowHeight = function() {
		var tableInfo = sTableHide.getCustomObject("tableInfo");
		return tableInfo.rowHeight;
	};
	// 得到所有选中项
	sTable.getSelections = function() {
		var selections = sTableHide.getCustomObject("selections");
		if (selections) {
			return selections.ids;
		} else {
			return null;
		}
	};
	// 得到单选项
	sTable.getSelection = function() {
		var ids = sTable.getSelections();
		if (ids == null && ids.length == 0) {
			return null;
		} else {
			return ids[0];
		}
	};
	// 设置行选中,传入当前行ID
	sTable.setRowClickSelection = function(rowId) {
		widget.exeMethod("setRowClickSelection", [rowId]);
	};
	// 取到表格的选择模式
	sTable.getSelectionMode = function(rowId) {
		var tableInfo = sTableHide.getCustomObject("tableInfo");
		return tableInfo.selctionMode;
	};
	// 通过列序号返回列名
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
	// 查找字符串
	sTable.findText = function(text) {
		widget.exeMethod("findText", [text]);
	}
	// 上一个,下一个,切换颜色
	sTable.changeColor = function(searchArray, searchNumber) {
		widget.exeMethod("changeSearchColor", [searchArray, searchNumber]);
	}
	//

};
/** * * * * * * * * * * * * *表头控件 * * * * * * * * * * * * * * * * * * * * */
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
