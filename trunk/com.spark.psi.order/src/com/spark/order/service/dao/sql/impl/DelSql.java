package com.spark.order.service.dao.sql.impl;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.service.dao.sql.ISimpleTaskSql;
import com.spark.order.service.dao.sql.ISpellSimpleTaskSql;

public abstract class DelSql implements ISimpleTaskSql, ISpellSimpleTaskSql{
	/**
	 * 上下文
	 */
	protected Context context;
	public DelSql(Context context){
		this.context = context;
	}
	
	/**
	 * 获得DB对象
	 * @return DBCommand
	 */
	public DBCommand getDB(SimpleTask task){
		return context.prepareStatement(getSql(task));
	}

	public String getSql(SimpleTask task) {
		StringBuilder sql = new StringBuilder("define delete D_Order");
		sql.append(" ( ");
		sql.append(setParameter());
		sql.append(" ) \n");
		sql.append(" begin \n");
		sql.append(setSql(task));
		sql.append(" \n end ");
		return sql.toString();
	}
}
