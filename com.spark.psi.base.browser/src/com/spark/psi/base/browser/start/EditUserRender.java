package com.spark.psi.base.browser.start;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * <p>������༭�û���ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-25
 */

public class EditUserRender extends BaseFormPageRender{

	/**
	 * �Ƿ��Զ���Form����
	 * 
	 * @return
	 */
	protected boolean customizeFormLayout() {
		return true;
	}

	/**
	 * �Ƿ��Զ��尴ť����
	 * 
	 * @return
	 */
	protected boolean customizeButtonLayout() {
		return true;
	}
	
	/**
	 * �������������
	 */
	@Override
    protected void renderFormArea(Composite formArea){
		formArea.setLayout(new GridLayout(4));
		//
		GridData firstGridData = new GridData();
		firstGridData.widthHint = 70;
		GridData secondGridData = new GridData();
		secondGridData.widthHint = 50;
		GridData textGridData = new GridData(GridData.FILL_HORIZONTAL);
		textGridData.horizontalSpan = 3;
		//��һ�С��������ֻ�
		new SAsteriskLabel(formArea, "������").setLayoutData(firstGridData);
		Text nameText = new Text(formArea, JWT.APPEARANCE3);
		nameText.setID(EditUserProcessor.ID_Text_Name);
		nameText.setMaximumLength(10);
		nameText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		new SAsteriskLabel(formArea, "�ֻ���").setLayoutData(secondGridData);
		Text mobileText = new Text(formArea, JWT.APPEARANCE3);
		mobileText.setID(EditUserProcessor.ID_Text_Mobile);
		mobileText.setMaximumLength(11);
		mobileText.setRegExp(SAAS.Reg.REGEXP_mob);
		mobileText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//�ڶ��С����֤��
		Label identityLabel = new Label(formArea, JWT.RIGHT);
		identityLabel.setText("���֤�ţ�");
		identityLabel.setLayoutData(firstGridData);
		Text identityNumberText = new Text(formArea, JWT.APPEARANCE3);
		identityNumberText.setID(EditUserProcessor.ID_Text_IdentityNumber);
		identityNumberText.setMaximumLength(20);
		identityNumberText.setLayoutData(textGridData);
		//�����С����估��λ
		Label emailLabel = new Label(formArea, JWT.RIGHT);
		emailLabel.setText("���䣺");
		emailLabel.setLayoutData(firstGridData);
		Text emailText = new Text(formArea, JWT.APPEARANCE3);
		emailText.setID(EditUserProcessor.ID_Text_Email);
		emailText.setMaximumLength(50);
		emailText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		Label jobLabel = new Label(formArea, JWT.RIGHT);
		jobLabel.setText("��λ��");
		jobLabel.setLayoutData(secondGridData);
		Text jobText = new Text(formArea, JWT.APPEARANCE3);
		jobText.setID(EditUserProcessor.ID_Text_Job);
		jobText.setMaximumLength(30);
		jobText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//�����С�����
		new SAsteriskLabel(formArea, "���ţ�").setLayoutData(firstGridData);
		Text departmentText = new Text(formArea, JWT.APPEARANCE3 | JWT.BUTTON);
		departmentText.setID(EditUserProcessor.ID_Text_Department);
		departmentText.setLayoutData(textGridData);
		departmentText.setEditable(false);
		//�����С��û�Ȩ��
		new SAsteriskLabel(formArea, "�û�Ȩ�ޣ�").setLayoutData(firstGridData);
		Text rolesInfoText = new Text(formArea, JWT.APPEARANCE3 | JWT.BUTTON);
		rolesInfoText.setID(EditUserProcessor.ID_Text_RolesInfo);
		rolesInfoText.setLayoutData(textGridData);
		rolesInfoText.setEditable(false);
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
