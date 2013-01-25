package com.spark.order.service.dao.sql.impl;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.service.dao.sql.ISimpleTaskSql;
import com.spark.order.service.dao.sql.ISpellSimpleTaskSql;

/**
 * <p>新增数据Sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-10-18
 */
public abstract class InsertSql implements ISimpleTaskSql, ISpellSimpleTaskSql{
	/**
	 * 上下文
	 */
	protected Context context;
	public InsertSql(Context context){
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
		StringBuilder sql = new StringBuilder("define insert I_Order");
		sql.append(" ( ");
		sql.append(setParameter());
		sql.append(" ) \n");
		sql.append(" begin \n");
		sql.append(setSql(task));
		sql.append(" \n end ");
		return sql.toString();
	}
}
