package com.spark.psi.account.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_PayRecordAll extends ORMDeclarator<com.spark.psi.account.intf.entity.pay.PaymentEntity> {

	public final QueryColumnDefine c_RECID;
	public final QueryColumnDefine c_affirmDate;
	public final QueryColumnDefine c_deptGuid;
	public final QueryColumnDefine c_inStoragePerson;
	public final QueryColumnDefine c_payDate;
	public final QueryColumnDefine c_payMoney;
	public final QueryColumnDefine c_payObject;
	public final QueryColumnDefine c_payObjectShort;
	public final QueryColumnDefine c_payObjectGuid;
	public final QueryColumnDefine c_payType;
	public final QueryColumnDefine c_payWay;
	public final QueryColumnDefine c_payTypeName;
	public final QueryColumnDefine c_payWayName;
	public final QueryColumnDefine c_payer;
	public final QueryColumnDefine c_payerGuid;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_tenantsGuid;
	public final QueryColumnDefine c_payObjectPY;
	public final QueryColumnDefine c_payObjectShortPY;
	public final QueryColumnDefine c_payTypeNamePY;
	public final QueryColumnDefine c_payWayNamePY;
	public final QueryColumnDefine c_payerPY;
	public final QueryColumnDefine c_inStoragePersonPY;
	public final QueryColumnDefine c_isSureReturn;
	public final QueryColumnDefine c_payBillNo;
	public final QueryColumnDefine c_quarter;
	public final QueryColumnDefine c_remonth;

	public Orm_PayRecordAll() {
		this.c_RECID = this.orm.getColumns().get(0);
		this.c_affirmDate = this.orm.getColumns().get(1);
		this.c_deptGuid = this.orm.getColumns().get(2);
		this.c_inStoragePerson = this.orm.getColumns().get(3);
		this.c_payDate = this.orm.getColumns().get(4);
		this.c_payMoney = this.orm.getColumns().get(5);
		this.c_payObject = this.orm.getColumns().get(6);
		this.c_payObjectShort = this.orm.getColumns().get(7);
		this.c_payObjectGuid = this.orm.getColumns().get(8);
		this.c_payType = this.orm.getColumns().get(9);
		this.c_payWay = this.orm.getColumns().get(10);
		this.c_payTypeName = this.orm.getColumns().get(11);
		this.c_payWayName = this.orm.getColumns().get(12);
		this.c_payer = this.orm.getColumns().get(13);
		this.c_payerGuid = this.orm.getColumns().get(14);
		this.c_remark = this.orm.getColumns().get(15);
		this.c_tenantsGuid = this.orm.getColumns().get(16);
		this.c_payObjectPY = this.orm.getColumns().get(17);
		this.c_payObjectShortPY = this.orm.getColumns().get(18);
		this.c_payTypeNamePY = this.orm.getColumns().get(19);
		this.c_payWayNamePY = this.orm.getColumns().get(20);
		this.c_payerPY = this.orm.getColumns().get(21);
		this.c_inStoragePersonPY = this.orm.getColumns().get(22);
		this.c_isSureReturn = this.orm.getColumns().get(23);
		this.c_payBillNo = this.orm.getColumns().get(24);
		this.c_quarter = this.orm.getColumns().get(25);
		this.c_remonth = this.orm.getColumns().get(26);
	}
}
