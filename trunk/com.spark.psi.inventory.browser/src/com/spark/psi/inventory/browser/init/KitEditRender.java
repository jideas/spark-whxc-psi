package com.spark.psi.inventory.browser.init;

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
 * 仓库启用设置-其它库存-添加物品表单
 */
public class KitEditRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button buttonSave = new Button(buttonArea, JWT.APPEARANCE3);
		buttonSave.setID(KitEditProcessor.ID_Button_ConfirmNew);
		buttonSave.setText("确定新增");
		buttonSave = new Button(buttonArea, JWT.APPEARANCE3);
		buttonSave.setID(KitEditProcessor.ID_Button_GiveUpNew);
		buttonSave.setText("放弃新增");
	}

	@Override
	protected void renderFormArea(Composite formArea) {		
		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);
		Composite cmp = new Composite(formArea);
		cmp.setLayout(new GridLayout(2));
		cmp.setLayoutData(GridData.INS_FILL_BOTH);	
		SAsteriskLabel label0 = new SAsteriskLabel(cmp,"物品名称：");
		label0.setLayoutData(gdLabel);
		GridData gd1 = new GridData();
		gd1.widthHint = 70;
		Text txtKitName = new Text(cmp,JWT.APPEARANCE3);
		txtKitName.setMaximumLength(100);
		txtKitName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtKitName.setID(KitEditProcessor.ID_Text_KitName);		
		SAsteriskLabel lblKitDescription = new SAsteriskLabel(cmp,"物品描述：");
		lblKitDescription.setLayoutData(gd1);
		Text txtKitDescription = new Text(cmp,JWT.APPEARANCE3);
		txtKitDescription.setMaximumLength(1000);
		txtKitDescription.setLayoutData(GridData.INS_FILL_BOTH);
		txtKitDescription.setID(KitEditProcessor.ID_Text_KitDescription);		
		GridData gd3 = new GridData();
		gd3.horizontalSpan = 2;
		gd3.grabExcessHorizontalSpace = true;
		gd3.horizontalAlignment = JWT.FILL;
		Composite cmpThree = new Composite(cmp);
		cmpThree.setLayout(new GridLayout(4));
		cmpThree.setLayoutData(gd3);		
		GridData gd2 = new GridData();
		gd2.widthHint = 90;
		label0 = new SAsteriskLabel(cmpThree,"单　　位：");
		label0.setLayoutData(gdLabel);
		Text txtUnit = new Text(cmpThree,JWT.APPEARANCE3);
		txtUnit.setLayoutData(gd2);
		txtUnit.setID(KitEditProcessor.ID_Text_Unit);
		txtUnit.setMaximumLength(12);		
		Label lblCount = new Label(cmpThree,JWT.RIGHT);
		lblCount.setText("初始数量：");
		lblCount.setLayoutData(gd1);
		GridData gd4 = new GridData();
		gd4.grabExcessHorizontalSpace = true;
		Text txtCount = new Text(cmpThree,JWT.APPEARANCE3);
		txtCount.setLayoutData(gd4);
		txtCount.setID(KitEditProcessor.ID_Text_Count);
		txtCount.setMaximumLength(17);
		txtCount.setRegExp("^\\d*?$");//SAAS.Reg.REGEXP_POSITIVE_DOUBLE);
	}
}