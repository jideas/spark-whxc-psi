package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.spark.common.components.pages.BaseFormPageRender;


public class AllocateInShelfInfoRender extends BaseFormPageRender {

	@Override
	protected void renderButton(Composite buttonArea) {
		Button confirmBtn = new Button(buttonArea, JWT.APPEARANCE3);
		confirmBtn.setText(" È·¶¨ ");
		confirmBtn.setID(AllocateInShelfInfoProcessor.ID_Button_Confirm);
	}

	@Override
	protected void renderFormArea(Composite formArea) {
		GridLayout glForm = new GridLayout();
		formArea.setLayout(glForm);
		formArea.setLayout(new FillLayout()); 
		ScrolledPanel scrolledPanel = new ScrolledPanel(formArea, JWT.V_SCROLL);   
		Composite scrolledArea = scrolledPanel.getComposite();
		scrolledPanel.setHorizontalPosition(JWT.NO);
		GridLayout gl = new GridLayout();
		gl.horizontalSpacing = 5;
		gl.marginRight = 5;
		gl.verticalSpacing = 5;
		scrolledArea.setLayout(gl);

		Composite composite = new Composite(scrolledArea);
		composite.setLayoutData(GridData.INS_FILL_HORIZONTAL);
		GridLayout glCmp = new GridLayout();
		glCmp.horizontalSpacing = 5;
		glCmp.verticalSpacing = 5;
		composite.setLayout(glCmp);
		
		composite.setID(AllocateInShelfInfoProcessor.ID_Area_Content);

	}
	
	@Override
	protected final boolean customizeFormLayout() {
		return true;
	}
}
