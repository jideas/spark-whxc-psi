package com.spark.psi.report.storage.common;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_REPORT_SCREEN extends TableDeclarator {

	public static final String TABLE_NAME ="SA_REPORT_SCREEN";

	public final TableFieldDefine f_tenantId;
	public final TableFieldDefine f_orderType;
	public final TableFieldDefine f_orderAmount;
	public final TableFieldDefine f_orderPerson;
	public final TableFieldDefine f_orderDate;
	public final TableFieldDefine f_deptGuid;
	public final TableFieldDefine f_viewOrder;

	public static final String FN_tenantId ="tenantId";
	public static final String FN_orderType ="orderType";
	public static final String FN_orderAmount ="orderAmount";
	public static final String FN_orderPerson ="orderPerson";
	public static final String FN_orderDate ="orderDate";
	public static final String FN_deptGuid ="deptGuid";
	public static final String FN_viewOrder ="viewOrder";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_REPORT_SCREEN() {
		super(TABLE_NAME);
		this.table.setDescription("滚动屏数据表");
		this.table.setTitle("滚动屏数据表");
		this.table.setCategory("报表主体");
		TableFieldDeclare field;
		this.f_tenantId = field = this.table.newField(FN_tenantId, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_orderType = field = this.table.newField(FN_orderType, TypeFactory.CHAR(2));
		field.setTitle("订单类型");
		this.f_orderAmount = field = this.table.newField(FN_orderAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("订单金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_orderPerson = field = this.table.newField(FN_orderPerson, TypeFactory.GUID);
		field.setTitle("订单创建人");
		this.f_orderDate = field = this.table.newField(FN_orderDate, TypeFactory.DATE);
		field.setTitle("订单日期");
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("订单所属部门GUID");
		this.f_viewOrder = field = this.table.newField(FN_viewOrder, TypeFactory.INT);
		field.setTitle("在一天中的显示顺序");
		this.table.newIndex("SA_REPORT_SCREEN",f_tenantId,f_orderDate,f_deptGuid,f_orderPerson);
	}

}
