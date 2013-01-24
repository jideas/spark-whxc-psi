/**
 * 出入库模块接口service
 */
package com.spark.psi.inventory.service.pub;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.base.Inventory;
import com.spark.psi.inventory.intf.entity.instorage.mod.RelationCheckInSheet;
import com.spark.psi.inventory.intf.entity.instorage.pub.CheckInDeductionLog;
import com.spark.psi.inventory.intf.entity.inventory.InventoryLog;
import com.spark.psi.inventory.intf.entity.outstorage.mod.RelationCheckOutSheet;
import com.spark.psi.inventory.intf.entity.outstorage.pub.CheckOutDeductionLog;
import com.spark.psi.inventory.intf.key.instorage.mod.GetRelationCheckInSheetKey;
import com.spark.psi.inventory.intf.key.inventory.StoStreamKey;
import com.spark.psi.inventory.intf.key.outstorage.mod.GetRelationCheckOutSheetKey;
import com.spark.psi.inventory.intf.key.pub.GetCheckInDeductionLogKey;
import com.spark.psi.inventory.intf.key.pub.GetCheckOutDeductionLogKey;
import com.spark.psi.inventory.intf.key.pub.GetGoodsInventoryKey;
import com.spark.psi.inventory.intf.task.instorage.StopInTask;
import com.spark.psi.inventory.intf.task.inventory.InventoryLockTask;
import com.spark.psi.inventory.intf.task.outstorage.StopOutTask;
import com.spark.psi.inventory.intf.task.pub.ExcuteCheckingInTask;
import com.spark.psi.inventory.intf.task.pub.ExcuteCheckingOutTask;
import com.spark.psi.inventory.intf.task.pub.LockInventoryTask;
import com.spark.psi.inventory.intf.task.pub.StopCheckingInTask;
import com.spark.psi.inventory.intf.task.pub.StopCheckingOutTask;
import com.spark.psi.publish.inventory.key.GetInventoryLogKey;

/**
 * 模块间接口service
 * 
 */
public class InventoryModuleService extends Service {

	protected InventoryModuleService() {
		super("com.spark.psi.inventory.service.pub.InventoryModuleService");
	}

	/**
	 * 中止出库单
	 * 
	 * @author 97
	 * 
	 */
	@Publish
	protected class StopCheckingOutHandle extends
			SimpleTaskMethodHandler<StopCheckingOutTask> {

		@Override
		protected void handle(Context context, StopCheckingOutTask task)
				throws Throwable {
			StopOutTask sTask = new StopOutTask(task.getRelationOrderId(), task
					.getStopReason(), true);
			context.handle(sTask);
		}

	}

	/**
	 * 重新执行出库单
	 * 
	 * @author 97
	 * 
	 */
	@Publish
	protected class ExcuteCheckingOutHandle extends
			SimpleTaskMethodHandler<ExcuteCheckingOutTask> {

		@Override
		protected void handle(Context context, ExcuteCheckingOutTask task)
				throws Throwable {
			StopOutTask sTask = new StopOutTask(task.getRelationOrderId(),
					null, false);
			context.handle(sTask);
		}

	}

	/**
	 * 中止入库单
	 * 
	 * @author 97
	 * 
	 */
	@Publish
	protected class StopCheckingInHandle extends
			SimpleTaskMethodHandler<StopCheckingInTask> {

		@Override
		protected void handle(Context context, StopCheckingInTask task)
				throws Throwable {
			StopInTask sTask = new StopInTask(task.getRelationOrderId(), task
					.getStopReason(), true);
			context.handle(sTask);

		}

	}

	/**
	 * 重新执行入库单
	 * 
	 * @author 97
	 * 
	 */
	@Publish
	protected class ExcuteCheckingInHandle extends
			SimpleTaskMethodHandler<ExcuteCheckingInTask> {

		@Override
		protected void handle(Context context, ExcuteCheckingInTask task)
				throws Throwable {
			StopInTask sTask = new StopInTask(task.getRelationOrderId(), null,
					false);
			context.handle(sTask);

		}

	}

	/**
	 * 加锁/解锁库存
	 * 
	 * @author 97
	 * 
	 */
	@Publish
	protected class LockGoodsInventoryHandle extends SimpleTaskMethodHandler<LockInventoryTask>
	{

		@Override
		protected void handle(Context context, LockInventoryTask task)
				throws Throwable {
			InventoryLockTask lTask = new InventoryLockTask(task.getStoreId(), task.getGoodsId());
			lTask.setLockedCount(task.getCount());
			context.handle(lTask);
		}
		
	}

	
}
