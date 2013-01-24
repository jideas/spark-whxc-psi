package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;

/**
 * 停售商品列表视图
 */
public class OffSaleGoodsListRender extends GoodsCategoryFramePageRender {

	protected void doRender() {
		super.doRender();
		getContext().regMessageListener(GoodsCategorySelectionMsg.class,
				new MessageListener<GoodsCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							GoodsCategorySelectionMsg message,
							MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
						rightArea
								.showPage(
										ControllerPage.NAME,
										new PageControllerInstance(
												new PageController(
														GoodsListOffSaleProcessor.class,
														GoodsListOffSaleRender.class),
												message.getCategoryId()));
					}
				});
	}
}
