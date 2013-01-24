package com.spark.psi.base.browser.material;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.spark.common.components.pages.ControllerPage;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree.CategoryNode;

public class MaterialCategoryEditRender extends MaterialCategoryFramePageRender {
	@Override
	protected void doRender() {
		super.doRender();

		rightArea.clear();
		rightArea.setLayout(new FillLayout());

		this.getContext().regMessageListener(MaterialCategorySelectionMsg.class,
				new MessageListener<MaterialCategorySelectionMsg>() {
					public void onMessage(
							Situation context,
							MaterialCategorySelectionMsg message,
							MessageTransmitter<MaterialCategorySelectionMsg> transmitter) {
						rightArea.clear();
						MaterialsCategoryTree treeData = getContext().find(
								MaterialsCategoryTree.class);
						CategoryNode node = treeData.getNodeById(message
								.getCategoryId());
						if (node == null || hasSetPropertyFlag(node.getChildren())) { // 显示以当前节点为根节点的树
							new MaterialCategoryTreePage(rightArea, node);
							rightArea.layout();
						} else {
							CategoryNode propertiedParentNode = getPropertiedParent(node);
							PageController pc = new PageController(
									MaterialCategoryDetailProcessor.class,
									MaterialCategoryDetailRender.class);
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
