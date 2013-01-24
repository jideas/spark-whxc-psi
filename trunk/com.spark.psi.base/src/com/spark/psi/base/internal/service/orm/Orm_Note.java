package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Note extends ORMDeclarator<com.spark.psi.base.publicimpl.NoteImpl> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_noteText;
	public final QueryColumnDefine c_recid;

	public Orm_Note() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_noteText = this.orm.getColumns().get(0);
		this.c_recid = this.orm.getColumns().get(1);
	}
}
