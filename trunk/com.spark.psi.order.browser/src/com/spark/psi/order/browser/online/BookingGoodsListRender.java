package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.widgets.Button;

public class BookingGoodsListRender extends UnHandledOnlineOrderListRender {
	@Override
	protected void afterFooterRender() {
		super.afterFooterRender();

		Button button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText(" ���ɲɹ����� ");
		button.setID(BookingGoodsListProcessor.ID_Button_NoticePurchase);
		
		button = new Button(footerRightArea, JWT.APPEARANCE3);
		button.setText("  �� ��  ");
		button.setID(BookingGoodsListProcessor.ID_Button_Summarizing);
	}
}
