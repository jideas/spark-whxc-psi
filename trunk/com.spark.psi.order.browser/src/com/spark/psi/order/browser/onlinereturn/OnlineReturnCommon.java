package com.spark.psi.order.browser.onlinereturn;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.WindowStyle;

public class OnlineReturnCommon {
	public static MsgRequest createAdvanceRequest() {
		 PageController pc = new PageController(OnlineReturnAdvanceSearchPageProcessor.class, OnlineReturnAdvanceSearchPageRender.class);
		 PageControllerInstance pci = new PageControllerInstance(pc);
		 WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		 windowStyle.setSize(390, 150);
		 MsgRequest request = new MsgRequest(pci, "¸ß¼¶ËÑË÷", windowStyle);
		 return request;
	}
}
