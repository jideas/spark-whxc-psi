package com.spark.order.service.dao.sql;

import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * <p>Sql处理接口</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-7
 */
public interface ISimpleTaskSql {
	/**
	 * 获得sql
	 * @param key
	 * @return String
	 */
	public String getSql(SimpleTask task);
	/**
	 * 获得DB对象
	 * @return DBCommand
	 */
	public DBCommand getDB(SimpleTask task);
}
