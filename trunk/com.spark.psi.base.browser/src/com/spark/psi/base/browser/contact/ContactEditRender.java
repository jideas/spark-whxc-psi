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
 * <p>联系人编辑界面视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-5-4
 */
public class ContactEditRender extends BaseFormPageRender{

	/**
	 * 自定义Form布局
	 */
	protected boolean customizeFormLayout(){
		return true;
	}

	/**
	 * 自定义按钮布局
	 */
	@Override
	protected boolean customizeButtonLayout(){
		return true;
	}

	/**
	 * 表单区域添加容器
	 */
	@Override
	protected void renderFormArea(Composite formArea){
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 15;
		formArea.setLayout(gridLayout);

		//基本信息
		Composite baseInfoContainer = new Composite(formArea);
		renderBaseInfo(baseInfoContainer);

		Label label = new Label(formArea);
		label.setText("更多信息");
		label.setFont(new Font("宋体", 9, JWT.FONT_STYLE_BOLD));
		GridData labelGridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_CENTER);
		label.setLayoutData(labelGridData);

		//更多信息
		Composite moreInfoContainer = new Composite(formArea);
		moreInfoContainer.setLayoutData(GridData.INS_FILL_BOTH);
		renderMoreInfo(moreInfoContainer);

	}

	/**
	 * 基本信息
	 */
	private void renderBaseInfo(Composite container){
		GridLayout gridLayout = new GridLayout(7);
		gridLayout.horizontalSpacing = 10;
		gridLayout.verticalSpacing = 10;
		container.setLayout(gridLayout);

		GridData firstGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);

		new SAsteriskLabel(container, "姓名：");
		if(null != this.getArgument() && ((ContactBookInfo)this.getArgument()).getType().equals(ContactType.Partner)){//客户或供应商
			Text nameText = new Text(container, JWT.APPEARANCE3);
			nameText.setID(ContactEditProcessor.ID_Text_Name);
			nameText.setMaximumLength(40);
			GridData nameGridData = new GridData();
			nameGridData.widthHint = 120;
			nameText.setLayoutData(nameGridData);
			Button mainPersonButton = new Button(container, JWT.CHECK);
			mainPersonButton.setID(ContactEditProcessor.ID_Button_MainPerson);
			mainPersonButton.setText("主联系人");
			GridData isMainGridData = new GridData();
			isMainGridData.widthHint = 80;
			mainPersonButton.setLayoutData(isMainGridData);
		}
		else{ //新增或非客户或非供应商详情不显示主联系人
			Text nameText = new Text(container, JWT.APPEARANCE3);
			nameText.setID(ContactEditProcessor.ID_Text_Name);
			nameText.setMaximumLength(40);
			GridData nameGridData = new GridData(GridData.FILL_HORIZONTAL);
			nameGridData.horizontalSpan = 2;
			nameGridData.widthHint = 210;
			nameText.setLayoutData(nameGridData);
		}
		new SAsteriskLabel(container, "性别：");
		LWComboList sexComboList = new LWComboList(container, JWT.APPEARANCE3);
		sexComboList.setID(ContactEditProcessor.ID_ComboList_Sex);
		sexComboList.setHint("请选择");
		Label label = new Label(container);
		label.setText("尊称：");
		label.setLayoutData(firstGridData);
		Text nickNameText = new Text(container, JWT.APPEARANCE3);
		nickNameText.setID(ContactEditProcessor.ID_Text_NickName);
		nickNameText.setMaximumLength(40);
		label = new Label(container);
		label.setText("手机：");
		label.setLayoutData(firstGridData);
		Text mobileText = new Text(container, JWT.APPEARANCE3);
		mobileText.setID(ContactEditProcessor.ID_Text_Mobile);
		mobileText.setMaximumLength(20);
		mobileText.setRegExp(SAAS.Reg.REGEXP_mob);
		GridData mobileGridData = new GridData(GridData.FILL_HORIZONTAL);
		mobileGridData.horizontalSpan = 2;
		mobileText.setLayoutData(mobileGridData);
		label = new Label(container);
		label.setText("固话：");
		label.setLayoutData(firstGridData);
		Text phoneText = new Text(container, JWT.APPEARANCE3);
		phoneText.setID(ContactEditProcessor.ID_Text_Phone);
		phoneText.setMaximumLength(20);
		phoneText.setRegExp(SAAS.Reg.REGEXP_PHONE);
		label = new Label(container);
		label.setText("邮箱：");
		label.setLayoutData(firstGridData);
		Text emailText = new Text(container, JWT.APPEARANCE3);
		emailText.setID(ContactEditProcessor.ID_Text_Email);
		emailText.setMaximumLength(50);
		new SAsteriskLabel(container, "单位：");
		GridData companyGridData = new GridData(GridData.FILL_BOTH);
		companyGridData.horizontalSpan = 2;
		companyGridData.widthHint = 210;
		if(null == this.getArgument()){
			Text companyText = new Text(container, JWT.APPEARANCE3);
			companyText.setMaximumLength(100);
			companyText.setID(ContactEditProcessor.ID_Text_Company);
			companyText.setLayoutData(companyGridData);
		}
		else{ //查看详情不允许修改单位
			Label companyText = new Label(container, JWT.MIDDLE);
			companyText.setID(ContactEditProcessor.ID_Text_Company);
			companyText.setLayoutData(companyGridData);
		}
		label = new Label(container);
		label.setText("部门：");
		label.setLayoutData(firstGridData);
		Text departmentText = new Text(container, JWT.APPEARANCE3);
		departmentText.setID(ContactEditProcessor.ID_Text_Department);
		departmentText.setMaximumLength(30);
		label = new Label(container);
		label.setText("职位：");
		label.setLayoutData(firstGridData);
		Text jobText = new Text(container, JWT.APPEARANCE3);
		jobText.setID(ContactEditProcessor.ID_Text_Job);
		jobText.setMaximumLength(30);
	}

	/**
	 * 更多信息
	 */
	private void renderMoreInfo(Composite container){

		GridLayout gridLayout = new GridLayout(6);
		gridLayout.horizontalSpacing = 10;
		gridLayout.verticalSpacing = 10;
		container.setLayout(gridLayout);

		GridData firstGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);

		Label label = new Label(container);
		label.setText("    QQ：");
		label.setLayoutData(firstGridData);
		Text qqText = new Text(container, JWT.APPEARANCE3);
		qqText.setID(ContactEditProcessor.ID_Text_QQ);
		qqText.setMaximumLength(15);
		qqText.setRegExp(SAAS.Reg.REGEXP_NUM);
		GridData qqGridData = new GridData();
		qqGridData.widthHint = 120;
		qqText.setLayoutData(qqGridData);
		label = new Label(container);
		label.setText("MSN：");
		label.setLayoutData(firstGridData);
		Text msnText = new Text(container, JWT.APPEARANCE3);
		msnText.setMaximumLength(30);
		msnText.setID(ContactEditProcessor.ID_Text_Msn);
		msnText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		label = new Label(container);
		label.setText("旺旺：");
		label.setLayoutData(firstGridData);
		Text aliimText = new Text(container, JWT.APPEARANCE3);
		aliimText.setID(ContactEditProcessor.ID_Text_Aliim);
		aliimText.setMaximumLength(50);
		label = new Label(container);
		label.setText("生日：");
		label.setLayoutData(firstGridData);
		SDatePicker birthdayDate = new SDatePicker(container);
		birthdayDate.setID(ContactEditProcessor.ID_Date_Birthday);
		label = new Label(container);
		label.setText("爱好：");
		label.setLayoutData(firstGridData);
		Text favoriteText = new Text(container, JWT.APPEARANCE3);
		favoriteText.setID(ContactEditProcessor.ID_Text_Favorite);
		favoriteText.setMaximumLength(100);
		GridData favoriteGridData = new GridData(GridData.FILL_HORIZONTAL);
		favoriteGridData.horizontalSpan = 3;
		favoriteText.setLayoutData(favoriteGridData);
		label = new Label(container);
		label.setText("备注：");
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END));
		Text commentsText = new Text(container, JWT.MULTI | JWT.WRAP);
		commentsText.setID(ContactEditProcessor.ID_Text_Comments);
		commentsText.setMaximumLength(200);
		GridData commentsGridData = new GridData(GridData.FILL_BOTH);
		commentsGridData.horizontalSpan = 5;
		commentsText.setLayoutData(commentsGridData);
	}

	/**
	 * 操作按钮
	 */
	@Override
	protected void renderButton(Composite buttonArea){
		buttonArea.setLayout(new GridLayout(3));

		GridData saveButtonGridData = new GridData();
		saveButtonGridData.widthHint = 80;
		saveButtonGridData.heightHint = 30;
		saveButtonGridData.verticalIndent = 5;

		//左边空白宽度
		new Label(buttonArea).setLayoutData(GridData.INS_FILL_BOTH);
		//保存按钮
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setText("保存");
		saveButton.setID(ContactEditProcessor.ID_Button_Save);
		saveButton.setLayoutData(saveButtonGridData);
		//保存并新增
		if(null == this.getArgument()){
			Button saveAddButton = new Button(buttonArea, JWT.APPEARANCE3);
			saveAddButton.setText("保存并新增");
			saveAddButton.setID(ContactEditProcessor.ID_Button_SaveAdd);
			saveAddButton.setLayoutData(saveButtonGridData);
		}
	}

}
