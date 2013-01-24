package com.spark.psi.storage.purchase;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Purchase_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Purchase_Det";

	public final TableFieldDefine f_billsId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_scale;
	public final TableFieldDefine f_price;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_reason;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;

	public static final String FN_billsId ="billsId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_unit ="unit";
	public static final String FN_scale ="scale";
	public static final String FN_price ="price";
	public static final String FN_count ="count";
	public static final String FN_amount ="amount";
	public static final String FN_reason ="reason";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Purchase_Det() {
		super(TABLE_NAME);
		this.table.setTitle("采购明细");
		TableFieldDeclare field;
		this.f_billsId = field = this.table.newField(FN_billsId, TypeFactory.GUID);
		field.setTitle("采购订单单据编号");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品Guid");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.VARCHAR(30));
		field.setTitle("商品编码");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("商品条码");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		field.setTitle("商品名称");
		this.f_goodsSpec = field = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(1000));
		field.setTitle("商品规格");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(12));
		field.setTitle("单位");
		this.f_scale = field = this.table.newField(FN_scale, TypeFactory.INT);
		field.setTitle("商品小数位数");
		this.f_price = field = this.table.newField(FN_price, TypeFactory.NUMERIC(17,2));
		field.setTitle("单价");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("数量");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("金额");
		this.f_reason = field = this.table.newField(FN_reason, TypeFactory.NVARCHAR(1000));
		field.setTitle("采购原因");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(40));
		field.setTitle("创建人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
	}

}
