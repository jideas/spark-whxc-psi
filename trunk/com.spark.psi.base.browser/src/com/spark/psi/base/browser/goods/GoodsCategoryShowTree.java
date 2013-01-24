/**
 * 
 */
package com.spark.psi.base.browser.goods;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.lightweight.LWTree;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.psi.base.browser.GoodsCategorySource;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree;
import com.spark.psi.publish.base.goods.entity.GoodsCategoryTree.CategoryNode;

/**
 * 
 *
 */
public class GoodsCategoryShowTree extends LWTree {
	private GoodsCategoryTree treeData;
	private Situation context;

	public GoodsCategoryShowTree(Composite parent) {
		super(parent, JWT.NONE);
		context = parent.getContext();
		treeData = context.find(GoodsCategoryTree.class);
	}

	public void setInputNodeId(GUID nodeId) {
		if (null == nodeId) {
			this.setSource(new GoodsCategorySource(null));
			setInput(treeData);
			this.setExpand("", true);
			expandChildren(treeData.getRootNodes());
		} else {
			this.setSource(new GoodsCategorySource(nodeId));
			setInput(treeData.getNodeById(nodeId));
			this.setExpand("", true);
			expandChildren(treeData.getNodeById(nodeId).getChildren());
		}

	}

	private void expandChildren(CategoryNode[] nodes) {
		if (nodes != null) {
			for (CategoryNode node : nodes) {
				this.setExpand(node, true);
				expandChildren(node.getChildren());
			}
		}
	}
}
