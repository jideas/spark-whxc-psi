package com.spark.psi.base.browser.config;

import com.jiuqi.dna.ui.wt.widgets.Composite;

/**
 * ������ʹ�õ������޸Ŀؼ�
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
