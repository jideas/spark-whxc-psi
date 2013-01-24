package com.spark.oms.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.OrderServiceFeeStatus;
import com.spark.oms.publish.OrderServiceRunStatus;
/**
 * ��Ʒ�б���Ӫ����ʷ�������
 */

public interface OrderItem {
	
	/**
	 * �������
	 * @return
	 */
	public String getSerialNumber();
	
	/**
	 * ǩ������
	 * @return
	 */
	public long getCreateTime();
	
	//ǩ����
	public String getCreator();
	
	//�⻧����
	public String getTenantsName();
	
	//�������
	public double getOrderAmount();
	
	//��������״̬
	public OrderServiceRunStatus getRunstatus();
	
	//����״��
	public OrderServiceFeeStatus getFeestatus();
	
	//����󶩵����
	public String getOrderChangeNo();
	
	public String getReceiptStatus();
	
	//���ԭ��
	public String getChangeReason();
	
	//���ʱ��
	public long getChangeDate();
	
	//�����
	public String getModifyer();
	
	/**
	 * �������
	 * @return
	 */
	public GUID getOrderRECID();
	
	/**
	 * �⻧���
	 * @return
	 */
	public GUID getTenantsRECID();
	
	public String getOrderSource();
	
	public String getProductCategory();
	
	public GUID getBeforeOrderId();
	
	public String getBeforeSerialNumber();
	
	public GUID getSalerId();
	
}
