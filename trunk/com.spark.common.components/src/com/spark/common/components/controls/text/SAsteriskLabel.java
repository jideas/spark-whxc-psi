package com.spark.common.components.controls.text;

import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;

public class SAsteriskLabel extends Composite {

	public SAsteriskLabel(Composite parent, String text) {
		super(parent);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		this.setLayout(gl);

		Label asteriskLabel = new Label(this);
		asteriskLabel.setText("*");
		asteriskLabel.setForeground(Color.COLOR_RED);
		GridData gd1 = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END);
		asteriskLabel.setLayoutData(gd1);

		Label textLabel = new Label(this);
		textLabel.setText(text);
		GridData gd2 = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END);
		textLabel.setLayoutData(gd2);

	}
}
