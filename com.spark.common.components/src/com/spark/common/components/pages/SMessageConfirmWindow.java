package com.spark.common.components.pages;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;

public class SMessageConfirmWindow extends SMessageWindow {

	private Runnable runnable;
	private Runnable cancelRunnable;

	public SMessageConfirmWindow(String message, Runnable runnable) {
		super();
		this.setTitle(" 确认信息");
		imageLabel.setImage(sureImage);
		textLabel.setText(message);
		this.runnable = runnable;
	}
	
	public SMessageConfirmWindow(String message, Runnable runnable, Runnable cancelRunnable) {
		super();
		this.setTitle(" 确认信息");
		imageLabel.setImage(sureImage);
		textLabel.setText(message);
		this.runnable = runnable;
		this.cancelRunnable = cancelRunnable;
	}
	
	@Override
	protected void createButton(Composite buttonArea) {
		Button button1 = new Button(buttonArea, JWT.APPEARANCE3);
		Button button2 = new Button(buttonArea, JWT.APPEARANCE3);
		button1.setText("确定");
		button2.setText("取消");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				if (runnable != null) {
					runnable.run();
				}
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				if (cancelRunnable != null) {
					cancelRunnable.run();
				}
			}
		});
	}

}
