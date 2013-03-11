package com.spark.order.util.process;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.internal.service.MeToModuleUtil;
import com.spark.order.intf.OrderEnum;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.task.FlowTask;
import com.spark.order.intf.task.RebutTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.promotion.intf.PromotionSaledCountTask;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.sales.intf.entity.SaleOrderItem;
import com.spark.order.sales.intf.task.ModifyDirectGoodsStopStatusTask;
import com.spark.order.sales.intf.task.SalePlanOutDateTask;
import com.spark.order.service.util.OrderUtil;
import com.spark.order.util.checking.IServiceCheck;
import com.spark.order.util.checking.ServiceCheckFactory;
import com.spark.order.util.checking.IServiceCheck.CheckParam;
import com.spark.order.util.checking.ServiceCheckFactory.CheckEnum;
import com.spark.order.util.dnaSql.DnaSql_query;
import com.spark.psi.base.Store;
import com.spark.psi.inventory.intf.entity.outstorage.mod.RelationCheckOutSheet;

public class SalesOrderActionImpl extends OrderActionImpl {

	public SalesOrderActionImpl(Context context) {
		super(context, BillsEnum.SALE);
	}

	private Store oneStore;// 当且仅当系统只有一个可用仓库时有值

	/**
	 * 判断当前租户是否有可用仓库
	 * 
	 * @return Store
	 */
	private boolean isHaveStore() {
		List<Store> list = OrderUtil.getStore(context);
		if (2 == list.size()) {
			for (Store st : list) {
				if (!st.getId().equals(Store.GoodsStoreId)) {
					this.oneStore = st;
					return true;
				}
			}
		}
		return 0 < list.size();
	}

	@Override
	protected boolean approval() throws Throwable {
		boolean isOk = true;
		// 执行
		IProcessManage pm = OrderFactory.getProcessManage(context, this.billsEnum);
		pm.setOrderInfo(this.orderInfo);
		next = pm.next(this.id);
		if (StatusEnum.Approve == next) {

		}
		RebutTask task = new RebutTask(billsEnum);
		task.billsRECID = id;
		task.oldstatus = this.orderInfo.getStatus();
		task.newstatus = next.getKey();
		task.info = this.orderInfo; 
		context.handle(task);
		if (StatusEnum.Store_N0 == next && task.isSucceed()) {
			boolean isHaveStore = isHaveStore();
			List<SaleOrderItem> dets = null;
			if (!isHaveStore) {
				throw new Throwable("当前租户无可用仓库！");
			} else if (null != oneStore) {
				IServiceCheck sc = ServiceCheckFactory.getServiceCheck(context, new CheckParam(CheckEnum.store_one));
				if (sc.doError()) {
					// this.orderInfo = sc.getOrderInfo();
					addCheck(sc);
					if (!modifyPlanOutDate(planOutDate)) {
						return false;
					} else {
						((SaleOrderInfo) this.orderInfo).setPlanOutDate(planOutDate);
					}
				}
				new MeToModuleUtil(context).createOutStore((SaleOrderInfo) this.orderInfo, context.getList(
						SaleOrderItem.class, this.id), oneStore);
			}
			// 增加客户已用信用额度
			OrderUtil.addAlreadyUseCredit(context, this.orderInfo.getPartnerId(), this.orderInfo.getTotalAmount());
			// 修改促销商品
			if (null == dets) {
				dets = context.getList(SaleOrderItem.class, this.id);
			}
			PromotionSaledCountTask promotionTask;
			for (SaleOrderItem item : dets) {
				if (null == item.getPromotionGuid()) {
					continue;
				}
				promotionTask = new PromotionSaledCountTask(item.getPromotionGuid(), item.getCount());
				context.handle(promotionTask);
				if (!promotionTask.isSucceed()) {
					isOk = false;
				}
			}
		}
		return task.isSucceed() && isOk;
	}

	/**
	 * 修改销售订单预计出库日期
	 * 
	 * @param l
	 * @return boolean,修改成功返回true
	 */
	private boolean modifyPlanOutDate(Long l) {
		if (null == l || 0 == l) {
			return false;
		}
		SalePlanOutDateTask task = new SalePlanOutDateTask(this.id, l);
		context.handle(task);
		return task.isSucceed();
	}

	@Override
	protected boolean consignment() {
		return false;
	}

	@Override
	protected boolean exect() throws Throwable {
		// 增加客户已用信用额度
		RelationCheckOutSheet rcos = OrderUtil.getOrderOutSheet(context, this.id);
		OrderUtil.addAlreadyUseCredit(context, this.orderInfo.getPartnerId(), this.orderInfo.getTotalAmount()
				- rcos.getOutStoreAmount());// rcos.getOutStoreAmount()+rcos.getCheckedOutAmount());
		return stopDirectGoods(false) && super.exect();
	}

	@Override
	protected void initOrderInfo() {
		if (!(CheckIsNull.isNotEmpty(this.orderInfo) && this.orderInfo instanceof SaleOrderInfo)) {
			orderInfo = context.find(SaleOrderInfo.class, id);
		}
	}

	@Override
	protected boolean stop() throws Throwable {
		// 减少客户已用信用额度
		RelationCheckOutSheet rcos = OrderUtil.getOrderOutSheet(context, this.id);
		OrderUtil.subAlreadyUseCredit(context, this.orderInfo.getPartnerId(), this.orderInfo.getTotalAmount()
				- rcos.getOutStoreAmount());// rcos.getOutStoreAmount()+rcos.getCheckedOutAmount());
		return stopDirectGoods(true) && super.stop();
	}

	/**
	 * 销售订单中止时，修改直供清单商品中止状态
	 * 
	 * @param saleRecid
	 * @param isStop
	 *            中止，为true
	 * @return boolean
	 */
	private boolean stopDirectGoods(boolean isStop) {
		ModifyDirectGoodsStopStatusTask task = new ModifyDirectGoodsStopStatusTask(this.id, isStop);
		context.handle(task);
		return true;// task.isSucceed();
	}

	@Override
	protected boolean submit() throws Throwable {
		boolean isOk = true;
		IProcessManage pm = OrderFactory.getProcessManage(context, this.billsEnum);
		pm.setOrderInfo(this.orderInfo);
		next = pm.next(this.id);
		FlowTask task = new FlowTask(billsEnum);
		task.billsRECID = id;
		task.oldstatus = this.orderInfo.getStatus();
		task.newstatus = next.getKey();
		task.setDeptGuid(BillsConstant.getUser(context).getDepartmentId()); 
		context.handle(task);
		if (StatusEnum.Store_N0 == next && task.isSucceed()) {
			boolean isHaveStore = isHaveStore();
			List<SaleOrderItem> dets = null;
			;
			if (!isHaveStore) {
				throw new Throwable("当前租户无可用仓库！");
			} else if (null != oneStore) {
				IServiceCheck sc = ServiceCheckFactory.getServiceCheck(context, new CheckParam(CheckEnum.store_one));
				if (sc.doError()) {
					addCheck(sc);
					if (!modifyPlanOutDate(planOutDate)) {
						return false;
					} else {
						((SaleOrderInfo) this.orderInfo).setPlanOutDate(planOutDate);
					}
				}
				dets = context.getList(SaleOrderItem.class, this.id);
				new MeToModuleUtil(context).createOutStore((SaleOrderInfo) this.orderInfo, dets, oneStore);
			}
			// 增加客户已用信用额度
			OrderUtil.addAlreadyUseCredit(context, this.orderInfo.getPartnerId(), this.orderInfo.getTotalAmount());
			// 修改促销商品
			if (null == dets) {
				dets = context.getList(SaleOrderItem.class, this.id);
			}
			PromotionSaledCountTask promotionTask;
			for (SaleOrderItem item : dets) {
				if (null == item.getPromotionGuid()) {
					continue;
				}
				promotionTask = new PromotionSaledCountTask(item.getPromotionGuid(), item.getCount());
				context.handle(promotionTask);
				if (!promotionTask.isSucceed()) {
					isOk = false;
				}
			}
		} 
		return task.isSucceed() && isOk;
	}

	/**
	 * 驳回
	 * 
	 * @return boolean
	 */
	protected boolean deny() throws Throwable {
		// 执行
		IProcessManage pm = OrderFactory.getProcessManage(context, this.billsEnum);
		pm.setOrderInfo(this.orderInfo);
		next = pm.prov(this.id);
		RebutTask task = new RebutTask(billsEnum);
		task.billsRECID = id;
		task.oldstatus = this.orderInfo.getStatus();
		task.newstatus = next.getKey();
		task.info = this.orderInfo;
		task.cause = this.cause; 
		context.handle(task);
		return task.isSucceed();
	}

	// =============
	/**
	 * 查询关联的采购订单ID列表
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * 
	 * 
	 * @author modi
	 * @version 2012-5-10
	 */
	protected static final class SelectRelevantPurchaseSql extends DnaSql_query {

		GUID[] getRelevantIdList() {
			List<GUID> list = new ArrayList<GUID>();
			RecordSet rs = this.executeQuery();
			while (rs.next()) {
				list.add(rs.getFields().get(0).getGUID());
			}
			return list.toArray(new GUID[list.size()]);
		}

		private final GUID id;

		public SelectRelevantPurchaseSql(Context context, GUID id) {
			super(context);
			this.id = id;
		}

		@Override
		protected String getSql() {
			StringBuilder sql = new StringBuilder();
			sql.append(" select ");
			sql.append(" t.recid as recid ");
			sql.append(" from ");
			sql.append(OrderEnum.Purchase.getDb_table());
			sql.append(" as t ");
			sql.append(" where ");
			sql.append(" t.saleGuid = @saleId ");
			this.addParam("@saleId guid", id);
			sql.append(" and t.status <> @status ");
			this.addParam("@status string", StatusEnum.Consignment_Yes.getKey());
			sql.append(" and t.status <> @status1 ");
			this.addParam("@status1 string", StatusEnum.Finished.getKey());
			return sql.toString();
		}

	}
}
