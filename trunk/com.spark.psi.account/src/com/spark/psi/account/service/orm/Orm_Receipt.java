package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Receipt extends ORMDeclarator<com.spark.psi.account.intf.entity.receipt.ReceiptEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_receiptsNo;
	public final QueryColumnDefine c_partnerName;
	public final QueryColumnDefine c_partnerId;
	public final QueryColumnDefine c_receiptMode;
	public final QueryColumnDefine c_reason;
	public final QueryColumnDefine c_receiptDate;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_amount;
	public final QueryColumnDefine c_receiptedAmount;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_receiptType;

	public Orm_Receipt() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_receiptsNo = this.orm.getColumns().get(1);
		this.c_partnerName = this.orm.getColumns().get(2);
		this.c_partnerId = this.orm.getColumns().get(3);
		this.c_receiptMode = this.orm.getColumns().get(4);
		this.c_reason = this.orm.getColumns().get(5);
		this.c_receiptDate = this.orm.getColumns().get(6);
		this.c_status = this.orm.getColumns().get(7);
		this.c_amount = this.orm.getColumns().get(8);
		this.c_receiptedAmount = this.orm.getColumns().get(9);
		this.c_remark = this.orm.getColumns().get(10);
		this.c_creatorId = this.orm.getColumns().get(11);
		this.c_creator = this.orm.getColumns().get(12);
		this.c_createDate = this.orm.getColumns().get(13);
		this.c_receiptType = this.orm.getColumns().get(14);
	}
}
