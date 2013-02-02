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

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_MEMBER_INFO() {
		super(TABLE_NAME);
		this.table.setTitle("会员详细信息");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_memberId = field = this.table.newField(FN_memberId, TypeFactory.NVARCHAR(32));
		field.setTitle("会员id");
		this.f_memberName = field = this.table.newField(FN_memberName, TypeFactory.NVARCHAR(30));
		field.setTitle("姓名");
		this.f_memberType = field = this.table.newField(FN_memberType, TypeFactory.CHAR(10));
		field.setTitle("会员类型");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.NVARCHAR(15));
		field.setTitle("手机号");
		this.f_telephone = field = this.table.newField(FN_telephone, TypeFactory.NVARCHAR(20));
		field.setTitle("电话");
		this.f_birthday = field = this.table.newField(FN_birthday, TypeFactory.LONG);
		field.setTitle("生日");
		this.f_sex = field = this.table.newField(FN_sex, TypeFactory.CHAR(2));
		field.setTitle("性别");
		this.f_province = field = this.table.newField(FN_province, TypeFactory.NVARCHAR(12));
		field.setTitle("居住地省");
		this.f_city = field = this.table.newField(FN_city, TypeFactory.NVARCHAR(12));
		field.setTitle("居住地市");
		this.f_town = field = this.table.newField(FN_town, TypeFactory.NVARCHAR(12));
		field.setTitle("居住地县");
		this.f_address = field = this.table.newField(FN_address, TypeFactory.NVARCHAR(1000));
		field.setTitle("详细地址");
		this.f_identity = field = this.table.newField(FN_identity, TypeFactory.CHAR(2));
		field.setTitle("身份");
		this.f_maritalStatus = field = this.table.newField(FN_maritalStatus, TypeFactory.CHAR(2));
		field.setTitle("婚姻状况");
		this.f_livingConditions = field = this.table.newField(FN_livingConditions, TypeFactory.CHAR(2));
		field.setTitle("居住状况");
	}

}
