package com.spark.portal.browser;

import com.jiuqi.dna.ui.custom.popup.FocusInfoWindow;
import com.jiuqi.dna.ui.wt.widgets.Control;

public class SFocusInfoWindow extends FocusInfoWindow {

	public SFocusInfoWindow(Control owner) {
		super(owner, SMenuWindow.imageBorder, SMenuWindow.image1,
				SMenuWindow.image2);
		setBackimage(SMenuWindow.backImage);
	}

}
