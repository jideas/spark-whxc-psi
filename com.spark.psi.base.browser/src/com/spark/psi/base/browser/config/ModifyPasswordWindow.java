package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.spark.portal.browser.SMenuWindow;

public class ModifyPasswordWindow extends SMenuWindow {

	public ModifyPasswordWindow(Control owner) {
		super(null, owner, Direction.Down, ActiveMode.Click);
		this.getContentArea().setLayout(new GridLayout());
		new UserPassEditor(this.getContentArea());
	}

}
