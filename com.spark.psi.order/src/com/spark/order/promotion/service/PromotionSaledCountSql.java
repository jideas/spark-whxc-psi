package com.spark.order.promotion.service;

import com.jiuqi.dna.core.Context;
import com.spark.order.promotion.intf.PromotionSaledCountTask;
import com.spark.order.util.dnaSql.DnaSql_update;

/**
 * <p>已销售数量</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-31
 */
class PromotionSaledCountSql extends DnaSql_update{
	private final PromotionSaledCountTask task;
	public PromotionSaledCountSql(Context context, PromotionSaledCountTask task) {
		super(context);
		this.task = task;
	}

	@Override
	protected String getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(SelectPromotionSql.promotion_Table);
		sql.append(" as t ");
		sql.append(" set ");
		sql.append(" saledCount = t.saledCount+@changeCount ");
		this.addParam("@changeCount double", task.getChangeCount());
		sql.append(" where ");
		sql.append(" t.recid = @id ");
		this.addParam("@id guid", task.getId());
		sql.append(" and t.promotionCount >= t.saledCount+@changeCount ");
		return sql.toString();
	}

}
