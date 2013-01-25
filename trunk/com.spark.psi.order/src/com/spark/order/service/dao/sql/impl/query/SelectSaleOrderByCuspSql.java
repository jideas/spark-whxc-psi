package com.spark.order.service.dao.sql.impl.query;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.RecordSet;
import com.spark.order.intf.key.SelectKey;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.service.dao.sql.IResultSetBySql;
import com.spark.order.service.dao.sql.impl.QuerySql;

public class SelectSaleOrderByCuspSql extends QuerySql implements IResultSetBySql{
//	private final SelectSaleOrderByCuspTask task;
	public SelectSaleOrderByCuspSql(Context context){//, SelectSaleOrderByCuspTask task) {
		super(context);
//		this.task = task;
	}

	public String setParameter() {
		return "@tenants guid, @cusp guid";
	}

	
	
	public String setSql() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" count(t.RECID) as num ");
		sql.append(" from ");
		sql.append(BillsEnum.SALE.getDb_table());
		sql.append(" as t ");
		sql.append(" where ");
		sql.append(" t.tenantsGuid = @tenants ");
		sql.append(" and t.cuspGuid = @cusp ");
		return sql.toString();
	}

	public String setSql(SelectKey key) {
		return setSql();
	}

	public <T> List<T> getList(RecordSet rs) {
		return null;
	}

	public Integer getResult(RecordSet rs) {
		int count = 0;
		if(rs.next()){
			count = rs.getFields().get(0).getInt();
		}
		return count;
	}

}
