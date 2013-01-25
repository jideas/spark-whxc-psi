package com.spark.psi.message.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Message_Info extends ORMDeclarator<com.spark.psi.message.entity.SMessageInfo> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_messageType;
	public final QueryColumnDefine c_relaObjId;
	public final QueryColumnDefine c_stringValue1;
	public final QueryColumnDefine c_stringValue2;
	public final QueryColumnDefine c_stringValue3;
	public final QueryColumnDefine c_templateCode;

	public Orm_Message_Info() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_messageType = this.orm.getColumns().get(1);
		this.c_relaObjId = this.orm.getColumns().get(2);
		this.c_stringValue1 = this.orm.getColumns().get(3);
		this.c_stringValue2 = this.orm.getColumns().get(4);
		this.c_stringValue3 = this.orm.getColumns().get(5);
		this.c_templateCode = this.orm.getColumns().get(6);
	}
}
