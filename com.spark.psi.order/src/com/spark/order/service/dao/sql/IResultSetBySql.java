package com.spark.order.service.dao.sql;

import java.util.List;

import com.jiuqi.dna.core.da.RecordSet;

/**
 * <p>������ӿ�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-12-2
 */
public interface IResultSetBySql {
	public <T> List<T> getList(RecordSet rs);
	public Object getResult(RecordSet rs);
}
