package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_AllocateInfo extends ORMDeclarator<com.spark.psi.inventory.internal.entity.AllocateInventory> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_allocSheetNo;
	public final QueryColumnDefine c_allocateInDate;
	public final QueryColumnDefine c_allocateInPerson;
	public final QueryColumnDefine c_allocateInPersonName;
	public final QueryColumnDefine c_allocateInStoreId;
	public final QueryColumnDefine c_allocateInStoreName;
	public final QueryColumnDefine c_allocateOutDate;
	public final QueryColumnDefine c_allocateOutPerson;
	public final QueryColumnDefine c_allocateOutPersonName;
	public final QueryColumnDefine c_allocateOutStoreId;
	public final QueryColumnDefine c_allocateOutStoreName;
	public final QueryColumnDefine c_allocateReason;
	public final QueryColumnDefine c_applyDate;
	public final QueryColumnDefine c_approveDate;
	public final QueryColumnDefine c_approvePerson;
	public final QueryColumnDefine c_approvePersonId;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_creatorPY;
	public final QueryColumnDefine c_deptId;
	public final QueryColumnDefine c_rejectReason;
	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_status;

	public Orm_AllocateInfo() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_allocSheetNo = this.orm.getColumns().get(0);
		this.c_allocateInDate = this.orm.getColumns().get(1);
		this.c_allocateInPerson = this.orm.getColumns().get(2);
		this.c_allocateInPersonName = this.orm.getColumns().get(3);
		this.c_allocateInStoreId = this.orm.getColumns().get(4);
		this.c_allocateInStoreName = this.orm.getColumns().get(5);
		this.c_allocateOutDate = this.orm.getColumns().get(6);
		this.c_allocateOutPerson = this.orm.getColumns().get(7);
		this.c_allocateOutPersonName = this.orm.getColumns().get(8);
		this.c_allocateOutStoreId = this.orm.getColumns().get(9);
		this.c_allocateOutStoreName = this.orm.getColumns().get(10);
		this.c_allocateReason = this.orm.getColumns().get(11);
		this.c_applyDate = this.orm.getColumns().get(12);
		this.c_approveDate = this.orm.getColumns().get(13);
		this.c_approvePerson = this.orm.getColumns().get(14);
		this.c_approvePersonId = this.orm.getColumns().get(15);
		this.c_createDate = this.orm.getColumns().get(16);
		this.c_creator = this.orm.getColumns().get(17);
		this.c_creatorId = this.orm.getColumns().get(18);
		this.c_creatorPY = this.orm.getColumns().get(19);
		this.c_deptId = this.orm.getColumns().get(20);
		this.c_rejectReason = this.orm.getColumns().get(21);
		this.c_id = this.orm.getColumns().get(22);
		this.c_status = this.orm.getColumns().get(23);
	}
}
