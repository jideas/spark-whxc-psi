package com.spark.oms.publish.product.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * ��ȡ��Ʒ���ӹ���
 *
 */
public interface ProductAddFunction {
	/**
	 * ��ù���ID
	 * @return
	 */
	public GUID getID();
	
	/**
	 * ������Ʒ�������
	 * @return
	 */
	public GUID getProductItemRECID();
	/**
	 * ��ֵ��������
	 * @return
	 */
	public String getName();
	/**
	 * ��ֵ���ܴ���
	 * @return
	 */
	public String getCode();
	/**
	 * ��ֵ���ܵ���
	 * @return
	 */
	public double getPrice();
	/**
	 * ��ֵ�����շѵ�λ
	 * @return
	 */
	public String getUnit();
	

}
