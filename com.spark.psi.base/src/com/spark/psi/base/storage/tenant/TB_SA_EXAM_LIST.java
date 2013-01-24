package com.spark.psi.base.storage.tenant;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_EXAM_LIST extends TableDeclarator {

	public static final String TABLE_NAME ="SA_EXAM_LIST";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_userGuid;
	public final TableFieldDefine f_examDate;
	public final TableFieldDefine f_billsNo;
	public final TableFieldDefine f_busType;
	public final TableFieldDefine f_busTypeName;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_status;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_userGuid ="userGuid";
	public static final String FN_examDate ="examDate";
	public static final String FN_billsNo ="billsNo";
	public static final String FN_busType ="busType";
	public static final String FN_busTypeName ="busTypeName";
	public static final String FN_amount ="amount";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";
	public static final String FN_status ="status";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_EXAM_LIST() {
		super(TABLE_NAME);
		this.table.setTitle("������¼  ");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_userGuid = field = this.table.newField(FN_userGuid, TypeFactory.GUID);
		field.setTitle("�û����");
		this.f_examDate = field = this.table.newField(FN_examDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_billsNo = field = this.table.newField(FN_billsNo, TypeFactory.NVARCHAR(20));
		field.setTitle("���ݱ��");
		this.f_busType = field = this.table.newField(FN_busType, TypeFactory.NVARCHAR(20));
		field.setTitle("��������");
		this.f_busTypeName = field = this.table.newField(FN_busTypeName, TypeFactory.NVARCHAR(20));
		field.setTitle("������������");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.DOUBLE);
		field.setTitle("���");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.NVARCHAR(15));
		field.setTitle("����״̬");
		this.table.newIndex("INDEX_EXAM_LIST_",f_tenantsGuid,f_userGuid,f_examDate);
	}

}
