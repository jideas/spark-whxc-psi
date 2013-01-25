package com.spark.psi.query.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.base.browser.PSIListPageRender;

public abstract class AbstractSummaryRender extends PSIListPageRender {
	
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		Label label = new Label(headerLeftArea);
		label.setText("��¼������");
		label = new Label(headerLeftArea);
		label.setID(AbstractSummaryProcessor.ID_Label_Count);
		GridData gdLabel = new GridData();
		gdLabel.widthHint = 120;
		label.setLayoutData(gdLabel);
		
		Button button = new Button(headerRightArea, JWT.APPEARANCE3);
		button.setText(" �߼����� ");
		button.setID(AbstractSummaryProcessor.ID_Button_AdvanceSearch);
		
		button = new Button(headerRightArea, JWT.APPEARANCE3);
		button.setText(" ����������� ");
		button.setID(AbstractSummaryProcessor.ID_Button_Reset);
		
//		button = new Button(footerRightArea, JWT.APPEARANCE3);
//		button.setText(" �� �� ");
//		button.setID(AbstractSummaryProcessor.ID_Button_Export);
	}
	
}
