package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Contack extends ORMDeclarator<com.spark.psi.base.internal.entity.ormentity.ContactOrmEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_tenantId;
	public final QueryColumnDefine c_name;
	public final QueryColumnDefine c_namePy;
	public final QueryColumnDefine c_main;
	public final QueryColumnDefine c_sexCode;
	public final QueryColumnDefine c_nickName;
	public final QueryColumnDefine c_mobileNumber;
	public final QueryColumnDefine c_landLineNumber;
	public final QueryColumnDefine c_email;
	public final QueryColumnDefine c_companyName;
	public final QueryColumnDefine c_companyPy;
	public final QueryColumnDefine c_department;
	public final QueryColumnDefine c_position;
	public final QueryColumnDefine c_postionPy;
	public final QueryColumnDefine c_qqNumber;
	public final QueryColumnDefine c_msnNumber;
	public final QueryColumnDefine c_wwNumber;
	public final QueryColumnDefine c_birth;
	public final QueryColumnDefine c_hobbies;
	public final QueryColumnDefine c_memo;
	public final QueryColumnDefine c_type;
	public final QueryColumnDefine c_createPersonId;
	public final QueryColumnDefine c_creaetPerson;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_partnerId;
	public final QueryColumnDefine c_province;
	public final QueryColumnDefine c_city;
	public final QueryColumnDefine c_district;
	public final QueryColumnDefine c_address;
	public final QueryColumnDefine c_postCode;
	public final QueryColumnDefine c_lastDate;

	public Orm_Contack() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_tenantId = this.orm.getColumns().get(1);
		this.c_name = this.orm.getColumns().get(2);
		this.c_namePy = this.orm.getColumns().get(3);
		this.c_main = this.orm.getColumns().get(4);
		this.c_sexCode = this.orm.getColumns().get(5);
		this.c_nickName = this.orm.getColumns().get(6);
		this.c_mobileNumber = this.orm.getColumns().get(7);
		this.c_landLineNumber = this.orm.getColumns().get(8);
		this.c_email = this.orm.getColumns().get(9);
		this.c_companyName = this.orm.getColumns().get(10);
		this.c_companyPy = this.orm.getColumns().get(11);
		this.c_department = this.orm.getColumns().get(12);
		this.c_position = this.orm.getColumns().get(13);
		this.c_postionPy = this.orm.getColumns().get(14);
		this.c_qqNumber = this.orm.getColumns().get(15);
		this.c_msnNumber = this.orm.getColumns().get(16);
		this.c_wwNumber = this.orm.getColumns().get(17);
		this.c_birth = this.orm.getColumns().get(18);
		this.c_hobbies = this.orm.getColumns().get(19);
		this.c_memo = this.orm.getColumns().get(20);
		this.c_type = this.orm.getColumns().get(21);
		this.c_createPersonId = this.orm.getColumns().get(22);
		this.c_creaetPerson = this.orm.getColumns().get(23);
		this.c_createDate = this.orm.getColumns().get(24);
		this.c_partnerId = this.orm.getColumns().get(25);
		this.c_province = this.orm.getColumns().get(26);
		this.c_city = this.orm.getColumns().get(27);
		this.c_district = this.orm.getColumns().get(28);
		this.c_address = this.orm.getColumns().get(29);
		this.c_postCode = this.orm.getColumns().get(30);
		this.c_lastDate = this.orm.getColumns().get(31);
	}
}
