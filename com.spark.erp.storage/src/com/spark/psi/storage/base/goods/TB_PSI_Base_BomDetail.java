package com.spark.psi.storage.base.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_BomDetail extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_BomDetail";

	public final TableFieldDefine f_bomId;
	public final TableFieldDefine f_materialId;
	public final TableFieldDefine f_baseCount;
	public final TableFieldDefine f_lossRate;
	public final TableFieldDefine f_realCount;

	public static final String FN_bomId ="bomId";
	public static final String FN_materialId ="materialId";
	public static final String FN_baseCount ="baseCount";
	public static final String FN_lossRate ="lossRate";
	public static final String FN_realCount ="realCount";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Base_BomDetail() {
		super(TABLE_NAME);
		this.table.setTitle("bom明细");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_bomId = field = this.table.newField(FN_bomId, TypeFactory.GUID);
		field.setTitle("BOM标识");
		this.f_materialId = field = this.table.newField(FN_materialId, TypeFactory.GUID);
		field.setTitle("子件id");
		this.f_baseCount = field = this.table.newField(FN_baseCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("基本数量");
		this.f_lossRate = field = this.table.newField(FN_lossRate, TypeFactory.NUMERIC(17,4));
		field.setTitle("子件损耗率");
		this.f_realCount = field = this.table.newField(FN_realCount, TypeFactory.NUMERIC(17,4));
		field.setTitle("需求数量");
	}

}
