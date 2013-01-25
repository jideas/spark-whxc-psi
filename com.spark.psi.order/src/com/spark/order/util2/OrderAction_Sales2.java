package com.spark.order.util2;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.entity.OrderFather;
import com.spark.order.intf.event.ChangedType;
import com.spark.order.intf.event.SalesOrderChangedEvent;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.purchase.intf.PurchaseGoodsDirect2;
import com.spark.order.purchase.intf.task.PurchaseGoodsDirectTask2;
import com.spark.order.sales.intf.task.ModifyDirectGoodsStopStatusTask;
import com.spark.order.sales2.SalesAllocateTask;
import com.spark.order.sales2.SalesOrderInfo2;
import com.spark.order.sales2.SalesOrderItem2;
import com.spark.order.sales2.SetSalesPlanOutDateTask;
import com.spark.order.service.util.OrderUtil;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.inventory.intf.task.outstorage.OutstoAddTask;
import com.spark.psi.publish.CheckingOutType;

class OrderAction_Sales2 extends OrderActionSPImpl2<SalesOrderInfo2>{
	private long planOutDate;

	protected OrderAction_Sales2(Context context) {
		super(context, OrderEnum.Sales);
	}
	
	private List<Store> stores = OrderUtil.getStore(getContext());
	private boolean isDirect = BillsConstant.isDirect(getContext());
	
	@Override
	protected void effectual() throws OrderActionLoseException{
		//自动生成出库单校验，订单若不能生成出库单抛出异常
		List<SalesOrderItem2> items = null;
		if(null == stores || 0 == stores.size()){
			if(isDirect){
				//自动直供
				createDirectGoods();
			}
			else{
				throw new OrderActionNotHaveStoreException();
			}
		}
		else if(1 == stores.size() && !isDirect){
			SalesOrderInfo2 sales = getEntity(SalesOrderInfo2.class);
			if(0 < sales.getPlanOutDate()){
				createOutStore(sales, stores.get(0));
			}
			else if(0 < planOutDate){
				//修改订单中的预计出库日期
				getContext().handle(new SetSalesPlanOutDateTask(getOrderId(), planOutDate));
				sales.setPlanOutDate(planOutDate);
				//创建出库单同时
				items = createOutStore(sales, stores.get(0));
			}
			else{
				throw new OrderActionPlanOutDateException();
			}
		}
		//修改已促销数量
		if(null == items){
			items = getContext().getList(SalesOrderItem2.class, getOrderId());
		}
		for(SalesOrderItem2 item : items){
			updatePromotionCount(item.getPromotionId(), item.getNum());
		}
		//抛出生效事件
		getContext().dispatch(new SalesOrderChangedEvent(getOrderId(), ChangedType.Effectual));
	}
	
	/**
	 * 生成出库单
	 *  void
	 * @param sales 
	 * @param store 
	 */
	private List<SalesOrderItem2> createOutStore(SalesOrderInfo2 sales, Store store) throws OrderActionLoseException {
		Outstorage entity = new Outstorage();
//		entity.setAllOutstoDate(allOutstoDate)
//		entity.setCreateDate(System.currentTimeMillis());
//		entity.setCreatePerson(BillsConstant.getUserGuid(getContext()));
//		entity.setCreatePersonName(BillsConstant.getUserName(getContext()));
//		entity.setCusproGuid(sales.getPartnerId());
//		entity.setCusproName(sales.getPartnerName());
//		entity.setCusproNamePY(sales.getPartnerNamePY());
//		entity.setCusproShortName(sales.getPartnerShortName());
////		entity.setGoodsFrom(goodsFrom)
////		entity.setGoodsUse(goodsUse)
//		entity.setOrderTotalAmount(sales.getTotalAmount());
////		entity.setOutstoNo(outstoNo)
////		entity.setOutstoState(outstoState)
//		entity.setOutstoType(CheckingOutType.Sales.getCode());
//		entity.setPlanOutstoDate(sales.getPlanOutDate());
//		entity.setRECID(getContext().newRECID());
//		entity.setRelaOrderGuid(sales.getRecid());
//		entity.setRelaOrderNo(sales.getOrderNumber());
//		entity.setRemark(sales.getRemark());
//		entity.setStoped(false);
//		entity.setStopReaseon(null);
//		entity.setStoreGuid(store.getId());
//		entity.setStoreName(store.getName());
//		entity.setStoreNamePY(PinyinHelper.getLetter(entity.getStoreName()));
////		entity.setSureOutsto(sureOutsto);
////		entity.setTakePerson(takePerson)
////		entity.setTakeUnit(takeUnit)
//		entity.setTenantsGuid(BillsConstant.getTenantsGuid(getContext()));
		List<OutstorageItem> list = new ArrayList<OutstorageItem>();
//		OutstorageItem det;
//		double totalCount = 0;
		List<SalesOrderItem2> items = getContext().getList(SalesOrderItem2.class, sales.getRecid());
//		for(SalesOrderItem2 item : items){
//			det = new OutstorageItem();
//			det.setCreateDate(System.currentTimeMillis());
//			det.setCreatePerson(entity.getCreatePersonName());
//			det.setDiscountRate(item.getDiscount());
//			det.setGoodsAttr(item.getGoodsProperties());
//			det.setGoodsGuid(item.getGoodsItemId());
//			det.setGoodsName(item.getGoodsName());
//			det.setGoodsNo(item.getGoodsCode());
//			det.setGoodsPrice(item.getPrice());
//			det.setGoodsScale(item.getScale());
////			det.setGoodsStorageType(goodsStorageType)
//			det.setGoodsUnit(item.getGoodsUnit());
////			det.setOutstoCount(outstoCount)
//			det.setOutstoGuid(entity.getRECID());
//			det.setPlanOutstoCount(item.getNum());
//			det.setRECID(getContext().newRECID());
////			det.setStoreCount(storeCount)
////			det.setTaxRate(taxRate)
//			det.setTenantsGuid(entity.getTenantsGuid());
////			det.setThisTimeCount(thisTimeCount)
//			totalCount += item.getNum();
//			list.add(det);
//		}
//		entity.setOrderGoodsTotalCount(totalCount);
		OutstoAddTask task = new OutstoAddTask(entity, list);
		getContext().handle(task, CheckingOutType.Sales);
		//出库完成，修改订单状态为出库完成状态
		allocate();
		return items;
	}

	@Override
	public void setPlanOutDate(Long l) {
		if(null == l){
			this.planOutDate = 0;
		}
		else{
			this.planOutDate = l;
		}
	}

	/**
	 * 生成直供商品
	 *  void
	 */
	private void createDirectGoods() throws OrderActionLoseException{
		SalesOrderInfo2 sales = getEntity(SalesOrderInfo2.class);
		PurchaseGoodsDirectTask2 task = new PurchaseGoodsDirectTask2();
		PurchaseGoodsDirect2 goods;
		for(SalesOrderItem2 item : getContext().getList(SalesOrderItem2.class, sales.getRecid())){
			goods = new PurchaseGoodsDirect2();
//			goods.setContactId(contactId);
//			goods.setContactName(contactName)
//			goods.setContactPhone(contactPhone)
//			goods.setContactTel(contactTel);
			goods.setCountDecimal(item.getScale());
			goods.setCreateDate(System.currentTimeMillis());
			goods.setCreator(BillsConstant.getUserName(getContext()));
			goods.setCreatorId(BillsConstant.getUserGuid(getContext()));
			goods.setDeleteFlag(false);
			goods.setGoodsCode(item.getGoodsCode());
			goods.setGoodsItemId(item.getGoodsItemId());
			goods.setGoodsName(item.getGoodsName());
			goods.setGoodsProperties(item.getGoodsProperties());
			goods.setGoodsUnit(item.getGoodsUnit());
//			goods.setPartnerFax(partnerFax)
//			goods.setPartnerId(partnerId)
//			goods.setPartnerName(partnerName)
//			goods.setPartnerShortName(partnerShortName)
//			goods.setPrice_lastPurchase(priceLastPurchase)
			goods.setPrice_purchase(-1);
			goods.setPurchaseCause("直供");
			goods.setPurchaseCount(item.getNum());
			goods.setRecid(getContext().newRECID());
			goods.setSourceId(sales.getPartnerId());
			goods.setSourceName(sales.getPartnerShortName());
			goods.setSourceSaleId(sales.getRecid());
			goods.setSourceSaleItemId(item.getRecid());
			goods.setTenantsId(BillsConstant.getTenantsGuid(getContext()));
			task.entity = goods;
			getContext().handle(task, PurchaseGoodsDirectTask2.Method.ADD);
		}
	}

	@Override
	protected boolean allocate()
			throws com.spark.order.util2.IOrderAction2.OrderActionLoseException {
		SalesAllocateTask allocate = new SalesAllocateTask(getOrderId());
		getContext().handle(allocate);
		if(!allocate.isSucceed()){
			throw new OrderActionLoseException("修改订单状态为完成分配状态出错！");
		}
		return allocate.isSucceed();
	}

	@Override
	protected SalesOrderInfo2 getOrder() {
		return getEntity(SalesOrderInfo2.class);
	}
	
	//===========销售订单的中止和重新执行还要管理到  直供需求、管理的采购订单==============
	@Override
	protected boolean stop(OrderFather order) {
		if(super.stop(order)){
			//中止关联的直供需求
			ModifyDirectGoodsStopStatusTask task = new ModifyDirectGoodsStopStatusTask(getOrderId(), true);
			getContext().handle(task);
			return task.isSucceed();
		}
		return false;
	}
	@Override
	protected boolean execut(OrderFather order) {
		if(super.execut(order)){
			//重新启用关联的直供需求
			ModifyDirectGoodsStopStatusTask task = new ModifyDirectGoodsStopStatusTask(getOrderId(), false);
			getContext().handle(task);
			return task.isSucceed();
		}
		return false;
	}
}
