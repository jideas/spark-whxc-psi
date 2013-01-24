/**
 * 
 */
package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * 
 *
 */
public class NewContactRender extends BaseFormPageRender {

	@Override
	protected void renderFormArea(Composite formArea) {
		new Text(formArea).setID(NewContactProcessor.ID_Text_Name);
		new Button(formArea, JWT.CHECK)
				.setID(NewContactProcessor.ID_Check_Main);
		new Button(formArea, JWT.RADIO)
				.setID(NewContactProcessor.ID_Radio_Male);
		new Button(formArea, JWT.RADIO)
				.setID(NewContactProcessor.ID_Radio_Female);
		new Text(formArea).setID(NewContactProcessor.ID_Text_Respectfully);
		new Text(formArea).setID(NewContactProcessor.ID_Text_Mobile);
		new Text(formArea).setID(NewContactProcessor.ID_Text_LandLineNumber);
		new Text(formArea).setID(NewContactProcessor.ID_Text_Mail);
		new Label(formArea).setID(NewContactProcessor.ID_Label_Company);
		new Text(formArea).setID(NewContactProcessor.ID_Text_Department);
		new Text(formArea).setID(NewContactProcessor.ID_Text_Position);
		new Text(formArea).setID(NewContactProcessor.ID_Text_QQ);
		new Text(formArea).setID(NewContactProcessor.ID_Text_MSN);
		new Text(formArea).setID(NewContactProcessor.ID_Text_WangWang);
		new DatePicker(formArea).setID(NewContactProcessor.ID_Date_Birthday);
		new Text(formArea).setID(NewContactProcessor.ID_Text_Hobby);
		new Text(formArea).setID(NewContactProcessor.ID_Text_Memo);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button saveButton = new Button(buttonArea);
		saveButton.setID(NewContactProcessor.ID_Button_Save);
		saveButton.setText("保存信息");

		Button saveAndNewButton = new Button(buttonArea);
		saveAndNewButton.setID(NewContactProcessor.ID_Button_SaveAndNew);
		saveAndNewButton.setText("保存并新增");

	}

}
