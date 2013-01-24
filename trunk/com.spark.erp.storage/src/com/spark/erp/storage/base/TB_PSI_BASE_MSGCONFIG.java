package com.spark.erp.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_BASE_MSGCONFIG extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_BASE_MSGCONFIG";

	public final TableFieldDefine f_activing;
	public final TableFieldDefine f_balanceUrl;
	public final TableFieldDefine f_submitUrl;
	public final TableFieldDefine f_userNameKey;
	public final TableFieldDefine f_companyAccountKey;
	public final TableFieldDefine f_companyAccount;
	public final TableFieldDefine f_passwordKey;
	public final TableFieldDefine f_phoneNumberKey;
	public final TableFieldDefine f_msgContentKey;
	public final TableFieldDefine f_userName;
	public final TableFieldDefine f_password;
	public final TableFieldDefine f_secretKey;
	public final TableFieldDefine f_modfiyPerson;
	public final TableFieldDefine f_modfiyDate;

	public static final String FN_activing ="activing";
	public static final String FN_balanceUrl ="balanceUrl";
	public static final String FN_submitUrl ="submitUrl";
	public static final String FN_userNameKey ="userNameKey";
	public static final String FN_companyAccountKey ="companyAccountKey";
	public static final String FN_companyAccount ="companyAccount";
	public static final String FN_passwordKey ="passwordKey";
	public static final String FN_phoneNumberKey ="phoneNumberKey";
	public static final String FN_msgContentKey ="msgContentKey";
	public static final String FN_userName ="userName";
	public static final String FN_password ="password";
	public static final String FN_secretKey ="secretKey";
	public static final String FN_modfiyPerson ="modfiyPerson";
	public static final String FN_modfiyDate ="modfiyDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_BASE_MSGCONFIG() {
		super(TABLE_NAME);
		this.table.setTitle("短信功能配置信息表");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_activing = this.table.newField(FN_activing, TypeFactory.BOOLEAN);
		this.f_balanceUrl = this.table.newField(FN_balanceUrl, TypeFactory.NVARCHAR(300));
		this.f_submitUrl = this.table.newField(FN_submitUrl, TypeFactory.NVARCHAR(300));
		this.f_userNameKey = this.table.newField(FN_userNameKey, TypeFactory.NVARCHAR(30));
		this.f_companyAccountKey = this.table.newField(FN_companyAccountKey, TypeFactory.NVARCHAR(30));
		this.f_companyAccount = this.table.newField(FN_companyAccount, TypeFactory.NVARCHAR(100));
		this.f_passwordKey = this.table.newField(FN_passwordKey, TypeFactory.NVARCHAR(30));
		this.f_phoneNumberKey = this.table.newField(FN_phoneNumberKey, TypeFactory.NVARCHAR(30));
		this.f_msgContentKey = this.table.newField(FN_msgContentKey, TypeFactory.NVARCHAR(30));
		this.f_userName = this.table.newField(FN_userName, TypeFactory.NVARCHAR(100));
		this.f_password = this.table.newField(FN_password, TypeFactory.NVARCHAR(500));
		this.f_secretKey = this.table.newField(FN_secretKey, TypeFactory.NVARCHAR(500));
		this.f_modfiyPerson = field = this.table.newField(FN_modfiyPerson, TypeFactory.NVARCHAR(30));
		field.setTitle("最后修改人");
		this.f_modfiyDate = field = this.table.newField(FN_modfiyDate, TypeFactory.DATE);
		field.setTitle("最后修改时间");
	}

}
