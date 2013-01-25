package com.spark.psi.publish.order.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;

/**
 * 
 * <p>销售、采购、订货、退货类单据基类</p>
 * 
 * 待提交采购订单界面
 * 待审批采购订单界面
 * 跟踪采购订单
 * 采购订单记录
 * 查询采购订单列表 OrderListEntity<OrderItem>+GetPurchaseOrderListKey
 * 
 * 销售订单列表
 * 待提交销售订单界面
 * 待审批销售订单界面
 * 销售订单跟踪
 * 销售订单记录
 * 查询方法 OrderListEntity<OrderItem>+GetSalesOrderListKey
 * 
 * 交易记录
 * 供应商未完成交易列表 TradingRecordListEntity<OrderItem> + GetPurchaseOrderBySupplierKey(id)
 * 供应商已完成交易记录列表 TradingRecordListEntity<OrderItem> + GetPurchaseOrderBySupplierKey(id，false)
 * 客户未完成交易列表 TradingRecordListEntity<OrderItem> + GetSalesOrderByCustomerKey(id)
 * 客户已完成交易记录列表 TradingRecordListEntity<OrderItem> + GetSalesOrderByCustomerKey(id，false) 
 *
 */
@StructClass
public interface OrderItem {
//	@StructField
//	protected GUID id;// GUID
//	@StructField
//	protected String orderNumber;// 单据编号
//	@StructField
//	protected GUID partnerId;// 客户/供应商GUID
//	@StructField
//	protected String partnerShortName;// 客户/供应商名称
//	@StructField
//	protected String partnerName;// 客户/供应商全称+
//	@StructField
//	protected OrderType orderType;// 类型
//	@StructField
//	protected String creator;// 制单人
//	@StructField
//	protected long createDate;// 制单日期
//	@StructField
//	protected OrderState orderState;// 处理情况
//	@StructField
//	protected boolean isStoped = false; // 是否已中止
//	@StructField
//	protected long deliveryDate;// 交货日期 
//	@StructField
//	protected double amount;   //订单金额
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
	 * 获得可执行的操作列表
	 * 
	 * @return OrderAction[]
	 */
	public OrderAction[] getActions();
	
}
