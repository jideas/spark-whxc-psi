package com.spark.psi.base.internal.service.orm;

import com.spark.psi.base.storage.base.TB_SA_SerialNumber;

public class Orm_SerialNumberByTenantAndType extends Orm_SerialNumber {

	public Orm_SerialNumberByTenantAndType(TB_SA_SerialNumber tbSerialNumber) {
		super(tbSerialNumber, "Orm_SerialNumberByTenantAndType");
		this.orm.setCondition( tableReference.expOf(tbSerialNumber.f_type).xEq(
						this.orm.newArgument(tbSerialNumber.f_type)));
	}

}
