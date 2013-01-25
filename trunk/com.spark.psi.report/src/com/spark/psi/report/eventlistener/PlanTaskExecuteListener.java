/**
 * 
 */
package com.spark.psi.report.eventlistener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.common.utils.date.DateUtil;
import com.spark.psi.base.Department;
import com.spark.psi.base.Employee;
import com.spark.psi.base.event.TimerEvent;
import com.spark.psi.message.entity.SMessageInfo;
import com.spark.psi.message.entity.SMessageMapping;
import com.spark.psi.message.task.CreditDayDailyTask;
import com.spark.psi.message.task.SMessageDelTask;
import com.spark.psi.message.task.SMessageInfoTask;
import com.spark.psi.message.task.SMessageMappingTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.SMessageTemplateEnum;
import com.spark.psi.publish.SMessageType;
import com.spark.psi.report.utils.SMessageUtils;

/**
 *
 */
public class PlanTaskExecuteListener extends Service {

	public PlanTaskExecuteListener() {
		super("PlanTaskExecuteListener");
	}

	@SuppressWarnings("unused")
	private final static int PAGE_SIZE = 500; // 每次分析员工数

	/**
	 * 每天将结束时的事件
	 * 
	 */
	@Publish
	class PP extends EventListener<TimerEvent> {

		@Override
		protected void occur(Context context, TimerEvent event) throws Throwable {
			if (event.isEndOfToday()) {
				Date date = new Date();
				long l1 = DateUtil.trunc(date.getTime(), Calendar.DATE) + 24 * 3600 * 1000;
				if (l1 - date.getTime() > 12 * 3600 * 1000) {
					context.handle(new CreditDayDailyTask());
					analyzeDay(context);
				} else {
					try {
						Thread.sleep(l1 - date.getTime() + 10000);
						context.handle(new CreditDayDailyTask());
						analyzeDay(context);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void analyzeDay(Context context) throws Exception {
		Date today = new Date();
		long n = DateUtil.truncDay(today.getTime());
		List<Employee> list = context.getList(Employee.class, n - 6 * 24 * 3600000, n + 24 * 3600000);
		for (Employee emp : list) {
			context.handle(new SMessageDelTask(SMessageType.NearBirthday, emp.getId()));
			Date birthday = new Date(emp.getBirthday());
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
			Set<GUID> set2 = getGuidSet(SMessageUtils.getEmployeeByAuths(context, emp.getTenantId(), Auth.Assistant));
			Set<GUID> set3 = getGuidSet(SMessageUtils.getEmployeeByAuths(context, emp.getTenantId(), Auth.Boss));
			set2.removeAll(set3);
			set2.remove(emp.getId());
			if (CheckIsNull.isNotEmpty(set2) && set2.size() > 0) {
				set.addAll(set2);
				set.removeAll(set3);
			}
			for (GUID e : set) {
				SMessageMapping mapping = new SMessageMapping();
				mapping.setEndTime(DateUtil.truncDay(today.getTime()) + 24 * 3600000);
				mapping.setMessageId(info.getRECID());
				mapping.setMessageType(SMessageType.NearBirthday.getCode());
				mapping.setRECID(context.newRECID());
				mapping.setStartTime(new Date().getTime());
				mapping.setUserId(e);
				context.handle(new SMessageMappingTask(mapping));
			}
		}
	}

	public Set<GUID> getGuidSet(Set<Employee> set1) {
		Set<GUID> set = new HashSet<GUID>();
		if (null == set1) {
			return set;
		}
		for (Employee e : set1) {
			set.add(e.getId());
		}
		return set;
	}

	/**
	 * 根据出生日期得到今年的生日日期
	 * 
	 * @param birthday
	 * @return
	 */
	@SuppressWarnings("unused")
	private long getBrithday(long birthday) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		cal.setTimeInMillis(birthday);
		cal.set(year, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		return cal.getTime().getTime();
	}
}
