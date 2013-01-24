package com.spark.common.components.pages;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.ImageBorder;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.ImageBorderComposite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Window;
import com.spark.common.components.CommonImages;

public abstract class SMessageWindow extends Window {

	protected final static ImageDescriptor infoImage = CommonImages
			.getImage("img/dialog/info.png");
	protected final static ImageDescriptor errorImage = CommonImages
			.getImage("img/dialog/error.png");
	protected final static ImageDescriptor sureImage = CommonImages
			.getImage("img/dialog/sure.png");

	private final static ImageDescriptor iconImage = CommonImages
			.getImage("img/dialog/dialog.png");

	private final static ImageDescriptor backImage = CommonImages
			.getImage("img/dialog/bg.png");

	private final static Color bgColor = new Color(0xcbd6de);

	private final static ImageBorder imageBorder = new ImageBorder(
			new ImageDescriptor[] { CommonImages.getImage("img/dialog/0.png"),
					CommonImages.getImage("img/dialog/1.png"),
					CommonImages.getImage("img/dialog/2.png"),
					CommonImages.getImage("img/dialog/3.png"),
					CommonImages.getImage("img/dialog/4.png"),
					CommonImages.getImage("img/dialog/5.png"),
					CommonImages.getImage("img/dialog/6.png"),
					CommonImages.getImage("img/dialog/7.png") });

	private final static GridData gdButton1;
	private final static GridData gdButton2;
	private final static GridData gdButtonArea;
	private final static GridData gdImageLabel;
	private final static GridData gdTextLabel;

	static {
		gdButton1 = new GridData(GridData.VERTICAL_ALIGN_CENTER
				| GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_END
				| GridData.GRAB_HORIZONTAL);
		gdButton1.heightHint = 28;
		gdButton1.widthHint = 80;

		gdButton2 = new GridData(GridData.VERTICAL_ALIGN_CENTER
				| GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_END);
		gdButton2.heightHint = 28;
		gdButton2.widthHint = 80;

		gdButtonArea = new GridData(GridData.FILL_HORIZONTAL);
		gdButtonArea.horizontalSpan = 2;
		gdButtonArea.heightHint = 33;

		gdImageLabel = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_CENTER);
		gdTextLabel = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_CENTER | GridData.GRAB_HORIZONTAL);
		gdTextLabel.verticalIndent = -8;

	}

	protected Label imageLabel;
	protected Label textLabel;

	public SMessageWindow() {
		super(JWT.CLOSE | JWT.MODAL);
		this.setSize(330, 175);
		this.setIcon(iconImage);
		this.setLayout(new FillLayout());
		ImageBorderComposite contentArea = new ImageBorderComposite(this);
		contentArea.setImageBorder(imageBorder);
		this.setBackimage(backImage);
		//
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		gl.marginLeft = 20;
		gl.marginRight = 15;
		gl.marginBottom = 10;
		gl.horizontalSpacing = 15;
		contentArea.setLayout(gl);

		imageLabel = new Label(contentArea);
		imageLabel.setLayoutData(gdImageLabel);

		textLabel = new Label(contentArea, JWT.WRAP);
		textLabel.setLayoutData(gdTextLabel);

		Composite buttonArea = new Composite(contentArea);
		buttonArea.setBackground(bgColor);
		buttonArea.setLayoutData(gdButtonArea);
		gl = new GridLayout();
		buttonArea.setLayout(gl);
		createButton(buttonArea);
		Control[] controls = buttonArea.getChildren();
		if (controls != null) {
			for (int i = 0; i < controls.length; i++) {
				if (i == 0) {
					controls[i].setLayoutData(gdButton1);
				} else {
					controls[i].setLayoutData(gdButton2);
				}
				controls[i].addClientEventHandler(JWT.CLIENT_EVENT_KEY_DOWN,
						"SComponent.handleButtonAreaKey");
			}
			gl.numColumns = controls.length;
		}
	}

	protected abstract void createButton(Composite buttonArea);
}
