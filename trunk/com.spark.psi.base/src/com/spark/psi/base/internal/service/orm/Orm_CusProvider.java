package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_CusProvider extends ORMDeclarator<com.spark.psi.base.internal.entity.ormentity.PartnerOrmEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_tenantId;
	public final QueryColumnDefine c_shortName;
	public final QueryColumnDefine c_name;
	public final QueryColumnDefine c_partnerType;
	public final QueryColumnDefine c_developType;
	public final QueryColumnDefine c_industyType;
	public final QueryColumnDefine c_scaleType;
	public final QueryColumnDefine c_bankAccountNumber;
	public final QueryColumnDefine c_bankName;
	public final QueryColumnDefine c_bankAccountName;
	public final QueryColumnDefine c_creditAmount;
	public final QueryColumnDefine c_creditAmountUsed;
	public final QueryColumnDefine c_workPhoneNumber;
	public final QueryColumnDefine c_faxNumber;
	public final QueryColumnDefine c_province;
	public final QueryColumnDefine c_city;
	public final QueryColumnDefine c_address;
	public final QueryColumnDefine c_taxationNumber;
	public final QueryColumnDefine c_website;
	public final QueryColumnDefine c_memo;
	public final QueryColumnDefine c_postCode;
	public final QueryColumnDefine c_creditDay;
	public final QueryColumnDefine c_warnningDay;
	public final QueryColumnDefine c_createPerson;
	public final QueryColumnDefine c_createDate;

	public Orm_CusProvider() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_tenantId = this.orm.getColumns().get(1);
		this.c_shortName = this.orm.getColumns().get(2);
		this.c_name = this.orm.getColumns().get(3);
		this.c_partnerType = this.orm.getColumns().get(4);
		this.c_developType = this.orm.getColumns().get(5);
		this.c_industyType = this.orm.getColumns().get(6);
		this.c_scaleType = this.orm.getColumns().get(7);
		this.c_bankAccountNumber = this.orm.getColumns().get(8);
		this.c_bankName = this.orm.getColumns().get(9);
		this.c_bankAccountName = this.orm.getColumns().get(10);
		this.c_creditAmount = this.orm.getColumns().get(11);
		this.c_creditAmountUsed = this.orm.getColumns().get(12);
		this.c_workPhoneNumber = this.orm.getColumns().get(13);
		this.c_faxNumber = this.orm.getColumns().get(14);
		this.c_province = this.orm.getColumns().get(15);
		this.c_city = this.orm.getColumns().get(16);
		this.c_address = this.orm.getColumns().get(17);
		this.c_taxationNumber = this.orm.getColumns().get(18);
		this.c_website = this.orm.getColumns().get(19);
		this.c_memo = this.orm.getColumns().get(20);
		this.c_postCode = this.orm.getColumns().get(21);
		this.c_creditDay = this.orm.getColumns().get(22);
		this.c_warnningDay = this.orm.getColumns().get(23);
		this.c_createPerson = this.orm.getColumns().get(24);
		this.c_createDate = this.orm.getColumns().get(25);
	}
}
