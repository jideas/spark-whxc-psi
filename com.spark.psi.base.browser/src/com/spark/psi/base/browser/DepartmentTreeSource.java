package com.spark.psi.base.browser;

import com.jiuqi.dna.ui.wt.provider.TreeSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;
import com.spark.psi.publish.base.organization.entity.DepartmentTree.DepartmentNode;

/**
 * 部门树数据，可用于LWTree
 * 
 */
public class DepartmentTreeSource extends TreeSourceAdapter {

	private DepartmentTree departmentTree;

	public DepartmentTreeSource() {
		departmentTree = Display.getCurrent().getSituation()
				.find(DepartmentTree.class);
	}

	public Object[] getChildren(Object parentElement) {
		return ((DepartmentNode) parentElement).getChildren();
	}

	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		return children != null && children.length > 0;
	}

	public Object[] getElements(Object inputElement) {
		return new DepartmentNode[] { this.departmentTree.getRoot() };
	}

	public String getText(Object element) {
		return ((DepartmentNode) element).getName();
	}

	public String getElementId(Object element) {
		return ((DepartmentNode) element).getId().toString();
	}

	public Object getElementById(String id) {
		return findById(departmentTree.getRoot(), id);
	}

	public Object getParent(Object element) {
		DepartmentNode root = departmentTree.getRoot();
		DepartmentNode target = (DepartmentNode) element;
		if (target.getId().equals(root.getId())) {
			return null;
		} else {
			return findParent(root, target);
		}
	}

	public DepartmentNode findById(DepartmentNode node, String id) {
		if (node.getId().toString().equals(id)) {
			return node;
		}
		DepartmentNode[] children = node.getChildren();
		if (children != null) {
			for (DepartmentNode child : children) {
				DepartmentNode result = findById(child, id);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	public DepartmentNode findParent(DepartmentNode parent,
			DepartmentNode target) {
		DepartmentNode[] children = parent.getChildren();
		if (children != null) {
			for (DepartmentNode child : children) {
				if (child.getId().equals(target.getId())) {
					return parent;
				}
				DepartmentNode result = findParent(child, target);
				if (result != null) {
					return result;
				}
			}

		}
		return null;
	}
};
