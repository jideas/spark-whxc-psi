package com.spark.psi.account.browser.invoice;


import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.AbstractBoxPageRender;

public class InvalidatePageRender extends AbstractBoxPageRender {

	@Override
	protected void afterFooterRender() {
		
		GridLayout gl = new GridLayout();
		gl.horizontalSpacing = 0;
		footerArea.setLayout(gl);
		
		GridData gdButton = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_END);
		
		Composite footerRightArea = new Composite(footerArea);
		footerRightArea.setLayoutData(gdButton);
		footerRightArea.setLayout(new GridLayout(2));
		
		Button checkButton = new Button(footerRightArea, JWT.APPEARANCE3);
		checkButton.setID(InvalidatePageProcessor.ID_Button_Check);
		checkButton.setLayoutData(gdButton);
		checkButton.setText(" È·¶¨ ");
		
		Button abortButton = new Button(footerRightArea, JWT.APPEARANCE3);
		abortButton.setID(InvalidatePageProcessor.ID_Button_Abort);
		abortButton.setLayoutData(gdButton);
		abortButton.setText(" ·ÅÆú ");
	}

	@Override
	protected void beforeFooterRender() {
		Text reasonText = new Text(contentArea, JWT.APPEARANCE3 | JWT.MULTI | JWT.WRAP | JWT.VERTICAL);
		reasonText.setID(InvalidatePageProcessor.ID_Text_Reason);
		reasonText.setLayoutData(GridData.INS_FILL_BOTH);
	}

}
