package com.spark.psi.base.storage.tenant;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_SALE_CREDIT extends TableDeclarator {

	public static final String TABLE_NAME ="SA_SALE_CREDIT";

	public final TableFieldDefine f_tenantsGUID;
	public final TableFieldDefine f_customerCreditLimit;
	public final TableFieldDefine f_availableTotalCreditLimit;
	public final TableFieldDefine f_customerCountUsed;
	public final TableFieldDefine f_customerCreditUsed;
	public final TableFieldDefine f_customerCreditDayLimit;
	public final TableFieldDefine f_orderApprovalLimit;

	public static final String FN_tenantsGUID ="tenantsGUID";
	public static final String FN_customerCreditLimit ="customerCreditLimit";
	public static final String FN_availableTotalCreditLimit ="availableTotalCreditLimit";
	public static final String FN_customerCountUsed ="customerCountUsed";
	public static final String FN_customerCreditUsed ="customerCreditUsed";
	public static final String FN_customerCreditDayLimit ="customerCreditDayLimit";
	public static final String FN_orderApprovalLimit ="orderApprovalLimit";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_SALE_CREDIT() {
		super(TABLE_NAME);
		this.table.setDescription("销售授权");
		this.table.setTitle(" ");
		TableFieldDeclare field;
		this.f_tenantsGUID = field = this.table.newField(FN_tenantsGUID, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_customerCreditLimit = field = this.table.newField(FN_customerCreditLimit, TypeFactory.NUMERIC(17,2));
		field.setTitle("客户信用额度");
		this.f_availableTotalCreditLimit = field = this.table.newField(FN_availableTotalCreditLimit, TypeFactory.NUMERIC(17,2));
		field.setTitle("累计可设置信用额度");
		this.f_customerCountUsed = field = this.table.newField(FN_customerCountUsed, TypeFactory.INT);
		field.setTitle("已设置信用额度客户数");
		this.f_customerCreditUsed = field = this.table.newField(FN_customerCreditUsed, TypeFactory.NUMERIC(17,2));
		field.setTitle("已分配信用额度");
		this.f_customerCreditDayLimit = field = this.table.newField(FN_customerCreditDayLimit, TypeFactory.INT);
		field.setTitle("客户最长账期");
		this.f_orderApprovalLimit = field = this.table.newField(FN_orderApprovalLimit, TypeFactory.NUMERIC(17,2));
		field.setTitle("订单审批限额");
		this.table.newIndex("INDEX_CREDIT",f_tenantsGUID);
	}

}
