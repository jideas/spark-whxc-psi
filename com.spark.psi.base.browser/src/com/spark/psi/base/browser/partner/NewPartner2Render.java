package com.spark.psi.base.browser.partner;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * ���������ͻ���ͼ
 * 
 */
public abstract class NewPartner2Render extends BaseFormPageRender {

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout gl = new GridLayout();
		formArea.setLayout(gl);

		GridData gdLabel = new GridData();
		gdLabel.widthHint = 60;

		GridData gd80 = new GridData();
		gd80.widthHint = 80;

		GridData gd100 = new GridData();
		gd100.widthHint = 100;

		GridData gd160 = new GridData();
		gd160.widthHint = 166;

		GridData gdFill = new GridData(GridData.FILL_HORIZONTAL);

		//
		Composite rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(4));
		new SAsteriskLabel(rowArea, getPartnerType() + "��")
				.setLayoutData(gdLabel);
		Text text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_ShortName);
		text.setLayoutData(gd100);
		text.setHint("�������ڼ��");
		text.setMaximumLength(6);
		new SAsteriskLabel(rowArea, "ȫ�ƣ�");
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_FullName);
		text.setLayoutData(gdFill);
		text.setMaximumLength(50);
		//
		rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(4));
		Label label = new Label(rowArea, JWT.RIGHT);
		label.setText("��ַ��");
		label.setLayoutData(gdLabel);
		LWComboList comboList = new LWComboList(rowArea, JWT.APPEARANCE3);
		comboList.setID(NewPartner2Processor.ID_List_Province);
		comboList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		comboList.setHint("ʡ��ֱϽ�У�");
		comboList = new LWComboList(rowArea, JWT.APPEARANCE3);
		comboList.setID(NewPartner2Processor.ID_List_City);
		comboList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		comboList.setHint("�У�����");
		comboList = new LWComboList(rowArea, JWT.APPEARANCE3);
		comboList.setID(NewPartner2Processor.ID_List_District);
		comboList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		comboList.setHint("�����أ�");
		//
		rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(3));
		new Label(rowArea).setLayoutData(gdLabel);

		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_Address);
		text.setLayoutData(gdFill);
		text.setHint("��ϸ��ַ");
		text.setMaximumLength(50);
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_PostCode);
		text.setLayoutData(gd100);
		text.setHint("�ʱ�");
		text.setMaximumLength(6);
		text.setRegExp(Reg.REGEXP_NUM);

		//
		rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(2));
		label = new Label(rowArea, JWT.RIGHT);
		label.setText("���棺");
		label.setLayoutData(gdLabel);
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_Fax);
		text.setLayoutData(gd160);
		text.setMaximumLength(20);
		text.setRegExp(Reg.REGEXP_PHONE);
		//
		rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(3));
		label = new Label(rowArea, JWT.RIGHT);
		label.setText("��ϵ�ˣ�");
		label.setLayoutData(gdLabel);
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_ContactName);
		text.setLayoutData(gd80);
		text.setMaximumLength(20);
		comboList = new LWComboList(rowArea, JWT.APPEARANCE3);
		comboList.setID(NewPartner2Processor.ID_List_ContactSex);
		comboList.setLayoutData(gd80);
		comboList.setHint("�Ա�");

		//
		rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(4));
		label = new Label(rowArea, JWT.RIGHT);
		label.setText("�ֻ���");
		label.setLayoutData(gdLabel);
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_ContactMobile);
		text.setLayoutData(gd160);
		text.setMaximumLength(11);
		text.setRegExp(Reg.REGEXP_mob);
		label = new Label(rowArea, JWT.RIGHT);
		label.setText(" �̻���");
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_ContactLandLine);
		text.setLayoutData(gdFill);
		text.setMaximumLength(20);
		text.setRegExp(Reg.REGEXP_PHONE);
		//
		rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(2));
		label = new Label(rowArea, JWT.RIGHT);
		label.setText("���䣺");
		label.setLayoutData(gdLabel);
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_ContactMail);
		text.setLayoutData(gdFill);
		text.setMaximumLength(25);
		//
		rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(4));
		label = new Label(rowArea, JWT.RIGHT);
		label.setText("�ջ���ַ��");
		label.setLayoutData(gdLabel);
		comboList = new LWComboList(rowArea, JWT.APPEARANCE3);
		comboList.setID(NewPartner2Processor.ID_List_DeliveryProvince);
		comboList.setHint("ʡ��ֱϽ�У�");
		comboList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		comboList = new LWComboList(rowArea, JWT.APPEARANCE3);
		comboList.setID(NewPartner2Processor.ID_List_DeliveryCity);
		comboList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		comboList.setHint("�У�����");
		comboList = new LWComboList(rowArea, JWT.APPEARANCE3);
		comboList.setID(NewPartner2Processor.ID_List_DeliveryDistrict);
		comboList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		comboList.setHint("�����أ�");

		//
		rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(3));
		new Label(rowArea).setLayoutData(gdLabel);
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_DeliveryAddress);
		text.setLayoutData(gdFill);
		text.setHint("��ϸ��ַ");
		text.setMaximumLength(50);
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_DeliveryPostCode);
		text.setLayoutData(gd100);
		text.setHint("�ʱ�");
		text.setMaximumLength(6);
		text.setRegExp(Reg.REGEXP_NUM);

		//
		rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(4));
		label = new Label(rowArea, JWT.RIGHT);
		label.setText("�ջ��ˣ�");
		label.setLayoutData(gdLabel);
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_Consignee);
		text.setLayoutData(gd160);
		text.setMaximumLength(20);
		label = new Label(rowArea, JWT.RIGHT);
		label.setText(" �ֻ���");
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_ConsigneeMobileNo);
		text.setLayoutData(gdFill);
		text.setMaximumLength(11);
		text.setRegExp(Reg.REGEXP_mob);

		//
		rowArea = new Composite(formArea);
		rowArea.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		rowArea.setLayout(new GridLayout(2));
		label = new Label(rowArea, JWT.RIGHT);
		label.setText("�̻���");
		label.setLayoutData(gdLabel);
		text = new Text(rowArea, JWT.APPEARANCE3);
		text.setID(NewPartner2Processor.ID_Text_ConsigneeLandLineNumber);
		text.setLayoutData(gd160);
		text.setMaximumLength(20);
		text.setRegExp(Reg.REGEXP_PHONE);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setID(NewPartner2Processor.ID_Button_Save);
		saveButton.setText(" ȷ������ ");

		Button cancelButton = new Button(buttonArea, JWT.APPEARANCE3);
		cancelButton.setID(NewPartner2Processor.ID_Button_Cancel);
		cancelButton.setText(" �������� ");

	}

	protected abstract String getPartnerType();

}
