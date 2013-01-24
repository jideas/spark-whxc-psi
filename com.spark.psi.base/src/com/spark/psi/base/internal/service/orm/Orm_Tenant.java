package com.spark.psi.base.internal.service.orm;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.table.TableReferenceDeclare;
import com.spark.psi.base.internal.entity.ormentity.TenantOrmEntity;
import com.spark.psi.base.storage.tenant.TB_SA_TENANT;

public abstract class Orm_Tenant extends ORMDeclarator<TenantOrmEntity>{

	protected TableReferenceDeclare tableRDeclare;
	
	public Orm_Tenant(TB_SA_TENANT table,String name){
	    super(name);
	    this.tableRDeclare = orm.newReference(table);
	    this.orm.newColumn(table.f_id,"id");
	    this.orm.newColumn(table.f_config,"config");
    }
	
}
