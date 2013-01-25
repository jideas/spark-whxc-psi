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

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_FINANCE_RECEIPT_RETAIL() {
		super(TABLE_NAME);
		this.table.setTitle("零售收款记录表");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_saleEmpGuid = field = this.table.newField(FN_saleEmpGuid, TypeFactory.GUID);
		field.setTitle("销售员工编号");
		this.f_saleEmpName = field = this.table.newField(FN_saleEmpName, TypeFactory.NVARCHAR(20));
		field.setTitle("销售员工名称");
		this.f_receiptDate = field = this.table.newField(FN_receiptDate, TypeFactory.DATE);
		field.setTitle("交款期间");
		this.f_shouldMoney = field = this.table.newField(FN_shouldMoney, TypeFactory.NUMERIC(17,2));
		field.setTitle("应交现金");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_shouldCardCount = field = this.table.newField(FN_shouldCardCount, TypeFactory.INT);
		field.setTitle("应交刷卡底单（数量）");
		field.setDefault(ConstExpression.builder.expOf(0));
		this.f_shouldCardMoney = field = this.table.newField(FN_shouldCardMoney, TypeFactory.NUMERIC(17,2));
		field.setTitle("应交刷卡底单（金额）");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_deptGuid = field = this.table.newField(FN_deptGuid, TypeFactory.GUID);
		field.setTitle("部门编号");
		this.f_retailGuid = field = this.table.newField(FN_retailGuid, TypeFactory.GUID);
		field.setTitle("零售单据编号");
	}

}
