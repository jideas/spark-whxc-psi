package com.spark.psi.publish.base.organization.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 * ��֯�ṹ�� <br>
 * (1)��ѯ������ֱ�Ӳ�ѯDepartmentTree����
 */
public interface DepartmentTree {

	public DepartmentNode getNodeById(GUID id);
	

	/**
	 * ��ȡ���⻧�ڵ�
	 * 
	 * @return
	 */
	public DepartmentNode getRoot();

	/**
	 * �������ڵ�
	 */
	public interface DepartmentNode {

		/**
		 * 
		 * @return
		 */
		public GUID getId();

		/**
		 * 
		 * @return
		 */
		public String getName();
		
		/**
		 * ���ȫ·����������
		 * 
		 * @return String
		 */
		public String getFillPathName();

		/**
		 * 
		 * @return
		 */
		public DepartmentNode[] getChildren();

		/**
		 * 
		 * @return
		 */
		public DepartmentNode getParent();

		/**
		 * �Ƿ�ӵ��ָ��ְ���е�һ��
		 * 
		 * @param auths
		 * @return boolean
		 */
		public boolean hasAuth(Auth... auths);
	}
}
