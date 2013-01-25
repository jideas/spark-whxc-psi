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
 * 基础模块数据提取
 */
public class BaseTaskHandle {

	// 员工离职、复职(员工ID)
	@SuppressWarnings("deprecation")
	protected static void handle(Context context, EmployeeStatusChangeEvent event, ReportQueue rq) {
		/**
		 *该员工生日预警信息
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
			info.setStringValue3((birthday.getMonth() + 1) + "月" + birthday.getDate() + "日");
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

	// 员工部门变化（员工ID）
	@SuppressWarnings("deprecation")
	protected static void handle(Context context, EmployeeDepartmentChangeEvent event, ReportQueue rq) {
		Employee emp = context.find(Employee.class, event.getId());
		if (emp == null) {
			return;
		}
		/**
		 * 更新生日提醒接收人
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
		info.setStringValue3((birthday.getMonth() + 1) + "月" + birthday.getDate() + "日");
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

	// 把employee集合变为guid集合
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

	// 审核配置发生变化（改动范围）
	protected static void handle(Context context, ApprovalConfigChangedEvent event, ReportQueue rq) {
		/**
		 * 更新待审批单据预警信息
		 */
		// 判断是更改的哪种单据的审批配置
		// 删除所有待审核单据提示消息
		// 删除所有已退回单据预警信息
		// 扫描业务表中所有已退回单据，重新生成给本人发送的预警消息
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

	// 审核记录生成时间（审批记录ID）
	protected static void handle(Context context, ApprovalLogEvent event, ReportQueue rq) {
		/**
		 * 删除审批提醒
		 */
		// 得到审批记录实体
		// 判断审批单据类型，查询审批单据实体
		// 删除该单据的审批提醒
		// 判断审批后状态，如还是待审批，转销售订单特殊逻辑
		// 不是待审批：返回
		// !!!!!!!!调拨单和销售订单审核后可能依然需要审核
		// ！！！！！！！！！！！！！！！！
		// ！！！！该方法本版本不使用！！！
		// ！！！！！！！！！！！！！！！！

	}

	// 商品状态变化（商品ID）
	protected static void handle(Context context, GoodsStatusChangeEvent event, ReportQueue rq) {
		/**
		 * 更新商品库存预警信息
		 */
		// 判断是停售还是重新启用
		// 1停售：删除商品库存预警信息
		// 2启用：查询商品库存信息，判断是否预警
		// !!!!!!!!!文档中不存在该情况下更新预警信息，故暂欠奉！！！！！！！！
	}

	// 仓库状态变化（仓库ID）
	protected static void handle(Context context, StoreStatusChangeEvent event, ReportQueue rq) {
		/**
		 * 更新商品库存预警信息
		 */
		// a停用：删除所有关联该仓库的商品预警信息
		// b启用：扫描所有该仓库商品预警信息
		// !!!!!!!!!文档中不存在该情况下更新预警信息，故暂欠奉！！！！！！！！
	}

	// 客户信息改变事件
	protected static void handle(Context context, CustomerDataChangeEvent event, ReportQueue rq) {
		/**
		 * 更改了客户账期设置和预警天数信息
		 */
		// Partner p = context.find(Partner.class, event.getCustomerId());
		// if (null == p) {
		// return;
		// }
		// /**
		// * 报表部分，业务负责人变更
		// */
		// if (PartnerStatus.Official == p.getStatus()) {
		// customerCountData(context, event.getCustomerId(), rq,
		// CustomerLogType.Offical.getCode());
		// } else {
		// customerCountData(context, event.getCustomerId(), rq,
		// CustomerLogType.Create.getCode());
		// }
	}

	// 客户创建事件
	protected static void handle(Context context, CustomerCreateEvent event, ReportQueue rq) {
		// customerCountData(context, event.getCustomerId(), rq,
		// CustomerLogType.Create.getCode());
	}

	// 客户转正事件
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
	 * 公告状态变更事件
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
	 * 公告阅读事件
	 */
	public static void handle(Context context, NoticeReadEvent event, ReportQueue rq) {
		context.handle(new SMessageDelTask(SMessageType.UnreadNotices, event.getId(), rq.getUserId()));
	}

	/**
	 * 根据生日得到今年的生日日期
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
	 * 库存上限下限变化
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
	 * 商品分类金额上限变化
	 * 
	 * @param context
	 * @param event
	 * @param rq
	 */
	public static void handle(Context context, GoodsCategoryStoreLimitUpperChangeEvent event, ReportQueue rq) {
		// 删除
		// 新增
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
