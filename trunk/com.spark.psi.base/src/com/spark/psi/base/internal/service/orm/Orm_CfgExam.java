package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_CfgExam extends ORMDeclarator<com.spark.psi.base.internal.entity.CfgExam> {

	public final ArgumentDefine arg_tenantsGuid;
	public final ArgumentDefine arg_modeid;

	public final QueryColumnDefine c_isOpenExam;
	public final QueryColumnDefine c_maxAmount;
	public final QueryColumnDefine c_modeid;
	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_tenantsGuid;
	public final QueryColumnDefine c_updatePerson;
	public final QueryColumnDefine c_updateDate;

	public Orm_CfgExam() {
		this.arg_tenantsGuid = this.orm.getArguments().get(0);
		this.arg_modeid = this.orm.getArguments().get(1);
		this.c_isOpenExam = this.orm.getColumns().get(0);
		this.c_maxAmount = this.orm.getColumns().get(1);
		this.c_modeid = this.orm.getColumns().get(2);
		this.c_recid = this.orm.getColumns().get(3);
		this.c_tenantsGuid = this.orm.getColumns().get(4);
		this.c_updatePerson = this.orm.getColumns().get(5);
		this.c_updateDate = this.orm.getColumns().get(6);
	}
}
