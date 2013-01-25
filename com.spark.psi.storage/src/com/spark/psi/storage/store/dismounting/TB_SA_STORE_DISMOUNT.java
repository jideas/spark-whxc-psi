package com.spark.psi.storage.store.dismounting;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_STORE_DISMOUNT extends TableDeclarator {

	public static final String TABLE_NAME ="SA_STORE_DISMOUNT";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_dismDate;
	public final TableFieldDefine f_dismOrdNo;
	public final TableFieldDefine f_markPerson;
	public final TableFieldDefine f_markName;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_storeGuid;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_storePY;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_dismDate ="dismDate";
	public static final String FN_dismOrdNo ="dismOrdNo";
	public static final String FN_markPerson ="markPerson";
	public static final String FN_markName ="markName";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_storeGuid ="storeGuid";
	public static final String FN_storeName ="storeName";
	public static final String FN_storePY ="storePY";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_STORE_DISMOUNT() {
		super(TABLE_NAME);
		this.table.setDescription("����װ");
		this.table.setTitle("����װ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧ID");
		this.f_dismDate = field = this.table.newField(FN_dismDate, TypeFactory.DATE);
		field.setTitle("��װ����");
		this.f_dismOrdNo = field = this.table.newField(FN_dismOrdNo, TypeFactory.VARCHAR(20));
		field.setTitle("��װ����ţ����ţ�");
		this.f_markPerson = field = this.table.newField(FN_markPerson, TypeFactory.GUID);
		field.setTitle("�Ƶ���");
		this.f_markName = field = this.table.newField(FN_markName, TypeFactory.VARCHAR(20));
		field.setTitle("�Ƶ�������");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("����");
		this.f_storeGuid = field = this.table.newField(FN_storeGuid, TypeFactory.GUID);
		field.setTitle("�ֿ�");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.VARCHAR(20));
		field.setTitle("�ֿ�����");
		this.f_storePY = field = this.table.newField(FN_storePY, TypeFactory.VARCHAR(25));
		field.setTitle("�ֿ����ƣ�ƴ����");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.VARCHAR(20));
		field.setTitle("������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		this.table.newIndex("dismount_markName",f_markName,f_storePY,f_storeName,f_dismOrdNo);
	}

}
