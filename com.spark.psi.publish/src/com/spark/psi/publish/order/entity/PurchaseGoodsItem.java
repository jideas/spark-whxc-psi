package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * �ɹ��嵥
 * </p>
 * ��òɹ������嵥 ��ѯ������ListEntity<PurchaseGoodsItem>
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 
 * @version 2012-2-22
 */
public interface PurchaseGoodsItem {

	public GUID getId();

	public GUID getGoodsItemId();

	public String getGoodsCode();
	public String getGoodsNo();

	public String getGoodsName();

	public String getProperties();

	public String getUnit();

	public int getScale();

	public double getPrice();

	public double getCount();

	public GUID getSupplierId();

	public double getRecentPrice();

	public GUID getStoreId();

	public String getStoreName();

	public boolean isDirectDelivery();
	
	/**
	 * �����ϵ��Id
	 * @return GUID
	 */
	GUID getContactId();
	/**
	 * ��Ӧ������
	 * @return String
	 */
	String getSupplierName();
	/**
	 * ��Ӧ�̼��
	 * @return String
	 */
	String getSupplierShorName();
	/**
	 * ���ֱ��������������
	 * @return long
	 */
	long getDirectDeliveryDate();
	/**
	 * ���۶�����ע
	 * @return String
	 */
	String getSalesMemo();
}
