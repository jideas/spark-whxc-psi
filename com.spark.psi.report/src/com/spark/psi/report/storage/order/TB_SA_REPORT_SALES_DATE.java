package com.spark.psi.report.storage.order;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_REPORT_SALES_DATE extends TableDeclarator {

	public static final String TABLE_NAME ="SA_REPORT_SALES_DATE";

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
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_orderPerson;
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
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_orderPerson ="orderPerson";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_REPORT_SALES_DATE() {
		super(TABLE_NAME);
		this.table.setTitle("客户分析数据表（按月份）");
		this.table.setCategory("报表主体");
		TableFieldDeclare field;
		this.f_tenantId = field = this.table.newField(FN_tenantId, TypeFactory.GUID);
		field.setTitle("租户编号");
		field.setKeepValid(true);
		this.f_cusProGuid = field = this.table.newField(FN_cusProGuid, TypeFactory.GUID);
		field.setTitle("客户");
		this.f_dateNo = field = this.table.newField(FN_dateNo, TypeFactory.INT);
		field.setTitle("日号");
		this.f_monthNo = field = this.table.newField(FN_monthNo, TypeFactory.INT);
		field.setTitle("期号");
		this.f_quarter = field = this.table.newField(FN_quarter, TypeFactory.INT);
		field.setTitle("季度");
		this.f_yearNo = field = this.table.newField(FN_yearNo, TypeFactory.INT);
		field.setTitle("年份");
		this.f_ordAmount = field = this.table.newField(FN_ordAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("订单金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_outstoAmount = field = this.table.newField(FN_outstoAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("出库金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_receiptAmount = field = this.table.newField(FN_receiptAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("采购收款金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_deductionAmount = field = this.table.newField(FN_deductionAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("抵减金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_rtnAmount = field = this.table.newField(FN_rtnAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("退货金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("制单人所属部门");
		this.f_orderPerson = field = this.table.newField(FN_orderPerson, TypeFactory.GUID);
		field.setTitle("制单人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建时间");
		field.setKeepValid(true);
		this.table.newIndex("SA_REPORT_CUSTOMER_MONONE",f_tenantId,f_deptGuid,f_orderPerson,f_monthNo,f_cusProGuid);
		this.table.newIndex("SA_REPORT_CUSTOMER_MONTWO",f_tenantId,f_deptGuid,f_orderPerson,f_quarter,f_cusProGuid);
	}

}
