package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_StoreEmployee extends ORMDeclarator<com.spark.psi.base.internal.entity.StoreEmployee> {

	public final ArgumentDefine arg_tenantId;
	public final ArgumentDefine arg_type;

	public final QueryColumnDefine c_employeeGuid;
	public final QueryColumnDefine c_employeeType;
	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_storeGuid;
	public final QueryColumnDefine c_tenantId;

	public Orm_StoreEmployee() {
		this.arg_tenantId = this.orm.getArguments().get(0);
		this.arg_type = this.orm.getArguments().get(1);
		this.c_employeeGuid = this.orm.getColumns().get(0);
		this.c_employeeType = this.orm.getColumns().get(1);
		this.c_recid = this.orm.getColumns().get(2);
		this.c_storeGuid = this.orm.getColumns().get(3);
		this.c_tenantId = this.orm.getColumns().get(4);
	}
}
