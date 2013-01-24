package com.spark.psi.base.browser.material;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.publish.MaterialsStatus;

/**
 * 在售商品列表视图
 */
public class OnSaleMaterialListRender extends MaterialCategoryFramePageRender {

	protected void doRender() {
		super.doRender();
		Button editCategoryButton = new Button(leftFooterArea, JWT.APPEARANCE3);
		GridData gd = new GridData(GridData.GRAB_VERTICAL
				| GridData.VERTICAL_ALIGN_END);
		gd.heightHint = 29;
		editCategoryButton.setLayoutData(gd);
		editCategoryButton
				.setID(OnSaleMaterialListProcessor.ID_Button_EditCategory);
		editCategoryButton.setText(" 管理分类 ");
		getContext().regMessageListener(MaterialCategorySelectionMsg.class,
				new MessageListener<MaterialCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							MaterialCategorySelectionMsg message,
							MessageTransmitter<MaterialCategorySelectionMsg> transmitter) {
						rightArea.showPage(
								ControllerPage.NAME,
								new PageControllerInstance(new PageController(
										MaterialListPageProcessor.class,
										MaterialListPageRender.class), message
										.getCategoryId(), MaterialsStatus.ON_SALE));
					}
				});
	}
}
