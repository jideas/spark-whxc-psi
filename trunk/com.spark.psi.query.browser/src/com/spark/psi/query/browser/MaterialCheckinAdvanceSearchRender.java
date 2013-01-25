package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageRender;

public class MaterialCheckinAdvanceSearchRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 确 定 ");
		button.setID(MaterialCheckinAdvanceSearchProcessor.ID_Button_Confirm);
		
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 取 消 ");
		button.setID(MaterialCheckinAdvanceSearchProcessor.ID_Button_Cancel);
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout glForm = new GridLayout();
		glForm.numColumns = 4;
		formArea.setLayout(glForm);
		
		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		
		GridData gdOneRow = new GridData(GridData.FILL_HORIZONTAL);
		gdOneRow.horizontalSpan = 3;
		
		Label label = new Label(formArea);
		label.setText("供应商编码：");
		label.setLayoutData(gdLabel);
		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(MaterialCheckinAdvanceSearchProcessor.ID_Text_PartnerNo);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("供应商名称：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(MaterialCheckinAdvanceSearchProcessor.ID_Text_PartnerName);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("采购订单编号：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(MaterialCheckinAdvanceSearchProcessor.ID_Text_PurchaseSheetNo);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("入库单号：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(MaterialCheckinAdvanceSearchProcessor.ID_Text_SheetNo);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("入库时间：");
		label.setLayoutData(gdLabel);
		SDatePicker date = new SDatePicker(formArea);
		date.setID(MaterialCheckinAdvanceSearchProcessor.ID_Date_CheckinDateBegin);
		new Label(formArea).setText("至");
		date = new SDatePicker(formArea);
		date.setID(MaterialCheckinAdvanceSearchProcessor.ID_Date_CheckinDateEnd);
		
		label = new Label(formArea);
		label.setText("入库类型：");
		label.setLayoutData(gdLabel);
		LWComboList list = new LWComboList(formArea, JWT.APPEARANCE3);
		list.setID(MaterialCheckinAdvanceSearchProcessor.ID_List_CheckingInType);
		list.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("材料编号：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(MaterialCheckinAdvanceSearchProcessor.ID_Text_MaterialNo);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("材料名称：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(MaterialCheckinAdvanceSearchProcessor.ID_Text_MaterialName);
		text.setLayoutData(gdOneRow);
	}
	@Override
	protected boolean customizeFormLayout() {
		return true;
	}
}
