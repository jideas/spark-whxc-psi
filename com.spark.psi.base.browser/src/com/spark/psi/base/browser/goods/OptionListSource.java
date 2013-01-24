package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;

public class OptionListSource extends ListSourceAdapter {

	private String[] options;

	public OptionListSource(String[] options) {
		this.options = options;
	}

	public Object[] getElements(Object inputElement) {
		return options;
	}

	public String getText(Object element) {
		return (String) element;
	}

	public String getElementId(Object element) {
		return (String) element;
	}

	public Object getElementById(String id) {
		return id;
	}

}
