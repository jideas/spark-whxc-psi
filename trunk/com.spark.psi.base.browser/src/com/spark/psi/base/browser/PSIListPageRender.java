package com.spark.psi.base.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.BaseListPageRender;
import com.spark.common.components.table.edit.SActionInfo;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.Action;

public abstract class PSIListPageRender extends BaseListPageRender {

	public SActionInfo getActionInfo(String actionId) {
		Action action = Action.valueOf(actionId);
		String title = actionId;
		if (action != null) {
			title = action.getTitle();
		}
		ActionButton button = ActionButton.getActionButton(action);
		return new SActionInfo(actionId, title, BaseImages.getImage(button.getNormalImagePath()),BaseImages.getImage(button.getHoverImagePaht()));
	}

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();
		Button button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" µ¼ ³ö ");
		button.setID(PSIListPageProcessor.ID_Button_Export);
	}
}
