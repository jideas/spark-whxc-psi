package com.spark.psi.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Deliver_Det extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Deliver_Det";

	public final TableFieldDefine f_deliverSheetId;
	public final TableFieldDefine f_onlineOrderId;
	public final TableFieldDefine f_onlineOrderNo;
	public final TableFieldDefine f_memberRealName;
	public final TableFieldDefine f_orderAmount;

	public static final String FN_deliverSheetId ="deliverSheetId";
	public static final String FN_onlineOrderId ="onlineOrderId";
	public static final String FN_onlineOrderNo ="onlineOrderNo";
	public static final String FN_memberRealName ="memberRealName";
	public static final String FN_orderAmount ="orderAmount";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Deliver_Det() {
		super(TABLE_NAME);
		this.table.setTitle("配送单明细");
		TableFieldDeclare field;
		this.f_deliverSheetId = field = this.table.newField(FN_deliverSheetId, TypeFactory.GUID);
		field.setTitle("配送单ID");
		this.f_onlineOrderId = field = this.table.newField(FN_onlineOrderId, TypeFactory.GUID);
		field.setTitle("网上订单Id");
		this.f_onlineOrderNo = field = this.table.newField(FN_onlineOrderNo, TypeFactory.NVARCHAR(30));
		field.setTitle("订单编号");
		this.f_memberRealName = field = this.table.newField(FN_memberRealName, TypeFactory.NVARCHAR(30));
		field.setTitle("会员名称");
		this.f_orderAmount = field = this.table.newField(FN_orderAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("订单金额");
	}

}
