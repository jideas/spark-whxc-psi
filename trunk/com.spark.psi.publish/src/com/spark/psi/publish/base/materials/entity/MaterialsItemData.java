package com.spark.psi.publish.base.materials.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.MaterialsStatus;

/**
 * ������ϸ��Ŀ���ݶ���<br>
 * ��ѯ��������
 */
public interface MaterialsItemData {

	/**
	 * id
	 * @return
	 */
	public GUID getId();
	
	/**
	 * 
	 * @return 
	 */
	public String getMaterialNo();
	
	/**
	 * ���
	 * @return
	 */
	public String getMaterialSpec();

	/**
	 * ����
	 * @return
	 */

	public double getSalePrice();
	
	/**
	 * 
	 * @return �����
	 */
	public double getLossRate();

	/**
	 * ����ɹ�����
	 * @return
	 */
	public double getRecentPurchasePrice();

	/**
	 * ƽ�����ɱ�
	 * @return
	 */
	public double getAvgBuyPrice();
	
	/**
	 * �ƻ��۸�
	 * @return
	 */
	public double getPlanPrice();

	/**
	 * ��׼�۸�
	 * @return
	 */
	public double getStandardPrice();
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
	public MaterialsStatus getStatus();
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
	
	/**
	 * ������
	 */
	public String getInventoryStrategy(); 
}
