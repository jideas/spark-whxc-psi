package com.spark.psi.base;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.entity.ILevelTree;
import com.spark.psi.base.internal.entity.MaterialsCategoryImpl;

/**
 * ��Ʒ����<br>
 * ��ѯ������������Ʒ����IDֱ�Ӳ�ѯMaterialsCategory����
 */
public interface MaterialsCategory extends ILevelTree {
	
	/**
	 * ���ڵ�
	 */
	public static final MaterialsCategory ROOT = new MaterialsCategoryImpl(GUID.valueOf("10000000000000000000000000000001"),"ȫ��"); 
	

	/**
	 * @return the id
	 */
	public GUID getId();

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * С����λ��
	 * @return the amountDecimal
	 */
	public int getScale();

	/**
	 * ������
	 * @return the inventoryUpperLimit
	 */
	public String getCategoryNo();
	
	/**
	 * ����������б�
	 * 
	 * @return GoodsCategory[]
	 */
	public MaterialsCategory[] getChildren(final Context context);
	
	/**
	 * �������Ҷ�ӽڵ������б�
	 * 
	 * @return GoodsCategory[]
	 */
	public MaterialsCategory[] getLeafNodes(final Context context);
	
	/**
	 * ��ø�����
	 * 
	 * @return GoodsCategory
	 */
	public MaterialsCategory getParent(final Context context);
	
	/**
	 * ����Id
	 * 
	 * @return GUID
	 */
	public GUID getParent();
	
	/**
	 * ���ӵ�е���Ʒ��Ŀ�б�
	 * 
	 * @return GoodsItem[]
	 */
	public MaterialsItem[] getMaterialsItems(final Context context);
	
	/**
	 * �Ƿ���Ҷ�ӽڵ�
	 * 
	 * @return boolean
	 */
	public boolean isLeafNode();
	
	/**
	 * �Ƿ�����������
	 * �����ã�true
	 * 
	 * @return boolean
	 */
	public boolean isPropertyFlag();
	
	/**
	 * ��ü���·��
	 * 
	 * @return String
	 */
	public String getPath();

//	/**
//	 * 
//	 * @return
//	 */
//	public PropertyDefine[] getPropertyDefines();
	/**
	 * ��������
	 */
	public long getCreateDate();
	
	public GUID getTenantId();
}
