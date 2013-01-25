package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.sales.intf.task.SaleExamDeptTask;
import com.spark.order.service.dao.sql.impl.ModifySql;

/**
 * <p>销售订单更改部门Sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-7
 */
public class SaleExamDeptSql extends ModifySql{

	public SaleExamDeptSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@recid guid, @examDeptGuid guid, @oldExamDept guid, @status guid, @examine string";
	}
	
	public String setSql(SaleExamDeptTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(BillsEnum.SALE.getDb_table());
		sql.append(" as t set ");
		sql.append(" examDeptGuid = @examDeptGuid, ");
		sql.append(" examin = @examine ");
		sql.append(" where ");
		sql.append(" t.recid = @recid and ");
		sql.append(" t.status = @status ");
		if(null != task.oldExamDetp){
			sql.append(" and t.examDeptGuid = @oldExamDept ");
		}
		return sql.toString();
	}

	public String setSql(SimpleTask task) {
		return setSql((SaleExamDeptTask)task);
	}

}
