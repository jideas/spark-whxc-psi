var PSIBase = function() {
};

PSIBase.EmployeeManage = function() {
};
PSIBase.GoodsCategoryList = function() {
};
PSIBase.GoodsDetail = function() {
};

PSIBase.EmployeeManage.handleSelection = function(event, widget) {
	var selections = widget.getSelections();
	var selected = selections != null && selections.length > 0;
	var contentArea = widget.parent();
	contentArea.find('Button_DepartmentConfig', 3).setEnabled(selected);
	contentArea.find('Button_AuthConfig', 3).setEnabled(selected);
};

PSIBase.GoodsCategoryList.handleAddButtonKeyDown = function(event, widget) {
	var headerArea = widget.next();
	if (event.getKeyCode() == 13) {
		headerArea.notifyAction();
	}
};

PSIBase.CategoryDetailEdit = function() {
};

PSIBase.CategoryDetailEdit.handlePropertyTypeChanged = function(event, widget) {
	var eventData = event.getEventData();
	if (eventData.col == 1) {
		// alert();
		// widget.notify();
	}
};

PSIBase.CategoryDetailEdit.handlePropertySelection = function(event, widget) {
	// var rowId = widget.getSelection();
	// alert(widget.getCellValue(rowId,"type"));
	// widget.notify();
};

PSIBase.GoodsSelect = function() {
};

PSIBase.GoodsSelect.onSelectedGoodsWindowShow = function(event, widget) {
	widget.notifyAction();
};
PSIBase.GoodsDetail.priceOnChange = function(event, widget) {
	var price = widget.getText();
	var table = widget.parent().parent().parent().find("goodsItemTable", 5);
	var rowIds = table.getRowIds();
	var priceIndex = table.getCustomObject("PriceColumnIndex").priceIndex;
	if (rowIds) {
		for ( var i = 0; i < rowIds.length; i++) {
			var rowPrice = table.getCellValue(rowIds[i], 'price');
			if (isNaN(rowPrice) || rowPrice=="") {
				table.updateEditValue(rowIds[i], [ {
					name : 'price',
					value : price
				} ]);
				if (!isNaN(priceIndex) && priceIndex > -1) {
					table.setCellText(rowIds[i], priceIndex, price); 
				}
			}
		}
	}
};

PSIBase.UserConfig = function() {};

/**
* �޸��ֻ�����ҳ��
* ���������֤��󣬵���ʱ���·�����֤��
*/
PSIBase.UserConfig.saasEmployeeModfiyPhoneTip = function(event,widget){
	var lab_tip = widget.next().get("Label_TipCheckCode");
	var lblTitle = widget.parent().get("Label_Tip");
	widget.setEnabled(false);
	lab_tip.setText("");
	lab_tip.setVisible(true);
	lblTitle.setVisible(true);
	var time = 30;
	var tip = "(?���)���»�ȡ��֤��";
	var task = setInterval(
		function(){
	       lab_tip.setText(tip.replace("?",time));
	       time--;
	       if(time==0){
	       		clearInterval(task);
	       		widget.setEnabled(true);
	       		lab_tip.setText("");
	       }
		},1000);
};

/**
* ��������ҳ�� ����ǿ����ʾ��
*/
PSIBase.UserConfig.passwordStrengthVerification = function (event,widget){

	var obj = getEmptyPasswordCmp(widget);
	obj.get("cmplv"+passwordStrength(widget.getText()).index).setVisible(true);
};

/**
* ��������ҳ�� ����������ؼ�ʧȥ����
*/
PSIBase.UserConfig.labNewpwdFocusLost = function (event,widget){
	var obj =  getEmptyPasswordCmp(widget);
	if(widget.getText().length>20 || widget.getText().length<6){//�򿪲��Ϸ���ʾ����
			obj.get("pwdMsgWarCmp").setVisible(true);
	}else{//������ǿ������
		  obj.get("pwdMsgOkCmp").setVisible(true);
		  obj.get("pwdMsgOkCmp").children()[1].setText("����ǿ�ȣ�"+passwordStrength(widget.getText()).name)
	}
};

//��������
var getEmptyPasswordCmp = function(widget){
	var obj = widget.next();//parent().parent().parent().parent().parent()..parent()
	for(var i=0;i<obj.children().length;i++){
			obj.children()[i].setVisible(false);
	}
	return obj;
};

/**
* ��֤�����ǿ��
*/
var passwordStrength = function(pwd){
	var regAry = [
		{reg:/[-\da-zA-Z`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]*((\d+[a-zA-Z]+[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+)|(\d+[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+[a-zA-Z]+)|([a-zA-Z]+\d+[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+)|([a-zA-Z]+[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+\d+)|([-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+\d+[a-zA-Z]+)|([-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+[a-zA-Z]+\d+))[-\da-zA-Z`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]*/,index:3,name:"��ǿ"},		
		{reg:/[\da-zA-Z]*\d+[a-zA-Z]+[\da-zA-Z]*/,index:2,name:"ǿ"},
		{reg:/[a-zA-Z]{6,}/,index:1,name:"��"},
		{reg:/\d{6,20}/,index:1,name:"��"}
	]; 
	var result = 0;
	for(var i=0;i<regAry.length ;i++){
		if(regAry[i].reg.test(pwd)){
			return regAry[i];
		}
	}
	return {index:0,name:"��"};
};

PSIBase.Bom = function() {
};

PSIBase.Bom.handleTableDataChange = function(event, widget) {
	var eventData = event.getEventData();
	if (eventData.columnName == 'count'
		|| eventData.columnName == 'lossRate') {
		var count = widget.getCellValue(eventData.id, 'count');
		var lossRate = widget.getCellValue(eventData.id, 'lossRate');
		if (!isNaN(count) && !isNaN(lossRate)) {
			if (Number(lossRate) < 1) {
				widget.setCellText(eventData.id, 8, SComponent.trimNumber(Number(count) / (1 - Number(lossRate)),2));
			} else {
				widget.setCellText(eventData.id, 8, "0.00");
			}
		}
	}
};