/**
 * 
 */
package com.spark.psi.report.service.provider;

import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.psi.report.constant.ConditionEnum;
import com.spark.psi.report.constant.TargetEnum.InventoryBookEnum;
import com.spark.psi.report.entity.ReportResult;
import com.spark.psi.report.key.ReportCommonKey;
import com.spark.psi.report.utils.Condition;
import com.spark.psi.report.utils.ConditionUtil;
import com.spark.psi.report.utils.QuerySqlBuilder;
import com.spark.psi.report.utils.TargetColumnMapping;

/**
 *
 */
public abstract class InventorySubjectProvider{
	/**
	 * 库存报表数据查询
	 * 
	 * @param context
	 * @param key
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	protected static void provide(Context context, ReportCommonKey key, List<ReportResult> list){
		QuerySqlBuilder qb = new QuerySqlBuilder(context);
		qb.addTable("sa_report_sto_stdbook", "t");
		qb.addArgs("tenantId", qb.guid, GUID.valueOf(context.getLogin().getUser().getDescription()));
		qb.addEquals("t.tenantId", "@tenantId");
		Map<InventoryBookEnum, String> InventoryBookColumnMap = TargetColumnMapping.createInventoryBookColumnMap();
		// 只需要开始结束时间 不需要按时间单位 分段
		for(int index = 0; index < key.getTargets().size(); index++){
			Enum ib = key.getTargets().get(index);
			if(ib.equals(InventoryBookEnum.GoodsItemNumber) || ib.equals(InventoryBookEnum.GoodsItemName)
			        || ib.equals(InventoryBookEnum.GoodsProperties) || ib.equals(InventoryBookEnum.GoodsUnit))
			{
				qb.addGroupBy("t." + InventoryBookColumnMap.get(ib));
			}
			qb.addColumn("t." + InventoryBookColumnMap.get(ib), InventoryBookColumnMap.get(ib));
		}
		qb.addArgs("beginDate", qb.DATE, key.getBeginDate());
		qb.addArgs("endDate", qb.DATE, key.getEndDate());
		qb.addBetween("t.currDate", "@beginDate", "@endDate");
		if(CheckIsNull.isNotEmpty(key.getConditions())){
			for(Condition con : key.getConditions()){
				if(con.getConditionColumn().equals(ConditionEnum.InventoryBookConditionEnum.StoreId)){
					ConditionUtil.fillConditionToSql(qb, con, "t.storeGuid");
				}
				else if(con.getConditionColumn().equals(ConditionEnum.InventoryBookConditionEnum.GoodsSearch)){
					qb.addArgs("goodsSearch", qb.STRING, con.getValue());
					qb
					        .addCondition(" (t.goodsNo like '%'+@goodsSearch+'%'   or t.goodsName like '%'+@goodsSearch+'%'  or t.goodsAttr like '%'+@goodsSearch+'%')");
				}
			}
		}

		RecordSet rs = qb.getRecord();
		while(rs.next()){
			ReportResult rr = new ReportResult();
			for(int index = 0; index < key.getTargets().size(); index++){
				Enum ib = key.getTargets().get(index);
				rr.setTargetValue(ib, rs.getFields().get(index).getObject());
			}
			list.add(rr);
		}
	}
}
