package com.spark.psi.storage.inventory;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory";

	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_stockId;
	public final TableFieldDefine f_initCount;
	public final TableFieldDefine f_initAmount;
	public final TableFieldDefine f_initCost;
	public final TableFieldDefine f_name;
	public final TableFieldDefine f_code;
	public final TableFieldDefine f_stockNo;
	public final TableFieldDefine f_properties;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_spec;
	public final TableFieldDefine f_onWayCount;
	public final TableFieldDefine f_toDeliverCount;
	public final TableFieldDefine f_upperLimitCount;
	public final TableFieldDefine f_lowerLimitCount;
	public final TableFieldDefine f_upperLimitAmount;
	public final TableFieldDefine f_inventoryType;
	public final TableFieldDefine f_lockedCount;

	public static final String FN_storeId ="storeId";
	public static final String FN_stockId ="stockId";
	public static final String FN_initCount ="initCount";
	public static final String FN_initAmount ="initAmount";
	public static final String FN_initCost ="initCost";
	public static final String FN_name ="name";
	public static final String FN_code ="code";
	public static final String FN_stockNo ="stockNo";
	public static final String FN_properties ="properties";
	public static final String FN_count ="count";
	public static final String FN_amount ="amount";
	public static final String FN_unit ="unit";
	public static final String FN_spec ="spec";
	public static final String FN_onWayCount ="onWayCount";
	public static final String FN_toDeliverCount ="toDeliverCount";
	public static final String FN_upperLimitCount ="upperLimitCount";
	public static final String FN_lowerLimitCount ="lowerLimitCount";
	public static final String FN_upperLimitAmount ="upperLimitAmount";
	public static final String FN_inventoryType ="inventoryType";
	public static final String FN_lockedCount ="lockedCount";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Inventory() {
		super(TABLE_NAME);
		this.table.setTitle("库存表");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("仓库标识");
		this.f_stockId = field = this.table.newField(FN_stockId, TypeFactory.GUID);
		field.setTitle("存货标识");
		this.f_initCount = field = this.table.newField(FN_initCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("初始化数量");
		this.f_initAmount = field = this.table.newField(FN_initAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("初始化金额");
		this.f_initCost = field = this.table.newField(FN_initCost, TypeFactory.NUMERIC(17,2));
		field.setTitle("初始化单位成本");
		this.f_name = field = this.table.newField(FN_name, TypeFactory.NVARCHAR(100));
		field.setTitle("存货名称");
		this.f_code = field = this.table.newField(FN_code, TypeFactory.NVARCHAR(30));
		field.setTitle("存货编码");
		this.f_stockNo = field = this.table.newField(FN_stockNo, TypeFactory.NVARCHAR(30));
		field.setTitle("存货条码");
		this.f_properties = field = this.table.newField(FN_properties, TypeFactory.NVARCHAR(1000));
		field.setTitle("属性");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("存货数量");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("存货金额");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.NVARCHAR(10));
		field.setTitle("存货单位");
		this.f_spec = field = this.table.newField(FN_spec, TypeFactory.NVARCHAR(50));
		field.setTitle("存货规格");
		this.f_onWayCount = field = this.table.newField(FN_onWayCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("采购在途数量");
		this.f_toDeliverCount = field = this.table.newField(FN_toDeliverCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("发货需求");
		this.f_upperLimitCount = field = this.table.newField(FN_upperLimitCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("库存数量上限");
		this.f_lowerLimitCount = field = this.table.newField(FN_lowerLimitCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("库存数量下限");
		this.f_upperLimitAmount = field = this.table.newField(FN_upperLimitAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("库存金额上限");
		this.f_inventoryType = field = this.table.newField(FN_inventoryType, TypeFactory.CHAR(2));
		field.setTitle("库存类型");
		this.f_lockedCount = field = this.table.newField(FN_lockedCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("锁定库存数量");
	}

}
