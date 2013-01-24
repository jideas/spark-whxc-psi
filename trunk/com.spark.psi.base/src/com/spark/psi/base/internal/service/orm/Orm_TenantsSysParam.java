package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_TenantsSysParam extends ORMDeclarator<com.spark.psi.base.internal.entity.TenantSysParam> {

	public final ArgumentDefine arg_tenantsGuid;
	public final ArgumentDefine arg_key;

	public final QueryColumnDefine c_key;
	public final QueryColumnDefine c_yes;
	public final QueryColumnDefine c_tenantId;
	public final QueryColumnDefine c_id;

	public Orm_TenantsSysParam() {
		this.arg_tenantsGuid = this.orm.getArguments().get(0);
		this.arg_key = this.orm.getArguments().get(1);
		this.c_key = this.orm.getColumns().get(0);
		this.c_yes = this.orm.getColumns().get(1);
		this.c_tenantId = this.orm.getColumns().get(2);
		this.c_id = this.orm.getColumns().get(3);
	}
}
