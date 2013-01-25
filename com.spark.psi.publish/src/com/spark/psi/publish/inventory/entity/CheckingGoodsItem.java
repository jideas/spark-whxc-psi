package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��Ʒ�ĳ������ϸ���� <br>
 * ���ṩ������ѯ
 */
public interface CheckingGoodsItem {

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
	 * ��ȡ�ѳ��������
	 */
	public double getCheckedCount();

	/**
	 * ��ȡ�˴γ��������
	 */
	public double getCheckCount();
	
	/**
	 * ����С��λ
	 */
	public int getScale();
	
	public double getInspectCount();

}
