package com.spark.psi.storage.customer;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_SALE_SENDADDERS extends TableDeclarator {

	public static final String TABLE_NAME ="SA_SALE_SENDADDERS";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_cusproGuid;
	public final TableFieldDefine f_cusproType;
	public final TableFieldDefine f_province;
	public final TableFieldDefine f_city;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_addressDetail;
	public final TableFieldDefine f_linkman;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_telephone;
	public final TableFieldDefine f_postCode;
	public final TableFieldDefine f_lastDate;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_cusproGuid ="cusproGuid";
	public static final String FN_cusproType ="cusproType";
	public static final String FN_province ="province";
	public static final String FN_city ="city";
	public static final String FN_address ="address";
	public static final String FN_addressDetail ="addressDetail";
	public static final String FN_linkman ="linkman";
	public static final String FN_mobile ="mobile";
	public static final String FN_telephone ="telephone";
	public static final String FN_postCode ="postCode";
	public static final String FN_lastDate ="lastDate";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_SALE_SENDADDERS() {
		super(TABLE_NAME);
		this.table.setTitle("�ͻ�/�ջ���ַ��");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		field.setKeepValid(true);
		this.f_cusproGuid = field = this.table.newField(FN_cusproGuid, TypeFactory.GUID);
		field.setTitle("�ͻ���Ӧ�̱��");
		this.f_cusproType = field = this.table.newField(FN_cusproType, TypeFactory.CHAR(2));
		field.setTitle("�ͻ���Ӧ������");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.NVARCHAR(20));
		field.setTitle("ʡ��");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.NVARCHAR(20));
		field.setTitle("����");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(100));
		field.setTitle("��ַ");
		this.f_addressDetail = field = this.table.newField(FN_addressDetail, TypeFactory.VARCHAR(100));
		field.setTitle("��ϸ��ַ");
		this.f_linkman = field = this.table.newField(FN_linkman, TypeFactory.VARCHAR(20));
		field.setTitle("�ջ���");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.VARCHAR(12));
		field.setTitle("�ֻ�");
		this.f_telephone = field = this.table.newField(FN_telephone, TypeFactory.VARCHAR(15));
		field.setTitle("�̻�");
		this.f_postCode = field = this.table.newField(FN_postCode, TypeFactory.NVARCHAR(10));
		field.setTitle("�ʱ�");
		this.f_lastDate = field = this.table.newField(FN_lastDate, TypeFactory.DATE);
		field.setTitle("���ʹ������");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.GUID);
		field.setTitle("������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.table.newIndex("INDEX_SALE_SENDADDERS",f_tenantsGuid,f_cusproGuid);
	}

}
