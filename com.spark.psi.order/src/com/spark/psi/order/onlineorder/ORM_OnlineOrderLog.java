package com.spark.psi.order.onlineorder;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_OnlineOrderLog extends ORMDeclarator<com.spark.onlineorder.intf.entity.OnlineOrderLogEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_billsId;
	public final QueryColumnDefine c_message;
	public final QueryColumnDefine c_operator;
	public final QueryColumnDefine c_processingTime;

	public ORM_OnlineOrderLog() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_billsId = this.orm.getColumns().get(1);
		this.c_message = this.orm.getColumns().get(2);
		this.c_operator = this.orm.getColumns().get(3);
		this.c_processingTime = this.orm.getColumns().get(4);
	}
}
