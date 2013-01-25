package com.spark.uac.service.db;

public class ORMUserListByTenantCode extends ORMUser{

	public final static String NAME = "ORM_UserByTenantCode"; 
	
	public ORMUserListByTenantCode(TableUser tbUser){
	    super(tbUser, NAME);
		this.orm.setCondition(tableReference
				.expOf(tbUser.f_tenant_code)
				.xEq(this.orm.newArgument(tbUser.f_tenant_code)));
    }

}
