package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_ReceiptRecordAll extends ORMDeclarator<com.spark.psi.account.intf.entity.receipt.ReceiptEntity> {

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_affirmDate;
	public final QueryColumnDefine c_affirmPerson;
	public final QueryColumnDefine c_deptGuid;
	public final QueryColumnDefine c_receiptDate;
	public final QueryColumnDefine c_receiptMoney;
	public final QueryColumnDefine c_receiptObject;
	public final QueryColumnDefine c_receiptObjectShort;
	public final QueryColumnDefine c_receiptObjectGuid;
	public final QueryColumnDefine c_receiptType;
	public final QueryColumnDefine c_receiptWay;
	public final QueryColumnDefine c_receiptTypeName;
	public final QueryColumnDefine c_receiptWayName;
	public final QueryColumnDefine c_receiver;
	public final QueryColumnDefine c_receiverGuid;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_tenantsGuid;
	public final QueryColumnDefine c_receiptObjectPY;
	public final QueryColumnDefine c_receiptObjectShortPY;
	public final QueryColumnDefine c_receiptTypeNamePY;
	public final QueryColumnDefine c_receiptWayNamePY;
	public final QueryColumnDefine c_receiverPY;
	public final QueryColumnDefine c_affirmPersonPY;
	public final QueryColumnDefine c_shouldCardCount;
	public final QueryColumnDefine c_shouldCardMoney;
	public final QueryColumnDefine c_retailGuid;
	public final QueryColumnDefine c_isRetail;
	public final QueryColumnDefine c_isSureReceipt;
	public final QueryColumnDefine c_isRetailDataByPay;
	public final QueryColumnDefine c_receiptBillNo;
	public final QueryColumnDefine c_quarter;
	public final QueryColumnDefine c_remonth;

	public Orm_ReceiptRecordAll() {
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_affirmDate = this.orm.getColumns().get(1);
		this.c_affirmPerson = this.orm.getColumns().get(2);
		this.c_deptGuid = this.orm.getColumns().get(3);
		this.c_receiptDate = this.orm.getColumns().get(4);
		this.c_receiptMoney = this.orm.getColumns().get(5);
		this.c_receiptObject = this.orm.getColumns().get(6);
		this.c_receiptObjectShort = this.orm.getColumns().get(7);
		this.c_receiptObjectGuid = this.orm.getColumns().get(8);
		this.c_receiptType = this.orm.getColumns().get(9);
		this.c_receiptWay = this.orm.getColumns().get(10);
		this.c_receiptTypeName = this.orm.getColumns().get(11);
		this.c_receiptWayName = this.orm.getColumns().get(12);
		this.c_receiver = this.orm.getColumns().get(13);
		this.c_receiverGuid = this.orm.getColumns().get(14);
		this.c_remark = this.orm.getColumns().get(15);
		this.c_tenantsGuid = this.orm.getColumns().get(16);
		this.c_receiptObjectPY = this.orm.getColumns().get(17);
		this.c_receiptObjectShortPY = this.orm.getColumns().get(18);
		this.c_receiptTypeNamePY = this.orm.getColumns().get(19);
		this.c_receiptWayNamePY = this.orm.getColumns().get(20);
		this.c_receiverPY = this.orm.getColumns().get(21);
		this.c_affirmPersonPY = this.orm.getColumns().get(22);
		this.c_shouldCardCount = this.orm.getColumns().get(23);
		this.c_shouldCardMoney = this.orm.getColumns().get(24);
		this.c_retailGuid = this.orm.getColumns().get(25);
		this.c_isRetail = this.orm.getColumns().get(26);
		this.c_isSureReceipt = this.orm.getColumns().get(27);
		this.c_isRetailDataByPay = this.orm.getColumns().get(28);
		this.c_receiptBillNo = this.orm.getColumns().get(29);
		this.c_quarter = this.orm.getColumns().get(30);
		this.c_remonth = this.orm.getColumns().get(31);
	}
}
