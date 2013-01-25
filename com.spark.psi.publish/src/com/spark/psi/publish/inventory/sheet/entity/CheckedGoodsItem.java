package com.spark.psi.publish.inventory.sheet.entity;

import com.jiuqi.dna.core.type.GUID;

public interface CheckedGoodsItem {

	/**
	 * ��ȡRECID
	 */
	public GUID getId();
	/**
	 * ��ȡ��Ʒ��ĿID
	 */
	public GUID getGoodsItemId();

	/**
	 * ��ȡ�����������
	 */
	public double getCheckingCount();

	/**
	 * ��ȡ����
	 */
	public double getPrice();

	/**
	 * ����С��λ
	 */
	public int getScale();

}
