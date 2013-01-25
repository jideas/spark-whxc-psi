package com.spark.psi.order.browser.online;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.WindowStyle;

public class OnlineCommon {
	public static MsgRequest createAdvanceRequest() {
		 PageController pc = new PageController(AdvanceSearchPageProcessor.class, AdvanceSearchPageRender.class);
		 PageControllerInstance pci = new PageControllerInstance(pc);
		 WindowStyle windowStyle = new WindowStyle(JWT.MODAL | JWT.CLOSE);
		 windowStyle.setSize(390, 200);
		 MsgRequest request = new MsgRequest(pci, "¸ß¼¶ËÑË÷", windowStyle);
		 return request;
	}
}
