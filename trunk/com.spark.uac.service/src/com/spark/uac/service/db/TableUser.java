package com.spark.uac.service.db;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.type.TypeFactory;

public class TableUser extends TableDeclarator {

	public final static String NAME = "UAC_User";

	public final TableFieldDefine f_id;
	public final TableFieldDefine f_tenant_name;
	public final TableFieldDefine f_tenant_id;
	public final TableFieldDefine f_tenant_code;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_password;
	public final TableFieldDefine f_enabled;
	public final TableFieldDefine f_user_status;
	
	
	public TableUser() {
		super(NAME);
		f_id = this.table.f_RECID();
		f_tenant_name = this.table.newField("tenantName", TypeFactory.VARCHAR64);
		f_tenant_id = this.table.newField("tenantId", TypeFactory.GUID);
		f_mobile = this.table.newField("mobile", TypeFactory.VARCHAR16);
		f_password = this.table.newField("password", TypeFactory.GUID);
		f_enabled = this.table.newField("enabled", TypeFactory.BOOLEAN);
		f_user_status = this.table.newField("userstatus", TypeFactory.CHAR(2));
		f_tenant_code = this.table.newField("tenantCode", TypeFactory.VARCHAR16);
		this.table.newIndex("idx_mobile_password", f_mobile, f_password);
	}
}
