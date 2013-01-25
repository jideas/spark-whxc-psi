package com.spark.psi.publish.base.materials.entity;

/**
 * 
 * <p>���Ͻ��׼�¼</p>
 *
 */
public interface MaterialsTraderLogItem{
	
	public enum TraderType {
		Sales,
		Purchase
	}
	
	
	/**
	 * �ͻ���Ӧ������
	 * 
	 * @return String
	 */
	public String getPartnerName();
	
	/**
	 * ��Ʒ��Ŀ����
	 * 
	 * @return String
	 */
	public String getProperty();
	
	/**
	 * ��Ʒ��λ
	 * 
	 * @return String
	 */
	public String getUnit();
	
	
	/**
	 * ��Ʒ״̬
	 * 
	 * @return String
	 */
	public String getStatus();
	
	/**
	 * ���״���
	 * 
	 * @return int
	 */
	public int getCount();
	
	/**
	 * �ۼƽ��׽��
	 * 
	 * @return int
	 */
	public double getTotalTraderAmount();
	
	/**
	 * �ۼƽ��״���
	 * 
	 * @return int
	 */
	public String getTotalTraderCount();
	
	/**
	 * ������׽��
	 * 
	 * @return double
	 */
	public double getRecentPrice();
	
	/**
	 * �����������
	 * 
	 * @return int
	 */
	public String getRecentCount();
	
	/**
	 * �����������
	 * 
	 * @return long
	 */
	public long getRecentDate();
	
	/**
	 * ���10�ν��׵����б�
	 * 
	 * @return double[]
	 */
	public double[] getPriceList();
}
