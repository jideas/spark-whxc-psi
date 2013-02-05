package com.spark.erp.storage.inventory.split;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;

public final class TB_PSI_GOODSSPLIT_GOODS extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_GOODSSPLIT_GOODS";

	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_billId;
	public final TableFieldDefine f_gcount;
	public final TableFieldDefine f_reason;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_goodsUnit;

	public static final String FN_goodsId ="goodsId";
	public static final String FN_billId ="billId";
	public static final String FN_gcount ="gcount";
	public static final String FN_reason ="reason";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_goodsUnit ="goodsUnit";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_GOODSSPLIT_GOODS() {
		super(TABLE_NAME);
		this.f_goodsId = this.table.newField(FN_goodsId, TypeFactory.GUID);
		this.f_billId = this.table.newField(FN_billId, TypeFactory.GUID);
		this.f_gcount = this.table.newField(FN_gcount, TypeFactory.NUMERIC(17,2));
		this.f_reason = this.table.newField(FN_reason, TypeFactory.NVARCHAR(500));
		this.f_goodsName = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(200));
		this.f_goodsSpec = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(100));
		this.f_goodsUnit = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(20));
	}

}
