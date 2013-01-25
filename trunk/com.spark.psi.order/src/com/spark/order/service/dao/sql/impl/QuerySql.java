package com.spark.order.service.dao.sql.impl;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.service.dao.sql.ISelectSql;
import com.spark.order.service.dao.sql.ISpellSelectSql;

/**
 * <p>查询Sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-10-18
 */
public abstract class QuerySql implements ISelectSql, ISpellSelectSql{
	/**
	 * 上下文
	 */
	protected Context context;
	public QuerySql(Context context){
		this.context = context;
	}

	public DBCommand getDB(SelectKey key) {
		return context.prepareStatement(getSql(key));
	}
	
	public String getSql(SelectKey key) {
		StringBuilder sql = new StringBuilder("define query Q_Order");
		String s = setSql(key);
		sql.append(" ( ");
		sql.append(setParameter());
		sql.append(" ) \n");
		sql.append(" begin \n");
		sql.append(s);
		sql.append(" \n end ");
		return sql.toString();
	}
}
