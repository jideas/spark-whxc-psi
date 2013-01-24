package com.spark.psi.base.browser.notice;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.CheckEvent;
import com.jiuqi.dna.ui.wt.events.CheckListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.provider.TreeSourceAdapter;
import com.jiuqi.dna.ui.wt.viewers.TreeViewer;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Tree;
import com.jiuqi.dna.ui.wt.widgets.TreeItem;
import com.spark.common.components.pages.PageProcessor;
import com.spark.common.utils.character.StringHelper;
import com.spark.portal.browser.MsgResponse;
import com.spark.psi.base.browser.DepartmentTreeSource;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;
import com.spark.psi.publish.base.organization.entity.DepartmentTree.DepartmentNode;

/**
 * <p>部门选择处理器</p>
 *
 * <p>Copyright: 版权所有 (c) 20012 - 20018<br>

 *
 * @author 刘青
 * @version 2012-4-23
 */

public class DepartmentSelectProcessor extends PageProcessor {
	
	/**
	 * 类型
	 */
	public final static String FINISH_SELECTED = "finishSelected"; //完成选择
	public final static String CANCEL_SELECTED = "cancelSelected"; //放弃选择

	/**
	 * 控件ID
	 */
	public final static String ID_TREE = "Department_Tree";
	public final static String ID_FINISH_SELECTED_BUTTON = "finishButton";
	public final static String ID_CANCEL_SELECT_BUTTON = "cancelButton";

	/**
	 * 控件
	 */
	private TreeViewer treeViewer;
	private Button finishButton;
	private Button cancelButton;

	/**
	 * 部门
	 */
	private TreeSourceAdapter departmentSource;

	/**
	 * 页面初始化
	 */
	@Override
	public void process(Situation context) {
		//部门树
		treeViewer  = new TreeViewer( this.createControl(ID_TREE, Tree.class, JWT.NONE));
		treeViewer.getTree().addCheckListener(new TreeCheckedListener());
		treeViewer.getTree().addSelectionListener(new TreeSelectionListener());
		//完成选择
		finishButton = this.createControl(ID_FINISH_SELECTED_BUTTON, Button.class, JWT.NONE);
		//放弃选择
		cancelButton = this.createControl(ID_CANCEL_SELECT_BUTTON, Button.class, JWT.NONE);
		
		//完成选择按钮监听器
		finishButtonListener();
		//放弃选择按钮监听器
		cancelButtonListener();
	}
	
	/**
	 * 页面初始化完毕（加载数据）
	 * 
	 * @param context 上下文
	 */
	public void postProcess(Situation context) {
		//获得部门树源数据，默认全部展开
		getDepartmentTreeSource();
		//设置已经选择的部门
		setSelectedDepartment();
	}
	
	/**
	 * 获得部门树源数据，默认全部展开
	 */
	private void getDepartmentTreeSource(){
		departmentSource = new DepartmentTreeSource();
		treeViewer.setContentProvider(departmentSource);
		treeViewer.setLabelProvider(departmentSource);
		treeViewer.setInput(null);
		DepartmentTree departmentTree = getContext().find(DepartmentTree.class);
		DepartmentNode root = departmentTree.getRoot();
		expandNode(root);
	}
	
	/**
	 * 设置已经选择的部门
	 */
	private void setSelectedDepartment(){
		if(StringHelper.isEmpty(getArgument())){
			return;
		}
		String deptGuidStr = getArgument().toString();
		String[] deptGuids = deptGuidStr.split(",");
		if(null != deptGuids && deptGuids.length>0){
			Object element = null;
			for(String guidStr : deptGuids){
				element = departmentSource.getElementById(guidStr);
				treeViewer.setChecked(element, true);
			}
		}
	}

	/**
	 * 展开所有结点
	 */
	private void expandNode(DepartmentNode node) {
		treeViewer.setExpandedState(node, true);
		DepartmentNode[] children = node.getChildren();
		if (children != null) {
			for (DepartmentNode child : children) {
				expandNode(child);
			}
		}
	}
	
	/**
	 * 完成选择按钮监听器
	 */
	private void finishButtonListener(){
		finishButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				Object[] selections = treeViewer.getCheckedElements();
				String deptGuidStr = "";
				String deptNameStr = "";
				if(null != selections && selections.length>0){
					DepartmentNode node = null;
					//获得所有子结点ID
					List<GUID> childrenGuidList = new ArrayList<GUID>();
					for(int i = 0 ;i < selections.length;i++){
						node = (DepartmentNode)selections[i]; 
						if(null != node.getChildren() && node.getChildren().length>0){
							for(DepartmentNode item : node.getChildren()){
								childrenGuidList.add(item.getId());
							}
						}
					}
					for(int i = 0 ;i < selections.length;i++){
						node = (DepartmentNode)selections[i]; 
						deptGuidStr += "," + node.getId();
						if(!childrenGuidList.contains(node.getId())){
							deptNameStr += "," + node.getName();
						}
					}
					//去掉第一个逗号
					deptGuidStr = deptGuidStr.substring(1);
					deptNameStr = deptNameStr.substring(1);
				}
				MsgResponse response = new MsgResponse(true, FINISH_SELECTED, deptNameStr, deptGuidStr);
				getContext().bubbleMessage(response);
				
			}
		});
	}
	
	/**
	 * 放弃选择按钮监听器
	 */
	private void cancelButtonListener(){
		cancelButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				MsgResponse response = new MsgResponse(true, CANCEL_SELECTED);
				getContext().bubbleMessage(response);
			}
		});
	}
	
	/**
	 * 部门树选择侦听器
	 */
	protected class TreeSelectionListener implements SelectionListener{

		public void widgetSelected(SelectionEvent e){
			Tree tree = (Tree)e.widget;
			if(tree.getSelection() != null && tree.getSelection().length>0){
				for(TreeItem treeItem : tree.getSelection()){
					treeItem.setChecked(!treeItem.getChecked());
				}
			}
        }
	}
	
	/**
	 * 部门树勾选侦听器
	 */
	protected class TreeCheckedListener implements CheckListener{
		/**
		 * 勾选时触发该方法
		 */
		public void stateChanged(CheckEvent e){
			TreeItem treeItem = (TreeItem)e.item;
			treeViewer.getTree().removeCheckListener(this);
			//选择(或取选择)该节点的子节点
			selectChildren(treeItem, treeItem.getGrayed() ? true : treeItem.getChecked());
			treeItem.setGrayed(false);
			//设置父结点的状态"直接子结点全部选中"、"直接子结点部分选中"和"直接子结点全部未选中"
			updateParentItemstatus(treeItem);
			treeViewer.getTree().addCheckListener(this);
        }
		
		/**
		 * 递归选择(或取消选择)某结点及其所有子结点
		 */
		private void selectChildren(TreeItem treeItem, boolean isSelected){
			if(null != treeItem){ 
				treeItem.setChecked(isSelected);
				treeItem.setGrayed(false);
				if(null != treeItem.getItems() && treeItem.getItems().length>0){
					for(TreeItem item : treeItem.getItems()){
						selectChildren(item, isSelected);
					}
				}
			}
		}
		/**
		 * 递归遍历更新其父结点的状态
		 */
		private void updateParentItemstatus(TreeItem treeItem){
			if(null != treeItem && null != treeItem.getParentItem()){
				//选中的子结点数量
				int num = 0;
				//置灰的子结点数量
				int grayedNum = 0;
				for(TreeItem item : treeItem.getParentItem().getItems()){
					if(item.getChecked()){
						num++;
					}else if(item.getGrayed()){
						num++;
						grayedNum++;
					}
				}
				if(0 == num){ //全部未选中
					treeItem.getParentItem().setGrayed(false);
					treeItem.getParentItem().setChecked(false);
				}else if(num < treeItem.getParentItem().getItems().length || grayedNum>0){ //部分选中
					treeItem.getParentItem().setGrayed(true);
					treeItem.getParentItem().setChecked(false);
				}else{ //全部选中
					treeItem.getParentItem().setGrayed(false);
					treeItem.getParentItem().setChecked(true);
				}
				//递归
				updateParentItemstatus(treeItem.getParentItem());
			}
		}
	}
}
