package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DealingsWay;
import com.spark.psi.publish.OrderType;

/**
 * 
 * <p>���۶�����¼</p>
 * ��ѯ���ۼ�¼ RetailListEntity<RetailItem> + GetRetailListKey retailstatus = RetailStatus.Completed
 * �ͻ��տ��б�  RetailListEntity<RetailItem> + GetRetailListKey  retailstatus = RetailStatus.Delivery
 *


 *
 
 * @version 2012-3-6
 */
public class RetailItemImpl implements com.spark.psi.publish.order.entity.RetailItem{
	
	
	private GUID id;// GUID
	
	private String orderNumber;// ���ݱ��
	
	private OrderType orderType;// ����
	
	private String creator;// �Ƶ���
	
	private String operator; //������	
	
	private long createDate;// ��������
	
	private DealingsWay dealingsWay;  //���㷽ʽ
	
	private double amount;   //�������
	
	private String consignee;  //�ջ���
	
	private String deliveryAddress;  // �ջ��ַ

	public GUID getId(){
    	return id;
    }

	public String getOrderNumber(){
    	return orderNumber;
    }

	public OrderType getOrderType(){
    	return orderType;
    }

	public String getCreator(){
    	return creator;
    }

	public String getOperator(){
    	return operator;
    }

	public long getCreateDate(){
    	return createDate;
    }

	public DealingsWay getDealingsWay(){
    	return dealingsWay;
    }

	public double getAmount(){
    	return amount;
    }

	public String getConsignee(){
    	return consignee;
    }

	public String getDeliveryAddress(){
    	return deliveryAddress;
    }

	/**
	 * @param id the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	/**
	 * @param dealingsWay the dealingsWay to set
	 */
	public void setDealingsWay(DealingsWay dealingsWay) {
		this.dealingsWay = dealingsWay;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param consignee the consignee to set
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	/**
	 * @param deliveryAddress the deliveryAddress to set
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

}
