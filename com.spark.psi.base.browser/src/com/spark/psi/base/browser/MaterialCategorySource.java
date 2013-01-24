package com.spark.psi.base.browser;

import org.json.JSONException;
import org.json.JSONObject;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;
import com.jiuqi.dna.ui.wt.provider.TreeSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.spark.psi.base.browser.internal.BaseImages;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree;
import com.spark.psi.publish.base.materials.entity.MaterialsCategoryTree.CategoryNode;

public class MaterialCategorySource extends TreeSourceAdapter {

	private MaterialsCategoryTree treeData;

	private GUID rootNodeId;

	public MaterialCategorySource() {
		this(null);
	}

	public MaterialCategorySource(GUID rootNodeId) {
		treeData = Display.getCurrent().getSituation()
				.find(MaterialsCategoryTree.class);
		this.rootNodeId = rootNodeId;
	}

	@Override
	public ImageDescriptor getImage(Object element) {
		if(element instanceof CategoryNode && ((CategoryNode)element).isSetPropertyFlag()) {
			return BaseImages
				.getImage("images/goods/ico_properties.png");
		} else {
			return super.getImage(element);
		}
	}

	public String getElementId(Object element) {
		if (element instanceof CategoryNode) {
			if(((CategoryNode) element).getId().equals(rootNodeId)) {
				return "";
			} else {
				return (((CategoryNode) element).getId()).toString();
			}
		} else if (element instanceof MaterialsCategoryTree) {
			return "";
		} else {
			return null;
		}
	}

	public Object getElementById(String id) {
		if (null == id) {
			return null;
		} else if (id.equals("")) {
			if(rootNodeId != null) return treeData.getNodeById(rootNodeId);
			return treeData;
		} else {
			return treeData.getNodeById(GUID.tryValueOf(id));
		}
	}

	public String getText(Object element) {
		if (element instanceof CategoryNode) {
			return ((CategoryNode) element).getName();
		} else if (element instanceof MaterialsCategoryTree) {
			return "全部";
		} else {
			return null;
		}
	}

	public Object[] getElements(Object inputElement) {
		return new Object[] { inputElement };
	}

	public boolean hasChildren(Object element) {
		Object[] children = this.getChildren(element);
		return children != null && children.length > 0;
	}

	public Object getParent(Object element) {
		if (element instanceof MaterialsCategoryTree
				|| (((CategoryNode) element).getId()).equals(rootNodeId)) {
			return null;
		} else if (element instanceof CategoryNode) {
			CategoryNode node = (CategoryNode) element;
			CategoryNode parent = node.getParent();
			return parent == null ? treeData : parent;
		}
		return null;
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof MaterialsCategoryTree) {
			return treeData.getRootNodes();
		} else if (parentElement instanceof CategoryNode) {
			return ((CategoryNode) parentElement).getChildren();
		}
		return null;
	}

	/**
	 * 设置只有叶子节点才可以选择
	 */
	public JSONObject getClientData(Object element) {
		if (element instanceof CategoryNode && !hasChildren(element)) {
			return null;
		}
		try {
			return new JSONObject().put(JWT.CLIENT_DATA_KEY_SELECT_DISABLED,
					true);
		} catch (JSONException e) {
		}
		return null;
	}

}
