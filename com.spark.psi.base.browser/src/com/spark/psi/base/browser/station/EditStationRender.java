package com.spark.psi.base.browser.station;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.graphics.Cursor;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAAS;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.controls.text.SAAS.Reg;
import com.spark.common.components.pages.BaseFormPageRender;
import com.spark.psi.base.browser.internal.BaseImages;

public class EditStationRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setID(EditStationProcessor.ID_Button_Save);
		saveButton.setText("    保存    ");
		Button saveAndNewButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveAndNewButton.setID(EditStationProcessor.ID_Button_SaveAdd);
		saveAndNewButton.setText(" 保存并新建 ");
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		Composite composite = new Composite(formArea);
		GridLayout gl = new GridLayout();
		gl.numColumns = 7;
		gl.horizontalSpacing = 5;
		gl.verticalSpacing = 5;
		composite.setLayout(gl);

		GridData gd3 = new GridData();
		gd3.horizontalSpan = 3;
		gd3.grabExcessHorizontalSpace = true;
		gd3.horizontalAlignment = JWT.FILL;

		GridData gdLabel = new GridData(GridData.HORIZONTAL_ALIGN_END);

		new SAsteriskLabel(composite, "站点简称：").setLayoutData(gdLabel);
		Text text = new Text(composite, JWT.APPEARANCE3);
		text.setID(EditStationProcessor.ID_Text_ShortName);
		text.setHint("六字以内简称");
		text.setMaximumLength(6);
		text.setRegExp(SAAS.Reg.TEXT);

		GridData thatgd = new GridData();
		thatgd.widthHint = 0;
		Label that = new Label(composite);
		that.setLayoutData(thatgd);

		new SAsteriskLabel(composite, "站点全称：").setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(EditStationProcessor.ID_Text_Name);
		// text.setLayoutData(gd3);
		text.setMaximumLength(50);
		text.setRegExp(SAAS.Reg.TEXT);
		new Label(composite);
		new Label(composite);

		Label label = new Label(composite);
		label.setText("      编号：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(EditStationProcessor.ID_Text_Number);
		text.setMaximumLength(20);
		text.setEnabled(false);
		that = new Label(composite);
		that.setLayoutData(thatgd);

		label = new Label(composite);
		label.setText("      电话：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(EditStationProcessor.ID_Text_WorkTel);
		text.setMaximumLength(20);
		text.setRegExp(Reg.REGEXP_PHONE);
		new Label(composite);
		new Label(composite);

		label = new Label(composite);
		label.setText("传真：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(EditStationProcessor.ID_Text_Fax);
		text.setRegExp(Reg.REGEXP_PHONE);
		text.setMaximumLength(20);
		that = new Label(composite);
		that.setLayoutData(thatgd);
		new Label(composite);
		new Label(composite);
		new Label(composite);
		new Label(composite);


		new SAsteriskLabel(composite, "地址：").setLayoutData(gdLabel);

		Composite addressArea1 = new Composite(composite);
		addressArea1.setLayout(new GridLayout(3));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 6;
		addressArea1.setLayoutData(gd);
		LWComboList comboList = new LWComboList(addressArea1, JWT.APPEARANCE3);
		comboList.setID(EditStationProcessor.ID_List_Province);
		comboList.setHint("省（直辖市）");
		comboList = new LWComboList(addressArea1, JWT.APPEARANCE3);
		comboList.setID(EditStationProcessor.ID_List_City);
		comboList.setHint("市（区）");
		comboList = new LWComboList(addressArea1, JWT.APPEARANCE3);
		comboList.setID(EditStationProcessor.ID_List_District);
		comboList.setHint("区（县）");

		new Label(composite).setText("");
		Composite addressArea2 = new Composite(composite);
		addressArea2.setLayout(new GridLayout(2));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 5;
		addressArea2.setLayoutData(gd);
		text = new Text(addressArea2, JWT.APPEARANCE3);
		text.setID(EditStationProcessor.ID_Text_Address);
		text.setHint("详细地址");
		text.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		text.setMaximumLength(50); 

		new Label(composite);

		new SAsteriskLabel(composite, "负责人：").setLayoutData(gdLabel);
		 
		Text keeperText = new Text(composite, JWT.APPEARANCE3);
		keeperText.setID(EditStationProcessor.ID_Text_ManageName);
		keeperText.setEnabled(false);

		Label labelAddKeepers = new Label(composite);
		labelAddKeepers.setCursor(Cursor.HAND);
		labelAddKeepers.setID(EditStationProcessor.ID_Label_AddKeeper);
		labelAddKeepers.setImage(BaseImages.getImage("images/store/saas_mark_add_manager.png"));

		new SAsteriskLabel(composite, "负责人电话：").setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(EditStationProcessor.ID_Text_ManageMobile);
		text.setMaximumLength(20);
		text.setEnabled(false);
		text.setRegExp(SAAS.Reg.REGEXP_mob);
		new Label(composite);
		new Label(composite);
		
		
		label = new Label(composite);
		label.setText("负责人身份证：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(EditStationProcessor.ID_Text_ManagePersonNo);
		text.setMaximumLength(23);text.setEnabled(false);
		that = new Label(composite);
		that.setLayoutData(thatgd);
		
		label = new Label(composite);
		label.setText("负责人邮箱：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(EditStationProcessor.ID_Text_ManageEmail);
		text.setMaximumLength(100);
		text.setRegExp(SAAS.Reg.Mail);
		new Label(composite);
		new Label(composite);
		
		label = new Label(composite);
		label.setText("备注：");
		label.setLayoutData(gdLabel);
		text = new Text(composite, JWT.APPEARANCE3);
		text.setID(EditStationProcessor.ID_Text_Remark);
		text.setMaximumLength(100);
		text.setLayoutData(gd);
	}
}
