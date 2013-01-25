package com.spark.psi.storage.account.invoice;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_FINANCE_INVOICE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_FINANCE_INVOICE";

	public final TableFieldDefine f_cusproGuid;
	public final TableFieldDefine f_cusproName;
	public final TableFieldDefine f_cusproNamePY;
	public final TableFieldDefine f_cusproFullName;
	public final TableFieldDefine f_cusproFullNamePY;
	public final TableFieldDefine f_invoType;
	public final TableFieldDefine f_invoCode;
	public final TableFieldDefine f_invoAmount;
	public final TableFieldDefine f_invoPerson;
	public final TableFieldDefine f_invoPersonName;
	public final TableFieldDefine f_invoDate;
	public final TableFieldDefine f_isInvalided;
	public final TableFieldDefine f_invalidReason;
	public final TableFieldDefine f_invalidPerson;
	public final TableFieldDefine f_invalidDate;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_cusDeptGuid;

	public static final String FN_cusproGuid ="cusproGuid";
	public static final String FN_cusproName ="cusproName";
	public static final String FN_cusproNamePY ="cusproNamePY";
	public static final String FN_cusproFullName ="cusproFullName";
	public static final String FN_cusproFullNamePY ="cusproFullNamePY";
	public static final String FN_invoType ="invoType";
	public static final String FN_invoCode ="invoCode";
	public static final String FN_invoAmount ="invoAmount";
	public static final String FN_invoPerson ="invoPerson";
	public static final String FN_invoPersonName ="invoPersonName";
	public static final String FN_invoDate ="invoDate";
	public static final String FN_isInvalided ="isInvalided";
	public static final String FN_invalidReason ="invalidReason";
	public static final String FN_invalidPerson ="invalidPerson";
	public static final String FN_invalidDate ="invalidDate";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_cusDeptGuid ="cusDeptGuid";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_FINANCE_INVOICE() {
		super(TABLE_NAME);
		this.table.setTitle("��Ʊ�Ǽ���Ϣ��");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_cusproGuid = field = this.table.newField(FN_cusproGuid, TypeFactory.GUID);
		field.setTitle("�ͻ�GUID");
		this.f_cusproName = field = this.table.newField(FN_cusproName, TypeFactory.NVARCHAR(20));
		field.setTitle("�ͻ����");
		this.f_cusproNamePY = field = this.table.newField(FN_cusproNamePY, TypeFactory.NVARCHAR(10));
		field.setTitle("�ͻ����ƴ��");
		this.f_cusproFullName = field = this.table.newField(FN_cusproFullName, TypeFactory.NVARCHAR(100));
		field.setTitle("�ͻ�ȫ��");
		this.f_cusproFullNamePY = field = this.table.newField(FN_cusproFullNamePY, TypeFactory.NVARCHAR(50));
		field.setTitle("�ͻ�ȫ��ƴ��");
		this.f_invoType = field = this.table.newField(FN_invoType, TypeFactory.CHAR(2));
		field.setTitle("��Ʊ����");
		this.f_invoCode = field = this.table.newField(FN_invoCode, TypeFactory.NVARCHAR(200));
		field.setTitle("��Ʊ��");
		this.f_invoAmount = field = this.table.newField(FN_invoAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("��Ʊ���");
		this.f_invoPerson = field = this.table.newField(FN_invoPerson, TypeFactory.GUID);
		field.setTitle("��Ʊ��GUID");
		this.f_invoPersonName = field = this.table.newField(FN_invoPersonName, TypeFactory.NVARCHAR(40));
		field.setTitle("��Ʊ��");
		this.f_invoDate = field = this.table.newField(FN_invoDate, TypeFactory.DATE);
		field.setTitle("��Ʊ����");
		this.f_isInvalided = field = this.table.newField(FN_isInvalided, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ�������");
		this.f_invalidReason = field = this.table.newField(FN_invalidReason, TypeFactory.NVARCHAR(200));
		field.setTitle("����ԭ��");
		this.f_invalidPerson = field = this.table.newField(FN_invalidPerson, TypeFactory.NVARCHAR(40));
		field.setTitle("������");
		this.f_invalidDate = field = this.table.newField(FN_invalidDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.NVARCHAR(40));
		field.setTitle("������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("��������");
		this.f_cusDeptGuid = field = this.table.newField(FN_cusDeptGuid, TypeFactory.GUID);
		field.setTitle("ҵ�����˲���");
	}

}
