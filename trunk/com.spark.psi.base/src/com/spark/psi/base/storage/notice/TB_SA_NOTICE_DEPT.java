package com.spark.psi.base.storage.notice;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_NOTICE_DEPT extends TableDeclarator {

	public static final String TABLE_NAME ="SA_NOTICE_DEPT";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_noticeGuid;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_deptName;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_noticeGuid ="noticeGuid";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_deptName ="deptName";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_NOTICE_DEPT() {
		super(TABLE_NAME);
		this.table.setDescription("���ڴ洢���淢����Χ");
		this.table.setTitle("�����벿���м��");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_noticeGuid = field = this.table.newField(FN_noticeGuid, TypeFactory.GUID);
		field.setTitle("����GUID");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("����GUID");
		this.f_deptName = field = this.table.newField(FN_deptName, TypeFactory.NVARCHAR(20));
		field.setTitle("��������");
	}

}
