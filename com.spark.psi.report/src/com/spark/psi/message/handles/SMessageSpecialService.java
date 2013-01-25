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
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Partner;
import com.spark.psi.base.key.OverPeriodAmountKey;
import com.spark.psi.base.key.RemindingAmountKey;
import com.spark.psi.message.entity.SMessageInfo;
import com.spark.psi.message.entity.SMessageMapping;
import com.spark.psi.message.task.CreditDayDailyTask;
import com.spark.psi.message.task.SMessageDelTask;
import com.spark.psi.message.task.SMessageInfoTask;
import com.spark.psi.message.task.SMessageMappingTask;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.PartnerType;
import com.spark.psi.publish.SMessageTemplateEnum;
import com.spark.psi.publish.SMessageType;
import com.spark.psi.report.utils.SMessageUtils;

/**
 *
 */
public class SMessageSpecialService extends Service{

	/**
	 * @param title
	 */
	protected SMessageSpecialService(){
		super("com.spark.psi.message.handles.SMessageSpecialService");
	}

	//更新所有客户的所有账期预警信息
	@Publish
	protected class CreditDayUpdateDaily extends SimpleTaskMethodHandler<CreditDayDailyTask>{

		@Override
		protected void handle(Context context, CreditDayDailyTask task) throws Throwable{
			List<Partner> list = context.getList(Partner.class);
			long startTime = new Date().getTime();
//			for(Partner p : list){
//				if(p.getPartnerType() == PartnerType.Supplier){
//					continue;
//				}
//				Employee emp = p.getBusinessPerson();
//
//				Set<Employee> glist = SMessageUtils.getMyManager(context, emp, true, Auth.Boss, Auth.SalesManager);
//				context.handle(new SMessageDelTask(SMessageType.CreditDay, p.getId()));
//				RemindingAmountKey key1 = new RemindingAmountKey();
//				key1.setPartnerId(p.getId());
//				key1.setAccountPeriod(p.getAccountPeriod());
//				key1.setRemindingDay(p.getAccountRemind());
//				Double remaindValue = context.find(Double.class, key1);
//				OverPeriodAmountKey key2 = new OverPeriodAmountKey();
//				key2.setPartnerId(p.getId());
//				key2.setAccountPeriod(p.getAccountPeriod());
//				key2.setQueryAll(true);
//				Double overValue = context.find(Double.class, key2);
//				Set<Employee> alllist1 =
//				        SMessageUtils.getEmployeeByAuths(context, null, Auth.Boss, Auth.Account,
//				                Auth.AccountManager);
//				alllist1.addAll(glist);
//				Set<GUID> alllist = new HashSet<GUID>();
//				for(Employee ee : alllist1){
//					alllist.add(ee.getId());
//				}
//				if(remaindValue > 0){
//					SMessageInfo info = new SMessageInfo();
//					info.setRECID(context.newRECID());
//					info.setRelaObjId(p.getId());
//					info.setMessageType(SMessageType.CreditDay.getCode());
//					info.setTenantId(emp.getTenantId());
//					info.setStringValue1(p.getShortName());
//					info.setStringValue2(DoubleUtil.getRoundStr(remaindValue));
//					info.setTemplateCode(SMessageTemplateEnum.CreditDay01.getCode());
//					context.handle(new SMessageInfoTask(info));
//					for(GUID ee : alllist){
//						SMessageMapping mapping = new SMessageMapping();
//						mapping.setRECID(context.newRECID());
//						mapping.setMessageId(info.getRECID());
//						mapping.setMessageType(info.getMessageType());
//						mapping.setTenantId(info.getTenantId());
//						mapping.setStartTime(startTime);
//						mapping.setUserId(ee);
//						context.handle(new SMessageMappingTask(mapping));
//					}
//				}
//				if(overValue > 0){
//					SMessageInfo info = new SMessageInfo();
//					info.setRECID(context.newRECID());
//					info.setRelaObjId(p.getId());
//					info.setMessageType(SMessageType.CreditDay.getCode());
//					info.setTenantId(emp.getTenantId());
//					info.setStringValue1(p.getShortName());
//					info.setStringValue2(DoubleUtil.getRoundStr(overValue));
//					info.setTemplateCode(SMessageTemplateEnum.CreditDay02.getCode());
//					context.handle(new SMessageInfoTask(info));
//					for(GUID ee : alllist){
//						SMessageMapping mapping = new SMessageMapping();
//						mapping.setRECID(context.newRECID());
//						mapping.setMessageId(info.getRECID());
//						mapping.setMessageType(info.getMessageType());
//						mapping.setTenantId(info.getTenantId());
//						mapping.setStartTime(startTime);
//						mapping.setUserId(ee);
//						context.handle(new SMessageMappingTask(mapping));
//					}
//				}
//			}

		}

	}
}
