package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageRender;

public class ProduceOrderAdvaceSearchRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 确 定 ");
		button.setID(ProduceOrderAdvaceSearchProcessor.ID_Button_Confirm);
		
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 取 消 ");
		button.setID(ProduceOrderAdvaceSearchProcessor.ID_Button_Cancel);
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
		label.setText("生产订单编码：");
		label.setLayoutData(gdLabel);
		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(ProduceOrderAdvaceSearchProcessor.ID_Text_BillsNo);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("商品编码：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(ProduceOrderAdvaceSearchProcessor.ID_Text_GoodsCode);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("商品名称：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(ProduceOrderAdvaceSearchProcessor.ID_Text_GoodsName);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("下单日期：");
		label.setLayoutData(gdLabel);
		SDatePicker date = new SDatePicker(formArea);
		date.setID(ProduceOrderAdvaceSearchProcessor.ID_Date_CreateDateBegin);
		new Label(formArea).setText("至");
		date = new SDatePicker(formArea);
		date.setID(ProduceOrderAdvaceSearchProcessor.ID_Date_CreateDateEnd);
		
		label = new Label(formArea);
		label.setText("交货日期：");
		label.setLayoutData(gdLabel);
		date = new SDatePicker(formArea);
		date.setID(ProduceOrderAdvaceSearchProcessor.ID_Date_DeliveryDateBegin);
		new Label(formArea).setText("至");
		date = new SDatePicker(formArea);
		date.setID(ProduceOrderAdvaceSearchProcessor.ID_Date_DeliveryDateEnd);
	}
	@Override
	protected boolean customizeFormLayout() {
		return true;
	}

}
