package com.spark.psi.order.browser.online;

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

public class AdvanceSearchPageRender extends BaseFormPageRender {
//	public static final String ID_Text_BillsNo = "Text_BillsNo";
//	public static final String ID_Text_RealName = "Text_RealName";
//	public static final String ID_Text_StationName = "Text_StationName";
//	public static final String ID_Date_CreateDateBegin = "Date_CreateDateBegin";
//	public static final String ID_Date_CreateDateEnd = "Date_CreateDateEnd";
//	public static final String ID_Date_DeliveryeDateBegin = "Date_DeliveryeDateBegin";
//	public static final String ID_Date_DeliveryeDateEnd = "Date_DeliveryeDateEnd";
	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 确 定 ");
		button.setID(AdvanceSearchPageProcessor.ID_Button_Confirm);
		
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 取 消 ");
		button.setID(AdvanceSearchPageProcessor.ID_Button_Cancel);
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
		label.setText("订单编号：");
		label.setLayoutData(gdLabel);
		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(AdvanceSearchPageProcessor.ID_Text_BillsNo);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("客户名称：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(AdvanceSearchPageProcessor.ID_Text_RealName);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("站点：");
		label.setLayoutData(gdLabel);
		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(AdvanceSearchPageProcessor.ID_Text_StationName);
		text.setLayoutData(gdOneRow);
		
		label = new Label(formArea);
		label.setText("下单日期：");
		label.setLayoutData(gdLabel);
		SDatePicker date = new SDatePicker(formArea);
		date.setID(AdvanceSearchPageProcessor.ID_Date_CreateDateBegin);
		new Label(formArea).setText("至");
		date = new SDatePicker(formArea);
		date.setID(AdvanceSearchPageProcessor.ID_Date_CreateDateEnd);
		
		label = new Label(formArea);
		label.setText("交货日期：");
		label.setLayoutData(gdLabel);
		date = new SDatePicker(formArea);
		date.setID(AdvanceSearchPageProcessor.ID_Date_DeliveryeDateBegin);
		new Label(formArea).setText("至");
		date = new SDatePicker(formArea);
		date.setID(AdvanceSearchPageProcessor.ID_Date_DeliveryeDateEnd);
		
		label = new Label(formArea);
		label.setText("交货时间：");
		label.setLayoutData(gdLabel);
		LWComboList timeList = new LWComboList(formArea, JWT.APPEARANCE3);
		timeList.setID(AdvanceSearchPageProcessor.ID_List_DeliveryeTime);
	}

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}
	
}
