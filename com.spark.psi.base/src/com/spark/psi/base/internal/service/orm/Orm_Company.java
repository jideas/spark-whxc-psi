package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Company extends ORMDeclarator<com.spark.psi.base.internal.entity.ormentity.CompanyOrmEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_address;
	public final QueryColumnDefine c_district;
	public final QueryColumnDefine c_city;
	public final QueryColumnDefine c_companyName;
	public final QueryColumnDefine c_companyShortName;
	public final QueryColumnDefine c_faxNumber;
	public final QueryColumnDefine c_landLineNumber;
	public final QueryColumnDefine c_postcode;
	public final QueryColumnDefine c_province;
	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_systemName;
	public final QueryColumnDefine c_logo;

	public Orm_Company() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_address = this.orm.getColumns().get(0);
		this.c_district = this.orm.getColumns().get(1);
		this.c_city = this.orm.getColumns().get(2);
		this.c_companyName = this.orm.getColumns().get(3);
		this.c_companyShortName = this.orm.getColumns().get(4);
		this.c_faxNumber = this.orm.getColumns().get(5);
		this.c_landLineNumber = this.orm.getColumns().get(6);
		this.c_postcode = this.orm.getColumns().get(7);
		this.c_province = this.orm.getColumns().get(8);
		this.c_id = this.orm.getColumns().get(9);
		this.c_systemName = this.orm.getColumns().get(10);
		this.c_logo = this.orm.getColumns().get(11);
	}
}
