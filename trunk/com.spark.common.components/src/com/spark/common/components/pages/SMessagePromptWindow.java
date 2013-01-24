package com.spark.common.components.pages;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;

public class SMessagePromptWindow extends SMessageWindow {

	private Button button1;
	private Button button2;
	private Button button3;

	public SMessagePromptWindow(String message, String buttonTitle1,
			String buttonTitle2) {
		super();
		this.setTitle(" 提示");
		this.setSize(400, 200);
		imageLabel.setImage(sureImage);
		//
		this.addClientEventHandler(JWT.CLIENT_EVENT_WINDOW,
				"SaasNavigator.handlePromptWindowClose");
		this.setID("SystemPromptWindow");

		//
		textLabel.setText(message);
		textLabel.setID("PromptTextLabel");
		button1.setText(buttonTitle1);
		button2.setText(buttonTitle2);
		button3.setText("取消");
		button1.setID("Button1");
		button2.setID("Button2");
		button3.setID("Button3");
		button1.addClientEventHandler(JWT.CLIENT_EVENT_ACTION,
				"SaasNavigator.handlePromptButton1");
		button2.addClientEventHandler(JWT.CLIENT_EVENT_ACTION,
				"SaasNavigator.handlePromptButton2");
		button3.addClientEventHandler(JWT.CLIENT_EVENT_ACTION,
				"SaasNavigator.handlePromptButton3");
	}

	@Override
	protected void createButton(Composite buttonArea) {
		button1 = new Button(buttonArea, JWT.APPEARANCE3);
		button2 = new Button(buttonArea, JWT.APPEARANCE3);
		button3 = new Button(buttonArea, JWT.APPEARANCE3);
	}

}
