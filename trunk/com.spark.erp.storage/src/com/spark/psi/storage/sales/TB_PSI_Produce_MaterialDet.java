package com.spark.psi.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_PSI_Produce_MaterialDet extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Produce_MaterialDet";

	public final TableFieldDefine f_billsId;
	public final TableFieldDefine f_MaterialId;
	public final TableFieldDefine f_MaterialCode;
	public final TableFieldDefine f_MaterialNo;
	public final TableFieldDefine f_MaterialName;
	public final TableFieldDefine f_MaterialSpec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_MaterialScale;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_receivedCount;
	public final TableFieldDefine f_returnedCount;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_storeName;

	public static final String FN_billsId ="billsId";
	public static final String FN_MaterialId ="MaterialId";
	public static final String FN_MaterialCode ="MaterialCode";
	public static final String FN_MaterialNo ="MaterialNo";
	public static final String FN_MaterialName ="MaterialName";
	public static final String FN_MaterialSpec ="MaterialSpec";
	public static final String FN_unit ="unit";
	public static final String FN_MaterialScale ="MaterialScale";
	public static final String FN_count ="count";
	public static final String FN_receivedCount ="receivedCount";
	public static final String FN_returnedCount ="returnedCount";
	public static final String FN_storeId ="storeId";
	public static final String FN_storeName ="storeName";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Produce_MaterialDet() {
		super(TABLE_NAME);
		this.table.setTitle("生产订单材料明细");
		TableFieldDeclare field;
		this.f_billsId = field = this.table.newField(FN_billsId, TypeFactory.GUID);
		field.setTitle("订单GUID");
		this.f_MaterialId = field = this.table.newField(FN_MaterialId, TypeFactory.GUID);
		field.setTitle("商品Guid");
		this.f_MaterialCode = field = this.table.newField(FN_MaterialCode, TypeFactory.NVARCHAR(30));
		field.setTitle("商品编号");
		this.f_MaterialNo = field = this.table.newField(FN_MaterialNo, TypeFactory.NVARCHAR(30));
		field.setTitle("商品条码");
		this.f_MaterialName = field = this.table.newField(FN_MaterialName, TypeFactory.NVARCHAR(50));
		field.setTitle("商品名称");
		this.f_MaterialSpec = field = this.table.newField(FN_MaterialSpec, TypeFactory.NVARCHAR(1000));
		field.setTitle("商品规格");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(12));
		field.setTitle("单位");
		this.f_MaterialScale = field = this.table.newField(FN_MaterialScale, TypeFactory.INT);
		field.setTitle("商品小数位数");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("数量");
		this.f_receivedCount = field = this.table.newField(FN_receivedCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("已领数量");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_returnedCount = field = this.table.newField(FN_returnedCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("已退数量");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("仓库ID");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.NVARCHAR(50));
		field.setTitle("仓库名称");
	}

}
