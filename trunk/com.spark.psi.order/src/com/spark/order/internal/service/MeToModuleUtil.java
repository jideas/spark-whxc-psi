package com.spark.order.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.PinyinHelper;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.entity.OrderDet;
import com.spark.order.intf.entity.OrderInfo;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.facade.OrderException;
import com.spark.order.intf.key.SelectOrderItemGoodsTotalCountKey;
import com.spark.order.purchase.intf.PurchaseGoodsDirect2;
import com.spark.order.purchase.intf.entity.PurchaseCancel;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;
import com.spark.order.purchase.intf.task.PurchaseGoodsDirectTask2;
import com.spark.order.sales.intf.entity.SaleCancel;
import com.spark.order.sales.intf.entity.SaleCancelItem;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.sales.intf.entity.SaleOrderItem;
import com.spark.order.sales.intf.task.SaleDeploymentOkTask;
import com.spark.order.service.util.OrderUtil;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.inventory.intf.task.instorage.InstoAddTask;
import com.spark.psi.inventory.intf.task.outstorage.OutstoAddTask;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;
import com.spark.psi.publish.order.task.SalesOrderDistributeTask;
import com.spark.psi.publish.order.task.SalesOrderDistributeTask.DistributionGoodsItem;
import com.spark.psi.publish.order.task.SalesOrderDistributeTask.DistributionItem;

/**
 * <p>
 * 本模块到内部其他模块转换
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author modi
 * @version 2012-3-20
 */
public class MeToModuleUtil {
	protected Context context;

	public MeToModuleUtil(Context context) {
		this.context = context;
	}

	/**
	 * <p>
	 * 没有仓库异常
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author modi
	 * @version 2012-3-21
	 */
	@SuppressWarnings("serial")
	public static class NotHaveStoreException extends OrderException {
		public NotHaveStoreException() {
			super("当前没有课操作的仓库");
		}
	}

	/**
	 * 获得仓库对象
	 * 
	 * @return
	 * @throws Throwable
	 *             Store
	 */
	public Store getStore() throws NotHaveStoreException {
		// TODO 销售仓库
		Store store = OrderUtil.getStoreBySales(context);
		if (CheckIsNull.isEmpty(store)) {
			throw new NotHaveStoreException();
		}
		return store;
	}

	private static OutstoAddTask getOutsto(Store store, OrderInfo facade, List<? extends OrderDet> detlist) {
		Outstorage outsto = new Outstorage();
		outsto.setPartnerId(facade.getPartnerId());
		outsto.setNamePY(facade.getPartnerNamePY());
		outsto.setPartnerName(facade.getPartnerName());
		outsto.setPartnerShortName(facade.getPartnerShortName());
		// 预计出库日期
		if (facade instanceof SaleOrderInfo) {
			outsto.setCheckoutDate(((SaleOrderInfo) facade).getPlanOutDate());
		} else {
			outsto.setCheckoutDate(((PurchaseOrderInfo) facade).getDeliveryDate());
		}
		outsto.setRelaBillsId(facade.getRECID());
		outsto.setRelaBillsNo(facade.getBillsNo());
		outsto.setRemark(facade.getRemark());
		outsto.setStoped(false);
		outsto.setBillsAmount(facade.getTotalAmount());
		if (CheckIsNull.isNotEmpty(store)) {
			outsto.setStoreId(store.getId());
			outsto.setStoreName(store.getName());
			outsto.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
		}
		List<OutstorageItem> dlist = new ArrayList<OutstorageItem>();
		for (OrderDet orderDet : detlist) {
			OutstorageItem det = new OutstorageItem();
			if (orderDet instanceof SaleOrderItem) {
				det.setDisRate(((SaleOrderItem) orderDet).getDisRate());
				det.setPrice(orderDet.getPrice());
			} else {
				det.setPrice(((PurchaseOrderItem) orderDet).getPrice());
			}
			det.setGoodsSpec(orderDet.getGoodsSpec());
			det.setGoodsId(orderDet.getGoodsId());
			det.setGoodsName(orderDet.getGoodsName());
			det.setGoodsNo(orderDet.getGoodsNo());
			det.setScale(orderDet.getScale());
			det.setUnit(orderDet.getUnit());
			det.setPlanCount(orderDet.getCount());
			dlist.add(det);
		}
		return new OutstoAddTask(outsto, dlist);
	}

	/**
	 * 创建出库单【销售】
	 * 
	 * @return boolean
	 */
	public boolean createOutStore(SaleOrderInfo info, List<SaleOrderItem> dets, Store store) throws Throwable {
		OutstoAddTask task = getOutsto(store, info, dets);
		task.getEntity().setBillsCount(
				context.find(Double.class, new SelectOrderItemGoodsTotalCountKey(info.getRECID(), OrderEnum.Sales)));
		context.handle(task, CheckingOutType.Sales);
		// 修改销售定的那状态为配货完成
		SaleDeploymentOkTask okTask = new SaleDeploymentOkTask(info.getRECID());
		context.handle(okTask);
		return true;
	}

	/**
	 * 创建出库单【采购退货】
	 * 
	 * @return boolean
	 */
	public boolean createOutStore(PurchaseCancel cancel, List<PurchaseCancelItem> detList) throws Throwable {
		Outstorage entity = null;
		List<OutstorageItem> detailList = new ArrayList<OutstorageItem>();
		if (detList.isEmpty()) {
			return false;
		}
		double count = context.find(Double.class, new SelectOrderItemGoodsTotalCountKey(cancel.getRECID(),
				OrderEnum.Purchase_Return));
		for (int i = 0; i < detList.size(); i++) {
			PurchaseCancelItem det = detList.get(i);

			if (null == entity || !cancel.getStoreId().equals(entity.getStoreId())) {
				if (null != entity) {
					OutstoAddTask outStoTask = new OutstoAddTask(entity, detailList);
					outStoTask.getEntity().setBillsCount(count);
					outStoTask.getEntity().setBillsAmount(cancel.getTotalAmount());
					context.handle(outStoTask, CheckingOutType.Return);
				}
				entity = new Outstorage();
				entity.setRelaBillsId(cancel.getRECID());
				entity.setRelaBillsNo(cancel.getBillsNo());
				entity.setStoreId(cancel.getStoreId());
				entity.setStoreName(cancel.getStoreName());
				entity.setStoreNamePY(PinyinHelper.getLetter(cancel.getStoreName()));
				entity.setPartnerId(cancel.getPartnerId());
				entity.setPartnerName(cancel.getPartnerName());
				entity.setNamePY(cancel.getPartnerNamePY());
				entity.setPartnerShortName(cancel.getPartnerShortName());
				entity.setRemark(cancel.getRemark());
				detailList.clear();
			}

			OutstorageItem odt = new OutstorageItem();
			odt.setGoodsId(det.getGoodsId());
			odt.setGoodsName(det.getGoodsName());
			odt.setGoodsNo(det.getGoodsNo());
			odt.setGoodsSpec(det.getGoodsSpec());
			odt.setUnit(det.getUnit());
			odt.setPrice(det.getPrice());
			odt.setPlanCount(det.getCount());
			odt.setScale(det.getScale());
			odt.setGoodsCode(det.getGoodsCode());
			detailList.add(odt);

			if (i == detList.size() - 1) {
				OutstoAddTask outStoTask = new OutstoAddTask(entity, detailList);
				outStoTask.getEntity().setBillsCount(count);
				outStoTask.getEntity().setBillsAmount(cancel.getTotalAmount());
				context.handle(outStoTask, CheckingOutType.Return);
			}

		}
		return true;
	}

	// ------------------销售配货出库--------------------------
	/**
	 * 销售配货生成出库单
	 * 
	 * @param task
	 * @return boolean
	 */
	public boolean createOutStore(SalesOrderDistributeTask task) {
		SaleOrderInfo facade = context.find(SaleOrderInfo.class, task.getSalesOrderId());
		Outstorage outsto = new Outstorage();
		outsto.setPartnerId(facade.getPartnerId());
		outsto.setNamePY(facade.getPartnerNamePY());
		outsto.setPartnerName(facade.getPartnerName());
		outsto.setPartnerShortName(facade.getPartnerShortName());
		outsto.setRelaBillsId(facade.getRECID());
		outsto.setRelaBillsNo(facade.getBillsNo());
		outsto.setRemark(facade.getRemark());
		outsto.setStoped(false);
		outsto.setBillsCount(context
				.find(Double.class, new SelectOrderItemGoodsTotalCountKey(facade.getRECID(), OrderEnum.Sales)));
		outsto.setBillsAmount(facade.getTotalAmount());
		for (DistributionItem di : task.getItems()) {
			if (di.isDirect()) {
				addDirect(facade, di);
				continue;
			}
			Store store = context.find(Store.class, di.getStoreId());
			outsto.setStoreId(store.getId());
			outsto.setStoreName(store.getName());
			outsto.setStoreNamePY(PinyinHelper.getLetter(store.getName()));
			outsto.setCheckoutDate(di.getDeliverDate());
			List<OutstorageItem> dlist = new ArrayList<OutstorageItem>();
			for (DistributionGoodsItem goods : di.getItems()) {
				OutstorageItem det = new OutstorageItem();
				SaleOrderItem si = context.find(SaleOrderItem.class, goods.getSalesGoodsItemId());
				det.setDisRate(si.getDisRate());
				det.setPrice(si.getPrice());
				det.setGoodsSpec(si.getGoodsSpec());
				det.setGoodsId(si.getGoodsId());
				det.setGoodsName(si.getGoodsName());
				det.setGoodsNo(si.getGoodsNo());
				det.setScale(si.getScale());
				det.setUnit(si.getUnit());
				det.setGoodsNo(si.getGoodsNo());
				det.setPlanCount(goods.getCount());
				dlist.add(det);
			}
			context.handle(new OutstoAddTask(outsto, dlist), CheckingOutType.Sales);
		}
		// 修改销售定的那状态为配货完成
		SaleDeploymentOkTask okTask = new SaleDeploymentOkTask(task.getSalesOrderId());
		context.handle(okTask);
		return true;
	}

	private boolean addDirect(SaleOrderInfo facade, DistributionItem item) {
		PurchaseGoodsDirectTask2 task = new PurchaseGoodsDirectTask2();
		PurchaseGoodsDirect2 info;
		for (DistributionGoodsItem goods : item.getItems()) {
			info = new PurchaseGoodsDirect2();
			task.entity = info;
			info.setPurchaseCause("直供");
			info.setCreateDate(System.currentTimeMillis());
			info.setCreatorId(BillsConstant.getUserGuid(context));
			info.setCreator(BillsConstant.getUserName(context));
			SaleOrderItem det = context.find(SaleOrderItem.class, goods.getSalesGoodsItemId());
			info.setGoodsProperties(det.getGoodsSpec());
			info.setCountDecimal(det.getScale());
			info.setGoodsItemId(det.getGoodsId());
			info.setGoodsName(det.getGoodsName());
			info.setGoodsCode(det.getGoodsNo());
			info.setRecid(context.newRECID());
			info.setSourceSaleItemId(goods.getSalesGoodsItemId());
			info.setSourceSaleId(facade.getRECID());
			// info.setSourceId(facade.getCuspGuid());
			info.setSourceId(facade.getRECID());
			info.setSourceName(facade.getPartnerShortName());
			info.setTenantsId(BillsConstant.getTenantsGuid(context));
			info.setGoodsUnit(det.getUnit());
			info.setPurchaseCount(goods.getCount());
			context.handle(task, PurchaseGoodsDirectTask2.Method.ADD);
		}
		return true;
	}

	// ---------------------------入库---------------------

	/**
	 * 生成入库单【采购】
	 * 
	 * @return boolean
	 */
	public boolean createInStore(PurchaseOrderInfo info, List<PurchaseOrderItem> dets) throws Throwable {
		Instorage entity = getInstoInfo(info);
		InstoAddTask task = new InstoAddTask(entity, getInstoDets(entity, dets));
		context.handle(task, CheckingInType.Purchase);
		return true;
	}

	/**
	 * 生成入库单【零星采购采购】
	 * @param distributeInventoryItems 
	 * 
	 * @return boolean
	 */
	public boolean createInStore_odd_lot(PurchaseOrderInfo info, List<PurchaseOrderItem> dets, DistributeInventoryItem[] distributeInventoryItems) throws Throwable {
		Instorage entity = getInstoInfo(info);
		List<InstorageItem> items = getInstoDets(entity, dets);
		for (InstorageItem item : items) {
			item.setThisTimeCount(item.getCount());
		}
		InstoAddTask task = new InstoAddTask(entity, items);
		task.setDistributeInventoryItems(distributeInventoryItems);
		task.getEntity().setSheetType(CheckingInType.Irregular.getCode());
		context.handle(task, CheckingInType.Irregular);
		return true;
	}

	/**
	 * 生成入库单【销售退货】
	 * 
	 * @return boolean
	 */
	public boolean createInStore(SaleCancel cancel, List<SaleCancelItem> dets) throws Throwable {
		Instorage entity = null;
		List<InstorageItem> detailList = new ArrayList<InstorageItem>();
		if (dets.isEmpty()) {
			return false;
		}
		double count = context.find(Double.class,
				new SelectOrderItemGoodsTotalCountKey(cancel.getRECID(), OrderEnum.Sales_Return));
		for (int i = 0; i < dets.size(); i++) {
			SaleCancelItem det = dets.get(i);

			if (null == entity || !det.getStoreId().equals(entity.getStoreId())) {
				if (null != entity) {
					InstoAddTask outStoTask = new InstoAddTask(entity, detailList);
					outStoTask.getEntity().setBillsCount(count);
					outStoTask.getEntity().setBillsAmount(cancel.getTotalAmount());
					context.handle(outStoTask, CheckingInType.Return);
				}
				entity = new Instorage();
				entity.setRelaBillsId(cancel.getRECID());
				entity.setRelaBillsNo(cancel.getBillsNo());
				entity.setStoreId(det.getStoreId());
				entity.setStoreName(det.getStoreName());
				entity.setStoreNamePY(PinyinHelper.getLetter(det.getStoreName()));
				entity.setPartnerId(cancel.getPartnerId());
				entity.setPartnerName(cancel.getPartnerName());
				// entity.setCusproNamePY(cancel.getCuspFullNamePY());
				entity.setPartnerShortName(cancel.getPartnerShortName());
				entity.setRemark(cancel.getRemark());
				detailList.clear();
			}

			InstorageItem odt = new InstorageItem();
			odt.setGoodsId(det.getGoodsId());
			odt.setGoodsName(det.getGoodsName());
			odt.setGoodsNo(det.getGoodsNo());
			odt.setGoodsCode(det.getGoodsCode());
			odt.setGoodsSpec(det.getGoodsSpec());
			odt.setUnit(det.getUnit());
			odt.setPrice(det.getPrice());
			odt.setCount(det.getCount());
			odt.setScale(det.getScale());
			detailList.add(odt);

			if (i == dets.size() - 1) {
				InstoAddTask outStoTask = new InstoAddTask(entity, detailList);
				outStoTask.getEntity().setBillsCount(count);
				outStoTask.getEntity().setBillsAmount(cancel.getTotalAmount());
				context.handle(outStoTask, CheckingInType.Return);
			}
		}
		return true;
	}

	private Instorage getInstoInfo(PurchaseOrderInfo poi) {
		Instorage info = new Instorage();
		info.setPartnerId(poi.getPartnerId());
		info.setPartnerName(poi.getPartnerName());
		info.setPartnerShortName(poi.getPartnerShortName());
		info.setRelaBillsId(poi.getRECID());
		info.setRelaBillsNo(poi.getBillsNo());
		info.setStoreId(poi.getStoreId());
		info.setStoreName(poi.getStoreName());
		info.setStoreNamePY(PinyinHelper.getLetter(info.getStoreName()));
		info.setRemark(poi.getRemark());
		info.setCheckinDate(poi.getDeliveryDate());
		info.setPurchaseDate(poi.getDeliveryDate());
		info.setPurchasePerson(poi.getPurchasePersonName());
		info.setBillsCount(context.find(Double.class, new SelectOrderItemGoodsTotalCountKey(poi.getRECID(), OrderEnum.Purchase)));
		info.setBillsAmount(poi.getTotalAmount());
		return info;
	}

	private List<InstorageItem> getInstoDets(Instorage entity, List<? extends OrderDet> items) {
		List<InstorageItem> dets = new ArrayList<InstorageItem>();
		InstorageItem det = null;
		double totalAmount = 0;
		double totalCount = 0;
		for (OrderDet d : items) {
			det = new InstorageItem();
			det.setGoodsId(d.getGoodsId());
			det.setGoodsCode(d.getGoodsCode());
			det.setGoodsNo(d.getGoodsNo());
			det.setGoodsName(d.getGoodsName());
			det.setGoodsSpec(d.getGoodsSpec());
			det.setUnit(d.getUnit());
			det.setPrice(d.getPrice());
			det.setCount(d.getCount());
			det.setScale(d.getScale());
			det.setAmount(d.getAmount());
			dets.add(det);
			totalAmount += d.getPrice() * d.getCount();
			totalCount += d.getCount();
		}
		entity.setBillsCount(totalCount);
		entity.setBillsAmount(totalAmount);
		return dets;
	}

}
