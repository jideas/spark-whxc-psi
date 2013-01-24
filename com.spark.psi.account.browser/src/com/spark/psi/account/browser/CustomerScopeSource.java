package com.spark.psi.account.browser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.ui.wt.provider.ListSourceAdapter;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.QueryScope;
import com.spark.psi.publish.QueryScope.ScopeType;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;

public class CustomerScopeSource extends ListSourceAdapter {

	private static final String MyScopeId = "MyScopeId010101010243";

	private LoginInfo loginInfo;
	private DepartmentTree tree;

	private String defaultId;

	private Map<String, QueryScope> allData = new HashMap<String, QueryScope>();

	public CustomerScopeSource(Context context) {
		loginInfo = context.find(LoginInfo.class);
		tree = context.find(DepartmentTree.class);
	}

	public Object[] getElements(Object inputElement) {
		List<QueryScope> list = subDeptScope();
		if (list.size() > 0) {
			if (list.get(0).getType().equals(QueryScope.ScopeType.Mine)) {
				defaultId = MyScopeId;
			} else {
				defaultId = list.get(0).getDepartmentId().toString();
			}
		}
		return subDeptScope().toArray();
	}

	public String getText(Object element) {
		QueryScope scope = (QueryScope) element;
		return scope.getName();
	}

	public Object getElementById(String id) {
		return allData.get(id);
	}

	public String getElementId(Object element) {
		QueryScope scope = (QueryScope) element;
		if (scope.getType().equals(QueryScope.ScopeType.Mine)) {
			return MyScopeId;
		}
		return scope.getDepartmentId().toString();
	}

	private List<QueryScope> subDeptScope() {
		List<QueryScope> list = new ArrayList<QueryScope>();

		if (loginInfo.hasAuth(Auth.SalesManager)) {
			QueryScope totalScope = new QueryScope("全部客户", loginInfo
					.getEmployeeInfo().getDepartmentId(),ScopeType.All);
			list.add(totalScope);
			allData.put(loginInfo.getEmployeeInfo().getDepartmentId()
					.toString(), totalScope);
		} else if (loginInfo.hasAuth(Auth.Boss)
				|| loginInfo.hasAuth(Auth.Account)) {
			QueryScope totalScope = new QueryScope("全部客户", loginInfo
					.getTenantId(),ScopeType.All);
			list.add(totalScope);
			allData.put(loginInfo.getTenantId().toString(), totalScope);
		}
		if (loginInfo.hasAuth(Auth.Boss)
				|| loginInfo.hasAuth(Auth.SalesManager)) {
			QueryScope myScope = new QueryScope("我的客户");
			list.add(myScope);
			allData.put(MyScopeId, myScope);
		}

		DepartmentTree.DepartmentNode node;
		if (loginInfo.hasAuth(Auth.Boss) || loginInfo.hasAuth(Auth.Account)) {
			node = tree.getNodeById(loginInfo.getTenantId());
		} else {
			node = tree.getNodeById(loginInfo.getEmployeeInfo()
					.getDepartmentId());
		}
		if (loginInfo.hasAuth(Auth.Boss)
				|| loginInfo.hasAuth(Auth.SalesManager)
				|| loginInfo.hasAuth(Auth.Account)) {
			for (DepartmentTree.DepartmentNode child : node.getChildren()) {
				if (child.hasAuth(Auth.Sales)) {
					QueryScope cqs = new QueryScope(child.getName(), child
							.getId());
					list.add(cqs);
					allData.put(child.getId().toString(), cqs);
				}
			}
		}
		return list;
	}

	public QueryScope getQueryScopeById(String id) {
		if (id == null)
			return null;
		return allData.get(id);
	}

	public String getDefaultId() {
		return defaultId;
	}
	
}
