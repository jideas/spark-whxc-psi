package com.spark.psi.report.storage.partner;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_REPORT_CUSTOMER_COUNT extends TableDeclarator {

	public static final String TABLE_NAME ="SA_REPORT_CUSTOMER_COUNT";

	public final TableFieldDefine f_tenantId;
	public final TableFieldDefine f_customerId;
	public final TableFieldDefine f_deptId;
	public final TableFieldDefine f_employeeId;
	public final TableFieldDefine f_dateNo;
	public final TableFieldDefine f_monthNo;
	public final TableFieldDefine f_quarter;
	public final TableFieldDefine f_yearNo;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_deptId2;
	public final TableFieldDefine f_employeeId2;
	public final TableFieldDefine f_officalDate;
	public final TableFieldDefine f_dateNo2;
	public final TableFieldDefine f_monthNo2;
	public final TableFieldDefine f_quarter2;
	public final TableFieldDefine f_yearNo2;

	public static final String FN_tenantId ="tenantId";
	public static final String FN_customerId ="customerId";
	public static final String FN_deptId ="deptId";
	public static final String FN_employeeId ="employeeId";
	public static final String FN_dateNo ="dateNo";
	public static final String FN_monthNo ="monthNo";
	public static final String FN_quarter ="quarter";
	public static final String FN_yearNo ="yearNo";
	public static final String FN_createDate ="createDate";
	public static final String FN_deptId2 ="deptId2";
	public static final String FN_employeeId2 ="employeeId2";
	public static final String FN_officalDate ="officalDate";
	public static final String FN_dateNo2 ="dateNo2";
	public static final String FN_monthNo2 ="monthNo2";
	public static final String FN_quarter2 ="quarter2";
	public static final String FN_yearNo2 ="yearNo2";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_REPORT_CUSTOMER_COUNT() {
		super(TABLE_NAME);
		this.table.setDescription("客户分析");
		this.table.setTitle("客户分析");
		this.table.setCategory("报表主体");
		TableFieldDeclare field;
		this.f_tenantId = field = this.table.newField(FN_tenantId, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_customerId = field = this.table.newField(FN_customerId, TypeFactory.GUID);
		field.setTitle("客户id");
		this.f_deptId = field = this.table.newField(FN_deptId, TypeFactory.GUID);
		field.setTitle("部门GUID");
		this.f_employeeId = field = this.table.newField(FN_employeeId, TypeFactory.GUID);
		field.setTitle("创建人编号");
		this.f_dateNo = field = this.table.newField(FN_dateNo, TypeFactory.INT);
		field.setTitle("日号");
		this.f_monthNo = field = this.table.newField(FN_monthNo, TypeFactory.INT);
		field.setTitle("期号");
		this.f_quarter = field = this.table.newField(FN_quarter, TypeFactory.INT);
		field.setTitle("季度");
		this.f_yearNo = field = this.table.newField(FN_yearNo, TypeFactory.INT);
		field.setTitle("年份");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建时间");
		this.f_deptId2 = field = this.table.newField(FN_deptId2, TypeFactory.GUID);
		field.setTitle("转正后部门GUID");
		this.f_employeeId2 = field = this.table.newField(FN_employeeId2, TypeFactory.GUID);
		field.setTitle("转正后负责人编号");
		this.f_officalDate = field = this.table.newField(FN_officalDate, TypeFactory.DATE);
		field.setTitle("转正日期");
		this.f_dateNo2 = field = this.table.newField(FN_dateNo2, TypeFactory.INT);
		field.setTitle("转正日号");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_monthNo2 = field = this.table.newField(FN_monthNo2, TypeFactory.INT);
		field.setTitle("转正期号");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_quarter2 = field = this.table.newField(FN_quarter2, TypeFactory.INT);
		field.setTitle("转正季度");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_yearNo2 = field = this.table.newField(FN_yearNo2, TypeFactory.INT);
		field.setTitle("转正年份");
		field.setDefault(ConstExpression.builder.expOf(0));
	}

}
