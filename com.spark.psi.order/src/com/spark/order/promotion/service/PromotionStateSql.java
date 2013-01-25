package com.spark.order.promotion.service;

import com.jiuqi.dna.core.Context;
import com.spark.common.utils.character.CheckIsNull;
import com.spark.order.promotion.intf.PromotionStatus2;
import com.spark.order.util.dnaSql.DnaSql_update;

/**
 * <p>促销状态变化</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-31
 */
class PromotionStatusSql extends DnaSql_update{
	private final PromotionStatusTask task;
	
	public PromotionStatusSql(Context context, PromotionStatusTask task) {
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
		if(CheckIsNull.isNotEmpty(task.getCause())){
			if(PromotionStatus2.Suspended == task.getNewstatus() || PromotionStatus2.Return == task.getNewstatus()){
				sql.append(" returnCause = @cause, ");
				this.addParam("@cause string", task.getCause());
			}
			else{
				sql.append(" promotionCause = @cause, ");
				this.addParam("@cause string", task.getCause());
			}
		}
		sql.append(" state = @newstatus ");
		this.addParam("@newstatus string", task.getNewstatus().getCode());
		sql.append(" where ");
		sql.append(" t.recid = @id and ");
		this.addParam("@id guid", task.getId());
		sql.append(" t.state = @oldstatus ");
		this.addParam("@oldstatus string", task.getOldstatus());
		return sql.toString();
	}

}
