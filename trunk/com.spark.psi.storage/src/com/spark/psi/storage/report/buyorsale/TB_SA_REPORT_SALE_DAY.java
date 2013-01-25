package com.spark.psi.storage.report.buyorsale;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_REPORT_SALE_DAY extends TableDeclarator {

	public static final String TABLE_NAME ="SA_REPORT_SALE_DAY";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_ordAmount;
	public final TableFieldDefine f_outstoAmount;
	public final TableFieldDefine f_receiptAmount;
	public final TableFieldDefine f_rtnAmount;
	public final TableFieldDefine f_orderPerson;
	public final TableFieldDefine f_orderDate;
	public final TableFieldDefine f_ordAmountLs;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_ordAmount ="ordAmount";
	public static final String FN_outstoAmount ="outstoAmount";
	public static final String FN_receiptAmount ="receiptAmount";
	public static final String FN_rtnAmount ="rtnAmount";
	public static final String FN_orderPerson ="orderPerson";
	public static final String FN_orderDate ="orderDate";
	public static final String FN_ordAmountLs ="ordAmountLs";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_REPORT_SALE_DAY() {
		super(TABLE_NAME);
		this.table.setTitle("���۷������ݱ������ڣ�");
		this.table.setCategory("��������");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("������������GUID");
		this.f_ordAmount = field = this.table.newField(FN_ordAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�������(����������)");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_outstoAmount = field = this.table.newField(FN_outstoAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("��/�����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_receiptAmount = field = this.table.newField(FN_receiptAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("��/������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_rtnAmount = field = this.table.newField(FN_rtnAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�˻����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_orderPerson = field = this.table.newField(FN_orderPerson, TypeFactory.GUID);
		field.setTitle("����������");
		this.f_orderDate = field = this.table.newField(FN_orderDate, TypeFactory.DATE);
		field.setTitle("������������");
		this.f_ordAmountLs = field = this.table.newField(FN_ordAmountLs, TypeFactory.NUMERIC(17,2));
		field.setTitle("�������(��������)");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.table.newIndex("SA_REPORT_SALE_DAY_ONE",f_tenantsGuid,f_orderDate,f_orderPerson,f_deptGuid);
	}

}
