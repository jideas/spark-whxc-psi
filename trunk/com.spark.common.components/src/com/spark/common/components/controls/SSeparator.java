package com.spark.common.components.controls;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;

public class SSeparator extends Composite {

	public final static Color color = new Color(0x78a9bf);

	public SSeparator(Composite parent, int direction, int span) {
		super(parent);
		if (parent.getLayout() instanceof GridLayout) {
			if (direction == JWT.VERTICAL) {
				GridData gd = new GridData(GridData.FILL_VERTICAL);
				gd.widthHint = 1;
				gd.verticalSpan = span;
				this.setLayoutData(gd);
			} else {
				GridData gd = new GridData(GridData.FILL_HORIZONTAL);
				gd.heightHint = 1;
				gd.horizontalSpan = span;
				this.setLayoutData(gd);
			}
		}
		this.setBackground(color);
	}

}
