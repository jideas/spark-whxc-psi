package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_RetailReceipt extends ORMDeclarator<com.spark.psi.account.intf.entity.receipt.RetailReceipt> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_deptGuid;
	public final QueryColumnDefine c_receiptDate;
	public final QueryColumnDefine c_retailGuid;
	public final QueryColumnDefine c_saleEmpGuid;
	public final QueryColumnDefine c_saleEmpName;
	public final QueryColumnDefine c_shouldCardCount;
	public final QueryColumnDefine c_shouldCardMoney;
	public final QueryColumnDefine c_shouldMoney;
	public final QueryColumnDefine c_tenantsGuid;

	public Orm_RetailReceipt() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_deptGuid = this.orm.getColumns().get(1);
		this.c_receiptDate = this.orm.getColumns().get(2);
		this.c_retailGuid = this.orm.getColumns().get(3);
		this.c_saleEmpGuid = this.orm.getColumns().get(4);
		this.c_saleEmpName = this.orm.getColumns().get(5);
		this.c_shouldCardCount = this.orm.getColumns().get(6);
		this.c_shouldCardMoney = this.orm.getColumns().get(7);
		this.c_shouldMoney = this.orm.getColumns().get(8);
		this.c_tenantsGuid = this.orm.getColumns().get(9);
	}
}
