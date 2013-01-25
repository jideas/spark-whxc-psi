package com.spark.psi.order.sales;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class ORM_SaleCancel extends ORMDeclarator<com.spark.order.sales.intf.entity.SaleCancel> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_linkman;
	public final QueryColumnDefine c_partnerId;
	public final QueryColumnDefine c_partnerName;
	public final QueryColumnDefine c_partnerShortName;
	public final QueryColumnDefine c_partnerNamePY;
	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_approveStr;
	public final QueryColumnDefine c_billsNo;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_deptId;
	public final QueryColumnDefine c_isStoped;
	public final QueryColumnDefine c_rejectReason;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_stopReason;
	public final QueryColumnDefine c_totalAmount;
	public final QueryColumnDefine c_billType;

	public ORM_SaleCancel() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_linkman = this.orm.getColumns().get(0);
		this.c_partnerId = this.orm.getColumns().get(1);
		this.c_partnerName = this.orm.getColumns().get(2);
		this.c_partnerShortName = this.orm.getColumns().get(3);
		this.c_partnerNamePY = this.orm.getColumns().get(4);
		this.c_RECID = this.orm.getColumns().get(5);
		this.c_approveStr = this.orm.getColumns().get(6);
		this.c_billsNo = this.orm.getColumns().get(7);
		this.c_createDate = this.orm.getColumns().get(8);
		this.c_creator = this.orm.getColumns().get(9);
		this.c_creatorId = this.orm.getColumns().get(10);
		this.c_deptId = this.orm.getColumns().get(11);
		this.c_isStoped = this.orm.getColumns().get(12);
		this.c_rejectReason = this.orm.getColumns().get(13);
		this.c_remark = this.orm.getColumns().get(14);
		this.c_status = this.orm.getColumns().get(15);
		this.c_stopReason = this.orm.getColumns().get(16);
		this.c_totalAmount = this.orm.getColumns().get(17);
		this.c_billType = this.orm.getColumns().get(18);
	}
}
