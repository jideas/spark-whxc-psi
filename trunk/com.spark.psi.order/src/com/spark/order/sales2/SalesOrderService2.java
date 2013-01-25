/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.sales.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-5     modi 
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.order.sales.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-4-5     modi 
 * ============================================================*/

package com.spark.order.sales2;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.util.dnaSql.DnaSql_update;
import com.spark.order.util.dnaSql.IDnaSql;
import com.spark.psi.order.storage.sales.Orm_SalesOrder2;

/**
 * <p>销售订单Task</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */

public class SalesOrderService2 extends Service {

	protected final Orm_SalesOrder2 q_Orm_SalesOrder2;

	protected SalesOrderService2(Orm_SalesOrder2 qOrmSalesOrder2) {
		super("SalesOrderService2");
		q_Orm_SalesOrder2 = qOrmSalesOrder2;
	}

	@Publish
	protected class BaseSalesOrderInfo2Provider extends
			OneKeyResultProvider<SalesOrderInfo2, GUID> {
		@Override
		protected SalesOrderInfo2 provide(Context context, GUID id)
				throws Throwable {
			ORMAccessor<SalesOrderInfo2> acc = context
					.newORMAccessor(q_Orm_SalesOrder2);
			return acc.findByRECID(id);
		}
	}

	@Publish
	protected class AllSalesOrderInfo2Provider extends
			ResultListProvider<SalesOrderInfo2> {
		@Override
		protected void provide(Context context, List<SalesOrderInfo2> resultList)
				throws Throwable {
			ORMAccessor<SalesOrderInfo2> acc = context
					.newORMAccessor(q_Orm_SalesOrder2);
			resultList.addAll(acc.fetch());
		}
	}

	@Publish
	protected class AddSalesOrderInfo2Handler extends
			TaskMethodHandler<SalesOrderTask2, SalesOrderTask2.Method> {
		public AddSalesOrderInfo2Handler() {
			super(SalesOrderTask2.Method.ADD);
		}

		@Override
		protected void handle(Context context, SalesOrderTask2 task)
				throws Throwable {
			ORMAccessor<SalesOrderInfo2> acc = context
					.newORMAccessor(q_Orm_SalesOrder2);
			acc.insert(task.entity);
		}
	}

	@Publish
	protected class ModifySalesOrderInfo2Handler extends
			TaskMethodHandler<SalesOrderTask2, SalesOrderTask2.Method> {
		public ModifySalesOrderInfo2Handler() {
			super(SalesOrderTask2.Method.MODIFY);
		}

		@Override
		protected void handle(Context context, SalesOrderTask2 task)
				throws Throwable {
			ORMAccessor<SalesOrderInfo2> acc = context
					.newORMAccessor(q_Orm_SalesOrder2);
			task.setSucceed(acc.update(task.entity));
		}
	}

	@Publish
	protected class DeleteSalesOrderInfo2Handler extends
			TaskMethodHandler<SalesOrderTask2, SalesOrderTask2.Method> {
		public DeleteSalesOrderInfo2Handler() {
			super(SalesOrderTask2.Method.DELETE);
		}

		@Override
		protected void handle(Context context, SalesOrderTask2 task)
				throws Throwable {
			ORMAccessor<SalesOrderInfo2> acc = context
					.newORMAccessor(q_Orm_SalesOrder2);
			task.setSucceed(acc.delete(task.recid));
		}
	}
	
	@Publish
	class SalesAllocateHandler extends SimpleTaskMethodHandler<SalesAllocateTask>{

		@Override
		protected void handle(Context context, SalesAllocateTask task)
				throws Throwable {
			IDnaSql sql = new SalesAllocateSql(context, task);
			task.setLenght(sql.executeUpdate());
			task.setSucceed(task.lenght() == 1);
		}
		class SalesAllocateSql extends DnaSql_update{
			private final SalesAllocateTask task;
			public SalesAllocateSql(Context context, SalesAllocateTask task) {
				super(context);
				this.task = task;
			}

			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" update ");
				sql.append(OrderEnum.Sales.getDb_table());
				sql.append(" as t ");
				sql.append(" set ");
				sql.append(" isAllot = @isAllot ");
				this.addParam("@isAllot boolean", true);
				sql.append(" where ");
				sql.append(" t.recid = @id ");
				this.addParam("@id guid", task.getId());
				sql.append(" and t.isAllot <> @isAllot ");
				this.addParam("@isAllot boolean", true);
				return sql.toString();
			}
			
		}
	}
	
	@Publish
	class SetSalesPlanOutDateHandler extends SimpleTaskMethodHandler<SetSalesPlanOutDateTask>{

		@Override
		protected void handle(Context context, SetSalesPlanOutDateTask task)
				throws Throwable {
			IDnaSql sql = new SetSalesPlanOutDateSql(context, task);
			task.setLenght(sql.executeUpdate());
			task.setSucceed(task.lenght() == 1);
		}
		class SetSalesPlanOutDateSql extends DnaSql_update{
			private SetSalesPlanOutDateTask task;
			public SetSalesPlanOutDateSql(Context context, SetSalesPlanOutDateTask task) {
				super(context);
				this.task = task;
			}
			@Override
			protected String getSql() {
				StringBuilder sql = new StringBuilder();
				sql.append(" update ");
				sql.append(OrderEnum.Sales.getDb_table());
				sql.append(" as t ");
				sql.append(" set ");
				sql.append(" planOutDate = @planDate ");
				this.addParam("@planDate date", task.getDate());
				sql.append(" where ");
				sql.append(" t.recid = @id ");
				this.addParam("@id guid", task.getId());
				sql.append(" and ");
				sql.append(" t.planOutDate <> @planDate ");
				this.addParam("@planDate date", task.getDate());
				return sql.toString();
			}
			
		}
		
	}

}
