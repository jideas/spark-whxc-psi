package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.LWComboList;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.base.browser.PSIListPageRender;

public abstract class AbstractShelfLifeWarningRender extends PSIListPageRender {
	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		Label label = new Label(headerLeftArea);
		label.setText("仓库：");
		LWComboList list = new LWComboList(headerLeftArea,JWT.APPEARANCE3);
		list.setID(AbstractShelfLifeWarningProcessor.ID_Store);
		label = new Label(headerLeftArea);
		label.setText("  状态：");
		list = new LWComboList(headerLeftArea,JWT.APPEARANCE3);
		list.setID(AbstractShelfLifeWarningProcessor.ID_Status);
		label = new Label(headerLeftArea);
		label.setText("  记录数量：");
		label = new Label(headerLeftArea);
		label.setID(AbstractSummaryProcessor.ID_Label_Count);
		GridData gdLabel = new GridData();
		gdLabel.widthHint = 120;
		label.setLayoutData(gdLabel);
		
//		Button button = new Button(headerRightArea, JWT.APPEARANCE3);
//		button.setText(" 高级搜索 ");
//		button.setID(AbstractSummaryProcessor.ID_Button_AdvanceSearch);
//		
//		button = new Button(headerRightArea, JWT.APPEARANCE3);
//		button.setText(" 清空搜索条件 ");
//		button.setID(AbstractSummaryProcessor.ID_Button_Reset);
		
//		button = new Button(footerRightArea, JWT.APPEARANCE3);
//		button.setText(" 导 出 ");
//		button.setID(AbstractSummaryProcessor.ID_Button_Export);
	}
	
}
