package com.spark.psi.base.browser.bom;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.table.edit.SActionInfo;

public class ActivingBomRender extends EditBomRender {
	@Override
	protected void renderButton(Composite buttonArea) {

		Button button = new Button(footerArea, JWT.APPEARANCE3);
		button.setText("  µ¼  ³ö  ");
		button.setID(EditBomProcessor.ID_Button_Except);
		
		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("  Æô  ÓÃ  ");
		button.setID(EditBomProcessor.ID_Button_Active);

	}
	@Override
	protected SActionInfo[] getActions() { 
		return null;
	}
}
