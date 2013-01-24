package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.psi.base.internal.entity.ormentity.SalesManCreditOrmEntity;
import com.spark.psi.base.storage.tenant.TB_SA_SALE_CREDIT;

public class Orm_SalesManCredit extends ORMDeclarator<SalesManCreditOrmEntity>{

	public static final String NAME = "ORM_SALESMANCREDIT";

	protected QuRelationRefDeclare tableReference;

	public Orm_SalesManCredit(TB_SA_SALE_CREDIT table){
		this(table,NAME);
	}
	
	public Orm_SalesManCredit(TB_SA_SALE_CREDIT table,String Name){
		super(Name);
		this.tableReference = this.orm.newReference(table);
		this.orm.newColumn(table.f_RECID, "id");
		this.orm.newColumn(table.f_tenantsGUID, "tenantId");
		this.orm.newColumn(table.f_customerCreditLimit, "customerCreditLimit");
		this.orm.newColumn(table.f_customerCreditDayLimit,
		"customerCreditDayLimit");
		this.orm.newColumn(table.f_availableTotalCreditLimit,
		"availableTotalCreditLimit");
		this.orm.newColumn(table.f_customerCountUsed, "customerCountUsed");
		this.orm.newColumn(table.f_customerCreditUsed, "customerCreditUsed");
		this.orm.newColumn(table.f_orderApprovalLimit, "orderApprovalLimit");
    }
}
