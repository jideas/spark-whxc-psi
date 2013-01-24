package com.spark.psi.storage.base.supplier;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_BankAccount extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_BankAccount";

	public final TableFieldDefine f_supplierId;
	public final TableFieldDefine f_bankName;
	public final TableFieldDefine f_accountHolder;
	public final TableFieldDefine f_account;
	public final TableFieldDefine f_remark;

	public static final String FN_supplierId ="supplierId";
	public static final String FN_bankName ="bankName";
	public static final String FN_accountHolder ="accountHolder";
	public static final String FN_account ="account";
	public static final String FN_remark ="remark";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_PSI_Base_BankAccount() {
		super(TABLE_NAME);
		this.table.setTitle("银行账号");
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_supplierId = field = this.table.newField(FN_supplierId, TypeFactory.GUID);
		field.setTitle("供应商id");
		this.f_bankName = field = this.table.newField(FN_bankName, TypeFactory.NVARCHAR(100));
		field.setTitle("开户银行");
		this.f_accountHolder = field = this.table.newField(FN_accountHolder, TypeFactory.NVARCHAR(50));
		field.setTitle("开户名称");
		this.f_account = field = this.table.newField(FN_account, TypeFactory.NVARCHAR(30));
		field.setTitle("银行帐号");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(400));
		field.setTitle("备注");
	}

}
