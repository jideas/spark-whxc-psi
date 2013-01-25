package com.spark.uac.service.db;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.type.TypeFactory;

public class TableActivation extends TableDeclarator {
	public final static String NAME = "UAC_Activation";

	public final TableFieldDefine f_id;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_targetId;
	public final TableFieldDefine f_sourceId;
	public final TableFieldDefine f_userId;
	public final TableFieldDefine f_userTitle;
	public final TableFieldDefine f_activeCode;
	public final TableFieldDefine f_codeCreate;

	public TableActivation() {
		super(NAME);
		f_id = this.table.f_RECID();
		f_mobile = this.table.newField("mobile", TypeFactory.VARCHAR16);
		f_targetId = this.table.newField("source", TypeFactory.GUID);
		f_sourceId = this.table.newField("target", TypeFactory.GUID);
		f_userId = this.table.newField("user", TypeFactory.GUID);
		f_userTitle = this.table.newField("title", TypeFactory.VARCHAR256);
		f_activeCode = this.table.newField("active_code",
				TypeFactory.VARCHAR256);
		f_codeCreate = this.table.newField("code_create", TypeFactory.LONG);

		this.table.newIndex("idx_mobile", f_mobile);
	}
}
