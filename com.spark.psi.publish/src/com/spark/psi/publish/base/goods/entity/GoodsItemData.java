package com.spark.psi.publish.base.goods.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.GoodsStatus;

/**
 * ��Ʒ��ϸ��Ŀ���ݶ���<br>
 * ��ѯ��������
 */
public interface GoodsItemData {

	/**
	 * id
	 * @return
	 */
	public GUID getId();
	public GUID getBomId();

	/**
	 * ����
	 * @return
	 */

	public double getSalePrice();
	
	/**
	 * 
	 * @return ԭ��
	 */
	public double getOriginalPrice();
	
	/**
	 * 
	 * @return ����
	 */
	public String getGoodsItemNo();
	
	/**
	 * 
	 * @return ���
	 */
	public String getSpec();
	
	/**
	 * 
	 * @return �����
	 */
	public double getLossRate();

	/**
	 * 
	 * ��׼�ɱ�
	 */
	public double getStandardCost();

	/**
	 * ƽ�����ɱ�
	 * @return
	 */
	public double getAverageCost();

	/**
	 * ����
	 * @return
	 */
	public String[] getPropertyValues();

	/**
	 * ������λ������ֵ
	 * @return
	 */
	public String getPropertiesWithoutUnit();

	/**
	 * ����λ������ֵ
	 * @return
	 */
	public String getPropertiesWithUnit();

	/**
	 * �������
	 * @return
	 */
	public double getInventoryUpperLimit();
	
	/**
	 * �ܿ����������
	 * 
	 * @return double
	 */
	public double getTotalStoreUpperLimit();
	/**
	 * �ܿ����������
	 * 
	 * @return double
	 */
	public double getTotalStoreLowerLimit();

	/**
	 * ״̬
	 * @return
	 */
	public GoodsStatus getStatus();
	/**
	 * �Ƿ��ѹ���
	 * @return
	 */
	public boolean isRefFlag();
	
	/**
	 * ��Ʒ����С��λ��
	 * 
	 * @return int
	 */
	public int getScale();
	
	/**
	 * ��Ʒ��λ
	 * 
	 * @return String
	 */
	public String getUnit();
	
	public String getSerialNumber();
	
	public double getHalfkgPrice();
}
