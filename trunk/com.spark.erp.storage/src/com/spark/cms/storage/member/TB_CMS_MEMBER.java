package com.spark.cms.storage.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_MEMBER extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_MEMBER";

	public final TableFieldDefine f_username;
	public final TableFieldDefine f_password;
	public final TableFieldDefine f_memberName;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_email;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_invitationCode;
	public final TableFieldDefine f_isOffical;
	public final TableFieldDefine f_code;
	public final TableFieldDefine f_lastLoginDate;
	public final TableFieldDefine f_checkedMobile;

	public static final String FN_username ="username";
	public static final String FN_password ="password";
	public static final String FN_memberName ="memberName";
	public static final String FN_mobile ="mobile";
	public static final String FN_email ="email";
	public static final String FN_status ="status";
	public static final String FN_invitationCode ="invitationCode";
	public static final String FN_isOffical ="isOffical";
	public static final String FN_code ="code";
	public static final String FN_lastLoginDate ="lastLoginDate";
	public static final String FN_checkedMobile ="checkedMobile";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_MEMBER() {
		super(TABLE_NAME);
		this.table.setTitle("登陆用户表");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_username = field = this.table.newField(FN_username, TypeFactory.NVARCHAR(30));
		field.setTitle("登陆名");
		this.f_password = field = this.table.newField(FN_password, TypeFactory.NVARCHAR(100));
		field.setTitle("密码");
		this.f_memberName = field = this.table.newField(FN_memberName, TypeFactory.NVARCHAR(30));
		field.setTitle("会员名");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.NVARCHAR(15));
		field.setTitle("手机号");
		this.f_email = field = this.table.newField(FN_email, TypeFactory.NVARCHAR(30));
		field.setTitle("邮箱");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("状态");
		this.f_invitationCode = field = this.table.newField(FN_invitationCode, TypeFactory.NVARCHAR(30));
		field.setTitle("邀请码");
		this.f_isOffical = field = this.table.newField(FN_isOffical, TypeFactory.BOOLEAN);
		field.setTitle("正式会员");
		this.f_code = field = this.table.newField(FN_code, TypeFactory.NVARCHAR(20));
		field.setTitle("编号");
		this.f_lastLoginDate = field = this.table.newField(FN_lastLoginDate, TypeFactory.DATE);
		field.setTitle("最后登录时间");
		this.f_checkedMobile = this.table.newField(FN_checkedMobile, TypeFactory.BOOLEAN);
	}

}
