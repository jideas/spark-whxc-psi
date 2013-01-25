package com.spark.order.util.dnaSql;

import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;

/**
 * <p>DNA SQL�ӿ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author modi
 * @version 2012-3-30
 */
public interface IDnaSql {
	/**
	 * ���DNA���ݿ�����ӿ�
	 * @return DBCommand
	 */
	DBCommand getDBCommand();
	/**
	 * ��÷������ݼ��ӿ�
	 * @return RecordSet
	 */
	RecordSet executeQuery();
	/**
	 * ��÷������ݼ��ӿ�
	 * @return RecordSet
	 */
	RecordSet executeQuery(long offset, long rowCount);
	/**
	 * ִ�и���SQL��������Ӧ����
	 * @return int
	 */
	int executeUpdate();
	/**
	 * ���������
	 * @return long
	 */
	int rowCountOf();
	/**
	 * ��ò�������
	 * @return List<?>
	 */
	Object[] getParams();
	/**
	 * ���DNA���ݿ�����ӿ�(���в���һ�������)
	 * @return DBCommand
	 */
	DBCommand getDBCommand_FinishedParams();
}
