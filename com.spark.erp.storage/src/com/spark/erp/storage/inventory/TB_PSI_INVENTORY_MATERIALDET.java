package com.spark.erp.storage.inventory;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_INVENTORY_MATERIALDET extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_INVENTORY_MATERIALDET";

	public final TableFieldDefine f_id;
	public final TableFieldDefine f_shelfId;
	public final TableFieldDefine f_shelfNo;
	public final TableFieldDefine f_tiersNo;
	public final TableFieldDefine f_materialId;
	public final TableFieldDefine f_materialCount;
	public final TableFieldDefine f_produceDate;

	public static final String FN_id ="id";
	public static final String FN_shelfId ="shelfId";
	public static final String FN_shelfNo ="shelfNo";
	public static final String FN_tiersNo ="tiersNo";
	public static final String FN_materialId ="materialId";
	public static final String FN_materialCount ="materialCount";
	public static final String FN_produceDate ="produceDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_INVENTORY_MATERIALDET() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_id = field = this.table.newField(FN_id, TypeFactory.GUID);
		field.setTitle("记录标识");
		this.f_shelfId = field = this.table.newField(FN_shelfId, TypeFactory.GUID);
		field.setTitle("货位标识");
		this.f_shelfNo = field = this.table.newField(FN_shelfNo, TypeFactory.INT);
		field.setTitle("货位编号");
		this.f_tiersNo = field = this.table.newField(FN_tiersNo, TypeFactory.INT);
		field.setTitle("商品所在层数");
		this.f_materialId = field = this.table.newField(FN_materialId, TypeFactory.GUID);
		field.setTitle("商品标识");
		this.f_materialCount = field = this.table.newField(FN_materialCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("商品数量");
		this.f_produceDate = field = this.table.newField(FN_produceDate, TypeFactory.DATE);
		field.setTitle("生产日期");
	}

}
