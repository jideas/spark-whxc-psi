package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.OrderType;

/**
 * 
 * <p>���۶�����¼</p>
 * ��ѯ���ۼ�¼  RetailListEntity<RetailItem> + GetRetailListKey retailstatus = RetailStatus.Completed
 * �ͻ��տ��б�  RetailListEntity<RetailItem> + GetRetailListKey  retailstatus = RetailStatus.Delivery
 *


 *
 
 * @version 2012-3-6
 */
public interface RetailItem{
	
	
//	private GUID id;// GUID
//	
//	private String orderNumber;// ���ݱ��
//	
//	private OrderType orderType;// ����
//	
//	private String creator;// �Ƶ���
//	
//	private String operator; //������	
//	
//	private long createDate;// ��������
//	
//	private EnumEntity dealingsWay;  //���㷽ʽ
//	
//	private double amount;   //�������
//	
//	private String consignee;  //�ջ���
//	
//	private String deliveryAddress;  // �ջ��ַ

	public GUID getId();

	public String getOrderNumber();

	public OrderType getOrderType();

	public String getCreator();

	public String getOperator();

	/**
	 * �������ڣ����ͻ���������Ϊ��������
	 * @return long
	 */
	public long getCreateDate();

	public DealingsWay getDealingsWay();

	public double getAmount();

	public String getConsignee();

	public String getDeliveryAddress();

}
