var SaasEditTable = function() {
};

SaasEditTable.handleClickEvent = function(event, widget) {
	var eventData = event.getEventData();
	SaasEditTable.moveToCell(widget, event.getEventData().col, event
			.getEventData().row);
};

SaasEditTable.handleTextKeydown = function(event, widget) {
	if (widget.isPanelVisible && widget.isPanelVisible()) {
		var innerWidget = widget.findByType('LWList', 3);
		if (!innerWidget) {
			innerWidget = widget.findByType('List', 3);
		}
		if (innerWidget) {
			innerWidget.forceFocus();
			event.setDoit(false);
			return;
		}
	}
	var rowIndex = widget.parent().getData("currentEditIndex");
	var sTable = widget.parent().parent().parent();
	if (event.getKeyCode() == 27) { // esc
		SaasEditTable.hideEditors(sTable);
		event.setDoit(false);
		return;
	} else if (event.getKeyCode() == 38) { // up
		SaasEditTable.tryMoveToEditableCell(sTable, SaasEditTable
				.getEditorColumnIndex(widget), --rowIndex, false);
		event.setDoit(false);
	} else if (event.getKeyCode() == 40) { // down
		SaasEditTable.tryMoveToEditableCell(sTable, SaasEditTable
				.getEditorColumnIndex(widget), ++rowIndex, true);
		event.setDoit(false);
	} else if (event.getKeyCode() == 13 || event.getKeyCode() == 9) {
		if (event.isShift()) {
			var prev = widget.prev();
			if (prev != null) {
				SaasEditTable.tryMoveToEditableCell(sTable, SaasEditTable
						.getEditorColumnIndex(prev), rowIndex, false);
				event.setDoit(false);
			} else {
				var siblings = widget.parent().children();
				SaasEditTable.tryMoveToEditableCell(sTable, SaasEditTable
						.getEditorColumnIndex(siblings[siblings.length - 3]),
						--rowIndex, false);
				event.setDoit(false);
			}
		} else {
			var next = widget.next();
			if (next.getType() != "Composite") {
				SaasEditTable.tryMoveToEditableCell(sTable, SaasEditTable
						.getEditorColumnIndex(next), rowIndex, true);
				event.setDoit(false);
			} else {
				var siblings = widget.parent().children();
				SaasEditTable.tryMoveToEditableCell(sTable, SaasEditTable
						.getEditorColumnIndex(siblings[0]), ++rowIndex, true);
				event.setDoit(false);
			}
		}
	}
};

SaasEditTable.getEditorColumnIndex = function(editor) {
	return editor.getCustomObject("columnInfo").index;
};

SaasEditTable.tryMoveToEditableCell = function(sTable, columnIndex, rowIndex,
		nextOrPrev, preferColumnIndex) {
	if (preferColumnIndex == null) {
		preferColumnIndex = columnIndex;
	}
	var sTableHide = sTable.get(2);
	var bodyArea = sTable.get(1).children(0).get(0);
	var rowId = SaasEditTable.getRowIdByIndex(sTable, rowIndex);
	var rowData = sTableHide.getCustomObject(rowId);
	//
	if (rowIndex >= sTable.getRowIds().length || rowIndex < 0) {
		return;
	}
	var controls = bodyArea.children();
	if (controls.length > 2) {
		for ( var i = 0; i < controls.length - 2; i++) {
			if (columnIndex == SaasEditTable.getEditorColumnIndex(controls[i])) {
				if (rowData.values[controls[i].getID()] != null) {
					SaasEditTable.moveToCell(sTable, columnIndex, rowIndex);
					return;
				} else {
					if (nextOrPrev) { // find next
						if (i < controls.length - 3) {
							SaasEditTable
									.tryMoveToEditableCell(
											sTable,
											SaasEditTable
													.getEditorColumnIndex(controls[i + 1]),
											rowIndex, nextOrPrev,
											preferColumnIndex);
							return;
						} else {
							SaasEditTable.tryMoveToEditableCell(sTable,
									preferColumnIndex, ++rowIndex, nextOrPrev,
									preferColumnIndex);
							return;
						}
					} else { // find prev
						if (i > 0) {
							SaasEditTable
									.tryMoveToEditableCell(
											sTable,
											SaasEditTable
													.getEditorColumnIndex(controls[i - 1]),
											rowIndex, nextOrPrev,
											preferColumnIndex);
							return;
						} else {
							SaasEditTable.tryMoveToEditableCell(sTable,
									preferColumnIndex, --rowIndex, nextOrPrev,
									preferColumnIndex);
							return;
						}
					}
				}
			}
		}
	}
};

SaasEditTable.moveToCell = function(sTable, columnIndex, rowIndex,
		disableAutoAddRow) {
	var sTableHide = sTable.get(2);
	var bodyArea = sTable.get(1).children(0).get(0);
	var rowId = SaasEditTable.getRowIdByIndex(sTable, rowIndex);
	var rowData = sTableHide.getCustomObject(rowId);
	if (!rowData) {
		// alert("无法找到行数据对象，请检查是否正确实现表格方法");
		return;
	}
	//
	var controls = bodyArea.children();
	if (controls.length == 2) {
		return false;
	}
	// check specified row if has editor
	var hasEditValue = false;
	for ( var i = 0; i < controls.length - 2; i++) {
		if (rowData.values[controls[i].getID()] != null) {
			hasEditValue = true;
			break;
		}
	}
	// if specified row has not editor, return false
	if (!hasEditValue) {
		return false;
	}

	//
	var y = sTable.getRowHeight() * rowIndex;
	var height = sTable.getRowHeight();

	//
	bodyArea.setData("currentEditIndex", rowIndex + "");
	bodyArea.setData("currentEditId", rowId);
	//
	for ( var i = 0; i < controls.length - 2; i++) {
		var control = controls[i];
		var valueObject = rowData.values[control.getID()];
		if (valueObject == null) {
			control.setVisible(false);
			continue;
		}
		var index = SaasEditTable.getEditorColumnIndex(control);
		control.setText(valueObject.value);
		control.setDescription(valueObject.desc);
		control.setY(y);
		control.setHeight(height - 1);
		var x = sTable.getLeftMargin();
		for ( var j = 1; j < index + 1; j++) {
			x += Number(sTable.getColumnWidth(j - 1));
		}
		control.setX(x); // 
		control.setWidth(sTable.getColumnWidth(index) - 1);
		control.setVisible(true);
		control.setBackColor("0xb3edf1");
		//
		if (control.setPanelVisible) {
			control.setPanelVisible(false);
		}
	}
	var matchControl;
	// try match specified column editor
	for ( var i = 0; i < controls.length - 2; i++) {
		var control = controls[i];
		if (columnIndex == SaasEditTable.getEditorColumnIndex(control)) {
			matchControl = control;
		}
	}
	// if not matched or matched editor is not visible, try match first visible
	// editor
	if (!matchControl || !matchControl.isVisible()) {
		for ( var i = 0; i < controls.length - 2; i++) {
			if (controls[i].isVisible()) {
				matchControl = controls[i];
				break;
			}
		}
	}
	// 
	if (matchControl) {
		matchControl.setStartSelectionPosition(0);
		matchControl.setEndSelectionPosition(1000);
		matchControl.forceFocus();
		if (matchControl.setPanelVisible) {
			// debugger
			matchControl.setPanelVisible(true);
			// matchControl.findByType('LWList',3).setSelected(0);
		}
	}

	//
	if (!disableAutoAddRow) {
		SaasEditTable.tryAutoAddRow(sTable, rowIndex);
	}

	//
	if (sTable.getSelectionMode() == "Row" && sTable.getSelection() != rowId) {
		sTable.setRowClickSelection(rowId);
	}
	//
	return true;
};

SaasEditTable.tryAutoAddRow = function(sTable, currRow) {
	var sTableHide = sTable.get(2);
	var info = sTableHide.getCustomObject("editTableInfo");
	if (info && info.autoAddRow && (currRow == sTable.getRowIds().length - 1)) {
		var notityActionObject = sTableHide
				.getCustomObject("notityActionObject");
		if (notityActionObject == null) {
			notityActionObject = {};
		}
		notityActionObject.autoAddRow = {};
		sTableHide.setCustomObject("notityActionObject", notityActionObject);
		sTableHide.notifyAction();
	}
};

SaasEditTable.handleTextButtonSelect = function(event, widget) {
	if (event.getCause() == DNA.JWT.BUTTON) {
		widget.notifyAction();
	}
};

SaasEditTable.handleTextBlur = function(event, widget) {
	//
	debugger
	var bodyArea = widget.parent();
	var sTable = bodyArea.parent().parent();
	var sTableHide = sTable.get(2);
	//
	var rowIndex = bodyArea.getData("currentEditIndex")
	var rowId = SaasEditTable.getRowIdByIndex(sTable, rowIndex);
	var rowData = sTableHide.getCustomObject(rowId);
	if (rowData) {
		var valueObject = rowData.values[widget.getID()];
		if(valueObject) {
			var decimal = sTable.getDecimal(rowId, SaasEditTable
					.getEditorColumnIndex(widget));
			widget.setText(valueObject.value);
			if (valueObject.desc) {
				widget.setDescription(valueObject.desc);
			}
		}
	}
};

SaasEditTable.handleTextChange = function(event, widget) {
	if (widget.getData("formualing")) {
		return;// 避免递归计算
	}
	// debugger;
	//
	var columnInfo = widget.getCustomObject("columnInfo");
	var bodyArea = widget.parent();
	var sTable = bodyArea.parent().parent();
	var sTableHide = sTable.get(2);
	var browser = bodyArea.get(bodyArea.children().length - 1);
	var rowIndex = bodyArea.getData("currentEditIndex")
	var rowId = SaasEditTable.getRowIdByIndex(sTable, rowIndex);
	var rowData = sTableHide.getCustomObject(rowId);
	if (!rowData) {
		// alert("无法找到行数据对象，请检查是否正确实现表格方法");
		return;
	}
	var processFormula = false;
	var valueObject = rowData.values[widget.getID()];
	if (valueObject.value != widget.getText()) {
		//
		var colIndex = SaasEditTable.getEditorColumnIndex(widget);
		//
		if (columnInfo.type == 'text') {
			valueObject.value = widget.getText();
		} else if (columnInfo.type == 'number') {
			var decimal = sTable.getDecimal(rowId, colIndex);
			valueObject.value = SComponent
					.trimNumber(widget.getText(), decimal);
			valueObject.desc = SComponent.formatNumber(valueObject.value,
					decimal);
		} else if (columnInfo.type == 'date') {
			valueObject.value = widget.getText();
		} else {
			valueObject.value = widget.getText();
			valueObject.desc = widget.getDescription();
		}

		//
		sTable.fireClientEvent("valueChanged", {
			id : rowId,
			row : rowIndex,
			col : colIndex,
			columnName : sTable.getColNameByNumber(colIndex)
		});
		processFormula = true;
	}

	//
	sTableHide.setCustomObject(rowId, rowData);
	var cellText = (valueObject.desc != null && valueObject.desc != '') ? valueObject.desc
			: valueObject.value;
	sTable.setCellText(rowIndex, SaasEditTable.getEditorColumnIndex(widget),
			cellText);

	//
	if (!processFormula) {
		return;
	}
	widget.setData("formualing", "true");
	try {
		var formulas = columnInfo.formulas;
		for ( var i = 0; i < formulas.length; i++) {
			var formula = formulas[i];
			if (formula.type == "asign") {
				var express = formula.express;
				var expressTarget = formula.target;
				//
				var parent = widget.parent();
				var siblings = parent.children();
				for ( var j = 0; j < siblings.length - 2; j++) {
					var editColumnValue = rowData.values[siblings[j].getID()];
					var editColumnInfo = siblings[j]
							.getCustomObject("columnInfo");
					var v = null;
					if (editColumnInfo.type == 'combo2') { // 用desc做计算
						if (editColumnValue && editColumnValue.desc) {
							v = editColumnValue.desc;
						}
					} else {
						if (editColumnValue && editColumnValue.value) {
							v = editColumnValue.value;
						}
					}
					if (!isNaN(v)) {
						var s1 = "[" + siblings[j].getID() + "]";
						var s2 = "(" + v + ")";
						while (express.indexOf(s1) != -1) {
							express = express.replace(s1, s2);
						}
					}
				}
				// debugger
				var extraDatas = sTable.getExtraDatas(rowId);
				for ( var name in extraDatas) {
					var v = extraDatas[name];
					var s1 = "[#" + name + "]";
					var s2 = "(" + v + ")";
					if (v && !isNaN(v)) {
						while (express.indexOf(s1) != -1) {
							express = express.replace(s1, s2);
						}
					}
				}
				// alert(express);
				try {
					var result = eval(express);
					if (!isNaN(result) && result != Infinity) {
						result = "" + result;
						if (expressTarget.charAt(0) == '#') { // 对extraData进行赋值
							expressTarget = expressTarget.substring(1);
							var extraDatas = sTable.getExtraDatas(rowId);
							extraDatas[expressTarget] = SComponent.trimNumber(
									result, 2);
							sTable.fireClientEvent("valueChanged", {
								id : rowId,
								extraName : expressTarget
							});
						} else {
							var matchEditor = false;
							for ( var j = 0; j < siblings.length - 2; j++) {
								if (siblings[j].getID() == expressTarget) {
									if (!siblings[j].getData("formualing")
											&& rowData.values[siblings[j]
													.getID()] != null) {
										var decimal = sTable
												.getDecimal(
														rowId,
														SaasEditTable
																.getEditorColumnIndex(siblings[j]));
										siblings[j].setText(SComponent
												.trimNumber(result, decimal));
										siblings[j].setDescription(SComponent
												.formatNumber(result, decimal));
										matchEditor = true;
									}
									break;
								}
							}
							// 如果目标不是编辑器，则需要匹配普通列
							if (!matchEditor) {
								var colCount = sTable.getColumnCount();
								var matchColumn = -1;
								for ( var col = 0; col < colCount; col++) {
									if (sTable.getColNameByNumber(col) == expressTarget) {
										matchColumn = col;
										break;
									}
								}
								if (matchColumn > -1) {
									sTable.setCellText(rowIndex, matchColumn,
											result);
								}
							}
						}
					}
				} catch (e) {
				}
			}
		}
	} finally {
		widget.setData("formualing", null);
	}

};

SaasEditTable.getRowIdByIndex = function(sTable, index) {
	return sTable.getRowIdByRowIndex(parseInt(index));
};

SaasEditTable.getColumnIndex = function(sTable, columnName) {
	var colCount = sTable.getColumnCount();
	for ( var i = 0; i < colCount; i++) {
		if (sTable.getColNameByNumber(i) == columnName) {
			return i;
		}
	}
	return -1;
};

SaasEditTable.handleTableInited = function(event, widget) {
	widget._setCellText = widget.setCellText;
	widget.setCellText = function(targetRow, colIndex, text) {
		var rowId = null;
		if (isNaN(targetRow)) {
			rowId = targetRow;
		} else {
			rowId = widget.getRowIdByRowIndex(targetRow);
		}
		widget._setCellText(rowId, colIndex, SComponent.formatNumber(text,
				widget.getDecimal(rowId, colIndex)));

	};
	widget.getCellValue = function(rowId, columnName) {
		try {
			var rowData = widget.get(2).getCustomObject(rowId);
			return rowData.values[columnName].value;
		} catch (e) {
			return null;
		}
	};
	widget.getExtraData = function(rowId, name) {
		try {
			var rowData = widget.get(2).getCustomObject(rowId);
			return rowData.extraData[name];
		} catch (e) {
			return null;
		}
	};
	widget.getExtraDatas = function(rowId) {
		try {
			var rowData = widget.get(2).getCustomObject(rowId);
			return rowData.extraData;
		} catch (e) {
			return null;
		}
	};
	widget.updateEditValue = function(rowId, nameValues) {
		var sTableHide = widget.get(2);
		var rowData = sTableHide.getCustomObject(rowId);
		if (!rowData || !rowData.values) {
			return;
		}
		for ( var i = 0; i < nameValues.length; i++) {
			var nameValue = nameValues[i];
			if (nameValue.value == null) {
				rowData.values[nameValue.name] = null;
			} else {
				rowData.values[nameValue.name] = {
					value : nameValue.value,
					desc : nameValue.text
				};
			}
		}
		sTableHide.setCustomObject(rowId, rowData);
		//
		var rowIds = widget.getRowIds();
		var rowIndex = -1;
		for ( var i = 0; i < rowIds.length; i++) {
			if (rowIds[i] == rowId) {
				try {
					var currentColumnIndex = SaasEditTable
							.getEditorColumnIndex(Display.getFocusControl());
					SaasEditTable.moveToCell(widget, currentColumnIndex, i);
					break;
				} catch (e) {
				}
			}
		}
	};
	widget.getDecimal = function(rowId, columnIndex) {
		try {
			var rowData = widget.get(2).getCustomObject(rowId);
			var decimal = rowData.decimals[columnIndex];
			if (decimal != null) {
				return Number(decimal);
			}
		} catch (e) {
		}
		return -1;
	};
	widget.setCellOptions = function(rowId, columnName, options) {
		var rowData = widget.get(2).getCustomObject(rowId);
		if (!rowData) {
			return;
		}
		rowData.options[columnName] = options;
		widget.get(2).setCustomObject(rowId, rowData);
	};
};

SaasEditTable.handleMouseOver = function(event, widget) {
	SaasEditTable.showActionBar(event.getEventData().row, widget);
};

SaasEditTable.handleMouseOut = function(event, widget) {
	// var controls = widget.get(1).children(0).get(0).children();
	// var menuArea = controls.get(controls.length - 2);
	// menuArea.setVisible(false);
};

SaasEditTable.handleRowUpdated = function(event, widget) {
	var bodyArea = widget.get(1).children(0).get(0);
	// try relocated action bar
	SaasEditTable.showActionBar(bodyArea.getData("currentActionRowIndex"),
			widget);
	// try relocated editor
	if (bodyArea.getData("currentEditId") == event.getEventData()) {
		var rowIds = widget.getRowIds();
		var rowIndex = -1;
		for ( var i = 0; i < rowIds.length; i++) {
			if (rowIds[i] == event.getEventData()) {
				rowIndex = i;
				break;
			}
		}
		SaasEditTable.moveToCell(widget, 0, rowIndex);
	}
};

SaasEditTable.handleRowAdded = function(event, widget) {
	var bodyArea = widget.get(1).children(0).get(0);
	// try relocated action bar
	SaasEditTable.showActionBar(bodyArea.getData("currentActionRowIndex"),
			widget);
	//
	var currentEditIndex = bodyArea.getData("currentEditIndex");
	if (currentEditIndex) {
		var currentColumnIndex = 0;
		try {
			currentColumnIndex = SaasEditTable.getEditorColumnIndex(Display
					.getFocusControl());
		} catch (e) {
		}
		SaasEditTable.moveToCell(widget, currentColumnIndex, currentEditIndex);
	}
	//
	widget.fireClientEvent("valueChanged", {});
};

SaasEditTable.handleRowRemoved = function(event, widget) {
	var bodyArea = widget.get(1).children(0).get(0);
	// try relocated action bar
	SaasEditTable.showActionBar(bodyArea.getData("currentActionRowIndex"),
			widget);
	//
	var currentEditId = bodyArea.getData("currentEditId");
	var removedId = event.getEventData();
	if (removedId == currentEditId) {
		SaasEditTable.hideEditors(widget);
	} else if (currentEditId) {
		var rowIds = widget.getRowIds();
		var rowIndex = -1;
		for ( var i = 0; i < rowIds.length; i++) {
			if (rowIds[i] == currentEditId) {
				rowIndex = i;
				break;
			}
		}
		SaasEditTable.moveToCell(widget, 0, rowIndex, true);
	}
	//
	widget.fireClientEvent("valueChanged", {});
};

SaasEditTable.hideEditors = function(sTable) {
	var bodyArea = sTable.get(1).children(0).get(0);
	bodyArea.setData("currentEditId", null);
	bodyArea.setData("currentEditIndex", null);
	var controls = bodyArea.children();
	for ( var i = 0; i < controls.length - 2; i++) {
		controls[i].setVisible(false);
	}
};

SaasEditTable.showActionBar = function(rowIndex, sTable) {
	try {
		var sTableHide = sTable.get(2);
		var bodyArea = sTable.get(1).children(0).get(0);
		bodyArea.setData("currentActionRowIndex", rowIndex + "");
		var controls = bodyArea.children();
		var menuArea = controls.get(controls.length - 2);
		var buttons = menuArea.children();
		if (buttons.length == 0) {
			return;
		}
		for ( var i = 0; i < buttons.length; i++) {
			buttons[i].setVisible(false);
		}
		var rowId = sTable.getRowIdByRowIndex(rowIndex);
		var rowHeight = sTable.getRowHeight();
		var x = sTable.getLeftMargin() + 1;
		var colCount = sTable.getColumnCount();
		for ( var i = 0; i < colCount - 1; i++) {
			x += sTable.getColumnWidth(i);
		}
		menuArea._getWidget().setBounds({
			x : x,
			y : rowIndex * rowHeight + 1,
			width : sTable.getColumnWidth(colCount - 1) - 2,
			height : rowHeight - 1
		})
		menuArea.setVisible(true);
		menuArea.setCustomObject("currentRow", {
			id : rowId
		});
		var rowActions = sTableHide.getCustomObject(rowId).rowActions;
		var buttonLeft = 3;
		for ( var i = 0; i < buttons.length; i++) {
			var actionId = buttons[i].getID();
			for ( var j = 0; j < rowActions.length; j++) {
				if (rowActions[j] == actionId) {
					var size = {x:buttons[i].getWidth(),y:buttons[i].getHeight()};
					if(size.x == 0 || size.y == 0) {
						var size = buttons[i].computeSize();
					}
					buttons[i]._getWidget().setBounds({
						x : buttonLeft,
						y : (rowHeight - size.y) / 2,
						width : size.x,
						height : size.y
					});
					buttons[i].setVisible(true);
					buttonLeft += (size.x + 3);
				}
			}
		}
	} catch (e) {
	}
};

//
SaasEditTable.handleListEditor2Popup = function(event, widget) {
	// debugger;
	var bodyArea = widget.parent();
	var sTable = bodyArea.parent().parent();
	var sTableHide = sTable.get(2);
	var rowIndex = bodyArea.getData("currentEditIndex")
	var rowId = SaasEditTable.getRowIdByIndex(sTable, rowIndex);
	var rowData = sTableHide.getCustomObject(rowId);
	if (!rowData || !rowData.options) {
		return;
	}
	var options = rowData.options[widget.getID()];
	if (!options) {
		return;
	}
	var listControl = widget.findByType('List', 3);
	var items = listControl.children();
	var selection = null;
	for ( var i = 0; i < items.length; i++) {
		if (i < options.length) {
			items[i]._setID(options[i].value);
			items[i].setText(options[i].desc);
			items[i].setVisible(true);
			if (options[i].value == widget.getText()
					|| options[i].desc == widget.getText()) {
				selection = i;
			}
		} else {
			items[i].setVisible(false);
		}
	}
	if (selection) {
		listControl.setSelectedIndices([ selection ]);
	}
	//
	widget.pack();
};

//
SaasEditTable.handleActionButton = function(event, widget) {
	var sTable = widget.parent().parent().parent().parent();
	var rowId = widget.parent().getCustomObject("currentRow").id;
	var actionName = widget.getID();
	var p = Display.mapping(widget, {
		x : event.getX(),
		y : event.getY()
	}, Display.getShell());
	var eventData = {
		actionName : actionName,
		actionValue : null,
		rowId : rowId,
		x : p.x,
		y : p.y,
		notifyServer : true
	};
	sTable.fireClientEvent("tableAction", eventData);
	if (eventData.notifyServer) {
		widget.setCustomObject("actionInfo", eventData);
	} else {
		event.stopServerEvent();
	}
};
