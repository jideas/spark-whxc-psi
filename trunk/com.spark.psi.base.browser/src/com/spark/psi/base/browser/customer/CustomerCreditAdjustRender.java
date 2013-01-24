package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.components.controls.text.SNumberText;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.base.browser.partner.PartnerInfoProcessor;

/**
 * �ͻ����ö�ȵ���������ͼ
 */
public class CustomerCreditAdjustRender extends BaseFormPageRender {

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout gl = new GridLayout(3);
		gl.marginLeft = gl.marginTop = 5;
		gl.verticalSpacing = 6;
		formArea.setLayout(gl);

		Label label = new Label(formArea);
		label.setText("���ö�ȣ�");
		SNumberText numberText = new SNumberText(formArea, 2);
		numberText.setID(PartnerInfoProcessor.ID_Text_CreditAmount);
//		numberText.setLayoutData(gd);
		numberText.setMaximumLength(19);
		numberText.setValue(0);

//		Text text = new Text(formArea, JWT.APPEARANCE3);
//		text.setID(CustomerCreditAdjustProcessor.ID_Text_CreditAmount);
//		text.setRegExp(Reg.NUM_EIGHT_TWO);
		
		label = new Label(formArea);
		label.setText("Ԫ");

		label = new Label(formArea);
		label.setText("����������");

		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(CustomerCreditAdjustProcessor.ID_Text_CreditDa);
		text.setRegExp(Reg.REGEXP_NUM);
		text.setMaximumLength(5);
		label = new Label(formArea);
		label.setText("��");

		new SSeparator(formArea, JWT.HORIZONTAL, 3);

		label = new Label(formArea);
		label.setText("������ǰ");

		text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(CustomerCreditAdjustProcessor.ID_Text_RemindDay);
		text.setRegExp(Reg.REGEXP_NUM);
		text.setMaximumLength(5);
		label = new Label(formArea);
		label.setText("�� ����");

	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setID(CustomerCreditAdjustProcessor.ID_Button_Save);
		saveButton.setText(" ȷ������ ");
		Button cancelButton = new Button(buttonArea, JWT.APPEARANCE3);
		cancelButton.setID(CustomerCreditAdjustProcessor.ID_Button_Cancel);
		cancelButton.setText(" ȡ������ ");
	}

}
