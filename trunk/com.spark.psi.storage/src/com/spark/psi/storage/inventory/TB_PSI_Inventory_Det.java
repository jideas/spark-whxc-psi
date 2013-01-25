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

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Inventory_Det() {
		super(TABLE_NAME);
		this.table.setTitle("�����ϸ");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_shelfId = field = this.table.newField(FN_shelfId, TypeFactory.GUID);
		field.setTitle("��λ��ʶ");
		this.f_shelfNo = field = this.table.newField(FN_shelfNo, TypeFactory.INT);
		field.setTitle("��λ���");
		this.f_tiersNo = field = this.table.newField(FN_tiersNo, TypeFactory.INT);
		field.setTitle("������ڲ���");
		this.f_stockId = field = this.table.newField(FN_stockId, TypeFactory.GUID);
		field.setTitle("�����ʶ");
		this.f_count = field = this.table.newField(FN_count, TypeFactory.NUMERIC(17,5));
		field.setTitle("�������");
		this.f_produceDate = field = this.table.newField(FN_produceDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_inventoryId = field = this.table.newField(FN_inventoryId, TypeFactory.GUID);
		field.setTitle("���id");
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("�ֿ�id");
	}

}
