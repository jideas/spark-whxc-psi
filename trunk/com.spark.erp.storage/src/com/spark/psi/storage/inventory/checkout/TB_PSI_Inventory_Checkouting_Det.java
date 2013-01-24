package com.spark.psi.storage.inventory.checkout;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Checkouting_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Checkouting_Det";

	public final TableFieldDefine f_sheetId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_scale;
	public final TableFieldDefine f_price;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_planCount;
	public final TableFieldDefine f_checkoutCount;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;

	public static final String FN_sheetId ="sheetId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_unit ="unit";
	public static final String FN_scale ="scale";
	public static final String FN_price ="price";
	public static final String FN_amount ="amount";
	public static final String FN_planCount ="planCount";
	public static final String FN_checkoutCount ="checkoutCount";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Inventory_Checkouting_Det() {
		super(TABLE_NAME);
		this.table.setTitle("出库单中间表明细");
		TableFieldDeclare field;
		this.f_sheetId = field = this.table.newField(FN_sheetId, TypeFactory.GUID);
		field.setTitle("出库单recid");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品recid");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		field.setTitle("商品编码");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("商品条码");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		field.setTitle("商品名称");
		this.f_goodsSpec = field = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(1000));
		field.setTitle("商品规格");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(10));
		field.setTitle("商品单位");
		this.f_scale = field = this.table.newField(FN_scale, TypeFactory.INT);
		field.setTitle("商品数量精度");
		this.f_price = field = this.table.newField(FN_price, TypeFactory.NUMERIC(17,2));
		field.setTitle("商品单价");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("商品金额");
		this.f_planCount = field = this.table.newField(FN_planCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("预计出库数量");
		this.f_checkoutCount = field = this.table.newField(FN_checkoutCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("已出库数量");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人名称");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建时间");
	}

}
