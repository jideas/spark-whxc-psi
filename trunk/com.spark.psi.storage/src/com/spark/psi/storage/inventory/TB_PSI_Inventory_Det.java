package com.spark.psi.storage.inventory;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Inventory_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Det";

	public final TableFieldDefine f_shelfId;
	public final TableFieldDefine f_shelfNo;
	public final TableFieldDefine f_tiersNo;
	public final TableFieldDefine f_stockId;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_produceDate;
	public final TableFieldDefine f_inventoryId;
	public final TableFieldDefine f_storeId;

	public static final String FN_shelfId ="shelfId";
	public static final String FN_shelfNo ="shelfNo";
	public static final String FN_tiersNo ="tiersNo";
	public static final String FN_stockId ="stockId";
	public static final String FN_count ="count";
	public static final String FN_produceDate ="produceDate";
	public static final String FN_inventoryId ="inventoryId";
	public static final String FN_storeId ="storeId";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Inventory_Det() {
		super(TABLE_NAME);
		this.table.setTitle("库存明细");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_shelfId = field = this.table.newField(FN_shelfId, TypeFactory.GUID);
		field.setTitle("货位标识");
		this.f_shelfNo = field = this.table.newField(FN_shelfNo, TypeFactory.INT);
		field.setTitle("货位编号");
		this.f_tiersNo = field = this.table.newField(FN_tiersNo, TypeFactory.INT);
		field.setTitle("存货所在层数");
		this.f_stockId = field = this.table.newField(FN_stockId, TypeFactory.GUID);
		field.setTitle("存货标识");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("存货数量");
		this.f_produceDate = field = this.table.newField(FN_produceDate, TypeFactory.DATE);
		field.setTitle("生产日期");
		this.f_inventoryId = field = this.table.newField(FN_inventoryId, TypeFactory.GUID);
		field.setTitle("库存id");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("仓库id");
	}

}
