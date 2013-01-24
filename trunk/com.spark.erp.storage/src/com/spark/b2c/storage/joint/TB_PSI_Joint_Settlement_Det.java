package com.spark.b2c.storage.joint;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Joint_Settlement_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Joint_Settlement_Det";

	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_sheetId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsPrice;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_percentage;
	public final TableFieldDefine f_percentageAmount;

	public static final String FN_goodsId ="goodsId";
	public static final String FN_sheetId ="sheetId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsPrice ="goodsPrice";
	public static final String FN_count ="count";
	public static final String FN_amount ="amount";
	public static final String FN_percentage ="percentage";
	public static final String FN_percentageAmount ="percentageAmount";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Joint_Settlement_Det() {
		super(TABLE_NAME);
		this.table.setTitle("结算明细");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品id");
		this.f_sheetId = this.table.newField(FN_sheetId, TypeFactory.GUID);
		this.f_goodsCode = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		this.f_goodsNo = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		this.f_goodsSpec = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(100));
		this.f_goodsUnit = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(20));
		this.f_goodsName = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		this.f_goodsPrice = this.table.newField(FN_goodsPrice, TypeFactory.NUMERIC(10,2));
		this.f_count = this.table.newField(FN_count, TypeFactory.NUMERIC(15,2));
		this.f_amount = this.table.newField(FN_amount, TypeFactory.NUMERIC(15,2));
		this.f_percentage = this.table.newField(FN_percentage, TypeFactory.NUMERIC(5,4));
		this.f_percentageAmount = this.table.newField(FN_percentageAmount, TypeFactory.NUMERIC(15,2));
	}

}
