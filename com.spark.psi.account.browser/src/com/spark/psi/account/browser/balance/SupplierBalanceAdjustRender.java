package com.spark.psi.account.browser.balance;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.BaseFormPageRender;

/**
 * 供应商往来调整页面视图
 */
public class SupplierBalanceAdjustRender extends BaseFormPageRender {

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		gl.verticalSpacing = 6;
		formArea.setLayout(gl);
		
		GridData gdLabel = new GridData(GridData.VERTICAL_ALIGN_CENTER | GridData.HORIZONTAL_ALIGN_END);
		
		Label nameLabel = new Label(formArea);
		nameLabel.setID(SupplierBalanceAdjustProcessor.ID_LABEL_PROVIDERNAME);
		nameLabel.setFont(new Font(9, "宋体", JWT.FONT_STYLE_BOLD));
		GridData gdNameLabel = new GridData();
		gdNameLabel.horizontalSpan = 3;
		gdNameLabel.heightHint = 25;
		nameLabel.setLayoutData(gdNameLabel);
		
		Label label0 = new Label(formArea);
		label0.setText("  应付余额：");
		label0.setLayoutData(gdLabel);
		Label dueAmountLabel = new Label(formArea);
		dueAmountLabel.setID(SupplierBalanceAdjustProcessor.ID_LABEL_DEUAMOUNT);
		GridData gdDueAmountLabel = new GridData();
		gdDueAmountLabel.horizontalSpan = 2;
		dueAmountLabel.setLayoutData(gdDueAmountLabel);
		
		
		Label label1 = new Label(formArea);
		label1.setText("  调整金额：");
		label1.setLayoutData(gdLabel);
		Text text = new Text(formArea, JWT.APPEARANCE3);
		text.setID(SupplierBalanceAdjustProcessor.ID_TEXT_ADJUSTAMOUNT);
		text.setRegExp(JWT.REGEXP_LIMIT_DOUBLE);
		new Label(formArea).setText("正值表示增加应付，负值表示减少应付");
		
		Label label2 = new Label(formArea);
		label2.setText("  调整原因：");
		GridData gdLabel2 = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END);
		label2.setLayoutData(gdLabel2);
		Text reasonText = new Text(formArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.WRAP);
		reasonText.setID(CustomerBalanceAdjustProcessor.ID_TEXT_ADJUSTREASON);
		GridData gdReasonText = new GridData(GridData.FILL_HORIZONTAL);
		gdReasonText.horizontalSpan = 2;
		gdReasonText.heightHint = 48;
		reasonText.setLayoutData(gdReasonText);
	}

	@Override
	protected void renderButton(Composite buttonArea) {
		new Button(buttonArea, JWT.APPEARANCE3)
		.setID(CustomerBalanceAdjustProcessor.ID_BUTTON_CHECKADJUST);

		new Button(buttonArea, JWT.APPEARANCE3)
				.setID(CustomerBalanceAdjustProcessor.ID_BUTTON_CANCELADJUST);
	}

	@Override
	protected boolean customizeFormLayout() {
		return true;
	}

}
