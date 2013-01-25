/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-14       莫迪        
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-14       莫迪        
 * ============================================================*/

package com.spark.order.purchase.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;
import com.spark.order.purchase.intf.task.PurchaseOrdDetTask;
import com.spark.order.service.dao.sql.impl.DelSql;
import com.spark.order.service.dao.sql.impl.del.BuyDetDelByBillsGuidSql;
import com.spark.psi.order.purchase.ORM_BuyOrderDet;

/**
 * <p>采购订单service</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-14
 */

public class BuyOrdDetService extends Service {

	protected final ORM_BuyOrderDet q_ORM_BuyOrderDet;

	protected BuyOrdDetService(ORM_BuyOrderDet qORMBuyOrderDet) {
		super("BuyOrdDetService");
		q_ORM_BuyOrderDet = qORMBuyOrderDet;
	}

	@Publish
	protected class BaseBuyOrdDetProvider extends
			OneKeyResultProvider<PurchaseOrderItem, GUID> {
		@Override
		protected PurchaseOrderItem provide(Context context, GUID id) throws Throwable {
			ORMAccessor<PurchaseOrderItem> acc = context
					.newORMAccessor(q_ORM_BuyOrderDet);
			return acc.findByRECID(id);
		}
	}
	
	/**
	 * <p>根据租户编号和订单编号获取订单明细</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 莫迪
	 * @version 2011-11-14
	 */
	@Publish
	protected class AllBuyOrdDetByBillsProvider extends TwoKeyResultListProvider<PurchaseOrderItem, GUID, GUID> {

		@Override
		protected void provide(Context context, GUID tenants, GUID bills,
				List<PurchaseOrderItem> resultList) throws Throwable {
			ORMAccessor<PurchaseOrderItem> acc = context
			.newORMAccessor(q_ORM_BuyOrderDet);
			resultList.addAll(acc.fetch(tenants, bills));
		}
	}
	
	/**
	 * <p>根据订单编号获取订单明细</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 莫迪
	 * @version 2011-11-14
	 */
	@Publish
	protected class AllBuyOrdDetByBillsIdProvider extends OneKeyResultListProvider<PurchaseOrderItem, GUID> {

		@Override
		protected void provide(Context context,GUID bills,
				List<PurchaseOrderItem> resultList) throws Throwable {
			ORMAccessor<PurchaseOrderItem> acc = context
			.newORMAccessor(q_ORM_BuyOrderDet);
			resultList.addAll(acc.fetch(BillsConstant.getTenantsGuid(context), bills));
		}
	}

	@Publish
	protected class AllBuyOrdDetProvider extends ResultListProvider<PurchaseOrderItem> {
		@Override
		protected void provide(Context context, List<PurchaseOrderItem> resultList)
				throws Throwable {
			ORMAccessor<PurchaseOrderItem> acc = context
					.newORMAccessor(q_ORM_BuyOrderDet);
			resultList.addAll(acc.fetch());
		}
	}

	@Publish
	protected class AddBuyOrdDetHandler extends
			TaskMethodHandler<PurchaseOrdDetTask, TaskEnum> {
		public AddBuyOrdDetHandler() {
			super(TaskEnum.ADD);
		}

		@Override
		protected void handle(Context context, PurchaseOrdDetTask task)
				throws Throwable {
			ORMAccessor<PurchaseOrderItem> acc = context
					.newORMAccessor(q_ORM_BuyOrderDet);
			acc.insert(task.entity);
		}
	}

	@Publish
	protected class ModifyBuyOrdDetHandler extends
			TaskMethodHandler<PurchaseOrdDetTask, TaskEnum> {
		public ModifyBuyOrdDetHandler() {
			super(TaskEnum.MODIFY);
		}

		@Override
		protected void handle(Context context, PurchaseOrdDetTask task)
				throws Throwable {
			ORMAccessor<PurchaseOrderItem> acc = context
					.newORMAccessor(q_ORM_BuyOrderDet);
			acc.update(task.entity);
		}
	}

	@Publish
	protected class DeleteBuyOrdDetHandler extends
			TaskMethodHandler<PurchaseOrdDetTask, TaskEnum> {
		public DeleteBuyOrdDetHandler() {
			super(TaskEnum.DELETE);
		}

		@Override
		protected void handle(Context context, PurchaseOrdDetTask task)
				throws Throwable {
			ORMAccessor<PurchaseOrderItem> acc = context
					.newORMAccessor(q_ORM_BuyOrderDet);
			acc.delete(task.recid);
		}
	}
	

	@Publish
	protected class DeleteBuyOrdDetByBuyOrderHandler extends
			TaskMethodHandler<PurchaseOrdDetTask, TaskEnum> {
		public DeleteBuyOrdDetByBuyOrderHandler() {
			super(TaskEnum.DELETE_LORD);
		}

		@Override
		protected void handle(Context context, PurchaseOrdDetTask task)
				throws Throwable {
			DelSql sql = new BuyDetDelByBillsGuidSql(context);
			DBCommand db = sql.getDB(null);
//			"@tenants guid, @bills guid"
			db.setArgumentValues(BillsConstant.getTenantsGuid(context), task.billsGuid);
			db.executeUpdate();
		}
	}

}
