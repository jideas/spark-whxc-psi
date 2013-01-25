package com.spark.psi.message.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Message_Mapping extends ORMDeclarator<com.spark.psi.message.entity.SMessageMapping> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_endTime;
	public final QueryColumnDefine c_messageId;
	public final QueryColumnDefine c_messageType;
	public final QueryColumnDefine c_startTime;
	public final QueryColumnDefine c_userId;

	public Orm_Message_Mapping() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_endTime = this.orm.getColumns().get(1);
		this.c_messageId = this.orm.getColumns().get(2);
		this.c_messageType = this.orm.getColumns().get(3);
		this.c_startTime = this.orm.getColumns().get(4);
		this.c_userId = this.orm.getColumns().get(5);
	}
}
