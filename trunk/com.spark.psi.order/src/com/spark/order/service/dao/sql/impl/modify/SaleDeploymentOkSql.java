package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.sales.intf.task.SaleDeploymentOkTask;
import com.spark.order.service.dao.sql.impl.ModifySql;

/**
 * <p>出库分配完成sql</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-20
 */
public class SaleDeploymentOkSql extends ModifySql{

	public SaleDeploymentOkSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@recid guid, @isAllot boolean";
	}

	public String setSql(SimpleTask task) {
		return setSql((SaleDeploymentOkTask)task);
	}
	
	private String setSql(SaleDeploymentOkTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(BillsEnum.SALE.getDb_table());
		sql.append(" as t ");
		sql.append(" set ");
		sql.append(" isAllot = @isAllot ");
		sql.append(" where ");
		sql.append(" t.RECID = @recid and t.isAllot <> @isAllot");
		return sql.toString();
	}

}
