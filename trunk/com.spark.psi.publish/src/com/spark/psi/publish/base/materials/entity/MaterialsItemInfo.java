package com.spark.psi.publish.base.materials.entity;

/**
 * ������ϸ��Ŀ���� <BR>
 * ��ѯ������<br>
 * (1)������ĿID��ѯ������MaterialsItem����
 */
public class MaterialsItemInfo {

	/**
	 * ��Ʒ������Ϣ
	 */
	protected MaterialsInfo baseInfo;

	/**
	 * ��Ŀ����
	 */
	protected MaterialsItemData itemData;

	/**
	 * ��ȡ��Ʒ������Ϣ
	 * 
	 * @return
	 */
	public MaterialsInfo getBaseInfo() {
		return baseInfo;
	}

	/**
	 * ��ȡ��Ʒ��Ŀ���ݶ���
	 * 
	 * @return
	 */
	public MaterialsItemData getItemData() {
		return itemData;
	}

	
}
