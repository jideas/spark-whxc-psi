package com.spark.erp.storage.inventory;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Log extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Log";

	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_stockId;
	public final TableFieldDefine f_name;
	public final TableFieldDefine f_properties;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_categoryId;
	public final TableFieldDefine f_code;
	public final TableFieldDefine f_stockNo;
	public final TableFieldDefine f_orderId;
	public final TableFieldDefine f_orderNo;
	public final TableFieldDefine f_logType;
	public final TableFieldDefine f_instoCount;
	public final TableFieldDefine f_instoAmount;
	public final TableFieldDefine f_scale;
	public final TableFieldDefine f_outstoCount;
	public final TableFieldDefine f_outstoAmount;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createdDate;
	public final TableFieldDefine f_inventoryType;

	public static final String FN_storeId ="storeId";
	public static final String FN_stockId ="stockId";
	public static final String FN_name ="name";
	public static final String FN_properties ="properties";
	public static final String FN_unit ="unit";
	public static final String FN_categoryId ="categoryId";
	public static final String FN_code ="code";
	public static final String FN_stockNo ="stockNo";
	public static final String FN_orderId ="orderId";
	public static final String FN_orderNo ="orderNo";
	public static final String FN_logType ="logType";
	public static final String FN_instoCount ="instoCount";
	public static final String FN_instoAmount ="instoAmount";
	public static final String FN_scale ="scale";
	public static final String FN_outstoCount ="outstoCount";
	public static final String FN_outstoAmount ="outstoAmount";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createdDate ="createdDate";
	public static final String FN_inventoryType ="inventoryType";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Inventory_Log() {
		super(TABLE_NAME);
		this.table.setDescription("库存流水");
		TableFieldDeclare field;
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("仓库GUID");
		this.f_stockId = field = this.table.newField(FN_stockId, TypeFactory.GUID);
		field.setTitle("商品GUID");
		this.f_name = field = this.table.newField(FN_name, TypeFactory.NVARCHAR(50));
		field.setTitle("商品名称");
		this.f_properties = field = this.table.newField(FN_properties, TypeFactory.NVARCHAR(100));
		field.setTitle("商品属性");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(20));
		field.setTitle("商品单位");
		this.f_categoryId = field = this.table.newField(FN_categoryId, TypeFactory.GUID);
		field.setTitle("商品分类GUID");
		this.f_code = field = this.table.newField(FN_code, TypeFactory.NVARCHAR(30));
		field.setTitle("商品编号");
		this.f_stockNo = field = this.table.newField(FN_stockNo, TypeFactory.NVARCHAR(30));
		field.setTitle("商品条码");
		this.f_orderId = field = this.table.newField(FN_orderId, TypeFactory.GUID);
		field.setTitle("单据id");
		this.f_orderNo = field = this.table.newField(FN_orderNo, TypeFactory.NVARCHAR(30));
		field.setTitle("单据编号");
		this.f_logType = field = this.table.newField(FN_logType, TypeFactory.CHAR(2));
		field.setTitle("流水类型");
		this.f_instoCount = field = this.table.newField(FN_instoCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("入库数量");
		this.f_instoAmount = field = this.table.newField(FN_instoAmount, TypeFactory.NUMERIC(17,5));
		field.setTitle("入库金额");
		this.f_scale = field = this.table.newField(FN_scale, TypeFactory.INT);
		field.setTitle("商品精度");
		this.f_outstoCount = field = this.table.newField(FN_outstoCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("出库数量");
		this.f_outstoAmount = field = this.table.newField(FN_outstoAmount, TypeFactory.NUMERIC(17,5));
		field.setTitle("出库金额");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(20));
		field.setTitle("创建人");
		this.f_createdDate = field = this.table.newField(FN_createdDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_inventoryType = field = this.table.newField(FN_inventoryType, TypeFactory.CHAR(2));
		field.setTitle("库存类型");
	}

}
