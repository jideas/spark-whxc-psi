package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.task.StopTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.service.dao.sql.impl.ModifySql;

/**
 * <p>中止Sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-11
 */
public class StopSql extends ModifySql{

	public StopSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@recid guid, @stop boolean, @oldstatus string, @cause string";
	}

	public String setSql(SimpleTask task) {
		return setSql((StopTask)task);
	}
	
	public String setSql(StopTask task) {
		BillsEnum e = task.billsEnum;
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(e.getDb_table());
		sql.append(" as t set ");
		sql.append(" isStoped = @stop, ");
		sql.append(" stopReason = @cause ");
		sql.append(" where ");
		sql.append(" t.recid = @recid ");
		if(null != task.oldstatus){
			sql.append(" and t.status = @oldstatus ");
		}
		return sql.toString();
	}
}
