package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Store_Dismounting extends ORMDeclarator<com.spark.psi.inventory.internal.entity.Dismounting> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_createPerson;
	public final QueryColumnDefine c_deptGuid;
	public final QueryColumnDefine c_dismDate;
	public final QueryColumnDefine c_dismOrdNo;
	public final QueryColumnDefine c_markName;
	public final QueryColumnDefine c_markPerson;
	public final QueryColumnDefine c_storeGuid;
	public final QueryColumnDefine c_storeName;
	public final QueryColumnDefine c_storePY;
	public final QueryColumnDefine c_tenantsGuid;

	public Orm_Store_Dismounting() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_createDate = this.orm.getColumns().get(1);
		this.c_createPerson = this.orm.getColumns().get(2);
		this.c_deptGuid = this.orm.getColumns().get(3);
		this.c_dismDate = this.orm.getColumns().get(4);
		this.c_dismOrdNo = this.orm.getColumns().get(5);
		this.c_markName = this.orm.getColumns().get(6);
		this.c_markPerson = this.orm.getColumns().get(7);
		this.c_storeGuid = this.orm.getColumns().get(8);
		this.c_storeName = this.orm.getColumns().get(9);
		this.c_storePY = this.orm.getColumns().get(10);
		this.c_tenantsGuid = this.orm.getColumns().get(11);
	}
}
