package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.task.FlowTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.StatusEnum;
import com.spark.order.service.dao.sql.impl.ModifySql;

/**
 * <p>订单流程Sql，更新状态status</p>  
 */
public class FlowStatusSql extends ModifySql{

	public FlowStatusSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@recid guid, @status string, @oldstatus string, @deptGuid guid, @okDate date, @examDept guid, @examin string, @planOutDate date";
	}

	public String setSql(FlowTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(task.billsEnum.getDb_table());
		sql.append(" as t set ");
		if(null != task.getDeptGuid()){
			sql.append(" deptId = @deptGuid, ");
		}  
		if(StatusEnum.Return.isThis(task.oldstatus)){ 
			if(BillsEnum.SALE == task.billsEnum){
				sql.append(" deliveryDate = @planOutDate, ");
			}
		}
		sql.append(" status = @status ");
		sql.append(" where ");
		sql.append(" t.recid = @recid ");
		if(null != task.oldstatus){
			sql.append(" and t.status = @oldstatus ");
		}
		return sql.toString();
	}

	public String setSql(SimpleTask task) {
		return setSql((FlowTask)task);
	}

}
