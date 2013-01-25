package com.spark.order.intf.publish.entity;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
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
 * 查询采购订单列表 OrderListEntity<PurchaseItem>+GetOrderItemKey
 * 
 * 销售订单列表
 * 待提交销售订单界面
 * 待审批销售订单界面
 * 销售订单跟踪
 * 销售订单记录
 * 查询方法 OrderListEntity<SalesOrderItem>+GetOrderItemKey
 * 
 * 交易记录
 * 供应商未完成交易列表 TradingRecordListEntity<TradingRecordItem> + GetPurchaseOrderBySupplierKey(id)
 * 供应商已完成交易记录列表 TradingRecordListEntity<TradingRecordItem> + GetPurchaseOrderBySupplierKey(id，false)
 * 客户未完成交易列表 TradingRecordListEntity<TradingRecordItem> + GetSalesOrderByCustomerKey(id)
 * 客户已完成交易记录列表 TradingRecordListEntity<TradingRecordItem> + GetSalesOrderByCustomerKey(id，false) * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 
 * @version 2012-2-22
 */
@StructClass
public class OrderItemImpl implements com.spark.psi.publish.order.entity.OrderItem{
	@StructField
	protected GUID id;// GUID
	@StructField
	protected String orderNumber;// 单据编号
	@StructField
	protected GUID partnerId;// 客户/供应商GUID
	@StructField
	protected String partnerShortName;// 客户/供应商名称
	@StructField
	protected String partnerName;// 客户/供应商全称+
	@StructField
	protected OrderType orderType;// 类型
	@StructField
	protected String creator;// 制单人
	@StructField
	protected long createDate;// 制单日期
	@StructField
	protected OrderStatus orderstatus;// 处理情况
	@StructField
	protected boolean isStoped = false; // 是否已中止
	@StructField
	protected long deliveryDate;// 交货日期 
	@StructField
	protected double amount;   //订单金额
	
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
