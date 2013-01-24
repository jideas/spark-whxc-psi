package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_ReceiptItemBySheetId extends ORMDeclarator<com.spark.psi.account.intf.entity.receipt.ReceiptItemEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_amount;
	public final QueryColumnDefine c_checkoutDate;
	public final QueryColumnDefine c_checkoutSheetId;
	public final QueryColumnDefine c_molingAmount;
	public final QueryColumnDefine c_receiptedAmount;
	public final QueryColumnDefine c_receiptsId;
	public final QueryColumnDefine c_relevantBillId;
	public final QueryColumnDefine c_relevantBillNo;
	public final QueryColumnDefine c_sheetNo;

	public Orm_ReceiptItemBySheetId() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_amount = this.orm.getColumns().get(1);
		this.c_checkoutDate = this.orm.getColumns().get(2);
		this.c_checkoutSheetId = this.orm.getColumns().get(3);
		this.c_molingAmount = this.orm.getColumns().get(4);
		this.c_receiptedAmount = this.orm.getColumns().get(5);
		this.c_receiptsId = this.orm.getColumns().get(6);
		this.c_relevantBillId = this.orm.getColumns().get(7);
		this.c_relevantBillNo = this.orm.getColumns().get(8);
		this.c_sheetNo = this.orm.getColumns().get(9);
	}
}
