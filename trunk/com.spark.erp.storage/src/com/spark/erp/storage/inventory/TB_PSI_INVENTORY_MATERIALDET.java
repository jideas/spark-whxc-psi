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

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_INVENTORY_MATERIALDET() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_id = field = this.table.newField(FN_id, TypeFactory.GUID);
		field.setTitle("��¼��ʶ");
		this.f_shelfId = field = this.table.newField(FN_shelfId, TypeFactory.GUID);
		field.setTitle("��λ��ʶ");
		this.f_shelfNo = field = this.table.newField(FN_shelfNo, TypeFactory.INT);
		field.setTitle("��λ���");
		this.f_tiersNo = field = this.table.newField(FN_tiersNo, TypeFactory.INT);
		field.setTitle("��Ʒ���ڲ���");
		this.f_materialId = field = this.table.newField(FN_materialId, TypeFactory.GUID);
		field.setTitle("��Ʒ��ʶ");
		this.f_materialCount = field = this.table.newField(FN_materialCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("��Ʒ����");
		this.f_produceDate = field = this.table.newField(FN_produceDate, TypeFactory.DATE);
		field.setTitle("��������");
	}

}
