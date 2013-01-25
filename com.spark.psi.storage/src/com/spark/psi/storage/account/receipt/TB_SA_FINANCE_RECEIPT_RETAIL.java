package com.spark.psi.storage.account.receipt;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_FINANCE_RECEIPT_RETAIL extends TableDeclarator {

	public static final String TABLE_NAME ="SA_FINANCE_RECEIPT_RETAIL";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_saleEmpGuid;
	public final TableFieldDefine f_saleEmpName;
	public final TableFieldDefine f_receiptDate;
	public final TableFieldDefine f_shouldMoney;
	public final TableFieldDefine f_shouldCardCount;
	public final TableFieldDefine f_shouldCardMoney;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_retailGuid;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_saleEmpGuid ="saleEmpGuid";
	public static final String FN_saleEmpName ="saleEmpName";
	public static final String FN_receiptDate ="receiptDate";
	public static final String FN_shouldMoney ="shouldMoney";
	public static final String FN_shouldCardCount ="shouldCardCount";
	public static final String FN_shouldCardMoney ="shouldCardMoney";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_retailGuid ="retailGuid";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_FINANCE_RECEIPT_RETAIL() {
		super(TABLE_NAME);
		this.table.setTitle("�����տ��¼��");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_saleEmpGuid = field = this.table.newField(FN_saleEmpGuid, TypeFactory.GUID);
		field.setTitle("����Ա�����");
		this.f_saleEmpName = field = this.table.newField(FN_saleEmpName, TypeFactory.NVARCHAR(20));
		field.setTitle("����Ա������");
		this.f_receiptDate = field = this.table.newField(FN_receiptDate, TypeFactory.DATE);
		field.setTitle("�����ڼ�");
		this.f_shouldMoney = field = this.table.newField(FN_shouldMoney, TypeFactory.NUMERIC(17,2));
		field.setTitle("Ӧ���ֽ�");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_shouldCardCount = field = this.table.newField(FN_shouldCardCount, TypeFactory.INT);
		field.setTitle("Ӧ��ˢ���׵���������");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_shouldCardMoney = field = this.table.newField(FN_shouldCardMoney, TypeFactory.NUMERIC(17,2));
		field.setTitle("Ӧ��ˢ���׵�����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("���ű��");
		this.f_retailGuid = field = this.table.newField(FN_retailGuid, TypeFactory.GUID);
		field.setTitle("���۵��ݱ��");
	}

}
