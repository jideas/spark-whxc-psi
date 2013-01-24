package com.spark.psi.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Produce_Order extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Produce_Order";

	public final TableFieldDefine f_billsNo;
	public final TableFieldDefine f_planDate;
	public final TableFieldDefine f_goodsCount;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_finishDate;
	public final TableFieldDefine f_status;
	public final TableFieldDefine f_rejectReason;
	public final TableFieldDefine f_approvePerson;
	public final TableFieldDefine f_approveDate;

	public static final String FN_billsNo ="billsNo";
	public static final String FN_planDate ="planDate";
	public static final String FN_goodsCount ="goodsCount";
	public static final String FN_remark ="remark";
	public static final String FN_creator ="creator";
	public static final String FN_createDate ="createDate";
	public static final String FN_finishDate ="finishDate";
	public static final String FN_status ="status";
	public static final String FN_rejectReason ="rejectReason";
	public static final String FN_approvePerson ="approvePerson";
	public static final String FN_approveDate ="approveDate";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Produce_Order() {
		super(TABLE_NAME);
		this.table.setTitle("生产订单");
		TableFieldDeclare field;
		this.f_billsNo = field = this.table.newField(FN_billsNo, TypeFactory.NVARCHAR(20));
		field.setTitle("订单编号");
		this.f_planDate = field = this.table.newField(FN_planDate, TypeFactory.DATE);
		field.setTitle("计划完成日期");
		this.f_goodsCount = field = this.table.newField(FN_goodsCount, TypeFactory.INT);
		field.setTitle("商品数量");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("备注");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("制单人");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("制单日期");
		this.f_finishDate = field = this.table.newField(FN_finishDate, TypeFactory.DATE);
		field.setTitle("完成日期");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("状态");
		this.f_rejectReason = field = this.table.newField(FN_rejectReason, TypeFactory.NVARCHAR(1000));
		field.setTitle("退回原因");
		this.f_approvePerson = field = this.table.newField(FN_approvePerson, TypeFactory.NVARCHAR(30));
		field.setTitle("审核人");
		this.f_approveDate = field = this.table.newField(FN_approveDate, TypeFactory.DATE);
		field.setTitle("审核时间");
	}

}
