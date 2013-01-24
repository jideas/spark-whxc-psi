package com.spark.erp.storage.sales;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_ERP_Sales_OnlineOrder_Log extends TableDeclarator {

	public static final String TABLE_NAME ="ERP_Sales_OnlineOrder_Log";

	public final TableFieldDefine f_billsId;
	public final TableFieldDefine f_processingTime;
	public final TableFieldDefine f_message;
	public final TableFieldDefine f_operator;

	public static final String FN_billsId ="billsId";
	public static final String FN_processingTime ="processingTime";
	public static final String FN_message ="message";
	public static final String FN_operator ="operator";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_ERP_Sales_OnlineOrder_Log() {
		super(TABLE_NAME);
		this.table.setTitle("网上订单跟踪信息表");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_billsId = field = this.table.newField(FN_billsId, TypeFactory.GUID);
		field.setTitle("订单GUID");
		this.f_processingTime = field = this.table.newField(FN_processingTime, TypeFactory.DATE);
		field.setTitle("处理时间");
		this.f_message = field = this.table.newField(FN_message, TypeFactory.NVARCHAR(200));
		field.setTitle("处理信息");
		this.f_operator = field = this.table.newField(FN_operator, TypeFactory.NVARCHAR(30));
		field.setTitle("操作人");
	}

}
