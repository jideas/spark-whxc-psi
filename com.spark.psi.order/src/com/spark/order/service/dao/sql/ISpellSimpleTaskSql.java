package com.spark.order.service.dao.sql;

import com.jiuqi.dna.core.invoke.SimpleTask;

/**
 * <p>ƴдSimpleTask���Sql</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-7
 */
public interface ISpellSimpleTaskSql {
	/**
	 * ���ô������
	 * @return String
	 */
	public String setParameter();
	/**
	 * Sql�ű�
	 * @param task
	 * @return String
	 */
	public String setSql(SimpleTask task);
}
