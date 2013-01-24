package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree.CategoryNode;

/**
 * 商品分类编辑界面视图
 */
public class GoodsCategoryEditRender extends GoodsCategoryFramePageRender {

	@Override
	protected void doRender() {
		super.doRender();

		rightArea.clear();
		rightArea.setLayout(new FillLayout());

		this.getContext().regMessageListener(GoodsCategorySelectionMsg.class,
				new MessageListener<GoodsCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							GoodsCategorySelectionMsg message,
							MessageTransmitter<GoodsCategorySelectionMsg> transmitter) {
						rightArea.clear();
						GoodsCategoryTree treeData = getContext().find(
								GoodsCategoryTree.class);
						CategoryNode node = treeData.getNodeById(message
								.getCategoryId());
						if (node == null || hasSetPropertyFlag(node.getChildren())) { // 显示以当前节点为根节点的树
							new GoodsCategoryTreePage(rightArea, node);
							rightArea.layout();
						} else {
							CategoryNode propertiedParentNode = getPropertiedParent(node);
							PageController pc = new PageController(
									GoodsCategoryDetailProcessor.class,
									GoodsCategoryDetailRender.class);
							rightArea
									.showPage(
											ControllerPage.NAME,
											new PageControllerInstance(
													pc,
													new GUID[] {
															node.getId(),
															propertiedParentNode != null ? propertiedParentNode
																	.getId()
																	: null }));
						}
					}
				});
	}

	protected CategoryNode getPropertiedParent(CategoryNode node) {
		CategoryNode parentNode = node.getParent();
		while (parentNode != null) {
			if (parentNode.isSetPropertyFlag()) {
				break;
			}
			parentNode = parentNode.getParent();
		}
		return parentNode;
	}

	protected boolean hasSetPropertyFlag(CategoryNode[] nodes) {
		for (CategoryNode node : nodes) {
			if (node.isSetPropertyFlag() || hasSetPropertyFlag(node.getChildren())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean isShowAdd() {
		return true;
	}

}
