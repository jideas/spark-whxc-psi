package com.spark.order.service.dao.sql.impl.query;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.purchase.intf.key.SelectPurchaseOvertimeAmountKey;
import com.spark.order.service.dao.sql.impl.QuerySql;

public class SelectBuyOvertimeAmountSql extends QuerySql{

	public SelectBuyOvertimeAmountSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@tenants guid, @noStore guid, @planStore guid, @end date";
	}

	public String setSql(SelectKey key) {
		return setSql((SelectPurchaseOvertimeAmountKey)key);
	}
	
	private String setSql(SelectPurchaseOvertimeAmountKey key){
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" sum(t.totalAmount - t.okAmount) as amount ");
		sql.append(" from ");
		sql.append(" sa_buy_order as t ");
		sql.append(" where ");
		sql.append(" t.tenantsGuid = @tenants ");
		sql.append(" and  ");
		sql.append(" (t.status = @noStore or t.status = @planStore) ");
		sql.append(" and ");
		sql.append(" t.payDate<@end ");
		sql.append(" group by ");
		sql.append(" t.tenantsGuid ");
		return sql.toString();
	}

}
