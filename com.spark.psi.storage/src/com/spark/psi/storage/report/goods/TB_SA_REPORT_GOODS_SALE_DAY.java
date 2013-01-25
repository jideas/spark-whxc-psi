package com.spark.psi.storage.report.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_REPORT_GOODS_SALE_DAY extends TableDeclarator {

	public static final String TABLE_NAME ="SA_REPORT_GOODS_SALE_DAY";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsPropertyGuid;
	public final TableFieldDefine f_ordAmount;
	public final TableFieldDefine f_ordCount;
	public final TableFieldDefine f_outstoAmount;
	public final TableFieldDefine f_receiptAmount;
	public final TableFieldDefine f_rtnAmount;
	public final TableFieldDefine f_orderDate;
	public final TableFieldDefine f_buySaleType;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_orderPerson;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsPropertyGuid ="goodsPropertyGuid";
	public static final String FN_ordAmount ="ordAmount";
	public static final String FN_ordCount ="ordCount";
	public static final String FN_outstoAmount ="outstoAmount";
	public static final String FN_receiptAmount ="receiptAmount";
	public static final String FN_rtnAmount ="rtnAmount";
	public static final String FN_orderDate ="orderDate";
	public static final String FN_buySaleType ="buySaleType";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_orderPerson ="orderPerson";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_REPORT_GOODS_SALE_DAY() {
		super(TABLE_NAME);
		this.table.setTitle("������Ʒ�������ݱ������ڣ�");
		this.table.setCategory("��������");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_goodsPropertyGuid = field = this.table.newField(FN_goodsPropertyGuid, TypeFactory.GUID);
		field.setTitle("��Ʒ����GUID");
		this.f_ordAmount = field = this.table.newField(FN_ordAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_ordCount = field = this.table.newField(FN_ordCount, TypeFactory.NUMERIC(17,5));
		field.setTitle("����");
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
		this.f_orderDate = field = this.table.newField(FN_orderDate, TypeFactory.DATE);
		field.setTitle("������������");
		this.f_buySaleType = field = this.table.newField(FN_buySaleType, TypeFactory.VARCHAR(2));
		field.setTitle("����/�ɹ�����");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("�Ƶ�����������");
		this.f_orderPerson = field = this.table.newField(FN_orderPerson, TypeFactory.GUID);
		field.setTitle("�Ƶ���");
		this.table.newIndex("SA_REPORT_GOODS_SALE_DAY",f_tenantsGuid,f_goodsPropertyGuid,f_orderDate,f_deptGuid,f_orderPerson);
	}

}
