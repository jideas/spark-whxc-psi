/**
 * 
 */
package com.spark.psi.report.dao.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.report.dao.task.ReportGoodsPurchaseTask;
import com.spark.psi.report.utils.InsertSqlBuilder;
import com.spark.psi.report.utils.UpdateSqlBuilder;

/**
 * 更新商品采购数据
 */
public class ReportGoodsPurchaseService extends Service{

	/**
	 * @param title
	 */
	protected ReportGoodsPurchaseService(){
		super("ReportGoodsPurchaseService");
	}

	@Publish
	protected class PurchaseHandle extends SimpleTaskMethodHandler<ReportGoodsPurchaseTask>{

		@Override
		protected void handle(Context context, ReportGoodsPurchaseTask task) throws Throwable{
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable("SA_REPORT_GOODS_BUY_DATE");
			ub.addExpression("ordAmount", ub.DOUBLE, task.getOrdAmount(), "ordAmount=t.ordAmount+@ordAmount");
			ub.addExpression("ordCount", ub.DOUBLE, task.getOrdCount(), "ordCount=t.ordCount+@ordCount");
			ub.addExpression("outstoAmount", ub.DOUBLE, task.getOutstoAmount(),
			        "outstoAmount=t.outstoAmount+@outstoAmount");
			ub.addExpression("receiptAmount", ub.DOUBLE, task.getReceiptAmount(),
			        "receiptAmount=t.receiptAmount+@receiptAmount");
			ub.addExpression("rtnAmount", ub.DOUBLE, task.getRtnAmount(), "rtnAmount=t.rtnAmount+@rtnAmount");
			ub.addCondition("comNo", ub.guid, task.getTenantId(), "t.tenantId=@comNo");
			ub.addCondition("goodsId", ub.guid, task.getGoodsItemId(), "t.goodsItemId=@goodsId");
			ub.addCondition("dateNo", ub.INT, task.getDateNo(), "t.dateNo=@dateNo");
			ub.addCondition("deptGuid", ub.guid, task.getDeptGuid(), "t.deptGuid=@deptGuid");
			ub.addCondition("orderPerson", ub.guid, task.getOrderPerson(), "t.orderPerson=@orderPerson");
			int count = ub.execute();
			if(0==count){
				InsertSqlBuilder ib = new InsertSqlBuilder(context);
				ib.setTable("SA_REPORT_GOODS_BUY_DATE");
				ib.addColumn("RECID", ib.guid, context.newRECID());
				ib.addColumn("tenantId", ib.guid, task.getTenantId());
				ib.addColumn("goodsItemId", ib.guid, task.getGoodsItemId());
				ib.addColumn("dateNo", ib.INT, task.getDateNo());
				ib.addColumn("monthNo", ib.INT, task.getMonthNo());
				ib.addColumn("quarter", ib.INT, task.getQuarter());
				ib.addColumn("yearNo", ib.INT, task.getYearNo());
				ib.addColumn("ordAmount", ib.DOUBLE, task.getOrdAmount());
				ib.addColumn("ordCount", ib.DOUBLE, task.getOrdCount());
				ib.addColumn("outstoAmount", ib.DOUBLE, task.getOutstoAmount());
				ib.addColumn("receiptAmount", ib.DOUBLE, task.getReceiptAmount());
				ib.addColumn("rtnAmount", ib.DOUBLE, task.getRtnAmount());
				ib.addColumn("deptGuid", ib.guid, task.getDeptGuid());
				ib.addColumn("orderPerson", ib.guid, task.getOrderPerson());
				ib.addColumn("createDate", ib.DATE, task.getCreateDate());
				count = ib.execute();
			}
			task.setCount(count);
		}
	}
}
