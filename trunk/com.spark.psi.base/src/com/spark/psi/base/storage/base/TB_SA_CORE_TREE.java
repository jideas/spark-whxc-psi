package com.spark.psi.base.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.IndexDeclare;

public final class TB_SA_CORE_TREE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_CORE_TREE";

	public final TableFieldDefine f_PATH;
	public final TableFieldDefine f_STAUTS;
	public final TableFieldDefine f_tenantId;

	public static final String FN_PATH ="PATH";
	public static final String FN_STAUTS ="STAUTS";
	public static final String FN_tenantId ="tenantId";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_CORE_TREE() {
		super(TABLE_NAME);
		this.table.setTitle("级次结构表 ");
		this.f_PATH = this.table.newField(FN_PATH, TypeFactory.VARBINARY(544));
		this.f_STAUTS = this.table.newField(FN_STAUTS, TypeFactory.CHAR(1));
		this.f_tenantId = this.table.newField(FN_tenantId, TypeFactory.GUID);
		IndexDeclare index;
		index = this.table.newIndex("INDEX_CORE_TREE",f_PATH);
		index.setUnique(true);
	}

}
