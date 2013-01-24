package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.layouts.LayoutData;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.pages.BaseFormPageRender;

public class InfoPageRender extends BaseFormPageRender{
	
	@Override
	protected boolean hideFooterArea() {
		return true;
	}
	
	@Override
	protected boolean customizeButtonLayout() {
		return true;
	}

	@Override
	protected void renderButton(Composite buttonArea) {}

	@Override
	protected void renderFormArea(Composite formArea) {
//		Composite space = new Composite(formArea);
//		space.setLayout(new GridLayout());
//		GridData data = new GridData();
//		data.grabExcessHorizontalSpace = true;
//		data.heightHint = 2;
//		space.setLayoutData(data);
		Composite cmp = new Composite(formArea);
		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 0;
		cmp.setLayout(layout);
		cmp.setLayoutData(GridData.INS_FILL_BOTH);
		cmp.setID(InfoPageProcessor.ID_Composite_ResultSet);
	}
}