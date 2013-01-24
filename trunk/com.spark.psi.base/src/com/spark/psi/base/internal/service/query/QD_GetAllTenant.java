package com.spark.psi.base.internal.service.query;

import com.spark.psi.base.internal.service.orm.Orm_Tenant;
import com.spark.psi.base.storage.tenant.TB_SA_TENANT;

/**
 * 
 * <p>获取所有租户信息</p>
 *


 *
 
 * @version 2012-6-8
 */
public class QD_GetAllTenant extends Orm_Tenant{

	public QD_GetAllTenant(TB_SA_TENANT table){
	    super(table, "QD_GetAllTenant");
    }

}
