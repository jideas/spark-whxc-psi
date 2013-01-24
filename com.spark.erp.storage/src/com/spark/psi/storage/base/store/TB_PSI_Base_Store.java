package com.spark.psi.storage.base.store;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_Store extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_Store";

	public final TableFieldDefine f_storeNo;
	public final TableFieldDefine f_storeName;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_consignee;
	public final TableFieldDefine f_mobileNo;
	public final TableFieldDefine f_province;
	public final TableFieldDefine f_city;
	public final TableFieldDefine f_town;
	public final TableFieldDefine f_address;
	public final TableFieldDefine f_postCode;
	public final TableFieldDefine f_shelfCount;
	public final TableFieldDefine f_defaultTiersCount;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_storeType;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;

	public static final String FN_storeNo ="storeNo";
	public static final String FN_storeName ="storeName";
	public static final String FN_namePY ="namePY";
	public static final String FN_consignee ="consignee";
	public static final String FN_mobileNo ="mobileNo";
	public static final String FN_province ="province";
	public static final String FN_city ="city";
	public static final String FN_town ="town";
	public static final String FN_address ="address";
	public static final String FN_postCode ="postCode";
	public static final String FN_shelfCount ="shelfCount";
	public static final String FN_defaultTiersCount ="defaultTiersCount";
	public static final String FN_status ="status";
	public static final String FN_storeType ="storeType";
	public static final String FN_createDate ="createDate";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Base_Store() {
		super(TABLE_NAME);
		this.table.setTitle("�ֿ�����");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_storeNo = field = this.table.newField(FN_storeNo, TypeFactory.NVARCHAR(30));
		field.setTitle("�ֿ���");
		this.f_storeName = field = this.table.newField(FN_storeName, TypeFactory.NVARCHAR(50));
		field.setTitle("�ֿ�����");
		this.f_namePY = field = this.table.newField(FN_namePY, TypeFactory.NVARCHAR(30));
		field.setTitle("����ƴ��");
		this.f_consignee = field = this.table.newField(FN_consignee, TypeFactory.NVARCHAR(30));
		field.setTitle("�ջ���");
		this.f_mobileNo = field = this.table.newField(FN_mobileNo, TypeFactory.NVARCHAR(30));
		field.setTitle("�ֻ���");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.NVARCHAR(20));
		field.setTitle("ʡ��");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.NVARCHAR(20));
		field.setTitle("����");
		this.f_town = field = this.table.newField(FN_town, TypeFactory.NVARCHAR(100));
		field.setTitle("����");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(100));
		field.setTitle("��ַ");
		this.f_postCode = field = this.table.newField(FN_postCode, TypeFactory.NVARCHAR(10));
		field.setTitle("�ʱ�");
		this.f_shelfCount = field = this.table.newField(FN_shelfCount, TypeFactory.INT);
		field.setTitle("��λ����");
		this.f_defaultTiersCount = field = this.table.newField(FN_defaultTiersCount, TypeFactory.INT);
		field.setTitle("Ĭ�ϲ���");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("�ֿ�״̬");
		this.f_storeType = field = this.table.newField(FN_storeType, TypeFactory.CHAR(2));
		field.setTitle("�ֿ�����");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������Guid");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("������");
	}

}
