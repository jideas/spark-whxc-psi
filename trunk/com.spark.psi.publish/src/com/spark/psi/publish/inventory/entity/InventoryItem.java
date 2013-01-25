package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ����б�����<br>
 * ��ѯ������<br>
 * ����GetInventoryItemKey��ѯInventoryItem�б�<br>
 */

public interface InventoryItem {

	/**
	 * ��Ʒ��ĿID
	 * 
	 * @return GUID
	 */
	public GUID getStockId();
	/**
	 * ��Ʒ��Ŀ���
	 * 
	 * @return String
	 */
	public String getCode();
	
	/**
	 * ����
	 * @return
	 */
	public String getNumber();
	
	/**
	 * ��Ʒ��Ŀ����
	 * 
	 * @return String
	 */
	public String getName();
	/**
	 * ��Ʒ����
	 * 
	 * @return String
	 */
	public String getProperties();
	/**
	 * ��λ
	 * 
	 * @return String
	 */
	public String getUnit();
	
	/**
	 * ���
	 * @return
	 */
	public String getSpec();
	
	/**
	 * ������
	 * @return
	 */
	public int getShelfLife();
	/**
	 * �������
	 * 
	 * @return double
	 */
	public double getCount();
	
	/**
	 * ����С��λ
	 * 
	 * @return
	 */
	public int getScale();
	
}
