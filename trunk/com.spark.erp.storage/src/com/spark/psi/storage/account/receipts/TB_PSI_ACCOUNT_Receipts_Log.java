package com.spark.psi.storage.account.receipts;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_ACCOUNT_Receipts_Log extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_ACCOUNT_Receipts_Log";

	public final TableFieldDefine f_id;
	public final TableFieldDefine f_receiptsId;
	public final TableFieldDefine f_receiptNo;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerType;
	public final TableFieldDefine f_checkoutSheetId;
	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_relevantBillId;
	public final TableFieldDefine f_relevantBillNo;
	public final TableFieldDefine f_checkinDate;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_molingAmount;
	public final TableFieldDefine f_receiptPersonId;
	public final TableFieldDefine f_receiptPersonName;
	public final TableFieldDefine f_receiptDate;

	public static final String FN_id ="id";
	public static final String FN_receiptsId ="receiptsId";
	public static final String FN_receiptNo ="receiptNo";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerType ="partnerType";
	public static final String FN_checkoutSheetId ="checkoutSheetId";
	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_relevantBillId ="relevantBillId";
	public static final String FN_relevantBillNo ="relevantBillNo";
	public static final String FN_checkinDate ="checkinDate";
	public static final String FN_amount ="amount";
	public static final String FN_molingAmount ="molingAmount";
	public static final String FN_receiptPersonId ="receiptPersonId";
	public static final String FN_receiptPersonName ="receiptPersonName";
	public static final String FN_receiptDate ="receiptDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_ACCOUNT_Receipts_Log() {
		super(TABLE_NAME);
		this.table.setTitle("收款记录");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_id = field = this.table.newField(FN_id, TypeFactory.GUID);
		field.setTitle("标识");
		this.f_receiptsId = field = this.table.newField(FN_receiptsId, TypeFactory.GUID);
		field.setTitle("通知单id");
		this.f_receiptNo = field = this.table.newField(FN_receiptNo, TypeFactory.NVARCHAR(30));
		field.setTitle("通知单编号");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("付款对象");
		this.f_partnerType = field = this.table.newField(FN_partnerType, TypeFactory.CHAR(2));
		field.setTitle("对象类型");
		this.f_checkoutSheetId = field = this.table.newField(FN_checkoutSheetId, TypeFactory.GUID);
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
		field.setTitle("收款金额");
		this.f_molingAmount = field = this.table.newField(FN_molingAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("抹零金额");
		this.f_receiptPersonId = field = this.table.newField(FN_receiptPersonId, TypeFactory.GUID);
		field.setTitle("收款人");
		this.f_receiptPersonName = field = this.table.newField(FN_receiptPersonName, TypeFactory.NVARCHAR(30));
		field.setTitle("姓名");
		this.f_receiptDate = field = this.table.newField(FN_receiptDate, TypeFactory.DATE);
		field.setTitle("收款时间");
	}

}
