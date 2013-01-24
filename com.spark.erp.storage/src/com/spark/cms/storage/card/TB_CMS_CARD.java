package com.spark.cms.storage.card;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_CARD extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_CARD";

	public final TableFieldDefine f_ordinal;
	public final TableFieldDefine f_cardNo;
	public final TableFieldDefine f_password;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_secretKey;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_activePersonId;
	public final TableFieldDefine f_activePerson;
	public final TableFieldDefine f_activeDate;
	public final TableFieldDefine f_distributePersonId;
	public final TableFieldDefine f_distributePerson;
	public final TableFieldDefine f_responsiblePersonId;
	public final TableFieldDefine f_responsiblePerson;
	public final TableFieldDefine f_distributeDate;
	public final TableFieldDefine f_usedPersonId;
	public final TableFieldDefine f_usedPerson;
	public final TableFieldDefine f_usedDate;
	public final TableFieldDefine f_stationId;
	public final TableFieldDefine f_stationName;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_stopDate;
	public final TableFieldDefine f_lastDate;

	public static final String FN_ordinal ="ordinal";
	public static final String FN_cardNo ="cardNo";
	public static final String FN_password ="password";
	public static final String FN_amount ="amount";
	public static final String FN_secretKey ="secretKey";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_activePersonId ="activePersonId";
	public static final String FN_activePerson ="activePerson";
	public static final String FN_activeDate ="activeDate";
	public static final String FN_distributePersonId ="distributePersonId";
	public static final String FN_distributePerson ="distributePerson";
	public static final String FN_responsiblePersonId ="responsiblePersonId";
	public static final String FN_responsiblePerson ="responsiblePerson";
	public static final String FN_distributeDate ="distributeDate";
	public static final String FN_usedPersonId ="usedPersonId";
	public static final String FN_usedPerson ="usedPerson";
	public static final String FN_usedDate ="usedDate";
	public static final String FN_stationId ="stationId";
	public static final String FN_stationName ="stationName";
	public static final String FN_status ="status";
	public static final String FN_stopDate ="stopDate";
	public static final String FN_lastDate ="lastDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_CARD() {
		super(TABLE_NAME);
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_ordinal = field = this.table.newField(FN_ordinal, TypeFactory.NVARCHAR(30));
		field.setTitle("˳���");
		this.f_cardNo = field = this.table.newField(FN_cardNo, TypeFactory.NVARCHAR(100));
		field.setTitle("����");
		this.f_password = field = this.table.newField(FN_password, TypeFactory.NVARCHAR(100));
		field.setTitle("����");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(10,2));
		field.setTitle("��ֵ");
		this.f_secretKey = field = this.table.newField(FN_secretKey, TypeFactory.NVARCHAR(100));
		field.setTitle("��Կ");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(100));
		field.setTitle("��������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_activePersonId = field = this.table.newField(FN_activePersonId, TypeFactory.GUID);
		field.setTitle("������");
		this.f_activePerson = field = this.table.newField(FN_activePerson, TypeFactory.NVARCHAR(100));
		field.setTitle("����������");
		this.f_activeDate = field = this.table.newField(FN_activeDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_distributePersonId = field = this.table.newField(FN_distributePersonId, TypeFactory.GUID);
		field.setTitle("�ɷ���");
		this.f_distributePerson = field = this.table.newField(FN_distributePerson, TypeFactory.NVARCHAR(100));
		field.setTitle("�ɷ�������");
		this.f_responsiblePersonId = field = this.table.newField(FN_responsiblePersonId, TypeFactory.GUID);
		field.setTitle("������");
		this.f_responsiblePerson = field = this.table.newField(FN_responsiblePerson, TypeFactory.NVARCHAR(100));
		field.setTitle("����������");
		this.f_distributeDate = field = this.table.newField(FN_distributeDate, TypeFactory.DATE);
		field.setTitle("�ַ�����");
		this.f_usedPersonId = field = this.table.newField(FN_usedPersonId, TypeFactory.GUID);
		field.setTitle("ʹ����");
		this.f_usedPerson = field = this.table.newField(FN_usedPerson, TypeFactory.NVARCHAR(100));
		field.setTitle("ʹ��������");
		this.f_usedDate = field = this.table.newField(FN_usedDate, TypeFactory.DATE);
		field.setTitle("ʹ������");
		this.f_stationId = field = this.table.newField(FN_stationId, TypeFactory.GUID);
		field.setTitle("վ��id");
		this.f_stationName = field = this.table.newField(FN_stationName, TypeFactory.NVARCHAR(100));
		field.setTitle("վ������");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("״̬");
		this.f_stopDate = field = this.table.newField(FN_stopDate, TypeFactory.DATE);
		field.setTitle("��ֹ����");
		this.f_lastDate = field = this.table.newField(FN_lastDate, TypeFactory.LONG);
		field.setTitle("��Ч����");
	}

}
