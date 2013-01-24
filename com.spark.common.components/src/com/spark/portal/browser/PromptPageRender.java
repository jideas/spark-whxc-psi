package com.spark.portal.browser;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.CommonImages;
import com.spark.common.components.pages.AbstractBoxPageRender;

public class PromptPageRender extends AbstractBoxPageRender {

	private String button1Title;
	private String button2Title;
	private String message;

	public void init(Situation context) {
		super.init(context);
		this.message = (String) getArgument();
		this.button1Title = (String) getArgument2();
		this.button2Title = (String) getArgument3();
	}

	@Override
	protected void beforeFooterRender() {
		Composite infoArea = new Composite(contentArea);
		contentArea.setBackimage(CommonImages.getImage("img/dialog/bg.png"));
		infoArea.setLayoutData(GridData.INS_FILL_BOTH);
		GridLayout gl = new GridLayout();
		gl.marginLeft = 12;
		gl.marginTop = 27;
		gl.numColumns = 2;
		infoArea.setLayout(gl);

		Label label = new Label(infoArea);
		label.setImage(CommonImages.getImage("img/dialog/sure.png"));

		label = new Label(infoArea);
		label.setID("PromptTextLabel");
		label.setText(message);
		GridData gd = new GridData();
		gd.verticalIndent = -40;
		label.setLayoutData(gd);
	}

	@Override
	protected void afterFooterRender() {
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		footerArea.setLayout(gl);
		Button button = new Button(footerArea, JWT.APPEARANCE3);
		button.setText(button1Title);
		button.setID("Button1");
		GridData gd = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END);
		gd.widthHint = 80;
		gd.heightHint = 28;
		button.setLayoutData(gd);
		button.addClientEventHandler(JWT.CLIENT_EVENT_ACTION,
				"SaasNavigator.handlePromptButton1");

		button = new Button(footerArea, JWT.APPEARANCE3);
		gd = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_END);
		gd.widthHint = 80;
		gd.heightHint = 28;
		button.setText(button2Title);
		button.setID("Button2");
		button.setLayoutData(gd);
		button.addClientEventHandler(JWT.CLIENT_EVENT_ACTION,
				"SaasNavigator.handlePromptButton2");

		button = new Button(footerArea, JWT.APPEARANCE3);
		gd = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_END);
		gd.widthHint = 80;
		gd.heightHint = 28;
		button.setText(" È¡ Ïû ");
		button.setLayoutData(gd);
		button.addClientEventHandler(JWT.CLIENT_EVENT_ACTION,
				"SaasNavigator.handlePromptButton3");
		button.forceFocus();
	}

}
