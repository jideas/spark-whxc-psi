package com.spark.psi.storage.account.cusdeal;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_SA_FINANCE_CUSDEAL_DEAL extends TableDeclarator {

	public static final String TABLE_NAME ="SA_FINANCE_CUSDEAL_DEAL";

	public final TableFieldDefine f_tenantsGuid;
	public final TableFieldDefine f_cusproGuid;
	public final TableFieldDefine f_billsGuid;
	public final TableFieldDefine f_billsNo;
	public final TableFieldDefine f_billsType;
	public final TableFieldDefine f_receiptType;
	public final TableFieldDefine f_planAmount;
	public final TableFieldDefine f_realAmount;
	public final TableFieldDefine f_balance;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_remark;

	public static final String FN_tenantsGuid ="tenantsGuid";
	public static final String FN_cusproGuid ="cusproGuid";
	public static final String FN_billsGuid ="billsGuid";
	public static final String FN_billsNo ="billsNo";
	public static final String FN_billsType ="billsType";
	public static final String FN_receiptType ="receiptType";
	public static final String FN_planAmount ="planAmount";
	public static final String FN_realAmount ="realAmount";
	public static final String FN_balance ="balance";
	public static final String FN_createDate ="createDate";
	public static final String FN_remark ="remark";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_FINANCE_CUSDEAL_DEAL() {
		super(TABLE_NAME);
		this.table.setDescription("往来明细");
		this.table.setTitle("往来明细");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_tenantsGuid = field = this.table.newField(FN_tenantsGuid, TypeFactory.GUID);
		field.setTitle("租户编号");
		this.f_cusproGuid = field = this.table.newField(FN_cusproGuid, TypeFactory.GUID);
		field.setTitle("客户供应商GUID");
		this.f_billsGuid = field = this.table.newField(FN_billsGuid, TypeFactory.GUID);
		field.setTitle("单据GUID");
		this.f_billsNo = field = this.table.newField(FN_billsNo, TypeFactory.VARCHAR(20));
		field.setTitle("单据编号");
		this.f_billsType = field = this.table.newField(FN_billsType, TypeFactory.VARCHAR(10));
		field.setTitle("单据类型");
		this.f_receiptType = field = this.table.newField(FN_receiptType, TypeFactory.CHAR(10));
		field.setTitle("收款方式");
		this.f_planAmount = field = this.table.newField(FN_planAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("应收/应付金额");
		this.f_realAmount = field = this.table.newField(FN_realAmount, TypeFactory.NUMERIC(17,2));
		field.setTitle("实收/实付金额");
		this.f_balance = field = this.table.newField(FN_balance, TypeFactory.NUMERIC(17,2));
		field.setTitle("应收/应付余额");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("日期");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.VARCHAR(1000));
		field.setTitle("备注");
		this.table.newIndex("index_finance_cusdel",f_tenantsGuid,f_cusproGuid,f_createDate);
	}

}
