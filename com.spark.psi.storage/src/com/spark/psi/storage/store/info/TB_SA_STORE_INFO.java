package com.spark.psi.storage.store.info;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.table.IndexDeclare;

public final class TB_SA_STORE_INFO extends TableDeclarator {

	public static final String TABLE_NAME ="SA_STORE_INFO";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_name;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_StoreStatus;
	public final TableFieldDefine f_province;
	public final TableFieldDefine f_city;
	public final TableFieldDefine f_country;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_postCode;
	public final TableFieldDefine f_phoneNo;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_consignee;
	public final TableFieldDefine f_isDirectToCustom;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createdDate;
	public final TableFieldDefine f_createPersonName;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_name ="name";
	public static final String FN_namePY ="namePY";
	public static final String FN_StoreStatus ="StoreStatus";
	public static final String FN_province ="province";
	public static final String FN_city ="city";
	public static final String FN_country ="country";
	public static final String FN_address ="address";
	public static final String FN_postCode ="postCode";
	public static final String FN_phoneNo ="phoneNo";
	public static final String FN_mobile ="mobile";
	public static final String FN_consignee ="consignee";
	public static final String FN_isDirectToCustom ="isDirectToCustom";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createdDate ="createdDate";
	public static final String FN_createPersonName ="createPersonName";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_STORE_INFO() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_name = field = this.table.newField(FN_name, TypeFactory.VARCHAR(50));
		field.setTitle("�ֿ�����");
		this.f_namePY = this.table.newField(FN_namePY, TypeFactory.VARCHAR(50));
		this.f_StoreStatus = field = this.table.newField(FN_StoreStatus, TypeFactory.CHAR(2));
		field.setTitle("�ֿ�״̬");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.VARCHAR(12));
		field.setTitle("ʡ");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.VARCHAR(12));
		field.setTitle("��");
		this.f_country = field = this.table.newField(FN_country, TypeFactory.VARCHAR(12));
		field.setTitle("��");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.VARCHAR(100));
		field.setTitle("�ֿ��ַ");
		this.f_postCode = field = this.table.newField(FN_postCode, TypeFactory.VARCHAR(10));
		field.setTitle("��������");
		this.f_phoneNo = field = this.table.newField(FN_phoneNo, TypeFactory.VARCHAR(20));
		field.setTitle("�绰����");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.VARCHAR(20));
		field.setTitle("�ֻ�����");
		this.f_consignee = field = this.table.newField(FN_consignee, TypeFactory.VARCHAR(20));
		field.setTitle("�ջ���");
		this.f_isDirectToCustom = field = this.table.newField(FN_isDirectToCustom, TypeFactory.CHAR(2));
		field.setTitle("�Ƿ�Ӧ��ֱ��");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.GUID);
		field.setTitle("������");
		this.f_createdDate = field = this.table.newField(FN_createdDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_createPersonName = field = this.table.newField(FN_createPersonName, TypeFactory.VARCHAR(10));
		field.setTitle("����������");
		IndexDeclare index;
		index = this.table.newIndex("index_storename",f_tenantsGuid,f_name);
		index.setUnique(true);
	}

}
