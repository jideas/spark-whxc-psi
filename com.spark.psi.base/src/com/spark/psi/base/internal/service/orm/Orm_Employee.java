package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_Employee extends ORMDeclarator<com.spark.psi.base.internal.entity.ormentity.EmployeeOrmEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_birthday;
	public final QueryColumnDefine c_createDate;
	public final QueryColumnDefine c_createPerson;
	public final QueryColumnDefine c_departmentId;
	public final QueryColumnDefine c_position;
	public final QueryColumnDefine c_email;
	public final QueryColumnDefine c_logo;
	public final QueryColumnDefine c_name;
	public final QueryColumnDefine c_idNumber;
	public final QueryColumnDefine c_mobileNumber;
	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_tenantId;
	public final QueryColumnDefine c_empState;
	public final QueryColumnDefine c_roles;
	public final QueryColumnDefine c_landLineNumber;
	public final QueryColumnDefine c_style;
	public final QueryColumnDefine c_pyName;
	public final QueryColumnDefine c_pyDuty;

	public Orm_Employee() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_birthday = this.orm.getColumns().get(0);
		this.c_createDate = this.orm.getColumns().get(1);
		this.c_createPerson = this.orm.getColumns().get(2);
		this.c_departmentId = this.orm.getColumns().get(3);
		this.c_position = this.orm.getColumns().get(4);
		this.c_email = this.orm.getColumns().get(5);
		this.c_logo = this.orm.getColumns().get(6);
		this.c_name = this.orm.getColumns().get(7);
		this.c_idNumber = this.orm.getColumns().get(8);
		this.c_mobileNumber = this.orm.getColumns().get(9);
		this.c_id = this.orm.getColumns().get(10);
		this.c_tenantId = this.orm.getColumns().get(11);
		this.c_empState = this.orm.getColumns().get(12);
		this.c_roles = this.orm.getColumns().get(13);
		this.c_landLineNumber = this.orm.getColumns().get(14);
		this.c_style = this.orm.getColumns().get(15);
		this.c_pyName = this.orm.getColumns().get(16);
		this.c_pyDuty = this.orm.getColumns().get(17);
	}
}
