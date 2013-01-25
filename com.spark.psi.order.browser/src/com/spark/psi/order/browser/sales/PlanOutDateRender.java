/**
 * 
 */
package com.spark.psi.order.browser.sales;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.controls.text.SDatePicker;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * 
 * 预计出库日期 请通过com.spark.psi.order.browser.sales.SetPlanOutDate工具类使用，不要直接使用
 */
public class PlanOutDateRender extends BaseFormPageRender {

	protected boolean customizeFormLayout() {
		return true;
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout gl = new GridLayout(2);
		gl.marginTop = 25;
		gl.marginLeft = 2;
		gl.marginRight = 2;
		formArea.setLayout(gl); //
		Label label = new Label(formArea);
		label.setText("预计出库日期：");

		DatePicker dp = new SDatePicker(formArea);
		dp.setID(PlanOutDateProcessor.ID_Plan_Date);
		dp.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		// | JWT.MULTI | JWT.H_SCROLL
		// | JWT.V_SCROLL).setID(PlanOutDateProcessor.ID_Plan_Date);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		Button saveButton = new Button(buttonArea, JWT.APPEARANCE3);
		saveButton.setID(PlanOutDateProcessor.ID_Button_Save);
		saveButton.setText("    确定    ");

		if (null != getArgument()) {
			Button returnButton = new Button(buttonArea, JWT.APPEARANCE3);
			returnButton.setID(PlanOutDateProcessor.ID_Button_Return);
			returnButton.setText("    退回    ");
		}
		
		Button cancelButton = new Button(buttonArea, JWT.APPEARANCE3);
		cancelButton.setID(PlanOutDateProcessor.ID_Button_Cancel);
		cancelButton.setText("    取消    ");
	}
}
