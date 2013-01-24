package com.spark.psi.base.browser;

import com.spark.common.components.table.SSelectionMode;
import com.spark.common.components.table.SSpanProvider;
import com.spark.common.components.table.STableStyle;
import com.spark.psi.publish.MultItemRowObject;

public abstract class PSIMultiItemListPageRender extends PSIListPageRender implements
		SSpanProvider {

	public int getMaxRowSpan(Object element) {
		MultItemRowObject object = (MultItemRowObject)element;
		if (object.isFirstItem()) {
			return object.getRowSpan();
		}
		return 0;
	}
	
	public final STableStyle getTableStyle() {
		STableStyle style = new STableStyle();
		style.setPageAble(false);
		style.setSelectionMode(getSelectionMode());
		return style;
	}
	
	protected SSelectionMode getSelectionMode() {
		return SSelectionMode.Multi;
	}
}
