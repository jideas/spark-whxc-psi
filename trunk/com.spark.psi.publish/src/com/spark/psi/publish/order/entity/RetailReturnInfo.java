package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;

/**
 * 
 * <p>�����˻�ά��ʵ��</p>
 *


 *
 
 * @version 2012-3-6
 */
public interface RetailReturnInfo{
	
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
//	private String retrunProof;  //�˻�ƾ��
//	
//	private long deliveryDate;// �������� 
//	
//	private String payPersonLabel;// �˿��˼�����
//	
//	private String createPersonLabel; //�Ƶ��˼�����
//	
//	private EnumEntity dealingsWay; //֧����ʽ
//	
//	private double amount;	 //�������	
//	
//	private String memo;// ��ע
//	
//	private SalesReturnGoodsItem[] salesReturnGoodsItems; //�˻���Ʒ��ϸ

	public GUID getId();

	public String getOrderNumber();

	public String getCustomerName();
	
	public GUID getCustomerId();

	public String getRetrunProof();

	public long getDeliveryDate();

	public String getPayPersonLabel();

	public String getCreatePersonLabel();

	public DealingsWay getDealingsWay();

	public double getAmount();

	public String getRemark();
	

	public SalesReturnGoodsItem[] getSalesReturnGoodsItems();
	
}
