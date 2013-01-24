package com.spark.psi.inventory.browser.checkin;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.psi.base.browser.SimpleSheetPageRender;

public abstract class ExtendSimpleSheetPageRender extends SimpleSheetPageRender{

	protected void addProcessing(Composite parent) {	
		GridLayout gridLayout = new GridLayout(4);
		gridLayout.marginTop = 3;
		GridData zlzdgd = new GridData();
		zlzdgd.heightHint = 24;
		Composite zlzdCmp = new Composite(parent);
		zlzdCmp.setLayoutData(zlzdgd);
		zlzdCmp.setLayout(gridLayout);		
		new Label(zlzdCmp).setText("¡¡´¦Àí×´Ì¬£º");
		new Label(zlzdCmp).setID(ExtendSimpleSheetPageProcessor.ID_Label_ProcessingStatusValue);
		new Label(zlzdCmp,JWT.LINK|JWT.CURSOR_HAND).setID(ExtendSimpleSheetPageProcessor.ID_Label_Link);
	}	
}