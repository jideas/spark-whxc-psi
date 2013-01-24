package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuTableRefDeclare;
import com.spark.psi.base.internal.entity.ormentity.ApprovalRecordOrmEntity;
import com.spark.psi.base.storage.tenant.TB_SA_EXAM_LIST;

public class Orm_ApprovalRecord extends ORMDeclarator<ApprovalRecordOrmEntity>{

	public static final String NAME = "Orm_ApprovalRecord";

	protected QuTableRefDeclare tableRefDeclare;

	public Orm_ApprovalRecord(TB_SA_EXAM_LIST table){
		super(NAME);
		tableRefDeclare = this.orm.newReference(table);
		this.orm.newColumn(table.f_amount, "amount");
		this.orm.newColumn(table.f_billsNo, "billsNumber");
		this.orm.newColumn(table.f_RECID, "id");
		this.orm.newColumn(table.f_busType, "busType");
		this.orm.newColumn(table.f_busTypeName, "busTypeName");
		this.orm.newColumn(table.f_createDate, "createDate");
		this.orm.newColumn(table.f_createPerson, "createPerson");
		this.orm.newColumn(table.f_examDate, "examDate");
		this.orm.newColumn(table.f_status, "status");
		this.orm.newColumn(table.f_tenantsGuid, "tenantId");
		this.orm.newColumn(table.f_userGuid, "userId");
		this.orm.setCondition(tableRefDeclare.expOf(table.f_tenantsGuid).xEq(
		        this.orm.newArgument(table.f_tenantsGuid)));
	}

}
