package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.pages.BaseListPageRender;
import com.spark.common.components.table.STableColumn;

public class GoodsBomPageRender extends BaseListPageRender {
	
	private Composite header2Area = null;
	
	@Override
	public STableColumn[] getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		
	}

	@Override
	protected void beforeTableRender() {
		super.beforeTableRender();
		header2Area = new Composite(contentArea);
		GridLayout glHeader2 = new GridLayout();
		glHeader2.numColumns = 8;
		
		GridData gdHeader2 = new GridData(GridData.FILL_HORIZONTAL);
		gdHeader2.heightHint = 32;
		
		header2Area.setLayout(glHeader2);
	}
	
	

}
