/**
 * 
 */
/**
 * 
 */
package com.spark.psi.base.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Department;
import com.spark.psi.base.Login;
import com.spark.psi.base.key.organization.GetChildrenDepartmentListKey;
import com.spark.psi.base.key.organization.GetDepartmentFullPathNameKey;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.base.organization.entity.DepartmentTree;
import com.spark.psi.publish.base.organization.entity.DepartmentTree.DepartmentNode;

/**
 * ��֯�ӿ� �ⲿ�ӿڷ���
 
 *
 */
public class DepartmentPublishService extends Service {

	protected DepartmentPublishService() {
		super("com.spark.psi.base.internal.service.DepartmentPublishService");
	}

	/**
	 * ��õ�ǰ�⻧����֯�ṹ��
	 
	 *
	 */
	@Publish
	protected class GetDepartmentTreeProvider extends ResultProvider<DepartmentTree>{

		@Override
		protected DepartmentTree provide(Context context) throws Throwable {
			DepartmentTreeImpl tree = new DepartmentTreeImpl();
			DepartmentNodeImpl node = new DepartmentNodeImpl(context,context.find(Department.class,context.find(Login.class).getTenantId()),null);
			tree.setRoot(node);
			return tree;
		}
	
		public class DepartmentTreeImpl implements DepartmentTree {

			private List<DepartmentNode> list = new ArrayList<DepartmentNode>();
			
			/**
			 * ���ڵ㣨���⻧��
			 */
			protected DepartmentNode root;

			/**
			 * ��ȡ���⻧�ڵ�
			 * 
			 * @return
			 */
			public DepartmentNode getRoot() {
				return this.root;
			}
			

			public void setRoot(DepartmentNode root){
		    	this.root = root;
		    }


			public DepartmentNode getNodeById(GUID id){
				if(root.getId().equals(id))return root;
	            return getNodeById(root,id);
            }
			
			private DepartmentNode getNodeById(DepartmentNode node,GUID id){
				if(node.getId().equals(id))return node;
	            for(DepartmentNode child : node.getChildren()){
	                	DepartmentNode result = getNodeById(child,id);
	           
	                    if(result!=null){
	                    	return result;
	                    }	                    	
                }
 				return null;
            }

		}

			/**
			 * �������ڵ�
			 */
			public class DepartmentNodeImpl implements DepartmentTree.DepartmentNode {

				private Department department;
				
				private DepartmentNode parent;

				private Context context;
				/**
				 * �ӽڵ�
				 */
				protected DepartmentNode[] children;

				/**
				 * ���캯��
				 * 
				 * @param id
				 * @param name
				 * @param children
				 */
				public DepartmentNodeImpl(Context context,Department dept,DepartmentNodeImpl parent) {
					this.context = context;
					this.department = dept;
					List<Department> childList = context.getList(Department.class,new GetChildrenDepartmentListKey(dept.getId()));
					children = new DepartmentNode[childList.size()];
					for(int i=0;i<children.length;i++){
			            children[i] = new DepartmentNodeImpl(context,childList.get(i),this);
		            }
					this.parent = parent;
				}

				/**
				 * 
				 * @return
				 */
				public GUID getId() {
					return department.getId();
				}

				/**
				 * 
				 * @return
				 */
				public String getName() {
					return department.getName();
				}

				/**
				 * 
				 * @return
				 */
				public DepartmentNode[] getChildren() {
					return children;
				}			

				public boolean hasAuth(Auth... targetAuths){
					return department.hasAuth(targetAuths);
                }

				public DepartmentNode getParent() {
					return parent;
				}

				public String getFillPathName(){
	                return context.find(String.class,new GetDepartmentFullPathNameKey(getId()));
                }

			}

	}
	
	
}
