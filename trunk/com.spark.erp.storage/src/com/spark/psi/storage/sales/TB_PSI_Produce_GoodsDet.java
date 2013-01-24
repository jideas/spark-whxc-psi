package com.spark.psi.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_PSI_Produce_GoodsDet extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Produce_GoodsDet";

	public final TableFieldDefine f_billsId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_goodsScale;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_bomId;
	public final TableFieldDefine f_finishedCount;

	public static final String FN_billsId ="billsId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_unit ="unit";
	public static final String FN_goodsScale ="goodsScale";
	public static final String FN_count ="count";
	public static final String FN_bomId ="bomId";
	public static final String FN_finishedCount ="finishedCount";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Produce_GoodsDet() {
		super(TABLE_NAME);
		this.table.setTitle("生产订单商品明细");
		TableFieldDeclare field;
		this.f_billsId = field = this.table.newField(FN_billsId, TypeFactory.GUID);
		field.setTitle("订单GUID");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品Guid");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		field.setTitle("商品编号");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("商品条码");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		field.setTitle("商品名称");
		this.f_goodsSpec = field = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(1000));
		field.setTitle("商品规格");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(12));
		field.setTitle("单位");
		this.f_goodsScale = field = this.table.newField(FN_goodsScale, TypeFactory.INT);
		field.setTitle("商品小数位数");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("数量");
		this.f_bomId = field = this.table.newField(FN_bomId, TypeFactory.GUID);
		field.setTitle("bom表Id");
		this.f_finishedCount = field = this.table.newField(FN_finishedCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("已完成数量");
		field.setDefault(ConstExpression.builder.expOf(0.0));
	}

}
