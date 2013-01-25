package com.spark.order.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TypeEnum;
import com.spark.order.purchase.intf.PurchaseGoods2;
import com.spark.order.purchase.intf.PurchaseGoodsDirect2;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;
import com.spark.order.purchase.intf.entity.PurchaseOrder;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;
import com.spark.order.purchase.service.SelectLastPurchasePartnerKey;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleCancelItem;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.sales.intf.entity.SaleOrderItem;
import com.spark.order.sales2.SalesOrderItem2;
import com.spark.order.service.util.OrderUtil;
import com.spark.order.util.SalesUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Materials;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Partner;
import com.spark.psi.base.Store;
import com.spark.psi.publish.base.contact.entity.ContactBookInfo;
import com.spark.psi.publish.base.partner.entity.CustomerInfo;
import com.spark.psi.publish.base.partner.entity.PartnerInfo;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.order.task.CreatePurchaseGoodsTask;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderTask;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnTask;
import com.spark.psi.publish.order.task.UpdateSalesOrderTask;
import com.spark.psi.publish.order.task.UpdateSalesReturnTask;
import com.spark.psi.publish.order.task.UpdatePurchaseOrderTask.PurchaseOrderGoodsItem;
import com.spark.psi.publish.order.task.UpdatePurchaseReturnTask.PurchaseReturnGoodsItem;
import com.spark.psi.publish.order.task.UpdateSalesOrderTask.SalesOrderGoodsItem;
import com.spark.psi.publish.order.task.UpdateSalesReturnTask.SalesReturnGoodsItem;

/**
 * <p>
 * 外部界面数据转换成内部数据Util
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * 
 * @version 2012-3-13
 */
public final class PublishToMeUtil {
	private Context context;

	/**
	 * @param context
	 */
	public PublishToMeUtil(Context context) {
		super();
		this.context = context;
	}

	// -----------------------------采购清单商品-----------------------
	/**
	 * 采购清单商品
	 * 
	 * @param task
	 * @return PurchaseGoodsInfo 采购清单商品
	 */
	public PurchaseGoods2 getPurchaseGoodsInfo(CreatePurchaseGoodsTask task) {
		PurchaseGoods2 info = new PurchaseGoods2();
		MaterialsItem goodsItem = context.find(MaterialsItem.class, task.getGoodsItemId());
		Materials goods = context.find(Materials.class, goodsItem.getMaterialId());
		Store store = context.find(Store.class, task.getStoreId());
		// for (ShowAllGoods sg : selectList) {
		info.setRecid(context.newRECID());
		info.setPurchaseCount(task.getPurchaseCount());
		info.setCreateDate(System.currentTimeMillis());
		info.setCreatorId(BillsConstant.getUserGuid(context));
		info.setCreator(BillsConstant.getUserName(context));
		info.setGoodsProperties(goodsItem.getSpec());
		info.setCountDecimal(goodsItem.getScale());
		info.setGoodsItemId(goodsItem.getId());
		info.setGoodsName(goods.getMaterialName());
		info.setGoodsCode(goods.getMaterialCode());
		info.setGoodsNo(goodsItem.getMaterialNo());
		info.setSourceId(store.getId());
		info.setSourceName(store.getName());
		info.setTenantsId(BillsConstant.getTenantsGuid(context));
		info.setGoodsUnit(goodsItem.getMaterialUnit());
		// info.setAddress(store.getAddress());
		// info.setContactName(store.getConsignee());
		// info.setConsigneeTel(PhoneAndTypeUtil.selectTel(store.getMobileNo(),
		// store.getLandLineNumber(), PhoneType.TELEPHONE).spell());
		// 取订单数据库
		PurchaseOrderInfo purchaseInfo = context.find(PurchaseOrderInfo.class, new SelectLastPurchasePartnerKey(info
				.getGoodsItemId(), info.getSourceId()));
		if (null != purchaseInfo) {
			info.setPartnerId(purchaseInfo.getPartnerId());
			info.setPartnerShortName(purchaseInfo.getPartnerShortName());
			info.setPartnerName(purchaseInfo.getPartnerName());
			info.setContactName(purchaseInfo.getLinkman());
		}
		return info;
	}

	/**
	 * 获取采购订单信息
	 * 
	 * @param task
	 * @return PurchaseOrderInfo
	 */
	public PurchaseOrder getPurchaseOrder(UpdatePurchaseOrderTask task) {
		PurchaseOrder order = new PurchaseOrder();
		order.setInfo(getInfo(task));
		List<PurchaseOrderItem> dets = new ArrayList<PurchaseOrderItem>();
		double totalAmount = 0;
		for (PurchaseOrderGoodsItem item : task.getPurchaseOrderGoodsItems()) {
			dets.add(getPurchaseOrderItem(item, order.getInfo().getRECID(), task.isDirectSupply()));
			totalAmount += item.getAmount();
		}
		order.getInfo().setTotalAmount(totalAmount);
		order.setDets(dets);
		return order;
	}

	/**
	 * 获得采购订单明细
	 * 
	 * @param goods
	 * @return PurchaseOrderItem
	 */
	public PurchaseOrderItem getPurchaseOrderItem(PurchaseOrderGoodsItem goods, GUID orderID, boolean isDirect) {
		PurchaseOrderItem item = new PurchaseOrderItem();
		item.setBillsId(orderID);
		item.setCreateDate(System.currentTimeMillis());
		item.setCreator(BillsConstant.getUserName(context));
		MaterialsItem gi = context.find(MaterialsItem.class, goods.getGoodsItemId());
		item.setGoodsSpec(gi.getSpec());
		item.setScale(gi.getScale());
		item.setGoodsId(gi.getId());
		item.setGoodsName(gi.getMaterialName());
		item.setGoodsNo(gi.getMaterialCode());
		item.setGoodsCode(gi.getMaterialNo());
		item.setCount(goods.getCount());
		item.setPrice(goods.getPrice());
		item.setRECID(context.newRECID());
		if (isDirect) {
			PurchaseGoodsDirect2 pgi = context.find(PurchaseGoodsDirect2.class, goods.getPurchaseGoodsItemId());
			SaleOrderItem saleItem = context.find(SaleOrderItem.class, pgi.getSourceSaleItemId());
			// item.setsaleItem.getDisRate());
			item.setPrice(saleItem.getPrice());
		}
		item.setUnit(gi.getMaterialUnit());
		item.setAmount(item.getCount() * item.getPrice());
		// 非数据库数据
		// item.purchaseGoodsId = goods.getPurchaseGoodsItemId();
		return item;
	}

	/**
	 * 获得采购订单信息
	 * 
	 * @param task
	 * @return PurchaseOrderInfo
	 */
	public PurchaseOrderInfo getInfo(UpdatePurchaseOrderTask task) {
		PurchaseOrderInfo info = new PurchaseOrderInfo();
		info.setBillsNo(OrderUtil.getBillsNumber(context, BillsEnum.PURCHASE));
		info.setCreateDate(System.currentTimeMillis());
		info.setCreatorId(BillsConstant.getUserGuid(context));
		info.setCreator(BillsConstant.getUserName(context));
		// 供应商信息
		Partner partner = context.find(Partner.class, task.getPartnerId());
		// info.setPartnerFax(partner.getFax());
		info.setPartnerName(partner.getName());
		info.setPartnerNamePY(PinyinHelper.getLetter(info.getPartnerName()));
		info.setPartnerId(partner.getId());
		info.setPartnerShortName(partner.getShortName());
		info.setDeptId(BillsConstant.getUser(context).getDepartmentId());
		info.setDeliveryDate(task.getDeliveryDate());
		info.setRECID(context.newRECID());
		info.setStoreId(task.getRelatedId());
		info.setStoreName(task.getRelateName());
		info.setRemark(task.getRemark());

		info.setBillType(TypeEnum.PLAIN.getKey());
		// 联系人
		PurchaseGoods2 pg = context.find(PurchaseGoods2.class, task.getPurchaseOrderGoodsItems()[0]
				.getPurchaseGoodsItemId());
		info.setLinkman(pg.getContactName());
		// address
		Store store = context.find(Store.class, pg.getSourceId());
		info.setAddress(store.getAddress());
		info.setConsignee(store.getConsignee());

		info.setStatus(StatusEnum.Approve.getKey());
		return info;
	}

	/**
	 * 获得订单信息
	 * 
	 * @param context
	 * @param goods
	 * @return BuyOrdInfo
	 */
	public PurchaseOrderInfo getInfo(PurchaseGoods2 goods, long payDate, String remark) {
		if (null == goods) {
			return null;
		}
		PurchaseOrderInfo info = new PurchaseOrderInfo();
		// 仓库
		Store store = context.find(Store.class, goods.getSourceId());
		info.setAddress(store.getAddress());
		// info.setBillsNo(OrderUtil.getBillsNo(context, BillsEnum.PURCHASE));
		info.setConsignee(store.getConsignee());
		// info.setConsigneeGuid();
		info.setCreateDate(System.currentTimeMillis());
		info.setCreator(BillsConstant.getUserName(context));
		info.setCreatorId(BillsConstant.getUserGuid(context));
		// info.setgoods.getPartnerFax());
		info.setPartnerName(goods.getPartnerName());
		info.setPartnerNamePY(goods.getPartnerNamePY());
		info.setPartnerId(goods.getPartnerId());
		info.setDeptId(BillsConstant.getUser(context).getDepartmentId());
		// info.setDisAmount(disAmount)
		info.setLinkman(goods.getContactName());
		info.setDeliveryDate(payDate);
		info.setRECID(context.newRECID());
		info.setStoreId(goods.getSourceId());
		info.setStoreName(goods.getSourceName());
		info.setRemark(remark);
		// info.setSaleGuid(goods.getSaleOrdGuid());
		// OrderUtil.isExam(context, BillsEnum.BUY, totalAmount)
		// info.setStatus(status)
		info.setBillType(TypeEnum.PLAIN.getKey());
		return info;
	}

	/**
	 * 获得订单明细
	 * 
	 * @param context
	 * @param goods
	 * @return BuyOrdDet
	 */
	public PurchaseOrderItem getDet(PurchaseGoods2 goods) {
		if (null == goods) {
			return null;
		}
		PurchaseOrderItem det = new PurchaseOrderItem();
		det.setAmount(goods.getPurchaseCount() * goods.getPrice_purchase());
		// det.setBillsId(billsGuid)
		det.setGoodsCode(goods.getGoodsCode());
		det.setCreateDate(System.currentTimeMillis());
		det.setCreator(BillsConstant.getUserName(context));
		det.setGoodsSpec(goods.getGoodsProperties());
		det.setScale(goods.getScale());
		det.setGoodsId(goods.getGoodsItemId());
		det.setGoodsName(goods.getGoodsName());
		det.setGoodsNo(goods.getGoodsNo());
		det.setCount(goods.getPurchaseCount());
		det.setPrice(goods.getPrice_purchase());
		det.setRECID(context.newRECID());
		if (goods instanceof PurchaseGoodsDirect2) {
			SalesOrderItem2 salesItem = context.find(SalesOrderItem2.class, ((PurchaseGoodsDirect2) goods)
					.getSourceSaleItemId());
			// det.setSaleDis(salesItem.getDiscount());
			det.setPrice(salesItem.getPrice());
		}
		det.setUnit(goods.getGoodsUnit());

		return det;
	}

	// ---------------------------------------采购退货单------------------------------------------
	/**
	 * 获得采购退货单信息
	 * 
	 * @param task
	 * @return PurchaseCancel
	 */
	public PurchaseCancel getPurchaseCancel(GUID orderId, UpdatePurchaseReturnTask task) {
		Employee user = BillsConstant.getUser(context);
		PurchaseCancel c = null;
		if (null != task.getId()) {
			c = context.find(PurchaseCancel.class, task.getId());
		}
		if (null == c) {
			c = new PurchaseCancel();
			String billsNo = OrderUtil.getBillsNumber(context, BillsEnum.PURCHASE_CANCEL);
			c.setBillsNo(billsNo);
		}
		c.setRECID(orderId);
		c.setCreatorId(user.getId());
		c.setCreator(user.getName());
		c.setCreateDate(System.currentTimeMillis());
		c.setDeptId(user.getDepartmentId());
		Store store = context.find(Store.class, task.getStoreId());
		c.setStoreId(task.getStoreId());
		c.setStoreName(store.getName());
		PartnerInfo partner = context.find(SupplierInfo.class, task.getPartnerId());
		c.setPartnerId(partner.getId());
		c.setPartnerShortName(partner.getShortName());
		c.setPartnerName(partner.getName());
		c.setPartnerNamePY(PinyinHelper.getLetter(partner.getName()));

		c.setBillType(TypeEnum.CANCEL.getKey());
		c.setStoped(false);
		c.setStatus(StatusEnum.Submit.getKey());
		c.setRemark(task.getRemark());
		c.setAddress(task.getAddress());
		c.setConsignee(task.getConsignee());
		c.setTotalAmount(task.getAmount());
		return c;
	}

	/**
	 * 获得采购退货单明细
	 * 
	 * @param item
	 * @return PurchaseCancelItem
	 */
	public PurchaseCancelItem getPurchaseCancelItem(GUID orderId, PurchaseReturnGoodsItem item) {
		PurchaseCancelItem det = new PurchaseCancelItem();
		det.setRECID(GUID.randomID());
		det.setBillsId(orderId);
		det.setGoodsId(item.getGoodsItemId());
		MaterialsItem goodsItem = context.find(MaterialsItem.class, item.getGoodsItemId());
		Materials goods = context.find(Materials.class, goodsItem.getMaterialId());
		det.setGoodsCode(goods.getMaterialCode());
		det.setGoodsNo(goods.getMaterialCode());
		det.setGoodsName(goods.getMaterialName());
		det.setGoodsSpec(goodsItem.getSpec());
		det.setUnit(goodsItem.getMaterialUnit());
		det.setCount(item.getCount());
		det.setPrice(item.getPrice());
		det.setAmount(item.getAmount());
		det.setScale(goodsItem.getScale());
		det.setCreator(BillsConstant.getUserName(context));
		det.setCreateDate(System.currentTimeMillis());
		return det;
	}

	// ----------------------销售订单-----------------------
	/**
	 * 获得销售订单信息
	 * 
	 * @param id
	 * @param task
	 * @return SaleOrderInfo
	 */
	public SaleOrderInfo getSaleOrderInfo(GUID id, UpdateSalesOrderTask task) {
		SaleOrderInfo info = null;
		if (null != task.getId()) {
			info = context.find(SaleOrderInfo.class, task.getId());
		}
		if (null == info) {
			info = new SaleOrderInfo();
			info.setBillsNo(OrderUtil.getBillsNumber(context, BillsEnum.SALE));
		}
		info.setAddress(task.getAddress());
		info.setAllot(false);
		info.setConsignee(task.getConsignee());
		info.setConsigneeGuid(task.getConsigneeId());
		info.setConsigneeTel(task.getConsigneePhoneNumber());

		info.setCreateDate(System.currentTimeMillis());
		info.setCreatorId(BillsConstant.getUserGuid(context));
		info.setCreator(BillsConstant.getUserName(context));
		// 客户
		CustomerInfo customer = context.find(CustomerInfo.class, task.getPartnerId());
		// info.setCuspFax(customer.getFaxNumber());
		info.setPartnerName(customer.getName());
		// info.setPartnerNamePY(cuspFullNamePY)
		info.setPartnerId(customer.getId());
		info.setPartnerShortName(customer.getShortName());

		info.setDeptId(BillsConstant.getUser(context).getDepartmentId());
		info.setDisAmount(task.getDiscountAmount());
		// 审核部门
		info.setExamDeptGuid(SalesUtil.getInitSalesApproveDept(context));
		// info.sete
		if (null != task.getContactPersonGuid()) {
			ContactBookInfo contact = context.find(ContactBookInfo.class, task.getContactPersonGuid());
			info.setLinkman(contact.getName());
		} else {
			info.setLinkman(null);
		}

		info.setDeliveryDate(task.getDeliveryDate());
		info.setRECID(id);
		info.setRemark(task.getRemark());
		info.setStatus(StatusEnum.Submit.getKey());
		info.setBillType(TypeEnum.PLAIN.getKey());
		info.setTotalAmount(task.getAmount());
		return info;
	}

	/**
	 * 获得销售订单明细
	 * 
	 * @param orderId
	 * @param task
	 * @return SaleOrderItem
	 */
	public SaleOrderItem getSaleOrderItem(GUID orderId, SalesOrderGoodsItem task) {
		SaleOrderItem det = new SaleOrderItem();
		det.setBillsId(orderId);
		det.setCreateDate(System.currentTimeMillis());
		det.setCreator(BillsConstant.getUserName(context));
		det.setDisAmount(task.getDiscountAmount());
		det.setDisRate(task.getDiscountCount());
		MaterialsItem goods = context.find(MaterialsItem.class, task.getGoodsItemId());
		det.setGoodsSpec(goods.getSpec());
		det.setScale(goods.getScale());
		det.setGoodsId(goods.getId());
		det.setGoodsCode(goods.getMaterialCode());
		det.setGoodsName(goods.getMaterialName());
		det.setGoodsNo(goods.getMaterialCode());
		det.setGoodsPrice(goods.getSalePrice());
		det.setCount(task.getCount());
		det.setPrice(task.getPrice());
		det.setPlanPrice(task.getPlanPrice());
		det.setPromotionGuid(task.getPromotionId());
		det.setRECID(task.getId() == null ? context.newRECID() : task.getId());
		det.setUnit(goods.getMaterialUnit());
		det.setAmount(det.getPrice() * det.getCount() - det.getDisAmount());
		return det;
	}

	// ---------------------销售退货单------------------------
	/**
	 * 获得销售退货单信息
	 * 
	 * @param task
	 * @return PurchaseCancel
	 */
	public SaleCancel getSaleCancel(GUID orderId, UpdateSalesReturnTask task) {
		Employee user = BillsConstant.getUser(context);
		SaleCancel c = null;
		String billsNo = null;
		if (null != task.getId()) {
			c = context.find(SaleCancel.class, task.getId());
		}
		if (null != c) {
			billsNo = c.getBillsNo();
		} else {
			c = new SaleCancel();
			billsNo = OrderUtil.getBillsNumber(context, BillsEnum.SALE_CANCEL);
		}
		c.setRECID(orderId);
		c.setBillsNo(billsNo);
		c.setCreatorId(user.getId());
		c.setCreator(user.getName());
		c.setCreateDate(System.currentTimeMillis());
		c.setDeptId(user.getDepartmentId());

		PartnerInfo partner = context.find(CustomerInfo.class, task.getPartnerId());
		c.setPartnerId(partner.getId());
		c.setPartnerShortName(partner.getShortName());
		c.setPartnerName(partner.getName());
		c.setPartnerNamePY(PinyinHelper.getLetter(partner.getName()));

		c.setBillType(TypeEnum.CANCEL.getKey());
		c.setStoped(false);
		c.setStatus(StatusEnum.Submit.getKey());
		c.setRemark(task.getRemark());
		// c.setAddress(task.getAddress());
		// 查询联系人信息
		// c.setLinkman(task.getContactPersonGuid());
		// c.setLinkman();
		// c.setLinkmanTel(lxrPhone.getPhoneNumberStr());
		// 收货人GUID
		// c.setConsignee(task.getConsignee());
		// c.setConsigneeGuid((GUID) shrphone.getValueLabel().getData());
		// c.setConsigneeTel(shrphone.getPhoneNumberStr());
		c.setTotalAmount(task.getAmount());
		// 商品数量总和，本次不再需要
		// c.setGoodsTotalCount(goodsTotalCount);
		return c;
	}

	/**
	 * 获得销售退货单明细
	 * 
	 * @param item
	 * @return PurchaseCancelItem
	 */
	public SaleCancelItem getSaleCancelItem(GUID orderId, SalesReturnGoodsItem item) {
		SaleCancelItem det = new SaleCancelItem();
		det.setRECID(GUID.randomID());
		det.setBillsId(orderId);
		det.setGoodsId(item.getGoodsItemId());
		MaterialsItem goodsItem = context.find(MaterialsItem.class, item.getGoodsItemId());
		Materials goods = context.find(Materials.class, goodsItem.getMaterialId());
		det.setGoodsCode(goods.getMaterialCode());
		det.setGoodsNo(goods.getMaterialCode());
		det.setGoodsName(goods.getMaterialName());
		det.setGoodsSpec(goodsItem.getSpec());
		det.setUnit(goodsItem.getMaterialUnit());

		det.setStoreId(item.getStoreId());
		Store store = context.find(Store.class, item.getStoreId());
		det.setStoreName(store.getName());
		det.setCount(item.getCount());
		det.setPrice(item.getPrice());
		det.setAmount(item.getAmount());
		det.setScale(goodsItem.getScale());
		det.setCreator(BillsConstant.getUserName(context));
		det.setCreateDate(System.currentTimeMillis());
		return det;
	}

}
