package com.spark.psi.storage.store.allocate;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_STORE_ALLOCATE_DET extends TableDeclarator {

	public static final String TABLE_NAME ="SA_STORE_ALLOCATE_DET";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_allocateOrdGuid;
	public final TableFieldDefine f_goodsGuid;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_goodsNo;
	public final TableFieldDefine f_goodsAttr;
	public final TableFieldDefine f_unit;
	public final TableFieldDefine f_ableCount;
	public final TableFieldDefine f_allocateCount;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_countDecimal;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_allocateOrdGuid ="allocateOrdGuid";
	public static final String FN_goodsGuid ="goodsGuid";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_goodsNo ="goodsNo";
	public static final String FN_goodsAttr ="goodsAttr";
	public static final String FN_unit ="unit";
	public static final String FN_ableCount ="ableCount";
	public static final String FN_allocateCount ="allocateCount";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";
	public static final String FN_countDecimal ="countDecimal";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_STORE_ALLOCATE_DET() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_allocateOrdGuid = field = this.table.newField(FN_allocateOrdGuid, TypeFactory.GUID);
		field.setTitle("调拨单号");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setTitle("商品GUID");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.VARCHAR(50));
		field.setTitle("商品名称");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.VARCHAR(20));
		field.setTitle("商品编号");
		this.f_goodsAttr = field = this.table.newField(FN_goodsAttr, TypeFactory.VARCHAR(1000));
		field.setTitle("商品属性");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.VARCHAR(4));
		field.setTitle("商品单位");
		this.f_ableCount = field = this.table.newField(FN_ableCount, TypeFactory.NUMERIC(17,4));
		field.setTitle("可用库存");
		this.f_allocateCount = field = this.table.newField(FN_allocateCount, TypeFactory.NUMERIC(17,4));
		field.setTitle("实际调拨数量");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.GUID);
		field.setTitle("创建人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建时间");
		this.f_countDecimal = field = this.table.newField(FN_countDecimal, TypeFactory.INT);
		field.setTitle("精度");
		this.table.newIndex("index_1",f_tenantsGuid,f_allocateOrdGuid);
	}

}
