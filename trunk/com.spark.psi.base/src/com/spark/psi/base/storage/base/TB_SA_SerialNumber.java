package com.spark.psi.base.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.type.TypeFactory;

public class TB_SA_SerialNumber extends TableDeclarator {

	public final static String NAME = "SA_SerialNumber";

	public final TableFieldDefine f_id;
	public final TableFieldDefine f_tenantId;
	public final TableFieldDefine f_type;
	public final TableFieldDefine f_prefix;
	public final TableFieldDefine f_serial;
	public final TableFieldDefine f_createTime;

	public TB_SA_SerialNumber() {
		super(NAME);
		f_id = this.table.f_RECID();
		f_tenantId = this.table.newField("tenantId", TypeFactory.GUID);
		f_type = this.table.newField("type", TypeFactory.VARCHAR64);
		f_prefix = this.table.newField("prefix", TypeFactory.VARCHAR64);
		f_serial = this.table.newField("serial", TypeFactory.LONG);
		f_createTime = this.table.newField("createTime", TypeFactory.LONG);
	}
}
