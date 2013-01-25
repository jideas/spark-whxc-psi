package com.spark.psi.report.storage.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_REPORT_GOODS_BUY_DATE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_REPORT_GOODS_BUY_DATE";

	public final TableFieldDefine f_tenantId;
	public final TableFieldDefine f_goodsItemId;
	public final TableFieldDefine f_dateNo;
	public final TableFieldDefine f_monthNo;
	public final TableFieldDefine f_quarter;
	public final TableFieldDefine f_yearNo;
	public final TableFieldDefine f_ordAmount;
	public final TableFieldDefine f_ordCount;
	public final TableFieldDefine f_outstoAmount;
	public final TableFieldDefine f_receiptAmount;
	public final TableFieldDefine f_rtnAmount;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_orderPerson;
	public final TableFieldDefine f_createDate;

	public static final String FN_tenantId ="tenantId";
	public static final String FN_goodsItemId ="goodsItemId";
	public static final String FN_dateNo ="dateNo";
	public static final String FN_monthNo ="monthNo";
	public static final String FN_quarter ="quarter";
	public static final String FN_yearNo ="yearNo";
	public static final String FN_ordAmount ="ordAmount";
	public static final String FN_ordCount ="ordCount";
	public static final String FN_outstoAmount ="outstoAmount";
	public static final String FN_receiptAmount ="receiptAmount";
	public static final String FN_rtnAmount ="rtnAmount";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_orderPerson ="orderPerson";
	public static final String FN_createDate ="createDate";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_REPORT_GOODS_BUY_DATE() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_tenantId = field = this.table.newField(FN_tenantId, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_goodsItemId = field = this.table.newField(FN_goodsItemId, TypeFactory.GUID);
		field.setTitle("��Ʒ����GUID");
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
		this.f_ordCount = field = this.table.newField(FN_ordCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_outstoAmount = field = this.table.newField(FN_outstoAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("���³�/�����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_receiptAmount = field = this.table.newField(FN_receiptAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ɹ���/������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_rtnAmount = field = this.table.newField(FN_rtnAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�����˻����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("�Ƶ�����������");
		this.f_orderPerson = field = this.table.newField(FN_orderPerson, TypeFactory.GUID);
		field.setTitle("�Ƶ���");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("����ʱ��");
		field.setKeepValid(true);
		this.table.newIndex("SA_REPORT_GOODS_BUY_MONO",f_tenantId,f_goodsItemId,f_dateNo,f_deptGuid,f_orderPerson);
		this.table.newIndex("SA_REPORT_GOODS_BUY_MONT",f_tenantId,f_goodsItemId,f_yearNo,f_deptGuid,f_orderPerson);
	}

}
