package com.spark.psi.base.browser.bom;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.base.browser.goods.GoodsCategoryFramePageRender;
import com.spark.psi.base.browser.goods.GoodsCategorySelectionMsg;

public class BOM_MainListRender extends GoodsCategoryFramePageRender {

	protected void doRender() {
		super.doRender();
		getContext().regMessageListener(GoodsCategorySelectionMsg.class, new MessageListener<GoodsCategorySelectionMsg>() {
			public void onMessage(Situation context, GoodsCategorySelectionMsg message,
					MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
				rightArea.showPage(ControllerPage.NAME, new PageControllerInstance(new PageController(
						BOM_GoodsItemListProcessor.class, BOM_GoodsItemListRender.class), message.getCategoryId()));
			}
		});
	}
}
