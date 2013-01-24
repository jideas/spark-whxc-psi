package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Outstorage extends ORMDeclarator<com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_billsAmount;
	public final QueryColumnDefine c_billsCount;
	public final QueryColumnDefine c_checkoutDate;
	public final QueryColumnDefine c_checkoutString;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_isStoped;
	public final QueryColumnDefine c_namePY;
	public final QueryColumnDefine c_partnerId;
	public final QueryColumnDefine c_partnerName;
	public final QueryColumnDefine c_partnerShortName;
	public final QueryColumnDefine c_relaBillsId;
	public final QueryColumnDefine c_relaBillsNo;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_stopReason;
	public final QueryColumnDefine c_storeId;
	public final QueryColumnDefine c_storeName;
	public final QueryColumnDefine c_storeNamePY;
	public final QueryColumnDefine c_sheetType;
	public final QueryColumnDefine c_partnerCode;
	public final QueryColumnDefine c_creatorDeptId;
	public final QueryColumnDefine c_deptName;

	public Orm_Outstorage() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_billsAmount = this.orm.getColumns().get(1);
		this.c_billsCount = this.orm.getColumns().get(2);
		this.c_checkoutDate = this.orm.getColumns().get(3);
		this.c_checkoutString = this.orm.getColumns().get(4);
		this.c_createDate = this.orm.getColumns().get(5);
		this.c_creator = this.orm.getColumns().get(6);
		this.c_creatorId = this.orm.getColumns().get(7);
		this.c_isStoped = this.orm.getColumns().get(8);
		this.c_namePY = this.orm.getColumns().get(9);
		this.c_partnerId = this.orm.getColumns().get(10);
		this.c_partnerName = this.orm.getColumns().get(11);
		this.c_partnerShortName = this.orm.getColumns().get(12);
		this.c_relaBillsId = this.orm.getColumns().get(13);
		this.c_relaBillsNo = this.orm.getColumns().get(14);
		this.c_remark = this.orm.getColumns().get(15);
		this.c_status = this.orm.getColumns().get(16);
		this.c_stopReason = this.orm.getColumns().get(17);
		this.c_storeId = this.orm.getColumns().get(18);
		this.c_storeName = this.orm.getColumns().get(19);
		this.c_storeNamePY = this.orm.getColumns().get(20);
		this.c_sheetType = this.orm.getColumns().get(21);
		this.c_partnerCode = this.orm.getColumns().get(22);
		this.c_creatorDeptId = this.orm.getColumns().get(23);
		this.c_deptName = this.orm.getColumns().get(24);
	}
}
