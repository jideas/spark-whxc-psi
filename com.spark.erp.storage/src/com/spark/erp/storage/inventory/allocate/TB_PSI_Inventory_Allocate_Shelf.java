package com.spark.erp.storage.inventory.allocate;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;

public final class TB_PSI_Inventory_Allocate_Shelf extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Inventory_Allocate_Shelf";

	public final TableFieldDefine f_allocateItemId;
	public final TableFieldDefine f_sheetId;
	public final TableFieldDefine f_stockId;
	public final TableFieldDefine f_stockName;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_shelfId;
	public final TableFieldDefine f_shelfNo;
	public final TableFieldDefine f_tiersNo;
	public final TableFieldDefine f_produceDate;

	public static final String FN_allocateItemId ="allocateItemId";
	public static final String FN_sheetId ="sheetId";
	public static final String FN_stockId ="stockId";
	public static final String FN_stockName ="stockName";
	public static final String FN_storeId ="storeId";
	public static final String FN_count ="count";
	public static final String FN_shelfId ="shelfId";
	public static final String FN_shelfNo ="shelfNo";
	public static final String FN_tiersNo ="tiersNo";
	public static final String FN_produceDate ="produceDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Inventory_Allocate_Shelf() {
		super(TABLE_NAME);
		this.f_allocateItemId = this.table.newField(FN_allocateItemId, TypeFactory.GUID);
		this.f_sheetId = this.table.newField(FN_sheetId, TypeFactory.GUID);
		this.f_stockId = this.table.newField(FN_stockId, TypeFactory.GUID);
		this.f_stockName = this.table.newField(FN_stockName, TypeFactory.NVARCHAR(100));
		this.f_storeId = this.table.newField(FN_storeId, TypeFactory.GUID);
		this.f_count = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		this.f_shelfId = this.table.newField(FN_shelfId, TypeFactory.GUID);
		this.f_shelfNo = this.table.newField(FN_shelfNo, TypeFactory.INT);
		this.f_tiersNo = this.table.newField(FN_tiersNo, TypeFactory.INT);
		this.f_produceDate = this.table.newField(FN_produceDate, TypeFactory.DATE);
	}

}
