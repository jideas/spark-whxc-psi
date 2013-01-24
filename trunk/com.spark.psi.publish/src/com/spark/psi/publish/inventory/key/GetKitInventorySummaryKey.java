package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;


/**
 * ��ѯָ����Ʒ�ڸ����ֿ�Ŀ�����
 */
public class GetKitInventorySummaryKey {

	/**
	 * ��Ʒ����
	 */
	private String kitName;

	/**
	 * ��Ʒ����
	 */
	private String kitDesc;

	/**
	 * ��λ
	 */
	private String unit;
	
	/**
	 * �ֿ�ID
	 */
	private GUID storeId;

	/**
	 * ���캯��
	 * 
	 * @param kitName
	 * @param kitDesc
	 * @param unit
	 */
	public GetKitInventorySummaryKey(String kitName, String kitDesc, String unit) {
		super();
		this.kitName = kitName;
		this.kitDesc = kitDesc;
		this.unit = unit;
	}

	/**
	 * @return the kitName
	 */
	public String getKitName() {
		return kitName;
	}

	/**
	 * @return the kitDesc
	 */
	public String getKitDesc() {
		return kitDesc;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public GUID getStoreId() {
		return storeId;
	}

}
