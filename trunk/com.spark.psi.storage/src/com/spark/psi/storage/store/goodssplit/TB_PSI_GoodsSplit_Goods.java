package com.spark.psi.storage.store.goodssplit;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_GoodsSplit_Goods extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_GoodsSplit_Goods";

	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_spec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_gcount;
	public final TableFieldDefine f_splitSheetId;

	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_spec ="spec";
	public static final String FN_unit ="unit";
	public static final String FN_gcount ="gcount";
	public static final String FN_splitSheetId ="splitSheetId";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_GoodsSplit_Goods() {
		super(TABLE_NAME);
		this.table.setTitle("拆分商品明细");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品GUID");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		field.setTitle("商品名称");
		this.f_spec = field = this.table.newField(FN_spec, TypeFactory.NVARCHAR(100));
		field.setTitle("规格");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(30));
		field.setTitle("单位");
		this.f_gcount = field = this.table.newField(FN_gcount, TypeFactory.NUMERIC(17,5));
		field.setTitle("数量");
		this.f_splitSheetId = field = this.table.newField(FN_splitSheetId, TypeFactory.GUID);
		field.setTitle("单据id");
	}

}
