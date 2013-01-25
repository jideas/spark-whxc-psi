package com.spark.psi.report.storage.order;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_REPORT_PURCHASE_DATE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_REPORT_PURCHASE_DATE";

	public final TableFieldDefine f_tenantId;
	public final TableFieldDefine f_cusProGuid;
	public final TableFieldDefine f_dateNo;
	public final TableFieldDefine f_monthNo;
	public final TableFieldDefine f_quarter;
	public final TableFieldDefine f_yearNo;
	public final TableFieldDefine f_ordAmount;
	public final TableFieldDefine f_outstoAmount;
	public final TableFieldDefine f_receiptAmount;
	public final TableFieldDefine f_deductionAmount;
	public final TableFieldDefine f_rtnAmount;
	public final TableFieldDefine f_createDate;

	public static final String FN_tenantId ="tenantId";
	public static final String FN_cusProGuid ="cusProGuid";
	public static final String FN_dateNo ="dateNo";
	public static final String FN_monthNo ="monthNo";
	public static final String FN_quarter ="quarter";
	public static final String FN_yearNo ="yearNo";
	public static final String FN_ordAmount ="ordAmount";
	public static final String FN_outstoAmount ="outstoAmount";
	public static final String FN_receiptAmount ="receiptAmount";
	public static final String FN_deductionAmount ="deductionAmount";
	public static final String FN_rtnAmount ="rtnAmount";
	public static final String FN_createDate ="createDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_REPORT_PURCHASE_DATE() {
		super(TABLE_NAME);
		this.table.setDescription("�ͻ�/��Ӧ�̷������ݱ����·ݣ�");
		this.table.setTitle("�ͻ�/��Ӧ�̷������ݱ����·ݣ�");
		this.table.setCategory("��������");
		TableFieldDeclare field;
		this.f_tenantId = field = this.table.newField(FN_tenantId, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_cusProGuid = field = this.table.newField(FN_cusProGuid, TypeFactory.GUID);
		field.setTitle("��Ӧ��GUID");
		this.f_dateNo = field = this.table.newField(FN_dateNo, TypeFactory.INT);
		field.setTitle("�պ�");
		this.f_monthNo = field = this.table.newField(FN_monthNo, TypeFactory.INT);
		field.setTitle("�ں�");
		this.f_quarter = field = this.table.newField(FN_quarter, TypeFactory.INT);
		field.setTitle("����");
		this.f_yearNo = field = this.table.newField(FN_yearNo, TypeFactory.INT);
		field.setTitle("���");
		this.f_ordAmount = field = this.table.newField(FN_ordAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("���¶������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_outstoAmount = field = this.table.newField(FN_outstoAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("���³�����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_receiptAmount = field = this.table.newField(FN_receiptAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ɹ��տ���");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_deductionAmount = field = this.table.newField(FN_deductionAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ּ����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_rtnAmount = field = this.table.newField(FN_rtnAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�����˻����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		field.setKeepValid(true);
	}

}
