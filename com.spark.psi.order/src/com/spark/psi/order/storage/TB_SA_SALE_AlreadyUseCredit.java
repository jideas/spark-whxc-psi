package com.spark.psi.order.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_SA_SALE_AlreadyUseCredit extends TableDeclarator {

	public static final String TABLE_NAME ="SA_SALE_AlreadyUseCredit";

	public final TableFieldDefine f_customerId;
	public final TableFieldDefine f_aleardyUseCredit;

	public static final String FN_customerId ="customerId";
	public static final String FN_aleardyUseCredit ="aleardyUseCredit";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_SA_SALE_AlreadyUseCredit() {
		super(TABLE_NAME);
		this.table.setTitle("已用信用额度维护表");
		this.table.setCategory("业务主体");
		TableFieldDeclare field;
		this.f_customerId = field = this.table.newPrimaryField(FN_customerId, TypeFactory.GUID);
		field.setTitle("客户id");
		field.setKeepValid(true);
		this.f_aleardyUseCredit = field = this.table.newField(FN_aleardyUseCredit, TypeFactory.NUMERIC(17,2));
		field.setTitle("已用信用额度");
		field.setKeepValid(true);
		field.setDefault(ConstExpression.builder.expOf(0.0));
	}

}
