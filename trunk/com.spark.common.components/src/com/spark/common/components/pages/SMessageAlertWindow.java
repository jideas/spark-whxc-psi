package com.spark.common.components.pages;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;

public class SMessageAlertWindow extends SMessageWindow {

	private Runnable runnable;

	public SMessageAlertWindow(boolean alert, String message, Runnable runnable) {
		super();
		this.setTitle(" 提示信息");
		if (alert) {
			imageLabel.setImage(errorImage);
		} else {
			imageLabel.setImage(infoImage);
		}
		textLabel.setText(message);
		this.runnable = runnable;
	}

	@Override
	protected void createButton(Composite buttonArea) {
		Button button = new Button(buttonArea, JWT.APPEARANCE3);
		button.setText("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				if (runnable != null) {
					runnable.run();
				}
			}
		});
	}
}
