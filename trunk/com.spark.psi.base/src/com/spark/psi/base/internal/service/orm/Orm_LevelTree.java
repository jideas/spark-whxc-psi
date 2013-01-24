package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_LevelTree extends ORMDeclarator<com.spark.psi.base.internal.entity.LevelTree> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_path;
	public final QueryColumnDefine c_stauts;
	public final QueryColumnDefine c_tenantId;

	public Orm_LevelTree() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_path = this.orm.getColumns().get(1);
		this.c_stauts = this.orm.getColumns().get(2);
		this.c_tenantId = this.orm.getColumns().get(3);
	}
}
