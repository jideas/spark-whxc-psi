package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.service.dao.sql.impl.ModifySql;

/**
 * <p>修改已用信用额度</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-29
 */
public class AlreadyUseCreditSql extends ModifySql{

	public AlreadyUseCreditSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@cusId guid, @change double";
	}

	public String setSql(SimpleTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(" SA_SALE_AlreadyUseCredit as t ");
		sql.append(" set ");
		sql.append(" aleardyUseCredit = t.aleardyUseCredit + @change ");
		sql.append(" where ");
		sql.append(" t.customerId = @cusId ");
		return sql.toString();
	}
	
}
