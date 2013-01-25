package com.spark.psi.order.storage.sales;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_SalesReturn2 extends ORMDeclarator<com.spark.order.sales2.SalesReturnInfo2> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_deliveryDate;
	public final QueryColumnDefine c_contactId;
	public final QueryColumnDefine c_contactName;
	public final QueryColumnDefine c_contactPhone;
	public final QueryColumnDefine c_contactTel;
	public final QueryColumnDefine c_defEight;
	public final QueryColumnDefine c_defFive;
	public final QueryColumnDefine c_defFour;
	public final QueryColumnDefine c_defNine;
	public final QueryColumnDefine c_defOne;
	public final QueryColumnDefine c_defSeven;
	public final QueryColumnDefine c_defSix;
	public final QueryColumnDefine c_defTen;
	public final QueryColumnDefine c_defThree;
	public final QueryColumnDefine c_defTwo;
	public final QueryColumnDefine c_deptId;
	public final QueryColumnDefine c_effectiveDate;
	public final QueryColumnDefine c_examin;
	public final QueryColumnDefine c_isStoped;
	public final QueryColumnDefine c_orderNumber;
	public final QueryColumnDefine c_partnerFax;
	public final QueryColumnDefine c_partnerId;
	public final QueryColumnDefine c_partnerName;
	public final QueryColumnDefine c_partnerNamePY;
	public final QueryColumnDefine c_partnerShortName;
	public final QueryColumnDefine c_remark;
	public final QueryColumnDefine c_returnCause;
	public final QueryColumnDefine c_status;
	public final QueryColumnDefine c_totalAmount;
	public final QueryColumnDefine c_type;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_creator;
	public final QueryColumnDefine c_creatorId;
	public final QueryColumnDefine c_recid;
	public final QueryColumnDefine c_tenantsId;

	public Orm_SalesReturn2() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_deliveryDate = this.orm.getColumns().get(0);
		this.c_contactId = this.orm.getColumns().get(1);
		this.c_contactName = this.orm.getColumns().get(2);
		this.c_contactPhone = this.orm.getColumns().get(3);
		this.c_contactTel = this.orm.getColumns().get(4);
		this.c_defEight = this.orm.getColumns().get(5);
		this.c_defFive = this.orm.getColumns().get(6);
		this.c_defFour = this.orm.getColumns().get(7);
		this.c_defNine = this.orm.getColumns().get(8);
		this.c_defOne = this.orm.getColumns().get(9);
		this.c_defSeven = this.orm.getColumns().get(10);
		this.c_defSix = this.orm.getColumns().get(11);
		this.c_defTen = this.orm.getColumns().get(12);
		this.c_defThree = this.orm.getColumns().get(13);
		this.c_defTwo = this.orm.getColumns().get(14);
		this.c_deptId = this.orm.getColumns().get(15);
		this.c_effectiveDate = this.orm.getColumns().get(16);
		this.c_examin = this.orm.getColumns().get(17);
		this.c_isStoped = this.orm.getColumns().get(18);
		this.c_orderNumber = this.orm.getColumns().get(19);
		this.c_partnerFax = this.orm.getColumns().get(20);
		this.c_partnerId = this.orm.getColumns().get(21);
		this.c_partnerName = this.orm.getColumns().get(22);
		this.c_partnerNamePY = this.orm.getColumns().get(23);
		this.c_partnerShortName = this.orm.getColumns().get(24);
		this.c_remark = this.orm.getColumns().get(25);
		this.c_returnCause = this.orm.getColumns().get(26);
		this.c_status = this.orm.getColumns().get(27);
		this.c_totalAmount = this.orm.getColumns().get(28);
		this.c_type = this.orm.getColumns().get(29);
		this.c_createDate = this.orm.getColumns().get(30);
		this.c_creator = this.orm.getColumns().get(31);
		this.c_creatorId = this.orm.getColumns().get(32);
		this.c_recid = this.orm.getColumns().get(33);
		this.c_tenantsId = this.orm.getColumns().get(34);
	}
}
