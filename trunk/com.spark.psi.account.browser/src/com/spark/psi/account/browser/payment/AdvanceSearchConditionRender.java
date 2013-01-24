package com.spark.psi.account.browser.payment;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * �߼������Ի���ҳ����ͼ
 */
public class AdvanceSearchConditionRender extends BaseFormPageRender {

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout glForm = new GridLayout();
		glForm.numColumns = 4;
		glForm.verticalSpacing = 8;
		formArea.setLayout(glForm);
		
		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_CENTER);
		
		GridData gdOneRow = new GridData(GridData.FILL_HORIZONTAL);
		gdOneRow.horizontalSpan = 3;
		
		GridData gdHalf = new GridData(GridData.FILL_HORIZONTAL);
//		gdHalf.widthHint = 120;
		
		Label label = new Label(formArea);
		label.setText("�������");
		label.setLayoutData(gdLabel);
		Text targetText = new Text(formArea, JWT.APPEARANCE3);
		targetText.setLayoutData(gdOneRow);
		targetText.setID(AdvanceSearchConditionProcessor.ID_Text_PayTarget);
		
		label = new Label(formArea);
		label.setLayoutData(gdLabel);
		label.setText("�������ڣ�");
		SDatePicker date1 = new SDatePicker(formArea);
		date1.setID(AdvanceSearchConditionProcessor.ID_Date_PayDateBegin);
		date1.setLayoutData(gdHalf);
		new Label(formArea).setText(" �� ");
		SDatePicker date2 = new SDatePicker(formArea);
		date2.setID(AdvanceSearchConditionProcessor.ID_Date_PayDateEnd);
		date2.setLayoutData(gdHalf);
		
		label = new Label(formArea);
		label.setText("�� �� �ˣ�");
		label.setLayoutData(gdLabel);
		Text payerText = new Text(formArea, JWT.APPEARANCE3);
		payerText.setLayoutData(gdOneRow);
		payerText.setID(AdvanceSearchConditionProcessor.ID_Text_Payer);
		
//		SSeparator separator = 
		new SSeparator(formArea, JWT.HORIZONTAL, 4);
//		GridData gdSeparator = new GridData();
//		gdSeparator.heightHint = 20;
//		separator.setLayoutData(gdSeparator);
		
		label = new Label(formArea);
		label.setText("�������ͣ�");
		label.setLayoutData(gdLabel);
		Composite typeCmp = new Composite(formArea);
		typeCmp.setLayoutData(gdOneRow);
		GridLayout glType = new GridLayout();
		glType.numColumns = 4;
		glType.horizontalSpacing = 10;
		typeCmp.setLayout(glType);
		Button typeCheck = new Button(typeCmp, JWT.CHECK);
		typeCheck.setText("�ɹ�����");
		typeCheck.setID(AdvanceSearchConditionProcessor.ID_Check_TypePurchase);
		Button typeSaleReturn = new Button(typeCmp, JWT.CHECK);
		typeSaleReturn.setText("�����˿�");
		typeSaleReturn.setID(AdvanceSearchConditionProcessor.ID_Check_TypeSaleReturn);
		Button typeRetailReturn = new Button(typeCmp, JWT.CHECK);
		typeRetailReturn.setText("�����˿�");
		typeRetailReturn.setID(AdvanceSearchConditionProcessor.ID_Check_TypeRetailReturn);
		Button typeIrregular = new Button(typeCmp, JWT.CHECK);
		typeIrregular.setText("��ɸ���");
		typeIrregular.setID(AdvanceSearchConditionProcessor.ID_Check_TypeIrregular);
		
		label = new Label(formArea);
		label.setText("�����");
		label.setLayoutData(gdLabel);
		Text amountText1 = new Text(formArea, JWT.APPEARANCE3);
		amountText1.setID(AdvanceSearchConditionProcessor.ID_Text_PayAmountBegin);
		amountText1.setLayoutData(gdHalf);
		new Label(formArea).setText(" �� ");
		Text amountText2 = new Text(formArea, JWT.APPEARANCE3);
		amountText2.setID(AdvanceSearchConditionProcessor.ID_Text_PayAmountEnd);
		amountText2.setLayoutData(gdHalf);
		
		label = new Label(formArea);
		label.setText("���ʽ��");
		label.setLayoutData(gdLabel);
		Composite wayCmp = new Composite(formArea);
		wayCmp.setLayoutData(gdOneRow);
		GridLayout glWay = new GridLayout();
		glWay.numColumns = 4;
		glWay.horizontalSpacing = 10;
		wayCmp.setLayout(glWay);
		Button wayCheck = new Button(wayCmp, JWT.CHECK);
		wayCheck.setText("�ֽ�");
		wayCheck.setID(AdvanceSearchConditionProcessor.ID_Check_WayCash);
		Button waySaleReturn = new Button(wayCmp, JWT.CHECK);
		waySaleReturn.setText("ˢ��");
		waySaleReturn.setID(AdvanceSearchConditionProcessor.ID_Check_WayCard);
		Button wayRetailReturn = new Button(wayCmp, JWT.CHECK);
		wayRetailReturn.setText("֧Ʊ");
		wayRetailReturn.setID(AdvanceSearchConditionProcessor.ID_Check_WayCheck);
		Button wayIrregular = new Button(wayCmp, JWT.CHECK);
		wayIrregular.setText("����ת��");
		wayIrregular.setID(AdvanceSearchConditionProcessor.ID_Check_WayAccount);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button checkBtn = new Button(buttonArea, JWT.APPEARANCE3);
		checkBtn.setText(" ��ʼ���� ");
		checkBtn.setID(AdvanceSearchConditionProcessor.ID_Button_Check);
		Button cancelBtn = new Button(buttonArea, JWT.APPEARANCE3);
		cancelBtn.setText(" ȡ������ ");
		cancelBtn.setID(AdvanceSearchConditionProcessor.ID_Button_Cancel);
	}

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}
	
	

}
