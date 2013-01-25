package com.spark.order.service.dao.sql;

import com.jiuqi.dna.core.da.DBCommand;
import com.spark.order.intf.key.SelectKey;

/**
 * <p>Sql����ӿ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-10-18
 */
public interface ISelectSql {
	/**
	 * ���sql
	 * @param key
	 * @return String
	 */
	public String getSql(SelectKey key);
	/**
	 * ���DB����
	 * @return DBCommand
	 */
	public DBCommand getDB(SelectKey key);
//	/**
//	 * ���orm����
//	 * @param <T>
//	 * @param t
//	 * @return ORMDeclarator<T>
//	 */
//	public <T> ORMDeclarator<T> getOrmSql(T t); 
//	/**
//	 * ����޸�����commond����
//	 * @return UpdateStatementDeclarator
//	 */
//	public UpdateStatementDeclarator getModifySql();
}
