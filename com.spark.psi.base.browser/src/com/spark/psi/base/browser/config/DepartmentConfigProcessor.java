package com.spark.psi.base.browser.config;

import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.lightweight.LWTree;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.provider.TreeSourceAdapter;
import com.spark.common.components.pages.PageProcessor;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.DepartmentTreeSource;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;
import com.spark.psi.publish.base.organization.entity.DepartmentTree.DepartmentNode;

;
public class DepartmentConfigProcessor extends PageProcessor {

	public final static String ID_TREE = "Department_Tree";

	private LWTree tree;

	private TreeSourceAdapter departmentSource;

	GUID tenantId;
	
	@Override
	public void process(Situation context) {
		tenantId = context.find(LoginInfo.class).getTenantId();
		tree = this.createControl(ID_TREE, LWTree.class, JWT.NONE);
		departmentSource = new DepartmentTreeSource();
		tree.setSource(departmentSource);
		tree.setInput(null);
		DepartmentTree departmentTree = context.find(DepartmentTree.class);
		DepartmentNode root = departmentTree.getRoot(); 
		expandNode(root);
		tree.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {				
				if(isManager()&&tree.getSeleted().equals(tenantId.toString())){
					return ;
//					alert("经理角色不能创建在公司直属下！");
				}
				getContext().bubbleMessage(
						new MsgResponse(true, departmentSource
								.getElementById(tree.getSeleted())));
			}
		});
	}

	private boolean isManager(){
		Integer[] argument = (Integer[])getArgument();
		if(argument==null||argument.length==0)return false;
		for(Integer roles : argument){
			if(roles==null)return false;
			List<Auth> list = getContext().getList(Auth.class,tenantId,roles);
			if(list.contains(Auth.Boss)||list.contains(Auth.Assistant)){ //如果是总经理或这助理 返回false
				return false;
			}
			if(list.contains(Auth.AccountManager)||
					list.contains(Auth.SalesManager)||
					list.contains(Auth.PurchaseManager)||
					list.contains(Auth.StoreKeeperManager)){
				return true;
			}	        
        }
		return false;
	}
	
	private void expandNode(DepartmentNode node) {
		tree.setExpand(node, true);
		DepartmentNode[] children = node.getChildren();
		if (children != null) {
			for (DepartmentNode child : children) {
				expandNode(child);
			}
		}
	}

}
