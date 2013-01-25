package com.spark.psi.storage.report.cusprovider;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_REPORT_CUSTOMER extends TableDeclarator {

	public static final String TABLE_NAME ="SA_REPORT_CUSTOMER";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_employeeId;
	public final TableFieldDefine f_dateNo;
	public final TableFieldDefine f_monthNo;
	public final TableFieldDefine f_quarter;
	public final TableFieldDefine f_yearNo;
	public final TableFieldDefine f_officialCount;
	public final TableFieldDefine f_potentialCount;
	public final TableFieldDefine f_todayIncrement;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_employeeId ="employeeId";
	public static final String FN_dateNo ="dateNo";
	public static final String FN_monthNo ="monthNo";
	public static final String FN_quarter ="quarter";
	public static final String FN_yearNo ="yearNo";
	public static final String FN_officialCount ="officialCount";
	public static final String FN_potentialCount ="potentialCount";
	public static final String FN_todayIncrement ="todayIncrement";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_REPORT_CUSTOMER() {
		super(TABLE_NAME);
		this.table.setDescription("�ͻ�����");
		this.table.setTitle("�ͻ�����");
		this.table.setCategory("��������");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("����GUID");
		this.f_employeeId = field = this.table.newField(FN_employeeId, TypeFactory.GUID);
		field.setTitle("Ա�����");
		this.f_dateNo = field = this.table.newField(FN_dateNo, TypeFactory.INT);
		field.setTitle("�պ�");
		this.f_monthNo = field = this.table.newField(FN_monthNo, TypeFactory.INT);
		field.setTitle("�ں�");
		this.f_quarter = field = this.table.newField(FN_quarter, TypeFactory.INT);
		field.setTitle("����");
		this.f_yearNo = field = this.table.newField(FN_yearNo, TypeFactory.INT);
		field.setTitle("���");
		this.f_officialCount = field = this.table.newField(FN_officialCount, TypeFactory.INT);
		field.setTitle("��ʽ�ͻ�����");
		this.f_potentialCount = field = this.table.newField(FN_potentialCount, TypeFactory.INT);
		field.setTitle("Ǳ�ڿͻ�����");
		this.f_todayIncrement = field = this.table.newField(FN_todayIncrement, TypeFactory.INT);
		field.setTitle("��������");
	}

}
