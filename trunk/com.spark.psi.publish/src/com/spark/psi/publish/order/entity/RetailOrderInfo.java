package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.RetailStatus;


/**
 * 
 * <p>���۵�ά��ʵ��</p>
 *


 *
 
 * @version 2012-3-6
 */
public interface RetailOrderInfo  {
	
	
//	/**
//	 * ���۵�id
//	 */
//	private GUID id;// GUID
//	
//	/**
//	 * ���۵��ݱ��
//	 */
//	private String orderNumber;// ���ݱ��
//	
//	private String customerName; //�ͻ�����
//	
//	private String consigneeName;  //�ջ���
//	
//	private String deliveryAddress;  //�ͻ���ַ
//	
//	private String mobileNumber;  //�绰
//	
//	private long deliveryDate;// �������� 
//	
//	private String receiptPersonLabel;// �տ��˼�����
//	
//	private String createPersonLabel; //�Ƶ��˼�����
//	
//	private OrderType orderType;// ����
//	
//	private EnumEntity dealingsWay; //֧����ʽ
//	
//	private RetailStatus retailstatus;// �������	
//	
//	private double amount;	 //�������	
//	
//	private String memo;// ��ע
//	
//	private double discountAmount;//	�����ۿ�	N
//	
//	private SalesOrderGoodsItem[] salesOrderGoodsItems;  //������Ʒ��ϸ	

	public GUID getId();

	public String getOrderNumber();
	
	public GUID getCustomerId();
	
	public String getCustomerName();

	public String getConsigneeName();

	public String getDeliveryAddress();

	public String getMobileNo();

	public long getDeliveryDate();

	public String getReceiptPersonLabel();

	public String getCreatePersonLabel();

	public OrderType getOrderType();

	public DealingsWay getDealingsWay();

	public RetailStatus getRetailStatus();

	public double getAmount();

	public String getRemark();

	public double getDiscountAmount();

	public SalesOrderGoodsItem[] getSalesOrderGoodsItems();
}
