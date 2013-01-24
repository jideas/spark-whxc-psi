package com.spark.psi.base.browser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.jiuqi.dna.ui.wt.widgets.Display;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.QueryScope.ScopeType;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;

public class QueryScopeSource extends ListSourceAdapter {

	private String myItemName;

	private String allItemName = "¹«Ë¾";

	private DepartmentTree tree;

	private Situation context;

	private String first;

	private String empDept;

	private Auth[] auths;

	private boolean isShowAll = false;

	private Map<String, QueryScope> data = new HashMap<String, QueryScope>();

	public QueryScopeSource(String myItemName, Auth... auths) {
		this.myItemName = myItemName;
		context = Display.getCurrent().getSituation();
		tree = context.find(DepartmentTree.class);
		this.auths = auths;
	}

	public QueryScopeSource(String myItemName, String allItemName, boolean isShowAll) {
		this(myItemName, allItemName);
		this.isShowAll = isShowAll;
	}

	public QueryScopeSource(String myItemName, String allItemName, Auth... auths) {
		this.myItemName = myItemName;
		context = Display.getCurrent().getSituation();
		tree = context.find(DepartmentTree.class);
		this.auths = auths;
		this.allItemName = allItemName;
	}

	public Object[] getElements(Object inputElement) {
		LoginInfo login = context.find(LoginInfo.class);
		GUID rootDept;
		if (isShowAll) {
			rootDept = login.getTenantId();
		} else {
			rootDept = login.getEmployeeInfo().getDepartmentId();
		}
		DepartmentTree.DepartmentNode node = tree.getNodeById(rootDept);
		if (node == null)
			return new QueryScope[0];
		List<QueryScope> result = new ArrayList<QueryScope>();
		QueryScope myQs = new QueryScope(myItemName);
		empDept = node.equals(tree.getRoot()) ? allItemName : node.getName();
		if (login.hasAuth(Auth.Boss, Auth.Account, Auth.SalesManager, Auth.PurchaseManager, Auth.StoreKeeperManager)
				|| isShowAll) {
			QueryScope qs = new QueryScope(empDept, node.getId());
			result.add(qs);
			data.put(node.getId().toString(), qs);
			first = node.getId().toString();
		} else {
			first = GUID.emptyID.toString();
		}
		result.add(myQs);
		data.put(GUID.emptyID.toString(), myQs);
		for (DepartmentTree.DepartmentNode child : node.getChildren()) {
			if (child.hasAuth(auths) || auths == null) {
				QueryScope cqs = new QueryScope(child.getName(), child.getId());
				result.add(cqs);
				data.put(child.getId().toString(), cqs);
			}
		}
		return result.toArray(new QueryScope[0]);
	}

	public String getText(Object element) {
		return ((QueryScope) element).getName();
	}

	public Object getElementById(String id) {
		if (GUID.emptyID.equals(GUID.valueOf(id))) {
			return new QueryScope(myItemName);
		}
		DepartmentTree.DepartmentNode node = tree.getNodeById(GUID.valueOf(id));
		if (id.equals(first)) {
			return new QueryScope(empDept, node.getId(), ScopeType.All);
		}
		return new QueryScope(node.getName(), node.getId());
	}

	public QueryScope getQueryScope(String id) {
		return data.get(id);
	}

	public String getElementId(Object element) {
		GUID deptid = ((QueryScope) element).getDepartmentId();
		if (deptid != null)
			return deptid.toString();
		return GUID.emptyID.toString();
	}

	public String getFirst() {
		return first;
	}

}