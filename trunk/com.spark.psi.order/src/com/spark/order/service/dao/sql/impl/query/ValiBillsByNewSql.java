package com.spark.order.service.dao.sql.impl.query;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.service.dao.sql.impl.QuerySql;

/**
 * <p>У���ڶ���ҳǩ�����ύ�����˻�״̬���Ƿ��е���</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-7
 */
public class ValiBillsByNewSql extends QuerySql{

	public ValiBillsByNewSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@status1 guid, @status2 guid";
	}

	public String setSql(SelectKey key) {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select ");
		sql.append("  ");
		sql.append("  ");
		sql.append("  ");
		sql.append("  ");
		sql.append("  ");
		sql.append("  ");
		sql.append("  ");
		sql.append("  ");
		return sql.toString();
	}
	
}
