var InventoryClient = function() {
};

InventoryClient.init = function() {
};

InventoryClient.handleRefactorSourceValueChanged = function(event, widget) {
	var label = widget.parent().find("Label_TotalCost",3);
	var rowIds = widget.getRowIds();
	var totalAmount = 0;
	if(rowIds) {
		for(var i = 0; i < rowIds.length; i++) {
			var cost = widget.getExtraData(rowIds[i],"Cost");
			var count = widget.getCellValue(rowIds[i],"Count");
			totalAmount  += (Number(count)*Number(cost));
		}
	}
	totalAmount = SComponent.formatNumber(totalAmount+"", 2);
	label.setText(totalAmount);
};


InventoryClient.handleRefactorDestinationValueChanged = function(event, widget) {
	// debugger
	var label = widget.parent().find("Label_TotalCost",3);
	var rowIds = widget.getRowIds();
	var totalCount = 0;
	var totalAmount = 0;
	if(rowIds) {
		for(var i = 0; i < rowIds.length; i++) {
			var count = widget.getCellValue(rowIds[i],"Count");
			var amount = widget.getCellValue(rowIds[i],"Amount");
			totalCount += Number(count);
			totalAmount  += Number(amount);
		}
	}
	totalAmount = SComponent.formatNumber(totalAmount+"", 2); 
	label.setText(totalAmount);
};

/**
  将鼠标点击位置坐标存放到actionValue中，用于浮出窗口定位
*/
InventoryClient.handleActionPerformed = function(event, widget) {
	var eventData = event.getEventData();
	if(eventData.actionName == "viewInventory") {
		eventData.actionValue = (eventData.x - 10) + ":" + (eventData.y+25);
	}
};

InventoryClient.handleTableDataChanged = function(event, widget) {
	var eventData = event.getEventData();
	if (eventData.columnName == "amount") {
		var totalAmountText = widget.parent().parent().find("Label_Amount", 5);
		var rowIds = widget.getRowIds();
		var totalAmount = 0;
		if (rowIds) {
			for ( var i = 0; i < rowIds.length; i++) {
				var v = widget.getCellValue(rowIds[i], 'amount');
				if (!isNaN(v)) {
					totalAmount += Number(v);
				}
			}
		}
		totalAmountText.setText(SComponent.trimNumber(totalAmount,2));
	}
	if (!eventData.columnName && !eventData.extraName) {
		var totalAmountText = widget.parent().parent().find("Label_Amount", 5);
		InventoryClient.calcAllData(widget, totalAmountText);
	}
};

InventoryClient.init.handleTableDataChanged = function(event, widget) {
	var eventData = event.getEventData();
	if (eventData.columnName == 'shelf') {
		var select = widget.getCellValue(eventData.id, 'shelf');
		debugger;
		var tiersCount = widget.getExtraData(eventData.id, select);
		var optionsStr = "[";
		for (var optionIndex = 0; optionIndex < tiersCount; optionIndex++) {
			optionsStr += "{value : '" + (optionIndex + 1)  + "',desc : '" + (optionIndex + 1)  + "'}";
			if (optionIndex != (tiersCount - 1)) {
				optionsStr += ",";
			}
		}
		optionsStr += "]";
		var options = eval('(' + optionsStr + ')');  
		widget.setCellOptions(eventData.id, 'tiersNo', options);
		/*
		var options = [ {
				value : '1',
				desc : '1'
			}, {
				value : '2',
				desc : '2'
			} ];
		if (select.length == 32) {
			widget.updateEditValue(eventData.id, [ {
				name : 'DisCount',
				value : null
			}, {
				name : 'DisAmount',
				value : null
			} ]);
			widget.setCellText(eventData.id, 6, '0.00');
			widget.setCellText(eventData.id, 7, '0.00');
		} else {
			widget.updateEditValue(eventData.id, [ {
				name : 'DisCount',
				value : "0.00"
			}, {
				name : 'DisAmount',
				value : "0.00"
			} ]);
		}
		*/
	}
};

InventoryClient.calcAllData = function(table, totalAmountText) {
	debugger;
	var rowIds = table.getRowIds();
	var totalAmount = 0;
	if (rowIds) {
		for ( var i = 0; i < rowIds.length; i++) {
			var v = table.getCellValue(rowIds[i], 'amount');
			if (!isNaN(v)) {
				totalAmount += Number(v);
			}
		}
	}
	totalAmountText.setText(SComponent.trimNumber(totalAmount,2));
};
