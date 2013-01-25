package com.spark.uac.service.db;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.uac.service.impl.UserInfoImpl;

public abstract class ORMUser extends ORMDeclarator<UserInfoImpl> {

	protected QuRelationRefDeclare tableReference;

	public ORMUser(TableUser tbUser, String name) {
		super(name);
		tableReference = this.orm.newReference(tbUser);
		this.orm.newColumn(tbUser.f_tenant_code,"tenantCode");
		this.orm.newColumn(tbUser.f_tenant_id,"tenantId");
		this.orm.newColumn(tbUser.f_id, "userId");
		this.orm.newColumn(tbUser.f_tenant_name, "tenantName");
		this.orm.newColumn(tbUser.f_mobile, "mobileNumber");
		this.orm.newColumn(tbUser.f_password, "password");
		this.orm.newColumn(tbUser.f_enabled, "enabled");
		this.orm.newColumn(tbUser.f_user_status,"status");
	}

}
