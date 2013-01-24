package com.spark.common.components.controls.text;

import java.util.Date;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.common.components.CommonImages;

public class SDatePicker extends DatePicker {

	public final ImageDescriptor image = CommonImages
			.getImage("img/ico_calendar.png");

	public SDatePicker(Composite parent) {
		super(parent, JWT.APPEARANCE3 | JWT.MIDDLE);
		this.setImage(image);
		this.setDate(new Date());
	}

}
