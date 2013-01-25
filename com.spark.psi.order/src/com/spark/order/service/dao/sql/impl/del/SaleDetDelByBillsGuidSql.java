package com.spark.order.service.dao.sql.impl.del;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.common.components.db.ERPTableNames;
import com.spark.order.service.dao.sql.impl.DelSql;

/**
 * <p>
 * 根据销售订单GUID删除采购订单明细
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * 
 * 
 * @author 莫迪
 * @version 2011-11-20
 */
public class SaleDetDelByBillsGuidSql extends DelSql {

	public SaleDetDelByBillsGuidSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@tenants guid, @billsGuid guid";
	}

	public String setSql(SimpleTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from ");
		sql.append(" " + ERPTableNames.Sales.Order_Det.getTableName() + " as t ");
		sql.append(" where ");
		sql.append(" t.billsId = @billsGuid ");
		return sql.toString();
	}

}
