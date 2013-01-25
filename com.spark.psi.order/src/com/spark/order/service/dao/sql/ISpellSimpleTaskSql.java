package com.spark.order.service.dao.sql;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * <p>拼写SimpleTask语句Sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-7
 */
public interface ISpellSimpleTaskSql {
	/**
	 * 设置传入参数
	 * @return String
	 */
	public String setParameter();
	/**
	 * Sql脚本
	 * @param task
	 * @return String
	 */
	public String setSql(SimpleTask task);
}
