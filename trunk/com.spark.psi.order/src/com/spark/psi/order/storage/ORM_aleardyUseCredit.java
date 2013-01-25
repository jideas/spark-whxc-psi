package com.spark.psi.order.storage;

import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_aleardyUseCredit extends ORMDeclarator<com.spark.customer.AlreadyUseCredit> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_customerId;

	public ORM_aleardyUseCredit() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_customerId = this.orm.getColumns().get(0);
	}
}
