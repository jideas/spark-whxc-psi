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

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_SA_SALE_AlreadyUseCredit() {
		super(TABLE_NAME);
		this.table.setTitle("�������ö��ά����");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_customerId = field = this.table.newPrimaryField(FN_customerId, TypeFactory.GUID);
		field.setTitle("�ͻ�id");
		field.setKeepValid(true);
		this.f_aleardyUseCredit = field = this.table.newField(FN_aleardyUseCredit, TypeFactory.NUMERIC(17,2));
		field.setTitle("�������ö��");
		field.setKeepValid(true);
		field.setDefault(ConstExpression.builder.expOf(0.0));
	}

}
