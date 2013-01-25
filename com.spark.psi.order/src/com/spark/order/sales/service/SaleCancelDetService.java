/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bills.service
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-10       莫迪        
 * ============================================================*/

package com.spark.order.sales.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.task.SaleCancelDetTask;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.sales.intf.entity.SaleCancelItem;
import com.spark.psi.order.sales.ORM_SaleCancelDet;
import com.spark.psi.order.sales.ORM_SaleCancelDetByBillsGuid;

/**
 * <p>销售退货明细Service</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 王天才
 * @version 2011-11-10
 */

public class SaleCancelDetService extends Service {

	private ORM_SaleCancelDet orm_SaleCancelDet;
	private ORM_SaleCancelDetByBillsGuid orm_SaleCancelDetByBillsGuid;
	protected SaleCancelDetService(ORM_SaleCancelDet orm_SaleCancelDet,ORM_SaleCancelDetByBillsGuid orm_SaleCancelDetByBillsGuid) {
		super("SaleCancelDetService");
		this.orm_SaleCancelDet = orm_SaleCancelDet;
		this.orm_SaleCancelDetByBillsGuid = orm_SaleCancelDetByBillsGuid;
	}
	
	
	/**
	 * 
	 * <p>新增</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 王天才
	 * @version 2011-11-10
	 */
	@Publish
	protected class AddSaleCancelDet extends TaskMethodHandler<SaleCancelDetTask, TaskEnum>
	{
		protected AddSaleCancelDet() {
			super(TaskEnum.ADD);
		}

		@Override
		protected void handle(Context context, SaleCancelDetTask task)
				throws Throwable {

			ORMAccessor<SaleCancelItem> orm = context.newORMAccessor(orm_SaleCancelDet);
			try
			{
				orm.insert(task.getEntity());
			}
			finally
			{
				orm.unuse();
			}
			
		}
	}
	
	
	/**
	 * 
	 * <p>删除主表下的所有明细</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 王天才
	 * @version 2011-11-10
	 */
	@Publish
	protected class DeleteSaleCancelDet extends TaskMethodHandler<SaleCancelDetTask, TaskEnum>
	{
		protected DeleteSaleCancelDet() {
			super(TaskEnum.DELETE);
		}

		@Override
		protected void handle(Context context, SaleCancelDetTask task)
				throws Throwable {

			StringBuilder dnaSql = new StringBuilder();
			if(null == task.getBillsGuid())
			{
				return;
			}
			dnaSql.append("define delete deleteCancelDetail(@billsGuid guid)");
			dnaSql.append(" begin ");
			dnaSql.append(" delete from PSI_Sales_Return_Det as t where t.billsId=@billsGuid ");
			dnaSql.append(" end ");
			DBCommand db = context.prepareStatement(dnaSql.toString());
			db.setArgumentValues(task.getBillsGuid());
			try
			{
				db.executeUpdate();
			}
			finally
			{
				db.unuse();
			}
			
		}
	}
	
	/**
	 * 
	 * <p>根据主表GUID查询所有明细</p>
	 *
	 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
	
	 *
	 * @author 王天才
	 * @version 2011-11-10
	 */
	@Publish
	protected class SaleCancelDetProvider extends OneKeyResultListProvider<SaleCancelItem, GUID>
	{
		@Override
		protected void provide(Context context, GUID guid, List<SaleCancelItem> list)
				throws Throwable {
			ORMAccessor<SaleCancelItem> orm = null;
			try
			{
				orm = context.newORMAccessor(orm_SaleCancelDetByBillsGuid);
				list.addAll(orm.fetch(guid));
			}
			finally
			{
				orm.unuse();
			}
		}
	}

}
