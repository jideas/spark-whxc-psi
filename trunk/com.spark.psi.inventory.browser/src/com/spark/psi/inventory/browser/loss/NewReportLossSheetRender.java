package com.spark.psi.inventory.browser.loss;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;

public class NewReportLossSheetRender extends ReportLossSheetRender {

	@Override
	protected void fillDataInfoControl(Composite dataInfoArea) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void fillStopCauseControl(Composite stopCauseArea) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void renderSheetButtonArea(Composite sheetButtonArea) {
		Button submitBtn = new Button(sheetButtonArea, JWT.APPEARANCE3);
		submitBtn.setText(" 提交申请 ");
		submitBtn.setID(NewReportLossSheetProcessor.ID_Button_Submit);
		
		Button saveBtn = new Button(sheetButtonArea, JWT.APPEARANCE3);
		saveBtn.setText(" 保存 ");
		saveBtn.setID(NewReportLossSheetProcessor.ID_Button_Save);
	}

	@Override
	protected void renderTableButtonArea(Composite tableButtonArea) {
		Button addBtn = new Button(tableButtonArea, JWT.APPEARANCE2);
		addBtn.setText(" 添加材料 ");
		addBtn.setID(NewReportLossSheetProcessor.ID_Button_AddMaterial);
	}

	@Override
	protected void renderBaseInfo(Composite baseInfoArea) {
		new Label(baseInfoArea).setText("报损仓库：");
		if (null == reportLossInfo) {
			LWComboList storeList = new LWComboList(baseInfoArea, JWT.APPEARANCE3);
			storeList.setID(NewReportLossSheetProcessor.ID_List_Store);
		} else {
			new Label(baseInfoArea).setText(reportLossInfo.getStoreName());
		}
	}

}
