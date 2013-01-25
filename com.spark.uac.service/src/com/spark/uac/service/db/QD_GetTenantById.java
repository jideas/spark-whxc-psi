package com.spark.uac.service.db;

public class QD_GetTenantById extends ORMTenant{

	public QD_GetTenantById(TableTenant table){
		super(table, "QD_GetUACTenantById");
		orm.setCondition(tablerefdeclare.expOf(table.f_id).xEq(
		        orm.newArgument(table.f_id)));
	}

}
