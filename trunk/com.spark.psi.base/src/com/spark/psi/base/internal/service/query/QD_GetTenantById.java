package com.spark.psi.base.internal.service.query;

import com.spark.psi.base.internal.service.orm.Orm_Tenant;
import com.spark.psi.base.storage.tenant.TB_SA_TENANT;

/**
 * 
 * <p>通过租户id获得租户对象</p>
 *


 *
 
 * @version 2012-6-8
 */
public class QD_GetTenantById extends Orm_Tenant{

	public QD_GetTenantById(TB_SA_TENANT table){
	    super(table, "QD_GetTenantById");
	    orm.setCondition(tableRDeclare.expOf(table.f_id).xEq(orm.newArgument(table.f_id)));
    }

}
