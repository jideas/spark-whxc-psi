package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_DealingItem extends ORMDeclarator<com.spark.psi.account.intf.entity.dealing.DealingItem> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_balance;
	public final QueryColumnDefine c_billsId;
	public final QueryColumnDefine c_billsNo;
	public final QueryColumnDefine c_billsType;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_partnerId;
	public final QueryColumnDefine c_planAmount;
	public final QueryColumnDefine c_realAmount;
	public final QueryColumnDefine c_receiptType;
	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_accountBillsId;
	public final QueryColumnDefine c_accountBillsNo;

	public Orm_DealingItem() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_balance = this.orm.getColumns().get(0);
		this.c_billsId = this.orm.getColumns().get(1);
		this.c_billsNo = this.orm.getColumns().get(2);
		this.c_billsType = this.orm.getColumns().get(3);
		this.c_createDate = this.orm.getColumns().get(4);
		this.c_partnerId = this.orm.getColumns().get(5);
		this.c_planAmount = this.orm.getColumns().get(6);
		this.c_realAmount = this.orm.getColumns().get(7);
		this.c_receiptType = this.orm.getColumns().get(8);
		this.c_id = this.orm.getColumns().get(9);
		this.c_remark = this.orm.getColumns().get(10);
		this.c_accountBillsId = this.orm.getColumns().get(11);
		this.c_accountBillsNo = this.orm.getColumns().get(12);
	}
}
