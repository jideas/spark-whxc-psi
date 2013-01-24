package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_NoticeDept extends ORMDeclarator<com.spark.psi.base.internal.entity.NoticeDept> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_deptGuid;
	public final QueryColumnDefine c_deptName;
	public final QueryColumnDefine c_noticeGuid;
	public final QueryColumnDefine c_tenantsGuid;

	public Orm_NoticeDept() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_deptGuid = this.orm.getColumns().get(1);
		this.c_deptName = this.orm.getColumns().get(2);
		this.c_noticeGuid = this.orm.getColumns().get(3);
		this.c_tenantsGuid = this.orm.getColumns().get(4);
	}
}
