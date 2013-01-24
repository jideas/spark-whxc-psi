package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Invoice extends ORMDeclarator<com.spark.psi.account.intf.entity.invoice.Invoice> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_createPerson;
	public final QueryColumnDefine c_cusDeptGuid;
	public final QueryColumnDefine c_cusproFullName;
	public final QueryColumnDefine c_cusproFullNamePY;
	public final QueryColumnDefine c_cusproGuid;
	public final QueryColumnDefine c_cusproName;
	public final QueryColumnDefine c_cusproNamePY;
	public final QueryColumnDefine c_deptGuid;
	public final QueryColumnDefine c_invalidDate;
	public final QueryColumnDefine c_invalidPerson;
	public final QueryColumnDefine c_invalidReason;
	public final QueryColumnDefine c_invoAmount;
	public final QueryColumnDefine c_invoCode;
	public final QueryColumnDefine c_invoDate;
	public final QueryColumnDefine c_invoPerson;
	public final QueryColumnDefine c_invoPersonName;
	public final QueryColumnDefine c_invoType;
	public final QueryColumnDefine c_isInvalided;

	public Orm_Invoice() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_createDate = this.orm.getColumns().get(1);
		this.c_createPerson = this.orm.getColumns().get(2);
		this.c_cusDeptGuid = this.orm.getColumns().get(3);
		this.c_cusproFullName = this.orm.getColumns().get(4);
		this.c_cusproFullNamePY = this.orm.getColumns().get(5);
		this.c_cusproGuid = this.orm.getColumns().get(6);
		this.c_cusproName = this.orm.getColumns().get(7);
		this.c_cusproNamePY = this.orm.getColumns().get(8);
		this.c_deptGuid = this.orm.getColumns().get(9);
		this.c_invalidDate = this.orm.getColumns().get(10);
		this.c_invalidPerson = this.orm.getColumns().get(11);
		this.c_invalidReason = this.orm.getColumns().get(12);
		this.c_invoAmount = this.orm.getColumns().get(13);
		this.c_invoCode = this.orm.getColumns().get(14);
		this.c_invoDate = this.orm.getColumns().get(15);
		this.c_invoPerson = this.orm.getColumns().get(16);
		this.c_invoPersonName = this.orm.getColumns().get(17);
		this.c_invoType = this.orm.getColumns().get(18);
		this.c_isInvalided = this.orm.getColumns().get(19);
	}
}
