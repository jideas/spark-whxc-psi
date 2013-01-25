package com.spark.order.service.dao.sql;

import com.spark.order.intf.key.SelectKey;

/**
 * <p>拼写查询语句Sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-7
 */
public interface ISpellSelectSql {
	
	/**
	 * 设置传入参数
	 * @return String
	 */
	public String setParameter();
	/**
	 * Sql脚本
	 * @return String
	 */
	public String setSql(SelectKey key);
}
