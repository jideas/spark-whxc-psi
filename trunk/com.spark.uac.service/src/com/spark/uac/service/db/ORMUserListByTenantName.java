package com.spark.uac.service.db;

public class ORMUserListByTenantName extends ORMUser{

	public final static String NAME = "ORM_UserByTenantName"; 
	
	public ORMUserListByTenantName(TableUser tbUser){
	    super(tbUser, NAME);
		this.orm.setCondition(tableReference
				.expOf(tbUser.f_tenant_name)
				.xEq(this.orm.newArgument(tbUser.f_tenant_name)));
    }

}
