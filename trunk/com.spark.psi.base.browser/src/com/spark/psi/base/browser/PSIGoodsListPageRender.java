package com.spark.psi.base.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;

public abstract class PSIGoodsListPageRender extends PSIListPageRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();

		//
		Text text = new Text(headerRightArea, JWT.APPEARANCE3);
		text.setID(PSIGoodsListPageProcessor.ID_Text_SearchText);
		GridData gdText = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_CENTER);
		gdText.verticalIndent = 2;
		text.setLayoutData(gdText);
		Button searchCurrentButton = new Button(headerRightArea,
				JWT.APPEARANCE3);
		searchCurrentButton
				.setID(PSIGoodsListPageProcessor.ID_Button_SearchCurrent);
		searchCurrentButton.setText(" 搜本类 ");
		Button searchAllButton = new Button(headerRightArea, JWT.APPEARANCE3);
		searchAllButton.setID(PSIGoodsListPageProcessor.ID_Button_SearchAll);
		searchAllButton.setText(" 搜全部 ");

	}

}
