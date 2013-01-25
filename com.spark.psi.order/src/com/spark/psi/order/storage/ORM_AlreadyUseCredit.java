package com.spark.psi.order.storage;

import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_AlreadyUseCredit extends ORMDeclarator<com.spark.customer.AlreadyUseCredit> {

	public final ArgumentDefine arg_customerId;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_customerId;
	public final QueryColumnDefine c_aleardyUseCredit;

	public ORM_AlreadyUseCredit() {
		this.arg_customerId = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_customerId = this.orm.getColumns().get(1);
		this.c_aleardyUseCredit = this.orm.getColumns().get(2);
	}
}
