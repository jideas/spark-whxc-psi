package com.spark.psi.storage.account.payment;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_PSI_ACCOUNT_Payment_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_ACCOUNT_Payment_Det";

	public final TableFieldDefine f_paymentId;
	public final TableFieldDefine f_checkinSheetId;
	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_relevantBillId;
	public final TableFieldDefine f_relevantBillNo;
	public final TableFieldDefine f_checkinDate;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_askAmount;
	public final TableFieldDefine f_paidAmount;
	public final TableFieldDefine f_payingAmount;
	public final TableFieldDefine f_molingAmount;

	public static final String FN_paymentId ="paymentId";
	public static final String FN_checkinSheetId ="checkinSheetId";
	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_relevantBillId ="relevantBillId";
	public static final String FN_relevantBillNo ="relevantBillNo";
	public static final String FN_checkinDate ="checkinDate";
	public static final String FN_amount ="amount";
	public static final String FN_askAmount ="askAmount";
	public static final String FN_paidAmount ="paidAmount";
	public static final String FN_payingAmount ="payingAmount";
	public static final String FN_molingAmount ="molingAmount";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_ACCOUNT_Payment_Det() {
		super(TABLE_NAME);
		this.table.setTitle("付款明细");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_paymentId = field = this.table.newField(FN_paymentId, TypeFactory.GUID);
		field.setTitle("申请单id");
		this.f_checkinSheetId = field = this.table.newField(FN_checkinSheetId, TypeFactory.GUID);
		field.setTitle("入库单id");
		this.f_sheetNo = field = this.table.newField(FN_sheetNo, TypeFactory.NVARCHAR(30));
		field.setTitle("入库单号");
		this.f_relevantBillId = field = this.table.newField(FN_relevantBillId, TypeFactory.GUID);
		field.setTitle("相关单据Id");
		this.f_relevantBillNo = field = this.table.newField(FN_relevantBillNo, TypeFactory.NVARCHAR(30));
		field.setTitle("相关单据");
		this.f_checkinDate = field = this.table.newField(FN_checkinDate, TypeFactory.DATE);
		field.setTitle("入库日期");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("入库金额");
		this.f_askAmount = field = this.table.newField(FN_askAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("申请金额");
		this.f_paidAmount = field = this.table.newField(FN_paidAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("已付金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_payingAmount = field = this.table.newField(FN_payingAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("未付金额");
		this.f_molingAmount = field = this.table.newField(FN_molingAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("抹零金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
	}

}
