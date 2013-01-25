package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;

/**
 * 
 * <p>���۴���������б�</p>
 * ��ѯ����
 * 1:context.find(ListEntity<SalesDistributeOrderItem)>,GetSalesDistributeOrderListKey)
 *


 *
 
 * @version 2012-3-16
 */
public interface SalesDistributeOrderItem{

	/**
	 * id
	 * 
	 * @return GUID
	 */
	public GUID getId();
	/**
	 * �������
	 * 
	 * @return String
	 */
	public String getOrderNumber();
	/**
	 * �ͻ�id
	 * 
	 * @return GUID
	 */
	public GUID getCustomerId();
	/**
	 * �ͻ����
	 * 
	 * @return String
	 */
	public String getCustomerShortName();
	/**
	 * �ͻ�ȫ��
	 * 
	 * @return String
	 */
	public String getCustomerName();
	/**
	 * �ջ���ַ
	 * 
	 * @return String
	 */
	public String getAddress();
	/**
	 * ��������
	 * @return long
	 */
	public long getDeliveryDate();
	/**
	 * �ɲ�������
	 * 
	 * @return OrderAction[]
	 */
	public OrderAction[] getActions();
	
}
