package com.spark.uac.service.db;

import com.jiuqi.dna.core.def.query.ORMDeclarator;
import com.jiuqi.dna.core.def.query.QuRelationRefDeclare;
import com.spark.uac.service.impl.TenantInfoImpl;

public abstract class ORMTenant extends ORMDeclarator<TenantInfoImpl>{
	
	protected QuRelationRefDeclare tablerefdeclare;
	
	public ORMTenant(TableTenant table,String name){
		super(name);
		tablerefdeclare = this.orm.newReference(table);
		orm.newColumn(table.f_id,"id");
		orm.newColumn(table.f_name,"tenantTitle");
		orm.newColumn(table.f_bossMobile,"bossMobile");
		orm.newColumn(table.f_bossName,"bossTitle");
		orm.newColumn(table.f_fishNumber,"fishNumber");
		orm.newColumn(table.f_hosts,"hostStr");
    }
	
}
