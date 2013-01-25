package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.sales.intf.task.SalePlanOutDateTask;
import com.spark.order.service.dao.sql.impl.ModifySql;

public class SalePlanOutDateSql extends ModifySql{

	public SalePlanOutDateSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@id guid, @planDate date";
	}

	public String setSql(SimpleTask task) {
		return setSql((SalePlanOutDateTask)task);
	}

	public String setSql(SalePlanOutDateTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(BillsEnum.SALE.getDb_table());
		sql.append(" as t ");
		sql.append(" set ");
		sql.append(" planOutDate = @planDate ");
		sql.append(" where ");
		sql.append(" t.RECID = @id ");
		return sql.toString();
	}
}
