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

public class OnlineSalesAdvaceSearchRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" ȷ �� ");
		button.setID(OnlineSalesAdvaceSearchProcessor.ID_Button_Confirm);
		
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" ȡ �� ");
		button.setID(OnlineSalesAdvaceSearchProcessor.ID_Button_Cancel);

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
		label.setText("�ͻ����ƣ�");
		label.setLayoutData(gdLabel);
		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(OnlineSalesAdvaceSearchProcessor.ID_Text_CustomerName);
		text.setLayoutData(gdOneRow);
		
//		label = new Label(formArea);
//		label.setText("���۶�����ţ�");
//		label.setLayoutData(gdLabel);
//		text = new Text(formArea, JWT.APPEARANCE3);
//		text.setID(OnlineSalesAdvaceSearchProcessor.ID_Text_SheetNo);
//		text.setLayoutData(gdOneRow);
//		
		label = new Label(formArea);
		label.setText("��Ʒ��ţ�");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(OnlineSalesAdvaceSearchProcessor.ID_Text_GoodsCode);
		text.setLayoutData(gdOneRow);
//		
		label = new Label(formArea);
		label.setText("��Ʒ���룺");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(OnlineSalesAdvaceSearchProcessor.ID_Text_GoodsNo);
		text.setLayoutData(gdOneRow);
//		
		label = new Label(formArea);
		label.setText("��Ʒ���ƣ�");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(OnlineSalesAdvaceSearchProcessor.ID_Text_GoodsName);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("�������ڣ�");
		label.setLayoutData(gdLabel);
		SDatePicker date = new SDatePicker(formArea);
		date.setID(OnlineSalesAdvaceSearchProcessor.ID_Date_DeliverDateBegin);
		new Label(formArea).setText("��");
		date = new SDatePicker(formArea);
		date.setID(OnlineSalesAdvaceSearchProcessor.ID_Date_DeliverDateEnd);
		
//		label = new Label(formArea);
//		label.setText("�������ڣ�");
//		label.setLayoutData(gdLabel);
//		date = new SDatePicker(formArea);
//		date.setID(OnlineSalesAdvaceSearchProcessor.ID_Date_DeliveryDateBegin);
//		new Label(formArea).setText("��");
//		date = new SDatePicker(formArea);
//		date.setID(OnlineSalesAdvaceSearchProcessor.ID_Date_DeliveryDateEnd);
	}
	@Override
	protected boolean customizeFormLayout() {
		return true;
	}

}
