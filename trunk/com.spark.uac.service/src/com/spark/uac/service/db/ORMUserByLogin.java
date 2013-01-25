package com.spark.uac.service.db;

public class ORMUserByLogin extends ORMUser {

	public final static String NAME = "ORM_UAC_User3";

	public ORMUserByLogin(TableUser tbUser) {
		super(tbUser, NAME);
		this.orm.setCondition(tableReference
				.expOf(tbUser.f_mobile)
				.xEq(this.orm.newArgument(tbUser.f_mobile)).and(tableReference.expOf(tbUser.f_enabled).xEq(true)));
			
	}

}
