package com.spark.psi.base.browser.material;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.lightweight.LWTree;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.spark.psi.base.browser.MaterialCategorySource;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree.CategoryNode;

public class MaterialCategoryShowTree extends LWTree {
	private MaterialsCategoryTree treeData;
	private Situation context;

	public MaterialCategoryShowTree(Composite parent) {
		super(parent, JWT.NONE);
		context = parent.getContext();
		treeData = context.find(MaterialsCategoryTree.class);
	}

	public void setInputNodeId(GUID nodeId) {
		if (null == nodeId) {
			this.setSource(new MaterialCategorySource(null));
			setInput(treeData);
			this.setExpand("", true);
			expandChildren(treeData.getRootNodes());
		} else {
			this.setSource(new MaterialCategorySource(nodeId));
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
