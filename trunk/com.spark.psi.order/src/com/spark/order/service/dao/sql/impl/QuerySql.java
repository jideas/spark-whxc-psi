package com.spark.order.service.dao.sql.impl;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.service.dao.sql.ISelectSql;
import com.spark.order.service.dao.sql.ISpellSelectSql;

/**
 * <p>��ѯSql</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-10-18
 */
public abstract class QuerySql implements ISelectSql, ISpellSelectSql{
	/**
	 * ������
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
