package com.spark.erp.storage.inventory.reportloss;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_INVENTORY_REPORTLOSS_SHELF extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_INVENTORY_REPORTLOSS_SHELF";

	public final TableFieldDefine f_reportLossItemId;
	public final TableFieldDefine f_sheetId;
	public final TableFieldDefine f_stockId;
	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_count;
	public final TableFieldDefine f_shelfId;
	public final TableFieldDefine f_shelfNo;
	public final TableFieldDefine f_tiersNo;
	public final TableFieldDefine f_produceDate;

	public static final String FN_reportLossItemId ="reportLossItemId";
	public static final String FN_sheetId ="sheetId";
	public static final String FN_stockId ="stockId";
	public static final String FN_storeId ="storeId";
	public static final String FN_count ="count";
	public static final String FN_shelfId ="shelfId";
	public static final String FN_shelfNo ="shelfNo";
	public static final String FN_tiersNo ="tiersNo";
	public static final String FN_produceDate ="produceDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_INVENTORY_REPORTLOSS_SHELF() {
		super(TABLE_NAME);
		this.table.setTitle("报损单货位信息");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_reportLossItemId = field = this.table.newField(FN_reportLossItemId, TypeFactory.GUID);
		field.setTitle("报损单条目标识");
		this.f_sheetId = field = this.table.newField(FN_sheetId, TypeFactory.GUID);
		field.setTitle("报损单标识");
		this.f_stockId = field = this.table.newField(FN_stockId, TypeFactory.GUID);
		field.setTitle("商品标识");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("仓库标识");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("报损数量");
		this.f_shelfId = field = this.table.newField(FN_shelfId, TypeFactory.GUID);
		field.setTitle("货位标识");
		this.f_shelfNo = field = this.table.newField(FN_shelfNo, TypeFactory.INT);
		field.setTitle("货位编号");
		this.f_tiersNo = field = this.table.newField(FN_tiersNo, TypeFactory.INT);
		field.setTitle("层数");
		this.f_produceDate = field = this.table.newField(FN_produceDate, TypeFactory.DATE);
		field.setTitle("生产日期");
	}

}
