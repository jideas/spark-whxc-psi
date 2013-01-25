package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.task.BillsExamineChangeTask;
import com.spark.order.service.dao.sql.impl.ModifySql;

/**
 * <p>单据流程变化Sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-12-1
 */
public class BillsExamChangeSql extends ModifySql{

	public BillsExamChangeSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@tenants guid, @oldstatus string, @newstatus string, @cause string";
	}

	public String setSql(SimpleTask task) {
		return setSql((BillsExamineChangeTask)task);
	}

	public String setSql(BillsExamineChangeTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(task.getBillsEnum().getDb_table());
		sql.append(" as t ");
		sql.append(" set ");
		sql.append(" \"status\" = @newstatus, ");
		sql.append(" \"rejectReason\" = @cause ");
		sql.append(" where ");
		sql.append(" t.\"status\" = @oldstatus ");
		return sql.toString();
	}
}
