/**
 * 
 */
package com.spark.psi.report.service.provider;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.base.Employee;
import com.spark.psi.base.Login;
import com.spark.psi.publish.MonitorDataType;
import com.spark.psi.publish.report.entity.SalesTargetItem;
import com.spark.psi.report.entity.MonitorTargetEntity;
import com.spark.psi.report.entity.SalesMonitorDailyEntity;
import com.spark.psi.report.key.MonitorTargetKey;
import com.spark.psi.report.key.SalesMonitorDailyKey;
import com.spark.psi.report.utils.AuthUtils;
import com.spark.psi.report.utils.QuerySqlBuilder;
import com.spark.psi.report.utils.ReportDateUtils;

/**
 *
 */
public class SpecialProvider extends Service{

	/**
	 * @param title
	 */
	protected SpecialProvider(){
		super("SpecialProvider");
	}

	@Publish
	protected class MonitorTargetProvider extends OneKeyResultListProvider<MonitorTargetEntity, MonitorTargetKey>{

		@Override
		protected void provide(Context context, MonitorTargetKey key, List<MonitorTargetEntity> list) throws Throwable{
			Date today = new Date();
			int monthNo = ReportDateUtils.getMonthNo(today);
			int yearNo = ReportDateUtils.getYearNo(today);
			List<SalesTargetItem> slist =
			        context.getList(SalesTargetItem.class, new GUID[] {key.getId()}, MonitorDataType.SalesAmount
			                .getCode(), yearNo + "");
			List<SalesTargetItem> rlist =
			        context.getList(SalesTargetItem.class, new GUID[] {key.getId()}, MonitorDataType.ReceiptAmount
			                .getCode(), yearNo + "");
			SalesTargetItem st = null;
			SalesTargetItem rt = null;
			if(CheckIsNull.isNotEmpty(rlist)){
				rt = rlist.get(0);
			}
			if(CheckIsNull.isNotEmpty(slist)){
				st = slist.get(0);
			}
			if(key.isThisYear()){
				for(int i = 1; i < 13; i++){
					doit(key, i, list, st, rt, yearNo);
				}
				return;
			}
			int toMonth = monthNo % 100;
			if(key.getMaxSize() <= toMonth && key.getMaxSize() > 0){
				for(int i = (toMonth - key.getMaxSize() + 1); i <= (toMonth); i++){
					doit(key, i, list, st, rt, yearNo);
				}
			}
			else{
				int preYearNo = ReportDateUtils.getLastYearNo(yearNo);
				List<SalesTargetItem> preslist =
				        context.getList(SalesTargetItem.class, new GUID[] {key.getId()}, MonitorDataType.SalesAmount
				                .getCode(), preYearNo + "");
				List<SalesTargetItem> prerlist =
				        context.getList(SalesTargetItem.class, new GUID[] {key.getId()}, MonitorDataType.ReceiptAmount
				                .getCode(), preYearNo + "");
				SalesTargetItem prest = null;
				SalesTargetItem prert = null;
				if(CheckIsNull.isNotEmpty(rlist)){
					prert = prerlist.get(0);
				}
				if(CheckIsNull.isNotEmpty(slist)){
					prest = preslist.get(0);
				}
				for(int i = 12 - (key.getMaxSize() - toMonth + 1); i <= 12; i++){
					doit(key, i, list, prest, prert, preYearNo);
				}
				for(int i = 1; i <= toMonth; i++){
					doit(key, i, list, st, rt, yearNo);
				}
			}
		}

		protected void doit(MonitorTargetKey key, int i, List<MonitorTargetEntity> list, SalesTargetItem st,
		        SalesTargetItem rt, int yearNo) throws SecurityException, NoSuchFieldException, NumberFormatException,
		        IllegalArgumentException, IllegalAccessException
		{
			MonitorTargetEntity rr = new MonitorTargetEntity();
			rr.setObjId(key.getId());
			String str = "";
			if(i < 10){
				str = "0" + (i);
			}
			else{
				str = "" + (i);
			}
			rr.setDateNo(Integer.parseInt(yearNo + str));
			Field f = SalesTargetItem.class.getDeclaredField("value" + str);
			f.setAccessible(true);
			if(null != st){
				rr.setSalesAmount(Double.parseDouble("" + f.get(st)));
			}
			if(null != rt){
				rr.setReceiptAmount(Double.parseDouble("" + f.get(rt)));
			}
			f.setAccessible(false);
			list.add(rr);
		}
	}

	@Publish
	protected class SalesMonitorDailyProvider extends
	        OneKeyResultListProvider<SalesMonitorDailyEntity, SalesMonitorDailyKey>
	{

		@Override
		protected void provide(Context context, SalesMonitorDailyKey key, List<SalesMonitorDailyEntity> resultList)
		        throws Throwable
		{
			Login login = context.find(Login.class);
			QuerySqlBuilder qb = new QuerySqlBuilder(context);
			qb.addArgs("tenant", qb.guid, login.getTenantId());
			qb.addEquals("t.tenantId", "@tenant");

			qb.addColumn("t.dateNo", "dateNo");
			qb.addColumn("sum(t.ordAmount)", "ordAmount");
			qb.addColumn("sum(t.outstoAmount)", "outstoAmount");
			qb.addColumn("sum(t.receiptAmount)", "receiptAmount");
			Employee user = context.find(Employee.class, login.getEmployeeId());
			if(AuthUtils.isBoss(context, login.getEmployeeId())){
				qb.addTable("SA_REPORT_DEPT_SALES_DATE", "t");
				if(null != key.getDeptId()){
					qb.addArgs("depId", qb.guid, key.getDeptId());
					qb.addEquals("t.deptGuid", "@depId");
				}
				else{
					qb.addArgs("depId", qb.guid, user.getDepartmentId());
					qb.addEquals("t.deptGuid", "@depId");
				}
			}
			else if(AuthUtils.isSalesManager(context, login.getEmployeeId())){
				qb.addTable("SA_REPORT_DEPT_SALES_DATE", "t");
				if(null != key.getDeptId()){
					qb.addArgs("depId", qb.guid, key.getDeptId());
					qb.addEquals("t.deptGuid", "@depId");
				}
				else{
					qb.addArgs("depId", qb.guid, user.getDepartmentId());
					qb.addEquals("t.deptGuid", "@depId");
				}
			}
			else if(AuthUtils.isSales(context, login.getEmployeeId())){
				qb.addTable("SA_REPORT_SALES_DATE", "t");
				qb.addArgs("userId", qb.guid, user.getId());
				qb.addEquals("t.orderPerson", "@userId");
			}

			qb.addGroupBy("t.dateNo,t.monthNo,t.yearNo");
			qb.addOrderBy("t.createDate desc");
			RecordSet rs = qb.getRecordLimit(0, 3);
			while(rs.next()){
				SalesMonitorDailyEntity entity = new SalesMonitorDailyEntity();
				entity.setDay(rs.getFields().get(0).getInt());
				entity.setSalesAmount(rs.getFields().get(1).getDouble());
				entity.setCheckoutAmount(rs.getFields().get(2).getDouble());
				entity.setReceiptAmount(rs.getFields().get(3).getDouble());
				resultList.add(entity);
			}
		}

	}
}
