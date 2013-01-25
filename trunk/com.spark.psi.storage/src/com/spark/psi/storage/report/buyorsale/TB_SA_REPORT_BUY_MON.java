package com.spark.psi.storage.report.buyorsale;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_REPORT_BUY_MON extends TableDeclarator {

	public static final String TABLE_NAME ="SA_REPORT_BUY_MON";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_orderPerson;
	public final TableFieldDefine f_monthNo;
	public final TableFieldDefine f_quarter;
	public final TableFieldDefine f_ordAmount;
	public final TableFieldDefine f_outstoAmount;
	public final TableFieldDefine f_receiptAmount;
	public final TableFieldDefine f_rtnAmount;
	public final TableFieldDefine f_ordAmountOrder;
	public final TableFieldDefine f_outstoAmountOrder;
	public final TableFieldDefine f_receiptAmountOrder;
	public final TableFieldDefine f_rtnAmountOrder;
	public final TableFieldDefine f_ordAmountFb;
	public final TableFieldDefine f_outstoAmountFb;
	public final TableFieldDefine f_receiptAmountFb;
	public final TableFieldDefine f_rtnAmountFb;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_orderPerson ="orderPerson";
	public static final String FN_monthNo ="monthNo";
	public static final String FN_quarter ="quarter";
	public static final String FN_ordAmount ="ordAmount";
	public static final String FN_outstoAmount ="outstoAmount";
	public static final String FN_receiptAmount ="receiptAmount";
	public static final String FN_rtnAmount ="rtnAmount";
	public static final String FN_ordAmountOrder ="ordAmountOrder";
	public static final String FN_outstoAmountOrder ="outstoAmountOrder";
	public static final String FN_receiptAmountOrder ="receiptAmountOrder";
	public static final String FN_rtnAmountOrder ="rtnAmountOrder";
	public static final String FN_ordAmountFb ="ordAmountFb";
	public static final String FN_outstoAmountFb ="outstoAmountFb";
	public static final String FN_receiptAmountFb ="receiptAmountFb";
	public static final String FN_rtnAmountFb ="rtnAmountFb";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_REPORT_BUY_MON() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("部门GUID");
		this.f_orderPerson = field = this.table.newField(FN_orderPerson, TypeFactory.GUID);
		field.setTitle("订单创建人");
		this.f_monthNo = field = this.table.newField(FN_monthNo, TypeFactory.INT);
		field.setTitle("期号");
		this.f_quarter = field = this.table.newField(FN_quarter, TypeFactory.INT);
		field.setTitle("季度");
		this.f_ordAmount = field = this.table.newField(FN_ordAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("本月订单金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_outstoAmount = field = this.table.newField(FN_outstoAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("本月出/入库金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_receiptAmount = field = this.table.newField(FN_receiptAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("采购收/付款金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_rtnAmount = field = this.table.newField(FN_rtnAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("本月退货金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_ordAmountOrder = field = this.table.newField(FN_ordAmountOrder, TypeFactory.INT);
		field.setTitle("本月订单金额排名");
		this.f_outstoAmountOrder = field = this.table.newField(FN_outstoAmountOrder, TypeFactory.INT);
		field.setTitle("本月出/入库金额排名");
		this.f_receiptAmountOrder = field = this.table.newField(FN_receiptAmountOrder, TypeFactory.INT);
		field.setTitle("本月收/付款金额排名");
		this.f_rtnAmountOrder = field = this.table.newField(FN_rtnAmountOrder, TypeFactory.INT);
		field.setTitle("本月退货金额排名");
		this.f_ordAmountFb = field = this.table.newField(FN_ordAmountFb, TypeFactory.NUMERIC(5,2));
		field.setTitle("本月订单金额环比");
		this.f_outstoAmountFb = field = this.table.newField(FN_outstoAmountFb, TypeFactory.NUMERIC(5,2));
		field.setTitle("本月出/入库金额环比");
		this.f_receiptAmountFb = field = this.table.newField(FN_receiptAmountFb, TypeFactory.NUMERIC(5,2));
		field.setTitle("本月收/付款金额环比");
		this.f_rtnAmountFb = field = this.table.newField(FN_rtnAmountFb, TypeFactory.NUMERIC(5,2));
		field.setTitle("本月退货金额环比");
		this.table.newIndex("SA_REPORT_BUY_MON_ONE",f_tenantsGuid,f_deptGuid,f_orderPerson,f_monthNo);
		this.table.newIndex("SA_REPORT_BUY_MON_TWO",f_tenantsGuid,f_deptGuid,f_orderPerson,f_quarter);
	}

}
