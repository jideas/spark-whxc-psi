package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.psi.base.internal.entity.SerialNumberEntity;
import com.spark.psi.base.storage.base.TB_SA_SerialNumber;

public class Orm_SerialNumber extends ORMDeclarator<SerialNumberEntity> {

	protected QuRelationRefDeclare tableReference;
	
	public Orm_SerialNumber(TB_SA_SerialNumber tbSerialNumber) {
		this(tbSerialNumber, "Orm_SerialNumber");
	}

	public Orm_SerialNumber(TB_SA_SerialNumber tbSerialNumber,String name) {
		super(name);
		tableReference = this.orm.newReference(tbSerialNumber);
		this.orm.newColumn(tbSerialNumber.f_id, "id"); 
		this.orm.newColumn(tbSerialNumber.f_type, "type");
		this.orm.newColumn(tbSerialNumber.f_prefix, "prefix");
		this.orm.newColumn(tbSerialNumber.f_serial, "serial");
		this.orm.newColumn(tbSerialNumber.f_createTime, "createTime");
	}

}
