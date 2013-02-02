package com.spark.cms.storage.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_MEMBER_INFO extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_MEMBER_INFO";

	public final TableFieldDefine f_memberId;
	public final TableFieldDefine f_memberName;
	public final TableFieldDefine f_memberType;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_telephone;
	public final TableFieldDefine f_birthday;
	public final TableFieldDefine f_sex;
	public final TableFieldDefine f_province;
	public final TableFieldDefine f_city;
	public final TableFieldDefine f_town;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_identity;
	public final TableFieldDefine f_maritalStatus;
	public final TableFieldDefine f_livingConditions;

	public static final String FN_memberId ="memberId";
	public static final String FN_memberName ="memberName";
	public static final String FN_memberType ="memberType";
	public static final String FN_mobile ="mobile";
	public static final String FN_telephone ="telephone";
	public static final String FN_birthday ="birthday";
	public static final String FN_sex ="sex";
	public static final String FN_province ="province";
	public static final String FN_city ="city";
	public static final String FN_town ="town";
	public static final String FN_address ="address";
	public static final String FN_identity ="identity";
	public static final String FN_maritalStatus ="maritalStatus";
	public static final String FN_livingConditions ="livingConditions";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_MEMBER_INFO() {
		super(TABLE_NAME);
		this.table.setTitle("��Ա��ϸ��Ϣ");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_memberId = field = this.table.newField(FN_memberId, TypeFactory.NVARCHAR(32));
		field.setTitle("��Աid");
		this.f_memberName = field = this.table.newField(FN_memberName, TypeFactory.NVARCHAR(30));
		field.setTitle("����");
		this.f_memberType = field = this.table.newField(FN_memberType, TypeFactory.CHAR(10));
		field.setTitle("��Ա����");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.NVARCHAR(15));
		field.setTitle("�ֻ���");
		this.f_telephone = field = this.table.newField(FN_telephone, TypeFactory.NVARCHAR(20));
		field.setTitle("�绰");
		this.f_birthday = field = this.table.newField(FN_birthday, TypeFactory.LONG);
		field.setTitle("����");
		this.f_sex = field = this.table.newField(FN_sex, TypeFactory.CHAR(2));
		field.setTitle("�Ա�");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.NVARCHAR(12));
		field.setTitle("��ס��ʡ");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.NVARCHAR(12));
		field.setTitle("��ס����");
		this.f_town = field = this.table.newField(FN_town, TypeFactory.NVARCHAR(12));
		field.setTitle("��ס����");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ϸ��ַ");
		this.f_identity = field = this.table.newField(FN_identity, TypeFactory.CHAR(2));
		field.setTitle("���");
		this.f_maritalStatus = field = this.table.newField(FN_maritalStatus, TypeFactory.CHAR(2));
		field.setTitle("����״��");
		this.f_livingConditions = field = this.table.newField(FN_livingConditions, TypeFactory.CHAR(2));
		field.setTitle("��ס״��");
	}

}
