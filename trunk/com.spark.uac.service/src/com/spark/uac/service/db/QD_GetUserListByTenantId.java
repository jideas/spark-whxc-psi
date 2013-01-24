package com.spark.uac.service.db;

public  class QD_GetUserListByTenantId extends ORMUser{

	public QD_GetUserListByTenantId(TableUser tbUser){
	    super(tbUser, "QD_GetUserListByTenantId");
		this.orm.setCondition(tableReference
				.expOf(tbUser.f_tenant_id)
				.xEq(this.orm.newArgument(tbUser.f_tenant_id)));

    }

}
