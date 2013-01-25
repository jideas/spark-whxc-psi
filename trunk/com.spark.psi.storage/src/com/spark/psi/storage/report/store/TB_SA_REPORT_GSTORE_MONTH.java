package com.spark.psi.storage.report.store;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_REPORT_GSTORE_MONTH extends TableDeclarator {

	public static final String TABLE_NAME ="SA_REPORT_GSTORE_MONTH";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_goodsPropertyGuid;
	public final TableFieldDefine f_monthNo;
	public final TableFieldDefine f_quarter;
	public final TableFieldDefine f_storeAmount;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_toDeliverCount;
	public final TableFieldDefine f_onWayCount;
	public final TableFieldDefine f_storeAmountOrder;
	public final TableFieldDefine f_goodsCountOrder;
	public final TableFieldDefine f_toDeliverCountOrder;
	public final TableFieldDefine f_onWayCountOrder;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_goodsPropertyGuid ="goodsPropertyGuid";
	public static final String FN_monthNo ="monthNo";
	public static final String FN_quarter ="quarter";
	public static final String FN_storeAmount ="storeAmount";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_toDeliverCount ="toDeliverCount";
	public static final String FN_onWayCount ="onWayCount";
	public static final String FN_storeAmountOrder ="storeAmountOrder";
	public static final String FN_goodsCountOrder ="goodsCountOrder";
	public static final String FN_toDeliverCountOrder ="toDeliverCountOrder";
	public static final String FN_onWayCountOrder ="onWayCountOrder";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_REPORT_GSTORE_MONTH() {
		super(TABLE_NAME);
		this.table.setDescription("��Ʒ���������ݱ����·ݣ�");
		this.table.setTitle("��Ʒ���������ݱ����·ݣ�");
		this.table.setCategory("��������");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("�⻧���");
		this.f_goodsPropertyGuid = field = this.table.newField(FN_goodsPropertyGuid, TypeFactory.GUID);
		field.setTitle("��Ʒ����GUID");
		this.f_monthNo = field = this.table.newField(FN_monthNo, TypeFactory.INT);
		field.setTitle("�ں�");
		this.f_quarter = field = this.table.newField(FN_quarter, TypeFactory.INT);
		field.setTitle("����");
		this.f_storeAmount = field = this.table.newField(FN_storeAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_goodsCount = field = this.table.newField(FN_goodsCount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_toDeliverCount = field = this.table.newField(FN_toDeliverCount, TypeFactory.NUMERIC(17,2));
		field.setTitle("��������");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_onWayCount = field = this.table.newField(FN_onWayCount, TypeFactory.NUMERIC(17,2));
		field.setTitle("�ɹ���;");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_storeAmountOrder = field = this.table.newField(FN_storeAmountOrder, TypeFactory.INT);
		field.setTitle("���¿��������");
		this.f_goodsCountOrder = field = this.table.newField(FN_goodsCountOrder, TypeFactory.INT);
		field.setTitle("���¿����������");
		this.f_toDeliverCountOrder = field = this.table.newField(FN_toDeliverCountOrder, TypeFactory.INT);
		field.setTitle("���½�����������");
		this.f_onWayCountOrder = field = this.table.newField(FN_onWayCountOrder, TypeFactory.INT);
		field.setTitle("���²ɹ���;����");
		this.table.newIndex("SA_REPORT_GSTORE_MONTHONE",f_tenantsGuid,f_goodsPropertyGuid,f_monthNo);
		this.table.newIndex("SA_REPORT_GSTORE_MONTHTWO",f_tenantsGuid,f_goodsPropertyGuid,f_quarter);
	}

}
