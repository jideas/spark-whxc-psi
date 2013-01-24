package com.spark.psi.base.storage.organization;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_PERSONNEL_DEPT extends TableDeclarator {

	public static final String TABLE_NAME ="SA_PERSONNEL_DEPT";

	public final TableFieldDefine f_deptnumber;
	public final TableFieldDefine f_Tenantsrecid;
	public final TableFieldDefine f_parent;
	public final TableFieldDefine f_name;
	public final TableFieldDefine f_dtype;
	public final TableFieldDefine f_note;
	public final TableFieldDefine f_rtime;
	public final TableFieldDefine f_rman;
	public final TableFieldDefine f_vaild;

	public static final String FN_deptnumber ="deptnumber";
	public static final String FN_Tenantsrecid ="Tenantsrecid";
	public static final String FN_parent ="parent";
	public static final String FN_name ="name";
	public static final String FN_dtype ="dtype";
	public static final String FN_note ="note";
	public static final String FN_rtime ="rtime";
	public static final String FN_rman ="rman";
	public static final String FN_vaild ="vaild";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_PERSONNEL_DEPT() {
		super(TABLE_NAME);
		this.table.setTitle("��֯�ṹ��");
		TableFieldDeclare field;
		this.f_deptnumber = field = this.table.newField(FN_deptnumber, TypeFactory.NVARCHAR(25));
		field.setTitle("���ű��");
		this.f_Tenantsrecid = field = this.table.newField(FN_Tenantsrecid, TypeFactory.GUID);
		field.setTitle("�⻧id");
		this.f_parent = field = this.table.newField(FN_parent, TypeFactory.GUID);
		field.setTitle("���ڵ�");
		this.f_name = field = this.table.newField(FN_name, TypeFactory.NVARCHAR(25));
		field.setTitle("��������");
		this.f_dtype = field = this.table.newField(FN_dtype, TypeFactory.CHAR(1));
		field.setTitle("���ͣ���˾or���ţ�");
		this.f_note = this.table.newField(FN_note, TypeFactory.NVARCHAR(100));
		this.f_rtime = this.table.newField(FN_rtime, TypeFactory.DATE);
		this.f_rman = this.table.newField(FN_rman, TypeFactory.GUID);
		this.f_vaild = field = this.table.newField(FN_vaild, TypeFactory.NCHAR(1));
		field.setTitle("�Ƿ���Ч��1��Ч��");
		this.table.newIndex("LK_TENANTS_RECID",f_Tenantsrecid);
	}

}
