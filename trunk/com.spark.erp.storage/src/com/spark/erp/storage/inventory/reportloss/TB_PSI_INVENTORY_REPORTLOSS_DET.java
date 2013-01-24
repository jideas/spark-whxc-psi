package com.spark.erp.storage.inventory.reportloss;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_INVENTORY_REPORTLOSS_DET extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_INVENTORY_REPORTLOSS_DET";

	public final TableFieldDefine f_reportLossSheetId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNumber;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_scale;
	public final TableFieldDefine f_reportCount;
	public final TableFieldDefine f_reportReason;
	public final TableFieldDefine f_reportType;

	public static final String FN_reportLossSheetId ="reportLossSheetId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNumber ="goodsNumber";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_scale ="scale";
	public static final String FN_reportCount ="reportCount";
	public static final String FN_reportReason ="reportReason";
	public static final String FN_reportType ="reportType";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_INVENTORY_REPORTLOSS_DET() {
		super(TABLE_NAME);
		this.table.setTitle("报损单明细");
		TableFieldDeclare field;
		this.f_reportLossSheetId = field = this.table.newField(FN_reportLossSheetId, TypeFactory.GUID);
		field.setTitle("报损单标识");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品标识");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		field.setTitle("商品编码");
		this.f_goodsNumber = field = this.table.newField(FN_goodsNumber, TypeFactory.NVARCHAR(30));
		field.setTitle("商品条码");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		field.setTitle("商品名称");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(10));
		field.setTitle("商品单位");
		this.f_goodsSpec = field = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(100));
		field.setTitle("商品规格");
		this.f_scale = this.table.newField(FN_scale, TypeFactory.INT);
		this.f_reportCount = field = this.table.newField(FN_reportCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("报损数量");
		this.f_reportReason = field = this.table.newField(FN_reportReason, TypeFactory.NVARCHAR(200));
		field.setTitle("报损原因");
		this.f_reportType = field = this.table.newField(FN_reportType, TypeFactory.CHAR(2));
		field.setTitle("报损类型(商品or材料，目前只支持材料)");
	}

}
