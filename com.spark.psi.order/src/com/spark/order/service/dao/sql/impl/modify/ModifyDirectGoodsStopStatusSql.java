package com.spark.order.service.dao.sql.impl.modify;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.order.sales.intf.task.ModifyDirectGoodsStopStatusTask;
import com.spark.order.service.dao.sql.impl.ModifySql;

public class ModifyDirectGoodsStopStatusSql extends ModifySql{

	public ModifyDirectGoodsStopStatusSql(Context context) {
		super(context);
	}

	public String setParameter() {
		return "@tenants guid, @saleRecid guid, @isStop boolean";
	}

	public String setSql(SimpleTask task) {
		return setSql((ModifyDirectGoodsStopStatusTask)task);
	}

	public String setSql(ModifyDirectGoodsStopStatusTask task){
		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(" SA_PURCHASE_GOODS_Direct as t ");
		sql.append(" set ");
		sql.append(" deleteFlag = @isStop ");
		sql.append(" where ");
		sql.append(" t.tenantsId = @tenants ");
		sql.append(" and t.sourceSaleId = @saleRecid ");
		return sql.toString();
	}
}
