//销售配货相关脚本
var DistributeSalesOrder = function() {
};

DistributeSalesOrder.ResetDistributeSalesOrder = function(event,widget){	
		setTimeout(function(){
			widget.notifyAction();
			DistributeSalesOrder.ResetDistributeSalesOrder(event,widget);
		},1000*10);
}

DistributeSalesOrder.handleStoreSelection = function(event, widget) {
	var id = widget.getRowIdByRowIndex(event.getEventData().row);
	widget.setServerObject("customSelectionInfo", {
		selecting : id
	});
	widget.notifyAction();
};


DistributeSalesOrder.handleActionPerformed = function(event, widget) {
	var eventData = event.getEventData();
	// debugger;
	if(eventData.actionName == "View") {
		eventData.actionValue = (eventData.x - 10) + ":" + (eventData.y + 10);
	}
};


var SaaSOrder = function() {
};

SaaSOrder.handleTableDataChanged = function(event, widget) {
	var eventData = event.getEventData();
	if (eventData.columnName == 'Sale_Price') {
		var select = widget.getCellValue(eventData.id, 'Sale_Price');
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
	}
	if (eventData.extraName == "Amount") {
		var totalAmountText = widget.parent().find("TotalAmount", 5);
		var totalDisAmountText = widget.parent().find("discount_amount", 5);
		var rowIds = widget.getRowIds();
		var totalAmount = 0;
		if (rowIds) {
			for ( var i = 0; i < rowIds.length; i++) {
				var v = widget.getExtraData(rowIds[i], "Amount");
				if (!isNaN(v)) {
					totalAmount += Number(v);
				}
			}
		}
		if (null != totalDisAmountText) {
			var totalDisAmount = Number(totalDisAmountText.getText());
			if (!isNaN(totalDisAmount)) {
				totalAmount -= totalDisAmount;
			}
		}
		totalAmountText.setText(SComponent.trimNumber(totalAmount,2));
		totalAmountText.setDescription(SComponent.formatNumber(totalAmount,2));
	}
	if (eventData.extraName == "ReturnAmount") {
		var totalAmountText = widget.parent().find("TotalAmount", 5);
		var rowIds = widget.getRowIds();
		var totalAmount = 0;
		if (rowIds) {
			for ( var i = 0; i < rowIds.length; i++) {
				var v = widget.getExtraData(rowIds[i], "ReturnAmount");
				if (!isNaN(v)) {
					totalAmount += Number(v);
				}
			}
		}
		totalAmountText.setText(SComponent.trimNumber(totalAmount,2));
		totalAmountText.setDescription(SComponent.formatNumber(totalAmount,2));
	}

	if (!eventData.columnName && !eventData.extraName) {
		var totalAmountText = widget.parent().find("TotalAmount", 5);
		var totalDisAmountText = widget.parent().find("discount_amount", 5);
		SaaSOrder.calcAllData(widget, totalDisAmountText, totalAmountText);
	}
};

SaaSOrder.handleTotalDisAmountChanged = function(event, widget) {
	var parent = widget.parent().parent().parent().parent();
	var table = parent.find("STable_MainTable", 10);
	var totalAmountText = parent.find("TotalAmount", 10);
	SaaSOrder.calcAllData(table, widget, totalAmountText);
};

SaaSOrder.calcAllData = function(table, totalDisAmountText, totalAmountText) {
	// debugger;
	var totalDisAmount;
	if (totalDisAmountText) {
		totalDisAmount = Number(totalDisAmountText.getText());
	}
	var rowIds = table.getRowIds();
	var totalAmount = 0;
	if (rowIds) {
		for ( var i = 0; i < rowIds.length; i++) {
			var v = table.getExtraData(rowIds[i], "Amount");
			if (isNaN(v) || 0 == v) {
				v = table.getExtraData(rowIds[i], "ReturnAmount");
			}
			if (!isNaN(v)) {
				totalAmount += Number(v);
			}
		}
	}
	if (!isNaN(totalDisAmount)) {
		totalAmount -= totalDisAmount;
	}
	totalAmountText.setText(SComponent.trimNumber(totalAmount,2));
	totalAmountText.setDescription(SComponent.formatNumber(totalAmount,2));
};

SaaSOrder.handleActionPerformed = function(event, widget) {
	var eventData = event.getEventData();
	if(eventData.actionName == "LookInventory") {
		eventData.actionValue = (eventData.x - 20) + ":" + (eventData.y - 3);
	}
};

//零售相关脚本
var SaaSRetail = function() {
};

SaaSRetail.handleRetriveAction = function(event, widget) {
	var callback = {
		run : function(processCurrentPage) {
			widget.setServerObject("actionData", {
				processCurrentPage : processCurrentPage
			});
			widget.notifyAction();
		}
	};
	SaasNavigator.tryDisposeFramePage(widget,callback,"取回","取回");
};

//零售添加商品相关脚本
var RetailAddGoods = function() {
};
RetailAddGoods.handleEnterKeyAction = function(event, widget) {
	///*
	var headerArea = widget.parent();
	if (event.getKeyCode() == 13) {
		widget.notifyAction();
	}
	//*/
	/*
	var contentArea = widget.parent();
	widget = widget.next();
	while (widget != null) {
		if (widget.getType() != "Label") {
			widget.forceFocus();
			return;
		} else {
			widget = widget.next();
		}
	}
	contentArea.findByType("Button", 3).forceFocus();
	contentArea.notifyAction();
	*/
};

//零售收款相关脚本
var ReceiptRetail = function() {
};


ReceiptRetail.handleReceiptDocChange = function(event, widget) {
	var changeText = widget.parent().parent().find("Label_ChangeAmount", 4);
	var amountStr = widget.parent().parent().find("Radio_ReceiptAmount").getText();
	var amount = 0;
	if(amountStr != null) {
		var x = amountStr.split(',');
		amount = parseFloat(x.join(""));
	}
	var tempAmount = widget.getText();
	if(tempAmount != null && changeText != null) {
		changeText.setText("" + (Number(tempAmount) - amount));
	}
	changeText.parent().layout();
};

var Produce = function() {
};
Produce.onReceiveReturn = function(event, widget) {
	widget.notifyAction();
};

var OnlineReturn = function() {
};
OnlineReturn.handleTableDataChange = function(event, widget) {
// debugger;
	var eventData = event.getEventData();
	if (eventData.columnName == 'returnCount') {
		var price = widget.getExtraData(eventData.id, 'price');
		var count = widget.getCellValue(eventData.id, 'returnCount');
		
		widget.setCellText(eventData.id, 8, SComponent.trimNumber(Number(price) * Number(count),2));
		
		var rowIds = widget.getRowIds();
		var totalAmount = 0;
		if (rowIds) {
			for (var i = 0; i < rowIds.length; i++) {
				var price = widget.getExtraData(rowIds[i], 'price');
				var count = widget.getCellValue(rowIds[i], 'returnCount');
				if (!isNaN(price) && !isNaN(count)) {
					totalAmount += Number(price) * Number(count);
				}
			}
		}
		var totalAmountText = widget.parent().parent().find("Text_TotalReturnAmount", 5);
		totalAmountText.setText(SComponent.trimNumber(totalAmount,2));
	}
};

var OnlineOrder = function() {
};

OnlineOrder.handleTableDataChange = function(event, widget) {
//debugger;
	var eventData = event.getEventData();
	if (eventData.columnName == 'separateCount') {
		var price = widget.getExtraData(eventData.id, 'price');
		var count = widget.getCellValue(eventData.id, 'separateCount');
		widget.setCellText(eventData.id, 7, SComponent.trimNumber(Number(price) * Number(count),2));
		var rowIds = widget.getRowIds();
		var totalAmount = 0;
		if (rowIds) {
			for (var i = 0; i < rowIds.length; i++) {
				var price = widget.getExtraData(rowIds[i], 'price');
				var count = widget.getCellValue(rowIds[i], 'separateCount');
				if (!isNaN(price) && !isNaN(count)) {
					totalAmount += Number(price) * Number(count);
				}
			}
		}
		var totalAmountText = widget.parent().parent().find("Label_TotalAmount", 5);
		totalAmountText.setText(SComponent.trimNumber(totalAmount,2));
	}
};
