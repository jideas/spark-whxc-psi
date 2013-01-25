/**
 * 
 */
package com.spark.psi.report.dao.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.Department;
import com.spark.psi.report.dao.task.ReportSalesTask;
import com.spark.psi.report.utils.InsertSqlBuilder;
import com.spark.psi.report.utils.UpdateSqlBuilder;

/**
 * 更新销售及客户数据
 */
public class ReportSalesService extends Service{

	/**
	 * @param title
	 */
	protected ReportSalesService(){
		super("ReportSalesService");
	}

	@Publish
	protected class SalesHandle extends SimpleTaskMethodHandler<ReportSalesTask>{

		@Override
		protected void handle(Context context, ReportSalesTask task) throws Throwable{
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable("SA_REPORT_SALES_DATE");
			ub.addExpression("ordAmount", ub.DOUBLE, task.getOrdAmount(), "ordAmount=t.ordAmount+@ordAmount");
			ub.addExpression("outstoAmount", ub.DOUBLE, task.getOutstoAmount(),
			        "outstoAmount=t.outstoAmount+@outstoAmount");
			ub.addExpression("receiptAmount", ub.DOUBLE, task.getReceiptAmount(),
			        "receiptAmount=t.receiptAmount+@receiptAmount");
			ub.addExpression("deductionAmount", ub.DOUBLE, task.getDeductionAmount(),
			        "deductionAmount=t.deductionAmount+@deductionAmount");
			ub.addExpression("rtnAmount", ub.DOUBLE, task.getRtnAmount(), "rtnAmount=t.rtnAmount+@rtnAmount");
			ub.addCondition("tenantId", ub.guid, task.getTenantId(), "t.tenantId=@tenantId");
			ub.addCondition("deptId", ub.guid, task.getDeptGuid(), "t.deptGuid=@deptId");
			ub.addCondition("dateNo", ub.INT, task.getDateNo(), "t.dateNo=@dateNo");
			ub.addCondition("orderPerson", ub.guid, task.getOrderPerson(), "t.orderPerson=@orderPerson");
			ub.addCondition("cusProGuid", ub.guid, task.getCusProGuid(), "t.cusProGuid=@cusProGuid");
			int count = ub.execute();
			if(0 == count){
				InsertSqlBuilder ib = new InsertSqlBuilder(context);
				ib.setTable("SA_REPORT_SALES_DATE");
				ib.addColumn("RECID", ib.guid, context.newRECID());
				ib.addColumn("tenantId", ib.guid, task.getTenantId());
				ib.addColumn("cusProGuid", ib.guid, task.getCusProGuid());
				ib.addColumn("dateNo", ib.INT, task.getDateNo());
				ib.addColumn("monthNo", ib.INT, task.getMonthNo());
				ib.addColumn("quarter", ib.INT, task.getQuarter());
				ib.addColumn("yearNo", ib.INT, task.getYearNo());
				ib.addColumn("ordAmount", ib.DOUBLE, task.getOrdAmount());
				ib.addColumn("outstoAmount", ib.DOUBLE, task.getOutstoAmount());
				ib.addColumn("receiptAmount", ib.DOUBLE, task.getReceiptAmount());
				ib.addColumn("deductionAmount", ib.DOUBLE, task.getDeductionAmount());
				ib.addColumn("rtnAmount", ib.DOUBLE, task.getRtnAmount());
				ib.addColumn("deptGuid", ib.guid, task.getDeptGuid());
				ib.addColumn("orderPerson", ib.guid, task.getOrderPerson());
				ib.addColumn("createDate", ib.DATE, task.getCreateDate());
				count = ib.execute();
			}
			deptDataHandle(context, task);
		}
	}

	/**
	 * @param task
	 */
	private void deptDataHandle(Context context, ReportSalesTask task){
		List<GUID> glist = new ArrayList<GUID>();
		if(null == task.getDeptGuid()){
			task.setDeptGuid(task.getTenantId());
		}
		Department dep = context.find(Department.class, task.getDeptGuid());
		glist.add(dep.getId());
		for(Department d : dep.getAncestors(context)){
			glist.add(d.getId());
		}
		for(GUID deptId : glist){
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable("SA_REPORT_DEPT_SALES_DATE");
			ub.addExpression("ordAmount", ub.DOUBLE, task.getOrdAmount(), "ordAmount=t.ordAmount+@ordAmount");
			ub.addExpression("outstoAmount", ub.DOUBLE, task.getOutstoAmount(),
			        "outstoAmount=t.outstoAmount+@outstoAmount");
			ub.addExpression("receiptAmount", ub.DOUBLE, task.getReceiptAmount(),
			        "receiptAmount=t.receiptAmount+@receiptAmount");
			ub.addExpression("deductionAmount", ub.DOUBLE, task.getDeductionAmount(),
			        "deductionAmount=t.deductionAmount+@deductionAmount");
			ub.addExpression("rtnAmount", ub.DOUBLE, task.getRtnAmount(), "rtnAmount=t.rtnAmount+@rtnAmount");
			ub.addCondition("tenantId", ub.guid, task.getTenantId(), "t.tenantId=@tenantId");
			ub.addCondition("deptId", ub.guid, deptId, "t.deptGuid=@deptId");
			ub.addCondition("dateNo", ub.INT, task.getDateNo(), "t.dateNo=@dateNo");
			int count = ub.execute();
			if(0 == count){
				InsertSqlBuilder ib = new InsertSqlBuilder(context);
				ib.setTable("SA_REPORT_DEPT_SALES_DATE");
				ib.addColumn("RECID", ib.guid, context.newRECID());
				ib.addColumn("tenantId", ib.guid, task.getTenantId());
				ib.addColumn("dateNo", ib.INT, task.getDateNo());
				ib.addColumn("monthNo", ib.INT, task.getMonthNo());
				ib.addColumn("quarter", ib.INT, task.getQuarter());
				ib.addColumn("yearNo", ib.INT, task.getYearNo());
				ib.addColumn("ordAmount", ib.DOUBLE, task.getOrdAmount());
				ib.addColumn("outstoAmount", ib.DOUBLE, task.getOutstoAmount());
				ib.addColumn("receiptAmount", ib.DOUBLE, task.getReceiptAmount());
				ib.addColumn("deductionAmount", ib.DOUBLE, task.getDeductionAmount());
				ib.addColumn("rtnAmount", ib.DOUBLE, task.getRtnAmount());
				ib.addColumn("deptGuid", ib.guid, deptId);
				ib.addColumn("createDate", ib.DATE, task.getCreateDate());
				count = ib.execute();
			}

		}

	}
}
