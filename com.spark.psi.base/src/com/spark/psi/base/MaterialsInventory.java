package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * ��Ʒ���
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 
 * @version 2012-3-1
 */
public interface MaterialsInventory {

	/**
	 * �ֿ�id
	 */
	public GUID getStoreId();

	/**
	 * ��Ʒ��Ŀid
	 */
	public GUID getGoodsItemId();

	/**
	 * �������
	 */
	public double getCount();

	/**
	 * �����
	 */
	public double getAmount();

	/**
	 * ������������
	 */
	public double getDeliveryingCount();

	/**
	 * ����������
	 */
	public double getDeliveryingAmount();

	/**
	 * �����������
	 */
	public double getUpperLimitCount();

	/**
	 * �����������
	 */
	public double getLowerLimitCount();

	/**
	 * ����������޽��
	 */
	public double getUpperLimitAmount();

	/**
	 * �ɹ���;����
	 */
	public double getOnWayCount();

	/**
	 * ���ÿ��
	 */
	// public double getAvailableInventory();

	/**
	 * 
	 * @return
	 */
	public double getLockedCount();

}
