package com.spark.psi.base.internal.service.orm;

import com.spark.psi.base.storage.tenant.TB_SA_SALE_CREDIT;


public class Orm_SalesManCreditByUserId extends Orm_SalesManCredit{

	public static final String NAME = "ORM_SALESMANCREDIT_BYID";
	
	public Orm_SalesManCreditByUserId(TB_SA_SALE_CREDIT table){
	    super(table,NAME);
		this.orm.setCondition(this.tableReference.expOf(table.f_RECID).xEq(
				this.orm.newArgument(table.f_RECID)));		
    }

}
