package com.spark.order.service.dao.sql.impl.del;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.service.dao.sql.impl.DelSql;

public class DeleteRetailTimeOverSql extends DelSql{

	public DeleteRetailTimeOverSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@tenants guid, @status string, @overTime date, @userId guid";
	}

	public String setSql(SimpleTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from ");
		sql.append(" sa_sale_retail as t join sa_sale_retail_det as b on t.recid = b.orderId ");
		sql.append(" where ");
		sql.append(" t.tenantsId = @tenants and t.status = @status and t.creatorId = @userId and t.createDate < @overTime");
		return sql.toString();
	}

}
