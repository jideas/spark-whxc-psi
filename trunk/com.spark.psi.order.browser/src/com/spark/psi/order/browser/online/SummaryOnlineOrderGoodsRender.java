package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.controls.text.SDatePicker;

public class SummaryOnlineOrderGoodsRender extends AbstractDistributePageRender {

	@Override
	protected void renderRHeaderRight(Composite headerRightArea) {
		Label label = new Label(headerRightArea);
		label.setText("计划完成日期：");
		DatePicker datePicker = new SDatePicker(headerRightArea);
		datePicker.setID(DistributeOnlineOrderProcessor.ID_Date_Date);
		datePicker.setEnabled(true);
		
		new Label(headerRightArea).setText("  ");
	}

	@Override
	protected String getConfirmButtonTitle() {
		return " 生成生产订单 ";
	}

	@Override
	protected void renderBottomArea(Composite formArea) {
		Composite bottomArea = new Composite(formArea);
		GridData gdBottom = new GridData(GridData.FILL_HORIZONTAL);
		gdBottom.horizontalSpan = 3;
		gdBottom.heightHint = 50;
		bottomArea.setLayoutData(gdBottom);
		
		GridLayout glBottom = new GridLayout();
		glBottom.numColumns = 2;
		bottomArea.setLayout(glBottom);
		Label memoLabel = new Label(bottomArea);
		memoLabel.setText("备注：");
		memoLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING
				| GridData.HORIZONTAL_ALIGN_END));
		Text memoText = new Text(bottomArea, JWT.APPEARANCE3);
		memoText.setID(SummaryOnlineOrderGoodsProcessor.ID_Text_Remark);
		GridData gdMemo = new GridData(GridData.FILL_BOTH);
		memoText.setLayoutData(gdMemo);
	}
}
