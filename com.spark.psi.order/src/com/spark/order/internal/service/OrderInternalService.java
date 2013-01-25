package com.spark.order.internal.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.event.ChangedType;
import com.spark.order.intf.event.PurchaseOrderChangedEvent;
import com.spark.order.intf.event.PurchaseReturnChangedEvent;
import com.spark.order.intf.event.SalesOrderChangedEvent;
import com.spark.order.intf.event.SalesReturnChangedEvent;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.task.BillsExamineChangeTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.service.util.OrderUtil;
import com.spark.order.util.dnaSql.DnaSql_delete;
import com.spark.order.util.dnaSql.DnaSql_update;
import com.spark.psi.account.intf.entity.dealing.DealingItem;
import com.spark.psi.account.intf.event.DealingAmountChanageEvent;
import com.spark.psi.base.GoodsItem;
import com.spark.psi.base.event.ApprovalConfigChangedEvent;
import com.spark.psi.base.event.CustomerCreditReduceEvent;
import com.spark.psi.base.event.GoodsStatusChangeEvent;
import com.spark.psi.inventory.intf.event.CheckInEvent;
import com.spark.psi.inventory.intf.event.CheckInSheetAmountBalanceCompleteEvent;
import com.spark.psi.inventory.intf.event.CheckOutEvent;
import com.spark.psi.inventory.intf.event.CheckOutSheetAmountBalanceCompleteEvent;
import com.spark.psi.publish.DealingsType;
import com.spark.psi.publish.GoodsStatus;

/**
 * <p>订单内部Service</p>
 *
 *
 */
public class OrderInternalService extends Service{

	protected OrderInternalService() {
		super("com.spark.order.internal.service.OrderInternalPublishService");
	}
	/**
	 * <p>入库递减完成</p>
	 *
	 * 
	 */
	@Publish
	protected class Order_CheckInSheetAmountBalanceCompleteEventListener extends EventListener<CheckInSheetAmountBalanceCompleteEvent>{

		@Override
		protected void occur(Context context,
				CheckInSheetAmountBalanceCompleteEvent event) throws Throwable {
			if(CheckIsNull.isEmpty(event.getRelaOrderId()) || CheckIsNull.isEmpty(event.getType())){
				throw new Throwable("相关单据编号和入库类型不能为空");
			}
			OrderInfo info = null;
			BillsEnum billsEnum = null;
			switch (event.getType()) {
//			Purchase("01", "采购入库"), //
//			Irregular("02", "零星采购"), //
//			Return("03", "销售退货"), //
//			RetailReturn("04","零售退货"),
//			Other("05", "其他入库");
			case Purchase:
				info = context.find(PurchaseOrderInfo.class, event.getRelaOrderId());
				billsEnum = BillsEnum.PURCHASE;
				break;
			case Return:
				info = context.find(SaleCancel.class, event.getRelaOrderId());
				billsEnum = BillsEnum.SALE_CANCEL;
				break;
//			case DirectSupply:
//				info = context.find(PurchaseOrderInfo.class, event.getRelaOrderId());
//				billsEnum = BillsEnum.PURCHASE;
//				break;
			default:
				return;
			}
			if(CheckIsNull.isEmpty(info)){
				throw new Throwable("相关单据编号在订单中不存在");
			}
			StatusEnum newstatus = InventoryDataUtil.getOrderStatusByIn(context, event.getRelaOrderId());
			if(!newstatus.isThis(info.getStatus()) && StatusEnum.Finished == newstatus){
				OrderUtil.modifystatus(billsEnum, context, info.getRECID(), newstatus, StatusEnum.getstatus(info.getStatus()));
				//订单完成事件
				if(BillsEnum.PURCHASE == billsEnum){
					context.dispatch(new PurchaseOrderChangedEvent(info.getRECID(), ChangedType.Finish));
				}
				else{
					context.dispatch(new SalesReturnChangedEvent(info.getRECID(), ChangedType.Finish));
				}
			}
		}
	}
	//出库递减完成
//	CheckOutSheetAmountBalanceCompleteEvent
	@Publish
	protected class Order_CheckOutSheetAmountBalanceCompleteEventListener extends EventListener<CheckOutSheetAmountBalanceCompleteEvent>{

		@Override
		protected void occur(Context context,
				CheckOutSheetAmountBalanceCompleteEvent event) throws Throwable {
			if(CheckIsNull.isEmpty(event.getRelaOrderId()) || CheckIsNull.isEmpty(event.getType())){
				throw new Throwable("相关单据编号和出库类型不能为空");
			}
			OrderInfo info = null;
			BillsEnum billsEnum = null;
			switch (event.getType()) {
//			Sales("01", "销售出库"), //
//			Return("02", "采购退货"), //
//			Retail("03", "零售出库"), //
//			Kit("04", "其他出库");
			case Sales:
				info = context.find(SaleOrderInfo.class, event.getRelaOrderId());
				billsEnum = BillsEnum.SALE;
				break;
			case Return:
				info = context.find(PurchaseCancel.class, event.getRelaOrderId());
				billsEnum = BillsEnum.PURCHASE_CANCEL;
				break; 
			default:
				return;
			}
			if(CheckIsNull.isEmpty(info)){
				throw new Throwable("相关单据编号在订单中不存在");
			}
			StatusEnum newstatus = InventoryDataUtil.getOrderStatusByOut(context, event.getRelaOrderId());
			if(!newstatus.isThis(info.getStatus()) && StatusEnum.Finished == newstatus){
				OrderUtil.modifystatus(billsEnum, context, info.getRECID(), newstatus, StatusEnum.getstatus(info.getStatus()));
				//订单完成事件
				if(BillsEnum.SALE == billsEnum){
					context.dispatch(new SalesOrderChangedEvent(info.getRECID(), ChangedType.Finish));
				}
				else{
					context.dispatch(new PurchaseReturnChangedEvent(info.getRECID(), ChangedType.Finish));
				}
			}
		}
		
	}
	//确认入库
//	CheckInEvent
	@Publish
	protected class Order_CheckInEventListener extends EventListener<CheckInEvent>{

		@Override
		protected void occur(Context context, CheckInEvent event)
				throws Throwable {
			if(CheckIsNull.isEmpty(event.getRelaOrderId()) || CheckIsNull.isEmpty(event.getType())){
				throw new Throwable("相关单据编号和入库类型不能为空");
			}
			OrderInfo info = null;
			BillsEnum billsEnum = null;
			switch (event.getType()) {
//			Purchase("01", "采购入库"), //
//			Irregular("02", "零星采购"), //
//			Return("03", "销售退货"), //
//			RetailReturn("04","零售退货"),
//			Other("05", "其他入库");
			case Purchase:
				info = context.find(PurchaseOrderInfo.class, event.getRelaOrderId());
				billsEnum = BillsEnum.PURCHASE;
				break;
			case Return:
				info = context.find(SaleCancel.class, event.getRelaOrderId());
				billsEnum = BillsEnum.SALE_CANCEL;
				break;
//			case DirectSupply:
//				info = context.find(PurchaseOrderInfo.class, event.getRelaOrderId());
//				billsEnum = BillsEnum.PURCHASE;
//				break;
			default:
				return;
			}
			if(CheckIsNull.isEmpty(info)){
				throw new Throwable("相关单据编号在订单中不存在");
			}
			StatusEnum newstatus = InventoryDataUtil.getOrderStatusByIn(context, event.getRelaOrderId());
			if(!newstatus.isThis(info.getStatus())){
				OrderUtil.modifystatus(billsEnum, context, info.getRECID(), newstatus,StatusEnum.getstatus(info.getStatus()) );
				//全部出库完成出库
				if(newstatus == StatusEnum.Store_All){
					if(BillsEnum.PURCHASE == billsEnum){
						context.dispatch(new PurchaseOrderChangedEvent(info.getRECID(), ChangedType.StoreFinish));
					}
					else{
						context.dispatch(new SalesReturnChangedEvent(info.getRECID(), ChangedType.StoreFinish));
					}
				}
			}
		}
		
	}
	
	//确认出库
//	CheckOutEvent
	@Publish
	protected class Order_CheckOutEventListener extends EventListener<CheckOutEvent>{

		@Override
		protected void occur(Context context, CheckOutEvent event)
				throws Throwable {
			if(CheckIsNull.isEmpty(event.getRelaOrderId()) || CheckIsNull.isEmpty(event.getType())){
				throw new Throwable("相关单据编号和出库类型不能为空");
			}
			OrderInfo info = null;
			BillsEnum billsEnum = null;
			switch (event.getType()) {
//			Sales("01", "销售出库"), //
//			Return("02", "采购退货"), //
//			Retail("03", "零售出库"), //
//			Kit("04", "其他出库");
			case Sales:
				info = context.find(SaleOrderInfo.class, event.getRelaOrderId());
				billsEnum = BillsEnum.SALE;
				break;
			case Return:
				info = context.find(PurchaseCancel.class, event.getRelaOrderId());
				billsEnum = BillsEnum.PURCHASE_CANCEL;
				break;
			default:
				return;
			}
			if(CheckIsNull.isEmpty(info)){
				throw new Throwable("相关单据编号在订单中不存在");
			}
			StatusEnum newstatus = InventoryDataUtil.getOrderStatusByOut(context, event.getRelaOrderId());
			if(!newstatus.isThis(info.getStatus())){
				OrderUtil.modifystatus(billsEnum, context, info.getRECID(), newstatus, StatusEnum.getstatus(info.getStatus()));
				//全部出库完成出库
				if(newstatus == StatusEnum.Store_All){
					if(BillsEnum.SALE == billsEnum){
						context.dispatch(new SalesOrderChangedEvent(info.getRECID(), ChangedType.StoreFinish));
					}
					else{
						context.dispatch(new PurchaseReturnChangedEvent(info.getRECID(), ChangedType.StoreFinish));
					}
				}
			}
		}
	}
	
	/**
	 * <p>客户往来款变化事件</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-29
	 */
	@Publish
	class Order_CustomerDealingAmountChanageListner extends EventListener<DealingAmountChanageEvent>{
 
		@SuppressWarnings("deprecation")
		@Override
		protected void occur(Context context,
				DealingAmountChanageEvent event) throws Throwable {
			DealingItem di = context.find(DealingItem.class, event.getDealingItemId());
			double amount = di.getRealAmount();
			DealingsType type = DealingsType.getEnum(di.getBillsType());
			if(amount == 0 && (DealingsType.CUS_TZYS == type || DealingsType.CUS_INIT == type )){//|| DealingsType.CUS_THRK == type)){
				amount = -di.getPlanAmount();
			}
			if(0 != amount){
         				OrderUtil.subAlreadyUseCredit(context, di.getPartnerId(), amount);
			}
		}
	}
	
	/**
	 * <p>商品状态变化事件</p>
	 */
	@Publish
	class Order_GoodsStatusChangeListner extends EventListener<GoodsStatusChangeEvent>{
		private GoodsStatusChangeEvent event;
		@Override
		protected void occur(Context context, GoodsStatusChangeEvent event)
				throws Throwable {
			this.event = event;
			GoodsItem gi = context.find(GoodsItem.class, event.goodsItemId);
			if(GoodsStatus.STOP_SALE == gi.getStatus()){
				new PromotionStopSql(context).executeUpdate();
				new PromotionDelSql(context).executeUpdate();
			}
			else if(GoodsStatus.ON_SALE == gi.getStatus()){
				new PromotionIssueSql(context).executeUpdate();
				new PromotionOutOfDateSql(context).executeUpdate();
			}
		}
		class PromotionDelSql extends DnaSql_delete{

			public PromotionDelSql(Context context) {
				super(context);
			}

			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" delete from ");
				sql.append(OrderEnum.Sales_Promotion.getDb_table());
				sql.append(" as t ");
				sql.append(" where ");
				sql.append(" 1 = 1 ");
//				this.addParam("@tenantsId guid", BillsConstant.getTenantsGuid(context));
				sql.append(" and( ");
				sql.append(" t.state = @submit ");
				this.addParam("@submit string", PromotionStatus2.Submit.getCode());
				sql.append(" or t.state = @approval ");
				this.addParam("@approval string", PromotionStatus2.Approve.getCode());
				sql.append(" ) ");
				sql.append(" and t.goodsItemId = @goodsItemId ");
				this.addParam("@goodsItemId guid", event.goodsItemId);
				return sql.toString();
			}
			
		}
		class PromotionStopSql extends DnaSql_update{

			public PromotionStopSql(Context context) {
				super(context);
			}

			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" update ");
				sql.append(OrderEnum.Sales_Promotion.getDb_table());
				sql.append(" as t ");
				sql.append(" set ");
				sql.append(" state = @stopstatus ");
				this.addParam("@stopstatus string", PromotionStatus2.Stoped_sales.getCode());
				sql.append(" where ");
				sql.append(" 1 = 1 ");
//				this.addParam("@tenantsId guid", BillsConstant.getTenantsGuid(context));
				sql.append(" and( ");
				sql.append(" t.state = @issue ");
				this.addParam("@issue string", PromotionStatus2.Issue.getCode());
				sql.append(" or t.state = @promotioning ");
				this.addParam("@promotioning string", PromotionStatus2.Promotioning.getCode());
				sql.append(" ) ");
				sql.append(" and t.goodsItemId = @goodsItemId ");
				this.addParam("@goodsItemId guid", event.goodsItemId);
				return sql.toString();
			}
		}
		class PromotionIssueSql extends DnaSql_update{
			
			public PromotionIssueSql(Context context) {
				super(context);
			}
			
			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" update ");
				sql.append(OrderEnum.Sales_Promotion.getDb_table());
				sql.append(" as t ");
				sql.append(" set ");
				sql.append(" state = @issue ");
				this.addParam("@issue string", PromotionStatus2.Issue.getCode());
				sql.append(" where ");
				sql.append(" 1 = 1 ");
//				this.addParam("@tenantsId guid", BillsConstant.getTenantsGuid(context));
				sql.append(" and t.state = @stopstatus ");
				this.addParam("@stopstatus string", PromotionStatus2.Stoped_sales.getCode());
				sql.append(" and t.goodsItemId = @goodsItemId ");
				this.addParam("@goodsItemId guid", event.goodsItemId);
				sql.append(" and t.beginDate > @thisDate  ");
				this.addParam("@thisDate date", System.currentTimeMillis());
				return sql.toString();
			}
		}
		class PromotionOutOfDateSql extends DnaSql_update{
			
			public PromotionOutOfDateSql(Context context) {
				super(context);
			}
			
			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" update ");
				sql.append(OrderEnum.Sales_Promotion.getDb_table());
				sql.append(" as t ");
				sql.append(" set ");
				sql.append(" state = @outOfDate ");
				this.addParam("@outOfDate string", PromotionStatus2.Out_of_date.getCode());
				sql.append(" where ");
				sql.append(" 1 = 1 ");
//				this.addParam("@tenantsId guid", BillsConstant.getTenantsGuid(context));
				sql.append(" and t.state = @stopstatus ");
				this.addParam("@stopstatus string", PromotionStatus2.Stoped_sales.getCode());
				sql.append(" and t.goodsItemId = @goodsItemId ");
				this.addParam("@goodsItemId guid", event.goodsItemId);
				sql.append(" and t.endDate < @thisDate  ");
				this.addParam("@thisDate date", System.currentTimeMillis());
				return sql.toString();
			}
		}
	}
	
	/**
	 * <p>审核变化事件监听器</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-4-26
	 */
	@Publish
	protected class ApprovalConfigChangedEventListner extends EventListener<ApprovalConfigChangedEvent>{

		@Override
		protected void occur(Context context, ApprovalConfigChangedEvent event)
				throws Throwable {
			OrderEnum orderEnum = OrderEnum.getOrderEnum(event.getMode());
			if(null != orderEnum){
				BillsExamineChangeTask task = new BillsExamineChangeTask(orderEnum, BillsConstant.getTenantsGuid(context));
				task.setCause(BillsConstant.FLOW_CAUSE);
				context.handle(task);
			}
		}
	}
	
	/**
	 * <p>客户变化监听器</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-4-26
	 */
	@Publish
	protected class CustomerCreditReduceEventListner extends EventListener<CustomerCreditReduceEvent>{
		
		@Override
		protected void occur(Context context, CustomerCreditReduceEvent event)
		throws Throwable {
			new CustomerCreditReduceSql(context, event.getCustomerId(), OrderEnum.Sales).executeUpdate();
			new CustomerCreditReduceSql(context, event.getCustomerId(), OrderEnum.Sales_Return).executeUpdate();
		}
		
		class CustomerCreditReduceSql extends DnaSql_update{
			GUID customerId;
			OrderEnum orderEnum;
			public CustomerCreditReduceSql(Context context, GUID customerId, OrderEnum orderEnum) {
				super(context);
				this.customerId = customerId;
				this.orderEnum = orderEnum;
			}

			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" update ");
				sql.append(orderEnum.getDb_table());
				sql.append(" as t ");
				sql.append(" set rebutCause = @returnCause ");
				this.addParam("@returnCause string", "审核流程变更，请重新提交！");
				sql.append(" ,status = @returnstatus ");
				this.addParam("@returnstatus string", StatusEnum.Return.getKey());
				sql.append(" where ");
				sql.append(" t.tenantsGuid = @tenantsId ");
				this.addParam("@tenantsId guid", BillsConstant.getTenantsGuid(context));
				sql.append(" and ");
				sql.append(" t.status = @eximstatus ");
				this.addParam("@eximstatus string", StatusEnum.Approve.getKey());
				sql.append(" and ");
				sql.append(" t.cuspGuid = @partnerId ");
				this.addParam("@partnerId guid", customerId);
				return sql.toString();
			}
			
		}
	}
	
	
//	/**
//	 * 触发异步事件，该方法一经调用马上返回，每个事件处理都拥有独立的事务。
//	 * 
//	 * @param event
//	 *            事件对象
//	 */
//	public AsyncHandle occur(Event event);
//
//	/**
//	 * 触发同步事件，该方法等待该事件的全部处理器执行完毕后返回，事件处理与调用者在同一事务中工作。
//	 * 
//	 * @param event事件对象
//	 * @return 返回false表示没有事件响应器
//	 */
//	public boolean dispatch(Event event);
	
}
