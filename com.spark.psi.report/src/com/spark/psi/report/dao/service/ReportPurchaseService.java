/**
 * 
 */
package com.spark.psi.report.dao.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.report.dao.command.Insert_Purchase;
import com.spark.psi.report.dao.command.Update_Purchase;
import com.spark.psi.report.dao.task.ReportPurchaseTask;

/**
 * 更新采购及供应商数据
 */
public class ReportPurchaseService extends Service {

	/**
	 * @param title
	 */
	protected ReportPurchaseService(Insert_Purchase insert, Update_Purchase update) {
		super("ReportPurchaseService");
		this.insert = insert;
		this.update = update;
	}

	private Insert_Purchase insert;
	private Update_Purchase update;

	@Publish
	protected class PurchaseHandle extends
			SimpleTaskMethodHandler<ReportPurchaseTask> {

		@Override
		protected void handle(Context context, ReportPurchaseTask task)
				throws Throwable {
			DBCommand db = context.prepareStatement(update);
			int index = 0;
			db.setArgumentValue(index++, task.getTenantId());
			db.setArgumentValue(index++, task.getCusProGuid());
			db.setArgumentValue(index++, task.getDateNo()); 
			db.setArgumentValue(index++, task.getOrdAmount());
			db.setArgumentValue(index++, task.getOutstoAmount());
			db.setArgumentValue(index++, task.getReceiptAmount());
			db.setArgumentValue(index++, task.getDeductionAmount());
			db.setArgumentValue(index++, task.getRtnAmount());
			int count = 0;
			try{
				count = db.executeUpdate();
			}finally{
				db.unuse();
			}
			if (0 == count) {
				index = 0;
				DBCommand db1 = context.prepareStatement(insert);
				db1.setArgumentValue(index++,context.newRECID());
				db1.setArgumentValue(index++, task.getTenantId());
				db1.setArgumentValue(index++, task.getCusProGuid());
				db1.setArgumentValue(index++, task.getDateNo());
				db1.setArgumentValue(index++, task.getMonthNo());
				db1.setArgumentValue(index++, task.getQuarter());
				db1.setArgumentValue(index++, task.getYearNo());
				db1.setArgumentValue(index++, task.getOrdAmount());
				db1.setArgumentValue(index++, task.getOutstoAmount());
				db1.setArgumentValue(index++, task.getReceiptAmount());
				db1.setArgumentValue(index++, task.getDeductionAmount());
				db1.setArgumentValue(index++, task.getRtnAmount()); 
				db1.setArgumentValue(index++, task.getCreateDate());
				try{
					count= db1.executeUpdate();
				}finally{
					db1.unuse();
				}
			}
			task.setCount(count);
		}
	}
}
