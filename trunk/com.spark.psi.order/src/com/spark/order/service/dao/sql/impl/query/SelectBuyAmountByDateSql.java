package com.spark.order.service.dao.sql.impl.query;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.purchase.intf.key.SelectPurchaseAmountByDateKey;
import com.spark.order.service.dao.sql.impl.QuerySql;

public class SelectBuyAmountByDateSql extends QuerySql{

	public SelectBuyAmountByDateSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@tenants guid, @noStore guid, @planStore guid, @allStore guid, @noCon guid, @yesCon guid, @finish guid, @start date, @end date";
	}

	public String setSql(SelectKey key) {
		return setSql((SelectPurchaseAmountByDateKey)key);
	}
	
	private String setSql(SelectPurchaseAmountByDateKey key){
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" sum(t.totalAmount) as amount ");
		sql.append(" from ");
		sql.append(" sa_buy_order as t ");
		sql.append(" where ");
		sql.append(" t.tenantsGuid = @tenants and ");
		sql.append(" (t.status = @noStore or t.status = @planStore or t.status = @allStore or t.status = @finish or t.status = @noCon or t.status = @yesCon) ");
		sql.append(" and ");
		sql.append(" t.createDate>@start ");
		sql.append(" and ");
		sql.append(" t.createDate<@end ");
		sql.append(" group by ");
		sql.append(" t.tenantsGuid ");
		return sql.toString();
	}

}
