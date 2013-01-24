package com.spark.cms.storage.base;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_CMS_PAYINGBILLS extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_PAYINGBILLS";

	public final TableFieldDefine f_payType;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_createTime;
	public final TableFieldDefine f_orderId;
	public final TableFieldDefine f_transDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_relaBillsId;

	public static final String FN_payType ="payType";
	public static final String FN_amount ="amount";
	public static final String FN_createTime ="createTime";
	public static final String FN_orderId ="orderId";
	public static final String FN_transDate ="transDate";
	public static final String FN_status ="status";
	public static final String FN_relaBillsId ="relaBillsId";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_CMS_PAYINGBILLS() {
		super(TABLE_NAME);
		this.table.setTitle("付款中间表");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_payType = field = this.table.newField(FN_payType, TypeFactory.CHAR(2));
		field.setTitle("支付类型");
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NVARCHAR(12));
		field.setTitle("金额字符串");
		this.f_createTime = field = this.table.newField(FN_createTime, TypeFactory.LONG);
		field.setTitle("创建日期");
		this.f_orderId = field = this.table.newField(FN_orderId, TypeFactory.NVARCHAR(30));
		field.setTitle("支付单号");
		this.f_transDate = field = this.table.newField(FN_transDate, TypeFactory.NVARCHAR(8));
		field.setTitle("支付日期");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("处理状态");
		this.f_relaBillsId = this.table.newField(FN_relaBillsId, TypeFactory.NVARCHAR(500));
	}

}
