/**
 * 
 */
package com.spark.psi.message.handles;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.message.entity.SMessageInfo;
import com.spark.psi.message.entity.SMessageMapping;
import com.spark.psi.message.task.SMessageDelTask;
import com.spark.psi.message.task.SMessageInfoTask;
import com.spark.psi.message.task.SMessageMappingTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.SMessageTemplateEnum;
import com.spark.psi.publish.SMessageType;
import com.spark.psi.publish.inventory.entity.ShelfLifeWarningMaterialsItem;
import com.spark.psi.report.task.ShelfLifeQueueRunTask;
import com.spark.psi.report.task.ShelfLifeQueueStartTask;
import com.spark.psi.report.utils.SMessageUtils;

/**
 *
 */
public class ShelfLifeWarningService extends Service {

	/**
	 * @param title
	 */
	protected ShelfLifeWarningService() {
		super(ShelfLifeWarningService.class.getName());
	}

	public static boolean isRunning = false;

	/*
	 * 创建定时器，每分钟扫描任务队列，若有线程正在执行，则等待
	 */
	@Override
	protected void init(Context context) throws Throwable {
		context.asyncHandle(new ShelfLifeQueueStartTask());
	}

	@Publish
	protected class DoInitThings extends SimpleTaskMethodHandler<ShelfLifeQueueStartTask> {

		@Override
		protected void handle(Context context, ShelfLifeQueueStartTask task) throws Throwable {
			while (true) {
				System.out.println("================");
				Thread.sleep(300000);
				if (isRunning) {
					continue;
				}
				context.asyncHandle(new ShelfLifeQueueRunTask());
			}
		}
	}

	@Publish
	protected class QueueRunHandle extends SimpleTaskMethodHandler<ShelfLifeQueueRunTask> {

		@Override
		protected void handle(Context context, ShelfLifeQueueRunTask arg1) throws Throwable {
			isRunning = true;
			context.handle(new SMessageDelTask(SMessageType.ShelfLifeWarning, null));
			List<ShelfLifeWarningMaterialsItem> list = context.getList(ShelfLifeWarningMaterialsItem.class,
					new com.spark.psi.publish.inventory.key.GetShelfLifeWarningMaterialsKey());
			if (null == list || list.isEmpty()) {
				return;
			}
			Set<GUID> set = getGuidSet(SMessageUtils.getEmployeeByAuths(context, null, Auth.StoreKeeper));
			for (ShelfLifeWarningMaterialsItem item : list) {
				SMessageInfo info = new SMessageInfo();
				info.setMessageType(SMessageType.ShelfLifeWarning.getCode());
				info.setRECID(context.newRECID());
				info.setRelaObjId(item.getMaterialId());
				if (item.getShelfLifeWarningType() == com.spark.psi.publish.ShelfLifeWarningType.Closeto) {
					info.setTemplateCode(SMessageTemplateEnum.ShelfLifeWarning01.getCode());
					info.setStringValue1("仓库：" + item.getStoreName() + "中" + item.getMaterialName() + "临近保质期，数量："
							+ item.getCount());
				} else {
					info.setTemplateCode(SMessageTemplateEnum.ShelfLifeWarning02.getCode());
					info.setStringValue1("仓库：" + item.getStoreName() + "中" + item.getMaterialName() + "已过保质期，数量："
							+ item.getCount());
				}
				context.handle(new SMessageInfoTask(info));
				Date today = new Date();
				for (GUID e : set) {
					SMessageMapping mapping = new SMessageMapping();
					mapping.setEndTime(DateUtil.truncDay(today.getTime()) + 24 * 3600000);
					mapping.setMessageId(info.getRECID());
					mapping.setMessageType(SMessageType.ShelfLifeWarning.getCode());
					mapping.setRECID(context.newRECID());
					mapping.setStartTime(new Date().getTime());
					mapping.setUserId(e);
					context.handle(new SMessageMappingTask(mapping));
				}
			}
			isRunning = false;
		}
	} // 把employee集合变为guid集合

	private Set<GUID> getGuidSet(Set<Employee> set1) {
		Set<GUID> set = new HashSet<GUID>();
		if (null == set1) {
			return set;
		}
		for (Employee e : set1) {
			set.add(e.getId());
		}
		return set;
	}
}
