package com.spark.psi.publish.base.goods.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ������<br>
 * ��ѯ˵����<br>
 * (1)ֱ�Ӳ�ѯ���޼�ֵ
 */
public interface GoodsCategoryTree {
	/**
	 * ��ȡ���������Եķ�������
	 * 
	 * @return
	 */
	public int getPropertiedCount();

	/**
	 * ��ȡ���з�������
	 * 
	 * @return
	 */
	public int getCount();

	/**
	 * ��ȡ���ڵ�
	 * 
	 * @return
	 */
	public CategoryNode[] getRootNodes();

	/**
	 * ͨ������ID ��ýڵ�
	 * 
	 * @param id
	 * @return CategoryNode
	 */
	public CategoryNode getNodeById(GUID id);

	/**
	 * ����ڵ�
	 */
	public interface CategoryNode {

		/**
		 * ��ȡ����ID
		 * 
		 * @return
		 */
		public GUID getId();

		/**
		 * ��ȡ��������
		 * 
		 * @return
		 */
		public String getName();
		
		/**
		 * 
		 * @return
		 */
		public String getCategoryNo();

		/**
		 * ��ȡ���ڵ�
		 * 
		 * @return
		 */
		public CategoryNode getParent();

		/**
		 * ��ȡ�ӷ���ڵ�
		 * 
		 * @return
		 */
		public CategoryNode[] getChildren();
		
		/**
		 * �Ƿ�����������
		 * �����ã�true
		 * 
		 * @return boolean
		 */
		public boolean isSetPropertyFlag();
	}
}
