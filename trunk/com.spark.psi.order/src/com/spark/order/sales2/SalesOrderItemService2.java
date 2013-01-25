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
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.service.DeleteOrderItem2ByOrderIdSql;
import com.spark.order.util.dnaSql.IDnaSql;
import com.spark.psi.order.storage.sales.Orm_SalesOrderItem2;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */

public class SalesOrderItemService2 extends Service {

	protected final Orm_SalesOrderItem2 q_Orm_SalesOrderItem2;

	protected SalesOrderItemService2(Orm_SalesOrderItem2 qOrmSalesOrderItem2) {
		super("SalesOrderItemService2");
		q_Orm_SalesOrderItem2 = qOrmSalesOrderItem2;
	}

	@Publish
	protected class BaseSalesOrderItem2Provider extends
			OneKeyResultProvider<SalesOrderItem2, GUID> {
		@Override
		protected SalesOrderItem2 provide(Context context, GUID id)
				throws Throwable {
			ORMAccessor<SalesOrderItem2> acc = context
					.newORMAccessor(q_Orm_SalesOrderItem2);
			return acc.findByRECID(id);
		}
	}

	@Publish
	protected class AllSalesOrderItem2Provider extends
			ResultListProvider<SalesOrderItem2> {
		@Override
		protected void provide(Context context, List<SalesOrderItem2> resultList)
				throws Throwable {
			ORMAccessor<SalesOrderItem2> acc = context
					.newORMAccessor(q_Orm_SalesOrderItem2);
			resultList.addAll(acc.fetch());
		}
	}
	
	@Publish
	protected class AllSalesOrderItem2ByOrderIdProvider extends
			OneKeyResultListProvider<SalesOrderItem2, GUID> {

		@Override
		protected void provide(Context context, GUID key,
				List<SalesOrderItem2> resultList) throws Throwable {
			ORMAccessor<SalesOrderItem2> acc = context
			.newORMAccessor(q_Orm_SalesOrderItem2);
			resultList.addAll(acc.fetch(BillsConstant.getTenantsGuid(context), key));
		}
	}

	@Publish
	protected class AddSalesOrderItem2Handler extends
			TaskMethodHandler<SalesOrderItemTask2, SalesOrderItemTask2.Method> {
		public AddSalesOrderItem2Handler() {
			super(SalesOrderItemTask2.Method.ADD);
		}

		@Override
		protected void handle(Context context, SalesOrderItemTask2 task)
				throws Throwable {
			ORMAccessor<SalesOrderItem2> acc = context
					.newORMAccessor(q_Orm_SalesOrderItem2);
			acc.insert(task.entity);
		}
	}

	@Publish
	protected class ModifySalesOrderItem2Handler extends
			TaskMethodHandler<SalesOrderItemTask2, SalesOrderItemTask2.Method> {
		public ModifySalesOrderItem2Handler() {
			super(SalesOrderItemTask2.Method.MODIFY);
		}

		@Override
		protected void handle(Context context, SalesOrderItemTask2 task)
				throws Throwable {
			ORMAccessor<SalesOrderItem2> acc = context
					.newORMAccessor(q_Orm_SalesOrderItem2);
			task.setSucceed( acc.update(task.entity));
		}
	}

	@Publish
	protected class DeleteSalesOrderItem2Handler extends
			TaskMethodHandler<SalesOrderItemTask2, SalesOrderItemTask2.Method> {
		public DeleteSalesOrderItem2Handler() {
			super(SalesOrderItemTask2.Method.DELETE);
		}

		@Override
		protected void handle(Context context, SalesOrderItemTask2 task)
				throws Throwable {
			ORMAccessor<SalesOrderItem2> acc = context
					.newORMAccessor(q_Orm_SalesOrderItem2);
			task.setSucceed(acc.delete(task.recid));
		}
	}
	
	@Publish
	protected class DeleteSalesOrderItem2ByOrderIdHandler extends
			TaskMethodHandler<SalesOrderItemTask2, SalesOrderItemTask2.Method> {
		public DeleteSalesOrderItem2ByOrderIdHandler() {
			super(SalesOrderItemTask2.Method.DELETE_Master);
		}

		@Override
		protected void handle(Context context, SalesOrderItemTask2 task)
				throws Throwable {
			if(null == task.orderId){
				throw new Throwable("相关订单id为空");
			}
			IDnaSql sql = new DeleteOrderItem2ByOrderIdSql(context, OrderEnum.Sales, task.orderId);
			task.setLenght(sql.executeUpdate());
			task.setSucceed(task.lenght() > 0);
		}
	}

}
