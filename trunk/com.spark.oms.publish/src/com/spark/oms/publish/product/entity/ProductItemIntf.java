package com.spark.oms.publish.product.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ProductItemIntf {
	/**
	 * ��Ʒ��ʶ
	 */
	public GUID getId();
	/**
	 * ��Ʒ���
	 */
	public String getCategory();
	/**
	 * ��Ʒϵ��
	 */
	public String getSerial();
	/**
	 * ��Ʒ����
	 */
	public String getName();
	/**
	 * ��Ʒ����
	 */
	public String getCode();
	/**
	 * ������������
	 */
	public double getRemindLine();
	/**
	 * ��Ʒ����
	 */
	public String getRemark();
	/**
	 * ��ƷͼƬ
	 */
	public byte[] getPicture();
	
	/**
	 * ��Ʒ״̬
	 */
	public String getSaledstatus();
}
