/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

package com.spark.order.purchase.service;

import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.date.DateUtil;
import com.spark.order.intf.entity.PageEntity;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.task.ModifyGoodsOkCountTask;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseOrder;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;
import com.spark.order.purchase.intf.key.SelectPurchaseAmountByDateKey;
import com.spark.order.purchase.intf.key.SelectPurchaseOrdByOneKey;
import com.spark.order.purchase.intf.key.SelectPurchaseOvertimeAmountKey;
import com.spark.order.purchase.intf.key.SelectPurchaseSubRebutKey;
import com.spark.order.purchase.intf.key.SelectTenAfterOneBillsKey;
import com.spark.order.purchase.intf.task.PurchaseOneOrderTask;
import com.spark.order.purchase.intf.task.PurchaseOrdDetTask;
import com.spark.order.purchase.intf.task.PurchaseOrderTask;
import com.spark.order.service.dao.sql.impl.modify.ModifyGoodsOkCountSql;
import com.spark.order.service.dao.sql.impl.query.QueryBuyMainNewPageSql;
import com.spark.order.service.dao.sql.impl.query.SelectBuyAmountByDateSql;
import com.spark.order.service.dao.sql.impl.query.SelectBuyOrdByOneAmountSql;
import com.spark.order.service.dao.sql.impl.query.SelectBuyOrdByOneSql;
import com.spark.order.service.dao.sql.impl.query.SelectBuyOvertimeAmountSql;
import com.spark.order.service.dao.sql.impl.query.SelectOnLineBillsTenSql;
import com.spark.psi.order.purchase.ORM_BuyOrder;
import com.spark.psi.order.purchase.ORM_BuyOrderByOne;
import com.spark.psi.order.purchase.ORM_BuyOrderNewPage;

/**
 * <p>
 * 采购订单Service
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author 莫迪
 * @version 2011-11-10
 */

public class BuyOrderService extends Service {

	protected final ORM_BuyOrder q_ORM_BuyOrder;
	protected final ORM_BuyOrderNewPage q_newPage; 

	protected BuyOrderService(ORM_BuyOrder qORMBuyOrder,
			ORM_BuyOrderNewPage q_newPage ) {
		super("com.spark.bills.service.BuyOrderService");
		q_ORM_BuyOrder = qORMBuyOrder;
		this.q_newPage = q_newPage; 
	}

	/**
	 * <p>
	 * 出入库反写确认出入库数量
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-17
	 */
	@Publish
	protected class BuyModifyGoodsOkCountHandle extends
			SimpleTaskMethodHandler<ModifyGoodsOkCountTask> {

		@Override
		protected void handle(Context context, ModifyGoodsOkCountTask task)
				throws Throwable {
			ModifyGoodsOkCountSql sql = new ModifyGoodsOkCountSql(context);
			DBCommand db = sql.getDB(task);
			// 初始化流程节点
			db.setArgumentValues(task.getBillsstatus().getKey());
			task.lenght = db.executeUpdate();

			// 写时间预警消息提醒
//			if (StatusEnum.STORE_ALL == task.getBillsstatus()
//					|| StatusEnum.FINISH == task.getBillsstatus()) {
//				OrderUtil.modifyDateMsg(context, task.getBillsGuid(), task
//						.getBillsEnum());
//			}
		}

	}

//	/**
//	 * <p>
//	 * 查询采购商品
//	 * </p>
//	 * 
//	 * <p>
//	 * Copyright: 版权所有 (c) 2002 - 2008<br>
//	 * Company: 久其
//	 * </p>
//	 * 
//	 * @author 莫迪
//	 * @version 2011-11-11
//	 */
//	@Publish
//	protected class BuyCreateGoodsProvider
//			extends
//			OneKeyResultProvider<PageEntity<PurchaseGoodsInfo>, SelectPurchaseGoodsKey> {
//
//		@Override
//		protected PageEntity<PurchaseGoodsInfo> provide(Context context,
//				SelectPurchaseGoodsKey key) throws Throwable {
//			// PageEntity<BuyCreateInfo> entity = new
//			// PageEntity<BuyCreateInfo>(DestData.getList(),
//			// DestData.getList().size());
//			// List<FDirectStorage> directs =
//			// context.getList(FDirectStorage.class,
//			// BillsConstant.getTenantsGuid(context));
//			// SelectDirectStorageKey directKey = new SelectDirectStorageKey();
//			// directKey.setTenantsGuid(BillsConstant.getTenantsGuid(context));
//			// List<BuyCreateInfo> list = BuyBusUtil.getCreateGoods(context
//			// .getList(FDirectStorage.class, directKey), TypeEnum.DIRECT);
//			List<PurchaseGoodsInfo> list = new ArrayList<PurchaseGoodsInfo>();
//			//加入直供商品
//			SelectDirectStorageKey directKey = new SelectDirectStorageKey(BillsConstant.getTenantsGuid(context));
//			list.addAll(context.getList(PurchaseGoodsInfo.class, directKey));
//			//加入普通商品
//			list.addAll(context.getList(PurchaseGoodsInfo.class));
//			for (PurchaseGoodsInfo t : list) {
//				(t).setOldPrice(t.getPrice());
//			}
//			PageEntity<PurchaseGoodsInfo> entity = new PageEntity<PurchaseGoodsInfo>(
//					list, list.size());
//			return entity;
//		}
//
//	}

	/**
	 * <p>
	 * 通过订单GUID查询订单信息+明细集合
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-14
	 */
	@Publish
	protected class BuyOrderProvider extends
			OneKeyResultProvider<PurchaseOrder, GUID> {

		@Override
		protected PurchaseOrder provide(Context context, GUID recid)
				throws Throwable {
			PurchaseOrder buy = new PurchaseOrder();
			buy.setInfo(context.find(PurchaseOrderInfo.class, recid));
			buy.setDets(context.getList(PurchaseOrderItem.class, BillsConstant
					.getTenantsGuid(context), recid));
			return buy;
		}

	}

	@Publish
	protected class BaseBuyOrdInfoProvider extends
			OneKeyResultProvider<PurchaseOrderInfo, GUID> {
		@Override
		protected PurchaseOrderInfo provide(Context context, GUID id)
				throws Throwable {
			ORMAccessor<PurchaseOrderInfo> acc = context
					.newORMAccessor(q_ORM_BuyOrder);
			return acc.findByRECID(id);
		}
	}

	/**
	 * <p>
	 * 查询当前租户下所有采购订单
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-18
	 */
	@Publish
	protected class AllBuyOrdInfoProvider extends
			ResultListProvider<PurchaseOrderInfo> {
		@Override
		protected void provide(Context context,
				List<PurchaseOrderInfo> resultList) throws Throwable {
			ORMAccessor<PurchaseOrderInfo> acc = context
					.newORMAccessor(q_ORM_BuyOrder);
			resultList.addAll(acc.fetch(BillsConstant.getTenantsGuid(context)));
		}
	}

	/**
	 * <p>
	 * 查询当前租户下所有采购订单(独立下单网上订单专用)
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-18
	 */
	@Publish
	protected class AllBuyOrdInfoByOneProvider
			extends
			OneKeyResultProvider<PageEntity<PurchaseOrderInfo>, SelectPurchaseOrdByOneKey> {

		// @Override
		// protected void provide(Context context, SelectBuyOrdByOneKey key,
		// List<BuyOrdInfo> resultList) throws Throwable {
		// ORMAccessor<BuyOrdInfo> acc = context.newORMAccessor(buyOne);
		// resultList.addAll(acc.fetch(BillsConstant.getTenantsGuid(context)));
		// }

		@Override
		protected PageEntity<PurchaseOrderInfo> provide(Context context,
				SelectPurchaseOrdByOneKey key) throws Throwable {
			PageEntity<PurchaseOrderInfo> page = new PageEntity<PurchaseOrderInfo>();
			// ORMAccessor<BuyOrdInfo> acc = context.newORMAccessor(buyOne);
			SelectBuyOrdByOneSql sql = new SelectBuyOrdByOneSql(context);
			SelectBuyOrdByOneAmountSql amount = new SelectBuyOrdByOneAmountSql(
					context);
			DBCommand db = sql.getDB(key);
			DBCommand dbAmount = amount.getDB(key);
			GUID tenants = BillsConstant.getTenantsGuid(context);
			db.setArgumentValues(tenants, key.getStartDate(), key.getEndDate());
			dbAmount.setArgumentValues(tenants, key.getStartDate(), key
					.getEndDate());
			page.setBillsCount(db.rowCountOf());
			page.setList(sql.getList(db.executeQueryLimit(key.getOffset(), key
					.getPageSize())));
			// page.setList(sql.getList(db.executeQuery()));
			page.setOrderAmount(amount.getTotalAmount(dbAmount.executeQuery()));
			return page;
		}
	}

	/**
	 * <p>
	 * 查询当前租户下最近10个采购订单(独立下单网上订单专用)
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-18
	 */
	@Publish
	protected class TenBuyOrdInfoByOneProvider
			extends
			OneKeyResultListProvider<PurchaseOrderInfo, SelectTenAfterOneBillsKey> {
		@Override
		protected void provide(Context context, SelectTenAfterOneBillsKey key,
				List<PurchaseOrderInfo> list) throws Throwable {
			SelectOnLineBillsTenSql sql = new SelectOnLineBillsTenSql(context);

			DBCommand db = sql.getDB(key);
			GUID tenants = BillsConstant.getTenantsGuid(context);
			db.setArgumentValues(tenants);
			list.addAll(sql.getList(db.executeQueryLimit(0, key.getRow())));
		}
	}

	/**
	 * <p>
	 * 新建订单页签下所有数据
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-18
	 */
	@Publish
	protected class AllCreatePageBuyOrdInfoProvider
			extends
			OneKeyResultListProvider<PurchaseOrderInfo, SelectPurchaseSubRebutKey> {
		@Override
		protected void provide(Context context, SelectPurchaseSubRebutKey key,
				List<PurchaseOrderInfo> resultList) throws Throwable {
			QueryBuyMainNewPageSql sql = new QueryBuyMainNewPageSql(context);
			DBCommand db = sql.getDB(key);
			db.setArgumentValues(BillsConstant.getTenantsGuid(context),
					BillsConstant.getUserGuid(context), StatusEnum.Submit
							.getKey(), StatusEnum.Return.getKey());
			RecordSet rs = db.executeQueryLimit(key.getMainKey().getOffset(),
					key.getMainKey().getPageSize());
			resultList.addAll(sql.getList(rs));
		}
	}

	@Publish
	protected class AddBuyOrdInfoHandler extends
			TaskMethodHandler<PurchaseOrderTask, TaskEnum> {
		public AddBuyOrdInfoHandler() {
			super(TaskEnum.ADD);
		}

		@Override
		protected void handle(Context context, PurchaseOrderTask task)
				throws Throwable {
			ORMAccessor<PurchaseOrderInfo> acc = context
					.newORMAccessor(q_ORM_BuyOrder);
			if (null == task.entity.getStatus()) {
				task.entity.setStatus(StatusEnum.Finished.getKey());
			}
			double totalAmount = 0;
			for (PurchaseOrderItem det : task.dets){//getDets(task.dets)) {
				det.setBillsId(task.entity.getRECID());
				PurchaseOrdDetTask detTask = new PurchaseOrdDetTask();
				det.setRECID(context.newRECID());
				det.setCreateDate(new Date().getTime());
				det.setCreator(BillsConstant.getUserName(context)); 
				detTask.entity = det;
				context.handle(detTask, TaskEnum.ADD);
				totalAmount += det.getAmount();
			}
			task.entity.setTotalAmount(totalAmount);
			acc.insert(task.entity);
		}

	} 

	/** 
	 * 独立下单task 
	 */
	@Publish
	protected class AddBuyOrdInfoByOneHandler extends
			SimpleTaskMethodHandler<PurchaseOneOrderTask> {

		@Override
		protected void handle(Context context, PurchaseOneOrderTask task)
				throws Throwable {
			PurchaseOrderTask buyTask;
			for (PurchaseOrder buy : task.getOrders()) {
				buyTask = new PurchaseOrderTask();
				buyTask.entity = buy.getInfo();
//				buyTask.entity.setSaleGuidB(task.buyRECID.get(buyTask.entity
//						.getPartnerId()));
				buyTask.entity.setStatus(StatusEnum.Finished.getKey());
				buyTask.dets = buy.getDets();
				context.handle(buyTask, TaskEnum.ADD);
			}
		}
	}

	@Publish
	protected class ModifyBuyOrdInfoHandler extends
			TaskMethodHandler<PurchaseOrderTask, TaskEnum> {
		public ModifyBuyOrdInfoHandler() {
			super(TaskEnum.MODIFY);
		}

		@Override
		protected void handle(Context context, PurchaseOrderTask task)
				throws Throwable {
			ORMAccessor<PurchaseOrderInfo> acc = context
					.newORMAccessor(q_ORM_BuyOrder);
			if (null != task.dets) {
				double totalAmount = 0;
				PurchaseOrdDetTask detTask = new PurchaseOrdDetTask();
				detTask.billsGuid = task.entity.getRECID();
				context.handle(detTask, TaskEnum.DELETE_LORD);
				for (PurchaseOrderItem det : task.dets){//getDets(task.dets)) {
					det.setBillsId(task.entity.getRECID());
					detTask = new PurchaseOrdDetTask();
					detTask.entity = det;
					context.handle(detTask, TaskEnum.ADD);
					totalAmount += det.getAmount();
				}
				task.entity.setTotalAmount(totalAmount);
			}
			acc.update(task.entity);
		}
	}

	@Publish
	protected class DeleteBuyOrdInfoHandler extends
			TaskMethodHandler<PurchaseOrderTask, TaskEnum> {
		public DeleteBuyOrdInfoHandler() {
			super(TaskEnum.DELETE);
		}

		@Override
		protected void handle(Context context, PurchaseOrderTask task)
				throws Throwable {
			ORMAccessor<PurchaseOrderInfo> acc = context
					.newORMAccessor(q_ORM_BuyOrder);
			acc.delete(task.recid);
			// 删除关联明细
			PurchaseOrdDetTask detTask = new PurchaseOrdDetTask();
			detTask.billsGuid = task.recid;
			context.handle(detTask, TaskEnum.DELETE_LORD);
		}
	}

	// -------------------------------经营分析——采购订单部分-------------------
	/**
	 * <p>
	 * 本月采购金额
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-12-16
	 */
	protected class BuyAmountByDateProvider extends
			OneKeyResultProvider<Double, SelectPurchaseAmountByDateKey> {

		@Override
		protected Double provide(Context context,
				SelectPurchaseAmountByDateKey key) throws Throwable {
			SelectBuyAmountByDateSql sql = new SelectBuyAmountByDateSql(context);
			DBCommand db = sql.getDB(key);
			// "@tenants guid, @noStore guid, @planStore guid, @allStore guid, @noCon guid, @yesCon guid, @finish guid, @start date, @end date"
			db.setArgumentValues(key.getTenantsGuid(), StatusEnum.Store_N0
					.getKey(), StatusEnum.Store_Part.getKey(),
					StatusEnum.Store_All.getKey(), StatusEnum.Consignment_No
							.getKey(), StatusEnum.Consignment_Yes.getKey(),
					StatusEnum.Finished.getKey(), key.getStartDate(), key
							.getEndDate());
			return (Double) db.executeScalar();
		}
	}

	/**
	 * <p>
	 * 逾期未到货金额
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-12-16
	 */
	@Publish
	protected class BuyAmountByOvertimeProvider extends
			OneKeyResultProvider<Double, SelectPurchaseOvertimeAmountKey> {

		@Override
		protected Double provide(Context context,
				SelectPurchaseOvertimeAmountKey key) throws Throwable {
			SelectBuyOvertimeAmountSql sql = new SelectBuyOvertimeAmountSql(
					context);
			DBCommand db = sql.getDB(key);
			// "@tenants guid, @noStore guid, @planStore guid, @end date"
			db.setArgumentValues(BillsConstant.getTenantsGuid(context),
					StatusEnum.Store_N0.getKey(), StatusEnum.Store_Part.getKey(), DateUtil
							.getToday());
			return (Double) db.executeScalar();
		}
	}
}
