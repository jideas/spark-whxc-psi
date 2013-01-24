package com.spark.erp.storage.inventory;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Book extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Book";

	public final TableFieldDefine f_storeGuid;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_beginStoreCount;
	public final TableFieldDefine f_beginStoreMoney;
	public final TableFieldDefine f_instoCount;
	public final TableFieldDefine f_instoAmount;
	public final TableFieldDefine f_outstoCount;
	public final TableFieldDefine f_outstoAmount;
	public final TableFieldDefine f_endStoreCount;
	public final TableFieldDefine f_endStoreMoney;
	public final TableFieldDefine f_createdDate;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsAttr;
	public final TableFieldDefine f_goodsUnit;
	public final TableFieldDefine f_goodsTypeGuid;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsScale;
	public final TableFieldDefine f_dateNo;
	public final TableFieldDefine f_monthNo;
	public final TableFieldDefine f_quarter;
	public final TableFieldDefine f_yearNo;

	public static final String FN_storeGuid ="storeGuid";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_beginStoreCount ="beginStoreCount";
	public static final String FN_beginStoreMoney ="beginStoreMoney";
	public static final String FN_instoCount ="instoCount";
	public static final String FN_instoAmount ="instoAmount";
	public static final String FN_outstoCount ="outstoCount";
	public static final String FN_outstoAmount ="outstoAmount";
	public static final String FN_endStoreCount ="endStoreCount";
	public static final String FN_endStoreMoney ="endStoreMoney";
	public static final String FN_createdDate ="createdDate";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsAttr ="goodsAttr";
	public static final String FN_goodsUnit ="goodsUnit";
	public static final String FN_goodsTypeGuid ="goodsTypeGuid";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsScale ="goodsScale";
	public static final String FN_dateNo ="dateNo";
	public static final String FN_monthNo ="monthNo";
	public static final String FN_quarter ="quarter";
	public static final String FN_yearNo ="yearNo";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Inventory_Book() {
		super(TABLE_NAME);
		this.table.setDescription("库存台账");
		TableFieldDeclare field;
		this.f_storeGuid = field = this.table.newField(FN_storeGuid, TypeFactory.GUID);
		field.setTitle("仓库GUID");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setTitle("商品GUID");
		this.f_beginStoreCount = field = this.table.newField(FN_beginStoreCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("期初库存数量");
		this.f_beginStoreMoney = field = this.table.newField(FN_beginStoreMoney, TypeFactory.NUMERIC(17,2));
		field.setTitle("期初库存金额");
		this.f_instoCount = field = this.table.newField(FN_instoCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("入库数量");
		this.f_instoAmount = field = this.table.newField(FN_instoAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("入库金额");
		this.f_outstoCount = field = this.table.newField(FN_outstoCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("出库数量");
		this.f_outstoAmount = field = this.table.newField(FN_outstoAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("出库金额");
		this.f_endStoreCount = field = this.table.newField(FN_endStoreCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("期末库存数量");
		this.f_endStoreMoney = field = this.table.newField(FN_endStoreMoney, TypeFactory.NUMERIC(17,2));
		field.setTitle("期末库存金额");
		this.f_createdDate = field = this.table.newField(FN_createdDate, TypeFactory.DATE);
		field.setTitle("创建日期");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(50));
		field.setTitle("商品名称");
		this.f_goodsAttr = field = this.table.newField(FN_goodsAttr, TypeFactory.NVARCHAR(100));
		field.setTitle("商品属性");
		this.f_goodsUnit = field = this.table.newField(FN_goodsUnit, TypeFactory.NVARCHAR(10));
		field.setTitle("商品单位");
		this.f_goodsTypeGuid = field = this.table.newField(FN_goodsTypeGuid, TypeFactory.GUID);
		field.setTitle("商品分类GUID");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("商品编号");
		this.f_goodsScale = field = this.table.newField(FN_goodsScale, TypeFactory.INT);
		field.setTitle("商品精度");
		this.f_dateNo = field = this.table.newField(FN_dateNo, TypeFactory.INT);
		field.setTitle("天号");
		this.f_monthNo = field = this.table.newField(FN_monthNo, TypeFactory.INT);
		field.setTitle("月号");
		this.f_quarter = field = this.table.newField(FN_quarter, TypeFactory.INT);
		field.setTitle("季号");
		this.f_yearNo = field = this.table.newField(FN_yearNo, TypeFactory.INT);
		field.setTitle("年号");
	}

}
