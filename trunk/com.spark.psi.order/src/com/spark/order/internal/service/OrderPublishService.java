package com.spark.order.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.spi.application.SessionDisposeEvent;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.common.utils.date.DateUtil;
import com.spark.common.utils.exceptions.DataStatusExpireException;
import com.spark.order.intf.SelectOrderKey;
import com.spark.order.intf.entity.CuspBillsEntity;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.entity.PageEntity;
import com.spark.order.intf.event.ChangedType;
import com.spark.order.intf.event.PromotionOrderChangedEvent;
import com.spark.order.intf.event.PurchaseOrderChangedEvent;
import com.spark.order.intf.event.PurchaseReturnChangedEvent;
import com.spark.order.intf.event.SalesOrderChangedEvent;
import com.spark.order.intf.event.SalesReturnChangedEvent;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.facade.BillsConstant.BillsWithout;
import com.spark.order.intf.key.SelectCuspMainKey;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.intf.key.SelectMainKey;
import com.spark.order.intf.key.SelectPurchaseByStoreAndGoodsKey;
import com.spark.order.intf.publish.entity.OrderListEntityImpl;
import com.spark.order.intf.publish.entity.SalesDistributeOrderItemImpl;
import com.spark.order.intf.publish.entity.TradingRecordListEntityImpl;
import com.spark.order.intf.task.SaleCancelTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.PageEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.promotion.intf.PromotionListEntity2;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.order.promotion.intf.PromotionTask2;
import com.spark.order.promotion.intf.SelectPromotionKey;
import com.spark.order.promotion.service.PromotionStatusTask;
import com.spark.order.purchase.intf.PurchaseGoods2;
import com.spark.order.purchase.intf.PurchaseGoodsDirect2;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;
import com.spark.order.purchase.intf.entity.PurchaseLatelyInfo;
import com.spark.order.purchase.intf.entity.PurchaseOrder;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;
import com.spark.order.purchase.intf.task.PurchaseCancelTask;
import com.spark.order.purchase.intf.task.PurchaseGoodsDirectTask2;
import com.spark.order.purchase.intf.task.PurchaseGoodsTask2;
import com.spark.order.purchase.intf.task.PurchaseOrdDetTask;
import com.spark.order.purchase.intf.task.PurchaseOrderTask;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleCancelItem;
import com.spark.order.sales.intf.entity.SaleOrder;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.sales.intf.entity.SaleOrderItem;
import com.spark.order.sales.intf.key.SaleDeploymentMainKey;
import com.spark.order.sales.intf.task.SaleOrderTask;
import com.spark.order.service.SelectBillsSql_old;
import com.spark.order.service.util.OrderUtil;
import com.spark.order.util.SaleDistrbuteUtil;
import com.spark.order.util.checking.IServiceCheck;
import com.spark.order.util.checking.ServiceCheckFactory;
import com.spark.order.util.checking.IServiceCheck.CheckParam;
import com.spark.order.util.checking.ServiceCheckFactory.CheckEnum;
import com.spark.order.util.process.IOrderAction;
import com.spark.order.util.process.OrderFactory;
import com.spark.order.util.purchase.CreatePurchaseFactory;
import com.spark.order.util.purchase.ICreatePurchase;
import com.spark.psi.base.Employee;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Partner;
import com.spark.psi.base.Store;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.OrderAction;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.SortType;
import com.spark.psi.publish.ValidationReturn.ErrorLevel;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.OrderListEntity;
import com.spark.psi.publish.order.entity.PromotionItem;
import com.spark.psi.publish.order.entity.PurchaseGoodsInfo;
import com.spark.psi.publish.order.entity.PurchaseGoodsItem;
import com.spark.psi.publish.order.entity.PurchaseOrderInfo;
import com.spark.psi.publish.order.entity.PurchaseReturnInfo;
import com.spark.psi.publish.order.entity.SalesDistributeOrderItem;
import com.spark.psi.publish.order.entity.SalesOrderInfo;
import com.spark.psi.publish.order.entity.SalesReturnInfo;
import com.spark.psi.publish.order.entity.TradingRecordListEntity;
import com.spark.psi.publish.order.key.GetPromotionListKey;
import com.spark.psi.publish.order.key.GetPurchaseOrderBySupplierKey;
import com.spark.psi.publish.order.key.GetPurchaseOrderListKey;
import com.spark.psi.publish.order.key.GetPurchasingGoodsCauseKey;
import com.spark.psi.publish.order.key.GetPurchasingGoodsItemListKey;
import com.spark.psi.publish.order.key.GetSalesDistributeOrderListKey;
import com.spark.psi.publish.order.key.GetSalesOrderByCustomerKey;
import com.spark.psi.publish.order.key.GetSalesOrderListKey;
import com.spark.psi.publish.order.key.GetOrderListKey.OrderType;
import com.spark.psi.publish.order.task.CreatePurchaseGoodsTask;
import com.spark.psi.publish.order.task.CreateSporadicallyPurchaseTask;
import com.spark.psi.publish.order.task.DeletePromotionTask;
import com.spark.psi.publish.order.task.DeletePurchaseGoodsTask;
import com.spark.psi.publish.order.task.DeletePurchaseOrderTask;
import com.spark.psi.publish.order.task.DeleteSalesOrderTask;
import com.spark.psi.publish.order.task.SalesOrderDistributeTask;
import com.spark.psi.publish.order.task.UpdatePromotionStatusTask;
import com.spark.psi.publish.order.task.UpdatePromotionTask;
import com.spark.psi.publish.order.task.UpdatePurchaseGoodsTask;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderStatusTask;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderTask;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnStatusTask;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnTask;
import com.spark.psi.publish.order.task.UpdateSalesOrderStatusTask;
import com.spark.psi.publish.order.task.UpdateSalesOrderTask;
import com.spark.psi.publish.order.task.UpdateSalesReturnStatusTask;
import com.spark.psi.publish.order.task.UpdateSalesReturnTask;
import com.spark.psi.publish.order.task.UpdatePromotionTask.Promotion;
import com.spark.psi.publish.order.task.UpdatePurchaseGoodsTask.PurchaseGoods;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderTask.PurchaseOrderGoodsItem;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnTask.PurchaseReturnGoodsItem;
import com.spark.psi.publish.order.task.UpdateSalesOrderTask.SalesOrderGoodsItem;
import com.spark.psi.publish.order.task.UpdateSalesReturnTask.SalesReturnGoodsItem;

/**
 * <p>
 * 订单对外业务service
 * </p>
 * 
 */
@SuppressWarnings("deprecation")
public class OrderPublishService extends Service {

	protected OrderPublishService() {
		super("com.spark.order.publish.service.OrderPublishService");
	}

	// ============================采购订单=============================
	// -----------------------------------结果提供器---------------------------------
	/**
	 * <p>
	 * 采购订单列表查询
	 * </p>
	 */
	@Publish
	protected class Pub_GetPurchaseOrderProvider extends OneKeyResultProvider<OrderListEntity, GetPurchaseOrderListKey> {

		@SuppressWarnings("unchecked")
		@Override
		protected OrderListEntity provide(Context context, GetPurchaseOrderListKey key) throws Throwable {
			PageEnum pageEnum = null;
			BillsEnum billsEnum = BillsEnum.PURCHASE;
			if (OrderStatus.Denied.isIn(key.getOrderStatus())) {
				pageEnum = PageEnum.NEW;
				if (null != key.getOrderTypes() && OrderType.Return == key.getOrderTypes()[0]) {
					billsEnum = BillsEnum.PURCHASE_CANCEL;
					pageEnum = PageEnum.NEW_CANCEL;
				}
			} else if (OrderStatus.Approval_No.isIn(key.getOrderStatus())) {
				pageEnum = PageEnum.EXAMINE;
			} else if (OrderStatus.CheckingIn_NO.isIn(key.getOrderStatus())) {
				pageEnum = PageEnum.FOLLOW;
			} else if (OrderStatus.finish.isIn(key.getOrderStatus())) {
				pageEnum = PageEnum.LOG;
			}
			if (null == pageEnum) {
				throw new Throwable("查询类型与后台无法对接！");
			}
			// oldKey.setTabEnum(pageEnum);
			SelectMainKey oldKey = new SelectMainKey(billsEnum, pageEnum);
			switch (BillsConstant.getUserAuth(context, BillsEnum.PURCHASE)) {
			case BOSS:
				oldKey.setBoss(true);
				break;
			case MANGER:
				oldKey.setManager(true);
				break;
			case EMPLOYEE:
				oldKey.setEmployee(true);
				break;
			}
			if (CheckIsNull.isNotEmpty(key.getQueryTerm())) {
				oldKey.setStartDate(key.getQueryTerm().getStartTime());
				oldKey.setEndDate(key.getQueryTerm().getEndTime());
			}
			if (CheckIsNull.isNotEmpty(key.getQueryScope())) {
				switch (key.getQueryScope().getType()) {
				case Department:
					oldKey.setDeptGuid(key.getQueryScope().getDepartmentId());
					break;
				case Mine:
					oldKey.setMine(true);
					break;
				}
			}
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				oldKey.setSortField(key.getSortField().getFieldName());
				if (CheckIsNull.isNotEmpty(key.getSortType())) {
					switch (key.getSortType()) {
					case Asc:
						oldKey.setSortType("");
						break;
					case Desc:
						oldKey.setSortType("desc");
						break;
					}
				}
			}
			oldKey.setSearch(key.getSearchText());
			// oldKey.setOffset(key.getOffset());
			// oldKey.setPageSize(key.getCount());
			oldKey.setLimitKey(key);
			PageEntity<OrderInfo> oldEntity = context.find(PageEntity.class, oldKey);
			List<OrderItem> resultList = new ArrayList<OrderItem>();
			for (OrderInfo orderInfo : oldEntity.getList()) {
				resultList.add(DataConverterUtil.toPub_OrderItem(context, BillsEnum.PURCHASE, orderInfo));
			}
			OrderListEntityImpl entity = new OrderListEntityImpl(resultList, oldEntity.getBillsCount());
			entity.setOrderAmount(oldEntity.getOrderAmount());
			entity.setReturnAmount(oldEntity.getCancelAmount());
			return entity;
		}

	}

	/**
	 * 交易记录 供应商未完成交易列表 TradingRecordListEntity +
	 * GetPurchaseOrderBySupplierKey(id) 供应商已完成交易记录列表 TradingRecordListEntity +
	 * GetPurchaseOrderBySupplierKey(id，false)
	 * 
	 */
	@Publish
	protected class Pub_SupplierTradingRecordProvider extends
			OneKeyResultProvider<TradingRecordListEntity, GetPurchaseOrderBySupplierKey> {

		@Override
		protected TradingRecordListEntity provide(Context context, GetPurchaseOrderBySupplierKey key) throws Throwable {
			String type = key.isCompleted() ? BillsWithout.FINISH_NO : BillsWithout.FINISH_YES;
			SelectCuspMainKey oldKey = new SelectCuspMainKey(BillsEnum.PURCHASE, type, key.getId());
			switch (BillsConstant.getUserAuth(context, BillsEnum.PURCHASE)) {
			case BOSS:
				oldKey.setBoss(true);
				break;
			case MANGER:
				oldKey.setManager(true);
				break;
			case EMPLOYEE:
				oldKey.setEmployee(true);
				break;
			}
			if (CheckIsNull.isNotEmpty(key.getQueryTerm())) {
				oldKey.setStartDate(key.getQueryTerm().getStartTime());
				oldKey.setEndDate(key.getQueryTerm().getEndTime());
			}
			if (null == key.getSortField()) {
				key.setSortField(GetPurchaseOrderBySupplierKey.SortField.CreateDate);
			}
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				oldKey.setSortField(key.getSortField().getFieldName());
				if (CheckIsNull.isNotEmpty(key.getSortType())) {
					switch (key.getSortType()) {
					case Asc:
						oldKey.setSortType("");
						break;
					case Desc:
						oldKey.setSortType("desc");
						break;
					}
				}
			}
			oldKey.setSearch(key.getSearchText());
			oldKey.setLimitKey(key);
			List<OrderInfo> oldList = context.getList(OrderInfo.class, oldKey);
			CuspBillsEntity oldEntity = context.find(CuspBillsEntity.class, oldKey);
			List<OrderItem> items = new ArrayList<OrderItem>();
			for (OrderInfo orderInfo : oldList) {
				items.add(DataConverterUtil.toPub_OrderItem(context, BillsEnum.PURCHASE, orderInfo));
			}
			TradingRecordListEntityImpl entity = new TradingRecordListEntityImpl(items, oldEntity.getCancelTime()
					+ oldEntity.getOrderTime());
			entity.setOrderAmount(oldEntity.getOrderReceipt());
			entity.setReturnAmount(oldEntity.getCancelAmount());
			entity.setReturnCount(oldEntity.getCancelTime());
			entity.setTotalAmount(oldEntity.getOrderTotalAmount());
			entity.setOrderCount(oldEntity.getOrderTime());
			return entity;
		}

	}

	/**
	 * <p>
	 * 采购清单
	 * </p>
	 * 获得采购需求清单 查询方法：ListEntity<PurchaseGoodsItem>
	 * <p>
	 */
	@Publish
	protected class Pub_PurchaseGoodsItemProvider extends
			OneKeyResultProvider<ListEntity<PurchaseGoodsItem>, GetPurchasingGoodsItemListKey> {

		@Override
		protected ListEntity<PurchaseGoodsItem> provide(Context context, GetPurchasingGoodsItemListKey key)
				throws Throwable {
			List<PurchaseGoodsItem> dataList = new ArrayList<PurchaseGoodsItem>();
			List<PurchaseGoods2> oldGoods = context.getList(PurchaseGoods2.class);
			for (PurchaseGoods2 goodsInfo : oldGoods) {
				dataList.add(DataConverterUtil.toPub_PurchaseGoodsItem(context, goodsInfo));
			}
			ListEntity<PurchaseGoodsItem> entity = new ListEntity<PurchaseGoodsItem>(dataList, dataList.size());
			return entity;
		}
	}

	/**
	 * <p>
	 * 商品采购原因
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_GetPurchaseGoodsCauseProvider extends OneKeyResultProvider<String, GetPurchasingGoodsCauseKey> {

		@Override
		protected String provide(Context context, GetPurchasingGoodsCauseKey key) throws Throwable {
			PurchaseGoods2 pg = context.find(PurchaseGoods2.class, key.getId());
			return pg == null ? null : pg.getPurchaseCause();
		}

	}

	/**
	 * 获得采购需求清单
	 * 
	 */
	@Publish
	protected class Pub_PurchaseGoodsInfoProvider extends OneKeyResultProvider<PurchaseGoodsInfo, GUID> {

		@Override
		protected PurchaseGoodsInfo provide(Context context, GUID key) throws Throwable {
			// com.spark.order.purchase.intf.entity.PurchaseGoodsInfo info
			// =
			// context.find(com.spark.order.purchase.intf.entity.PurchaseGoodsInfo.class,
			// key);
			PurchaseGoodsInfo result = null;
			PurchaseGoods2 info = context.find(PurchaseGoods2.class, key);
			if (CheckIsNull.isNotEmpty(info)) {
				result = DataConverterUtil.toPub_PurchaseGoodsInfo(context, info);
			} else {
				PurchaseGoodsDirect2 direct = context.find(PurchaseGoodsDirect2.class, key);
				if (CheckIsNull.isNotEmpty(direct)) {
					result = DataConverterUtil.toPub_PurchaseGoodsInfo(context, direct);
				}
			}
			return result;
		}

	}

	/**
	 * 采购订单详情
	 * 
	 */
	@Publish
	protected class Pub_PurchaseOrderInfoProvider extends OneKeyResultProvider<PurchaseOrderInfo, GUID> {

		@Override
		protected PurchaseOrderInfo provide(Context context, GUID key) throws Throwable {
			PurchaseOrder order = context.find(PurchaseOrder.class, key);
			return DataConverterUtil.toPub_PurchaseOrderInfo(context, order);
		}

	}

	/**
	 * <p>
	 * 采购退货单详情
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_PurchaseReturnInfoProvider extends OneKeyResultProvider<PurchaseReturnInfo, GUID> {

		@Override
		protected PurchaseReturnInfo provide(Context context, GUID key) throws Throwable {
			PurchaseCancel cancel = context.find(PurchaseCancel.class, key);
			List<PurchaseCancelItem> dets = context.getList(PurchaseCancelItem.class, key);
			return DataConverterUtil.toPub_PurchaseReturnInfo(context, cancel, dets);
		}

	}

	// -----------------------------------任务处理器---------------------------------
	/**
	 * <p>
	 * 新增采购需求
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_CreatePurchaseGoodsHandler extends SimpleTaskMethodHandler<CreatePurchaseGoodsTask> {

		@Override
		protected void handle(Context context, CreatePurchaseGoodsTask task) throws Throwable {
			// PurchaseGoodsTask goodsTask = new PurchaseGoodsTask();
			PurchaseGoodsTask2 goodsTask = new PurchaseGoodsTask2();
			goodsTask.entity = new PublishToMeUtil(context).getPurchaseGoodsInfo(task);
			goodsTask.entity.setPrice_purchase(-1);
			context.handle(goodsTask, PurchaseGoodsTask2.Method.ADD);
		}
	}

	/**
	 * <p>
	 * 零星采购(新建)入库
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_CreateSporadicallyPurchaseHandler extends
			SimpleTaskMethodHandler<CreateSporadicallyPurchaseTask> {

		@Override
		protected void handle(Context context, CreateSporadicallyPurchaseTask task) throws Throwable {
			this.context = context;
			ICreatePurchase util = CreatePurchaseFactory.getCreatePurchase(context,
					CreatePurchaseFactory.CreateEnum.Odd_Lot);
			com.spark.order.purchase.intf.entity.PurchaseOrderInfo info = getInfo(task);
			List<PurchaseOrderItem> dets = getDets(task);
			if (util.create(info, dets)) {
				new MeToModuleUtil(context).createInStore_odd_lot(info, dets, task.getInventoryItems());
				context.dispatch(new PurchaseOrderChangedEvent(info.getRECID(), ChangedType.Effectual));
			}
		}

		private Context context;

		private List<PurchaseOrderItem> getDets(CreateSporadicallyPurchaseTask task) {
			List<PurchaseOrderItem> dets = new ArrayList<PurchaseOrderItem>();
			PurchaseOrderItem det;
			for (CheckingGoodsItem item : task.getItems()) {
				det = new PurchaseOrderItem();
				det.setCreateDate(System.currentTimeMillis());
				det.setCreator(BillsConstant.getUserName(context));
				MaterialsItem gi = context.find(MaterialsItem.class, item.getGoodsItemId());
				det.setGoodsSpec(gi.getSpec());
				det.setScale(gi.getScale());
				det.setGoodsId(gi.getId());
				det.setGoodsName(gi.getMaterialName());
				det.setGoodsNo(gi.getMaterialNo());
				det.setGoodsCode(gi.getMaterialCode());
				det.setCount(item.getCheckCount());
				det.setPrice(item.getPrice());
				det.setRECID(context.newRECID());
				det.setUnit(gi.getMaterialUnit());
				det.setAmount(det.getCount() * det.getPrice());
				dets.add(det);
			}
			return dets;
		}

		private com.spark.order.purchase.intf.entity.PurchaseOrderInfo getInfo(CreateSporadicallyPurchaseTask task) {
			com.spark.order.purchase.intf.entity.PurchaseOrderInfo info = new com.spark.order.purchase.intf.entity.PurchaseOrderInfo();
			info.setBillsNo(OrderUtil.getBillsNumber(context, BillsEnum.PURCHASE));
			info.setCreateDate(System.currentTimeMillis());
			info.setCreatorId(BillsConstant.getUserGuid(context));
			info.setCreator(task.getPurchaser());
			// 供应商信息
			Partner partner = context.find(Partner.class, task.getSupplierId());
			info.setPartnerName(partner.getName());
			info.setPartnerNamePY(PinyinHelper.getLetter(info.getPartnerName()));
			info.setPartnerId(partner.getId());
			info.setPartnerShortName(partner.getShortName());
			info.setDeptId(BillsConstant.getUser(context).getDepartmentId());
			info.setPurchasePersonName(task.getPurchaser());
			info.setDeliveryDate(task.getPurchaseDate());
			info.setRECID(context.newRECID());
			Store store = context.find(Store.class, task.getStoreId());
			info.setStoreId(task.getStoreId());
			info.setStoreName(store.getName());
			info.setRemark(task.getRemark());
			info.setBillType(TypeEnum.BUY_SPORADIC.getKey());
			info.setStatus(StatusEnum.Finished.getKey());
			return info;
		}
	}

	/**
	 * <p>
	 * 删除采购清单
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_DeletePurchaseGoodsHandler extends SimpleTaskMethodHandler<DeletePurchaseGoodsTask> {

		@Override
		protected void handle(Context context, DeletePurchaseGoodsTask task) throws Throwable {
			PurchaseGoodsTask2 goodsTask = new PurchaseGoodsTask2();
			goodsTask.recid = task.getId();
			context.handle(goodsTask, PurchaseGoodsTask2.Method.DELETE);
		}

	}

	/**
	 * <p>
	 * 删除采购订单、退货单
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_DeletePurchaseOrderHandler extends SimpleTaskMethodHandler<DeletePurchaseOrderTask> {

		@Override
		protected void handle(Context context, DeletePurchaseOrderTask task) throws Throwable {
			if (OrderType.Order == task.getOrderType()) {
				// 删除采购单
				PurchaseOrderTask orderTask = new PurchaseOrderTask();
				orderTask.recid = task.getId();
				context.handle(orderTask, TaskEnum.DELETE);
				if (orderTask.isSucceed()) {
					context.dispatch(new PurchaseOrderChangedEvent(task.getId(), ChangedType.Delete));
				}
			} else {
				// 删除采购退货单
				PurchaseCancelTask cancelTask = new PurchaseCancelTask();
				cancelTask.setEntity(context.find(PurchaseCancel.class, task.getId()));
				context.handle(cancelTask, TaskEnum.DELETE);
				if (cancelTask.isSucceed()) {
					context.dispatch(new PurchaseReturnChangedEvent(task.getId(), ChangedType.Delete));
				}
			}
		}

	}

	/**
	 * 采购需求Task 修改采购需求
	 * 
	 */
	@Publish
	protected class Pub_UpdatePurchaseGoodsHandler extends SimpleTaskMethodHandler<UpdatePurchaseGoodsTask> {

		@Override
		protected void handle(Context context, UpdatePurchaseGoodsTask task) throws Throwable {
			PurchaseGoodsDirectTask2 directTask;
			PurchaseGoodsTask2 purchaseTask;
			for (PurchaseGoods pg : task.getPurchaseGoods()) {
				if (pg.isDirect()) {
					directTask = new PurchaseGoodsDirectTask2();
					directTask.recid = pg.getId();
					directTask.entity = context.find(PurchaseGoodsDirect2.class, pg.getId());
					if (null == directTask.entity) {
						break;
					}
					directTask.entity.setPurchaseCause("直供");// pg.getPurchaseCause());
					directTask.entity.setPrice_purchase(pg.getPrice());
					if (null != pg.getSupplierId() && directTask.entity.getPartnerId() != pg.getSupplierId()) {
						SupplierInfo p = context.find(SupplierInfo.class, pg.getSupplierId());
						// directTask.entity.setPartnerFax(p.getFaxNumber());
						directTask.entity.setPartnerName(p.getName());
						directTask.entity.setPartnerId(p.getId());
						directTask.entity.setPartnerShortName(p.getShortName());
					}
					if (null != pg.getContactId() && directTask.entity.getContactId() != pg.getContactId()) {
						ContactBookInfo cbi = context.find(ContactBookInfo.class, pg.getContactId());
						directTask.entity.setContactId(pg.getContactId());
						directTask.entity.setContactName(cbi.getName());
						directTask.entity.setContactPhone(cbi.getMobileNo());
						directTask.entity.setContactTel(cbi.getLandLineNumber());
					}
					if (pg.getPrice() >= 0) {
						directTask.entity.setPrice_purchase(pg.getPrice());
					}
					// 取订单数据库
					SelectPurchaseByStoreAndGoodsKey key = new SelectPurchaseByStoreAndGoodsKey(directTask.entity
							.getGoodsItemId(), directTask.entity.getSourceId());
					PurchaseLatelyInfo bi = context.find(PurchaseLatelyInfo.class, key);
					if (null != bi) {
						// 上次采购单价
						key.setCuspGuid(bi.getPartnerId());
						bi = context.find(PurchaseLatelyInfo.class, key);
						// if (null != bi) {
						// directTask.entity.setPrice_lastPurchase(bi
						// .getProPrice());
						// }
					}
					context.handle(directTask, PurchaseGoodsDirectTask2.Method.MODIFY);
				} else {
					purchaseTask = new PurchaseGoodsTask2();
					purchaseTask.recid = pg.getId();
					purchaseTask.entity = context.find(PurchaseGoods2.class, pg.getId());
					if (null != pg.getPurchaseCause()) {
						purchaseTask.entity.setPurchaseCause(pg.getPurchaseCause());
					}
					purchaseTask.entity.setPrice_purchase(pg.getPrice());
					if (null != pg.getSupplierId() && purchaseTask.entity.getPartnerId() != pg.getSupplierId()) {
						SupplierInfo p = context.find(SupplierInfo.class, pg.getSupplierId());
						// purchaseTask.entity.setPartnerFax(p.getFaxNumber());
						purchaseTask.entity.setPartnerName(p.getName());
						purchaseTask.entity.setPartnerId(p.getId());
						purchaseTask.entity.setPartnerShortName(p.getShortName());
					}
					if (null != pg.getContactId() && purchaseTask.entity.getContactId() != pg.getContactId()) {
						ContactBookInfo cbi = context.find(ContactBookInfo.class, pg.getContactId());
						purchaseTask.entity.setContactId(pg.getContactId());
						purchaseTask.entity.setContactName(cbi.getName());
						purchaseTask.entity.setContactPhone(cbi.getMobileNo());
						purchaseTask.entity.setContactTel(cbi.getLandLineNumber());
					}
					if (pg.getPrice() >= 0) {
						purchaseTask.entity.setPrice_purchase(pg.getPrice());
					}
					// 取订单数据库
					SelectPurchaseByStoreAndGoodsKey key = new SelectPurchaseByStoreAndGoodsKey(purchaseTask.entity
							.getGoodsItemId(), purchaseTask.entity.getSourceId());
					PurchaseLatelyInfo bi = context.find(PurchaseLatelyInfo.class, key);
					if (null != bi) {
						// 上次采购单价
						key.setCuspGuid(bi.getPartnerId());
						bi = context.find(PurchaseLatelyInfo.class, key);
						// if (null != bi) {
						// purchaseTask.entity.setPrice_lastPurchase(bi
						// .getProPrice());
						// }
					}
					context.handle(purchaseTask, PurchaseGoodsTask2.Method.MODIFY);
				}
			}
		}

	}

	/**
	 * <p>
	 * 新建采购订单
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_CreatePurchaseOrderHandler extends
			TaskMethodHandler<UpdatePurchaseOrderTask, UpdatePurchaseOrderTask.Method> {

		protected Pub_CreatePurchaseOrderHandler() {
			super(UpdatePurchaseOrderTask.Method.Create);
		}

		@Override
		protected void handle(Context context, UpdatePurchaseOrderTask task) throws Throwable {
			// 校验
			boolean isError = false;
			for (int i = 0; i < task.getPurchaseOrderGoodsItems().length; i++) {
				PurchaseOrderGoodsItem item = task.getPurchaseOrderGoodsItems()[i];
				// 商品采购数量大于0检查
				IServiceCheck sc = ServiceCheckFactory.getServiceCheck(context, new CheckParam(item.getCount(),
						CheckEnum.goods_count_zero));
				if (sc.doError()) {
					task.addItemErrors(i, UpdatePurchaseOrderTask.Error.GoodsCount_Zero);
				}
			}
			if (isError) {
				task.setSuccess(false);
				return;
			}

			// 执行
			ICreatePurchase util = CreatePurchaseFactory.getCreatePurchase(context,
					CreatePurchaseFactory.CreateEnum.Plan);
			PublishToMeUtil pu = new PublishToMeUtil(context);
			PurchaseOrder po = pu.getPurchaseOrder(task);
			try {
				if (util.create(po.getInfo(), po.getDets())) {
					// ----------
					task.setId(po.getInfo().getRECID());
					// ======
					context.dispatch(new PurchaseOrderChangedEvent(task.getId(), ChangedType.SAVE));

					for (Integer index : util.getDirectedIndexs()) {
						task.addItemErrors(index, UpdatePurchaseOrderTask.Error.GoodsDirected, ErrorLevel.Tooltip);
					}
					// 删除采购清单
					for (UpdatePurchaseOrderTask.PurchaseOrderGoodsItem goods : task.getPurchaseOrderGoodsItems()) {
						if (task.isDirectSupply()) {
							context.handle(new PurchaseGoodsDirectTask2(goods.getPurchaseGoodsItemId()),
									PurchaseGoodsDirectTask2.Method.DELETE);
						} else {
							context.handle(new PurchaseGoodsTask2(goods.getPurchaseGoodsItemId()),
									PurchaseGoodsTask2.Method.DELETE);
						}
					}
				}
			} finally {
				for (UpdatePurchaseOrderTask.PurchaseOrderGoodsItem goods : task.getPurchaseOrderGoodsItems()) {
					if (task.isDirectSupply()) {
						BillsConstant.useingDirectGoods.remove(goods.getPurchaseGoodsItemId());
					}
				}
			}
		}

	}

	/**
	 * <p>
	 * 修改采购订单
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_UpdatePurchaseOrderHandler extends
			TaskMethodHandler<UpdatePurchaseOrderTask, UpdatePurchaseOrderTask.Method> {

		protected Pub_UpdatePurchaseOrderHandler() {
			super(UpdatePurchaseOrderTask.Method.Update);
		}

		@Override
		protected void handle(Context context, UpdatePurchaseOrderTask task) throws Throwable {
			// 校验
			boolean isError = false;
			for (int i = 0; i < task.getPurchaseOrderGoodsItems().length; i++) {
				PurchaseOrderGoodsItem item = task.getPurchaseOrderGoodsItems()[i];
				// 商品采购数量大于0检查
				IServiceCheck sc = ServiceCheckFactory.getServiceCheck(context, new CheckParam(item.getCount(),
						CheckEnum.goods_count_zero));
				if (sc.doError()) {
					task.addItemErrors(i, UpdatePurchaseOrderTask.Error.GoodsCount_Zero);
					isError = true;
				}
			}
			if (isError)
				return;

			// 执行
			PurchaseOrdDetTask detTask = new PurchaseOrdDetTask();
			detTask.billsGuid = task.getId();
			context.handle(detTask, TaskEnum.DELETE_LORD);
			double totalAmount = 0;
			for (PurchaseOrderGoodsItem item : task.getPurchaseOrderGoodsItems()) {
				detTask.entity = getItem(context, item);
				detTask.entity.setBillsId(task.getId());
				context.handle(detTask, TaskEnum.ADD);
				totalAmount += detTask.entity.getAmount();
			}
			// 修改订单主表信息
			PurchaseOrderTask orderTask = new PurchaseOrderTask();
			orderTask.entity = context.find(com.spark.order.purchase.intf.entity.PurchaseOrderInfo.class, task.getId());
			orderTask.entity.setRemark(task.getRemark());
			orderTask.entity.setTotalAmount(totalAmount);
			context.handle(orderTask, TaskEnum.MODIFY);

			context.dispatch(new PurchaseOrderChangedEvent(task.getId(), ChangedType.SAVE));
		}

		private PurchaseOrderItem getItem(Context context, PurchaseOrderGoodsItem goods) {
			PurchaseOrderItem item = new PurchaseOrderItem();
			item.setCreateDate(System.currentTimeMillis());
			item.setCreator(BillsConstant.getUserName(context));
			MaterialsItem gi = context.find(MaterialsItem.class, goods.getGoodsItemId());
			item.setGoodsSpec(gi.getSpec());
			item.setScale(gi.getScale());
			item.setGoodsId(gi.getId());
			item.setGoodsName(gi.getMaterialName());
			item.setGoodsNo(gi.getMaterialCode());
			item.setCount(goods.getCount());
			item.setPrice(goods.getPrice());
			item.setRECID(context.newRECID());
			item.setUnit(gi.getMaterialUnit());
			item.setGoodsCode(gi.getMaterialCode());
			item.setAmount(DoubleUtil.mul(item.getCount() , item.getPrice()));
			return item;
		}

	}

	/**
	 * <p>
	 * 新建采购退货单
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_CreatePurchaseReturnHandler extends
			TaskMethodHandler<UpdatePurchaseReturnTask, UpdatePurchaseReturnTask.Method> {

		protected Pub_CreatePurchaseReturnHandler() {
			super(UpdatePurchaseReturnTask.Method.Create);
		}

		@Override
		protected void handle(Context context, UpdatePurchaseReturnTask task) throws Throwable {
			GUID orderId = task.getId();
			PurchaseCancelTask oldTask = new PurchaseCancelTask();
			PublishToMeUtil util = new PublishToMeUtil(context);
			oldTask.setEntity(util.getPurchaseCancel(orderId, task));
			List<PurchaseCancelItem> detailList = new ArrayList<PurchaseCancelItem>();
			double totalAmount = 0;
			for (PurchaseReturnGoodsItem item : task.getPurchaseReturnGoodsItems()) {
				detailList.add(util.getPurchaseCancelItem(orderId, item));
				totalAmount += item.getAmount();
			}
			oldTask.setList(detailList);
			oldTask.getEntity().setTotalAmount(totalAmount);
			context.handle(oldTask, TaskEnum.ADD);
			if (oldTask.isSucceed()) {
				context.dispatch(new PurchaseReturnChangedEvent(orderId, ChangedType.SAVE));
			}
		}

	}

	/**
	 * <p>
	 * 修改采购退货单
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_UpdatePurchaseReturnHandler extends
			TaskMethodHandler<UpdatePurchaseReturnTask, UpdatePurchaseReturnTask.Method> {

		protected Pub_UpdatePurchaseReturnHandler() {
			super(UpdatePurchaseReturnTask.Method.Update);
		}

		@Override
		protected void handle(Context context, UpdatePurchaseReturnTask task) throws Throwable {

			GUID orderId = task.getId();
			PurchaseCancelTask oldTask = new PurchaseCancelTask();
			PublishToMeUtil util = new PublishToMeUtil(context);
			oldTask.setEntity(util.getPurchaseCancel(orderId, task));
			List<PurchaseCancelItem> detailList = new ArrayList<PurchaseCancelItem>();
			for (PurchaseReturnGoodsItem item : task.getPurchaseReturnGoodsItems()) {
				detailList.add(util.getPurchaseCancelItem(orderId, item));
			}
			oldTask.setList(detailList);
			//
			PurchaseCancel pc = context.find(PurchaseCancel.class, task.getId());
			oldTask.getEntity().setCreateDate(pc.getCreateDate());
			context.handle(oldTask, TaskEnum.MODIFY);
			// }
			if (oldTask.isSucceed()) {
				context.dispatch(new PurchaseReturnChangedEvent(orderId, ChangedType.SAVE));
			}
		}

	}

	/**
	 * <p>
	 * 修改采购订单状态
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_UpdatePurchaseOrderStatusHandler extends SimpleTaskMethodHandler<UpdatePurchaseOrderStatusTask> {

		@Override
		protected void handle(Context context, UpdatePurchaseOrderStatusTask task) throws Throwable {
			IOrderAction action = OrderFactory.getOrderAction(context, BillsEnum.PURCHASE);
			action.setCause(task.getCause());
			if (action.action(task.getId(), task.getAction(), task.isIgnoredWarning())) {
				context.dispatch(new PurchaseOrderChangedEvent(task.getId(), task.getAction()));
				if (action.getNewstatus().isIn(StatusEnum.Store_N0, StatusEnum.Consignment_No)
						&& !task.getAction().isIn(OrderAction.Stop, OrderAction.Execut)) {
					context.dispatch(new PurchaseOrderChangedEvent(task.getId(), ChangedType.Effectual));
				}
			} else {
				for (IServiceCheck sc : action.getServiceCheck()) {
					if (CheckEnum.inventory_amount_upper == sc.getCheckEnum()) {
						task.addItemErrors(sc.getIndex(), UpdatePurchaseOrderStatusTask.Error.inventory_amount_upper,
								ErrorLevel.Warning);
					} else if (CheckEnum.inventory_count_upper == sc.getCheckEnum()) {
						task.addItemErrors(sc.getIndex(), UpdatePurchaseOrderStatusTask.Error.inventory_count_upper,
								ErrorLevel.Warning);
					}
				}
				context.abort();
			}
		}

	}

	/**
	 * <p>
	 * 修改采购退货单状态
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_UpdatePurchaseReturnstatusHandler extends
			SimpleTaskMethodHandler<UpdatePurchaseReturnStatusTask> {

		@Override
		protected void handle(Context context, UpdatePurchaseReturnStatusTask task) throws Throwable {
			IOrderAction action = OrderFactory.getOrderAction(context, BillsEnum.PURCHASE_CANCEL);
			action.setCause(task.getCause());
			if (action.action(task.getId(), task.getAction())) {
				context.dispatch(new PurchaseReturnChangedEvent(task.getId(), task.getAction()));
				if (action.getNewstatus().isIn(StatusEnum.Store_N0)
						&& !task.getAction().isIn(OrderAction.Stop, OrderAction.Execut)) {
					context.dispatch(new PurchaseReturnChangedEvent(task.getId(), ChangedType.Effectual));
				}
			} else {
				context.abort();
			}
		}

	}

	// ============================采购退货（暂时和采购订单在一起，为采购管理）=============================
	// -----------------------------------结果提供器---------------------------------

	// -----------------------------------任务处理器---------------------------------

	// ============================销售订单=============================
	// -----------------------------------结果提供器---------------------------------
	/**
	 * 销售订单列表查询
	 * 
	 */
	@Publish
	protected class Pub_GetSalesOrderProvider extends OneKeyResultProvider<OrderListEntity, GetSalesOrderListKey> {

		@SuppressWarnings("unchecked")
		@Override
		protected OrderListEntity provide(Context context, GetSalesOrderListKey key) throws Throwable {
			PageEnum pageEnum = null;
			if (OrderStatus.Denied.isIn(key.getOrderStatus())) {
				pageEnum = PageEnum.NEW;
			} else if (OrderStatus.Approval_No.isIn(key.getOrderStatus())) {
				pageEnum = PageEnum.EXAMINE;
			} else if (OrderStatus.CheckingIn_NO.isIn(key.getOrderStatus())) {
				pageEnum = PageEnum.FOLLOW;
			} else if (OrderStatus.finish.isIn(key.getOrderStatus())) {
				pageEnum = PageEnum.LOG;
			}
			if (null == pageEnum) {
				throw new Throwable("查询类型与后台无法对接！");
			}
			BillsEnum billsEnum = BillsEnum.SALE;
			if (null != key.getOrderTypes() && 1 == key.getOrderTypes().length
					&& OrderType.Return == key.getOrderTypes()[0]) {
				billsEnum = BillsEnum.SALE_CANCEL;
				pageEnum = PageEnum.NEW_CANCEL;
			}
			SelectMainKey oldKey = new SelectMainKey(billsEnum, pageEnum);
			switch (BillsConstant.getUserAuth(context, BillsEnum.SALE)) {
			case BOSS:
				oldKey.setBoss(true);
				break;
			case MANGER:
				oldKey.setManager(true);
				break;
			case EMPLOYEE:
				oldKey.setEmployee(true);
				break;
			}
			if (CheckIsNull.isNotEmpty(key.getQueryTerm())) {
				oldKey.setStartDate(key.getQueryTerm().getStartTime());
				oldKey.setEndDate(key.getQueryTerm().getEndTime());
			}
			if (CheckIsNull.isNotEmpty(key.getQueryScope())) {
				switch (key.getQueryScope().getType()) {
				case Department:
					oldKey.setDeptGuid(key.getQueryScope().getDepartmentId());
					break;
				case Mine:
					oldKey.setMine(true);
					break;
				}
			}
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				oldKey.setSortField(key.getSortField().getFieldName());
				if (CheckIsNull.isNotEmpty(key.getSortType())) {
					switch (key.getSortType()) {
					case Asc:
						oldKey.setSortType("");
						break;
					case Desc:
						oldKey.setSortType("desc");
						break;
					}
				}
			}
			oldKey.setSearch(key.getSearchText());
			// oldKey.setOffset(key.getOffset());
			// oldKey.setPageSize(key.getCount());
			oldKey.setLimitKey(key);
			// 销售跟踪页签单独走流程=========
			List<OrderItem> resultList = new ArrayList<OrderItem>();
			if (BillsEnum.SALE == oldKey.getBillsEnum() && PageEnum.FOLLOW == oldKey.getTabEnum()) {
				SelectBillsSql_old sql = new SelectBillsSql_old(context, new SelectOrderKey(key));
				double orderAmount = 0;
				double returnAmount = 0;
				for (OrderInfo orderInfo : sql.getList(sql.executeQuery())) {
					if (TypeEnum.CANCEL.getKey().equals(orderInfo.getBillType())) {
						returnAmount += orderInfo.getTotalAmount();
					} else {
						// orderAmount += orderInfo.getTotalAmount();
						orderAmount = DoubleUtil.sum(orderAmount, orderInfo.getTotalAmount());
					}
					resultList.add(DataConverterUtil.toPub_OrderItem(context, BillsEnum.SALE, orderInfo));
				}
				OrderListEntityImpl entity = new OrderListEntityImpl(resultList, resultList.size());
				entity.setOrderAmount(orderAmount);
				entity.setReturnAmount(returnAmount);
				return entity;
			}
			// =============销售跟踪单独流程END==========
			PageEntity<OrderInfo> oldEntity = context.find(PageEntity.class, oldKey);

			for (OrderInfo orderInfo : oldEntity.getList()) {
				resultList.add(DataConverterUtil.toPub_OrderItem(context, BillsEnum.SALE, orderInfo));
			}
			OrderListEntityImpl entity = new OrderListEntityImpl(resultList, oldEntity.getBillsCount());
			entity.setOrderAmount(oldEntity.getOrderAmount());
			entity.setReturnAmount(oldEntity.getCancelAmount());
			return entity;
		}

	}

	/**
	 * 交易记录 客户未完成交易列表 TradingRecordListEntity<TradingRecordItem> +
	 * GetSalesOrderByCustomerKey(id) 客户已完成交易记录列表
	 * TradingRecordListEntity<TradingRecordItem> +
	 * GetSalesOrderByCustomerKey(id，false)
	 * 
	 */
	@Publish
	protected class Pub_CustomerTradingRecordProvider extends
			OneKeyResultProvider<TradingRecordListEntity, GetSalesOrderByCustomerKey> {

		@Override
		protected TradingRecordListEntity provide(Context context, GetSalesOrderByCustomerKey key) throws Throwable {
			String type = key.isCompleted() ? BillsWithout.FINISH_NO : BillsWithout.FINISH_YES;
			SelectCuspMainKey oldKey = new SelectCuspMainKey(BillsEnum.SALE, type, key.getId());
			switch (BillsConstant.getUserAuth(context, BillsEnum.SALE)) {
			case BOSS:
				oldKey.setBoss(true);
				break;
			case MANGER:
				oldKey.setManager(true);
				break;
			case EMPLOYEE:
				oldKey.setEmployee(true);
				break;
			}
			if (CheckIsNull.isNotEmpty(key.getQueryTerm())) {
				oldKey.setStartDate(key.getQueryTerm().getStartTime());
				oldKey.setEndDate(key.getQueryTerm().getEndTime());
			}
			// if(CheckIsNull.isNotEmpty(key.getQueryScope())){
			// switch(key.getQueryScope().getType()){
			// case Department:
			// oldKey.setDeptGuid(key.getQueryScope().getDepartmentId());
			// break;
			// case Mine:
			// oldKey.setMine(true);
			// break;
			// }
			// }
			if (null == key.getSortField()) {
				key.setSortField(GetSalesOrderByCustomerKey.SortField.CreateDate);
			}
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				oldKey.setSortField(key.getSortField().getFieldName());
				if (CheckIsNull.isNotEmpty(key.getSortType())) {
					switch (key.getSortType()) {
					case Asc:
						oldKey.setSortType("");
						break;
					case Desc:
						oldKey.setSortType("desc");
						break;
					}
				}
			}
			oldKey.setSearch(key.getSearchText());
			// oldKey.setOffset(key.getOffset());
			// oldKey.setPageSize(key.getCount());
			oldKey.setLimitKey(key);
			List<OrderInfo> oldList = context.getList(OrderInfo.class, oldKey);
			CuspBillsEntity oldEntity = context.find(CuspBillsEntity.class, oldKey);
			List<OrderItem> items = new ArrayList<OrderItem>();
			for (OrderInfo orderInfo : oldList) {
				items.add(DataConverterUtil.toPub_OrderItem(context, BillsEnum.SALE, orderInfo));
			}
			TradingRecordListEntityImpl entity = new TradingRecordListEntityImpl(items, oldEntity.getCancelTime()
					+ oldEntity.getOrderTime());
			entity.setOrderAmount(oldEntity.getOrderReceipt());
			entity.setReturnAmount(oldEntity.getCancelAmount());
			entity.setReturnCount(oldEntity.getCancelTime());
			entity.setTotalAmount(oldEntity.getOrderTotalAmount());
			entity.setOrderCount(oldEntity.getOrderTime());
			return entity;
		}

	}

	/**
	 * 销售订单详情维护
	 * 
	 */
	@Publish
	protected class Pub_GetSaleOrderInfoProvider extends OneKeyResultProvider<SalesOrderInfo, GUID> {

		@Override
		protected SalesOrderInfo provide(Context context, GUID key) throws Throwable {
			SaleOrder order = context.find(SaleOrder.class, key);
			if (CheckIsNull.isEmpty(order)) {
				return null;
			} else {
				return DataConverterUtil.toPub_SalesOrderInfo(context, order);
			}
		}

	}

	/**
	 * 销售退货单单详情维护
	 * 
	 */
	@Publish
	protected class Pub_GetSalesReturnInfoProvider extends OneKeyResultProvider<SalesReturnInfo, GUID> {

		@Override
		protected SalesReturnInfo provide(Context context, GUID key) throws Throwable {
			SaleCancel cancel = context.find(SaleCancel.class, key);
			List<SaleCancelItem> dets = context.getList(SaleCancelItem.class, key);
			return DataConverterUtil.toPub_SalesReturnInfo(context, cancel, dets);
		}

	}

	// -----------------------------------任务处理器---------------------------------
	/**
	 * 删除销售订单,退货单
	 * 
	 */
	@Publish
	protected class Pub_DeleteSalesOrderHandler extends SimpleTaskMethodHandler<DeleteSalesOrderTask> {

		@Override
		protected void handle(Context context, DeleteSalesOrderTask task) throws Throwable {
			// 删除采购单
			SaleOrderTask orderTask = new SaleOrderTask();
			orderTask.recid = task.getId();
			context.handle(orderTask, TaskEnum.DELETE);
			if (orderTask.isSucceed()) {
				context.dispatch(new SalesOrderChangedEvent(task.getId(), ChangedType.Delete));
			}
			// 删除采购退货单
			SaleCancelTask cancelTask = new SaleCancelTask();
			cancelTask.setEntity(context.find(SaleCancel.class, task.getId()));
			context.handle(cancelTask, TaskEnum.DELETE);
			if (orderTask.isSucceed()) {
				context.dispatch(new SalesReturnChangedEvent(task.getId(), ChangedType.Delete));
			}
		}

	}

	/**
	 * <p>
	 * 新建销售订单
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_Create_SalesOrderHandler extends
			TaskMethodHandler<UpdateSalesOrderTask, UpdateSalesOrderTask.Method> {

		protected Pub_Create_SalesOrderHandler() {
			super(UpdateSalesOrderTask.Method.Create);
		}

		@Override
		protected void handle(Context context, UpdateSalesOrderTask task) throws Throwable {
			// 校验
			boolean isError = false;
			for (int i = 0; i < task.getSalesOrderGoodsItems().length; i++) {
				SalesOrderGoodsItem item = task.getSalesOrderGoodsItems()[i];
				// 商品采购数量大于0检查
				IServiceCheck sc = ServiceCheckFactory.getServiceCheck(context, new CheckParam(item.getCount(),
						CheckEnum.goods_count_zero));
				if (sc.doError()) {
					task.addItemErrors(i, UpdateSalesOrderTask.Error.GoodsCount_Zero);
					isError = true;
				}

				// 检查促销数量
				if (null != item.getPromotionId()) {
					sc = ServiceCheckFactory.getServiceCheck(context, new CheckParam(item.getPromotionId(), item
							.getCount(), item.getPrice(), CheckEnum.promotion));
					if (sc.doError()) {
						task.addItemErrors(i, UpdateSalesOrderTask.Error.promotion);
						isError = true;
					}
				}
			}
			if (isError) {
				task.setSuccess(false);
				return;
			}
			GUID orderId = task.getId() == null ? context.newRECID() : task.getId();
			PublishToMeUtil util = new PublishToMeUtil(context);
			List<SaleOrderItem> detailList = new ArrayList<SaleOrderItem>();
			for (SalesOrderGoodsItem item : task.getSalesOrderGoodsItems()) {
				detailList.add(util.getSaleOrderItem(orderId, item));
			}
			SaleOrderTask oldTask = new SaleOrderTask();
			oldTask.order = new SaleOrder(util.getSaleOrderInfo(orderId, task), detailList);
			context.handle(oldTask, TaskEnum.ADD);
			if (oldTask.isSucceed()) {
				task.setId(oldTask.entity.getRECID());
				context.dispatch(new SalesOrderChangedEvent(orderId, ChangedType.SAVE));
			}
		}

	}

	/**
	 * <p>
	 * 修改销售订单
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_Update_SalesOrderHandler extends
			TaskMethodHandler<UpdateSalesOrderTask, UpdateSalesOrderTask.Method> {

		protected Pub_Update_SalesOrderHandler() {
			super(UpdateSalesOrderTask.Method.Update);
		}

		@Override
		protected void handle(Context context, UpdateSalesOrderTask task) throws Throwable {
			// 校验
			boolean isError = false;
			for (int i = 0; i < task.getSalesOrderGoodsItems().length; i++) {
				SalesOrderGoodsItem item = task.getSalesOrderGoodsItems()[i];
				// 商品采购数量大于0检查
				IServiceCheck sc = ServiceCheckFactory.getServiceCheck(context, new CheckParam(item.getCount(),
						CheckEnum.goods_count_zero));
				if (sc.doError()) {
					task.addItemErrors(i, UpdateSalesOrderTask.Error.GoodsCount_Zero);
					isError = true;
				}

				// 检查促销数量
				if (null != item.getPromotionId()) {
					sc = ServiceCheckFactory.getServiceCheck(context, new CheckParam(item.getPromotionId(), item
							.getCount(), item.getPrice(), CheckEnum.promotion));
					if (sc.doError()) {
						task.addItemErrors(i, UpdateSalesOrderTask.Error.promotion);
						isError = true;
					}
				}
			}
			if (isError)
				return;
			GUID orderId = task.getId();
			PublishToMeUtil util = new PublishToMeUtil(context);
			List<SaleOrderItem> detailList = new ArrayList<SaleOrderItem>();
			for (SalesOrderGoodsItem item : task.getSalesOrderGoodsItems()) {
				detailList.add(util.getSaleOrderItem(orderId, item));
			}
			SaleOrderTask oldTask = new SaleOrderTask();
			oldTask.order = new SaleOrder(util.getSaleOrderInfo(orderId, task), detailList);
			//
			SaleOrderInfo sale = context.find(SaleOrderInfo.class, orderId);
			oldTask.order.getInfo().setCreateDate(sale.getCreateDate());
			oldTask.order.getInfo().setStatus(sale.getStatus());

			context.handle(oldTask, TaskEnum.MODIFY);
			if (oldTask.isSucceed()) {
				context.dispatch(new SalesOrderChangedEvent(orderId, ChangedType.SAVE));
			}
		}

	}

	/**
	 * <p>
	 * 新建销售退货单
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_Create_SalesReturnHandler extends
			TaskMethodHandler<UpdateSalesReturnTask, UpdateSalesReturnTask.Method> {

		protected Pub_Create_SalesReturnHandler() {
			super(UpdateSalesReturnTask.Method.Create);
		}

		@Override
		protected void handle(Context context, UpdateSalesReturnTask task) throws Throwable {
			// GUID orderId = context.newRECID();
			GUID orderId = task.getId() == null ? context.newRECID() : task.getId();
			SaleCancelTask oldTask = new SaleCancelTask();
			PublishToMeUtil util = new PublishToMeUtil(context);
			oldTask.setEntity(util.getSaleCancel(orderId, task));
			List<SaleCancelItem> detailList = new ArrayList<SaleCancelItem>();
			for (SalesReturnGoodsItem item : task.getSalesReturnGoodsItems()) {
				detailList.add(util.getSaleCancelItem(orderId, item));
			}
			oldTask.setList(detailList);
			// if (null == cancel.getRECID()) {
			context.handle(oldTask, TaskEnum.ADD);
			// } else {
			// context.handle(oldTask, TaskEnum.MODIFY);
			// }
			if (oldTask.isSucceed()) {
				task.setId(orderId);
				context.dispatch(new PurchaseReturnChangedEvent(orderId, ChangedType.SAVE));
			}
		}

	}

	/**
	 * <p>
	 * 修改销售退货单
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_Update_SalesReturnHandler extends
			TaskMethodHandler<UpdateSalesReturnTask, UpdateSalesReturnTask.Method> {

		protected Pub_Update_SalesReturnHandler() {
			super(UpdateSalesReturnTask.Method.Update);
		}

		@Override
		protected void handle(Context context, UpdateSalesReturnTask task) throws Throwable {
			GUID orderId = task.getId();
			SaleCancelTask oldTask = new SaleCancelTask();
			PublishToMeUtil util = new PublishToMeUtil(context);
			oldTask.setEntity(util.getSaleCancel(orderId, task));
			List<SaleCancelItem> detailList = new ArrayList<SaleCancelItem>();
			for (SalesReturnGoodsItem item : task.getSalesReturnGoodsItems()) {
				detailList.add(util.getSaleCancelItem(orderId, item));
			}
			oldTask.setList(detailList);
			// if (null == cancel.getRECID()) {
			// context.handle(oldTask, TaskEnum.ADD);
			// } else {
			//
			SaleCancel sale = context.find(SaleCancel.class, orderId);
			oldTask.getEntity().setCreateDate(sale.getCreateDate());

			context.handle(oldTask, TaskEnum.MODIFY);
			// }
			if (oldTask.isSucceed()) {
				context.dispatch(new PurchaseReturnChangedEvent(orderId, ChangedType.SAVE));
			}
		}

	}

	/**
	 * 修改销售订单状态
	 * 
	 */
	@Publish
	protected class Pub_UpdateSalesOrderStatusHandler extends SimpleTaskMethodHandler<UpdateSalesOrderStatusTask> {

		@Override
		protected void handle(Context context, UpdateSalesOrderStatusTask task) throws Throwable {
			IOrderAction oa = OrderFactory.getOrderAction(context, BillsEnum.SALE);
			oa.setCause(task.getCause());
			oa.setPlanOutDate(task.getPlanOutDate());
			boolean b = false;
			try {
				b = oa.action(task.getId(), task.getActions());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (b) {
				context.dispatch(new SalesOrderChangedEvent(task.getId(), task.getActions()));
				if (oa.getNewstatus().isIn(StatusEnum.Store_N0)
						&& !task.getActions().isIn(OrderAction.Stop, OrderAction.Execut)) {
					context.dispatch(new SalesOrderChangedEvent(task.getId(), ChangedType.Effectual));
				}
			} else {
				if (null == task.getPlanOutDate() || 0 == task.getPlanOutDate()) {
					task.addError(UpdateSalesOrderStatusTask.Error.SetPlanDate);
				} else {
					task.addError(UpdateSalesOrderStatusTask.Error.PromotionCountError);
				}
				task.setSuccess(false);
				context.abort();
			}
		}

	}

	/**
	 * 修改销售退货单状态
	 * 
	 */
	@Publish
	protected class Pub_UpdateSalesReturnstatusHandler extends SimpleTaskMethodHandler<UpdateSalesReturnStatusTask> {

		@Override
		protected void handle(Context context, UpdateSalesReturnStatusTask task) throws Throwable {
			IOrderAction oa = OrderFactory.getOrderAction(context, BillsEnum.SALE_CANCEL);
			oa.setCause(task.getCause());
			if (oa.action(task.getId(), task.getActions())) {
				context.dispatch(new SalesReturnChangedEvent(task.getId(), task.getActions()));
				if (oa.getNewstatus().isIn(StatusEnum.Store_N0)
						&& !task.getActions().isIn(OrderAction.Stop, OrderAction.Execut)) {
					context.dispatch(new SalesReturnChangedEvent(task.getId(), ChangedType.Effectual));
				}
			} else {
				context.abort();
			}
		}

	}

	// ============================销售退货（暂时和销售订单在一起，为销售管理）=============================
	// -----------------------------------结果提供器---------------------------------

	// -----------------------------------任务处理器---------------------------------

	// ============================促销管理=============================
	// -----------------------------------结果提供器---------------------------------

	/**
	 * <p>
	 * 促销列表
	 * </p>
	 * 查询未确认的促销列表 ListEntity<PromotionItem> + GetPromotionListKey
	 * promotionstatus = PromotionStatus.Unrecognized 查询待审批的促销列表
	 * ListEntity<PromotionItem> + GetPromotionListKey promotionstatus =
	 * PromotionStatus.Examine 查询促销中的促销列表 ListEntity<PromotionItem> +
	 * GetPromotionListKey promotionstatus = PromotionStatus.Promotion 查询促销记录
	 * ListEntity<PromotionItem> + GetPromotionListKey promotionstatus = null
	 * 
	 */
	@Publish
	protected class Pub_GetPromotionItemProvider extends
			OneKeyResultProvider<ListEntity<PromotionItem>, GetPromotionListKey> {

		@Override
		protected ListEntity<PromotionItem> provide(Context context, GetPromotionListKey key) throws Throwable {
			SelectPromotionKey selectKey = new SelectPromotionKey();
			// 外部key装换成内部key
			selectKey.setLimitKey(key);
			selectKey.setQueryTerm(key.getQueryTerm());
			// 状态
			List<PromotionStatus2> statusList = PromotionStatus2.getPubToMeList(key.getPromotionStatus());
			// ==============================================
			// if(UserAuthEnum.BOSS != BillsConstant.getUserAuth(context,
			// BillsEnum.SALE_PROMOTION)){
			// statusList.remove(PromotionStatus2.Issue);
			// }
			// 加入状态
			selectKey.setStatuss(statusList.toArray(new PromotionStatus2[statusList.size()]));
			// 业务权限判断【业务判断】
			if (PromotionStatus2.Submit.isInEnum(selectKey.getStatus())) {
				selectKey.setScopeEnum(SelectKey.ScopeEnum.Main);
			}
			// else
			// if(PromotionStatus2.Approve.isInEnum(selectKey.getStatus())){
			else {
				selectKey.setScopeEnum(SelectKey.ScopeEnum.Dept);
				selectKey.setSelectDeptId(BillsConstant.getUser(context).getDepartmentId());
			}
			// ==============================================

			// //查询对象
			// private ScopeEnum scopeEnum;//当前查询对象。为null时自我判断
			// private GUID selectDeptId;//选择部门是的部门Id;
			if (CheckIsNull.isNotEmpty(key.getQueryScope())) {
				selectKey.setScopeEnum(key.getQueryScope().getType());
				selectKey.setSelectDeptId(key.getQueryScope().getDepartmentId());
			}
			// //排序字段
			// private String sortField = "t.goodsName";//排序字段
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				selectKey.setSortField(key.getSortField().getFieldName());
			}
			// 执行查询
			PromotionListEntity2 entity = context.find(PromotionListEntity2.class, selectKey);
			List<PromotionItem> items = new ArrayList<PromotionItem>();
			for (Promotion2 p : entity.getList()) {
				items.add(DataConverterUtil.toPub_PromotionItem(context, p));
			}
			return new ListEntity<PromotionItem>(items, entity.getCount());
			// PageDataKey oldKey = new PageDataKey();
			// if(CheckIsNull.isNotEmpty(key.getQueryTerm())){
			// oldKey.setBeginDate(key.getQueryTerm().getStartTime());
			// oldKey.setEndDate(key.getQueryTerm().getEndTime());
			// }
			// oldKey.setDeptGuid(BillsConstant.getUser(context).getDepartmentId());
			// oldKey.setDeptIsSelect(true);
			// if(CheckIsNull.isNotEmpty(key.getQueryScope())){
			// oldKey.setDeptGuid(key.getQueryScope().getDepartmentId());
			// if(key.getQueryScope().getType() == ScopeType.Mine){
			// oldKey.setDeptIsSelect(false);
			// }
			// }
			// switch (key.getSortType()) {
			// case Asc:
			// oldKey.setSortType("");
			// break;
			// case Desc:
			// oldKey.setSortType("desc");
			// break;
			// }
			// if(CheckIsNull.isNotEmpty(key.getSortField())){
			// oldKey.setSortColumn(key.getSortField().getFieldName());
			// }
			// oldKey.setOffset(key.getOffset());
			// oldKey.setPageSize(key.getCount());
			// oldKey.setPersonGuid(BillsConstant.getUserGuid(context));
			// oldKey.setSearchKey(key.getSearchText());
			// // public final static String TYPE_WAIT = "wait"; //待促销商品
			// // public final static String TYPE_SALE = "sale"; //促销中的商品
			// oldKey.setType(PromotionConstant.TYPE_WAIT);
			// switch (key.getPromotionStatus()[0]) {
			// case Unrecognized:
			// oldKey.setStatus(StatusEnum.SUBMIT.getKey(),StatusEnum.REBUT.getKey(),StatusEnum.PUBLISH.getKey());
			// break;
			// case Examine:
			// oldKey.setStatus(StatusEnum.EXAMINE.getKey());
			// break;
			// case Promotion:
			// oldKey.setStatus(StatusEnum.PROMOTIONING.getKey());
			// oldKey.setType(PromotionConstant.TYPE_SALE);
			// break;
			// case Expired:
			// oldKey.setStatus(StatusEnum.OUT_OF_DATE.getKey());
			// break;
			// case Stoped:
			// oldKey.setStatus(StatusEnum.BREAK_OFF.getKey());
			// break;
			// default:
			// // /**
			// // * 已中止
			// // */
			// // BREAK_OFF("13", "已中止"),
			// // /**
			// // * 已过期
			// // */
			// // OUT_OF_DATE("14", "已过期"),
			// // /**
			// // * 已停售
			// // */
			// // STOP("15", "已停售"),
			// oldKey.setStatus(StatusEnum.BREAK_OFF.getKey(),
			// StatusEnum.OUT_OF_DATE.getKey(), StatusEnum.STOP.getKey());
			// break;
			// }
			//
			// DataResultKey oldResult = context.find(DataResultKey.class,
			// oldKey);
			// List<PromotionItem> items = new ArrayList<PromotionItem>();
			// for(Promotion p : oldResult.getList()){
			// items.add(DataConverterUtil.toPub_PromotionItem(p));
			// }
			// return new ListEntity<PromotionItem>(items,
			// oldResult.getResCount());
		}

	}

	/**
	 * <p>
	 * 根据商品查询当前商品促销中列表
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_GetPromotioningGoodsListProvider extends OneKeyResultListProvider<PromotionItem, GUID> {

		@Override
		protected void provide(Context context, GUID key, List<PromotionItem> resultList) throws Throwable {
			List<Promotion2> list = context.getList(Promotion2.class, key);
			for (Promotion2 p : list) {
				resultList.add(DataConverterUtil.toPub_PromotionItem(context, p));
			}
		}
	}

	@Publish
	protected class Pub_GetPromotionItem extends OneKeyResultProvider<PromotionItem, GUID> {

		@Override
		protected PromotionItem provide(Context context, GUID key) throws Throwable {
			return DataConverterUtil.toPub_PromotionItem(context, context.find(Promotion2.class, key));
		}

	}

	// -----------------------------------任务处理器---------------------------------
	/**
	 * <p>
	 * 删除一个促销
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_DeletePromotionHandler extends SimpleTaskMethodHandler<DeletePromotionTask> {

		@Override
		protected void handle(Context context, DeletePromotionTask task) throws Throwable {
			// PromotionTask oldTask = new PromotionTask();
			// Promotion promotion = new Promotion();
			// promotion.setRECID(task.getId());
			// oldTask.setPromotion(promotion);
			// context.handle(oldTask, PromotionTask.Method.DELETE);
			PromotionTask2 task2 = new PromotionTask2();
			task2.recid = task.getId();
			context.handle(task2, PromotionTask2.Method.DELETE);
			if (task2.isSucceed()) {
				context.dispatch(new PromotionOrderChangedEvent(task.getId(), ChangedType.Delete));
			}
		}

	}

	/**
	 * <p>
	 * 修改促销状态
	 * </p>
	 * 确认促销 审批促销 停止促销
	 * 
	 */
	@Publish
	protected class Pub_UpdatePromotionStatusHandler extends SimpleTaskMethodHandler<UpdatePromotionStatusTask> {
		private Context context;
		private UpdatePromotionStatusTask task;

		@Override
		protected void handle(Context context, UpdatePromotionStatusTask task) throws Throwable {
			this.context = context;
			this.task = task;
			// PromotionTask oldTask = new PromotionTask();
			// Promotion promotion = context.find(Promotion.class,
			// task.getId());
			// oldTask.setPromotion(promotion);
			// oldTask.setRECID(task.getId());
			// oldTask.setStatus(promotion.getStatus());
			// oldTask.setCause(task.getCause());
			// switch (task.getPromotionAction()) {
			// case Submit:
			// context.handle(oldTask, PromotionTask.Method.SUBMIT_PROMOTION);
			// break;
			// case Approval:
			// context.handle(oldTask, PromotionTask.Method.CHECK_PROMOTION);
			// break;
			// case Deny:
			// context.handle(oldTask, PromotionTask.Method.RETURN_PROMOTION);
			// break;
			// case Stop:
			// context.handle(oldTask, PromotionTask.Method.STOP_PROMOTION);
			// break;
			// default:
			// break;
			// }
			Promotion2 p = context.find(Promotion2.class, task.getId());
			PromotionStatusTask statusTask = new PromotionStatusTask(task.getId(), p.getStatus(), getNewstatus(p));
			context.handle(statusTask);
			if (!statusTask.isSucceed()) {
				throw new Throwable();
			}
			// 促销单事件
			context.dispatch(new PromotionOrderChangedEvent(p.getRecid(), task.getPromotionAction()));
		}

		private PromotionStatus2 getNewstatus(Promotion2 p) throws Throwable {
			PromotionStatus2 status = null;
			long beginTime = DateUtil.truncDay(p.getBeginDate());
			long thisTime = DateUtil.truncDay(System.currentTimeMillis());
			switch (task.getPromotionAction()) {
			case Submit:
				if (beginTime < thisTime) {
					task.addError(UpdatePromotionStatusTask.Error.BeginDateError);
					throw new DataStatusExpireException("开始时间不能小于当前时间...");
				}
				switch (BillsConstant.getUserAuth(context, BillsEnum.SALE_PROMOTION)) {
				case BOSS:
					if (beginTime == thisTime) {
						status = PromotionStatus2.Promotioning;
					} else if (beginTime > thisTime) {
						status = PromotionStatus2.Issue;
					}
					break;
				case MANGER:
					status = PromotionStatus2.Approve;
					break;
				}
				break;
			case Approval:
				if (DateUtil.truncDay(p.getEndDate()) < thisTime) {
					task.addError(UpdatePromotionStatusTask.Error.EndDateError);
					throw new DataStatusExpireException(UpdatePromotionStatusTask.Error.EndDateError.getMsg());
				}
				if (beginTime <= thisTime) {
					status = PromotionStatus2.Promotioning;
				} else if (beginTime > thisTime) {
					status = PromotionStatus2.Issue;
				}
				break;
			case Deny:
				status = PromotionStatus2.Return;
				break;
			case Stop:
				status = PromotionStatus2.Suspended;
				break;
			default:
				throw new DataStatusExpireException("促销当前无此操作处理，请扩展...");
			}
			if (null == status) {
				throw new DataStatusExpireException("获取新促销状态失败...");
			}
			return status;
		}
	}

	/**
	 * <p>
	 * 保存促销
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_UpdatePromotionHandler extends SimpleTaskMethodHandler<UpdatePromotionTask> {
		private Context context;

		@Override
		protected void handle(Context context, UpdatePromotionTask task) throws Throwable {
			this.context = context;
			// PromotionTask oldTask = new PromotionTask();
			// for(com.spark.psi.publish.order.task.UpdatePromotionTask.Promotion
			// i : task.getPromotions()){
			// Promotion promotion = pubToMe(context, i);
			// oldTask.setPromotion(promotion);
			// oldTask.setStatus(promotion.getStatus());
			// context.handle(oldTask, PromotionTask.Method.SAVEORUPDATE);
			// }
			PromotionTask2 task2 = new PromotionTask2();
			for (Promotion p : task.getPromotions()) {
				if (null == p.getId()) {
					task2.entity = pubToMe(p);
					if (!isOk(task2.entity)) {
						throw new Throwable("促销信息错误！");
					}
					task2.entity.setRecid(context.newRECID());
					p.setId(task2.entity.getRecid());
					context.handle(task2, PromotionTask2.Method.ADD);
				} else {
					task2.entity = pubToMe(p);
					if (!isOk(task2.entity)) {
						throw new Throwable("促销信息错误！");
					}
					task2.recid = p.getId();
					context.handle(task2, PromotionTask2.Method.MODIFY);
				}
			}
		}

		private boolean isOk(Promotion2 p) throws Throwable {
			if (p.getEndDate() < p.getBeginDate() || 0 == p.getEndDate() || 0 == p.getPromotionCount()
					|| 0 == p.getPrice_promotion()) {
				return false;
			}
			return true;
		}

		private Promotion2 pubToMe(Promotion p) {
			Promotion2 p2 = new Promotion2();
			// p2.setApprovalDate(approvalDate);
			MaterialsItem gi = context.find(MaterialsItem.class, p.getGoodsItemId());
			p2.setCountDecimal(gi.getScale());
			p2.setCreateDate(System.currentTimeMillis());
			Employee user = BillsConstant.getUser(context);
			p2.setCreator(user.getName());
			p2.setCreatorId(user.getId());
			p2.setDeptId(user.getDepartmentId());
			p2.setEndDate(p.getEndDate());
			p2.setGoodsItemId(p.getGoodsItemId());
			p2.setGoodsName(gi.getMaterialName());
			p2.setGoodsProperties(gi.getMaterialProperties().toString());
			p2.setPrice_goods(gi.getSalePrice());
			p2.setPrice_promotion(p.getPromotionPrice());
			// p2.setPromotionCause();
			p2.setPromotionCount(p.getPromotionCount());
			p2.setRecid(p.getId());
			// p2.setReturnCause(returnCause);
			// p2.setSaledCount(saledCount);
			p2.setStatus(PromotionStatus2.Submit.getCode());
			p2.setTenantsId(BillsConstant.getTenantsGuid(context));
			p2.setBeginDate(p.getStartDate());
			return p2;
		}
	}

	// ============================销售分配=============================
	// -----------------------------------结果提供器---------------------------------
	/**
	 * <p>
	 * 销售待配货订单列表
	 * </p>
	 * 查询方法 context.find(ListEntity<SalesDistributeOrderItem)>,
	 * GetSalesDistributeOrderListKey)
	 */
	@Publish
	protected class Pub_GetSalesDistrbuteOrderListHandler extends
			OneKeyResultProvider<ListEntity<SalesDistributeOrderItem>, GetSalesDistributeOrderListKey> {

		@Override
		protected ListEntity<SalesDistributeOrderItem> provide(Context context, GetSalesDistributeOrderListKey key)
				throws Throwable {
			SaleDeploymentMainKey oldKey = new SaleDeploymentMainKey();
			oldKey.setLimitKey(key);
			oldKey.setSearch(key.getSearchText());
			if (CheckIsNull.isNotEmpty(key.getSortField())) {
				oldKey.setSortColumnName(key.getSortField().getFieldName());
				oldKey.setSortType(key.getSortType() == SortType.Asc ? "" : "desc");
			}
			List<SaleOrderInfo> oldList = context.getList(SaleOrderInfo.class, oldKey);
			List<SalesDistributeOrderItem> items = new ArrayList<SalesDistributeOrderItem>();
			for (SaleOrderInfo i : oldList) {
				items.add(getSalesDistributeOrderItem(context, i));
			}
			return new ListEntity<SalesDistributeOrderItem>(items, oldKey.totalCount);
		}

		private SalesDistributeOrderItem getSalesDistributeOrderItem(Context context, SaleOrderInfo i) {
			SalesDistributeOrderItemImpl item = new SalesDistributeOrderItemImpl();
			// 操作
			if (SaleDistrbuteUtil.isDistrbuting(context.getLogin().getID(), i.getRECID())) {
				item.setActions();
			} else {
				item.setActions(OrderAction.Allocate);
			}
			item.setAddress(i.getAddress());
			item.setCustomerId(i.getPartnerId());
			item.setCustomerName(i.getPartnerName());
			item.setCustomerShortName(i.getPartnerShortName());
			item.setId(i.getRECID());
			item.setOrderNumber(i.getBillsNo());
			item.setDeliveryDate(i.getDeliveryDate());
			return item;
		}
	}

	// -----------------------------------任务处理器---------------------------------
	/**
	 * <p>
	 * 配货模块
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_Start_SalesOrderDistributeHandler extends
			TaskMethodHandler<SalesOrderDistributeTask, SalesOrderDistributeTask.Method> {

		protected Pub_Start_SalesOrderDistributeHandler() {
			super(SalesOrderDistributeTask.Method.Start);
		}

		@Override
		protected void handle(Context context, SalesOrderDistributeTask task) throws Throwable {
			if (!SaleDistrbuteUtil.start(context.getLogin().getID(), task.getSalesOrderId())) {
				context.abort();
				// throw new Throwable("当前订单正在分配！");
			}
		}
	}

	/**
	 * <p>
	 * 配货模块
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_Confirm_SalesOrderDistributeHandler extends
			TaskMethodHandler<SalesOrderDistributeTask, SalesOrderDistributeTask.Method> {

		protected Pub_Confirm_SalesOrderDistributeHandler() {
			super(SalesOrderDistributeTask.Method.Confirm);
		}

		@Override
		protected void handle(Context context, SalesOrderDistributeTask task) throws Throwable {
			SaleDistrbuteUtil.cancel(context.getLogin().getID());
			// 出库单
			new MeToModuleUtil(context).createOutStore(task);
			// 发送订单状态改变事件
			context.dispatch(new SalesOrderChangedEvent(task.getSalesOrderId(), ChangedType.Distribute));
		}
	}

	/**
	 * <p>
	 * 配货模块
	 * </p>
	 * 
	 */
	@Publish
	protected class Pub_Cancel_SalesOrderDistributeHandler extends
			TaskMethodHandler<SalesOrderDistributeTask, SalesOrderDistributeTask.Method> {

		protected Pub_Cancel_SalesOrderDistributeHandler() {
			super(SalesOrderDistributeTask.Method.Cancel);
		}

		@Override
		protected void handle(Context context, SalesOrderDistributeTask task) throws Throwable {
			SaleDistrbuteUtil.cancel(context.getLogin().getID());
		}

	}

	@Publish
	protected class Pub_Reset_SalesOrderDistributeHandler extends
			TaskMethodHandler<SalesOrderDistributeTask, SalesOrderDistributeTask.Method> {

		protected Pub_Reset_SalesOrderDistributeHandler() {
			super(SalesOrderDistributeTask.Method.Reset);
		}

		@Override
		protected void handle(Context context, SalesOrderDistributeTask task) throws Throwable {
			SaleDistrbuteUtil.reset(context.getLogin().getID());
		}

	}

	/**
	 * 会话销毁事件监听器
	 */
	@Publish
	protected class EventListener extends Service.EventListener<SessionDisposeEvent> {
		public EventListener() {
		}

		@Override
		protected void occur(Context context, SessionDisposeEvent event) throws Throwable {
			SaleDistrbuteUtil.cancel(context.getLogin().getID());
		}
	}
}
