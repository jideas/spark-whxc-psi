/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.order.sales.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-4-5     modi 
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.order.sales.service
 * �޸ļ�¼��
 * ����                ����           ����
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
import com.spark.psi.order.storage.sales.Orm_SalesReturn2;

/**
 * <p>�����˻���2</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-5
 */

public class SalesReturnService2 extends Service {

	protected final Orm_SalesReturn2 q_Orm_SalesReturn2;

	protected SalesReturnService2(Orm_SalesReturn2 qOrmSalesReturn2) {
		super("SalesReturnService2");
		q_Orm_SalesReturn2 = qOrmSalesReturn2;
	}

	@Publish
	protected class BaseSalesReturnInfo2Provider extends
			OneKeyResultProvider<SalesReturnInfo2, GUID> {
		@Override
		protected SalesReturnInfo2 provide(Context context, GUID id)
				throws Throwable {
			ORMAccessor<SalesReturnInfo2> acc = context
					.newORMAccessor(q_Orm_SalesReturn2);
			return acc.findByRECID(id);
		}
	}

	@Publish
	protected class AllSalesReturnInfo2Provider extends
			ResultListProvider<SalesReturnInfo2> {
		@Override
		protected void provide(Context context,
				List<SalesReturnInfo2> resultList) throws Throwable {
			ORMAccessor<SalesReturnInfo2> acc = context
					.newORMAccessor(q_Orm_SalesReturn2);
			resultList.addAll(acc.fetch());
		}
	}

	@Publish
	protected class AddSalesReturnInfo2Handler extends
			TaskMethodHandler<SalesReturnTask2, SalesReturnTask2.Method> {
		public AddSalesReturnInfo2Handler() {
			super(SalesReturnTask2.Method.ADD);
		}

		@Override
		protected void handle(Context context, SalesReturnTask2 task)
				throws Throwable {
			ORMAccessor<SalesReturnInfo2> acc = context
					.newORMAccessor(q_Orm_SalesReturn2);
			acc.insert(task.entity);
		}
	}

	@Publish
	protected class ModifySalesReturnInfo2Handler extends
			TaskMethodHandler<SalesReturnTask2, SalesReturnTask2.Method> {
		public ModifySalesReturnInfo2Handler() {
			super(SalesReturnTask2.Method.MODIFY);
		}

		@Override
		protected void handle(Context context, SalesReturnTask2 task)
				throws Throwable {
			ORMAccessor<SalesReturnInfo2> acc = context
					.newORMAccessor(q_Orm_SalesReturn2);
			acc.update(task.entity);
		}
	}

	@Publish
	protected class DeleteSalesReturnInfo2Handler extends
			TaskMethodHandler<SalesReturnTask2, SalesReturnTask2.Method> {
		public DeleteSalesReturnInfo2Handler() {
			super(SalesReturnTask2.Method.DELETE);
		}

		@Override
		protected void handle(Context context, SalesReturnTask2 task)
				throws Throwable {
			ORMAccessor<SalesReturnInfo2> acc = context
					.newORMAccessor(q_Orm_SalesReturn2);
			acc.delete(task.recid);
		}
	}

}
