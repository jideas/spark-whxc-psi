package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.wt.widgets.Composite;

/**
 * 主界面使用的密码修改控件
 * @author durendong
 *
 */
public class IndexUserPassEditor extends UserPassEditor {

	public IndexUserPassEditor(Composite parent) {
		super(parent);
	}
	
	@Override
	public void doinit() {
		super.doinit();
		getBtnCheck().dispose();
		getCmp().setEnabled(true);
	}
}
