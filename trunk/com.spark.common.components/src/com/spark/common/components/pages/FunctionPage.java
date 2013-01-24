package com.spark.common.components.pages;

import com.jiuqi.dna.ui.wt.graphics.Point;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Page;

public abstract class FunctionPage extends Page {

	public FunctionPage(Composite parent) {
		super(parent);
	}

	protected abstract Point getPreferenceSize();

	protected abstract String getTitle();

}
