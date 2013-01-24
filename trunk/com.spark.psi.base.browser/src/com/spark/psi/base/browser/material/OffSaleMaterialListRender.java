package com.spark.psi.base.browser.material;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.publish.MaterialsStatus;

/**
 * 停售商品列表视图
 */
public class OffSaleMaterialListRender extends MaterialCategoryFramePageRender {

	protected void doRender() {
		super.doRender();
		getContext().regMessageListener(MaterialCategorySelectionMsg.class,
				new MessageListener<MaterialCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							MaterialCategorySelectionMsg message,
							MessageTransmitter<MaterialCategorySelectionMsg> transmitter) {
						rightArea
								.showPage(
										ControllerPage.NAME,
										new PageControllerInstance(
												new PageController(
														MaterialListPageProcessor.class,
														MaterialListPageRender.class),
												message.getCategoryId(), MaterialsStatus.STOP_SALE));
					}
				});
	}
}
