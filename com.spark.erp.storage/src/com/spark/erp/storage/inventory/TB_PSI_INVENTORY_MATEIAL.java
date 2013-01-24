package com.spark.erp.storage.inventory;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_INVENTORY_MATEIAL extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_INVENTORY_MATEIAL";

	public final TableFieldDefine f_storeId;
	public final TableFieldDefine f_materialId;
	public final TableFieldDefine f_initCount;
	public final TableFieldDefine f_initAmount;
	public final TableFieldDefine f_initCost;
	public final TableFieldDefine f_materialName;
	public final TableFieldDefine f_materialCode;
	public final TableFieldDefine f_materialNumber;
	public final TableFieldDefine f_materialCount;
	public final TableFieldDefine f_materialAmount;
	public final TableFieldDefine f_shelfLife;
	public final TableFieldDefine f_materialUnit;
	public final TableFieldDefine f_materialSpec;
	public final TableFieldDefine f_onWayCount;
	public final TableFieldDefine f_toDeliverCount;
	public final TableFieldDefine f_upperLimitCount;
	public final TableFieldDefine f_lowerLimitCount;
	public final TableFieldDefine f_upperLimitAmount;
	public final TableFieldDefine f_inventoryType;
	public final TableFieldDefine f_lockedCount;

	public static final String FN_storeId ="storeId";
	public static final String FN_materialId ="materialId";
	public static final String FN_initCount ="initCount";
	public static final String FN_initAmount ="initAmount";
	public static final String FN_initCost ="initCost";
	public static final String FN_materialName ="materialName";
	public static final String FN_materialCode ="materialCode";
	public static final String FN_materialNumber ="materialNumber";
	public static final String FN_materialCount ="materialCount";
	public static final String FN_materialAmount ="materialAmount";
	public static final String FN_shelfLife ="shelfLife";
	public static final String FN_materialUnit ="materialUnit";
	public static final String FN_materialSpec ="materialSpec";
	public static final String FN_onWayCount ="onWayCount";
	public static final String FN_toDeliverCount ="toDeliverCount";
	public static final String FN_upperLimitCount ="upperLimitCount";
	public static final String FN_lowerLimitCount ="lowerLimitCount";
	public static final String FN_upperLimitAmount ="upperLimitAmount";
	public static final String FN_inventoryType ="inventoryType";
	public static final String FN_lockedCount ="lockedCount";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_INVENTORY_MATEIAL() {
		super(TABLE_NAME);
		this.table.setTitle("���Ͽ��");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_storeId = field = this.table.newField(FN_storeId, TypeFactory.GUID);
		field.setTitle("�ֿ��ʶ");
		this.f_materialId = field = this.table.newField(FN_materialId, TypeFactory.GUID);
		field.setTitle("���ϱ�ʶ");
		this.f_initCount = field = this.table.newField(FN_initCount, TypeFactory.NUMERIC(17,0));
		field.setTitle("��ʼ������");
		this.f_initAmount = field = this.table.newField(FN_initAmount, TypeFactory.NUMERIC(17,0));
		field.setTitle("��ʼ�����");
		this.f_initCost = field = this.table.newField(FN_initCost, TypeFactory.NUMERIC(17,0));
		field.setTitle("��ʼ����λ�ɱ�");
		this.f_materialName = field = this.table.newField(FN_materialName, TypeFactory.NVARCHAR(100));
		field.setTitle("��������");
		this.f_materialCode = field = this.table.newField(FN_materialCode, TypeFactory.NVARCHAR(30));
		field.setTitle("���ϱ���");
		this.f_materialNumber = field = this.table.newField(FN_materialNumber, TypeFactory.NVARCHAR(30));
		field.setTitle("��������");
		this.f_materialCount = field = this.table.newField(FN_materialCount, TypeFactory.NUMERIC(17,0));
		field.setTitle("����������");
		this.f_materialAmount = field = this.table.newField(FN_materialAmount, TypeFactory.NUMERIC(17,0));
		field.setTitle("�����Ͻ��");
		this.f_shelfLife = field = this.table.newField(FN_shelfLife, TypeFactory.INT);
		field.setTitle("������");
		this.f_materialUnit = field = this.table.newField(FN_materialUnit, TypeFactory.CHAR(2));
		field.setTitle("���ϵ�λ");
		this.f_materialSpec = field = this.table.newField(FN_materialSpec, TypeFactory.NVARCHAR(100));
		field.setTitle("���Ϲ��");
		this.f_onWayCount = field = this.table.newField(FN_onWayCount, TypeFactory.NUMERIC(17,0));
		field.setTitle("�ɹ���;����");
		this.f_toDeliverCount = field = this.table.newField(FN_toDeliverCount, TypeFactory.NUMERIC(17,0));
		field.setTitle("��������");
		this.f_upperLimitCount = field = this.table.newField(FN_upperLimitCount, TypeFactory.NUMERIC(17,0));
		field.setTitle("�����������");
		this.f_lowerLimitCount = field = this.table.newField(FN_lowerLimitCount, TypeFactory.NUMERIC(17,0));
		field.setTitle("�����������");
		this.f_upperLimitAmount = field = this.table.newField(FN_upperLimitAmount, TypeFactory.NUMERIC(17,0));
		field.setTitle("���������");
		this.f_inventoryType = field = this.table.newField(FN_inventoryType, TypeFactory.CHAR(2));
		field.setTitle("�������");
		this.f_lockedCount = field = this.table.newField(FN_lockedCount, TypeFactory.NUMERIC(17,0));
		field.setTitle("�����������");
	}

}
