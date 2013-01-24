package com.spark.psi.inventory.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_ReportLoss extends ORMDeclarator<com.spark.psi.inventory.internal.entity.ReportLoss> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_applyDate;
	public final QueryColumnDefine c_approvalDate;
	public final QueryColumnDefine c_approvalPersonId;
	public final QueryColumnDefine c_approvalPersonName;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_rejectReason;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_reportType;
	public final QueryColumnDefine c_sheetNo;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_storeId;
	public final QueryColumnDefine c_storeName;

	public Orm_ReportLoss() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_applyDate = this.orm.getColumns().get(0);
		this.c_approvalDate = this.orm.getColumns().get(1);
		this.c_approvalPersonId = this.orm.getColumns().get(2);
		this.c_approvalPersonName = this.orm.getColumns().get(3);
		this.c_createDate = this.orm.getColumns().get(4);
		this.c_creator = this.orm.getColumns().get(5);
		this.c_creatorId = this.orm.getColumns().get(6);
		this.c_id = this.orm.getColumns().get(7);
		this.c_rejectReason = this.orm.getColumns().get(8);
		this.c_remark = this.orm.getColumns().get(9);
		this.c_reportType = this.orm.getColumns().get(10);
		this.c_sheetNo = this.orm.getColumns().get(11);
		this.c_status = this.orm.getColumns().get(12);
		this.c_storeId = this.orm.getColumns().get(13);
		this.c_storeName = this.orm.getColumns().get(14);
	}
}
