package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;

/**
 * 
 * <p>���ۡ��ɹ����������˻��൥�ݻ���</p>
 * 
 * ���ύ�ɹ���������
 * �������ɹ���������
 * ���ٲɹ�����
 * �ɹ�������¼
 * ��ѯ�ɹ������б� OrderListEntity<PurchaseItem>+GetOrderItemKey
 * 
 * ���۶����б�
 * ���ύ���۶�������
 * ���������۶�������
 * ���۶�������
 * ���۶�����¼
 * ��ѯ���� OrderListEntity<SalesOrderItem>+GetOrderItemKey
 * 
 * ���׼�¼
 * ��Ӧ��δ��ɽ����б� TradingRecordListEntity<TradingRecordItem> + GetPurchaseOrderBySupplierKey(id)
 * ��Ӧ������ɽ��׼�¼�б� TradingRecordListEntity<TradingRecordItem> + GetPurchaseOrderBySupplierKey(id��false)
 * �ͻ�δ��ɽ����б� TradingRecordListEntity<TradingRecordItem> + GetSalesOrderByCustomerKey(id)
 * �ͻ�����ɽ��׼�¼�б� TradingRecordListEntity<TradingRecordItem> + GetSalesOrderByCustomerKey(id��false) * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 
 * @version 2012-2-22
 */
@StructClass
public class OrderItemImpl implements com.spark.psi.publish.order.entity.OrderItem{
	@StructField
	protected GUID id;// GUID
	@StructField
	protected String orderNumber;// ���ݱ��
	@StructField
	protected GUID partnerId;// �ͻ�/��Ӧ��GUID
	@StructField
	protected String partnerShortName;// �ͻ�/��Ӧ������
	@StructField
	protected String partnerName;// �ͻ�/��Ӧ��ȫ��+
	@StructField
	protected OrderType orderType;// ����
	@StructField
	protected String creator;// �Ƶ���
	@StructField
	protected long createDate;// �Ƶ�����
	@StructField
	protected OrderStatus orderstatus;// �������
	@StructField
	protected boolean isStoped = false; // �Ƿ�����ֹ
	@StructField
	protected long deliveryDate;// �������� 
	@StructField
	protected double amount;   //�������
	
	protected OrderAction[] actions;
	
	
	
	public OrderAction[] getActions(){
    	return actions;
    }
	public void setActions(OrderAction[] actions){
    	this.actions = actions;
    }
	public GUID getId(){
    	return id;
    }
	public String getOrderNumber(){
    	return orderNumber;
    }
	public GUID getPartnerId(){
    	return partnerId;
    }
	public String getPartnerShortName(){
    	return partnerShortName;
    }
	public String getPartnerName(){
    	return partnerName;
    }
	public OrderType getOrderType(){
    	return orderType;
    }
	public String getCreator(){
    	return creator;
    }
	public long getCreateDate(){
    	return createDate;
    }
	public OrderStatus getOrderStatus(){
    	return orderstatus;
    }
	public boolean isStoped(){
    	return isStoped;
    }
	public long getDeliveryDate(){
    	return deliveryDate;
    }
	public double getAmount(){
    	return amount;
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
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	/**
	 * @param partnerShortName the partnerShortName to set
	 */
	public void setPartnerShortName(String partnerShortName) {
		this.partnerShortName = partnerShortName;
	}
	/**
	 * @param partnerName the partnerName to set
	 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
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
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	/**
	 * @param orderstatus the orderstatus to set
	 */
	public void setOrderStatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}
	/**
	 * @param isStoped the isStoped to set
	 */
	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
