package com.spark.b2c.storage.joint;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_PSI_Joint_Settlement extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Joint_Settlement";

	public final TableFieldDefine f_supplierName;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_shortName;
	public final TableFieldDefine f_supplierNo;
	public final TableFieldDefine f_supplierId;
	public final TableFieldDefine f_beginDate;
	public final TableFieldDefine f_endDate;
	public final TableFieldDefine f_salesAmount;
	public final TableFieldDefine f_percentageAmount;
	public final TableFieldDefine f_adjustAmount;
	public final TableFieldDefine f_molingAmount;
	public final TableFieldDefine f_amount;
	public final TableFieldDefine f_paidAmount;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_sheetNo;
	public final TableFieldDefine f_recordIds;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_denyReason;

	public static final String FN_supplierName ="supplierName";
	public static final String FN_namePY ="namePY";
	public static final String FN_shortName ="shortName";
	public static final String FN_supplierNo ="supplierNo";
	public static final String FN_supplierId ="supplierId";
	public static final String FN_beginDate ="beginDate";
	public static final String FN_endDate ="endDate";
	public static final String FN_salesAmount ="salesAmount";
	public static final String FN_percentageAmount ="percentageAmount";
	public static final String FN_adjustAmount ="adjustAmount";
	public static final String FN_molingAmount ="molingAmount";
	public static final String FN_amount ="amount";
	public static final String FN_paidAmount ="paidAmount";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_status ="status";
	public static final String FN_sheetNo ="sheetNo";
	public static final String FN_recordIds ="recordIds";
	public static final String FN_remark ="remark";
	public static final String FN_denyReason ="denyReason";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Joint_Settlement() {
		super(TABLE_NAME);
		this.table.setTitle("联营结算");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_supplierName = field = this.table.newField(FN_supplierName, TypeFactory.NVARCHAR(50));
		field.setTitle("全称");
		this.f_namePY = field = this.table.newField(FN_namePY, TypeFactory.NVARCHAR(50));
		field.setTitle("全称拼音");
		this.f_shortName = field = this.table.newField(FN_shortName, TypeFactory.NVARCHAR(12));
		field.setTitle("简称");
		this.f_supplierNo = field = this.table.newField(FN_supplierNo, TypeFactory.NVARCHAR(25));
		field.setTitle("编号");
		this.f_supplierId = this.table.newField(FN_supplierId, TypeFactory.GUID);
		this.f_beginDate = this.table.newField(FN_beginDate, TypeFactory.DATE);
		this.f_endDate = this.table.newField(FN_endDate, TypeFactory.DATE);
		this.f_salesAmount = field = this.table.newField(FN_salesAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("销售金额");
		this.f_percentageAmount = field = this.table.newField(FN_percentageAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("提成金额");
		this.f_adjustAmount = field = this.table.newField(FN_adjustAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("调整金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_molingAmount = field = this.table.newField(FN_molingAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("抹零金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_amount = field = this.table.newField(FN_amount, TypeFactory.NUMERIC(17,2));
		field.setTitle("应付金额");
		this.f_paidAmount = field = this.table.newField(FN_paidAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("已付金额");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_creatorId = this.table.newField(FN_creatorId, TypeFactory.GUID);
		this.f_creator = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		this.f_createDate = this.table.newField(FN_createDate, TypeFactory.DATE);
		this.f_status = this.table.newField(FN_status, TypeFactory.CHAR(2));
		this.f_sheetNo = this.table.newField(FN_sheetNo, TypeFactory.NVARCHAR(20));
		this.f_recordIds = this.table.newField(FN_recordIds, TypeFactory.NTEXT);
		this.f_remark = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		this.f_denyReason = field = this.table.newField(FN_denyReason, TypeFactory.NVARCHAR(1000));
		field.setTitle("退回原因");
	}

}
