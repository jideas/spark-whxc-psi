package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_PayMent extends ORMDeclarator<com.spark.psi.account.intf.entity.pay.PaymentEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_paymentNo;
	public final QueryColumnDefine c_partnerName;
	public final QueryColumnDefine c_partnerId;
	public final QueryColumnDefine c_paymentType;
	public final QueryColumnDefine c_denyReason;
	public final QueryColumnDefine c_payDate;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_amount;
	public final QueryColumnDefine c_paidAmount;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_approvePerson;
	public final QueryColumnDefine c_approvePersonName;
	public final QueryColumnDefine c_approveDate;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_dealingsWay;

	public Orm_PayMent() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_paymentNo = this.orm.getColumns().get(1);
		this.c_partnerName = this.orm.getColumns().get(2);
		this.c_partnerId = this.orm.getColumns().get(3);
		this.c_paymentType = this.orm.getColumns().get(4);
		this.c_denyReason = this.orm.getColumns().get(5);
		this.c_payDate = this.orm.getColumns().get(6);
		this.c_status = this.orm.getColumns().get(7);
		this.c_amount = this.orm.getColumns().get(8);
		this.c_paidAmount = this.orm.getColumns().get(9);
		this.c_remark = this.orm.getColumns().get(10);
		this.c_approvePerson = this.orm.getColumns().get(11);
		this.c_approvePersonName = this.orm.getColumns().get(12);
		this.c_approveDate = this.orm.getColumns().get(13);
		this.c_creatorId = this.orm.getColumns().get(14);
		this.c_creator = this.orm.getColumns().get(15);
		this.c_createDate = this.orm.getColumns().get(16);
		this.c_dealingsWay = this.orm.getColumns().get(17);
	}
}
