package com.spark.common.components.controls.text;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.CommonImages;

public class SSearchText2 extends Text {

	public final static ImageDescriptor image = CommonImages
			.getImage("img/search/search.png");

	public SSearchText2(Composite parent) {
		super(parent, JWT.APPEARANCE3 | JWT.BUTTON | JWT.MIDDLE);
		setImage(image);
		this.setHint(" ‰»ÎÀ—À˜ƒ⁄»›");
		this.addClientEventHandler(JWT.CLIENT_EVENT_CLICK,
				"SComponent.handleSearchTextFocus");
		this.addClientEventHandler(JWT.CLIENT_EVENT_FOCUS_GAINED,
				"SComponent.handleSearchTextFocus");
	}

}
