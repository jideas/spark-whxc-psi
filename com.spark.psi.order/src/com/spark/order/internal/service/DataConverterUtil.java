package com.spark.order.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.publish.entity.OrderItemImpl;
import com.spark.order.intf.publish.entity.PromotionItemImpl;
import com.spark.order.intf.publish.entity.PurchaseGoodsInfoImpl;
import com.spark.order.intf.publish.entity.PurchaseGoodsItemImpl;
import com.spark.order.intf.publish.entity.PurchaseOrderGoodsItemImpl;
import com.spark.order.intf.publish.entity.PurchaseOrderInfoImpl;
import com.spark.order.intf.publish.entity.PurchaseReturnGoodsItemImpl;
import com.spark.order.intf.publish.entity.PurchaseReturnInfoImpl;
import com.spark.order.intf.publish.entity.SalesOrderGoodsItemImpl;
import com.spark.order.intf.publish.entity.SalesOrderInfoImpl;
import com.spark.order.intf.publish.entity.SalesReturnGoodsItemImpl;
import com.spark.order.intf.publish.entity.SalesReturnInfoImpl;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.promotion.intf.Promotion2;
import com.spark.order.promotion.service.PromotionFactory;
import com.spark.order.purchase.intf.PurchaseGoods2;
import com.spark.order.purchase.intf.PurchaseGoodsDirect2;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;
import com.spark.order.purchase.intf.entity.PurchaseOrder;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;
import com.spark.order.purchase.service.SelectLastPurchasePriceKey;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleCancelItem;
import com.spark.order.sales.intf.entity.SaleOrder;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.sales.intf.entity.SaleOrderItem;
import com.spark.order.util.button.OrderButtonFactory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Partner;
import com.spark.psi.publish.OrderStatus;
import com.spark.psi.publish.OrderType;
import com.spark.psi.publish.base.organization.entity.EmployeeInfo;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.order.entity.OrderItem;
import com.spark.psi.publish.order.entity.PromotionItem;
import com.spark.psi.publish.order.entity.PurchaseGoodsItem;
import com.spark.psi.publish.order.entity.PurchaseOrderGoodsItem;
import com.spark.psi.publish.order.entity.PurchaseOrderInfo;
import com.spark.psi.publish.order.entity.PurchaseReturnGoodsItem;
import com.spark.psi.publish.order.entity.PurchaseReturnInfo;
import com.spark.psi.publish.order.entity.SalesOrderGoodsItem;
import com.spark.psi.publish.order.entity.SalesOrderInfo;
import com.spark.psi.publish.order.entity.SalesReturnGoodsItem;
import com.spark.psi.publish.order.entity.SalesReturnInfo;

/**
 * <p>
 * 接口与本地数据转换工具类
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * 
 * @version 2012-3-12
 */
public final class DataConverterUtil {
	// ===================================内部数据转换成界面数据=====================================
	/**
	 * 将后台订单信息装换成前台需要的订单Item
	 * 
	 * @param orderInfo
	 * @return OrderItem
	 * @throws Throwable
	 */
	public static OrderItem toPub_OrderItem(Context context, BillsEnum billsEnum, OrderInfo orderInfo) throws Throwable {
		OrderItemImpl orderItem = new OrderItemImpl();
		if (CheckIsNull.isNotEmpty(orderInfo)) {
			orderItem.setAmount(orderInfo.getTotalAmount());
			orderItem.setCreateDate(orderInfo.getCreateDate());
			orderItem.setCreator(orderInfo.getCreator());
			orderItem.setDeliveryDate(orderInfo.getDeliveryDate());
			orderItem.setId(orderInfo.getRECID());
			orderItem.setOrderNumber(orderInfo.getBillsNo());
			if (orderInfo.isStoped()) {
				orderItem.setOrderStatus(OrderStatus.Stop);
			} else {
				orderItem.setOrderStatus(getOrderStatus(billsEnum, TypeEnum.getType(orderInfo.getBillType()), orderInfo
						.getStatus()));
			}
			orderItem.setOrderType(getOrderType(billsEnum, orderInfo.getBillType()));
			orderItem.setPartnerId(orderInfo.getPartnerId());
			orderItem.setPartnerName(orderInfo.getPartnerName());
			orderItem.setPartnerShortName(orderInfo.getPartnerShortName());
			orderItem.setStoped(orderInfo.isStoped());
			// 操作图标
			orderItem.setActions(OrderButtonFactory.getOrderButton(context, billsEnum, orderInfo).getOrderAction(
					orderInfo.getRECID()));
		}
		return orderItem;
	}

	/**
	 * 出库
	 * 
	 * @param billsEnum
	 * @param type
	 * @param status
	 * @return boolean
	 */
	private static boolean isOut(BillsEnum billsEnum, TypeEnum type, StatusEnum status) {
		if (BillsEnum.PURCHASE_CANCEL == billsEnum) {
			return true;
		}
		if (BillsEnum.PURCHASE == billsEnum && TypeEnum.CANCEL == type) {
			return true;
		}
		if (BillsEnum.SALE == billsEnum && TypeEnum.CANCEL != type) {
			return true;
		}
		return false;
	}

	/**
	 * 根据订单数据库状态，获得前台页面状态（销售、采购订单专用）
	 * 
	 * @param key
	 * @param key
	 * @return OrderStatus
	 */
	public static OrderStatus getOrderStatus(BillsEnum billsEnum, TypeEnum type, String key) {
		OrderStatus orderstatus = null;
		StatusEnum status = StatusEnum.getstatus(key);
		switch (status) {
		case Submit:
			orderstatus = OrderStatus.Submit;
			break;
		case Approveing:
			orderstatus = OrderStatus.Approval_No;
			break;
		case Approve:
			orderstatus = OrderStatus.Approval_Yes;
			break;
		case Return:
			orderstatus = OrderStatus.Denied;
			break;
		case Store_N0:
			if (isOut(billsEnum, type, status)) {
				orderstatus = OrderStatus.CheckingOut_No;
			} else {
				orderstatus = OrderStatus.CheckingIn_NO;
			}
			break;
		case Store_Part:
			if (isOut(billsEnum, type, status)) {
				orderstatus = OrderStatus.CheckingOut_Part;
			} else {
				orderstatus = OrderStatus.CheckingIn_Part;
			}
			break;
		case Store_All:
			if (isOut(billsEnum, type, status)) {
				orderstatus = OrderStatus.CheckedOut;
			} else {
				orderstatus = OrderStatus.CheckedIn;
			}
			break;
		case Finished:
			orderstatus = OrderStatus.finish;
			break;
		case Consignment_No:
			orderstatus = OrderStatus.CONSIGNMENT_NO;
			break;
		case Consignment_Yes:
			orderstatus = OrderStatus.CONSIGNMENT_YES;
			break;
		}
		return orderstatus;
	}

	/**
	 * 根据订单数据库类型，获得前台页面类型（销售、采购订单专用）
	 * 
	 * @param key
	 * @return OrderType
	 */
	private static OrderType getOrderType(BillsEnum billsEnum, String key) {
		OrderType orderType = null;
		TypeEnum type = TypeEnum.getType(key);
		switch (billsEnum) {
		case PURCHASE:
			switch (type) {
			case BUY_SPORADIC:
				orderType = OrderType.Purchase_SPORADIC;
				break;
			case CANCEL:
				orderType = OrderType.Purchase_Return;
				break;
			case PLAIN:
				orderType = OrderType.PLAIN;
				break;
			}
			break;
		case PURCHASE_CANCEL:
			switch (type) {
			case CANCEL:
				orderType = OrderType.Purchase_Return;
				break;
			}
			break;
		case SALE:
			switch (type) {
			case PLAIN:
				orderType = OrderType.PLAIN;
				break;
			case CANCEL:
				orderType = OrderType.SALES_RETURN;
				break;
			}
			break;
		case SALE_CANCEL:
			switch (type) {
			case CANCEL:
				orderType = OrderType.SALES_RETURN;
				break;
			}
			break;
		}
		return orderType;
	}

	/**
	 * 得到采购清单Item
	 * 
	 * @param goodsInfo
	 * @return PurchaseGoodsItem
	 */
	public static PurchaseGoodsItem toPub_PurchaseGoodsItem(Context context, PurchaseGoods2 goodsInfo) {
		PurchaseGoodsItemImpl item = new PurchaseGoodsItemImpl();
		if (CheckIsNull.isNotEmpty(goodsInfo)) {
			item.setId(goodsInfo.getRecid());
			item.setDirectDelivery(goodsInfo instanceof PurchaseGoodsDirect2);
			item.setGoodsCode(goodsInfo.getGoodsCode());
			item.setGoodsNo(goodsInfo.getGoodsNo());
			item.setCount(goodsInfo.getPurchaseCount());
			item.setCountDecimal(goodsInfo.getScale());
			item.setGoodsItemId(goodsInfo.getGoodsItemId());
			item.setGoodsName(goodsInfo.getGoodsName());
			item.setPrice(goodsInfo.getPrice_purchase());
			item.setProperties(goodsInfo.getGoodsProperties());
			item.setRecentPrice(getPrice_lastPurchase(context, goodsInfo.getPartnerId(), goodsInfo.getGoodsItemId()));
			item.setStoreId(goodsInfo.getSourceId());
			item.setStoreName(goodsInfo.getSourceName());
			if (!Partner.RetailPurchaseSupplierId.equals(goodsInfo.getPartnerId())) {
				item.setSupplierId(goodsInfo.getPartnerId());
				item.setSupplierName(goodsInfo.getPartnerName());
				item.setSupplierShorName(goodsInfo.getPartnerShortName());
			}
			MaterialsItem mi = context.find(MaterialsItem.class, goodsInfo.getGoodsItemId());
			item.setUnit(mi.getMaterialUnit());
			item.setContactId(goodsInfo.getContactId());
			if (goodsInfo instanceof PurchaseGoodsDirect2) {
				SaleOrderInfo sale = context.find(SaleOrderInfo.class, ((PurchaseGoodsDirect2) goodsInfo)
						.getSourceSaleId());
				// item.setDirectDeliveryDate(sale.getPayDate());
				item.setSalesMemo(sale.getRemark());
			}
		}
		return item;
	}

	private static double getPrice_lastPurchase(Context context, GUID partnerId, GUID goodsItemId) {
		Double d = context.find(Double.class, new SelectLastPurchasePriceKey(partnerId, goodsItemId));
		return null == d ? 0 : d;
	}

	/**
	 * 得到采购清单信息
	 * 
	 * @param goodsInfo
	 * @return com.spark.psi.publish.order.entity.PurchaseGoodsInfo
	 */
	public static com.spark.psi.publish.order.entity.PurchaseGoodsInfo toPub_PurchaseGoodsInfo(Context context,
			PurchaseGoods2 goodsInfo) {
		PurchaseGoodsInfoImpl item = new PurchaseGoodsInfoImpl();
		if (CheckIsNull.isNotEmpty(goodsInfo)) {
			item.setId(goodsInfo.getRecid());
			item.setDirectDelivery(goodsInfo instanceof PurchaseGoodsDirect2);
			item.setCode(goodsInfo.getGoodsCode());
			item.setCount(goodsInfo.getPurchaseCount());
			item.setCountDecimal(goodsInfo.getScale());
			item.setGoodsItemId(goodsInfo.getGoodsItemId());
			item.setGoodsName(goodsInfo.getGoodsName());
			item.setPrice(goodsInfo.getPrice_purchase());
			item.setProperties(goodsInfo.getGoodsProperties());
			item.setRecentPrice(getPrice_lastPurchase(context, goodsInfo.getPartnerId(), goodsInfo.getGoodsItemId()));
			item.setStoreId(goodsInfo.getSourceId());
			item.setStoreName(goodsInfo.getSourceName());
			item.setSupplierId(goodsInfo.getPartnerId());
			item.setUnit(goodsInfo.getGoodsUnit());
			item.setContactId(goodsInfo.getContactId());
			item.setSupplierName(goodsInfo.getPartnerName());
			item.setSupplierShorName(goodsInfo.getPartnerShortName());
			if (goodsInfo instanceof PurchaseGoodsDirect2) {
				SaleOrderInfo sale = context.find(SaleOrderInfo.class, ((PurchaseGoodsDirect2) goodsInfo)
						.getSourceSaleId());
				item.setDirectDeliveryDate(sale.getDeliveryDate());
				item.setSalesMemo(sale.getRemark());
			}
		}
		return item;
	}

	/**
	 * 得到销售单信息
	 * 
	 * @param context
	 * @param order
	 * @return PurchaseOrderInfo
	 * @throws Throwable
	 */
	public static SalesOrderInfo toPub_SalesOrderInfo(Context context, SaleOrder order) throws Throwable {
		if (CheckIsNull.isEmpty(order) || null == order.getInfo() || null == order.getInfo().getRECID()) {
			return null;
		}
		return toPub_SalesOrderInfo(context, order.getInfo(), order.getDets());
	}

	/**
	 * 得到采购单信息
	 * 
	 * @param context
	 * @param order
	 * @return PurchaseOrderInfo
	 * @throws Throwable
	 */
	public static PurchaseOrderInfo toPub_PurchaseOrderInfo(Context context, PurchaseOrder order) throws Throwable {
		if (CheckIsNull.isEmpty(order)) {
			return null;
		}
		return toPub_PurchaseOrderInfo(context, order.getInfo(), order.getDets());
	}

	/**
	 * 获得采购单信息
	 * 
	 * @param context
	 * @param orderInfo
	 * @param dets
	 * @return PurchaseOrderInfo
	 * @throws Throwable
	 */
	public static PurchaseOrderInfo toPub_PurchaseOrderInfo(Context context,
			final com.spark.order.purchase.intf.entity.PurchaseOrderInfo orderInfo, List<PurchaseOrderItem> dets)
			throws Throwable {
		PurchaseOrderInfoImpl info = new PurchaseOrderInfoImpl();
		if (CheckIsNull.isNotEmpty(orderInfo)) {
			info.setAmount(orderInfo.getTotalAmount());
			info.setApproverLabel(orderInfo.getApproveStr());
			info.setConsignee(orderInfo.getConsignee());
			info.setLinkman(orderInfo.getLinkman());
			info.setCreateDate(orderInfo.getCreateDate());
			info.setCreator(context.find(EmployeeInfo.class, orderInfo.getCreatorId()));
			info.setCreatorLabel(orderInfo.getCreator());
			info.setDeliveryDate(orderInfo.getDeliveryDate());
			info.setDenyCause(orderInfo.getRejectReason());
			info.setDeptId(orderInfo.getDeptId());
			info.setId(orderInfo.getRECID());
			info.setMemo(orderInfo.getRemark());
			info.setOrderNumber(orderInfo.getBillsNo());
			if (orderInfo.isStoped()) {
				info.setOrderStatus(OrderStatus.Stop);
			} else {
				info.setOrderStatus(getOrderStatus(BillsEnum.PURCHASE, TypeEnum.getType(orderInfo.getBillType()),
						orderInfo.getStatus()));
			}
			info.setOrderType(getOrderType(BillsEnum.PURCHASE, orderInfo.getBillType()));
			if (null != orderInfo.getPartnerId()) {
				info.setPartnerInfo(context.find(SupplierInfo.class, orderInfo.getPartnerId()));
			}
			info.setStopCause(orderInfo.getStopReason());
			info.setStoped(orderInfo.isStoped());
			info.setSourceId(orderInfo.getStoreId());
			info.setSourceName(orderInfo.getStoreName());
			// 操作图标
			info.setActions(OrderButtonFactory.getOrderButton(context, BillsEnum.PURCHASE, orderInfo).getOrderAction(
					orderInfo.getRECID()));
		}
		// 订单明细
		info.setGoodsItems(toPub_PurchaseOrderGoodsItemList(dets).toArray(new PurchaseOrderGoodsItemImpl[dets.size()]));
		return info;
	}

	/**
	 * 获得采购退货单信息
	 * 
	 * @param context
	 * @param orderInfo
	 * @param dets
	 * @return PurchaseOrderInfo
	 * @throws Throwable
	 */
	public static PurchaseReturnInfo toPub_PurchaseReturnInfo(Context context, PurchaseCancel orderInfo,
			List<PurchaseCancelItem> dets) throws Throwable {
		if (CheckIsNull.isEmpty(orderInfo)) {
			return null;
		}
		PurchaseReturnInfoImpl info = new PurchaseReturnInfoImpl();
		info.setAmount(orderInfo.getTotalAmount());
		info.setApproverLabel(orderInfo.getApproveStr());
		info.setConsignee(orderInfo.getConsignee());
		info.setLinkman(orderInfo.getLinkman());
		info.setCreateDate(orderInfo.getCreateDate());
		info.setCreator(context.find(EmployeeInfo.class, orderInfo.getCreatorId()));
		info.setCreatorLabel(orderInfo.getCreator());
		info.setDeliveryDate(orderInfo.getDeliveryDate());
		info.setDenyCause(orderInfo.getRejectReason());
		info.setDeptId(orderInfo.getDeptId());
		info.setId(orderInfo.getRECID());
		info.setMemo(orderInfo.getRemark());
		info.setOrderNumber(orderInfo.getBillsNo());
		if (orderInfo.isStoped()) {
			info.setOrderStatus(OrderStatus.Stop);
		} else {
			info.setOrderStatus(getOrderStatus(BillsEnum.PURCHASE_CANCEL, TypeEnum.getType(orderInfo.getBillType()),
					orderInfo.getStatus()));
		}
		info.setOrderType(getOrderType(BillsEnum.PURCHASE_CANCEL, orderInfo.getBillType()));
		info.setPartnerInfo(context.find(SupplierInfo.class, orderInfo.getPartnerId()));
		info.setStopCause(orderInfo.getStopReason());
		info.setStoped(orderInfo.isStoped());
		// 操作图标
		info.setActions(OrderButtonFactory.getOrderButton(context, BillsEnum.PURCHASE_CANCEL, orderInfo)
				.getOrderAction(orderInfo.getRECID()));
		// 订单明细
		info.setGoodsItems(toPub_PurchaseReturnGoodsItemList(dets)
				.toArray(new PurchaseReturnGoodsItemImpl[dets.size()]));
		return info;
	}

	/**
	 * 获得销售退货单信息
	 * 
	 * @param context
	 * @param orderInfo
	 * @param dets
	 * @return PurchaseOrderInfo
	 * @throws Throwable
	 */
	public static SalesReturnInfo toPub_SalesReturnInfo(Context context, SaleCancel orderInfo, List<SaleCancelItem> dets)
			throws Throwable {
		if (CheckIsNull.isEmpty(orderInfo)) {
			return null;
		}
		SalesReturnInfoImpl info = new SalesReturnInfoImpl();
		info.setAmount(orderInfo.getTotalAmount());
		info.setApproverLabel(orderInfo.getApproveStr());
		info.setLinkman(orderInfo.getLinkman());
		info.setCreateDate(orderInfo.getCreateDate());
		info.setCreator(context.find(EmployeeInfo.class, orderInfo.getCreatorId()));
		info.setCreatorLabel(orderInfo.getCreator());
		info.setDeliveryDate(orderInfo.getDeliveryDate());
		info.setDenyCause(orderInfo.getRejectReason());
		info.setDeptId(orderInfo.getDeptId());
		info.setId(orderInfo.getRECID());
		info.setMemo(orderInfo.getRemark());
		info.setOrderNumber(orderInfo.getBillsNo());
		if (orderInfo.isStoped()) {
			info.setOrderStatus(OrderStatus.Stop);
		} else {
			info.setOrderStatus(getOrderStatus(BillsEnum.SALE_CANCEL, TypeEnum.getType(orderInfo.getBillType()),
					orderInfo.getStatus()));
		}
		info.setOrderType(getOrderType(BillsEnum.SALE_CANCEL, orderInfo.getBillType()));
		info.setPartnerInfo(context.find(CustomerInfo.class, orderInfo.getPartnerId()));
		info.setStopCause(orderInfo.getStopReason());
		info.setStoped(orderInfo.isStoped());
		// 操作图标
		info.setActions(OrderButtonFactory.getOrderButton(context, BillsEnum.SALE_CANCEL, orderInfo).getOrderAction(
				orderInfo.getRECID()));
		// 订单明细
		info.setReturnGoodsItems(toPub_SalesReturnGoodsItemList(dets)
				.toArray(new SalesReturnGoodsItemImpl[dets.size()]));
		return info;
	}

	/**
	 * 获得销售单信息
	 * 
	 * @param context
	 * @param orderInfo
	 * @param dets
	 * @return PurchaseOrderInfo
	 * @throws Throwable
	 */
	public static SalesOrderInfo toPub_SalesOrderInfo(Context context, SaleOrderInfo orderInfo, List<SaleOrderItem> dets)
			throws Throwable {
		SalesOrderInfoImpl info = new SalesOrderInfoImpl();
		if (CheckIsNull.isNotEmpty(orderInfo)) {
			info.setAmount(orderInfo.getTotalAmount());
			info.setApproverLabel(orderInfo.getApproveStr());
			info.setConsignee(orderInfo.getConsignee());
			info.setLinkman(orderInfo.getLinkman());
			info.setCreateDate(orderInfo.getCreateDate());
			info.setCreator(context.find(EmployeeInfo.class, orderInfo.getCreatorId()));
			info.setCreatorLabel(orderInfo.getCreator());
			info.setDeliveryDate(orderInfo.getDeliveryDate());
			info.setDenyCause(orderInfo.getRejectReason());
			info.setDeptId(orderInfo.getDeptId());
			info.setId(orderInfo.getRECID());
			info.setMemo(orderInfo.getRemark());
			info.setOrderNumber(orderInfo.getBillsNo());
			if (orderInfo.isStoped()) {
				info.setOrderStatus(OrderStatus.Stop);
			} else {
				info.setOrderStatus(getOrderStatus(BillsEnum.SALE, TypeEnum.getType(orderInfo.getBillType()), orderInfo
						.getStatus()));
			}
			info.setOrderType(getOrderType(BillsEnum.SALE, orderInfo.getBillType()));
			info.setPartnerInfo(context.find(CustomerInfo.class, orderInfo.getPartnerId()));
			info.setStopCause(orderInfo.getStopReason());
			info.setStoped(orderInfo.isStoped());
			info.setDiscountAmount(orderInfo.getDisAmount());
			// 操作图标
			info.setActions(OrderButtonFactory.getOrderButton(context, BillsEnum.SALE, orderInfo).getOrderAction(
					orderInfo.getRECID()));
		}
		// 订单明细
		info.setSalesOrderGoodsItems(toPub_SalesOrderGoodsItemList(context, dets).toArray(
				new SalesOrderGoodsItemImpl[dets.size()]));
		return info;
	}

	/**
	 * 采购订单明细集合
	 * 
	 * @param dets
	 * @return List<PurchaseOrderGoodsItem>
	 */
	private static List<PurchaseOrderGoodsItem> toPub_PurchaseOrderGoodsItemList(List<PurchaseOrderItem> dets) {
		List<PurchaseOrderGoodsItem> list = new ArrayList<PurchaseOrderGoodsItem>();
		for (PurchaseOrderItem item : dets) {
			list.add(toPub_PurchaseOrderGoodsItem(item));
		}
		return list;
	}

	/**
	 * 采购退货单明细集合
	 * 
	 * @param dets
	 * @return List<PurchaseOrderGoodsItem>
	 */
	private static List<PurchaseReturnGoodsItem> toPub_PurchaseReturnGoodsItemList(List<PurchaseCancelItem> dets) {
		List<PurchaseReturnGoodsItem> list = new ArrayList<PurchaseReturnGoodsItem>();
		for (PurchaseCancelItem item : dets) {
			list.add(toPub_PurchaseReturnGoodsItem(item));
		}
		return list;
	}

	/**
	 * 销售退货单明细集合
	 * 
	 * @param dets
	 * @return List<PurchaseOrderGoodsItem>
	 */
	private static List<SalesReturnGoodsItem> toPub_SalesReturnGoodsItemList(List<SaleCancelItem> dets) {
		List<SalesReturnGoodsItem> list = new ArrayList<SalesReturnGoodsItem>();
		for (SaleCancelItem item : dets) {
			list.add(toPub_SalesReturnGoodsItem(item));
		}
		return list;
	}

	/**
	 * 销售订单明细集合
	 * 
	 * @param dets
	 * @return List<PurchaseOrderGoodsItem>
	 */
	private static List<SalesOrderGoodsItem> toPub_SalesOrderGoodsItemList(Context context, List<SaleOrderItem> dets) {
		List<SalesOrderGoodsItem> list = new ArrayList<SalesOrderGoodsItem>();
		for (SaleOrderItem item : dets) {
			list.add(toPub_SalesOrderGoodsItem(context, item));
		}
		return list;
	}

	/**
	 * 获得采购订单明细Item
	 * 
	 * @param det
	 * @return PurchaseOrderGoodsItem
	 */
	public static PurchaseOrderGoodsItem toPub_PurchaseOrderGoodsItem(PurchaseOrderItem det) {
		PurchaseOrderGoodsItemImpl item = new PurchaseOrderGoodsItemImpl();
		if (CheckIsNull.isNotEmpty(det)) {
			item.setAmount(det.getAmount());
			item.setCode(det.getGoodsNo());
			item.setCount(det.getCount());
			item.setCountDecimal(det.getScale());
			item.setGoodsItemId(det.getGoodsId());
			item.setName(det.getGoodsName());
			item.setPrice(det.getPrice());
			item.setProperties(det.getGoodsSpec());
			// item.setPurchaseCause(det.getCause());
			item.setGoodsNo(det.getGoodsNo());
			item.setUnit(det.getUnit());
			item.setId(det.getRECID());
		}
		return item;
	}

	/**
	 * 获得采购退货单明细Item
	 * 
	 * @param det
	 * @return PurchaseOrderGoodsItem
	 */
	public static PurchaseReturnGoodsItem toPub_PurchaseReturnGoodsItem(PurchaseCancelItem det) {
		PurchaseReturnGoodsItemImpl item = new PurchaseReturnGoodsItemImpl();
		if (CheckIsNull.isNotEmpty(det)) {
			item.setAmount(det.getAmount());
			item.setCode(det.getGoodsNo());
			item.setCount(det.getCount());
			item.setCountDecimal(det.getScale());
			item.setGoodsItemId(det.getGoodsId());
			item.setName(det.getGoodsName());
			item.setPrice(det.getPrice());
			item.setProperties(det.getGoodsSpec());
			item.setUnit(det.getUnit());
			item.setGoodsNo(det.getGoodsNo());
			// item.setStoreId(det.getStoreGuid());
			// item.setStoreName(det.getStoreName());
			item.setId(det.getRECID());
		}
		return item;
	}

	/**
	 * 获得销售退货单明细Item
	 * 
	 * @param det
	 * @return PurchaseOrderGoodsItem
	 */
	public static SalesReturnGoodsItem toPub_SalesReturnGoodsItem(SaleCancelItem det) {
		SalesReturnGoodsItemImpl item = new SalesReturnGoodsItemImpl();
		if (CheckIsNull.isNotEmpty(det)) {
			item.setAmount(det.getAmount());
			item.setCode(det.getGoodsNo());
			item.setCount(det.getCount());
			item.setCountDecimal(det.getScale());
			item.setGoodsItemId(det.getGoodsId());
			item.setName(det.getGoodsName());
			item.setPrice(det.getPrice());
			item.setProperties(det.getGoodsSpec());
			item.setUnit(det.getUnit());
			item.setGoodsNo(det.getGoodsNo());
			item.setStoreId(det.getStoreId());
			item.setStoreName(det.getStoreName());
			item.setId(det.getRECID());
		}
		return item;
	}

	/**
	 * 获得销售订单明细Item
	 * 
	 * @param det
	 * @return SalesOrderGoodsItem
	 */
	public static SalesOrderGoodsItem toPub_SalesOrderGoodsItem(Context context, SaleOrderItem det) {
		SalesOrderGoodsItemImpl item = new SalesOrderGoodsItemImpl();
		if (CheckIsNull.isNotEmpty(det)) {
			item.setAmount(det.getAmount());
			item.setCode(det.getGoodsNo());
			item.setGoodsNo(det.getGoodsNo());
			item.setCount(det.getCount());
			item.setCountDecimal(det.getScale());
			item.setGoodsItemId(det.getGoodsId());
			item.setName(det.getGoodsName());
			item.setPrice(det.getPrice());
			item.setBuyAvgPrice(det.getBuyAvgPrice());
			item.setProperties(det.getGoodsSpec());
			item.setUnit(det.getUnit());
			item.setDiscountAmount(det.getDisAmount());
			item.setDiscountCount(det.getDisRate());
			item.setId(det.getRECID());
			item.setPromotionId(det.getPromotionGuid());
			// 促销列表
			List<PromotionItem> promotionList = new ArrayList<PromotionItem>();
			List<Promotion2> list = context.getList(Promotion2.class, item.getGoodsItemId());
			for (Promotion2 p : list) {
				promotionList.add(DataConverterUtil.toPub_PromotionItem(context, p));
			}
			item.setPromotionList(promotionList);
			item.setGoodsItemPrice(context.find(MaterialsItem.class, det.getGoodsId()).getSalePrice());
		}
		return item;
	}

	/**
	 * 获得促销列表
	 * 
	 * @param p
	 * @return PromotionItem
	 */
	public static PromotionItem toPub_PromotionItem(Context context, Promotion2 p) {
		PromotionItemImpl item = new PromotionItemImpl();
		item.setCreator(p.getCreator());
		item.setStartDate(p.getBeginDate());
		item.setEndDate(p.getEndDate());
		item.setGoodsItemId(p.getGoodsItemId());
		item.setGoodsName(p.getGoodsName());
		item.setGoodsProperties(p.getGoodsProperties());
		item.setId(p.getRecid());
		item.setOriginalPrice(p.getPrice_goods());
		// 可操作图标
		item.setActions(PromotionFactory.getPromotionButton(context, p).getActions());
		item.setPromotionCount(p.getPromotionCount());
		item.setCountDecimal(p.getScale());
		item.setPromotionPrice(p.getPrice_promotion());
		// 促销状态
		item.setPromotionStatus(p.getPubstatus());
		item.setSoldCount(p.getSaledCount());
		return item;
	}

}
