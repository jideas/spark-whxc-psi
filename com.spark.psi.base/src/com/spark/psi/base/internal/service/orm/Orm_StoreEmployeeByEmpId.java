package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.psi.base.internal.entity.StoreEmployee;
import com.spark.psi.base.storage.store.TB_SA_STORE_EMPLOYEE;

public class Orm_StoreEmployeeByEmpId extends ORMDeclarator<StoreEmployee>{

	private static final String Name = "Orm_StoreEmployeeByEmpId";

	protected QuRelationRefDeclare tableRefdeclare;

	public Orm_StoreEmployeeByEmpId(TB_SA_STORE_EMPLOYEE table){
		super(Name);
		this.tableRefdeclare = this.orm.newReference(table);
		this.orm.newColumn(table.f_employeeGuid, "employeeGuid");
		this.orm.newColumn(table.f_RECID, "recid");
		this.orm.newColumn(table.f_employeeType, "employeeType");
		this.orm.newColumn(table.f_storeGuid, "storeGuid");
		this.orm.newColumn(table.f_tenantsGuid, "tenantId");
		this.orm.setCondition(tableRefdeclare.expOf(table.f_employeeGuid).xEq(
		        orm.newArgument(table.f_employeeGuid)).and(
		        tableRefdeclare.expOf(table.f_employeeType).xEq(
		                orm.newArgument(table.f_employeeType))));
	}
}
