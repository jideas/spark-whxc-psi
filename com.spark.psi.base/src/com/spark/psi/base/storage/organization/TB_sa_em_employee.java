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

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_sa_em_employee() {
		super(TABLE_NAME);
		this.table.setTitle("Ա��������Ϣ��");
		TableFieldDeclare field;
		this.f_empname = field = this.table.newField(FN_empname, TypeFactory.NVARCHAR(20));
		field.setTitle("Ա������");
		field.setKeepValid(true);
		this.f_idnum = field = this.table.newField(FN_idnum, TypeFactory.NVARCHAR(50));
		field.setTitle("֤������");
		this.f_birthday = field = this.table.newField(FN_birthday, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_departmentid = field = this.table.newField(FN_departmentid, TypeFactory.GUID);
		field.setTitle("����");
		this.f_rolestatue = field = this.table.newField(FN_rolestatue, TypeFactory.NVARCHAR(2));
		field.setTitle("Ա��״̬");
		this.f_terminaldate = field = this.table.newField(FN_terminaldate, TypeFactory.DATE);
		field.setTitle("��ְ����");
		this.f_mobilephone = field = this.table.newField(FN_mobilephone, TypeFactory.NVARCHAR(20));
		field.setTitle("�ֻ�");
		this.f_email = field = this.table.newField(FN_email, TypeFactory.NVARCHAR(50));
		field.setTitle("�����ʼ�");
		this.f_phone = field = this.table.newField(FN_phone, TypeFactory.NVARCHAR(20));
		field.setTitle("�����绰");
		this.f_createperson = field = this.table.newField(FN_createperson, TypeFactory.NVARCHAR(10));
		field.setTitle("������");
		this.f_createdate = field = this.table.newField(FN_createdate, TypeFactory.DATE);
		field.setTitle("��������1");
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_empimg = field = this.table.newField(FN_empimg, TypeFactory.GUID);
		field.setTitle("ͷ��");
		this.f_issysuser = field = this.table.newField(FN_issysuser, TypeFactory.CHAR(2));
		field.setTitle("�Ƿ�ϵͳ�û�");
		this.f_duty = field = this.table.newField(FN_duty, TypeFactory.NVARCHAR(100));
		field.setTitle("��λ");
		this.f_pyempname = field = this.table.newField(FN_pyempname, TypeFactory.NVARCHAR(50));
		field.setTitle("����ƴ��");
		this.f_pyduty = field = this.table.newField(FN_pyduty, TypeFactory.NVARCHAR(50));
		field.setTitle("��λƴ��");
		this.f_roles = field = this.table.newField(FN_roles, TypeFactory.INT);
		field.setTitle("��ɫ");
		this.f_style = field = this.table.newField(FN_style, TypeFactory.NVARCHAR(255));
		field.setTitle("������");
		IndexDeclare index;
		this.table.newIndex("INDEX_EMPLOYEE_TENANTS",f_tenantsGuid,f_departmentid);
		index = this.table.newIndex("INDEX_EMPLOYEE_MOBILE",f_tenantsGuid,f_mobilephone);
		index.setUnique(true);
	}

}
