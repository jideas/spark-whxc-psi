package com.spark.psi.base.browser.bom;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.table.edit.SActionInfo;

public class ApprovingBomRender extends EditBomRender {
	@Override
	protected void renderButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("  Åú  ×¼  ");
		button.setID(EditBomProcessor.ID_Button_Approve);

		button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("  ÍË  »Ø  ");
		button.setID(EditBomProcessor.ID_Button_Reject);

	}@Override
	protected SActionInfo[] getActions() { 
		return null;
	}
}
