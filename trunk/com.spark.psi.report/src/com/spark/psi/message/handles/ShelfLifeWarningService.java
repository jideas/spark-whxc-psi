/**
 * 
 */
package com.spark.psi.message.handles;

import com.jiuqi.dna.core.service.Service;

/**
 *
 */
public class ShelfLifeWarningService extends Service {
	//
	/**
	 * @param title
	 */
	protected ShelfLifeWarningService() {
		super(ShelfLifeWarningService.class.getName());
	}


	// public static boolean isRunning = false;
	//
	// /*
	// * ������ʱ����ÿ����ɨ��������У������߳�����ִ�У���ȴ�
	// */
	// @Override
	// protected void init(Context context) throws Throwable {
	// context.asyncHandle(new ShelfLifeQueueStartTask());
	// }
	//
	// @Publish
	// protected class DoInitThings extends
	// SimpleTaskMethodHandler<ShelfLifeQueueStartTask> {
	//
	// @Override
	// protected void handle(Context context, ShelfLifeQueueStartTask task)
	// throws Throwable {
	// while (true) {
	// Thread.sleep(300000);
	// if (isRunning) {
	// continue;
	// }
	// context.asyncHandle(new ShelfLifeQueueRunTask());
	// }
	// }
	// }
	//
	// @Publish
	// protected class QueueRunHandle extends
	// SimpleTaskMethodHandler<ShelfLifeQueueRunTask> {
	//
	// @Override
	// protected void handle(Context context, ShelfLifeQueueRunTask arg1) throws
	// Throwable {
	// isRunning = true;
	// context.handle(new SMessageDelTask(SMessageType.ShelfLifeWarning, null));
	// List<ShelfLifeWarningMaterialsItem> list =
	// context.getList(ShelfLifeWarningMaterialsItem.class,
	// new
	// com.spark.psi.publish.inventory.key.GetShelfLifeWarningMaterialsKey());
	// if (null == list || list.isEmpty()) {
	// return;
	// }
	// Set<GUID> set = getGuidSet(SMessageUtils.getEmployeeByAuths(context,
	// null, Auth.StoreKeeper));
	// for (ShelfLifeWarningMaterialsItem item : list) {
	// SMessageInfo info = new SMessageInfo();
	// info.setMessageType(SMessageType.ShelfLifeWarning.getCode());
	// info.setRECID(context.newRECID());
	// info.setRelaObjId(item.getMaterialId());
	// if (item.getShelfLifeWarningType() ==
	// com.spark.psi.publish.ShelfLifeWarningType.Closeto) {
	// info.setTemplateCode(SMessageTemplateEnum.ShelfLifeWarning01.getCode());
	// info.setStringValue1("�ֿ⣺" + item.getStoreName() + "��" +
	// item.getMaterialName() + "�ٽ������ڣ�������"
	// + item.getCount());
	// } else {
	// info.setTemplateCode(SMessageTemplateEnum.ShelfLifeWarning02.getCode());
	// info.setStringValue1("�ֿ⣺" + item.getStoreName() + "��" +
	// item.getMaterialName() + "�ѹ������ڣ�������"
	// + item.getCount());
	// }
	// context.handle(new SMessageInfoTask(info));
	// Date today = new Date();
	// for (GUID e : set) {
	// SMessageMapping mapping = new SMessageMapping();
	// mapping.setEndTime(DateUtil.truncDay(today.getTime()) + 24 * 3600000);
	// mapping.setMessageId(info.getRECID());
	// mapping.setMessageType(SMessageType.ShelfLifeWarning.getCode());
	// mapping.setRECID(context.newRECID());
	// mapping.setStartTime(new Date().getTime());
	// mapping.setUserId(e);
	// context.handle(new SMessageMappingTask(mapping));
	// }
	// }
	// isRunning = false;
	// }
	// } // ��employee���ϱ�Ϊguid����
	//
	// private Set<GUID> getGuidSet(Set<Employee> set1) {
	// Set<GUID> set = new HashSet<GUID>();
	// if (null == set1) {
	// return set;
	// }
	// for (Employee e : set1) {
	// set.add(e.getId());
	// }
	// return set;
	// }
}
