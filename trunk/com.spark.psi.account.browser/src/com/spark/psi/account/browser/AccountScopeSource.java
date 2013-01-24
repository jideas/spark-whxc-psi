package com.spark.psi.account.browser;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;
import com.spark.psi.publish.base.organization.entity.DepartmentTree.DepartmentNode;

public class AccountScopeSource extends ListSourceAdapter {

	private DepartmentTree deptTree;
	
	public AccountScopeSource(Situation context) {
		deptTree = context.find(DepartmentTree.class);
	}

	public Object[] getElements(Object inputElement) {
		List<DepartmentNode> resultList = new ArrayList<DepartmentNode>();
		addSaleDeptNods(deptTree.getRoot(), resultList);
		return resultList.toArray();
	}

	public String getText(Object element) {
		DepartmentNode node = (DepartmentNode)element;
		return node.getName();
	}

	public Object getElementById(String id) {
		return deptTree.getNodeById(GUID.tryValueOf(id));
	}

	public String getElementId(Object element) {
		DepartmentNode node = (DepartmentNode)element;
		return node.getId().toString();
	}
	
	private void addSaleDeptNods(DepartmentNode node, List<DepartmentNode> resultList) {
		if(node.hasAuth(Auth.Sales)) {
			resultList.add(node);
		}
		for(DepartmentNode nodeTemp : node.getChildren()) {
			addSaleDeptNods(nodeTemp, resultList);
		}
	}
	
	public DepartmentTree getDeptTree() {
		return deptTree;
	}

}
