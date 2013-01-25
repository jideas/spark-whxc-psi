/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-19       Ī��        
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-19       Ī��        
 * ============================================================*/

package com.spark.order.sales.service;

import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.sales.intf.entity.SaleOrderItem;
import com.spark.order.sales.intf.task.SaleOrdDetTask;
import com.spark.order.service.dao.sql.impl.del.SaleDetDelByBillsGuidSql;
import com.spark.psi.order.sales.ORM_SaleOrderDet;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-19
 */

public class SaleOrdDetService extends Service {

	protected final ORM_SaleOrderDet q_ORM_SaleOrderDet;

	protected SaleOrdDetService(ORM_SaleOrderDet qORMSaleOrderDet) {
		super("SaleOrdDetService");
		q_ORM_SaleOrderDet = qORMSaleOrderDet;
	}

	@Publish
	protected class BaseSaleOrdDetProvider extends
			OneKeyResultProvider<SaleOrderItem, GUID> {
		@Override
		protected SaleOrderItem provide(Context context, GUID id)
				throws Throwable {
			ORMAccessor<SaleOrderItem> acc = context
					.newORMAccessor(q_ORM_SaleOrderDet);
			return acc.findByRECID(id);
		}
	}
	
	/**
	 * <p>���ݶ���guid��ѯ��ϸ����</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author Ī��
	 * @version 2011-11-19
	 */
	@Publish
	protected class AllSaleOrdDetBySaleOrderProvider extends
			OneKeyResultListProvider<SaleOrderItem, GUID> {

		@Override
		protected void provide(Context context, GUID bills, List<SaleOrderItem> resultList)
				throws Throwable {
			ORMAccessor<SaleOrderItem> acc = context
			.newORMAccessor(q_ORM_SaleOrderDet);
			resultList.addAll(acc.fetch(BillsConstant.getTenantsGuid(context), bills));
		}
	}

	@Publish
	protected class AllSaleOrdDetProvider extends
			ResultListProvider<SaleOrderItem> {
		@Override
		protected void provide(Context context, List<SaleOrderItem> resultList)
				throws Throwable {
			ORMAccessor<SaleOrderItem> acc = context
					.newORMAccessor(q_ORM_SaleOrderDet);
			resultList.addAll(acc.fetch());
		}
	}

	@Publish
	protected class AddSaleOrdDetHandler extends
			TaskMethodHandler<SaleOrdDetTask, TaskEnum> {
		public AddSaleOrdDetHandler() {
			super(TaskEnum.ADD);
		}

		@Override
		protected void handle(Context context, SaleOrdDetTask task)
				throws Throwable {
			ORMAccessor<SaleOrderItem> acc = context
					.newORMAccessor(q_ORM_SaleOrderDet);
			task.entity.setRECID(context.newRECID());
			task.entity.setCreateDate(new Date().getTime()); 
			if(null == task.entity.getCreator()){
				task.entity.setCreator(BillsConstant.getUserName(context));
			}
			acc.insert(task.entity);
		}
	}

	@Publish
	protected class ModifySaleOrdDetHandler extends
			TaskMethodHandler<SaleOrdDetTask, TaskEnum> {
		public ModifySaleOrdDetHandler() {
			super(TaskEnum.MODIFY);
		}

		@Override
		protected void handle(Context context, SaleOrdDetTask task)
				throws Throwable {
			ORMAccessor<SaleOrderItem> acc = context
					.newORMAccessor(q_ORM_SaleOrderDet);
			acc.update(task.entity);
		}
	}

	@Publish
	protected class DeleteSaleOrdDetHandler extends
			TaskMethodHandler<SaleOrdDetTask, TaskEnum> {
		public DeleteSaleOrdDetHandler() {
			super(TaskEnum.DELETE);
		}

		@Override
		protected void handle(Context context, SaleOrdDetTask task)
				throws Throwable {
			ORMAccessor<SaleOrderItem> acc = context
					.newORMAccessor(q_ORM_SaleOrderDet);
			acc.delete(task.recid);
		}
	}

	@Publish
	protected class DeleteSaleOrdDetBySaleOrderHandler extends
			TaskMethodHandler<SaleOrdDetTask, TaskEnum> {
		public DeleteSaleOrdDetBySaleOrderHandler() {
			super(TaskEnum.DELETE_LORD);
		}

		@Override
		protected void handle(Context context, SaleOrdDetTask task)
				throws Throwable {
			SaleDetDelByBillsGuidSql sql = new SaleDetDelByBillsGuidSql(context);
			DBCommand db = sql.getDB(null);
			db.setArgumentValues(BillsConstant.getTenantsGuid(context), task.billsGuid);
			db.executeUpdate();
		}
	}
}
