package com.spark.erp.storage.onlinereturn;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_SALES_OLRETURN_DET extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_SALES_OLRETURN_DET";

	public final TableFieldDefine f_sheetId;
	public final TableFieldDefine f_onlineBillsItemId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_returnCount;
	public final TableFieldDefine f_billsCount;
	public final TableFieldDefine f_price;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_vantages;

	public static final String FN_sheetId ="sheetId";
	public static final String FN_onlineBillsItemId ="onlineBillsItemId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_returnCount ="returnCount";
	public static final String FN_billsCount ="billsCount";
	public static final String FN_price ="price";
	public static final String FN_amount ="amount";
	public static final String FN_vantages ="vantages";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_SALES_OLRETURN_DET() {
		super(TABLE_NAME);
		this.table.setTitle("退货明细");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_sheetId = field = this.table.newField(FN_sheetId, TypeFactory.GUID);
		field.setTitle("退货单id");
		this.f_onlineBillsItemId = field = this.table.newField(FN_onlineBillsItemId, TypeFactory.GUID);
		field.setTitle("网上订单明细id");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品id");
		this.f_goodsCode = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		this.f_goodsNo = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		this.f_goodsSpec = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(100));
		this.f_goodsUnit = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(10));
		this.f_goodsName = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		this.f_returnCount = this.table.newField(FN_returnCount, TypeFactory.NUMERIC(17,2));
		this.f_billsCount = this.table.newField(FN_billsCount, TypeFactory.NUMERIC(17,2));
		this.f_price = this.table.newField(FN_price, TypeFactory.NUMERIC(17,2));
		this.f_amount = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		this.f_vantages = this.table.newField(FN_vantages, TypeFactory.NUMERIC(17,0));
	}

}
