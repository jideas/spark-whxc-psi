package com.spark.psi.base.storage.tenant;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.table.IndexDeclare;

public final class TB_SA_TENANTS extends TableDeclarator {

	public static final String TABLE_NAME ="SA_TENANTS";

	public final TableFieldDefine f_code;
	public final TableFieldDefine f_name;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_createPerson;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_updatePerson;
	public final TableFieldDefine f_updateDate;
	public final TableFieldDefine f_examPerson;
	public final TableFieldDefine f_examDate;
	public final TableFieldDefine f_examstatus;
	public final TableFieldDefine f_examstatusFunrecid;
	public final TableFieldDefine f_namePy;
	public final TableFieldDefine f_backreason;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_tenantsType;
	public final TableFieldDefine f_configstatus;

	public static final String FN_code ="code";
	public static final String FN_name ="name";
	public static final String FN_status ="status";
	public static final String FN_createPerson ="createPerson";
	public static final String FN_createDate ="createDate";
	public static final String FN_updatePerson ="updatePerson";
	public static final String FN_updateDate ="updateDate";
	public static final String FN_examPerson ="examPerson";
	public static final String FN_examDate ="examDate";
	public static final String FN_examstatus ="examstatus";
	public static final String FN_examstatusFunrecid ="examstatusFunrecid";
	public static final String FN_namePy ="namePy";
	public static final String FN_backreason ="backreason";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_tenantsType ="tenantsType";
	public static final String FN_configstatus ="configstatus";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_TENANTS() {
		super(TABLE_NAME);
		this.table.setTitle("�⻧������Ϣ��");
		TableFieldDeclare field;
		this.f_code = field = this.table.newField(FN_code, TypeFactory.NVARCHAR(20));
		field.setTitle("�⻧���");
		this.f_name = field = this.table.newField(FN_name, TypeFactory.NVARCHAR(50));
		field.setTitle("����");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.NVARCHAR(2));
		field.setTitle("״̬");
		this.f_createPerson = field = this.table.newField(FN_createPerson, TypeFactory.GUID);
		field.setTitle("������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_updatePerson = field = this.table.newField(FN_updatePerson, TypeFactory.GUID);
		field.setTitle("����޸���");
		this.f_updateDate = field = this.table.newField(FN_updateDate, TypeFactory.DATE);
		field.setTitle("����޸�����");
		this.f_examPerson = field = this.table.newField(FN_examPerson, TypeFactory.GUID);
		field.setTitle("�����");
		this.f_examDate = field = this.table.newField(FN_examDate, TypeFactory.DATE);
		field.setTitle("�������");
		this.f_examstatus = field = this.table.newField(FN_examstatus, TypeFactory.VARCHAR(1));
		field.setTitle("���״̬1");
		this.f_examstatusFunrecid = field = this.table.newField(FN_examstatusFunrecid, TypeFactory.GUID);
		field.setTitle("���״̬��ʶ");
		this.f_namePy = field = this.table.newField(FN_namePy, TypeFactory.NVARCHAR(50));
		field.setTitle("�⻧����ƴ����д");
		this.f_backreason = field = this.table.newField(FN_backreason, TypeFactory.NVARCHAR(200));
		field.setTitle("����ԭ��");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("���ű��");
		this.f_tenantsType = field = this.table.newField(FN_tenantsType, TypeFactory.NVARCHAR(2));
		field.setTitle("�⻧����");
		field.setKeepValid(true);
		this.f_configstatus = field = this.table.newField(FN_configstatus, TypeFactory.GUID);
		field.setTitle("��������״̬");
		IndexDeclare index;
		index = this.table.newIndex("INDEX_TENANTS_NAME",f_name);
		index.setUnique(true);
	}

}
