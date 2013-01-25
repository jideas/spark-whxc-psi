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
import com.spark.psi.order.storage.sales.Orm_SalesReturnItem2;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */

public class SalesReturnItemService2 extends Service {

	protected final Orm_SalesReturnItem2 q_Orm_SalesReturnItem2;

	protected SalesReturnItemService2(Orm_SalesReturnItem2 qOrmSalesReturnItem2) {
		super("SalesReturnItemTask2");
		q_Orm_SalesReturnItem2 = qOrmSalesReturnItem2;
	}

	@Publish
	protected class BaseSalesReturnItem2Provider extends
			OneKeyResultProvider<SalesReturnItem2, GUID> {
		@Override
		protected SalesReturnItem2 provide(Context context, GUID id)
				throws Throwable {
			ORMAccessor<SalesReturnItem2> acc = context
					.newORMAccessor(q_Orm_SalesReturnItem2);
			return acc.findByRECID(id);
		}
	}

	@Publish
	protected class AllSalesReturnItem2Provider extends
			ResultListProvider<SalesReturnItem2> {
		@Override
		protected void provide(Context context,
				List<SalesReturnItem2> resultList) throws Throwable {
			ORMAccessor<SalesReturnItem2> acc = context
					.newORMAccessor(q_Orm_SalesReturnItem2);
			resultList.addAll(acc.fetch());
		}
	}
	
	@Publish
	protected class AllSalesReturnItem2ByOrderIdProvider extends
			OneKeyResultListProvider<SalesReturnItem2, GUID> {

		@Override
		protected void provide(Context context, GUID key,
				List<SalesReturnItem2> resultList) throws Throwable {
			ORMAccessor<SalesReturnItem2> acc = context
			.newORMAccessor(q_Orm_SalesReturnItem2);
			resultList.addAll(acc.fetch(BillsConstant.getTenantsGuid(context), key));
		}
	}

	@Publish
	protected class AddSalesReturnItem2Handler
			extends
			TaskMethodHandler<SalesReturnItemTask2, SalesReturnItemTask2.Method> {
		public AddSalesReturnItem2Handler() {
			super(SalesReturnItemTask2.Method.ADD);
		}

		@Override
		protected void handle(Context context, SalesReturnItemTask2 task)
				throws Throwable {
			ORMAccessor<SalesReturnItem2> acc = context
					.newORMAccessor(q_Orm_SalesReturnItem2);
			acc.insert(task.entity);
		}
	}

	@Publish
	protected class ModifySalesReturnItem2Handler
			extends
			TaskMethodHandler<SalesReturnItemTask2, SalesReturnItemTask2.Method> {
		public ModifySalesReturnItem2Handler() {
			super(SalesReturnItemTask2.Method.MODIFY);
		}

		@Override
		protected void handle(Context context, SalesReturnItemTask2 task)
				throws Throwable {
			ORMAccessor<SalesReturnItem2> acc = context
					.newORMAccessor(q_Orm_SalesReturnItem2);
			task.setSucceed(acc.update(task.entity));
		}
	}

	@Publish
	protected class DeleteSalesReturnItem2Handler
			extends
			TaskMethodHandler<SalesReturnItemTask2, SalesReturnItemTask2.Method> {
		public DeleteSalesReturnItem2Handler() {
			super(SalesReturnItemTask2.Method.DELETE);
		}

		@Override
		protected void handle(Context context, SalesReturnItemTask2 task)
				throws Throwable {
			ORMAccessor<SalesReturnItem2> acc = context
					.newORMAccessor(q_Orm_SalesReturnItem2);
			task.setSucceed(acc.delete(task.recid));
		}
	}
	
	@Publish
	protected class DeleteSalesReturnItem2ByOrderIdHandler
			extends
			TaskMethodHandler<SalesReturnItemTask2, SalesReturnItemTask2.Method> {
		public DeleteSalesReturnItem2ByOrderIdHandler() {
			super(SalesReturnItemTask2.Method.DELETE_Master);
		}

		@Override
		protected void handle(Context context, SalesReturnItemTask2 task)
				throws Throwable {
			if(null == task.orderId){
				throw new Throwable("相关订单id为空");
			}
			IDnaSql sql = new DeleteOrderItem2ByOrderIdSql(context, OrderEnum.Sales_Return, task.orderId);
			task.setLenght(sql.executeUpdate());
			task.setSucceed(task.lenght()>0);
		}
	}

}
