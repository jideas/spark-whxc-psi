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
public class CustomerDeliveryAddressProcessor extends BaseFormPageProcessor {
	
	public final static String ID_List_Province = "List_Province";
	public final static String ID_List_City = "List_City";
	public final static String ID_List_District = "List_District";
	public final static String ID_Text_Address = "Text_Address";
	public final static String ID_Text_PostCode = "Text_PostCode";
	public final static String ID_Text_Consignee = "Text_Consignee";
	public final static String ID_Text_MobileNo = "Text_MobileNo";
	public final static String ID_Text_LandLineNumber = "Text_LandLineNumber";
	public final static String ID_Button_Save = "Button_Save";
	public final static String ID_Button_Cancel = "Button_Cancel";
	
	@Override
	public void process(Situation context) {
		Button saveButton = this.createControl(ID_Button_Save, Button.class, JWT.NONE);
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO 保存收货地址
				
			}
		});
	}

}
