package com.spark.psi.publish.base.materials.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ���Ϸ�����ϸ���ݣ�����������Ϣ <br>
 * ��ѯ˵����<br>
 * (1)���ݷ���ID��ѯMaterialsCategoryInfo����
 */
public interface MaterialsCategoryInfo {


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
	 * ��ȡ������
	 * @return
	 */
	public String getCategoryNo();

	/**
	 * ��ȡ����С��λ
	 * 
	 * @return
	 */
	public int getScale();

	/**
	 * ��ȡ���������
	 * 
	 * @return
	 */
//	public double getInventoryUpperLimit();

	/**
	 * ��ȡ���Զ����б�
	 * 
	 * @return
	 */
	public MaterialsPropertyDefine[] getPropertyDefines();

}
