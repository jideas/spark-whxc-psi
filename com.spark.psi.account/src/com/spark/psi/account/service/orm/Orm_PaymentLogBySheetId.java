package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_PaymentLogBySheetId extends ORMDeclarator<com.spark.psi.account.intf.entity.pay.PaymentLogEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_amount;
	public final QueryColumnDefine c_checkinDate;
	public final QueryColumnDefine c_checkinSheetId;
	public final QueryColumnDefine c_molingAmount;
	public final QueryColumnDefine c_payDate;
	public final QueryColumnDefine c_payPersonId;
	public final QueryColumnDefine c_payPersonName;
	public final QueryColumnDefine c_paymentId;
	public final QueryColumnDefine c_paymentNo;
	public final QueryColumnDefine c_relevantBillId;
	public final QueryColumnDefine c_relevantBillNo;
	public final QueryColumnDefine c_sheetNo;

	public Orm_PaymentLogBySheetId() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_amount = this.orm.getColumns().get(1);
		this.c_checkinDate = this.orm.getColumns().get(2);
		this.c_checkinSheetId = this.orm.getColumns().get(3);
		this.c_molingAmount = this.orm.getColumns().get(4);
		this.c_payDate = this.orm.getColumns().get(5);
		this.c_payPersonId = this.orm.getColumns().get(6);
		this.c_payPersonName = this.orm.getColumns().get(7);
		this.c_paymentId = this.orm.getColumns().get(8);
		this.c_paymentNo = this.orm.getColumns().get(9);
		this.c_relevantBillId = this.orm.getColumns().get(10);
		this.c_relevantBillNo = this.orm.getColumns().get(11);
		this.c_sheetNo = this.orm.getColumns().get(12);
	}
}
