package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.SSeparator;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.base.browser.internal.BaseImages;

/**
 * <p>������༭�ֿ���ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-28
 */

public class EditStoreRender extends BaseFormPageRender{
	/**
	 * �Ƿ��Զ���Form����
	 * 
	 * @return
	 */
	protected boolean customizeFormLayout(){
		return true;
	}

	/**
	 * �Ƿ��Զ��尴ť����
	 * 
	 * @return
	 */
	protected boolean customizeButtonLayout(){
		return true;
	}

	/**
	 * �������������
	 */
	@Override
	protected void renderFormArea(Composite formArea){
		GridLayout gridLayout = new GridLayout(3);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 5;
		formArea.setLayout(gridLayout);
		//�ϲ�����
		addUpArea(formArea);
		//�л���
		new SSeparator(formArea, JWT.HORIZONTAL, 3);
		//�²����򡡿��Ա������Ա
		addDownArea(formArea);
	}
	
	/**
	 * �л����ϲ�����
	 */
	private void addUpArea(Composite formArea){
		//
		GridData secondLabelGridData = new GridData();
		secondLabelGridData.widthHint = 50;
		GridData secondTextGridData = new GridData();
		secondTextGridData.widthHint = 200;
		//��һ�С��ֿ����Ƽ��̻�
		new SAsteriskLabel(formArea, "�ֿ����ƣ�").setLayoutData(getFirstGridData());
		//
		Composite firstContainer = new Composite(formArea);
		firstContainer.setLayout(new GridLayout(3));
		firstContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text nameText = new Text(firstContainer, JWT.APPEARANCE3);
		nameText.setID(EditStoreProcessor.ID_Text_Name);
		nameText.setMaximumLength(25);
		nameText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Label phoneLabel = new Label(firstContainer, JWT.RIGHT);
		phoneLabel.setText("�̻���");
		phoneLabel.setLayoutData(secondLabelGridData);
		//
		Text phoneText = new Text(firstContainer, JWT.APPEARANCE3);
		phoneText.setID(EditStoreProcessor.ID_Text_Phone);
		phoneText.setMaximumLength(20);
		phoneText.setLayoutData(secondTextGridData);
		phoneText.setRegExp(SAAS.Reg.REGEXP_PHONE);
		//
		new Label(formArea);
		//�ڶ��С��ջ��˼��ֻ�
		new SAsteriskLabel(formArea, "�ջ��ˣ�").setLayoutData(getFirstGridData());
		//
		Composite secondContainer = new Composite(formArea);
		secondContainer.setLayout(new GridLayout(3));
		secondContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text consigneeText = new Text(secondContainer, JWT.APPEARANCE3);
		consigneeText.setID(EditStoreProcessor.ID_Text_Consignee);
		consigneeText.setMaximumLength(10);
		consigneeText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Label mobileLabel = new Label(secondContainer, JWT.RIGHT);
		mobileLabel.setText("�ֻ���");
		mobileLabel.setLayoutData(secondLabelGridData);
		//
		Text mobileText = new Text(secondContainer, JWT.APPEARANCE3);
		mobileText.setID(EditStoreProcessor.ID_Text_Mobile);
		mobileText.setMaximumLength(11);
		mobileText.setLayoutData(secondTextGridData);
		mobileText.setRegExp(SAAS.Reg.REGEXP_mob);
		//
		new Label(formArea);
		//�����С���ַ
		new SAsteriskLabel(formArea, "��ַ��").setLayoutData(getFirstGridData());
		//
		Composite thirdContainer = new Composite(formArea);
		thirdContainer.setLayout(new GridLayout(3));
		thirdContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		LWComboList provinceList = new LWComboList(thirdContainer, JWT.APPEARANCE3);
		provinceList.setID(EditStoreProcessor.ID_List_Province);
		provinceList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		LWComboList cityList = new LWComboList(thirdContainer, JWT.APPEARANCE3);
		cityList.setID(EditStoreProcessor.ID_List_City);
		cityList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		LWComboList districtList = new LWComboList(thirdContainer, JWT.APPEARANCE3);
		districtList.setID(EditStoreProcessor.ID_List_District);
		districtList.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		new Label(formArea);
		//�����С���ϸ��ַ���ʱ�
		new Label(formArea).setLayoutData(getFirstGridData());
		//
		Composite fourContainer = new Composite(formArea);
		fourContainer.setLayout(new GridLayout(2));
		fourContainer.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text addressText = new Text(fourContainer, JWT.APPEARANCE3);
		addressText.setID(EditStoreProcessor.ID_Text_Address);
		addressText.setHint("��ϸ��ַ");
		addressText.setMaximumLength(100);
		addressText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Text postCodeText = new Text(fourContainer, JWT.APPEARANCE3);
		postCodeText.setID(EditStoreProcessor.ID_Text_Postcode);
		postCodeText.setHint("�ʱ�");
		postCodeText.setMaximumLength(6);
		postCodeText.setRegExp(SAAS.Reg.REGEXP_POSTCODE);
		GridData postCodeGridData = new GridData();
		postCodeGridData.widthHint = 120;
		postCodeText.setLayoutData(postCodeGridData);
		//
		new Label(formArea);
	}

	/**
	 * �л����²�����
	 */
	private void addDownArea(Composite formArea){
		//���Ա
		new SAsteriskLabel(formArea, "���Ա��").setLayoutData(getFirstGridData());
		//
		Text keeperText = new Text(formArea, JWT.APPEARANCE3);
		keeperText.setID(EditStoreProcessor.ID_Text_Keeper);
		keeperText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Label labelAddKeepers = new Label(formArea);
		labelAddKeepers.setCursor(Cursor.HAND);
		labelAddKeepers.setID(EditStoreProcessor.ID_Label_AddKeeper);
		labelAddKeepers.setImage(BaseImages.getImage("images/store/saas_mark_add_manager.png"));
		//������Ա
		Label saleLabel = new Label(formArea, JWT.RIGHT);
		saleLabel.setText("������Ա��");
		saleLabel.setLayoutData(getFirstGridData());
		//
		Text saleText = new Text(formArea, JWT.APPEARANCE3);
		saleText.setID(EditStoreProcessor.ID_Text_Sales);
		saleText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//
		Label labelAddSales = new Label(formArea);
		labelAddSales.setCursor(Cursor.HAND);
		labelAddSales.setID(EditStoreProcessor.ID_Label_AddSales);
		labelAddSales.setImage(BaseImages.getImage("images/store/saas_mark_add_sale.png"));
	}

	/**
	 * ��õ�һ��label��������
	 */
	private GridData getFirstGridData(){
		GridData gridData = new GridData();
		gridData.widthHint = 75;
		return gridData;
	}

	/**
	 * ������ť
	 */
	@Override
	protected void renderButton(Composite buttonArea){
		buttonArea.setLayout(new GridLayout());
		//���水ť
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setID(EditUserProcessor.ID_Button_Save);
		GridData saveButtonGridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		saveButtonGridData.widthHint = 80;
		saveButtonGridData.heightHint = 30;
		saveButton.setText("����");
		saveButton.setLayoutData(saveButtonGridData);
	}
}
