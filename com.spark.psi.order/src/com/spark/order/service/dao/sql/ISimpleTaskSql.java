package com.spark.order.service.dao.sql;

import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * <p>Sql����ӿ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-7
 */
public interface ISimpleTaskSql {
	/**
	 * ���sql
	 * @param key
	 * @return String
	 */
	public String getSql(SimpleTask task);
	/**
	 * ���DB����
	 * @return DBCommand
	 */
	public DBCommand getDB(SimpleTask task);
}
