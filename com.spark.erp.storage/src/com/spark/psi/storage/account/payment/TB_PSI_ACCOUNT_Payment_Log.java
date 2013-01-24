package com.spark.psi.storage.account.payment;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_ACCOUNT_Payment_Log extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_ACCOUNT_Payment_Log";

	public final TableFieldDefine f_paymentId;
	public final TableFieldDefine f_paymentNo;
	public final TableFieldDefine f_checkinSheetId;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerType;
	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_relevantBillId;
	public final TableFieldDefine f_relevantBillNo;
	public final TableFieldDefine f_checkinDate;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_molingAmount;
	public final TableFieldDefine f_payPersonId;
	public final TableFieldDefine f_payPersonName;
	public final TableFieldDefine f_payDate;

	public static final String FN_paymentId ="paymentId";
	public static final String FN_paymentNo ="paymentNo";
	public static final String FN_checkinSheetId ="checkinSheetId";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerType ="partnerType";
	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_relevantBillId ="relevantBillId";
	public static final String FN_relevantBillNo ="relevantBillNo";
	public static final String FN_checkinDate ="checkinDate";
	public static final String FN_amount ="amount";
	public static final String FN_molingAmount ="molingAmount";
	public static final String FN_payPersonId ="payPersonId";
	public static final String FN_payPersonName ="payPersonName";
	public static final String FN_payDate ="payDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_ACCOUNT_Payment_Log() {
		super(TABLE_NAME);
		this.table.setTitle("付款记录");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_paymentId = field = this.table.newField(FN_paymentId, TypeFactory.GUID);
		field.setTitle("申请单id");
		this.f_paymentNo = field = this.table.newField(FN_paymentNo, TypeFactory.NVARCHAR(30));
		field.setTitle("申请单编号");
		this.f_checkinSheetId = field = this.table.newField(FN_checkinSheetId, TypeFactory.GUID);
		field.setTitle("入库单id");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("付款对象");
		this.f_partnerType = field = this.table.newField(FN_partnerType, TypeFactory.CHAR(2));
		field.setTitle("对象类型");
		this.f_sheetNo = field = this.table.newField(FN_sheetNo, TypeFactory.NVARCHAR(30));
		field.setTitle("入库单号");
		this.f_relevantBillId = field = this.table.newField(FN_relevantBillId, TypeFactory.GUID);
		field.setTitle("相关单据Id");
		this.f_relevantBillNo = field = this.table.newField(FN_relevantBillNo, TypeFactory.NVARCHAR(30));
		field.setTitle("相关单据");
		this.f_checkinDate = field = this.table.newField(FN_checkinDate, TypeFactory.DATE);
		field.setTitle("入库日期");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("付款金额");
		this.f_molingAmount = field = this.table.newField(FN_molingAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("抹零金额");
		this.f_payPersonId = field = this.table.newField(FN_payPersonId, TypeFactory.GUID);
		field.setTitle("付款人");
		this.f_payPersonName = field = this.table.newField(FN_payPersonName, TypeFactory.NVARCHAR(30));
		field.setTitle("付款人姓名");
		this.f_payDate = field = this.table.newField(FN_payDate, TypeFactory.DATE);
		field.setTitle("付款日期");
	}

}
