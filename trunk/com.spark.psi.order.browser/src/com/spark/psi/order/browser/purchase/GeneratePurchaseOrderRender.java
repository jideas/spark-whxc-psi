/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * 
 *
 */
public class GeneratePurchaseOrderRender extends BaseFormPageRender {

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		formArea.setID(GeneratePurchaseOrderProcessor.ID_Form_Area);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText(" 确定生成 ");
		button.setID(GeneratePurchaseOrderProcessor.ID_Button_Confirm);
	}

}
