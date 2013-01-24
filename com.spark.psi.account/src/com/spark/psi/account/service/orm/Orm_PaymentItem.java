package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_PaymentItem extends ORMDeclarator<com.spark.psi.account.intf.entity.pay.PaymentItemEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_amount;
	public final QueryColumnDefine c_askAmount;
	public final QueryColumnDefine c_checkinDate;
	public final QueryColumnDefine c_checkinSheetId;
	public final QueryColumnDefine c_molingAmount;
	public final QueryColumnDefine c_paidAmount;
	public final QueryColumnDefine c_payingAmount;
	public final QueryColumnDefine c_paymentId;
	public final QueryColumnDefine c_relevantBillId;
	public final QueryColumnDefine c_relevantBillNo;
	public final QueryColumnDefine c_sheetNo;

	public Orm_PaymentItem() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_amount = this.orm.getColumns().get(1);
		this.c_askAmount = this.orm.getColumns().get(2);
		this.c_checkinDate = this.orm.getColumns().get(3);
		this.c_checkinSheetId = this.orm.getColumns().get(4);
		this.c_molingAmount = this.orm.getColumns().get(5);
		this.c_paidAmount = this.orm.getColumns().get(6);
		this.c_payingAmount = this.orm.getColumns().get(7);
		this.c_paymentId = this.orm.getColumns().get(8);
		this.c_relevantBillId = this.orm.getColumns().get(9);
		this.c_relevantBillNo = this.orm.getColumns().get(10);
		this.c_sheetNo = this.orm.getColumns().get(11);
	}
}
