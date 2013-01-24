package com.spark.psi.storage.account.receipts;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_PSI_ACCOUNT_Receipts_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_ACCOUNT_Receipts_Det";

	public final TableFieldDefine f_receiptsId;
	public final TableFieldDefine f_checkoutSheetId;
	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_relevantBillId;
	public final TableFieldDefine f_relevantBillNo;
	public final TableFieldDefine f_checkoutDate;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_receiptedAmount;
	public final TableFieldDefine f_molingAmount;

	public static final String FN_receiptsId ="receiptsId";
	public static final String FN_checkoutSheetId ="checkoutSheetId";
	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_relevantBillId ="relevantBillId";
	public static final String FN_relevantBillNo ="relevantBillNo";
	public static final String FN_checkoutDate ="checkoutDate";
	public static final String FN_amount ="amount";
	public static final String FN_receiptedAmount ="receiptedAmount";
	public static final String FN_molingAmount ="molingAmount";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_ACCOUNT_Receipts_Det() {
		super(TABLE_NAME);
		this.table.setTitle("收款明细");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_receiptsId = field = this.table.newField(FN_receiptsId, TypeFactory.GUID);
		field.setTitle("申请单id");
		this.f_checkoutSheetId = field = this.table.newField(FN_checkoutSheetId, TypeFactory.GUID);
		field.setTitle("出库单id");
		this.f_sheetNo = field = this.table.newField(FN_sheetNo, TypeFactory.NVARCHAR(30));
		field.setTitle("出库单号");
		this.f_relevantBillId = field = this.table.newField(FN_relevantBillId, TypeFactory.GUID);
		field.setTitle("相关单据Id");
		this.f_relevantBillNo = field = this.table.newField(FN_relevantBillNo, TypeFactory.NVARCHAR(30));
		field.setTitle("相关单据");
		this.f_checkoutDate = field = this.table.newField(FN_checkoutDate, TypeFactory.DATE);
		field.setTitle("出库日期");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("出库金额");
		this.f_receiptedAmount = field = this.table.newField(FN_receiptedAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("已收金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_molingAmount = field = this.table.newField(FN_molingAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("抹零金额");
	}

}
