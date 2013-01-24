package com.spark.erp.storage.inventory.allocate;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Allocate_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Allocate_Det";

	public final TableFieldDefine f_allocateId;
	public final TableFieldDefine f_stockId;
	public final TableFieldDefine f_stockName;
	public final TableFieldDefine f_stockCode;
	public final TableFieldDefine f_stockNo;
	public final TableFieldDefine f_stockSpec;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_ableCount;
	public final TableFieldDefine f_allocateCount;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_stockScale;

	public static final String FN_allocateId ="allocateId";
	public static final String FN_stockId ="stockId";
	public static final String FN_stockName ="stockName";
	public static final String FN_stockCode ="stockCode";
	public static final String FN_stockNo ="stockNo";
	public static final String FN_stockSpec ="stockSpec";
	public static final String FN_unit ="unit";
	public static final String FN_ableCount ="ableCount";
	public static final String FN_allocateCount ="allocateCount";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_createDate ="createDate";
	public static final String FN_stockScale ="stockScale";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Inventory_Allocate_Det() {
		super(TABLE_NAME);
		this.table.setDescription("库存调拔明细");
		TableFieldDeclare field;
		this.f_allocateId = field = this.table.newField(FN_allocateId, TypeFactory.GUID);
		field.setTitle("调拨单号");
		this.f_stockId = field = this.table.newField(FN_stockId, TypeFactory.GUID);
		field.setTitle("商品GUID");
		this.f_stockName = field = this.table.newField(FN_stockName, TypeFactory.NVARCHAR(50));
		field.setTitle("商品名称");
		this.f_stockCode = field = this.table.newField(FN_stockCode, TypeFactory.NVARCHAR(30));
		field.setTitle("商品编号");
		this.f_stockNo = field = this.table.newField(FN_stockNo, TypeFactory.NVARCHAR(30));
		field.setTitle("商品条码");
		this.f_stockSpec = field = this.table.newField(FN_stockSpec, TypeFactory.NVARCHAR(100));
		field.setTitle("商品规格");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(10));
		field.setTitle("商品单位");
		this.f_ableCount = field = this.table.newField(FN_ableCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("可用库存");
		this.f_allocateCount = field = this.table.newField(FN_allocateCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("实际调拨数量");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人ID");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建时间");
		this.f_stockScale = field = this.table.newField(FN_stockScale, TypeFactory.INT);
		field.setTitle("精度");
	}

}
