package com.spark.psi.base.storage.tenant;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.type.TypeFactory;

public class TB_SA_TENANT extends TableDeclarator{

	public final TableFieldDefine f_id;
	public final TableFieldDefine f_config;
	
	public TB_SA_TENANT(){
	    super("SA_PSI_TENANT");
	    this.f_config = table.newField("config", TypeFactory.TEXT);
	    this.f_id = table.f_RECID();
    }
	
}
