package com.spark.order.service.dao.sql;

import com.jiuqi.dna.core.da.DBCommand;
import com.spark.order.intf.key.SelectKey;

/**
 * <p>Sql处理接口</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-10-18
 */
public interface ISelectSql {
	/**
	 * 获得sql
	 * @param key
	 * @return String
	 */
	public String getSql(SelectKey key);
	/**
	 * 获得DB对象
	 * @return DBCommand
	 */
	public DBCommand getDB(SelectKey key);
//	/**
//	 * 获得orm对象
//	 * @param <T>
//	 * @param t
//	 * @return ORMDeclarator<T>
//	 */
//	public <T> ORMDeclarator<T> getOrmSql(T t); 
//	/**
//	 * 获得修改类型commond对象
//	 * @return UpdateStatementDeclarator
//	 */
//	public UpdateStatementDeclarator getModifySql();
}
