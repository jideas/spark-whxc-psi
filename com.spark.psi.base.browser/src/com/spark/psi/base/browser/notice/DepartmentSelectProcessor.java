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
 * <p>����ѡ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-4-23
 */

public class DepartmentSelectProcessor extends PageProcessor {
	
	/**
	 * ����
	 */
	public final static String FINISH_SELECTED = "finishSelected"; //���ѡ��
	public final static String CANCEL_SELECTED = "cancelSelected"; //����ѡ��

	/**
	 * �ؼ�ID
	 */
	public final static String ID_TREE = "Department_Tree";
	public final static String ID_FINISH_SELECTED_BUTTON = "finishButton";
	public final static String ID_CANCEL_SELECT_BUTTON = "cancelButton";

	/**
	 * �ؼ�
	 */
	private TreeViewer treeViewer;
	private Button finishButton;
	private Button cancelButton;

	/**
	 * ����
	 */
	private TreeSourceAdapter departmentSource;

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(Situation context) {
		//������
		treeViewer  = new TreeViewer( this.createControl(ID_TREE, Tree.class, JWT.NONE));
		treeViewer.getTree().addCheckListener(new TreeCheckedListener());
		treeViewer.getTree().addSelectionListener(new TreeSelectionListener());
		//���ѡ��
		finishButton = this.createControl(ID_FINISH_SELECTED_BUTTON, Button.class, JWT.NONE);
		//����ѡ��
		cancelButton = this.createControl(ID_CANCEL_SELECT_BUTTON, Button.class, JWT.NONE);
		
		//���ѡ��ť������
		finishButtonListener();
		//����ѡ��ť������
		cancelButtonListener();
	}
	
	/**
	 * ҳ���ʼ����ϣ��������ݣ�
	 * 
	 * @param context ������
	 */
	public void postProcess(Situation context) {
		//��ò�����Դ���ݣ�Ĭ��ȫ��չ��
		getDepartmentTreeSource();
		//�����Ѿ�ѡ��Ĳ���
		setSelectedDepartment();
	}
	
	/**
	 * ��ò�����Դ���ݣ�Ĭ��ȫ��չ��
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
	 * �����Ѿ�ѡ��Ĳ���
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
	 * չ�����н��
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
	 * ���ѡ��ť������
	 */
	private void finishButtonListener(){
		finishButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				Object[] selections = treeViewer.getCheckedElements();
				String deptGuidStr = "";
				String deptNameStr = "";
				if(null != selections && selections.length>0){
					DepartmentNode node = null;
					//��������ӽ��ID
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
					//ȥ����һ������
					deptGuidStr = deptGuidStr.substring(1);
					deptNameStr = deptNameStr.substring(1);
				}
				MsgResponse response = new MsgResponse(true, FINISH_SELECTED, deptNameStr, deptGuidStr);
				getContext().bubbleMessage(response);
				
			}
		});
	}
	
	/**
	 * ����ѡ��ť������
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
	 * ������ѡ��������
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
	 * ��������ѡ������
	 */
	protected class TreeCheckedListener implements CheckListener{
		/**
		 * ��ѡʱ�����÷���
		 */
		public void stateChanged(CheckEvent e){
			TreeItem treeItem = (TreeItem)e.item;
			treeViewer.getTree().removeCheckListener(this);
			//ѡ��(��ȡѡ��)�ýڵ���ӽڵ�
			selectChildren(treeItem, treeItem.getGrayed() ? true : treeItem.getChecked());
			treeItem.setGrayed(false);
			//���ø�����״̬"ֱ���ӽ��ȫ��ѡ��"��"ֱ���ӽ�㲿��ѡ��"��"ֱ���ӽ��ȫ��δѡ��"
			updateParentItemstatus(treeItem);
			treeViewer.getTree().addCheckListener(this);
        }
		
		/**
		 * �ݹ�ѡ��(��ȡ��ѡ��)ĳ��㼰�������ӽ��
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
		 * �ݹ���������丸����״̬
		 */
		private void updateParentItemstatus(TreeItem treeItem){
			if(null != treeItem && null != treeItem.getParentItem()){
				//ѡ�е��ӽ������
				int num = 0;
				//�ûҵ��ӽ������
				int grayedNum = 0;
				for(TreeItem item : treeItem.getParentItem().getItems()){
					if(item.getChecked()){
						num++;
					}else if(item.getGrayed()){
						num++;
						grayedNum++;
					}
				}
				if(0 == num){ //ȫ��δѡ��
					treeItem.getParentItem().setGrayed(false);
					treeItem.getParentItem().setChecked(false);
				}else if(num < treeItem.getParentItem().getItems().length || grayedNum>0){ //����ѡ��
					treeItem.getParentItem().setGrayed(true);
					treeItem.getParentItem().setChecked(false);
				}else{ //ȫ��ѡ��
					treeItem.getParentItem().setGrayed(false);
					treeItem.getParentItem().setChecked(true);
				}
				//�ݹ�
				updateParentItemstatus(treeItem.getParentItem());
			}
		}
	}
}
