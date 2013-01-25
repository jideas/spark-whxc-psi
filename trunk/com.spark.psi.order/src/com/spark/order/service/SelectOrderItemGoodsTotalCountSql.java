package com.spark.order.service;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.facade.BillsConstant;
import com.spark.order.intf.key.SelectOrderItemGoodsTotalCountKey;
import com.spark.order.util.dnaSql.DnaSql_query;

/**
 * <p>查询订单明细商品数量总和</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-4-18
 */
public class SelectOrderItemGoodsTotalCountSql extends DnaSql_query{
	private final SelectOrderItemGoodsTotalCountKey key;
	public SelectOrderItemGoodsTotalCountSql(Context context, SelectOrderItemGoodsTotalCountKey key) {
		super(context);
		this.key = key;
	}

	@Override
	protected String getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" sum(t.\"count\") as totalNum ");
		sql.append(" from ");
		sql.append(key.getOrderEnum().getDb_table_item());
		sql.append(" as t ");
		sql.append(" where "); 
		sql.append(" t.billsId = @billsId ");
		addParam("@billsId guid", key.getOrderId());
		return sql.toString();
	}
}
