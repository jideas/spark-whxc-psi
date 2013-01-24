package com.spark.portal.browser;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.spark.common.components.pages.AbstractBoxPageRender;

public class ErrorPageRender extends AbstractBoxPageRender {

	@Override
	protected void beforeFooterRender() {
		Text text = new Text(contentArea, JWT.APPEARANCE3 | JWT.MULTI
				| JWT.H_SCROLL | JWT.V_SCROLL);
		text.setLayoutData(GridData.INS_FILL_BOTH);
		text.setEditable(false);
		text.setForeground(Color.COLOR_BROWN);
		Throwable t = (Throwable) this.getArgument();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		text.setText(sw.toString());
	}

	@Override
	protected void afterFooterRender() {
		GridLayout gl = new GridLayout();
		footerArea.setLayout(gl);
		Button button = new Button(footerArea, JWT.APPEARANCE3);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContext().bubbleMessage(new MsgCancel());
			}
		});
		button.setText("  È· ¶¨  ");
		GridData gd = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END);
		gd.heightHint = 29;
		button.setLayoutData(gd);
	}

}
