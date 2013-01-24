package com.spark.psi.storage.account.receipts;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_PSI_ACCOUNT_Receipts extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_ACCOUNT_Receipts";

	public final TableFieldDefine f_receiptsNo;
	public final TableFieldDefine f_partnerId;
	public final TableFieldDefine f_partnerType;
	public final TableFieldDefine f_partnerName;
	public final TableFieldDefine f_pNamePy;
	public final TableFieldDefine f_partnerShortName;
	public final TableFieldDefine f_receiptMode;
	public final TableFieldDefine f_receiptType;
	public final TableFieldDefine f_reason;
	public final TableFieldDefine f_receiptDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_receiptedAmount;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;

	public static final String FN_receiptsNo ="receiptsNo";
	public static final String FN_partnerId ="partnerId";
	public static final String FN_partnerType ="partnerType";
	public static final String FN_partnerName ="partnerName";
	public static final String FN_pNamePy ="pNamePy";
	public static final String FN_partnerShortName ="partnerShortName";
	public static final String FN_receiptMode ="receiptMode";
	public static final String FN_receiptType ="receiptType";
	public static final String FN_reason ="reason";
	public static final String FN_receiptDate ="receiptDate";
	public static final String FN_status ="status";
	public static final String FN_amount ="amount";
	public static final String FN_receiptedAmount ="receiptedAmount";
	public static final String FN_remark ="remark";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_ACCOUNT_Receipts() {
		super(TABLE_NAME);
		this.table.setTitle("收款通知单");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_receiptsNo = field = this.table.newField(FN_receiptsNo, TypeFactory.NVARCHAR(30));
		field.setTitle("编号");
		this.f_partnerId = field = this.table.newField(FN_partnerId, TypeFactory.GUID);
		field.setTitle("收款对象");
		this.f_partnerType = field = this.table.newField(FN_partnerType, TypeFactory.CHAR(2));
		field.setTitle("对象类型");
		this.f_partnerName = field = this.table.newField(FN_partnerName, TypeFactory.VARCHAR(50));
		field.setTitle("对象名称");
		this.f_pNamePy = field = this.table.newField(FN_pNamePy, TypeFactory.VARCHAR(10));
		field.setTitle("对象名拼音");
		this.f_partnerShortName = field = this.table.newField(FN_partnerShortName, TypeFactory.VARCHAR(10));
		field.setTitle("对象简称");
		this.f_receiptMode = field = this.table.newField(FN_receiptMode, TypeFactory.CHAR(2));
		field.setTitle("收款方式");
		this.f_receiptType = field = this.table.newField(FN_receiptType, TypeFactory.CHAR(2));
		field.setTitle("收款类型");
		this.f_reason = field = this.table.newField(FN_reason, TypeFactory.CHAR(2));
		field.setTitle("收款原因");
		this.f_receiptDate = field = this.table.newField(FN_receiptDate, TypeFactory.DATE);
		field.setTitle("收款日期");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("状态");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("总金额");
		this.f_receiptedAmount = field = this.table.newField(FN_receiptedAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("已收金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("备注");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("创建人");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("创建人姓名");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("创建日期");
	}

}
