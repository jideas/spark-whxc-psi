package com.spark.psi.base.browser;

import com.spark.common.components.table.edit.SActionInfo;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.Action;

public class PSIActionCommon {
	public static SActionInfo getActionInfo(String actionId) {
		Action action = Action.valueOf(actionId);
		String title = actionId;
		if (action != null) {
			title = action.getTitle();
		}
		ActionButton button = ActionButton.getActionButton(action);
		return new SActionInfo(actionId, title, BaseImages.getImage(button.getNormalImagePath()),BaseImages.getImage(button.getHoverImagePaht()));
	}
}
