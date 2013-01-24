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

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_INVENTORY_REPORTLOSS_SHELF() {
		super(TABLE_NAME);
		this.table.setTitle("���𵥻�λ��Ϣ");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_reportLossItemId = field = this.table.newField(FN_reportLossItemId, TypeFactory.GUID);
		field.setTitle("������Ŀ��ʶ");
		this.f_sheetId = field = this.table.newField(FN_sheetId, TypeFactory.GUID);
		field.setTitle("���𵥱�ʶ");
		this.f_stockId = field = this.table.newField(FN_stockId, TypeFactory.GUID);
		field.setTitle("��Ʒ��ʶ");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("�ֿ��ʶ");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("��������");
		this.f_shelfId = field = this.table.newField(FN_shelfId, TypeFactory.GUID);
		field.setTitle("��λ��ʶ");
		this.f_shelfNo = field = this.table.newField(FN_shelfNo, TypeFactory.INT);
		field.setTitle("��λ���");
		this.f_tiersNo = field = this.table.newField(FN_tiersNo, TypeFactory.INT);
		field.setTitle("����");
		this.f_produceDate = field = this.table.newField(FN_produceDate, TypeFactory.DATE);
		field.setTitle("��������");
	}

}
