package com.spark.b2c.storage.base.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_B2C_Base_Member extends TableDeclarator {

	public static final String TABLE_NAME ="B2C_Base_Member";

	public final TableFieldDefine f_userId;
	public final TableFieldDefine f_realName;
	public final TableFieldDefine f_username;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_email;
	public final TableFieldDefine f_extendCode;
	public final TableFieldDefine f_level;
	public final TableFieldDefine f_sex;
	public final TableFieldDefine f_birthday;
	public final TableFieldDefine f_subscribeFlag;
	public final TableFieldDefine f_createDate;

	public static final String FN_userId ="userId";
	public static final String FN_realName ="realName";
	public static final String FN_username ="username";
	public static final String FN_mobile ="mobile";
	public static final String FN_email ="email";
	public static final String FN_extendCode ="extendCode";
	public static final String FN_level ="level";
	public static final String FN_sex ="sex";
	public static final String FN_birthday ="birthday";
	public static final String FN_subscribeFlag ="subscribeFlag";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_B2C_Base_Member() {
		super(TABLE_NAME);
		this.table.setTitle("会员信息表");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_userId = field = this.table.newField(FN_userId, TypeFactory.GUID);
		field.setTitle("用户id");
		this.f_realName = field = this.table.newField(FN_realName, TypeFactory.NVARCHAR(20));
		field.setTitle("真实姓名");
		this.f_username = field = this.table.newField(FN_username, TypeFactory.NVARCHAR(20));
		field.setTitle("用户名");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.NVARCHAR(20));
		field.setTitle("手机号码");
		this.f_email = field = this.table.newField(FN_email, TypeFactory.NVARCHAR(30));
		field.setTitle("邮箱地址");
		this.f_extendCode = field = this.table.newField(FN_extendCode, TypeFactory.NVARCHAR(30));
		field.setTitle("推广码");
		this.f_level = field = this.table.newField(FN_level, TypeFactory.CHAR(2));
		field.setTitle("会员等级");
		this.f_sex = field = this.table.newField(FN_sex, TypeFactory.BOOLEAN);
		field.setTitle("性别");
		this.f_birthday = field = this.table.newField(FN_birthday, TypeFactory.DATE);
		field.setTitle("生日");
		this.f_subscribeFlag = field = this.table.newField(FN_subscribeFlag, TypeFactory.BOOLEAN);
		field.setTitle("是否订阅邮件");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建时间");
	}

}
