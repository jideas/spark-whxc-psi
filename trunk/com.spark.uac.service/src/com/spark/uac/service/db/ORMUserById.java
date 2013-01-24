package com.spark.uac.service.db;

public class ORMUserById extends ORMUser {

	public final static String NAME = "ORM_UAC_User2";

	public ORMUserById(TableUser tbUser) {
		super(tbUser, NAME);
		this.orm.setCondition(tableReference.expOf(tbUser.f_id).xEq(
				this.orm.newArgument(tbUser.f_id)));
	}

}
