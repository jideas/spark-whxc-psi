package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Dealing extends ORMDeclarator<com.spark.psi.account.intf.entity.dealing.Dealing> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_amount;
	public final QueryColumnDefine c_initAmount;
	public final QueryColumnDefine c_type;
	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_partnerId;
	public final QueryColumnDefine c_partnerName;
	public final QueryColumnDefine c_namePY;
	public final QueryColumnDefine c_partnerShortName;

	public Orm_Dealing() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_amount = this.orm.getColumns().get(0);
		this.c_initAmount = this.orm.getColumns().get(1);
		this.c_type = this.orm.getColumns().get(2);
		this.c_id = this.orm.getColumns().get(3);
		this.c_partnerId = this.orm.getColumns().get(4);
		this.c_partnerName = this.orm.getColumns().get(5);
		this.c_namePY = this.orm.getColumns().get(6);
		this.c_partnerShortName = this.orm.getColumns().get(7);
	}
}
