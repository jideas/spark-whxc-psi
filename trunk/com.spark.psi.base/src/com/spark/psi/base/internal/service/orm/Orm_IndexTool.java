package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_IndexTool extends ORMDeclarator<com.spark.psi.base.publicimpl.IndexToolImpl> {

	public final ArgumentDefine arg_userid;
	public final ArgumentDefine arg_name;

	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_name;
	public final QueryColumnDefine c_x;
	public final QueryColumnDefine c_y;
	public final QueryColumnDefine c_userid;

	public Orm_IndexTool() {
		this.arg_userid = this.orm.getArguments().get(0);
		this.arg_name = this.orm.getArguments().get(1);
		this.c_recid = this.orm.getColumns().get(0);
		this.c_name = this.orm.getColumns().get(1);
		this.c_x = this.orm.getColumns().get(2);
		this.c_y = this.orm.getColumns().get(3);
		this.c_userid = this.orm.getColumns().get(4);
	}
}
