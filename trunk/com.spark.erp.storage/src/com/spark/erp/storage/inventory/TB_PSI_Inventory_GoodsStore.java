package com.spark.erp.storage.inventory;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_GoodsStore extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_GoodsStore";

	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_goodsId;
	public final TableFieldDefine f_initCount;
	public final TableFieldDefine f_initAmount;
	public final TableFieldDefine f_initCost;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_goodsAmount;
	public final TableFieldDefine f_shelfLife;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_goodsSpec;
	public final TableFieldDefine f_upperLimitCount;
	public final TableFieldDefine f_lowerLimitCount;
	public final TableFieldDefine f_upperLimitAmount;
	public final TableFieldDefine f_inventoryType;
	public final TableFieldDefine f_lockedCount;

	public static final String FN_storeId ="storeId";
	public static final String FN_goodsId ="goodsId";
	public static final String FN_initCount ="initCount";
	public static final String FN_initAmount ="initAmount";
	public static final String FN_initCost ="initCost";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_goodsAmount ="goodsAmount";
	public static final String FN_shelfLife ="shelfLife";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_goodsSpec ="goodsSpec";
	public static final String FN_upperLimitCount ="upperLimitCount";
	public static final String FN_lowerLimitCount ="lowerLimitCount";
	public static final String FN_upperLimitAmount ="upperLimitAmount";
	public static final String FN_inventoryType ="inventoryType";
	public static final String FN_lockedCount ="lockedCount";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Inventory_GoodsStore() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("仓库标识");
		this.f_goodsId = field = this.table.newField(FN_goodsId, TypeFactory.GUID);
		field.setTitle("商品标识");
		this.f_initCount = field = this.table.newField(FN_initCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("初始化数量");
		this.f_initAmount = field = this.table.newField(FN_initAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("初始化金额");
		this.f_initCost = field = this.table.newField(FN_initCost, TypeFactory.NUMERIC(17,5));
		field.setTitle("初始化单位成本");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		field.setTitle("商品名称");
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		field.setTitle("商品编码");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("商品条码");
		this.f_goodsCount = field = this.table.newField(FN_goodsCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("库存商品数量");
		this.f_goodsAmount = field = this.table.newField(FN_goodsAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("库存商品金额");
		this.f_shelfLife = field = this.table.newField(FN_shelfLife, TypeFactory.INT);
		field.setTitle("保质期");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.CHAR(2));
		field.setTitle("商品单位");
		this.f_goodsSpec = field = this.table.newField(FN_goodsSpec, TypeFactory.NVARCHAR(100));
		field.setTitle("商品规格");
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
