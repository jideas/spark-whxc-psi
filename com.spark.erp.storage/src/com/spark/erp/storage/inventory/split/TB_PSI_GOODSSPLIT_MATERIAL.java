package com.spark.erp.storage.inventory.split;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;

public final class TB_PSI_GOODSSPLIT_MATERIAL extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_GOODSSPLIT_MATERIAL";

	public final TableFieldDefine f_billId;
	public final TableFieldDefine f_materialId;
	public final TableFieldDefine f_mcount;
	public final TableFieldDefine f_mname;
	public final TableFieldDefine f_munit;
	public final TableFieldDefine f_mcode;
	public final TableFieldDefine f_mnumber;
	public final TableFieldDefine f_mspec;

	public static final String FN_billId ="billId";
	public static final String FN_materialId ="materialId";
	public static final String FN_mcount ="mcount";
	public static final String FN_mname ="mname";
	public static final String FN_munit ="munit";
	public static final String FN_mcode ="mcode";
	public static final String FN_mnumber ="mnumber";
	public static final String FN_mspec ="mspec";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_GOODSSPLIT_MATERIAL() {
		super(TABLE_NAME);
		this.f_billId = this.table.newField(FN_billId, TypeFactory.GUID);
		this.f_materialId = this.table.newField(FN_materialId, TypeFactory.GUID);
		this.f_mcount = this.table.newField(FN_mcount, TypeFactory.NUMERIC(17,2));
		this.f_mname = this.table.newField(FN_mname, TypeFactory.NVARCHAR(200));
		this.f_munit = this.table.newField(FN_munit, TypeFactory.NVARCHAR(20));
		this.f_mcode = this.table.newField(FN_mcode, TypeFactory.NVARCHAR(30));
		this.f_mnumber = this.table.newField(FN_mnumber, TypeFactory.NVARCHAR(30));
		this.f_mspec = this.table.newField(FN_mspec, TypeFactory.NVARCHAR(100));
	}

}
