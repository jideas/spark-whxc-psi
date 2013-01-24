package com.spark.psi.base.browser;

import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;

public class SheetNumberComposite extends Composite {
	private Label codeLabel;

	public SheetNumberComposite(Composite parent) {
		super(parent);
		GridLayout gl = new GridLayout(2);
		gl.horizontalSpacing = 0;
		this.setLayout(gl);
		this.setVisible(false);

		Label titleLabel = new Label(this);
		titleLabel.setText("µ¥ºÅ£º");
		titleLabel.setForeground(new Color(0x77A3DD));

		codeLabel = new Label(this);
		codeLabel.setForeground(new Color(0x77A3DD));
	}

	public void setSheetNumber(String sheetNumber) {
		codeLabel.setText(sheetNumber);
		this.setVisible(true);
		this.layout();
	}
}
