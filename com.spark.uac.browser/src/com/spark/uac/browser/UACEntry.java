package com.spark.uac.browser;

import com.jiuqi.dna.ui.wt.UIEntry;
import com.jiuqi.dna.ui.wt.widgets.Shell;

public class UACEntry implements UIEntry {

	public void createUI(String[] args, Shell shell) {
		shell.setTitle("ÆßºÅÉú»î¹Ý");
		shell.showPage("UAC_LoginPage");

	}
}
