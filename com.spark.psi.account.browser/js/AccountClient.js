var Account = function() {
};

Account.handleTableDataChanged = function(event, widget) {
	widget.notifyAction();
	/*
	var eventData = event.getEventData();
	if (eventData.columnName == 'applyAmount') {
		var select = widget.getCellValue(eventData.id, 'applyAmount');
		var rowIds = widget.getSelections();
		var totalAmount = 0;
		if (rowIds) {
			for ( var i = 0; i < rowIds.length; i++) {
				var v = widget.getCellValue(rowIds[i], "applyAmount");
				if (!isNaN(v)) {
					totalAmount += Number(v);
				}
			}
		}
		var totalAmountText = widget.parent().find("Label_TotalAmount", 5);
		totalAmountText.setText(SComponent.trimNumber(totalAmount,2));
	}
	*/
};

Account.handleAdjustDocChange = function(event, widget) {
debugger;
	var saleAmountLabel = widget.parent().parent().parent().parent().parent().find("Label_TotalSaleAmount", 4);
	var percentageAmountLabel = widget.parent().parent().parent().parent().parent().find("Label_TotalPercentageAmount", 4); 
	var payingAmountText = widget.parent().parent().find("Text_TotalAmount", 4); 
	
	var saleAmountStr = saleAmountLabel.getText();
	var percentageAmountStr = percentageAmountLabel.getText();
	var adjustAmountStr = widget.getText();
	
	var totalSalesAmount = 0;
	var totalPercentageAmount = 0;
	var adjustAmount = 0;
	
	if(saleAmountStr != null) {
		totalSalesAmount = getNumberValueWithoQFW(saleAmountStr);
	}
	if(percentageAmountStr != null) {
		totalPercentageAmount = getNumberValueWithoQFW(percentageAmountStr);
	}
	if(adjustAmountStr != null) {
		adjustAmount = getNumberValueWithoQFW(adjustAmountStr);
	}
	
	payingAmountText.setText("" + SComponent.trimNumber((totalSalesAmount - totalPercentageAmount + adjustAmount),2));
	payingAmountText.parent().layout();
};

function getNumberValueWithoQFW(amountStr) {
	var strArray = amountStr.split(",");
	var newAmountStr = "";
	for (var i = 0; i < strArray.length; i++) {
		newAmountStr += strArray[i];
	}
	if (newAmountStr.length > 0) {
		return Number(newAmountStr);
	} else {
		return 0.0;
	}
}

var Receipt = function() {
};
Receipt.onReceiveLog = function(event, widget) {
	widget.notifyAction();
};