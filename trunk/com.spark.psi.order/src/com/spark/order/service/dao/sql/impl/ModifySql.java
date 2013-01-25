package com.spark.order.service.dao.sql.impl;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.service.dao.sql.ISimpleTaskSql;
import com.spark.order.service.dao.sql.ISpellSimpleTaskSql;

/**
 * <p>�޸�Sql</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-10-18
 */
public abstract class ModifySql implements ISimpleTaskSql, ISpellSimpleTaskSql{
	/**
	 * ������
	 */
	protected Context context;
	public ModifySql(Context context){
		this.context = context;
	}

	public DBCommand getDB(SimpleTask task) {
		return context.prepareStatement(getSql(task));
	}

	public String getSql(SimpleTask task) {
		//define update C_Order
		StringBuilder sql = new StringBuilder("define update C_Order");
		sql.append(" ( ");
		sql.append(setParameter());
		sql.append(" ) \n");
		sql.append(" begin \n");
		sql.append(setSql(task));
		sql.append(" \n end ");
		return sql.toString();
	}
}
