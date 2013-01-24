package com.spark.psi.base.storage.organization;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.table.IndexDeclare;

public final class TB_sa_em_employee extends TableDeclarator {

	public static final String TABLE_NAME ="sa_em_employee";

	public final TableFieldDefine f_empname;
	public final TableFieldDefine f_idnum;
	public final TableFieldDefine f_birthday;
	public final TableFieldDefine f_departmentid;
	public final TableFieldDefine f_rolestatue;
	public final TableFieldDefine f_terminaldate;
	public final TableFieldDefine f_mobilephone;
	public final TableFieldDefine f_email;
	public final TableFieldDefine f_phone;
	public final TableFieldDefine f_createperson;
	public final TableFieldDefine f_createdate;
	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_empimg;
	public final TableFieldDefine f_issysuser;
	public final TableFieldDefine f_duty;
	public final TableFieldDefine f_pyempname;
	public final TableFieldDefine f_pyduty;
	public final TableFieldDefine f_roles;
	public final TableFieldDefine f_style;

	public static final String FN_empname ="empname";
	public static final String FN_idnum ="idnum";
	public static final String FN_birthday ="birthday";
	public static final String FN_departmentid ="departmentid";
	public static final String FN_rolestatue ="rolestatue";
	public static final String FN_terminaldate ="terminaldate";
	public static final String FN_mobilephone ="mobilephone";
	public static final String FN_email ="email";
	public static final String FN_phone ="phone";
	public static final String FN_createperson ="createperson";
	public static final String FN_createdate ="createdate";
	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_empimg ="empimg";
	public static final String FN_issysuser ="issysuser";
	public static final String FN_duty ="duty";
	public static final String FN_pyempname ="pyempname";
	public static final String FN_pyduty ="pyduty";
	public static final String FN_roles ="roles";
	public static final String FN_style ="style";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_sa_em_employee() {
		super(TABLE_NAME);
		this.table.setTitle("员工基础信息表");
		TableFieldDeclare field;
		this.f_empname = field = this.table.newField(FN_empname, TypeFactory.NVARCHAR(20));
		field.setTitle("员工姓名");
		field.setKeepValid(true);
		this.f_idnum = field = this.table.newField(FN_idnum, TypeFactory.NVARCHAR(50));
		field.setTitle("证件号码");
		this.f_birthday = field = this.table.newField(FN_birthday, TypeFactory.DATE);
		field.setTitle("出生日期");
		this.f_departmentid = field = this.table.newField(FN_departmentid, TypeFactory.GUID);
		field.setTitle("部门");
		this.f_rolestatue = field = this.table.newField(FN_rolestatue, TypeFactory.NVARCHAR(2));
		field.setTitle("员工状态");
		this.f_terminaldate = field = this.table.newField(FN_terminaldate, TypeFactory.DATE);
		field.setTitle("离职日期");
		this.f_mobilephone = field = this.table.newField(FN_mobilephone, TypeFactory.NVARCHAR(20));
		field.setTitle("手机");
		this.f_email = field = this.table.newField(FN_email, TypeFactory.NVARCHAR(50));
		field.setTitle("电子邮件");
		this.f_phone = field = this.table.newField(FN_phone, TypeFactory.NVARCHAR(20));
		field.setTitle("工作电话");
		this.f_createperson = field = this.table.newField(FN_createperson, TypeFactory.NVARCHAR(10));
		field.setTitle("创建人");
		this.f_createdate = field = this.table.newField(FN_createdate, TypeFactory.DATE);
		field.setTitle("创建日期1");
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_empimg = field = this.table.newField(FN_empimg, TypeFactory.GUID);
		field.setTitle("头像");
		this.f_issysuser = field = this.table.newField(FN_issysuser, TypeFactory.CHAR(2));
		field.setTitle("是否系统用户");
		this.f_duty = field = this.table.newField(FN_duty, TypeFactory.NVARCHAR(100));
		field.setTitle("岗位");
		this.f_pyempname = field = this.table.newField(FN_pyempname, TypeFactory.NVARCHAR(50));
		field.setTitle("姓名拼音");
		this.f_pyduty = field = this.table.newField(FN_pyduty, TypeFactory.NVARCHAR(50));
		field.setTitle("岗位拼音");
		this.f_roles = field = this.table.newField(FN_roles, TypeFactory.INT);
		field.setTitle("角色");
		this.f_style = field = this.table.newField(FN_style, TypeFactory.NVARCHAR(255));
		field.setTitle("界面风格");
		IndexDeclare index;
		this.table.newIndex("INDEX_EMPLOYEE_TENANTS",f_tenantsGuid,f_departmentid);
		index = this.table.newIndex("INDEX_EMPLOYEE_MOBILE",f_tenantsGuid,f_mobilephone);
		index.setUnique(true);
	}

}
