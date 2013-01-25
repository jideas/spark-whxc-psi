/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-19       莫迪        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-19       莫迪        
 * ============================================================*/

package com.spark.order.sales.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.exceptions.DataStatusExpireException;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.sales.intf.entity.SaleOrder;
import com.spark.order.sales.intf.entity.SaleOrderInfo;
import com.spark.order.sales.intf.entity.SaleOrderItem;
import com.spark.order.sales.intf.key.SaleDeploymentMainKey;
import com.spark.order.sales.intf.key.SelectSaleSubRebutKey;
import com.spark.order.sales.intf.task.SaleDeploymentOkTask;
import com.spark.order.sales.intf.task.SaleExamDeptTask;
import com.spark.order.sales.intf.task.SaleOrdDetTask;
import com.spark.order.sales.intf.task.SaleOrderTask;
import com.spark.order.sales.intf.task.SalePlanOutDateTask;
import com.spark.order.sales.intf.task.SelectSaleOrderByCuspTask;
import com.spark.order.service.dao.sql.impl.ModifySql;
import com.spark.order.service.dao.sql.impl.modify.SaleDeploymentOkSql;
import com.spark.order.service.dao.sql.impl.modify.SaleExamDeptSql;
import com.spark.order.service.dao.sql.impl.modify.SalePlanOutDateSql;
import com.spark.order.service.dao.sql.impl.query.QueryBuyMainNewPageSql;
import com.spark.order.service.dao.sql.impl.query.SaleDeploymentMainSql;
import com.spark.order.service.dao.sql.impl.query.SelectSaleOrderByCuspSql;
import com.spark.psi.order.sales.ORM_SaleOrder;

/**
 * <p>
 * 销售订单
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author 莫迪
 * @version 2011-11-19
 */

public class SaleOrderService extends Service {

	protected final ORM_SaleOrder q_ORM_SaleOrder;

	protected SaleOrderService(ORM_SaleOrder qORMSaleOrder) {
		super("SaleOrderService");
		q_ORM_SaleOrder = qORMSaleOrder;
	}


	/**
	 * <p>
	 * 修改预计出库日期
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-28
	 */
	@Publish
	protected class ModifySalePlanOutDateHandle extends SimpleTaskMethodHandler<SalePlanOutDateTask> {

		@Override
		protected void handle(Context context, SalePlanOutDateTask task) throws Throwable {
			SalePlanOutDateSql sql = new SalePlanOutDateSql(context);
			DBCommand db = sql.getDB(task);
			// "@id guid, @planDate date";
			db.setArgumentValues(task.getRecid(), task.getPlanDate());
			task.setLenght(db.executeUpdate());
		}

	}

	/**
	 * <p>
	 * 更新销售订单审核部门
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-23
	 */
	@Publish
	protected class ModifySaleExamDeptHandle extends SimpleTaskMethodHandler<SaleExamDeptTask> {

		@Override
		protected void handle(Context context, SaleExamDeptTask task) throws Throwable {
			SaleExamDeptSql sql = new SaleExamDeptSql(context);
			// @recid guid, @examDeptGuid guid, @oldExamDept guid, @status guid
			DBCommand db = sql.getDB(task);
			db.setArgumentValues(task.recid, task.examDeptGuid, task.oldExamDetp, StatusEnum.Approve.getKey(), task.examinStr);
			task.setLenght(db.executeUpdate());
		}
	}

	/**
	 * <p>
	 * 销售订单出库分配完成反写完成状态
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-20
	 */
	@Publish
	protected class ModifyDeploymentTOSaleOrderHandle extends SimpleTaskMethodHandler<SaleDeploymentOkTask> {

		@Override
		protected void handle(Context context, SaleDeploymentOkTask task) throws Throwable {
			ModifySql sql = new SaleDeploymentOkSql(context);
			DBCommand db = sql.getDB(task);
			db.setArgumentValues(task.getBillsGuid(), true);
			// 删除消息提醒
			// OrderMsgUtil.delSaleOutMsg(context,
			// context.find(SaleOrderInfo.class, task.getBillsGuid()));
			task.lenght = db.executeUpdate();
			if (!task.isSucceed()) {
				throw new DataStatusExpireException();
			}
		}
	}

	/**
	 * 出库分配获取销售订单信息 Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * 
	 * @author 莫迪
	 * @version 2011-11-20
	 */
	@Publish
	protected class SaleDeploymentMainProvider extends OneKeyResultListProvider<SaleOrderInfo, SaleDeploymentMainKey> {

		@Override
		protected void provide(Context context, SaleDeploymentMainKey key, List<SaleOrderInfo> list) throws Throwable {
			SaleDeploymentMainSql sql = new SaleDeploymentMainSql(context);
			DBCommand db = sql.getDB(key);
			db.setArgumentValues(StatusEnum.Store_N0.getKey(), false);
			RecordSet rs = db.executeQueryLimit(key.getOffset(), key.getPageSize());
			list.addAll(sql.getList(rs));
			key.lenght = list.size();
		}

	}

	/**
	 * 出库分配获取销售订单条数 Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * 
	 * @author 莫迪
	 * @version 2011-11-20
	 */
	@Publish
	protected class SaleDeploymentMainCountProvider extends OneKeyResultProvider<Integer, SaleDeploymentMainKey> {

		@Override
		protected Integer provide(Context context, SaleDeploymentMainKey key) throws Throwable {
			SaleDeploymentMainSql sql = new SaleDeploymentMainSql(context);
			DBCommand db = sql.getDB(key);
			db.setArgumentValues(BillsConstant.getTenantsGuid(context), StatusEnum.Store_N0.getKey(), false);
			return db.rowCountOf();
		}

	}

	/**
	 * <p>
	 * 根据订单Guid获得订单信息+明细集合
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-19
	 */
	@Publish
	protected class SaleOrderInfoAndDetProvider extends OneKeyResultProvider<SaleOrder, GUID> {

		@Override
		protected SaleOrder provide(Context context, GUID bills) throws Throwable {
			SaleOrder so = new SaleOrder();
			try {
				ORMAccessor<SaleOrderInfo> acc = context.newORMAccessor(q_ORM_SaleOrder);
				SaleOrderInfo info = acc.findByRECID(bills);
				List<SaleOrderItem> dets = context.getList(SaleOrderItem.class, bills);
				for (SaleOrderItem det : dets) {
					(det).setGoodsPrice(det.getPrice());
				}
				so.setInfo(info);
				so.setDets(dets);
			} catch (NullPointerException ex) {
				return null;
			}
			return so;
		}

	}

	@Publish
	protected class BaseSaleOrdInfoProvider extends OneKeyResultProvider<SaleOrderInfo, GUID> {
		@Override
		protected SaleOrderInfo provide(Context context, GUID id) throws Throwable {
			ORMAccessor<SaleOrderInfo> acc = context.newORMAccessor(q_ORM_SaleOrder);
			return acc.findByRECID(id);
		}
	}

	/**
	 * <p>
	 * SelectBuySubRebutKey.java销售订单新建页签
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-28
	 */
	@Publish
	protected class NewTabPageSaleOrderProvider extends OneKeyResultListProvider<SaleOrderInfo, SelectSaleSubRebutKey> {

		@Override
		protected void provide(Context context, SelectSaleSubRebutKey key, List<SaleOrderInfo> resultList) throws Throwable {
			QueryBuyMainNewPageSql sql = new QueryBuyMainNewPageSql(context);
			DBCommand db = sql.getDB(key);
			db.setArgumentValues(BillsConstant.getTenantsGuid(context), BillsConstant.getUserGuid(context), StatusEnum.Submit
					.getKey(), StatusEnum.Return.getKey());
			RecordSet rs = db.executeQueryLimit(key.getMainKey().getOffset(), key.getMainKey().getPageSize());
			resultList.addAll(sql.getSaleList(rs));
		}

	}

	/**
	 * <p>
	 * 租户所有销售订单
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-11-27
	 */
	@Publish
	protected class AllSaleOrdInfoProvider extends ResultListProvider<SaleOrderInfo> {
		@Override
		protected void provide(Context context, List<SaleOrderInfo> resultList) throws Throwable {
			ORMAccessor<SaleOrderInfo> acc = context.newORMAccessor(q_ORM_SaleOrder);
			resultList.addAll(acc.fetch(BillsConstant.getTenantsGuid(context)));
		}
	}

	@Publish
	protected class AddSaleOrdInfoHandler extends TaskMethodHandler<SaleOrderTask, TaskEnum> {
		public AddSaleOrdInfoHandler() {
			super(TaskEnum.ADD);
		}

		@Override
		protected void handle(Context context, SaleOrderTask task) throws Throwable {
			if (null == task.entity && null != task.order) {
				task.entity = task.order.getInfo();
			}
			if (null == task.entity) {
				task.setSucceed(false);
				return;
			}
			ORMAccessor<SaleOrderInfo> acc = context.newORMAccessor(q_ORM_SaleOrder);
			acc.insert(task.entity);
			if (null == task.order || null == task.order.getDets()) {
				return;
			}
			SaleOrdDetTask detTask;
			for (int i = 0; i < getDets(task.order.getDets()).size(); i++) {
				SaleOrderItem det = task.order.getDets().get(i);
				det.setBillsId(task.entity.getRECID()); 
				detTask = new SaleOrdDetTask();
				detTask.entity = det;
				context.handle(detTask, TaskEnum.ADD);
			}
			// // 写时间预警消息提醒
			// OrderUtil.modifyDateMsg(context, task.entity, BillsEnum.SALE);
		}
	}

	private List<SaleOrderItem> getDets(List<SaleOrderItem> dets) {
		List<SaleOrderItem> list = new ArrayList<SaleOrderItem>();
		for (SaleOrderItem det : dets) {
			boolean b = true;
			for (SaleOrderItem d : list) {
				if (d.getGoodsId().equals(det.getGoodsId()) && d.getPrice() == det.getPrice()
						&& d.getDisRate() == det.getDisRate()) {
					d.setCount(d.getCount() + det.getCount());
					d.setDisAmount(d.getDisAmount() + det.getDisAmount());
					b = false;
					break;
				}
			}
			if (b) {
				list.add(det);
			}
		}
		return list;
	}

	@Publish
	protected class ModifySaleOrdInfoHandler extends TaskMethodHandler<SaleOrderTask, TaskEnum> {
		public ModifySaleOrdInfoHandler() {
			super(TaskEnum.MODIFY);
		}

		@Override
		protected void handle(Context context, SaleOrderTask task) throws Throwable {
			if (null == task.entity && null != task.order) {
				task.entity = task.order.getInfo();
			}
			if (null == task.entity) {
				task.setSucceed(false);
				return;
			}
			ORMAccessor<SaleOrderInfo> acc = context.newORMAccessor(q_ORM_SaleOrder);
			acc.update(task.entity);
			if (null == task.order || null == task.order.getDets()) {
				return;
			}
			SaleOrdDetTask detTask = new SaleOrdDetTask();
			detTask.billsGuid = task.entity.getRECID();
			context.handle(detTask, TaskEnum.DELETE_LORD);
			List<SaleOrderItem> dets = getDets(task.order.getDets());
			for (int i = 0; i < dets.size(); i++) {
				SaleOrderItem det = task.order.getDets().get(i);
				det.setBillsId(task.entity.getRECID());
				detTask = new SaleOrdDetTask();
				detTask.entity = det;
				context.handle(detTask, TaskEnum.ADD);
			}
		}
	}

	@Publish
	protected class DeleteSaleOrdInfoHandler extends TaskMethodHandler<SaleOrderTask, TaskEnum> {
		public DeleteSaleOrdInfoHandler() {
			super(TaskEnum.DELETE);
		}

		@Override
		protected void handle(Context context, SaleOrderTask task) throws Throwable {
			ORMAccessor<SaleOrderInfo> acc = context.newORMAccessor(q_ORM_SaleOrder);
			acc.delete(task.recid);
			SaleOrdDetTask detTask = new SaleOrdDetTask();
			detTask.billsGuid = task.recid;
			context.handle(detTask, TaskEnum.DELETE_LORD);
		}
	}

	// ------------------------------对外接口-----------------------
	/**
	 * <p>
	 * 客户查询是否有关联的销售订单
	 * </p>
	 * 
	 * <p>
	 * Copyright: 版权所有 (c) 2002 - 2008<br>
	 * Company: 久其
	 * </p>
	 * 
	 * @author 莫迪
	 * @version 2011-12-2
	 */
	@Publish
	protected class CusproDeleteFlagHandler extends SimpleTaskMethodHandler<SelectSaleOrderByCuspTask> {

		@Override
		protected void handle(Context context, SelectSaleOrderByCuspTask task) throws Throwable {
			SelectSaleOrderByCuspSql sql = new SelectSaleOrderByCuspSql(context);// ,
			// task);
			DBCommand db = sql.getDB(null);
			GUID tenants = task.getTenantsGuid() == null ? BillsConstant.getTenantsGuid(context) : task.getTenantsGuid();
			db.setArgumentValues(tenants, task.getCusproGuid());
			task.setLenght(sql.getResult(db.executeQuery()));
		}

	}

}
