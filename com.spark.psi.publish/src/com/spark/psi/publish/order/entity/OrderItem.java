package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
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
 * ��ѯ�ɹ������б� OrderListEntity<OrderItem>+GetPurchaseOrderListKey
 * 
 * ���۶����б�
 * ���ύ���۶�������
 * ���������۶�������
 * ���۶�������
 * ���۶�����¼
 * ��ѯ���� OrderListEntity<OrderItem>+GetSalesOrderListKey
 * 
 * ���׼�¼
 * ��Ӧ��δ��ɽ����б� TradingRecordListEntity<OrderItem> + GetPurchaseOrderBySupplierKey(id)
 * ��Ӧ������ɽ��׼�¼�б� TradingRecordListEntity<OrderItem> + GetPurchaseOrderBySupplierKey(id��false)
 * �ͻ�δ��ɽ����б� TradingRecordListEntity<OrderItem> + GetSalesOrderByCustomerKey(id)
 * �ͻ�����ɽ��׼�¼�б� TradingRecordListEntity<OrderItem> + GetSalesOrderByCustomerKey(id��false) 
 *
 */
@StructClass
public interface OrderItem {
//	@StructField
//	protected GUID id;// GUID
//	@StructField
//	protected String orderNumber;// ���ݱ��
//	@StructField
//	protected GUID partnerId;// �ͻ�/��Ӧ��GUID
//	@StructField
//	protected String partnerShortName;// �ͻ�/��Ӧ������
//	@StructField
//	protected String partnerName;// �ͻ�/��Ӧ��ȫ��+
//	@StructField
//	protected OrderType orderType;// ����
//	@StructField
//	protected String creator;// �Ƶ���
//	@StructField
//	protected long createDate;// �Ƶ�����
//	@StructField
//	protected OrderState orderState;// �������
//	@StructField
//	protected boolean isStoped = false; // �Ƿ�����ֹ
//	@StructField
//	protected long deliveryDate;// �������� 
//	@StructField
//	protected double amount;   //�������
	public GUID getId();
	public String getOrderNumber();
	public GUID getPartnerId();
	public String getPartnerShortName();
	public String getPartnerName();
	public OrderType getOrderType();
	public String getCreator();
	public long getCreateDate();
	public OrderStatus getOrderStatus();
	public boolean isStoped();
	public long getDeliveryDate();
	public double getAmount();
	
	/**
	 * ��ÿ�ִ�еĲ����б�
	 * 
	 * @return OrderAction[]
	 */
	public OrderAction[] getActions();
	
}
