package com.spark.psi.base.browser;

import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;

public class SheetTitleComposite extends Composite {
	private Label titleLabel;

	private static GridData gdLabel;
	private static GridData gdSeperater;

	private static Font titleFont;

	static {
		gdLabel = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_CENTER | GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END);

		gdSeperater = new GridData(GridData.FILL_HORIZONTAL);
		gdSeperater.heightHint = 1;

		titleFont = new Font();
		titleFont.setSize(18);
		titleFont.setName("ºÚÌå");
	}

	public SheetTitleComposite(Composite parent) {
		super(parent);
		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 2;
		this.setLayout(gl);

		titleLabel = new Label(this);
		titleLabel.setForeground(new Color(0x77A3DD));
		titleLabel.setLayoutData(gdLabel);
		titleLabel.setFont(titleFont);

		Composite separatorHr0 = new Composite(this);
		separatorHr0.setBackground(new Color(0x77A3DD));
		separatorHr0.setLayoutData(gdSeperater);

		Composite separatorHr1 = new Composite(this);
		separatorHr1.setBackground(new Color(0x77A3DD));
		separatorHr1.setLayoutData(gdSeperater);

		this.layout();
	}

	public void setTitleValue(String title) {
		if (title == null)
			return;
		String titleWithSpace = "";
		for (int charactorIndex = 0; charactorIndex < title.length(); charactorIndex++) {
			titleWithSpace += title.substring(charactorIndex,
					charactorIndex + 1)
					+ " ";
		}
		titleLabel.setText(" " + titleWithSpace + " ");
		this.layout();
	}
}
