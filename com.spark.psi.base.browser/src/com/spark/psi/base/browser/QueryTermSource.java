package com.spark.psi.base.browser;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.spark.psi.publish.QueryTerm;

/**
 * ≤÷ø‚¡–±Ì‘¥
 * 
 */
public class QueryTermSource extends ListSourceAdapter {

	private String firstTermId;

	public Object[] getElements(Object inputElement) {
		Context context = Display.getCurrent().getSituation();
		List<QueryTerm> itemList = context.getList(QueryTerm.class);
		firstTermId = itemList.get(2).getName();
		return itemList.toArray();
	}

	public String getFirstStoreId() {
		return this.firstTermId;
	}

	public String getText(Object element) {
		return ((QueryTerm) element).getName();
	}

	public String getElementId(Object element) {
		return ((QueryTerm) element).getName();
	}

	public Object getElementById(String id) {
		return Display.getCurrent().getSituation().find(QueryTerm.class, id);
	}

}
