package com.spark.portal.browser;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.CommonImages;
import com.spark.common.components.pages.AbstractBoxPageRender;

public class ConfirmPageRender extends AbstractBoxPageRender {

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
		label.setImage(CommonImages.getImage("img/dialog/info.png"));

		label = new Label(infoArea);
		label.setText((String) this.getArgument());
		GridData gd = new GridData();
		gd.verticalIndent = -40;
		label.setLayoutData(gd);
	}

	@Override
	protected void afterFooterRender() {
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		footerArea.setLayout(gl);
		Button button = new Button(footerArea, JWT.APPEARANCE3);
		button.setText(" 确 定 ");
		GridData gd = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END);
		gd.widthHint = 80;
		gd.heightHint = 28;
		button.setLayoutData(gd);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgResponse(true));
			}
		});

		button = new Button(footerArea, JWT.APPEARANCE3);
		gd = new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_END);
		gd.widthHint = 80;
		gd.heightHint = 28;
		button.setText(" 取 消 ");
		button.setLayoutData(gd);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgCancel());
			}
		});
		button.forceFocus();
	}

}
