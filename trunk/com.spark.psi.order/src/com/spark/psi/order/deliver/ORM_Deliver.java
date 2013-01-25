package com.spark.psi.order.deliver;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_Deliver extends ORMDeclarator<com.spark.deliver.intf.entity.DeliverEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_address;
	public final QueryColumnDefine c_consignee;
	public final QueryColumnDefine c_consigneeDate;
	public final QueryColumnDefine c_consigneeId;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_deliverDate;
	public final QueryColumnDefine c_deliverPerson;
	public final QueryColumnDefine c_deliverPersonId;
	public final QueryColumnDefine c_deliveredPackageCount;
	public final QueryColumnDefine c_description;
	public final QueryColumnDefine c_finishDate;
	public final QueryColumnDefine c_formula;
	public final QueryColumnDefine c_handleDate;
	public final QueryColumnDefine c_handler;
	public final QueryColumnDefine c_handlerId;
	public final QueryColumnDefine c_receivedPackageCount;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_sheetNo;
	public final QueryColumnDefine c_stationId;
	public final QueryColumnDefine c_stationName;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_planDate;

	public ORM_Deliver() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_address = this.orm.getColumns().get(1);
		this.c_consignee = this.orm.getColumns().get(2);
		this.c_consigneeDate = this.orm.getColumns().get(3);
		this.c_consigneeId = this.orm.getColumns().get(4);
		this.c_createDate = this.orm.getColumns().get(5);
		this.c_creator = this.orm.getColumns().get(6);
		this.c_creatorId = this.orm.getColumns().get(7);
		this.c_deliverDate = this.orm.getColumns().get(8);
		this.c_deliverPerson = this.orm.getColumns().get(9);
		this.c_deliverPersonId = this.orm.getColumns().get(10);
		this.c_deliveredPackageCount = this.orm.getColumns().get(11);
		this.c_description = this.orm.getColumns().get(12);
		this.c_finishDate = this.orm.getColumns().get(13);
		this.c_formula = this.orm.getColumns().get(14);
		this.c_handleDate = this.orm.getColumns().get(15);
		this.c_handler = this.orm.getColumns().get(16);
		this.c_handlerId = this.orm.getColumns().get(17);
		this.c_receivedPackageCount = this.orm.getColumns().get(18);
		this.c_remark = this.orm.getColumns().get(19);
		this.c_sheetNo = this.orm.getColumns().get(20);
		this.c_stationId = this.orm.getColumns().get(21);
		this.c_stationName = this.orm.getColumns().get(22);
		this.c_status = this.orm.getColumns().get(23);
		this.c_planDate = this.orm.getColumns().get(24);
	}
}
