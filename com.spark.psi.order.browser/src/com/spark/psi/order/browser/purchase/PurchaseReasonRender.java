/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * 
 *采购原因
 */
public class PurchaseReasonRender extends BaseFormPageRender {

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		formArea.setLayout(new FillLayout()); //
		new Text(formArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.H_SCROLL
				| JWT.V_SCROLL).setID(PurchaseReasonProcessor.ID_Text_Reason);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button saveButton = new Button(buttonArea,JWT.APPEARANCE3);
		saveButton.setID(PurchaseReasonProcessor.ID_Button_Save);
		saveButton.setText("    确定   ");

		Button cancelButton = new Button(buttonArea,JWT.APPEARANCE3);
		cancelButton.setID(PurchaseReasonProcessor.ID_Button_Cancel);
		cancelButton.setText("   取消   ");
	}
}
