package com.spark.cms.storage.member;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;
import com.jiuqi.dna.core.def.exp.ConstExpression;

public final class TB_CMS_MEMBER_ACCOUNT extends TableDeclarator {

	public static final String TABLE_NAME ="CMS_MEMBER_ACCOUNT";

	public final TableFieldDefine f_moneyBalance;
	public final TableFieldDefine f_vantages;
	public final TableFieldDefine f_payPassword;

	public static final String FN_moneyBalance ="moneyBalance";
	public static final String FN_vantages ="vantages";
	public static final String FN_payPassword ="payPassword";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_CMS_MEMBER_ACCOUNT() {
		super(TABLE_NAME);
		this.table.setTitle("�����˻�");
		this.table.setCategory("ҵ������");
		TableFieldDeclare field;
		this.f_moneyBalance = field = this.table.newField(FN_moneyBalance, TypeFactory.NUMERIC(17,2));
		field.setTitle("�˻����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_vantages = field = this.table.newField(FN_vantages, TypeFactory.NUMERIC(17,2));
		field.setTitle("����");
		field.setDefault(ConstExpression.builder.expOf(0.0));
		this.f_payPassword = field = this.table.newField(FN_payPassword, TypeFactory.NVARCHAR(100));
		field.setTitle("֧������");
	}

}
