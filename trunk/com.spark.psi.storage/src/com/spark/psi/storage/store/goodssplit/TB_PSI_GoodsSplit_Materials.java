package com.spark.psi.storage.store.goodssplit;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_GoodsSplit_Materials extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_GoodsSplit_Materials";

	public final TableFieldDefine f_materialsId;
	public final TableFieldDefine f_materialsName;
	public final TableFieldDefine f_spec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_standardCount;
	public final TableFieldDefine f_mcount;
	public final TableFieldDefine f_splitSheetId;

	public static final String FN_materialsId ="materialsId";
	public static final String FN_materialsName ="materialsName";
	public static final String FN_spec ="spec";
	public static final String FN_unit ="unit";
	public static final String FN_standardCount ="standardCount";
	public static final String FN_mcount ="mcount";
	public static final String FN_splitSheetId ="splitSheetId";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_GoodsSplit_Materials() {
		super(TABLE_NAME);
		this.table.setTitle("拆分材料明细");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_materialsId = field = this.table.newField(FN_materialsId, TypeFactory.GUID);
		field.setTitle("材料GUID");
		this.f_materialsName = field = this.table.newField(FN_materialsName, TypeFactory.NVARCHAR(100));
		field.setTitle("材料名称");
		this.f_spec = field = this.table.newField(FN_spec, TypeFactory.NVARCHAR(100));
		field.setTitle("规格");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(30));
		field.setTitle("单位");
		this.f_standardCount = field = this.table.newField(FN_standardCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("参考数量");
		this.f_mcount = field = this.table.newField(FN_mcount, TypeFactory.NUMERIC(17,5));
		field.setTitle("数量");
		this.f_splitSheetId = field = this.table.newField(FN_splitSheetId, TypeFactory.GUID);
		field.setTitle("单据id");
	}

}
