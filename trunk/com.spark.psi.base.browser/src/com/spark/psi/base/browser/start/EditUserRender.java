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
 * <p>新增或编辑用户视图</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-6-25
 */

public class EditUserRender extends BaseFormPageRender{

	/**
	 * 是否自定义Form布局
	 * 
	 * @return
	 */
	protected boolean customizeFormLayout() {
		return true;
	}

	/**
	 * 是否自定义按钮布局
	 * 
	 * @return
	 */
	protected boolean customizeButtonLayout() {
		return true;
	}
	
	/**
	 * 表单区域添加容器
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
		//第一行　姓名及手机
		new SAsteriskLabel(formArea, "姓名：").setLayoutData(firstGridData);
		Text nameText = new Text(formArea, JWT.APPEARANCE3);
		nameText.setID(EditUserProcessor.ID_Text_Name);
		nameText.setMaximumLength(10);
		nameText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		new SAsteriskLabel(formArea, "手机：").setLayoutData(secondGridData);
		Text mobileText = new Text(formArea, JWT.APPEARANCE3);
		mobileText.setID(EditUserProcessor.ID_Text_Mobile);
		mobileText.setMaximumLength(11);
		mobileText.setRegExp(SAAS.Reg.REGEXP_mob);
		mobileText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//第二行　身份证号
		Label identityLabel = new Label(formArea, JWT.RIGHT);
		identityLabel.setText("身份证号：");
		identityLabel.setLayoutData(firstGridData);
		Text identityNumberText = new Text(formArea, JWT.APPEARANCE3);
		identityNumberText.setID(EditUserProcessor.ID_Text_IdentityNumber);
		identityNumberText.setMaximumLength(20);
		identityNumberText.setLayoutData(textGridData);
		//第三行　邮箱及岗位
		Label emailLabel = new Label(formArea, JWT.RIGHT);
		emailLabel.setText("邮箱：");
		emailLabel.setLayoutData(firstGridData);
		Text emailText = new Text(formArea, JWT.APPEARANCE3);
		emailText.setID(EditUserProcessor.ID_Text_Email);
		emailText.setMaximumLength(50);
		emailText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		Label jobLabel = new Label(formArea, JWT.RIGHT);
		jobLabel.setText("岗位：");
		jobLabel.setLayoutData(secondGridData);
		Text jobText = new Text(formArea, JWT.APPEARANCE3);
		jobText.setID(EditUserProcessor.ID_Text_Job);
		jobText.setMaximumLength(30);
		jobText.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		//第四行　部门
		new SAsteriskLabel(formArea, "部门：").setLayoutData(firstGridData);
		Text departmentText = new Text(formArea, JWT.APPEARANCE3 | JWT.BUTTON);
		departmentText.setID(EditUserProcessor.ID_Text_Department);
		departmentText.setLayoutData(textGridData);
		departmentText.setEditable(false);
		//第五行　用户权限
		new SAsteriskLabel(formArea, "用户权限：").setLayoutData(firstGridData);
		Text rolesInfoText = new Text(formArea, JWT.APPEARANCE3 | JWT.BUTTON);
		rolesInfoText.setID(EditUserProcessor.ID_Text_RolesInfo);
		rolesInfoText.setLayoutData(textGridData);
		rolesInfoText.setEditable(false);
    }
	
	/**
	 * 操作按钮
	 */
	@Override
    protected void renderButton(Composite buttonArea){
		buttonArea.setLayout(new GridLayout());
		//保存按钮
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setID(EditUserProcessor.ID_Button_Save);
		GridData saveButtonGridData = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		saveButtonGridData.widthHint = 80;
		saveButtonGridData.heightHint = 30;
		saveButton.setText("保存");
		saveButton.setLayoutData(saveButtonGridData);
    }
}
