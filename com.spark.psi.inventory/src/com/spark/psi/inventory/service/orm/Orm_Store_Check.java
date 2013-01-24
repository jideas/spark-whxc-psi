package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Store_Check extends ORMDeclarator<com.spark.psi.inventory.internal.entity.CheckInventory> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_checkDeficient;
	public final QueryColumnDefine c_checkObj;
	public final QueryColumnDefine c_checkOrdNo;
	public final QueryColumnDefine c_checkOrdState;
	public final QueryColumnDefine c_checkPerson;
	public final QueryColumnDefine c_checkProfit;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_createPerson;
	public final QueryColumnDefine c_deptGuid;
	public final QueryColumnDefine c_endDate;
	public final QueryColumnDefine c_markName;
	public final QueryColumnDefine c_markPerson;
	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_startDate;
	public final QueryColumnDefine c_storeGuid;
	public final QueryColumnDefine c_storeName;
	public final QueryColumnDefine c_storePY;
	public final QueryColumnDefine c_StoreStatus;
	public final QueryColumnDefine c_tenantsGuid;

	public Orm_Store_Check() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_checkDeficient = this.orm.getColumns().get(0);
		this.c_checkObj = this.orm.getColumns().get(1);
		this.c_checkOrdNo = this.orm.getColumns().get(2);
		this.c_checkOrdState = this.orm.getColumns().get(3);
		this.c_checkPerson = this.orm.getColumns().get(4);
		this.c_checkProfit = this.orm.getColumns().get(5);
		this.c_createDate = this.orm.getColumns().get(6);
		this.c_createPerson = this.orm.getColumns().get(7);
		this.c_deptGuid = this.orm.getColumns().get(8);
		this.c_endDate = this.orm.getColumns().get(9);
		this.c_markName = this.orm.getColumns().get(10);
		this.c_markPerson = this.orm.getColumns().get(11);
		this.c_recid = this.orm.getColumns().get(12);
		this.c_remark = this.orm.getColumns().get(13);
		this.c_startDate = this.orm.getColumns().get(14);
		this.c_storeGuid = this.orm.getColumns().get(15);
		this.c_storeName = this.orm.getColumns().get(16);
		this.c_storePY = this.orm.getColumns().get(17);
		this.c_StoreStatus = this.orm.getColumns().get(18);
		this.c_tenantsGuid = this.orm.getColumns().get(19);
	}
}
