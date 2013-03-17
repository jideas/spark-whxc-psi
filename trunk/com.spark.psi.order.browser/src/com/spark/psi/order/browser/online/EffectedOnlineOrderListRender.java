package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;

public class EffectedOnlineOrderListRender extends UnHandledOnlineOrderListRender {

	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();

		Button summaryBtn = new Button(footerRightArea, JWT.APPEARANCE3);
		summaryBtn.setText("  ¼ð »õ  ");
		summaryBtn.setID(EffectedOnlineOrderListProcessor.ID_Button_Summarizing);
		
		Button button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText("  Í³ ¼Æ  ");
		button.setID(EffectedOnlineOrderListProcessor.ID_Button_Summary);
		
//		Button button = new Button(footerLeftArea, JWT.APPEARANCE3);
//		button.setText("  Test  ");
//		button.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				table.render();
//			}
//		});
	}
}
