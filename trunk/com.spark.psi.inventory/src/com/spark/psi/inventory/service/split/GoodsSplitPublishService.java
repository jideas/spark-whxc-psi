package com.spark.psi.inventory.service.split;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.split.entity.GoodsSplitInfo;
import com.spark.psi.publish.split.entity.GoodsSplitItem;
import com.spark.psi.publish.split.key.GetGoodsSplitBillListKey;
import com.spark.psi.publish.split.task.DeleteGoodsSplitBillTask;
import com.spark.psi.publish.split.task.UpdateGoodsSplitBillTask;
import com.spark.psi.publish.split.task.UpdateGoodsSplitStatusTask;

public class GoodsSplitPublishService extends Service {

	protected GoodsSplitPublishService() {
		super("com.spark.psi.inventory.service.split.GoodsSplitPublishService");
	}

	/**
	 * 分页查询列表
	 */
	@Publish
	protected class GetGoodsSplitBillList
			extends
			OneKeyResultProvider<ListEntity<GoodsSplitItem>, GetGoodsSplitBillListKey> {

		@Override
		protected ListEntity<GoodsSplitItem> provide(Context context,
				GetGoodsSplitBillListKey key) throws Throwable {
			return null;
		}
	}

	/**
	 * 新增
	 */
	@Publish
	protected class UpdateGoodsSplitBill extends
			SimpleTaskMethodHandler<UpdateGoodsSplitBillTask> {

		@Override
		protected void handle(Context context, UpdateGoodsSplitBillTask task)
				throws Throwable {

		}

	}

	/**
	 * 删除
	 */
	@Publish
	protected class DeleteGoodsSplitBill extends
			SimpleTaskMethodHandler<DeleteGoodsSplitBillTask> {

		@Override
		protected void handle(Context context, DeleteGoodsSplitBillTask task)
				throws Throwable {

		}

	}

	/**
	 * 状态变更
	 */
	@Publish
	protected class UpdateGoodsSplitStatus extends
			SimpleTaskMethodHandler<UpdateGoodsSplitStatusTask> {

		@Override
		protected void handle(Context context, UpdateGoodsSplitStatusTask task)
				throws Throwable {

		}

	}

	/**
	 * 详情
	 */
	@Publish
	protected class FindGoodsSplitBill extends
			OneKeyResultProvider<GoodsSplitInfo, GUID> {

		@Override
		protected GoodsSplitInfo provide(Context context, GUID key)
				throws Throwable {
			return null;
		}
	}
}
