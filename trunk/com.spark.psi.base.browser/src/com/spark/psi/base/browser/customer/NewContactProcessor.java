/**
 * 
 */
package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.BaseFormPageProcessor;

/**
 * 
 *
 */
public class NewContactProcessor extends BaseFormPageProcessor {
	public final static String ID_Text_Name = "Text_Name";
	public final static String ID_Check_Main = "Check_Main";
	public final static String ID_Radio_Male = "Radio_Male";
	public final static String ID_Radio_Female = "Radio_Female";
	public final static String ID_Text_Respectfully = "Text_Respectfully";
	public final static String ID_Text_Mobile = "Text_Mobile";
	public final static String ID_Text_LandLineNumber = "Text_LandLineNumber";
	public final static String ID_Text_Mail = "Text_Mail";
	public final static String ID_Label_Company = "Label_Company";
	public final static String ID_Text_Department = "Text_Department";
	public final static String ID_Text_Position = "Text_Position";
	public final static String ID_Text_QQ = "Text_QQ";
	public final static String ID_Text_MSN = "Text_MSN";
	public final static String ID_Text_WangWang = "Text_WangWang";
	public final static String ID_Date_Birthday = "Date_BirthDay";
	public final static String ID_Text_Hobby = "Text_Hobby";
	public final static String ID_Text_Memo = "Text_Memo";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_SaveAndNew = "Button_SaveAndNew";
	@Override
	public void process(Situation context) {
		Button saveButton = this.createControl(ID_Button_Save, Button.class, JWT.NONE);
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 保存信息
				
			}
		});
		
		Button saveAndNewButton = this.createControl(ID_Button_SaveAndNew, Button.class, JWT.NONE);
		saveAndNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 保存并新增
				
			}
		});
	}

}
