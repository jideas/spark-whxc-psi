/**
 * 
 */
package com.spark.psi.inventory.browser.checkout;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SAsteriskLabel;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * 
 *
 */
public class CheckOutRender extends BaseFormPageRender {

	
	private final static GridLayout gl;
	private final static GridData gdLabel;
	private final static GridData gd2;

	static {
		gl = new GridLayout();
		gl.numColumns = 3;
		gl.verticalSpacing = 8;

		gdLabel = new GridData();
		gdLabel.widthHint = 70;

		gd2 = new GridData(GridData.FILL_HORIZONTAL);
		gd2.horizontalSpan = 2;
	}

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		formArea.setLayout(gl);
		SAsteriskLabel asteriskLabel = new SAsteriskLabel(formArea,"提货人：  ");
		asteriskLabel.setLayoutData(gdLabel);

		Text text  = new Text(formArea, JWT.APPEARANCE3);
		text.setID(CheckOutProcessor.ID_Text_DeliveryPeople);
		text.setLayoutData(gd2);

		Label label = new Label(formArea);
		label.setText("提货单位：");
		Text text1 = new Text(formArea, JWT.APPEARANCE3);
		text1.setID(CheckOutProcessor.ID_Text_DeliveryCompany);
		text1.setLayoutData(gd2);

		label = new Label(formArea);
		label.setText("  凭证号：");
		Text text2 = new Text(formArea, JWT.APPEARANCE3);
		text2.setID(CheckOutProcessor.ID_Text_CertificateNo);
		text2.setLayoutData(gd2);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button checkButton = new Button(buttonArea,JWT.APPEARANCE3);
		checkButton.setID(CheckOutProcessor.ID_Button_Check);
		checkButton.setText(" 确定出库 ");

		Button cancelButton = new Button(buttonArea,JWT.APPEARANCE3);
		cancelButton.setID(CheckOutProcessor.ID_Button_Cancel);
		cancelButton.setText(" 放弃出库 ");
	}

}
