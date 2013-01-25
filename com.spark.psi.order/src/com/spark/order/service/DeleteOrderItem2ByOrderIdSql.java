package com.spark.order.service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.OrderEnum;
import com.spark.order.util.dnaSql.DnaSql_delete;

public class DeleteOrderItem2ByOrderIdSql extends DnaSql_delete{
	final GUID orderId;
	final OrderEnum orderEnum;
	
	public DeleteOrderItem2ByOrderIdSql(Context context, OrderEnum orderEnum, GUID orderId) {
		super(context);
		this.orderId = orderId;
		this.orderEnum = orderEnum;
	}

	@Override
	protected String getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete ");
		sql.append(orderEnum.getDb_table_item());
		sql.append(" as t ");
		sql.append(" from ");
		sql.append(" t.orderId = @orderId ");
		this.addParam("@orderId guid", orderId);
		return sql.toString();
	}
}
