/**
 * 
 */
package com.spark.psi.report.queue;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Inventory;
import com.spark.psi.base.MaterialsItem;
import com.spark.psi.base.Notice;
import com.spark.psi.base.Store;
import com.spark.psi.base.ApprovalConfig.Mode;
import com.spark.psi.base.event.ApprovalConfigChangedEvent;
import com.spark.psi.base.event.ApprovalLogEvent;
import com.spark.psi.base.event.CustomerCreateEvent;
import com.spark.psi.base.event.CustomerDataChangeEvent;
import com.spark.psi.base.event.CustomerTurnOfficalEvent;
import com.spark.psi.base.event.EmployeeDepartmentChangeEvent;
import com.spark.psi.base.event.EmployeeStatusChangeEvent;
import com.spark.psi.base.event.GoodsCategoryStoreLimitUpperChangeEvent;
import com.spark.psi.base.event.GoodsStatusChangeEvent;
import com.spark.psi.base.event.MaterialsItemThresholdChangeEvent;
import com.spark.psi.base.event.NoticeReadEvent;
import com.spark.psi.base.event.NoticeStatusChangeEvent;
import com.spark.psi.base.event.StoreStatusChangeEvent;
import com.spark.psi.base.event.NoticeStatusChangeEvent.NoticeAction;
import com.spark.psi.base.key.GetChildrenDeptEmployeeListByAuthKey;
import com.spark.psi.base.key.GetInventoryByStockIdKey;
import com.spark.psi.message.entity.SMessageInfo;
import com.spark.psi.message.entity.SMessageMapping;
import com.spark.psi.message.task.SMessageDelTask;
import com.spark.psi.message.task.SMessageInfoTask;
import com.spark.psi.message.task.SMessageMappingTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.InventoryType;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.SMessageTemplateEnum;
import com.spark.psi.publish.SMessageType;
import com.spark.psi.publish.base.notice.key.FindDeptGuidListByNoticeKey;
import com.spark.psi.report.entity.ReportQueue;
import com.spark.psi.report.utils.AuthUtils;
import com.spark.psi.report.utils.SMessageUtils;

/**
 * ����ģ��������ȡ
 */
public class BaseTaskHandle {

	// Ա����ְ����ְ(Ա��ID)
	@SuppressWarnings("deprecation")
	protected static void handle(Context context, EmployeeStatusChangeEvent event, ReportQueue rq) {
		/**
		 *��Ա������Ԥ����Ϣ
		 */
		if (event.getAction() == com.spark.psi.base.event.EmployeeStatusChangeEvent.Action.Resign) {
			context.handle(new SMessageDelTask(SMessageType.NearBirthday, event.getId()));
		} else {
			Employee emp = context.find(Employee.class, event.getId());
			Date birthday = new Date(getBrithday(emp.getBirthday()));
			Date today = new Date();
			long count = DateUtil.getDaysBetween(today, birthday);
			if (count < 0 || count >= 7) {
				return;
			}
			SMessageInfo info = new SMessageInfo();
			info.setMessageType(SMessageType.NearBirthday.getCode());
			info.setRECID(context.newRECID());
			info.setRelaObjId(emp.getId());
			info.setTemplateCode(SMessageTemplateEnum.NearBirthday01.getCode());
			info.setStringValue1(emp.getName());
			Department dep = context.find(Department.class, emp.getDepartmentId());
			info.setStringValue2(dep.getName());
			info.setStringValue3((birthday.getMonth() + 1) + "��" + birthday.getDate() + "��");
			context.handle(new SMessageInfoTask(info));
			Set<GUID> set = getGuidSet(SMessageUtils.getMyManager(context, emp, false, Auth.Boss, Auth.SalesManager, Auth.AccountManager,
					Auth.PurchaseManager, Auth.StoreKeeperManager));
			Set<GUID> set2 = getGuidSet(SMessageUtils.getEmployeeByAuths(context, null, Auth.Assistant));
			Set<GUID> set3 = getGuidSet(SMessageUtils.getEmployeeByAuths(context, null, Auth.Boss));
			set2.removeAll(set3);
			set2.remove(emp.getId());
			if (CheckIsNull.isNotEmpty(set2) && set2.size() > 0) {
				set.addAll(set2);
				set.removeAll(set3);
			}
			for (GUID e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setEndTime(DateUtil.truncDay(today.getTime()) + count * 24 * 3600000);
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(SMessageType.NearBirthday.getCode());
				mapping.setRECID(context.newRECID());
				mapping.setStartTime(new Date().getTime());
				mapping.setUserId(e);
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

	// Ա�����ű仯��Ա��ID��
	@SuppressWarnings("deprecation")
	protected static void handle(Context context, EmployeeDepartmentChangeEvent event, ReportQueue rq) {
		Employee emp = context.find(Employee.class, event.getId());
		if (emp == null) {
			return;
		}
		/**
		 * �����������ѽ�����
		 */
		context.handle(new SMessageDelTask(SMessageType.NearBirthday, event.getId()));
		Date birthday = new Date(getBrithday(emp.getBirthday()));
		Date today = new Date();
		long count = DateUtil.getDaysBetween(today, birthday);
		if (count < 0 || count >= 7) {
			return;
		}
		SMessageInfo info = new SMessageInfo();
		info.setMessageType(SMessageType.NearBirthday.getCode());
		info.setRECID(context.newRECID());
		info.setRelaObjId(emp.getId());
		info.setTemplateCode(SMessageTemplateEnum.NearBirthday01.getCode());
		info.setStringValue1(emp.getName());
		Department dep = context.find(Department.class, emp.getDepartmentId());
		info.setStringValue2(dep.getName());
		info.setStringValue3((birthday.getMonth() + 1) + "��" + birthday.getDate() + "��");
		context.handle(new SMessageInfoTask(info));
		Set<GUID> set = getGuidSet(SMessageUtils.getMyManager(context, emp, false, Auth.Boss, Auth.SalesManager, Auth.AccountManager,
				Auth.PurchaseManager, Auth.StoreKeeperManager));
		Set<GUID> set2 = getGuidSet(SMessageUtils.getEmployeeByAuths(context, null, Auth.Assistant));
		Set<GUID> set3 = getGuidSet(SMessageUtils.getEmployeeByAuths(context, null, Auth.Boss));
		set2.removeAll(set3);
		set2.remove(emp.getId());
		if (CheckIsNull.isNotEmpty(set2) && set2.size() > 0) {
			set.addAll(set2);
			set.removeAll(set3);
		}
		for (GUID e : set) {
			SMessageMapping mapping = new SMessageMapping();
			mapping.setEndTime(DateUtil.truncDay(today.getTime()) + count * 24 * 3600000);
			mapping.setMessageId(info.getRECID());
			mapping.setMessageType(SMessageType.NearBirthday.getCode());
			mapping.setRECID(context.newRECID());
			mapping.setStartTime(new Date().getTime());
			mapping.setUserId(e);
			context.handle(new SMessageMappingTask(mapping));
		}
	}

	// ��employee���ϱ�Ϊguid����
	public static Set<GUID> getGuidSet(Set<Employee> set1) {
		Set<GUID> set = new HashSet<GUID>();
		if (null == set1) {
			return set;
		}
		for (Employee e : set1) {
			set.add(e.getId());
		}
		return set;
	}

	// ������÷����仯���Ķ���Χ��
	protected static void handle(Context context, ApprovalConfigChangedEvent event, ReportQueue rq) {
		/**
		 * ���´���������Ԥ����Ϣ
		 */
		// �ж��Ǹ��ĵ����ֵ��ݵ���������
		// ɾ�����д���˵�����ʾ��Ϣ
		// ɾ���������˻ص���Ԥ����Ϣ
		// ɨ��ҵ������������˻ص��ݣ��������ɸ����˷��͵�Ԥ����Ϣ
		Mode mode = event.getMode();
		SMessageDelTask task = new SMessageDelTask(SMessageType.UnapproveOrders, null);
		task.setTenantId(rq.getTenantsGuid());
		switch (mode) {
		case BLENDING:
			task.setTemp(SMessageTemplateEnum.UnapproveOrders06);
			break;
		case BUY_ORDER:
			task.setTemp(SMessageTemplateEnum.UnapproveOrders01);
			break;
		case BUY_RETURN:
			task.setTemp(SMessageTemplateEnum.UnapproveOrders03);
			break;
		// case PROMOTION:
		// task.setTemp(SMessageTemplateEnum.UnapproveOrders05);
		// break;
		case SALES_BILLS:
			task.setTemp(SMessageTemplateEnum.UnapproveOrders02);
			break;
		case SALES_RETURN:
			task.setTemp(SMessageTemplateEnum.UnapproveOrders04);
			break;
		default:
			break;
		}
		if (null != task) {
			context.handle(task);
		}
	}

	// ��˼�¼����ʱ�䣨������¼ID��
	protected static void handle(Context context, ApprovalLogEvent event, ReportQueue rq) {
		/**
		 * ɾ����������
		 */
		// �õ�������¼ʵ��
		// �ж������������ͣ���ѯ��������ʵ��
		// ɾ���õ��ݵ���������
		// �ж�������״̬���绹�Ǵ�������ת���۶��������߼�
		// ���Ǵ�����������
		// !!!!!!!!�����������۶�����˺������Ȼ��Ҫ���
		// ��������������������������������
		// ���������÷������汾��ʹ�ã�����
		// ��������������������������������

	}

	// ��Ʒ״̬�仯����ƷID��
	protected static void handle(Context context, GoodsStatusChangeEvent event, ReportQueue rq) {
		/**
		 * ������Ʒ���Ԥ����Ϣ
		 */
		// �ж���ͣ�ۻ�����������
		// 1ͣ�ۣ�ɾ����Ʒ���Ԥ����Ϣ
		// 2���ã���ѯ��Ʒ�����Ϣ���ж��Ƿ�Ԥ��
		// !!!!!!!!!�ĵ��в����ڸ�����¸���Ԥ����Ϣ������Ƿ���������������
	}

	// �ֿ�״̬�仯���ֿ�ID��
	protected static void handle(Context context, StoreStatusChangeEvent event, ReportQueue rq) {
		/**
		 * ������Ʒ���Ԥ����Ϣ
		 */
		// aͣ�ã�ɾ�����й����òֿ����ƷԤ����Ϣ
		// b���ã�ɨ�����иòֿ���ƷԤ����Ϣ
		// !!!!!!!!!�ĵ��в����ڸ�����¸���Ԥ����Ϣ������Ƿ���������������
	}

	// �ͻ���Ϣ�ı��¼�
	protected static void handle(Context context, CustomerDataChangeEvent event, ReportQueue rq) {
		/**
		 * �����˿ͻ��������ú�Ԥ��������Ϣ
		 */
		// Partner p = context.find(Partner.class, event.getCustomerId());
		// if (null == p) {
		// return;
		// }
		// /**
		// * �����֣�ҵ�����˱��
		// */
		// if (PartnerStatus.Official == p.getStatus()) {
		// customerCountData(context, event.getCustomerId(), rq,
		// CustomerLogType.Offical.getCode());
		// } else {
		// customerCountData(context, event.getCustomerId(), rq,
		// CustomerLogType.Create.getCode());
		// }
	}

	// �ͻ������¼�
	protected static void handle(Context context, CustomerCreateEvent event, ReportQueue rq) {
		// customerCountData(context, event.getCustomerId(), rq,
		// CustomerLogType.Create.getCode());
	}

	// �ͻ�ת���¼�
	protected static void handle(Context context, CustomerTurnOfficalEvent event, ReportQueue rq) {
		// customerCountData(context, event.getCustomerId(), rq,
		// CustomerLogType.Offical.getCode());
	}

	// private static void customerCountData(Context context, GUID customerId,
	// ReportQueue rq, String code) {
	// Partner p = context.find(Partner.class, customerId);
	// if (null == p) {
	// return;
	// }
	// ReportCustomerCountTask task = new ReportCustomerCountTask();
	// task.setCurrDate(rq.getCurrTime());
	// task.setCustomerId(customerId);
	// Date date = new Date(rq.getCurrTime());
	// task.setDateNo(ReportDateUtils.getDateNo(date));
	// if (null != p.getBusinessPerson()) {
	// task.setDeptId(p.getBusinessPerson().getDepartmentId());
	// task.setEmployeeId(p.getBusinessPerson().getId());
	// }
	// task.setLogType(code);
	// task.setMonthNo(ReportDateUtils.getMonthNo(date));
	// task.setQuarter(ReportDateUtils.getQuarter(date));
	// task.setTenantId(rq.getTenantsGuid());
	// task.setYearNo(ReportDateUtils.getYearNo(date));
	// context.handle(task);
	// }

	/**
	 * ����״̬����¼�
	 */
	public static void handle(Context context, NoticeStatusChangeEvent event, ReportQueue rq) {
		if (event.getAction() == NoticeAction.Expired) {
			context.handle(new SMessageDelTask(SMessageType.UnreadNotices, event.getId()));
			return;
		}
		Notice notice = context.find(Notice.class, event.getId());
		SMessageInfo info = new SMessageInfo();
		info.setMessageType(SMessageType.UnreadNotices.getCode());
		info.setRECID(context.newRECID());
		info.setRelaObjId(event.getId());
		info.setTemplateCode(SMessageTemplateEnum.UnreadNotices01.getCode());
		// info.setTenantId(rq.getTenantsGuid());
		info.setStringValue1(notice.getNoticeTitle());
		info.setStringValue2(DateUtil.dateFromat(notice.getPublishTime()));
		context.handle(new SMessageInfoTask(info));
		List<GUID> glist = context.getList(GUID.class, new FindDeptGuidListByNoticeKey(event.getId()));
		Set<Employee> elist = new HashSet<Employee>();
		for (GUID g : glist) {
			List<Employee> list = context.getList(Employee.class, new GetChildrenDeptEmployeeListByAuthKey(g, AuthUtils.getAuthArray()));
			elist.addAll(list);
		}
		for (Employee e : elist) {
			if (e.getId().equals(notice.getCreateGuid())) {
				continue;
			}
			SMessageMapping mapping = new SMessageMapping();
			mapping.setEndTime(notice.getCancelTime());
			mapping.setStartTime(notice.getPublishTime());
			mapping.setMessageId(info.getRECID());
			mapping.setMessageType(info.getMessageType());
			mapping.setRECID(context.newRECID());
			// mapping.setTenantId(info.getTenantId());
			mapping.setUserId(e.getId());
			context.handle(new SMessageMappingTask(mapping));
		}
	}

	/**
	 * �����Ķ��¼�
	 */
	public static void handle(Context context, NoticeReadEvent event, ReportQueue rq) {
		context.handle(new SMessageDelTask(SMessageType.UnreadNotices, event.getId(), rq.getUserId()));
	}

	/**
	 * �������յõ��������������
	 * 
	 * @param birthday
	 * @return
	 */
	private static long getBrithday(long birthday) {
		if (0 == birthday) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		cal.setTimeInMillis(birthday);
		cal.set(year, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		return cal.getTime().getTime();
	}

	/**
	 * ����������ޱ仯
	 * 
	 * @param event
	 * @param rq
	 */
	public static void handle(Context context, MaterialsItemThresholdChangeEvent event, ReportQueue rq) {
		context.handle(new SMessageDelTask(SMessageType.GoodsInventory, event.getGoodsItemId()));
		List<Inventory> list = context.getList(Inventory.class, new GetInventoryByStockIdKey(event.getGoodsItemId(),InventoryType.Materials));
		double sumAmount = 0;
		double sumCount = 0;
		Set<Employee> set = SMessageUtils.getEmployeeByAuths(context, rq.getTenantsGuid(), Auth.Boss, Auth.Purchase, Auth.PurchaseManager);
		MaterialsItem item = context.find(MaterialsItem.class, event.getGoodsItemId());
		boolean amounted = false;
		boolean countuped = false;
		boolean countloed = false;
		for (Inventory gi : list) {
			sumAmount += gi.getAmount();
			sumCount += gi.getCount();
			if (item.getMaterial().getInventoryWarningType()== InventoryWarningType.Store_Count && gi.getUpperLimitCount() >= 0
					&& gi.getCount() > gi.getUpperLimitCount()) {
				Store store = context.find(Store.class, gi.getStoreId());
				InventoryTaskHandle.sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory04, set, store
						.getName(), DoubleUtil.sub(gi.getCount(), gi.getUpperLimitCount()));
				countuped = true;
			} else if (item.getMaterial().getInventoryWarningType() == InventoryWarningType.Store_Count && gi.getLowerLimitCount() >= 0
					&& gi.getCount() < gi.getLowerLimitCount()) {
				Store store = context.find(Store.class, gi.getStoreId());
				InventoryTaskHandle.sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory03, set, store
						.getName(), DoubleUtil.sub(gi.getLowerLimitCount(), gi.getCount()));
				countloed = true;
			}
			if (item.getMaterial().getInventoryWarningType() == InventoryWarningType.Store_Amount && gi.getUpperLimitAmount() >= 0
					&& gi.getAmount() > gi.getUpperLimitAmount()) {
				Store store = context.find(Store.class, gi.getStoreId());
				InventoryTaskHandle.sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory02, set, store
						.getName(), DoubleUtil.sub(gi.getAmount(), gi.getUpperLimitAmount()));
				amounted = true;
			}
		}
		if (item.getMaterial().getInventoryWarningType() == InventoryWarningType.ALL_Count && item.getTotalStoreFlore() >= 0
				&& sumCount > item.getTotalStoreUpper() && !countuped) {
			InventoryTaskHandle.sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory07, set, null,
					DoubleUtil.sub(sumCount, item.getTotalStoreUpper()));
		} else if (item.getMaterial().getInventoryWarningType() == InventoryWarningType.ALL_Count && item.getTotalStoreFlore() >= 0
				&& sumCount < item.getTotalStoreFlore() && !countloed) {
			InventoryTaskHandle.sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory06, set, null,
					DoubleUtil.sub(item.getTotalStoreFlore(), sumCount));
		}
		if (item.getMaterial().getInventoryWarningType() == InventoryWarningType.ALL_Amount && item.getTotalStoreAmount() >= 0
				&& sumAmount > item.getTotalStoreAmount() && !amounted) {
			InventoryTaskHandle.sendMessage(context, item, rq.getTenantsGuid(), SMessageTemplateEnum.GoodsInventory05, set, null,
					DoubleUtil.sub(sumAmount, item.getTotalStoreAmount()));
		}
	}

	/**
	 * ��Ʒ���������ޱ仯
	 * 
	 * @param context
	 * @param event
	 * @param rq
	 */
	public static void handle(Context context, GoodsCategoryStoreLimitUpperChangeEvent event, ReportQueue rq) {
		// ɾ��
		// ����
		// List<Inventory> allList = context.getList(Inventory.class, new
		// GetGoodsInventoryByTenantIdKey(rq.getTenantsGuid()));
		// GoodsCategory gc = context.find(GoodsCategory.class,
		// event.getCategoryId());
		// Set<Employee> set = SMessageUtils.getEmployeeByAuths(context,
		// rq.getTenantsGuid(), Auth.Boss, Auth.Purchase, Auth.PurchaseManager);
		// InventoryTaskHandle.sendMessage_Category(context, gc, allList, rq
		// .getTenantsGuid(), set);
	}
}
