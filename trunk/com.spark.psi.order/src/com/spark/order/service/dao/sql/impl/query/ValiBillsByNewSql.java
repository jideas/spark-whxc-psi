package com.spark.order.service.dao.sql.impl.query;

import com.jiuqi.dna.core.Context;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.service.dao.sql.impl.QuerySql;

/**
 * <p>校验在订货页签（待提交或已退回状态）是否有单据</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
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
