package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Monitor extends ORMDeclarator<com.spark.psi.base.publicimpl.MonitorImpl> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_monitors;
	public final QueryColumnDefine c_recid;

	public Orm_Monitor() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_monitors = this.orm.getColumns().get(0);
		this.c_recid = this.orm.getColumns().get(1);
	}
}
