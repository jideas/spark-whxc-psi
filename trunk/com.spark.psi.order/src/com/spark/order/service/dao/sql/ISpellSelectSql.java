package com.spark.order.service.dao.sql;

import com.spark.order.intf.key.SelectKey;

/**
 * <p>ƴд��ѯ���Sql</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-7
 */
public interface ISpellSelectSql {
	
	/**
	 * ���ô������
	 * @return String
	 */
	public String setParameter();
	/**
	 * Sql�ű�
	 * @return String
	 */
	public String setSql(SelectKey key);
}
