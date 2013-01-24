package com.spark.psi.inventory.internal.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.inventory.internal.entity.CheckInventoryItem;
import com.spark.psi.inventory.intf.inventoryenum.pub.Method;
import com.spark.psi.inventory.intf.key.checkinventory.CheckInventoryItemKey;
import com.spark.psi.inventory.intf.task.checkinventory.CheckInventoryItemTask;
import com.spark.psi.inventory.intf.util.checkinventory.StoreCheckDetDataOperator;
import com.spark.psi.inventory.service.orm.Orm_Store_Check_Det;

/**
 * @author durendong
 *
 */
public class StoreCheckDetService extends Service {

	private Orm_Store_Check_Det ormStoreCheckDet;
	
	protected StoreCheckDetService(Orm_Store_Check_Det ormStoreCheckDet) {
		super("StoreCheckDetService");
		this.ormStoreCheckDet = ormStoreCheckDet;
	}
	
	/**
	 * 删除
	 * @author durendong
	 *
	 */
	@Publish
	protected class StoreCheckDetDelTask extends TaskMethodHandler<CheckInventoryItemTask, Method>{

		protected StoreCheckDetDelTask() {
			super(Method.DELETE);
		}

		@Override
		protected void handle(Context context, CheckInventoryItemTask checkDetTask)
				throws Throwable {
			ORMAccessor<CheckInventoryItem> orm = context.newORMAccessor(ormStoreCheckDet);
			try {
				List<CheckInventoryItem> list = checkDetTask.getList();
				for(CheckInventoryItem checkDet : list) {
					orm.delete(checkDet);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				orm.unuse();
			}
		}
		
	}
	
	/**
	 * 新增
	 * @author durendong
	 *
	 */
	@Publish
	protected class StoreCheckDetAddTask extends TaskMethodHandler<CheckInventoryItemTask, Method> {

		protected StoreCheckDetAddTask() {
			super(Method.INSERT);
		}

		@Override
		protected void handle(Context context, CheckInventoryItemTask checkDetTask)
				throws Throwable {
			ORMAccessor<CheckInventoryItem> orm = context.newORMAccessor(ormStoreCheckDet);
			try {
				List<CheckInventoryItem> list = checkDetTask.getList();
				for(CheckInventoryItem checkDet : list) {
					orm.insert(checkDet);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				orm.unuse();
			}
		}
		
	}
	
	/**
	 * 通过盘点单查询盘点明细
	 * @author durendong
	 *
	 */
	@Publish
	protected class StoreCheckDetQuery extends OneKeyResultListProvider<CheckInventoryItem, CheckInventoryItemKey> {

		@Override
		protected void provide(Context context, CheckInventoryItemKey checkDetKey,
				List<CheckInventoryItem> list) throws Throwable {
			DBCommand command = null;
			if(checkDetKey.getGoodsTypeGuid() == null) {
				command = context.prepareStatement(StoreCheckDetDataOperator.getbossSql(checkDetKey));
				command.setArgumentValue(0, checkDetKey.getCheckOrdGuid());
			}else {
				command = context.prepareStatement(StoreCheckDetDataOperator.getSql(checkDetKey));
				command.setArgumentValue(0, checkDetKey.getCheckOrdGuid());
				command.setArgumentValue(1, checkDetKey.getGoodsTypeGuid());
				command.setArgumentValue(2, true);
				command.setArgumentValue(3, checkDetKey.getTenantsGuid());
			}
			RecordSet rs = command.executeQuery();
			while(rs.next()) {
				list.add(StoreCheckDetDataOperator.getStoreCheckDet(rs));
			}
			
			
			
		}
	}
	
}
