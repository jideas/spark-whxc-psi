/**
 * 
 */
package com.spark.psi.base.browser.customer;

import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * 
 *
 */
public class CustomerDeliveryAddressRender extends BaseFormPageRender {

	@Override
	protected void renderFormArea(Composite formArea) {
		new ComboList(formArea)
				.setID(CustomerDeliveryAddressProcessor.ID_List_Province);
		new ComboList(formArea)
				.setID(CustomerDeliveryAddressProcessor.ID_List_City);
		new ComboList(formArea)
				.setID(CustomerDeliveryAddressProcessor.ID_List_District);
		new Text(formArea)
				.setID(CustomerDeliveryAddressProcessor.ID_Text_Address);
		new Text(formArea)
				.setID(CustomerDeliveryAddressProcessor.ID_Text_PostCode);
		new Text(formArea)
				.setID(CustomerDeliveryAddressProcessor.ID_Text_Consignee);
		new Text(formArea)
				.setID(CustomerDeliveryAddressProcessor.ID_Text_MobileNo);
		new Text(formArea)
				.setID(CustomerDeliveryAddressProcessor.ID_Text_LandLineNumber);

	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button buttonSave = new Button(buttonArea);
		buttonSave.setID(CustomerDeliveryAddressProcessor.ID_Button_Save);
		buttonSave.setText("确定编辑");

		Button cancelSave = new Button(buttonArea);
		cancelSave.setID(CustomerDeliveryAddressProcessor.ID_Button_Cancel);
		cancelSave.setText("取消编辑");

	}
}
