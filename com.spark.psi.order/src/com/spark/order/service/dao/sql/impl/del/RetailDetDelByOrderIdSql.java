package com.spark.order.service.dao.sql.impl.del;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.service.dao.sql.impl.DelSql;

/**
 * <p>根据销售订单GUID删除采购订单明细</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-20
 */
public class RetailDetDelByOrderIdSql extends DelSql{

	public RetailDetDelByOrderIdSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@tenants guid, @billsGuid guid";
	}

	public String setSql(SimpleTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from ");
		sql.append(" sa_sale_retail_det as t ");
		sql.append(" where ");
		sql.append(" t.tenantsId = @tenants ");
		sql.append(" and t.orderId = @billsGuid ");
		return sql.toString();
	}

}
