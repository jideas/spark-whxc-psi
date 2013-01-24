package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.ObjectQuerier;
import com.jiuqi.dna.core.def.arg.ArgumentDefine;
import com.jiuqi.dna.core.def.query.QueryColumnDefine;
import com.jiuqi.dna.core.def.query.ORMDeclarator;

public class Orm_JointVentureLog extends ORMDeclarator<com.spark.psi.base.internal.entity.ormentity.JointVentureLogOrmEntity> {

	public final ArgumentDefine arg_id;

	public final QueryColumnDefine c_id;
	public final QueryColumnDefine c_beginDate;
	public final QueryColumnDefine c_endDate;
	public final QueryColumnDefine c_materialCode;
	public final QueryColumnDefine c_materialId;
	public final QueryColumnDefine c_materialName;
	public final QueryColumnDefine c_materialNo;
	public final QueryColumnDefine c_materialUnit;
	public final QueryColumnDefine c_percentage;
	public final QueryColumnDefine c_supplierId;

	public Orm_JointVentureLog() {
		this.arg_id = this.orm.getArguments().get(0);
		this.c_id = this.orm.getColumns().get(0);
		this.c_beginDate = this.orm.getColumns().get(1);
		this.c_endDate = this.orm.getColumns().get(2);
		this.c_materialCode = this.orm.getColumns().get(3);
		this.c_materialId = this.orm.getColumns().get(4);
		this.c_materialName = this.orm.getColumns().get(5);
		this.c_materialNo = this.orm.getColumns().get(6);
		this.c_materialUnit = this.orm.getColumns().get(7);
		this.c_percentage = this.orm.getColumns().get(8);
		this.c_supplierId = this.orm.getColumns().get(9);
	}
}
