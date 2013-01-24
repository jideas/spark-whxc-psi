package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_JointSettlement extends ORMDeclarator<com.spark.psi.account.intf.entity.jointSettlement.JointSettlementEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_adjustAmount;
	public final QueryColumnDefine c_amount;
	public final QueryColumnDefine c_beginDate;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_endDate;
	public final QueryColumnDefine c_molingAmount;
	public final QueryColumnDefine c_namePY;
	public final QueryColumnDefine c_paidAmount;
	public final QueryColumnDefine c_percentageAmount;
	public final QueryColumnDefine c_salesAmount;
	public final QueryColumnDefine c_sheetNo;
	public final QueryColumnDefine c_shortName;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_supplierId;
	public final QueryColumnDefine c_supplierName;
	public final QueryColumnDefine c_supplierNo;
	public final QueryColumnDefine c_recordIds;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_denyReason;

	public Orm_JointSettlement() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_adjustAmount = this.orm.getColumns().get(1);
		this.c_amount = this.orm.getColumns().get(2);
		this.c_beginDate = this.orm.getColumns().get(3);
		this.c_createDate = this.orm.getColumns().get(4);
		this.c_creator = this.orm.getColumns().get(5);
		this.c_creatorId = this.orm.getColumns().get(6);
		this.c_endDate = this.orm.getColumns().get(7);
		this.c_molingAmount = this.orm.getColumns().get(8);
		this.c_namePY = this.orm.getColumns().get(9);
		this.c_paidAmount = this.orm.getColumns().get(10);
		this.c_percentageAmount = this.orm.getColumns().get(11);
		this.c_salesAmount = this.orm.getColumns().get(12);
		this.c_sheetNo = this.orm.getColumns().get(13);
		this.c_shortName = this.orm.getColumns().get(14);
		this.c_status = this.orm.getColumns().get(15);
		this.c_supplierId = this.orm.getColumns().get(16);
		this.c_supplierName = this.orm.getColumns().get(17);
		this.c_supplierNo = this.orm.getColumns().get(18);
		this.c_recordIds = this.orm.getColumns().get(19);
		this.c_remark = this.orm.getColumns().get(20);
		this.c_denyReason = this.orm.getColumns().get(21);
	}
}
