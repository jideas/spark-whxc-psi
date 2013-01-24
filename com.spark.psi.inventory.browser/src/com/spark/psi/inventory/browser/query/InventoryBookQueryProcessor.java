package com.spark.psi.inventory.browser.query;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.base.browser.material.MaterialCategoryFramePageProcessor;
import com.spark.psi.base.browser.material.MaterialCategorySelectionMsg;

/**
 * 库存台账查询处理器
 */
public class InventoryBookQueryProcessor extends MaterialCategoryFramePageProcessor {

	private InventoryBookQueryListProcessor queryListProcessor;

	public enum Columns {
		goodsNo, goodsName, goodsAttr, goodsUnit, count_begin, amount_begin, instoCount, instoAmount, outstoCount, outstoAmount, count_end, amount_end
	}

	@Override
	public void process(final Situation context) {
		getContext().regMessageListener(MaterialCategorySelectionMsg.class,
				new MessageListener<MaterialCategorySelectionMsg>() {
					public void onMessage(Situation context, MaterialCategorySelectionMsg message,
							MessageTransmitter<MaterialCategorySelectionMsg> transmitter) {
						String storeId = null;
						String queryTerm = null;
						if (queryListProcessor != null) {
							storeId = queryListProcessor.getStoreList().getText();
							queryTerm = queryListProcessor.getTermList().getText();
						}
						ControllerPage page = (ControllerPage) rightArea.showPage(ControllerPage.NAME,
								new PageControllerInstance(new PageController(InventoryBookQueryListProcessor.class,
										InventoryBookQueryListRender.class), message.getCategoryId(), storeId,
										queryTerm));
						if (page != null) {
							queryListProcessor = (InventoryBookQueryListProcessor) page.getProcessor();
						}
					}
				});
		super.process(context);
	}

}
