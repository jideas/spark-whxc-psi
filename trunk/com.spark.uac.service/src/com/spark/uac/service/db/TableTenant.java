package com.spark.uac.service.db;

import com.jiuqi.dna.core.def.table.IndexDeclare;
import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.type.TypeFactory;

public class TableTenant extends TableDeclarator{
	
	public TableFieldDefine f_id;
	
	public TableFieldDefine f_name;
	
	public TableFieldDefine f_fishNumber;
	
	public TableFieldDefine f_hosts;
	
	public TableFieldDefine f_bossMobile;
	
	public TableFieldDefine f_bossName;
	
	public TableTenant(){
		super("UAC_TENANT");
		f_id = table.f_RECID();
		f_name = table.newField("name", TypeFactory.VARCHAR64);
		f_fishNumber = table.newField("fishNumber", TypeFactory.VARCHAR32);
		f_hosts = table.newField("hosts", TypeFactory.TEXT);
		f_bossMobile = table.newField("bossMobile", TypeFactory.VARCHAR16);
		f_bossName = table.newField("bossTitle", TypeFactory.VARCHAR32);
		IndexDeclare index = this.table.newIndex("INDEX_TENANT_TITLE", f_name);
		index.setUnique(true);
	}
	
}
