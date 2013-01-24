package com.spark.psi.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Sales_Return_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Sales_Return_Det";

	public final TableFieldDefine f_billsId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_goodsScale;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_price;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_amount;

	public static final String FN_billsId ="billsId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_unit ="unit";
	public static final String FN_goodsScale ="goodsScale";
	public static final String FN_storeId ="storeId";
	public static final String FN_storeName ="storeName";
	public static final String FN_price ="price";
	public static final String FN_count ="count";
	public static final String FN_amount ="amount";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Sales_Return_Det() {
		super(TABLE_NAME);
		this.table.setTitle("销售退货明细");
		TableFieldDeclare field;
		this.f_billsId = field = this.table.newField(FN_billsId, TypeFactory.GUID);
		field.setTitle("退货单号");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品Guid");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		field.setTitle("商品编号");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("商品条码");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		field.setTitle("商品名称");
		this.f_goodsSpec = field = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(100));
		field.setTitle("商品规格");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(12));
		field.setTitle("单位");
		this.f_goodsScale = field = this.table.newField(FN_goodsScale, TypeFactory.INT);
		field.setTitle("商品小数位数");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("仓库GUID");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.NVARCHAR(50));
		field.setTitle("仓库名称");
		this.f_price = field = this.table.newField(FN_price, TypeFactory.NUMERIC(17,2));
		field.setTitle("单价");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("数量");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("金额");
	}

}
