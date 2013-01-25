/**
 * 
 */
package com.spark.psi.report.dao.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.report.constant.ReportConstants.CustomerLogType;
import com.spark.psi.report.dao.task.ReportCustomerCountTask;
import com.spark.psi.report.utils.InsertSqlBuilder;
import com.spark.psi.report.utils.UpdateSqlBuilder;

/**
 *
 */
public class ReportCustomerCountService extends Service{

	/**
	 * @param title
	 */
	protected ReportCustomerCountService(){
		super("ReportCustomerCountService");
	}

	@Publish
	protected class CustomerCountHandle extends SimpleTaskMethodHandler<ReportCustomerCountTask>{
		@Override
		protected void handle(Context context, ReportCustomerCountTask task) throws Throwable{
			if(CustomerLogType.Create.getCode().equals(task.getLogType())){
				createHandle(context, task);
			}
			else{
				officalHandle(context, task);
			}
		}
	}

	private void createHandle(Context context, ReportCustomerCountTask task){
		UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
		ub.setTable("SA_REPORT_CUSTOMER_COUNT");
		ub.addColumn("deptId", ub.guid, task.getDeptId());
		if(null == task.getEmployeeId()){
			ub.addExpression("employeeId = null");
		}
		else{
			ub.addColumn("employeeId", ub.guid, task.getEmployeeId());
		}
		ub.addCondition("tenantId", ub.guid, task.getTenantId(), "t.tenantId=@tenantId");
		ub.addCondition("customerId", ub.guid, task.getCustomerId(), "t.customerId=@customerId");
		int count = ub.execute();
		if(0 == count){
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable("SA_REPORT_CUSTOMER_COUNT");
			ib.addColumn("RECID", ib.guid, context.newRECID());
			ib.addColumn("tenantId", ib.guid, task.getTenantId());
			ib.addColumn("deptId", ib.guid, task.getDeptId());
			ib.addColumn("employeeId", ib.guid, task.getEmployeeId());
			ib.addColumn("customerId", ib.guid, task.getCustomerId());
			ib.addColumn("dateNo", ib.INT, task.getDateNo());
			ib.addColumn("monthNo", ib.INT, task.getMonthNo());
			ib.addColumn("quarter", ib.INT, task.getQuarter());
			ib.addColumn("yearNo", ib.INT, task.getYearNo());
			ib.addColumn("createDate", ib.DATE, task.getCurrDate());
			count = ib.execute();
		}
		task.setCount(count);
	}

	private void officalHandle(Context context, ReportCustomerCountTask task){
		UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
		ub.setTable("SA_REPORT_CUSTOMER_COUNT");
		ub.addColumn("deptId2", ub.guid, task.getDeptId());
		if(null == task.getEmployeeId()){
			ub.addExpression("employeeId2 = null");
		}
		else{
			ub.addColumn("employeeId2", ub.guid, task.getEmployeeId());
		}
		ub.addCondition("tenantId", ub.guid, task.getTenantId(), "t.tenantId=@tenantId");
		ub.addCondition("customerId", ub.guid, task.getCustomerId(), "t.customerId=@customerId");
		ub.addColumn("dateNo2", ub.INT, task.getDateNo());
		ub.addColumn("monthNo2", ub.INT, task.getMonthNo());
		ub.addColumn("quarter2", ub.INT, task.getQuarter());
		ub.addColumn("yearNo2", ub.INT, task.getYearNo());
		ub.addColumn("officalDate", ub.DATE, task.getCurrDate());
		int count = ub.execute();
		task.setCount(count);
	}
}
