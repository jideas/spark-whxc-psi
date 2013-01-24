package com.spark.cms.storage.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_MEMBER_ADDRESS extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_MEMBER_ADDRESS";

	public final TableFieldDefine f_memberid;
	public final TableFieldDefine f_consignee;
	public final TableFieldDefine f_province;
	public final TableFieldDefine f_city;
	public final TableFieldDefine f_town;
	public final TableFieldDefine f_stationId;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_postcode;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_telephone;
	public final TableFieldDefine f_isDefault;

	public static final String FN_memberid ="memberid";
	public static final String FN_consignee ="consignee";
	public static final String FN_province ="province";
	public static final String FN_city ="city";
	public static final String FN_town ="town";
	public static final String FN_stationId ="stationId";
	public static final String FN_address ="address";
	public static final String FN_postcode ="postcode";
	public static final String FN_mobile ="mobile";
	public static final String FN_telephone ="telephone";
	public static final String FN_isDefault ="isDefault";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_MEMBER_ADDRESS() {
		super(TABLE_NAME);
		this.table.setTitle("�ջ���ַ����");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_memberid = field = this.table.newField(FN_memberid, TypeFactory.GUID);
		field.setTitle("��Աid");
		this.f_consignee = field = this.table.newField(FN_consignee, TypeFactory.NVARCHAR(20));
		field.setTitle("�ջ���");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.NVARCHAR(20));
		field.setTitle("ʡ");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.NVARCHAR(20));
		field.setTitle("��");
		this.f_town = field = this.table.newField(FN_town, TypeFactory.NVARCHAR(30));
		field.setTitle("��");
		this.f_stationId = field = this.table.newField(FN_stationId, TypeFactory.GUID);
		field.setTitle("վ��id");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(200));
		field.setTitle("��ϸ��ַ");
		this.f_postcode = field = this.table.newField(FN_postcode, TypeFactory.NVARCHAR(6));
		field.setTitle("�ʱ�");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.NVARCHAR(15));
		field.setTitle("�ֻ�����");
		this.f_telephone = field = this.table.newField(FN_telephone, TypeFactory.NVARCHAR(20));
		field.setTitle("�̶��绰");
		this.f_isDefault = field = this.table.newField(FN_isDefault, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ�Ĭ��");
	}

}
