/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bills.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-10       Ī��        
 * ============================================================*/

package com.spark.order.purchase.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.TaskEnum;
import com.spark.order.purchase.intf.entity.PurchaseCancelItem;
import com.spark.order.purchase.intf.task.PurchaseCancelDetTask;
import com.spark.psi.order.purchase.ORM_BuyCancelDet;
import com.spark.psi.order.purchase.ORM_BuyCancelDetByBillsGuid;

/**
 * <p>�ɹ��˻���ϸService</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author �����
 * @version 2011-11-10
 */

public class BuyCancelDetService extends Service {

	protected final ORM_BuyCancelDet orm_BuyCancelDet;
	protected final ORM_BuyCancelDetByBillsGuid orm_BuyCancelDetByBillsGuid;
	protected BuyCancelDetService(ORM_BuyCancelDet orm_BuyCancelDet,ORM_BuyCancelDetByBillsGuid orm_BuyCancelDetByBillsGuid) {
		super("BuyCancelDetService");
		this.orm_BuyCancelDet = orm_BuyCancelDet;
		this.orm_BuyCancelDetByBillsGuid = orm_BuyCancelDetByBillsGuid;
	}
	
	
	/**
	 * 
	 * <p>����</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author �����
	 * @version 2011-11-10
	 */
	@Publish
	protected class AddBuyCancelDet extends TaskMethodHandler<PurchaseCancelDetTask, TaskEnum>
	{
		protected AddBuyCancelDet() {
			super(TaskEnum.ADD);
		}

		@Override
		protected void handle(Context context, PurchaseCancelDetTask task)
				throws Throwable {

			ORMAccessor<PurchaseCancelItem> orm = context.newORMAccessor(orm_BuyCancelDet);
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
	 * <p>ɾ�������µ�������ϸ</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author �����
	 * @version 2011-11-10
	 */
	@Publish
	protected class DeleteBuyCancelDet extends TaskMethodHandler<PurchaseCancelDetTask, TaskEnum>
	{
		protected DeleteBuyCancelDet() {
			super(TaskEnum.DELETE);
		}

		@Override
		protected void handle(Context context, PurchaseCancelDetTask task)
				throws Throwable {

			StringBuilder dnaSql = new StringBuilder();
			if(null == task.getBillsGuid())
			{
				return;
			}
			dnaSql.append("define delete deleteCancelDetail(@billsGuid guid)");
			dnaSql.append(" begin ");
			dnaSql.append(" delete from PSI_Purchase_Return_Det as t where t.billsId=@billsGuid ");
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
	 * <p>��������GUID��ѯ������ϸ</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author �����
	 * @version 2011-11-10
	 */
	@Publish
	protected class BuyCancelDetProvider extends OneKeyResultListProvider<PurchaseCancelItem, GUID>
	{
		@Override
		protected void provide(Context context, GUID guid, List<PurchaseCancelItem> list)
				throws Throwable {
			ORMAccessor<PurchaseCancelItem> orm = null;
			try
			{
				orm = context.newORMAccessor(orm_BuyCancelDetByBillsGuid);
				list.addAll(orm.fetch(guid));
			}
			finally
			{
				orm.unuse();
			}
		}
	}

}
