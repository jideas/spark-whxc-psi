package com.spark.order.service.dao.sql.impl.del;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.service.dao.sql.impl.DelSql;

/**
 * <p>根据采购订单GUID删除采购订单明细</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-18
 */
public class BuyDetDelByBillsGuidSql extends DelSql{

	public BuyDetDelByBillsGuidSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return " @bills guid";
	}

	public String setSql(SimpleTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from ");
		sql.append(" PSI_Purchase_Det ");
		sql.append(" as t ");
		sql.append(" where "); 
		sql.append(" t.billsId = @bills ");
		return sql.toString();
	}
}
