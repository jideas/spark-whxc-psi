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

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_STORE_ALLOCATE_DET() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_allocateOrdGuid = field = this.table.newField(FN_allocateOrdGuid, TypeFactory.GUID);
		field.setTitle("��������");
		this.f_goodsGuid = field = this.table.newField(FN_goodsGuid, TypeFactory.GUID);
		field.setTitle("��ƷGUID");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.VARCHAR(50));
		field.setTitle("��Ʒ����");
		this.f_goodsNo = field = this.table.newField(FN_goodsNo, TypeFactory.VARCHAR(20));
		field.setTitle("��Ʒ���");
		this.f_goodsAttr = field = this.table.newField(FN_goodsAttr, TypeFactory.VARCHAR(1000));
		field.setTitle("��Ʒ����");
		this.f_unit = field = this.table.newField(FN_unit, TypeFactory.VARCHAR(4));
		field.setTitle("��Ʒ��λ");
		this.f_ableCount = field = this.table.newField(FN_ableCount, TypeFactory.NUMERIC(17,4));
		field.setTitle("���ÿ��");
		this.f_allocateCount = field = this.table.newField(FN_allocateCount, TypeFactory.NUMERIC(17,4));
		field.setTitle("ʵ�ʵ�������");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.GUID);
		field.setTitle("������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		this.f_countDecimal = field = this.table.newField(FN_countDecimal, TypeFactory.INT);
		field.setTitle("����");
		this.table.newIndex("index_1",f_tenantsGuid,f_allocateOrdGuid);
	}

}
