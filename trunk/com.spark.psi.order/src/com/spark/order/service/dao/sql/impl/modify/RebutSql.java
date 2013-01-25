package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.task.RebutTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.service.dao.sql.impl.ModifySql;

/**
 * <p>驳回Sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br> 
 */
public class RebutSql extends ModifySql{

	public RebutSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@recid guid, @status string, @oldstatus string, @cause string, @approveStr string";
	}

	public String setSql(SimpleTask task) {
		return setSql((RebutTask)task);
	}
	
	public String setSql(RebutTask task) {
		BillsEnum e = task.billsEnum;
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(e.getDb_table());
		sql.append(" as t set ");
		sql.append(" status = @status, ");
		sql.append(" approveStr = @approveStr, ");
		 
		sql.append(" rejectReason = @cause ");
		sql.append(" where ");
		sql.append(" t.recid = @recid and ");
		sql.append(" t.status = @oldstatus ");
		return sql.toString();
	}
}
