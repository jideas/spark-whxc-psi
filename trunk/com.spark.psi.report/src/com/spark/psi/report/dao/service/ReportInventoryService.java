/**
 * 
 */
package com.spark.psi.report.dao.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.base.Inventory;
import com.spark.psi.report.dao.task.ReportInventoryTask;
import com.spark.psi.report.utils.InsertSqlBuilder;
import com.spark.psi.report.utils.UpdateSqlBuilder;

/**
 *
 */
public class ReportInventoryService extends Service {

	/**
	 * @param title
	 */
	protected ReportInventoryService() {
		super("ReportInventoryService");
	}

	@Publish
	protected class InventoryHandle extends SimpleTaskMethodHandler<ReportInventoryTask> {

		@Override
		protected void handle(Context context, ReportInventoryTask task) throws Throwable {
			UpdateSqlBuilder ub = new UpdateSqlBuilder(context);
			ub.setTable("sa_report_sto_stdbook");
			ub.addExpression("instoCount", ub.DOUBLE, task.getInstoCount(), "instoCount = t.instoCount +@instoCount");
			ub.addExpression("instoAmount", ub.DOUBLE, task.getInstoAmount(),
					"instoAmount = t.instoAmount +@instoAmount");
			ub.addExpression("outstoCount", ub.DOUBLE, task.getOutstoCount(),
					"outstoCount = t.outstoCount +@outstoCount");
			ub.addExpression("outstoAmount", ub.DOUBLE, task.getOutstoAmount(),
					"outstoAmount = t.outstoAmount +@outstoAmount");
			ub.addExpression("endStoreCount = t.endStoreCount +@instoCount-@outstoCount");
			ub.addExpression("endStoreMoney = t.endStoreMoney +@instoAmount-@outstoAmount");
			ub.addCondition("storeGuid", ub.guid, task.getStoreGuid(), "t.storeGuid=@storeGuid");
			ub.addCondition("goodsGuid", ub.guid, task.getGoodsGuid(), "t.goodsGuid=@goodsGuid");
			ub.addCondition("dateNo", ub.INT, task.getDateNo(), "t.dateNo=@dateNo");
			int count = ub.execute();
			if (1 == count) {
				task.setCount(count);
				return;
			}
			InsertSqlBuilder ib = new InsertSqlBuilder(context);
			ib.setTable("sa_report_sto_stdbook");
			ib.addColumn("RECID", ib.guid, context.newRECID());
			ib.addColumn("storeGuid", ib.guid, task.getStoreGuid());
			ib.addColumn("goodsGuid", ib.guid, task.getGoodsGuid());
			ResourceToken<Inventory> token = null;
			if (task.getStoreGuid() != null) {
				token = context.findResourceToken(Inventory.class, task.getStoreGuid(), task.getGoodsGuid());
			}
			if (null == token) {
				double count0 = task.getOutstoCount() - task.getInstoCount();
				count0 = count0 < 0 ? 0 : count0;
				double amount0 = task.getOutstoAmount() - task.getInstoAmount();
				amount0 = amount0 < 0 ? 0 : amount0;
				ib.addColumn("beginStoreCount", ib.DOUBLE, count0);
				ib.addColumn("beginStoreMoney", ib.DOUBLE, amount0);
				ib.addColumn("endStoreCount", ib.DOUBLE, count0 + task.getInstoCount() - task.getOutstoCount());
				ib.addColumn("endStoreMoney", ib.DOUBLE, amount0 + task.getInstoAmount() - task.getOutstoAmount());
			} else {
				double count0 = token.getFacade().getCount() == -1 ? 0 : token.getFacade().getCount()
						+ task.getOutstoCount() - task.getInstoCount();
				count0 = count0 < 0 ? 0 : count0;
				double amount0 = token.getFacade().getAmount() == -1 ? 0 : token.getFacade().getAmount()
						+ task.getOutstoAmount() - task.getInstoAmount();
				amount0 = amount0 < 0 ? 0 : amount0;
				ib.addColumn("beginStoreCount", ib.DOUBLE, count0);
				ib.addColumn("beginStoreMoney", ib.DOUBLE, amount0);
				ib.addColumn("endStoreCount", ib.DOUBLE, count0 + task.getInstoCount() - task.getOutstoCount());
				ib.addColumn("endStoreMoney", ib.DOUBLE, token.getFacade().getAmount() == -1 ? 0 : token.getFacade()
						.getAmount());
			}
			ib.addColumn("instoCount", ib.DOUBLE, task.getInstoCount());
			ib.addColumn("instoAmount", ib.DOUBLE, task.getInstoAmount());
			ib.addColumn("outstoCount", ib.DOUBLE, task.getOutstoCount());
			ib.addColumn("outstoAmount", ib.DOUBLE, task.getOutstoAmount());
			ib.addColumn("createdDate", ib.DATE, task.getCreatedDate());
			ib.addColumn("goodsName", ib.STRING, task.getGoodsName());
			ib.addColumn("goodsAttr", ib.STRING, task.getGoodsAttr());
			ib.addColumn("goodsUnit", ib.STRING, task.getGoodsUnit());
			ib.addColumn("goodsTypeGuid", ib.guid, task.getGoodsTypeGuid());
			ib.addColumn("goodsNo", ib.STRING, task.getGoodsNo());
			ib.addColumn("goodsScale", ib.INT, task.getGoodsScale());
			ib.addColumn("dateNo", ib.INT, task.getDateNo());
			ib.addColumn("monthNo", ib.INT, task.getMonthNo());
			ib.addColumn("quarter", ib.INT, task.getQuarter());
			ib.addColumn("yearNo", ib.INT, task.getYearNo());
			ib.addColumn("inventoryType", ib.STRING, task.getInventoryType());
			count = ib.execute();
			task.setCount(count);
		}
	}
}
