package com.spark.psi.base.browser.contact;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.publish.ContactType;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;

/**
 * 
 * <p>��ϵ�˱༭������ͼ</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-5-4
 */
public class ContactEditRender extends BaseFormPageRender{

	/**
	 * �Զ���Form����
	 */
	protected boolean customizeFormLayout(){
		return true;
	}

	/**
	 * �Զ��尴ť����
	 */
	@Override
	protected boolean customizeButtonLayout(){
		return true;
	}

	/**
	 * �������������
	 */
	@Override
	protected void renderFormArea(Composite formArea){
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 15;
		formArea.setLayout(gridLayout);

		//������Ϣ
		Composite baseInfoContainer = new Composite(formArea);
		renderBaseInfo(baseInfoContainer);

		Label label = new Label(formArea);
		label.setText("������Ϣ");
		label.setFont(new Font("����", 9, JWT.FONT_STYLE_BOLD));
		GridData labelGridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_CENTER);
		label.setLayoutData(labelGridData);

		//������Ϣ
		Composite moreInfoContainer = new Composite(formArea);
		moreInfoContainer.setLayoutData(GridData.INS_FILL_BOTH);
		renderMoreInfo(moreInfoContainer);

	}

	/**
	 * ������Ϣ
	 */
	private void renderBaseInfo(Composite container){
		GridLayout gridLayout = new GridLayout(7);
		gridLayout.horizontalSpacing = 10;
		gridLayout.verticalSpacing = 10;
		container.setLayout(gridLayout);

		GridData firstGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);

		new SAsteriskLabel(container, "������");
		if(null != this.getArgument() && ((ContactBookInfo)this.getArgument()).getType().equals(ContactType.Partner)){//�ͻ���Ӧ��
			Text nameText = new Text(container, JWT.APPEARANCE3);
			nameText.setID(ContactEditProcessor.ID_Text_Name);
			nameText.setMaximumLength(40);
			GridData nameGridData = new GridData();
			nameGridData.widthHint = 120;
			nameText.setLayoutData(nameGridData);
			Button mainPersonButton = new Button(container, JWT.CHECK);
			mainPersonButton.setID(ContactEditProcessor.ID_Button_MainPerson);
			mainPersonButton.setText("����ϵ��");
			GridData isMainGridData = new GridData();
			isMainGridData.widthHint = 80;
			mainPersonButton.setLayoutData(isMainGridData);
		}
		else{ //������ǿͻ���ǹ�Ӧ�����鲻��ʾ����ϵ��
			Text nameText = new Text(container, JWT.APPEARANCE3);
			nameText.setID(ContactEditProcessor.ID_Text_Name);
			nameText.setMaximumLength(40);
			GridData nameGridData = new GridData(GridData.FILL_HORIZONTAL);
			nameGridData.horizontalSpan = 2;
			nameGridData.widthHint = 210;
			nameText.setLayoutData(nameGridData);
		}
		new SAsteriskLabel(container, "�Ա�");
		LWComboList sexComboList = new LWComboList(container, JWT.APPEARANCE3);
		sexComboList.setID(ContactEditProcessor.ID_ComboList_Sex);
		sexComboList.setHint("��ѡ��");
		Label label = new Label(container);
		label.setText("��ƣ�");
		label.setLayoutData(firstGridData);
		Text nickNameText = new Text(container, JWT.APPEARANCE3);
		nickNameText.setID(ContactEditProcessor.ID_Text_NickName);
		nickNameText.setMaximumLength(40);
		label = new Label(container);
		label.setText("�ֻ���");
		label.setLayoutData(firstGridData);
		Text mobileText = new Text(container, JWT.APPEARANCE3);
		mobileText.setID(ContactEditProcessor.ID_Text_Mobile);
		mobileText.setMaximumLength(20);
		mobileText.setRegExp(SAAS.Reg.REGEXP_mob);
		GridData mobileGridData = new GridData(GridData.FILL_HORIZONTAL);
		mobileGridData.horizontalSpan = 2;
		mobileText.setLayoutData(mobileGridData);
		label = new Label(container);
		label.setText("�̻���");
		label.setLayoutData(firstGridData);
		Text phoneText = new Text(container, JWT.APPEARANCE3);
		phoneText.setID(ContactEditProcessor.ID_Text_Phone);
		phoneText.setMaximumLength(20);
		phoneText.setRegExp(SAAS.Reg.REGEXP_PHONE);
		label = new Label(container);
		label.setText("���䣺");
		label.setLayoutData(firstGridData);
		Text emailText = new Text(container, JWT.APPEARANCE3);
		emailText.setID(ContactEditProcessor.ID_Text_Email);
		emailText.setMaximumLength(50);
		new SAsteriskLabel(container, "��λ��");
		GridData companyGridData = new GridData(GridData.FILL_BOTH);
		companyGridData.horizontalSpan = 2;
		companyGridData.widthHint = 210;
		if(null == this.getArgument()){
			Text companyText = new Text(container, JWT.APPEARANCE3);
			companyText.setMaximumLength(100);
			companyText.setID(ContactEditProcessor.ID_Text_Company);
			companyText.setLayoutData(companyGridData);
		}
		else{ //�鿴���鲻�����޸ĵ�λ
			Label companyText = new Label(container, JWT.MIDDLE);
			companyText.setID(ContactEditProcessor.ID_Text_Company);
			companyText.setLayoutData(companyGridData);
		}
		label = new Label(container);
		label.setText("���ţ�");
		label.setLayoutData(firstGridData);
		Text departmentText = new Text(container, JWT.APPEARANCE3);
		departmentText.setID(ContactEditProcessor.ID_Text_Department);
		departmentText.setMaximumLength(30);
		label = new Label(container);
		label.setText("ְλ��");
		label.setLayoutData(firstGridData);
		Text jobText = new Text(container, JWT.APPEARANCE3);
		jobText.setID(ContactEditProcessor.ID_Text_Job);
		jobText.setMaximumLength(30);
	}

	/**
	 * ������Ϣ
	 */
	private void renderMoreInfo(Composite container){

		GridLayout gridLayout = new GridLayout(6);
		gridLayout.horizontalSpacing = 10;
		gridLayout.verticalSpacing = 10;
		container.setLayout(gridLayout);

		GridData firstGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);

		Label label = new Label(container);
		label.setText("    QQ��");
		label.setLayoutData(firstGridData);
		Text qqText = new Text(container, JWT.APPEARANCE3);
		qqText.setID(ContactEditProcessor.ID_Text_QQ);
		qqText.setMaximumLength(15);
		qqText.setRegExp(SAAS.Reg.REGEXP_NUM);
		GridData qqGridData = new GridData();
		qqGridData.widthHint = 120;
		qqText.setLayoutData(qqGridData);
		label = new Label(container);
		label.setText("MSN��");
		label.setLayoutData(firstGridData);
		Text msnText = new Text(container, JWT.APPEARANCE3);
		msnText.setMaximumLength(30);
		msnText.setID(ContactEditProcessor.ID_Text_Msn);
		msnText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		label = new Label(container);
		label.setText("������");
		label.setLayoutData(firstGridData);
		Text aliimText = new Text(container, JWT.APPEARANCE3);
		aliimText.setID(ContactEditProcessor.ID_Text_Aliim);
		aliimText.setMaximumLength(50);
		label = new Label(container);
		label.setText("���գ�");
		label.setLayoutData(firstGridData);
		SDatePicker birthdayDate = new SDatePicker(container);
		birthdayDate.setID(ContactEditProcessor.ID_Date_Birthday);
		label = new Label(container);
		label.setText("���ã�");
		label.setLayoutData(firstGridData);
		Text favoriteText = new Text(container, JWT.APPEARANCE3);
		favoriteText.setID(ContactEditProcessor.ID_Text_Favorite);
		favoriteText.setMaximumLength(100);
		GridData favoriteGridData = new GridData(GridData.FILL_HORIZONTAL);
		favoriteGridData.horizontalSpan = 3;
		favoriteText.setLayoutData(favoriteGridData);
		label = new Label(container);
		label.setText("��ע��");
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END));
		Text commentsText = new Text(container, JWT.MULTI | JWT.WRAP);
		commentsText.setID(ContactEditProcessor.ID_Text_Comments);
		commentsText.setMaximumLength(200);
		GridData commentsGridData = new GridData(GridData.FILL_BOTH);
		commentsGridData.horizontalSpan = 5;
		commentsText.setLayoutData(commentsGridData);
	}

	/**
	 * ������ť
	 */
	@Override
	protected void renderButton(Composite buttonArea){
		buttonArea.setLayout(new GridLayout(3));

		GridData saveButtonGridData = new GridData();
		saveButtonGridData.widthHint = 80;
		saveButtonGridData.heightHint = 30;
		saveButtonGridData.verticalIndent = 5;

		//��߿հ׿��
		new Label(buttonArea).setLayoutData(GridData.INS_FILL_BOTH);
		//���水ť
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setText("����");
		saveButton.setID(ContactEditProcessor.ID_Button_Save);
		saveButton.setLayoutData(saveButtonGridData);
		//���沢����
		if(null == this.getArgument()){
			Button saveAddButton = new Button(buttonArea, JWT.APPEARANCE3);
			saveAddButton.setText("���沢����");
			saveAddButton.setID(ContactEditProcessor.ID_Button_SaveAdd);
			saveAddButton.setLayoutData(saveButtonGridData);
		}
	}

}
